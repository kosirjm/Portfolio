#Jonathan Kosir
#Greater Than
#Takes two numbers and returns the largest of the two
#Registers:
# 	$a0 - param syscall - string to print
#  	$v0 - param and return syscall
#	$t0 - first number
#	$t1 - second number
#	$t2 - biggest

li $v0, 5
syscall 
move $t0, $v0

li $v0, 5
syscall 
move $t1, $v0

bgt $t0, $t1, bigger_t0
move $t2, $t1
b  endif

bigger_t0:
move $t2, $t0

endif:

move $a0, $t2

li $v0, 1
syscall 

li $v0, 10
syscall 

#String Message
