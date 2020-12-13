movi r0,1//把x所在的RAM地址1存入r0
movi r1,4//把x初始值4存入r1
sw r1,0(r0)//把r1中的值“4”写入RAM的1号位置。

movi r1,4//把y初始值4存入r1
sw r1,1(r0)//把r1中的值“4”写入RAM的1号位置。