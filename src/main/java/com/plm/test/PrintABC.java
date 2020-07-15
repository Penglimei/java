package com.plm.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  多线程交替打印ABC
 *
 *      Synchronized
 *      Lock
 *      ReentrantLock+Condition
 *      Semaphore
 *
 *      以下使用 ReentrantLock 实现
 *
 *          ReentrantLock：读独占锁，可重入锁，JDK代码层实现
 */
public class PrintABC {
    // 独占锁
    private static Lock lock = new ReentrantLock();
    // 确认是否打印
    private static int state = 0;

    static class ThreadA extends Thread {
        @Override
        public void run() {

            // 总共要打印多少个A
            for(int i=0;i<10;){
                try {
                    // 加锁
                    lock.lock();
                    while (state%3 == 0){
                        System.out.print("A");
                        state++;
                        i++;
                    }
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {

            for (int i=0;i<10;){
                try {
                    lock.lock();
                    while(state%3 == 1){
                        System.out.print("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadC extends Thread{

        @Override
        public void run() {

            for (int i=0;i<10;){
                try {
                    lock.lock();
                    while(state%3 == 2){
                        System.out.print("C");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
