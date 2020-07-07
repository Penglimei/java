package com.plm.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore类
 *      主要有两个目的：
 *          一个是用于多个共享资源的互斥使用；
 *          另一个是用于并发线程数的控制。
 */
public class SemaphoreDemo {

    // 模拟共享资源数目
    private static Integer nums = 3;
    // 需要抢占共享资源的线程数目
    private static Integer ThreadNums = 6;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(nums);

        for(int i=1;i<=ThreadNums;i++){
            new Thread(() -> {
                // 抢到资源
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t 抢占资源");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t 抢占资源3秒后释放");
                // 释放资源
                semaphore.release();
            },String.valueOf(i)).start();
        }
    }
}
