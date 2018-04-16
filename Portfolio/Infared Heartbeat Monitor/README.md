# HEARTBEAT MONITOR (EMBEDDED SYSTEM DESIGN)
-----------------------

DESCRIPTION
-----------------------
In this project we created an embedded system device that could monitor a heartbeat via a cell phone.  This project was created to get a better understanding of embedded systems and their development.  With this we utilized a Arduino UNO, bluetooth shield, a infrared sensor and an android phone.  A big part of this was to follow real world issues such as size, weight, cost, and ease of use for the person wearing the device. Data was acquired from a infrared sensor connected to a person's ear which sent voltage according to blood flow through the ear.  This was then sent to the arduino either in the person's pocket or attached to the person belt.  The Arduino then analyzed the data and sent the necessary information to an Android cell phone app which then displayed what was needed.

KNOWLEDGE GAINED
------------------------
* An understanding of AVR microcontrollers and C programming
* Embedded system design methodology
* Bitwise operations
* Interrupts, timer/counter, and pulse width modulation
* Communication protocols
* Analog to Digital Conversion, Analog Comparator
* Real world design plans and options.  Such as certain trade offs and challenges between hardware and software

FILES
------------------------
Disclaimer:  These files were done for a course that was taken.  Some files may be poorly commented, not completely working. or made much more robust and user friendly.  This is due to either time constraints or simply lack of knowledge of the subject at time of creation.  At some point I may go back and fix up everything, but for now this is here to provide an idea of thought process and an idea of general ability in this subject matter.

**Code(Folder)**
	**ArduinoCode.c** - Our code programmed into our arduino
	**HeartBeatReport.pdf** - Our final report for this project
	