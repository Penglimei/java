package com.plm.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建方式：
 *  1、继承 Thread类，调用 start()方法
 *  2、继承 Runnable接口，重载 run()方法
 *  3、继承 Callable接口，重载 call()方法
 *  4、线程池
 */

// 1、继承 Thread类，调用 start()方法启动线程
class MyThread1 extends Thread{

    @Override
    public synchronized void run() {
        System.out.println("1、继承 Thread类，调用 start()方法");
    }
}

// 2、继承 Runnable接口，重载 run()方法，调用 start()方法启动线程
class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("2、继承 Runnable接口，重载 run()方法");
    }
}

// 3、继承 Callable接口，重载 call()方法，返回值用 FutureTask接口包裹，调用 start()方法启动线程
class MyThread3 implements Callable<String> {

    @Override
    public String call() {
        return "3、继承 Callable接口，重载 call()方法";
    }
}

public class CreateThreadDemo {

    public static void main(String[] args) {

        // Thread
        Thread thread1 = new Thread(new MyThread1(),"Thread");
        thread1.start();

        // Runnable
        Thread thread2 = new Thread(new MyThread2(),"Runnable");
        thread2.start();

        // Callable
        FutureTask<String> futureTask = new FutureTask<>(new MyThread3());
        Thread thread3 = new Thread(futureTask,"Callable");
        thread3.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
