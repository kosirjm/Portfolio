/** A simple driver to store characters into a memory buffer and echo
    the contents from the buffer back.
*/

#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/fs.h> 
#include <asm/uaccess.h> // required for copy_from and copy_to user functions 
#include <linux/semaphore.h>
#include <linux/cdev.h>
#include <linux/init.h>

#define BUF_SIZE 1024

// The major device number (obtained from kernel)
static int Major   = 0;

// Buffer to store the data to echo
char buffer[BUF_SIZE];

// Variable to track how many bytes are present in the buffer
static int bufUsed = 0; 

// Method called when a program opens the device
int open(struct inode *inode, struct file *filp) {
    printk(KERN_INFO "Inside open \n");
    // Currently nothing else to be done here for now.
    return 0;
}

// Function called when a program closes this device
int release(struct inode *inode, struct file *filp) {
    printk (KERN_INFO "Inside close \n");
    // Currently nothing else to be done here for now.
    return 0;
}

// Function called to read at most count bytes into
// user_buff. Currently, offp is ignored.
ssize_t read_char(struct file *filp, char *user_buff, size_t count,
                  loff_t *offp) {
    printk("Inside read \n");
    // Ensure we only provide as many bytes as we have in our buffer
    // and update the 'count' parameter appropriately
    // NOTE: Implement an if-statement.
    if(count > bufUsed){
	count = bufUsed;
    }


    // Copy necessary bytes to user space (uncomment line below)
     copy_to_user(user_buff, buffer, count);
    
    // Remove the data copied to the user from buffer (simple array
    // manipulation)
    // NOTE: Implement suitable for-loop here
    int i;
    for(i = count; i < bufUsed; i++){
      buffer[i-count] = buffer[i];
    }
    // Update pending data in the buffer (already implemented)
    bufUsed -= count;
    
    // Return the actual number of bytes copied instead of returning zero
    return count;
}

// Function called to store at most count bytes from
// user_buff. Currently offp is ignored.
ssize_t write_char(struct file *filp, const char *user_buff, size_t count,
		   loff_t *offp) { 
    printk(KERN_INFO "Inside write \n");
    if (bufUsed < BUF_SIZE) {
        // Ensure we only copy as much space as we have.
        if (bufUsed + count > BUF_SIZE) {
            count = BUF_SIZE - bufUsed;
        }
        copy_from_user(buffer + bufUsed, user_buff, count);
        bufUsed += count;
    }
    // Return the actual number of bytes written/stored.
    return count;
}

struct file_operations fops = {
    owner:   THIS_MODULE,
    read:    read_char,
    write:   write_char,
    open:    open,
    release: release
};


struct cdev *kernel_cdev; 

int char_arr_init (void) {
    int ret;
    dev_t dev_no;
    
    kernel_cdev = cdev_alloc(); 
    kernel_cdev->ops = &fops;
    kernel_cdev->owner = THIS_MODULE;
    printk (" Inside init module\n");
    ret = alloc_chrdev_region( &dev_no , 0, 1,"char_arr_dev");
    if (ret < 0) {
	printk("Major number allocation is failed\n");
	return ret; 
    }
    Major = MAJOR(dev_no);
    printk (KERN_INFO" The major number for your device is %d\n", Major); 

    ret = cdev_add(kernel_cdev, dev_no, 1);
    if(ret < 0 )  {
	printk(KERN_INFO "Unable to allocate cdev");
	return ret;
    }

    return 0;
}

void char_arr_cleanup(void) {
    printk(KERN_INFO " Inside cleanup_module\n");
    cdev_del(kernel_cdev);
    unregister_chrdev_region(Major, 1);
}

MODULE_LICENSE("GPL"); 
module_init(char_arr_init);
module_exit(char_arr_cleanup);
