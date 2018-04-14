
        /* Assembly source for Master Boot Record (MBR) program.
 *
 * General Information:
 * --------------------
 * The Boot Loader (BL) is the first piece of software that get's
 * loaded by the Basic Input-Output System (BIOS) of a modern x86 PC from
 * the boot sector of a floppy disk or HDD.  The BL is loaded at address
 * 7c0:0 (Segment:Offset) by the BIOS.  Consequently, the starting
 * address of the BL must be 7C00.  Note that there is no OS present
 * when the BL is run.  Therefore, it must rely purely of BIOS calls
 * to do its job.
 *
 *     +--------------------------------------------------------------+
 *     |  When the machine boots up the processor is in REAL MODE!    |
 *     |  Therefore all of the BL code must be in 16-bit real mode!   |
 *     +--------------------------------------------------------------+	
 *
 * Compilation:	
 * ------------
 * The MBR program needs to be compiled into a raw binary program in the
 * following manner:
 *
 * as mbr.s -o mbr.o
 * ld -nostdlib --oformat binary -Ttext 0x7c00 -static mbr.o -o mbr.bin
 *
 * Installation:	
 * -------------
 * The MBR code is specifically ment to be placed in the boot sector
 * of FAT12 file system.  A special program needs to be used to copy the
 * MBR code into the boot sector. See BLinstall.c for details.
 * 
 */

.text         /* Start of text segment.  Indication to GNU assembler (as) */

.code16        /* Inform the assembler that the code is 16-bit, real mode */
	       /* In this mode the assembler flags warnings or errors     */
	       /* Regarding 32-bit code.                                  */

.global _start /* This global label is needed to keep the linker happy    */
	       /* as the linker always needs an entry point to work.      */

_start:
	/* Refer to the layout of the boot record for reasons why   */
	/* this code is organized in the following manner.          */
	/* There are 4 bytes at the begging after which the metadata*/
	/* on the floppy disk begins.  The following jump skips over*/
	/* the metadata.                                            */

	jmp begin    /* 2-byte instruction.  */
	nop          /* 1-byte filler        */

        /* The next 8 bytes contain an ASCII signature.  Note that the */
	/* signature must have 8 characters in it.                     */
signature:  .ascii "raodm   "

	/* Next 51 bytes contain the BIOS Parameter Block (BPB) that */
	/* contains information regarding the physical organization  */
	/* of the floppy disk.  This is read and filled in later on  */
	/* when the BL is installed on a properly formatted FS.      */
	/* The following assembler directive just creates suitable   */
	/* space and lables for easily referencing BPB fields.       */
bpb:
bytesPerSector:	.short 0   /* Size of a sector in bytes.             */
sectPerCluster:	.byte  0   /* No. of sectors in a cluster.           */
numResrSect   :	.short 0   /* Number of reserved sectors.            */
numFat        :	.byte  0   /* Number of FAT entries on floppy.       */
numRDEntries  :	.short 0   /* Number of root directory entries.      */
numSectors    :	.short 0   /* Total number of sectors on floppy.     */
mediaType     :	.byte  0   /* Media type indicator.                  */
numFatSectors :	.short 0   /* Number of sectors for each FAT         */
sectPerTrack  :	.short 0   /* Number of sectors in each track.       */
numHeads      :	.short 0   /* Number of heads or sides on floppy.    */
numHideSects  :	.long  0   /* Number of hidden sectors on floppy.    */
numSectorsBig :	.long  0   /* Number of sectors for large disks.     */
driveNumber   :	.byte  0   /* Physical drive ID.                     */
reserved      :	.byte  0   /* Reserved byte (not used).              */
osSignature   :	.byte  0   /* OS specific floppy signature.          */
volumeID      :	.long  0   /* A serial number for the floppy.        */
label         :	.space 11  /* A 11 character long volume lable.      */
fsType        :	.space 8   /* 8 characters long file system type.    */
       
begin:
	cli                   /* Disable interrupts to set all the   */
	                      /* segment registers to 0 */
	xorw %ax, %ax
	movw %ax, %ds
	movw %ax, %ss
	push %ss
	pop  %es

	movw $0x7c00, %sp     /* Setup stack pointer to use stack safely. */

	sti                   /* Enable interrupts */

	/* The above instruction completes initialization part.  Next */
	/* the MBR proceeds to display a message on the screen.        */
        movb $0, (%si)
Loop:
        cmpb $10, (%si)
        jg done
	/* Display a '*' on the screen */
	movb $0x00,   %bh /* Set the display page to be 0          */
	movb $10,     %bl /* set color to yellow                   */
	movw $0x013, %cx /* Set repetitions to 1                  */
	movb $'*',    %al /* Set character to be displayed         */
	movb $0x09,   %ah /* Display charachter with color.        */
	int  $0x10        /* Call BIOS to display character        */
        movb $0, %ah      /* Set ah to 0 to read a key             */
	int  $0x16        /* Read a key from keyboard.             */
	                  /* Now %al register has keystroke        */
        movb $'\r', %al
        movb $0, %bl
        movb $0xe, %ah
        movw $1, %cx
        int $0x10

        movb $'\n', %al
        movb $0, %bl
        movb $0xe, %ah
        movw $1, %cx
        int $0x10
        
	inc %si
        Jmp Loop
	/* Simulate a reboot of the machine */
done:   
	ljmp $0xffff, $0

/* The following variables or lables are used to point to appropriate */
/* Data in the MBR code                                               */
banner:	    .string "MBR program developed by raodm@miamiOH.edu\r\n"

/* The last two bytes of a valid boot sector must contain the bytes   */
/* 0x55 0xaa in that specific sequence.  If it is not present some of */
/* the BIOSes will not properly boot from a floppy.                   */

	
/* First ensure that the assembler puts the last two bytes at the     */
/* correct location by adding as many filler bytes as needed.         */
.=510

/* Next define the two bytes so that they are present at the end.     */
trailer:  .byte  0x55, 0xaa
