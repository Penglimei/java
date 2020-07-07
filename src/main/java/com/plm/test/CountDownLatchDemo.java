package com.plm.test;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch类
 *      让某个线程等待，直到倒计时结束，再执行
 *
 */
public class CountDownLatchDemo {

    private static int nums = 5;

    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(nums);

        for(int i=1;i<=5;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 离开");
                countDownLatch.countDown();
            },CountDownLatchEnum.forEach_Enum(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 最后离开");
    }
}
