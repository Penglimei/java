package com.plm.test;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier类
 *      让一组线程到达屏障(同步点)时被阻塞，直到最后一个线程到达屏障时，
 *      屏障才会开门，所有呗屏障拦截的线程才会继续处理任务
 */
public class CyclicBarrierDemo {

    private static int nums = 7;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(nums,() -> {
            System.out.println("***可以离开***");
        });

        for(int i=1;i<=nums;i++){
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 到来");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
