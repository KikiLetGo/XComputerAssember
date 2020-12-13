//label
foodX .word 0
foodY .word 1
snakeLenPos .word 3
stepXPos .word 4
stepYPos .word 5
direction .word 6
snakeBasePos .word 100
i .word 7
Init:
	//init snake head
	movi r0,4
	movi r1,snakeBasePos
	sw r0,0(r1)//设置蛇头初始X位置
	sw r0,1(r1)//设置蛇头初始Y位置
	//r0,r1使用完毕
	movi r0,5
	movi r1,snakeLenPos
	sw r0,0(r1)//设置蛇长度
	//r0,r1使用完毕


	//init step and direction
	movi r0,1
	movi r1,stepXPos
	sw r0,0(r1)//设置X方向速度为1
	movi r0,1
	movi r1,stepYPos
	sw r0,0(r1)//设置y方向速度为0

	movi r0,0//1是向右转
	movi r1,direction
	sw r0,0(r1)//设置方向

START:
CalI:
    movi r0,snakeLenPos
    lw r1,0(r0)//r1->蛇长snakeLen
    add r1,r1,r1//r1->2*snakeLen
    movi r2,snakeBasePos//r2->snakeBasePos
    addi r2,r2,2//r2->snakeBasePos+2
    sub r2,r2,r1//r2->snakeBasePos+2-2*snakeLen
    movi r0,i
    sw r2,0(r0)//把末尾索引存到i中


UpdateSnakeBody:
    //r0存放索引地址，r1存放索引值
    movi r0,i
    lw r1,0(r0)//读出索引i位置的值
    movi r2,snakeBasePos
    beq r1,r2,UpdateSnakeHead//遇到蛇头

    lw r3,2(r1)//读取m[i+2]位置的值，也就是上一个点的x
    sw r3,0(r1)//把m[i+2]的值存入当前m[i]中，更新X
    lw r3,3(r1)//读取m[i+3]位置的值，也就是上一个点的
    sw r3,1(r1)//把m[i+3]的值存入当前m[i+1]中，更新y


    addi r1,r1,2//ivalue=ivalue+2
    sw r1,0(r0)
    jump UpdateSnakeBody

UpdateSnakeHead:
    movi r0,snakeBasePos
    movi r3,direction
    lw r3,0(r3)
    //分支语句，类似于switch
    movi r2,0
    beq r3,r2,LeftMove
    movi r2,1
    beq r3,r2,RightMove
    movi r2,2
    beq r3,r2,UpMove
    movi r2,3
    beq r3,r2,DownMove
    jump UpdateHeadEnd
//case:0
LeftMove:
    lw r1,0(r0)//读取蛇头X值
    movi r2,1
    sub r1,r1,r2//r1->X-1
    sw r1,0(r0)
    jump UpdateHeadEnd//break
//case:1
RightMove:
    lw r1,0(r0)//读取蛇头X值
    addi r1,r1,1//r1->X+stepX
    sw r1,0(r0)
    jump UpdateHeadEnd
//case:2
UpMove:
    lw r1,1(r0)//读取蛇头Y值
    addi r1,r1,1//r1->Y+stepY
    sw r1,1(r0)
    jump UpdateHeadEnd
//case:3
DownMove:
    lw r1,1(r0)//读取蛇头Y值
    movi r2,1
    sub r1,r1,r2//r1->X+stepX
    sw r1,1(r0)
UpdateHeadEnd:

Draw:
    movi r0,snakeBasePos
    movi r1,i
    sw r0,0(r1)//蛇头x位置存入索引
CLENSCREEN:
    movi r4,9
DrawNext:
    //r1表示索引地址
    movi r1,i
    //r0表示当前索引值
    lw r0,0(r1)//读取当前索引

    lw r2,0(r0)//读取x
    mov r5,r2//设置cols
    lw r2,1(r0)//读取y
    mov r4,r2//设置rows
    movi r6,1//驱动显示寄存器输入
    movi r6,0//驱动显示

    movi r2,snakeLenPos//
    lw r2,0(r2)//读取蛇长snakeLen
    movi r3,1
    sub r2,r2,r3//r2->snakeLen-1
    add r2,r2,r2//r2->2*(snakeLen-1)
    movi r3,snakeBasePos
    sub r3,r3,r0
    beq r2,r3,Loop
    movi r2,2
    sub r0,r0,r2//索引-2
    sw r0,0(r1)//更新索引
    jump DrawNext
Loop:
    movi r6,2//驱动显示寄存器输出
    movi r6,0//驱动显示
    jump UpdateSnakeBody

//400
INT0:
    movi r0,direction
    movi r1,0
    sw r1,0(r0)
    jump START
//500
INT1:
    movi r0,direction
    movi r1,1
    sw r1,0(r0)
    jump START
//600
INT2:
    movi r0,direction
    movi r1,2
    sw r1,0(r0)
    jump START
//700
INT3:
    movi r0,direction
    movi r1,3
    sw r1,0(r0)
    jump START

