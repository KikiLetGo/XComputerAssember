//init snake
movi r0,1
movi r1,4
sw r1,(0)r0
sw r1,(1)r0

//load snake x into register
lw r1,(0)r0

//x = x+1
addi r1,r1,1

//rewrite new x into RAM
sw r1,(0)r0

