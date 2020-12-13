movi r0,1
movi r1,4
sw r1,0(r0)
movi r1,4
sw r1,1(r0)

lw r6,0(r0)
lw r5,1(r0)
movi r4,4
movi r4,2

movi r0,1
lw r1,0(r0)
addi r1,r1,1
sw r1,0(r0)
lw r6,0(r0)
lw r5,1(r0)
movi r4,1
movi r4,4
movi r4,2