#Jonathan Kosir
#Hello World
# Registers:
# 	$a0 - param syscall - string to print
#  	$v0 - param and return syscall

#String Message
	.data 
hello_msg:
	.ascii "Hello World\n"
	
#Execute print message
	.text 
la $a0 hello_msg
li $v0, 4
syscall 

#Close
li $v0, 10
syscall 
#end hello world
