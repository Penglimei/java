package com.plm.test;


import java.util.concurrent.*;

/**
 *  用 ThreadPoolExecutor 自定义线程池
 *
 *      可以避免 Executors工具类创建线程对象的弊端
 *          弊端：导致 OOM
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        // 查看运行设备 硬件配置
        // JVM 允许的进行数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 利用 ThreadPoolExecutor 类 自定义线程池对象
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            // 10个任务请求处理时，会触发 RejectedExecution
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
