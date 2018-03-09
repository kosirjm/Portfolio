#Jonathan Kosr
#Palindrome
#Takes in string from user and the tells user if string is palindrome
#Registers:
#	$a0 - syscall param
#	$v0 - sysycall param return
#	$t1 - palindrome beginning
#	$t2 - palindrome End
#	$t3 - Bginning Byte
#	$t4 - End Byte


.text 
la $a0, pali
li $a1, 1024
li $v0, 8
syscall

la $t1, pali

la $t2, pali

length_loop:
lb $t3, ($t2)
beqz $t3, end_length_loop
addu $t2, $t2, 1
b length_loop

end_length_loop:
subu $t2, $t2, 2

test_loop:
bge $t1, $t2, is_pali

lb $t3, ($t1)
lb $t4, ($t2)
bne $t3, $t4, not_pali

addu $t1, $t1, 1
subu $t2, $t2, 1
b test_loop

is_pali:
la $a0, isA_Mes
li $v0, 4
syscall 
b exit

not_pali:
la $a0, isNot_Mes
li $v0, 4
syscall 
b exit

exit:
li $v0, 10
syscall 

.data 
pali: .space 1024
isNot_Mes: .asciiz "This is not a palidrome\n"
isA_Mes: .asciiz "This is a palidrome\n"
#### end of palindrome.asm
#### The End #####

