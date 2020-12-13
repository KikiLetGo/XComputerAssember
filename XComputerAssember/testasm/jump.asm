//init snake
movi r0,1
movi r1,4
sw r1,(0)r0
sw r1,(1)r0

//Show snake to screen
//load snake x into r4
movi r0,1
lw r4,(0)r0
//load snake y into r5
lw r5,(1)r0
//clk
movi r6,4
//flash
movi r6,2

//load snake x into r1
lw r1,(0)r0

//x = x+1
addi r1,r1,1

//rewrite new x into RAM
sw r1,(0)r0

//clear screen
//clk
movi r6,1


//Show snake to screen
//load snake x into r4
movi r0,1
lw r4,(0)r0
//load snake y into r5
lw r5,(1)r0
//clk
movi r6,4
//flash
movi r6,2

jump 9