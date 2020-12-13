//init snake
//set snake direction=1
movi r0,0
movi r1,1
sw r1,(0)r0
//set snake x=4,y=4
movi r0,1
movi r1,4
sw r1,(0)r0
sw r1,(1)r0

jump SHOW

MOVE_SNAKE:
movi r0,0
lw r1,(0)r0
movi r0,1
beq r0,r1,RIGHT
movi r0,0
beq r0,r1,LEFT

movi r0,2
beq r0,r1,UP

movi r0,3
beq r0,r1,DOWN

RIGHT:
//load snake x into r1
movi r0,1
lw r1,(0)r0
//x = x+1
addi r1,r1,1
//rewrite new x into RAM
sw r1,(0)r0
jump OUT

LEFT:
//load snake x into r1
movi r0,1
lw r1,(0)r0
//x = x+1
subi r1,r1,1
//rewrite new x into RAM
sw r1,(0)r0
jump OUT

UP:
//load snake x into r1
movi r0,1
lw r1,(1)r0
//x = x+1
addi r1,r1,1
//rewrite new x into RAM
sw r1,(1)r0
jump OUT

DOWN:
//load snake x into r1
movi r0,1
lw r1,(1)r0
//x = x+1
subi r1,r1,1
//rewrite new x into RAM
sw r1,(1)r0
jump OUT

OUT:

//Show snake to screen
SHOW:
//clear screen
//clk
movi r6,1
//load snake x into r4
movi r0,1
lw r4,(0)r0
//load snake y into r5
lw r5,(1)r0
//clk
movi r6,4
//flash
movi r6,2

jump MOVE_SNAKE
