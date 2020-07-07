package com.plm.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者-ReentrantLock实现方式
 *
 *      如： 一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，重复5轮
 *
 *      1、线程 操作 资源类
 *      2、判断 执行 通知
 *      3、防止虚假唤醒机制 用 while 判断资源数目 而不是 if
 */

// 共享资源类
class ShareDate{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 生产
    public void increment() throws Exception{

        lock.lock();

        try {
            // 1 判断
            while (number != 0){
                // 等待，不生产
                condition.await();
            }
            // 2 生产
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3 通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    // 消费
    public void decrement() throws Exception{

        lock.lock();

        try {
            // 1 判断
            while (number == 0){
                // 等待，不消费
                condition.await();
            }
            // 2 消费
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3 通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class ProducerConsumer_traditionDemo {

    public static void main(String[] args) {

        ShareDate shareDate = new ShareDate();

        // 线程AA 生产一个
        new Thread(() -> {
            for(int i=1;i<=5;i++){
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        // 线程BB 消费一个
        new Thread(() -> {
            for(int i=1;i<=5;i++){
                try {
                    shareDate.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
