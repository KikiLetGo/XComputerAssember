movi r0,1
movi r1,4
movi r2,4
sw r1,0(r0)
sw r2,1(r0)
mov r5,r1
mov r6,r2
movi r4,4
movi r4,2
lw r1,0(r0)
lw r2,1(r0)
mov r5,r1
mov r6,r2
movi r4,1//clr
movi r4,4
movi r4,2
addi r1,r1,1
sw r1,0(r0)
jump 9