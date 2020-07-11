package com.plm.test;

/**
 *  成员变量、局部变量区别
 *
 *      成员变量：
 *          1、声明位置：类中方法外
 *              类变量：有 static修饰的
 *              实例变量：没有 static修饰的
 *          2、修饰符：
 *              public、protected、private、final、static、volatile、transient
 *          3、值存储位置：
 *              实例变量：堆
 *              类变量：方法区
 *          4、作用域：
 *              实例变量：
 *                  当前类中 this. （可缺省）
 *                  其他类中 对象名.
 *              类变量：
 *                  当前类中 类名. （可缺省）
 *                  其他类中 类名. 或者 对象名.
 *
 *
 *      局部变量：
 *          1、声明位置：方法体{}中，形参，代码块{}中
 *          2、修饰符：final
 *          3、值存储位置：栈
 *          4、作用域：从声明处开始，到所属 } 结束
 *
 *   非静态代码块的执行：
 *      每次创建实例对象都会执行
 *
 *   方法的调用规则：
 *      调用一次执行一次
 *
 *
 *   当局部变量与XX变量重名时，如下区分：
 *      1、局部变量与实例变量重名
 *          在实例变量前面加 this.
 *
 *      2、局部变量与类变量重名
 *          在类变量前面加 类名.
 */
public class VariableDemo {
    static int s; // s 类变量，成员变量
    int i; // i 实例变量，成员变量
    int j; // j 实例变量，成员变量

    {
        int i = 1; // i 局部变量
        i++; // 就近原则，改变的是 局部变量i 的值
        // this.i; // 改变的是 实例变量i 的值
        j++; // 改变的是 实例变量j 的值
        s++; // 改变的是 类变量s 的值
    }

    public void test(int j){ // j 形参，局部变量
        j++; // 就近原则，改变的是 局部变量j 的值
        // this.j; // 改变的是 实例变量j 的值
        i++; // 改变的是 实例变量i 的值
        s++; // 改变的是 类变量s 的值
    }

    public static void main(String[] args) { // args 形参，局部变量
        VariableDemo variableDemo = new VariableDemo(); // variableDemo 局部变量
        VariableDemo variableDemo1 = new VariableDemo(); // variableDemo1 局部变量
        variableDemo.test(10);
        variableDemo.test(20);
        variableDemo1.test(30);

        // variableDemo i = 2, j = 1, s = 5
        System.out.println("variableDemo i = "+variableDemo.i+", j = "+variableDemo.j+", s = "+variableDemo.s);
        // variableDemo1 i = 1, j = 1, s = 5
        System.out.println("variableDemo1 i = "+variableDemo1.i+", j = "+variableDemo1.j+", s = "+variableDemo1.s);
    }
}
