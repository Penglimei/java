package com.plm.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  ReentrantLock可以绑定多个条件，而Synchronized不能
 *      ReentrantLock实现精确唤醒需要的线程
 *
 *      如； 多线程之间按顺序调用，实现A->B->C三个线程的启动，要求
 *          AA打印5次，BB打印10次，CC打印15次
 *          。。。
 *          轮回10次
 */

class ShareResource{

    // A->1,B->2,C->3
    private int numbers = 1;
    private Lock lock = new ReentrantLock();
    // 绑定多个条件
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    // 打印 5次
    public void print5(){

        lock.lock();
        try {
            // 判断
            while(numbers != 1){
                // AA线程不用工作
                condition1.await();
            }
            // 执行
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t print "+i);
            }
            // 通知
            numbers = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 打印 10次
    public void print10(){

        lock.lock();
        try {
            // 判断
            while(numbers != 2){
                // BB线程不用工作
                condition2.await();
            }
            // 执行
            for(int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t print "+i);
            }
            // 通知
            numbers = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 打印 15次
    public void print15(){

        lock.lock();
        try {
            // 判断
            while(numbers != 3){
                // CC线程不用工作
                condition3.await();
            }
            // 执行
            for(int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t print "+i);
            }
            // 通知
            numbers = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLock_mutilCondtions {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        // AA
        new Thread(() -> {
            for(int i=1;i<=10;i++){
                shareResource.print5();
            }
        },"AA").start();
        // BB
        new Thread(() -> {
            for(int i=1;i<=10;i++){
                shareResource.print10();
            }
        },"BB").start();
        // CC
        new Thread(() -> {
            for(int i=1;i<=10;i++){
                shareResource.print15();
            }
        },"CC").start();
    }
}
