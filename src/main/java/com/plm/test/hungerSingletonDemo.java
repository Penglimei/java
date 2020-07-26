package com.plm.test;

/**
 *  单例模式：
 *      单例类在任何情况下都只存在一个实例，
 *      构造方法必须是私有的、
 *      由自己创建一个静态变量存储实例，
 *      对外提供一个静态公有方法获取实例
 *
 *
 *      饿汉式
 *          饿汉式是线程安全的，
 *          不管系统的那一个线程获取这个对象，
 *          他们都是该类同一个对象。
 *          缺点是在程序在一开始就创建了该对象，
 *          占用内存空间。
 */
public class hungerSingletonDemo {
    // 静态成员变量
    private static hungerSingletonDemo singletonDemo = new hungerSingletonDemo();

    // 构造函数私有化
    private hungerSingletonDemo() {
    }

    // 返回静态资源中Singleton实例
    public static hungerSingletonDemo getSingletonDemo() {
        return singletonDemo;
    }
}
