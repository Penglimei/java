package com.plm.test;

/**
 * 双重锁单例模式
 */

public class Singleton {

    /**
     * volatile 禁止指令重排
     *       并发在此处容易出现问题
     *       singleton = new Singleton();
     */
    private static volatile Singleton singleton = null;

    public Singleton(){
        System.out.println("Singleton 的构造函数");
    }

    public static Singleton getSingleton(){

        if(singleton == null){

            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {

        // 并发多线程
        for(int i=1;i<=10;i++){
            new Thread(()-> {
                Singleton.getSingleton();
            },String.valueOf(i)).start();
        }
    }
}
