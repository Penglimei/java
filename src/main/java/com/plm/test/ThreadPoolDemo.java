package com.plm.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  线程池创建线程
 *
 *      Executor接口
 *      ExecutorService接口
 *          实现了 Executor接口
 *      Executors 工具类
 *          Executor接口的工具类
 *          方法有：
 *              newFixedThreadPool(int)
 *                  -> 执行长期的任务，性能好很多
 *              newCachedThreadPool()
 *                  -> 一个任务一个任务执行的场景
 *              newSingleThreadExecutor()
 *                  -> 执行很多短期异步的小程序或者负载较轻的服务
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        // 一池 5 个线程
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 一池 1 个线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 一池 N 个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // 模拟10个请求
            for(int i=1;i<=10;i++){
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
