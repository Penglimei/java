package com.plm.test;

/**
 *  单例模式：
 *      单例类在任何情况下都只存在一个实例，
 *      构造方法必须是私有的、
 *      由自己创建一个静态变量存储实例，
 *      对外提供一个静态公有方法获取实例
 *
 *
 *      懒汉式
 *
 *      解决了饿汉式的实例对象初始化占用内存的情况，
 *      但是懒汉式存在线程安全的问题，当多个线程同时访问 getSingletonDemo()方法时，
 *      有可能在第一个线程进入if语句是还没new lazySingletonDemo()时，
 *      这时第二个线程判断if的时候就会为真，则会创建新的实例，单例模式设计失败。
 *
 *      需要在 getSingletonDemo()方法内加锁,双重检验锁
 *
 *      if(singletonDemo == null){
 *          synchronized(lazySingletonDemo.class){
 *              if(singletonDemo == null){
 *                  System.out.println("new lazySingletonDemo()");
 *                  singletonDemo = new lazySingletonDemo();
 *               }
 *          }
 *      }
 *
 *      表示线程同步，即当一个线程访问该方法时，其他线程需要等待其释放资源。
 */
public class lazySingletonDemo {

    // 不初始化实例对象
    private static lazySingletonDemo singletonDemo;

    // 构造方法私有化
    private lazySingletonDemo() {
    }

    // 当被调用时才动态实例化
    public static lazySingletonDemo getSingletonDemo() {
        if(singletonDemo == null){
            System.out.println("new lazySingletonDemo()");
            singletonDemo = new lazySingletonDemo();
        }
        return singletonDemo;
    }
}
