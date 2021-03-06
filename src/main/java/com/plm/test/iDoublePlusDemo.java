package com.plm.test;

/**
 *  知识点涉及
 *      局部变量表
 *      操作数栈
 *
 *      赋值 = ，最后计算
 *      = 右边的从左到右加载值依次压入操作数栈
 *      实际先算哪一个，看运算符优先级
 *      自增、自减操作都是直接修改局部变量表中该变量的值，不经过操作数栈
 *      最后的赋值之前，临时结果也是存储再操作数栈中，操作栈中的值赋给局部变量表中对应的变量
 */
public class iDoublePlusDemo {

    public static void main(String[] args) {
        // 局部变量表中存入i=1
        int i = 1;
        // 先针对 i++  操作
        // 1、将局部变量表中 i 的值压入操作数栈，操作数栈中 i=1
        // 2、i++ 开始自增，局部变量表中 i=2
        // 再针对 i = i++ 操作
        // 3、将操作数栈中的值 i=1 赋给 局部变量表中 i，局部变量表中 i=1
        i = i++; // 等价于 int temp = i; i++; i = temp;
        // 先针对 i++ 操作
        // 1、把局部变量表中 i 的值压入操作数栈中，局部变量表中 i=1，操作数栈中 i=1
        // 2、i++ 开始自增，局部变量表中 i=2
        // 再针对 j=i++操作
        // 3、把操作数栈中的 i=1 值赋给 局部变量表中 j,局部变量表中i=2， j=1
        int j = i++;
        // 先针对 i 操作
        // 1、将局部变量表中 i 的值压入操作数栈，操作数栈中 i=2
        // 再针对 ++i 操作
        // 2、开始自增，局部变量表中 i=3，j=1
        // 3、将局部变量表中 i 的值压入操作数栈，操作数栈中 i=3，i=2
        // 再针对 i++ 操作
        // 4、将局部变量表中 i 的值压入操作数栈，操作数栈中 i=3，i=3，i=2
        // 5、i++ 开始自增，局部变量表中 i=4，j=1
        // 再针对 ++i * i++ 操作
        // 6、从操作数栈中取出 i ，进行乘法运算 3*3=9，再将 i 存入操作数栈，操作数栈中 i=9，i=2
        // 再针对 i + ++i * i++ 操作
        // 7、从操作数栈中取出 i ，进行加法运算 9+2=11，再将 i 存入操作数栈，操作数栈 i=11
        // 最后针对 k = i + ++i * i++ 操作
        // 8、从操作数占取出 i 赋给 局部变量表中 k ，局部变量表中 k=11,j=1,i=4
        int k = i + ++i * i++;

        System.out.println("i = "+i); // i = 4
        System.out.println("j = "+j); // j = 1
        System.out.println("k = "+k); // k = 11
    }
}
