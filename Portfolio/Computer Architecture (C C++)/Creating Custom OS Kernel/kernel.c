/* This macro is necessary to inform the assembler to generate 16-bit
   code even though gcc generates 32-bit code.
*/
asm(".code16gcc\n");

#include "BiosUtils.h"

// Forward declaration to make compiler happy
void timerISR();

void _start() {
    // Set up background timer-based operations
    setIVT(0x1c, (int) timerISR);

    // Display a simple startup message (this can be made fancy using the
    // drawBox function in BiosUtils.h
    clearScreen(MAKE_COLOR(BLACK,BLACK));
    drawBox(2,2,60,5,MAKE_COLOR(BLACK,BLUE));
    moveCursor(4,4);
    displayString("Jonathan Kosir!\r\n", MAKE_COLOR(BLACK, WHITE));
    moveCursor(9,2);
    // The operations of the OS go here. For now, it does nothing
    // other than pretend to process keystrokes.
    char count[16];
    count[0] = '0';
    count[1] = '1';
    count[2] = '2';
    count[3] = '3';
    count[4] = '4';
    count[5] = '5';
    count[6] = '6';
    count[7] = '7';
    count[8] = '8';
    count[9] = '9';
    count[10] = 'A';
    count[11] = 'B';
    count[12] = 'C';
    count[13] = 'D';
    count[14] = 'E';
    count[15] = 'F';
    short colorNum = 9;
    char line[82];
    short letter = 0;
    short space = 1;
    do {
  
	// Get a keystroke
	byte key = getKeyStroke();
	line[letter] = key;
	displayChar(key, colorNum);
	// Fake "enter" key moves to next line (via helper function)

	if (key == '\r') {
	 
	  if(colorNum == 15);
	  {
	    colorNum = 9;
	  }
	    displayString("Enter A Sentence: ", colorNum);
	    displayChar('\n', colorNum);
	   
	    displayString("Your Sentenence Backwards: ", colorNum);
	    int i = letter;
	   
	    while(i > 0)
	      {
		displayChar(line[i],colorNum);
		i--;
	      }

	    displayChar('\n', colorNum); 
	    
	    displayString("Word Count 0x",colorNum);
	    displayChar(count[space], colorNum);
	    displayChar('\n', colorNum);
	    letter = 0;
	    space = 0;
	    colorNum++;
	}
       
	if(key == '\s');
	{
	  space++;
	}
	letter++;
    } while (true);
}

/**
   A simple timer Interrupt Service Routine (ISR). This function is
   automatically invoked by the BIOS 19 times a second.  It can be
   used for context switching or performing some short background
   operations.  NOTE: Keep this method fast!
*/
void timerISR() {
    // The following line is crtical & must be the first line in this function
    ISR_CALLER_PROLOGUE();

    // The body of the ISR goes here!

    // The following line is critical & must be the last line in this function
    ISR_CALLER_EPILOGUE();
}

// End of source code.
