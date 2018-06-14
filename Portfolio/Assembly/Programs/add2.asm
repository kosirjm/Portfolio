# Jonathn Kosir
#Add2
#Prints sum of numbers inputed by user at runtime
#Regsters Used:
#	$t0 - holds first number
#	$t1 - holds second number
#	$t2 - holds the sum
#	$v0 - syscall param and return
#	$a0 - syscall param

li $v0, 5 #load syscall read int
syscall 
move $t0, $v0 #move value inputed to new reg

#Get second num
li $v0, 5 
syscall 
move $t1, $v0

#Add
add $t2, $t0, $t1

#Print
move $a0, $t2
li $v0, 1
syscall

#Exit
li $v0, 10
syscall

#end add2.asm
