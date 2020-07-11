package com.plm.test;

/**
 * 父、子类中
 *      类初始化过程
 *          类要创建实例需要先加载初始化该类
 *          main方法所在的类需要先加载和初始化
 *          一个子类要初始化需要先初始化父类
 *          由静态类变量显示赋值代码和静态代码块组成
 *          根据从上到下的顺序执行，且只执行一次
 *
 *      实例初始化过程
 *          由非静态实例变量显示赋值代码、非静态代码块、对应构造器代码组成
 *          非静态实例变量显示赋值代码和非静态代码块根据从上到下的顺序执行，且只执行一次
 *          构造器代码最后执行
 *          先执行父类实例初始化
 *          实例创建了几次，初始化就执行了几次
 *
 *      方法重写
 *          子类不可以重写的方法：
 *              final方法
 *              静态方法
 *              private等子类中不可见的方法
 *
 *          要求：
 *              子类中重写的方法访问修饰符 大于等于 父类
 *              子类中重写的方法返回值类型、方法名、参数列表 与 父类中的相同
 *              子类中重写的方法异常抛出异常范围 小于等于 父类
 *
 *          对象的多态性：
 *              子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的的代码
 *              非静态方法默认的调用对象是this
 *
 *      执行顺序：
 *          父类的静态代码块/静态属性赋值
 *          子类的静态代码块/静态属性赋值
 *          父类的普通初始化块/普通属性初始化
 *          父类的构造函数
 *          子类的普通初始化块/普通属性初始化
 *          子类的构造函数
 *
 *
 */

class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }

    public Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    // 子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的的代码
    public int test(){
        System.out.print("(4)");
        return 1;
    }

    public static int method(){
        System.out.print("(5)");
        return 1;
    }
}

class Son extends Father {
    private int i= test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }

    public Son() {
        System.out.print("(7)");
    }
    {
        System.out.print("(8)");
    }

    public int test(){
        System.out.print("(9)");
        return 1;
    }
    public static int method(){
        System.out.print("(10)");
        return 1;
    }
}
public class InitialDemo {
    public static void main(String[] args) {
        // (5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
        Son son = new Son();
        System.out.println();
        System.out.println();
        // (9)(3)(2)(9)(8)(7)
        Son son1 = new Son();
    }
}
