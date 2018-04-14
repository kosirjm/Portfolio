/** A simple FAT-12 driver based on FUSE */

// Use latest version of FUSE API
#define FUSE_USE_VERSION 26

#include <fuse.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <unistd.h>
#include "FAT12Utils.h"
#include <math.h>

// Prototype / Declaration for convenience function defined further on.
char* getFAT12FileName(const char name[], char fatName[]);
    

/** Get's the attribute for a given file or the root directory.

    This is a FUSE API function that is used to obtain the file
    attributes for a given file or directory.  This method only
    handles entries for the root directory.

    Refer to the solution from previous exercise to help complete this
    function.  The findFileEntry() function (defined in FAT12Utils.h)
    and the getFAT12FileName() function (defined in this file) will
    come in handy to implement this function.
    
    \param[in] path The path to the file whose attributes are to be
    retrieved.

    \param[out] stbuf The Linux stat buffer to be populated.

    \return This method returns 0 on success or -ENOENT on errors.
*/
int fat12_getattr(const char *path, struct stat *stbuf) {
    // Clear out the stat buffer first.
    memset(stbuf, 0, sizeof(struct stat));
    // Handle root directory suitably.
    time_t time = 0;
    if (strcmp(path, "/") == 0) {
        stbuf->st_mode  = S_IFDIR | 0755;
        stbuf->st_nlink = 2;
        stbuf->st_size  = 4096;
    } else {
	// Implement more code here
        stbuf->st_mode  = S_IFREG | 0444;
        // Ensure that this file name is valid!
        stbuf->st_nlink = 1;
    }
    stbuf->st_uid   = getuid();
    stbuf->st_gid   = getuid();
    stbuf->st_atime = time;
    stbuf->st_mtime = time;
    stbuf->st_ctime = time;

    
    return 0; // success
}

/** FUSE API method to read all file entries in a given directory.

    This is a convenience method that is used to read all the root
    directory entries into the given buffer. This function currently
    handles only root directory entries.  Consequently, the path must
    be "/".

    Refer to the solution from previous exercise to help complete this
    function.  The getFileEntry() function defined in FAT12Utils.h
    will come in handy here.

    \return This function returns -ENOENT if path is invalid.
    Otherwise it returns 0 for success.
*/
int fat12_readdir(const char *path, void *buf, fuse_fill_dir_t filler,
                  off_t offset, struct fuse_file_info *fi) {
    // Tell compiler we are intentionally not using 2 parameters
    (void) offset;
    (void) fi;
    // Only handle root directory on FAT-12.
    if (strcmp(path, "/") != 0) {
        return -ENOENT;
    }
    
    filler(buf,".", NULL,0);
    filler(buf,"..",NULL,0);
    
    int i;
    
    for(i = 0;  i > 0; i++){
      struct FileEntry fe;
      if(getFileEntry(path+1, &fe) != "SUCCESS"){
	return -ENOENT;
      }
     
      /**char buf[12];
      strncopy(buf,fe.fileName,11);
      buf[11]='\0';*/

      filler(buf, fe.fileName, NULL, 0);
    }

    return 0;
}

//------------------------------------------------------------------
//            DO  NOT  MODIFY  CODE  BELOW  THIS  LINE
//------------------------------------------------------------------

int fat12_open(const char *path, struct fuse_file_info *fi) {
    // Tell compiler we are intentionally not using 1 parameter
    (void) fi;
    // Ensure that the file is valid and is not a directory etc.
    struct stat stbuf;
    if ((fat12_getattr(path, &stbuf) != 0) || !(stbuf.st_mode & S_IFREG)) {
        return -ENOENT;
    }
    // We just logically assume this file to be now open. We don't really
    // use inode information etc.
    return 0;
}

/**
   Convenience function to convert regular file names in the form
   "TEST.TXT" to FAT-12 file names in the form "TEST    .TXT" to ease
   searching for files in the root directory.
*/
char* getFAT12FileName(const char name[], char fatName[]) {
    // Initialize fatName with 11 blank spaces.
    strcpy(fatName, "           ");
    // Convert the file name to 8.3 form with trailing '\0'
    const char *dotPos = strchr(name, '.');
    if (dotPos == NULL) {
        strncpy(fatName, name, strlen(name));
    } else {
        // Copy the file name first.
        strncpy(fatName, name, dotPos - name);
        strncpy((fatName + 8), dotPos + 1, 3);
    }
    printf("Fat name = %s\n", fatName);
    return fatName;
}

int fat12_read(const char *path, char *buf, size_t size, off_t offset,
               struct fuse_file_info *fi) {
    // Tell compiler we are intentionally not using 1 parameter
    (void) fi;
    // Load the entire FAT-12 file into a temporary buffer
    byte buffer[16000];
    char fat12name[16];
    int  fileSize = 0, bytesCopied = 0;
    if (readFile(getFAT12FileName(path + 1, fat12name), buffer,
                 sizeof(buffer), &fileSize) != SUCCESS) {
        return -ENOENT;
    }
    // Compute and copy th ebytes we can copy given size, offset, and file size.
    if (offset < fileSize) {
        bytesCopied = fmin(size + offset, fileSize) - offset;
        memcpy(buf, buffer + offset, bytesCopied);
    }
    return bytesCopied;
}

struct fuse_operations fat12_oper = {
    .getattr = fat12_getattr,
    .readdir = fat12_readdir,
    .open    = fat12_open,
    .read    = fat12_read,
};

int main(int argc, char *argv[]) {
    if (argc < 3) {
        printf("Usage: %s <MountPoint> <FAT12-Image-File-Name>\n", argv[0]);
        return 1;
    }
    // Initialize the FAT-12 driver
    if (initializeFAT12(argv[argc - 1]) != SUCCESS) {
        printf("Unable to initialize FAT-12 driver "
               "using file: %s\n", argv[argc - 1]);
        return 2;
    }
    // Startup the FUSE driver system
    int retVal = fuse_main(argc - 1, argv, &fat12_oper, NULL);
    // Fianlize the FAT-12 driver
    finalizeFAT12();
    // Return status back
    return retVal;
}

// End of source code.
