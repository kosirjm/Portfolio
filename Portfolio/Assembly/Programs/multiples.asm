#Jonathan Kosir
#Multiples
#takes two numbers prints out multiple every iteration until complete
#Registers:
#	$a0 - syscall param
#	$v0 - syscall return and param
#	$t0 - user input
#	$t1 - user input 2
#	$t2 - multiply
#	$t3 - stores $t1

.data 
space: .ascii " "
newline: .ascii "\n"

.text 
#get user input
li $v0, 5
syscall 
move $t0, $v0

#get user input 2 if <0 terminate
li $v0, 5
syscall 
move $t1, $v0
blez $t1, exit


mul  $t2, $t0, $t1
move $t3, $t0

loop:
move $a0, $t3
li $v0, 1
syscall 

beq $t2, $t3, endloop
add $t3, $t3, $t0

la $a0, space
li $v0, 4
syscall 

b loop

endloop:
la $a0, newline
li $v0, 4
syscall 

exit:
li $v0, 10
syscall 
##### The End #####



