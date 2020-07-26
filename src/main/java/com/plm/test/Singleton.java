package com.plm.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 单例模式
 *      饿汉式：直接创建对象--》线程安全
 *          直接实例化饿汉式
 *          枚举式
 *          静态代码块饿汉式
 *
 *      懒汉式：延迟创建对象
 *          简单懒汉式
 *          双重检验锁方式实现单例模式--》线程安全
 *          静态内部类实现单例模式--》线程安全
 */

// 直接实例化饿汉式
class Singleton1 {

    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getSingleton1() {
        return singleton1;
    }
}

// 枚举式
enum Singleton2 {

    singleton2;
}

// 静态代码块饿汉式
class Singleton3 {

    private static Singleton3 singleton3;

    static {
        singleton3 = new Singleton3();
    }

    private Singleton3() {
    }

    public static Singleton3 getSingleton3() {
        return singleton3;
    }
}

// 简单懒汉式
class Singleton4 {

    private static Singleton4 singleton4 = null;

    private Singleton4() {
    }

    public static Singleton4 getSingleton4() {
        if(singleton4 == null){
            singleton4 = new Singleton4();
        }
        return singleton4;
    }
}

// 双重检验锁实现单例模式
class Singleton5 {
    /**
     * volatile 禁止指令重排
     *       并发在此处容易出现问题
     *       singleton = new Singleton();
     */
    private static volatile Singleton5 singleton5 = null;

    private Singleton5(){
        System.out.println("Singleton 的构造函数");
    }

    public static Singleton5 getSingleton5(){

        if(singleton5 == null){

            synchronized (Singleton5.class){
                if(singleton5 == null){
                    singleton5 = new Singleton5();
                }
            }
        }
        return singleton5;
    }
}

// 静态内部类实现单例模式
class Singleton6 {

    private Singleton6() {
    }

    public static class InnerSClass {

        private static Singleton6 singleton6 = new Singleton6();
    }

    public static Singleton6 getSingleton6(){
        return InnerSClass.singleton6;
    }
}

public class Singleton{
    public static void main(String[] args) throws Exception {

        // 并发多线程
        Callable<Singleton6> callable = new Callable<Singleton6>() {
            @Override
            public Singleton6 call() throws Exception {
                return Singleton6.getSingleton6();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton6> future = executorService.submit(callable);
        Future<Singleton6> future1 = executorService.submit(callable);

        Singleton6 singleton = future.get();
        Singleton6 singleton1 = future1.get();

        System.out.println("singleton == singleton1 ?"+(singleton == singleton1));
        System.out.println("singleton : "+singleton);
        System.out.println("singleton1 : "+singleton1);
    }
}
