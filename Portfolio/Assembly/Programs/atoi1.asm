#Jonathan Kosir
#Atoi1.asm
#Converts string data into into data
#Registers:
#	$a0 - param
#	$v0 - param and return
#	$t0 - S:
#	$t1 - scratch space
#	$t2 - D: Sum
#	$a1 - 1024

.data 
newline: .asciiz "\n"
string_space: .space 1024

.text 
la $a0, string_space
li $a1, 1024
li $v0, 8
syscall 

la $t0, string_space
li $t2, 0

sum_loop:
	lb $t1, ($t0)
	addu $t0, $t0, 1
	
	beq $t1, 10, end_sum_loop
	
	mul $t2, $t2, 10
	
	sub $t1, $t1, '0'
	add $t2, $t2, $t1
	
	b sum_loop
	
end_sum_loop:
	move $a0, $t2
	li $v0, 1
	syscall 
	
	la $a0, newline
	li $v0, 4
	syscall 
	
	b exit
	
exit: 
	li $v0, 10
	syscall 

#the End##






	
