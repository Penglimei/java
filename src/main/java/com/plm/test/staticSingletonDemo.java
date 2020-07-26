package com.plm.test;

/**
 *  单例模式：
 *      单例类在任何情况下都只存在一个实例，
 *      构造方法必须是私有的、
 *      由自己创建一个静态变量存储实例，
 *      对外提供一个静态公有方法获取实例
 *
 *
 *      静态内部类实现单例模式
 *
 *          当第一次加载 staticSingletonDemo 类时并不会初始化 singletonDemo,
 *          只有在第一次调用 staticSingletonDemo 的 getInstance方法时，
 *          才会导致 singletonDemo 被初始化。
 *
 *          这种方式不仅能够确保线程安全，也能保证单例对象的唯一性
 */
public class staticSingletonDemo {

    private staticSingletonDemo() {
    }

     private static class staticInnerClass {
         private static staticSingletonDemo singletonDemo = new staticSingletonDemo();
    }

    public static staticSingletonDemo getInstance(){
        return staticInnerClass.singletonDemo;
    }
}
