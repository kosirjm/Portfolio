#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "FAT12.h"

/** \file FAT12Tester.c

    \brief Test routines for FAT12Driver.

    This file essentially contains the main() function along with
    helper functions to test the working of the FAT12Driver.
*/

/**
   This is a utility function that is used to display a given number
   of characters (numChars) in a given string (str).  If numChars is
   less than string length (strlen) of str, then this method should
   print only strlen() characters rather than numChars.

   \note Use printf("%c") to display a character.

   \param str The string whose charachters are to be displayed.  If
   this parameter is NULL, then this function exits immediately.

   \param numChars The number of characters in str[] that must be
   displayed.  If this parameter is less than 1, then this function
   exits immediately.
*/
void printString(const char str[], const int numChars) {
  int i;
  if ((str == NULL) || (numChars < 1)) {
    // Nothing further to be done.
    return;
  }
  
  for(i = 0; ((i < numChars) && (str[i] != '\0')); i++) {
    printf("%c", str[i]);
  }
}

/**
   This function should use a series of printf() statements and
   display each field of the BPB.  For displaying string-like data
   (note they don't have a trailing '\0'!) use the displayString()
   helper function.

   Tip: You could use emacs in a split-window configuration to view
   the BPB structure in FAT12.h and develop this function.
   
   \note First you have to obtain the BPB information from the driver
   using the getBPB() function.
*/
void dumpBPB() {
  struct BPB bpb;
  getBPB(&bpb);
  printf("Floppy created using                   : ");
  printString(bpb.oemName, 11); printf("\n");
  printf("Bytes per sector                       : %d\n",bpb.bytesPerSector);
  printf("Sectors per cluster                    : %d\n",bpb.sectorsPerCluster);
  printf("Number of reserved sectors             : %d\n",bpb.reservedSectors);
  printf("Number of FAT directories              : %d\n",bpb.fatCount);
  printf("Max. # root directory file entries     : %d\n",bpb.rootEntries);
  printf("Total number of sectors                : %d\n",bpb.sectorsCount);
  printf("Media descriptor value                 : %d\n",bpb.mediaDescriptor);
  printf("Sectors occupied by each FAT directory : %d\n",bpb.sectorsPerFAT);
  printf("Sectors per track                      : %d\n",bpb.sectorsPerTrack);
  printf("Number of heads/sides                  : %d\n",bpb.headCount);
  printf("Number of hidden sectors               : %d\n",bpb.hiddenSectors);
  printf("Sectors per track (optional)           : %d\n",bpb.largeSectorsCount);
  printf("Driver number (usually not used)       : %d\n",bpb.driveNumber);
  printf("Current head  (usually not used)       : %d\n",bpb.currentHead);
  printf("Signature byte                       : %d\n",bpb.signature);
  printf("Volume ID value                      : %x\n",bpb.serialNumber);
  printf("Volume label                         : ");
  printString(bpb.volumeLabel, 11); printf("\n");
}
 
/** Function to print the FAT entry for a given cluster.

    This function is indirectly called from main() to print the FAT
    entry corresponding to a given cluster.  This information is
    essentially the next logical cluster (if any) to be read in the
    chain of clusters associated with some random entry.
    
    \param[in] cluster The cluster number for which the FAT entry is
    to be printed.  This value is always correct.
*/
void printFATEntry(int cluster) {
  struct BPB bpb;
  getBPB(&bpb);
  byte offset = cluster * 3/2;
  byte sector = (offset / bpb.bytesPerSector) + bpb.reservedSectors;
  byte buffer[512];
  readSector(sector, buffer);
}

/** This function lists all the files in the root directory in a fixed
    format.

*/
void ls() {
  // struct BPB bpb;
  // get(&bpb);
  // sector = //
  // bytes buffer[512];
  // readSector(sector, buffer);
  // struct FileEntry *fe = struct FileEntry *) buffer;
  //printString(fe->fileName,11);
  //printf('n');
}

/** This function prints the clusters associated with each file in the
    root directory.
*/
void lsFAT() {
}

/** This function prints the data for file in both hex and character
    formats
*/
void dump(const char *fileName) {
  if (fileName == NULL) {
    return;
  }
}

//----------------------------------------------------------------------
//       DO    NOT    MODIFY    CODE    BELOW    THIS    LINE 
//----------------------------------------------------------------------

/**
   Convenience method that calls the helper function corresponding to
   a given command-line argument.

   \param argv An array of strings (note that strings are arrays of
   charachters) containing the arguments in the same order it was
   specified when this tester was run.
*/
void dispatch(char *argv[]) {
  const char ValidCommands[] = "bpb  ls   dump lsfatnext";
  // Determine a command code by searching for the command in the list of
  // valid commands, subtracting their pointers to obtain index and divide
  // it by 5 to get 0 for bpb, 1 for ls, and 2 for dump.
  int commandCode = (strstr(ValidCommands, argv[2]) - ValidCommands) / 5;
  switch (commandCode) {
  case 0: dumpBPB();
    break;
  case 1: ls();
    break;
  case 2: dump(argv[3]);
    break;
  case 3: lsFAT();
    break;
  case 4: printFATEntry(atoi(argv[3]));
    break;
  default:
    printf("Unhandled command specified.\n");
  }    
}

/**
   The main method that invokes various functions in the FAT12 tester.
   This method essentially checks if the sufficient and valid command
   line parameters have been sepcified.  If not it reports an error
   and quits.  Otherwise it calls the appropriate helper functions to
   do the actual task.

   \param argc The number of command line arguments passed to main.
   This value is auotmatically filled in by the OS when this program
   is run.

   \param argv An array of strings (note that strings are arrays of
   charachters) containing the arguments in the same order it was
   specified when this tester was run.

   \return This method returns an exit code back to the operating
   system.
*/
int
main(int argc, char *argv[]) {
  // First check to ensure we have enough valid parameters to operate with.
  if ((argc < 3) || (argc > 4) ||
      (!strcmp(argv[2], "dump") && (argc == 3)) ||
      (!strcmp(argv[2], "fat")  && (argc == 3))) {
    printf("Invalid number of command line arguments provided.\n");
    printf("Usage: %s <floppy device or image file> "
	   "[bpb | ls | dump <fileName> | lsfat | next <cluster>]\n", argv[0]);
    return 1;
  }

  // Ok, now that we have valid parameters, the first task is to
  // initialize the FAT12Driver.
  printf("Initializing FAT12 driver to process %s ... \n", argv[1]);
  FAT12ErrorCodes errorCode = initializeFAT12(argv[1]);
  if (errorCode != SUCCESS) {
    printf("Initialization of driver failed (errorCode=%d)\n", errorCode);
    return errorCode;
  }
  printf("FAT12 driver initialized successfully.\n");
  // Run the supplied command
  dispatch(argv);
  // Finally wrap up the driver and use its return value as overall
  // exit status.
  printf("Finalizing  FAT12 driver.\n");
  return finalizeFAT12();
}
