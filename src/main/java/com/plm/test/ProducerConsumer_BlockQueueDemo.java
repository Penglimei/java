package com.plm.test;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者-BlockQueue实现方式
 *
 */

class ShareResources{
    // 默认开启，进行生产+消费
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public ShareResources(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // 当前BlockingQueue接口的实现类名
        System.out.println(blockingQueue.getClass().getName());
    }

    // 生产
    public void myProduce() throws Exception{
        String data = null;
        boolean retValue;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t flag=false 生产停止");
    }

    // 消费
    public void myConsume() throws Exception{

        String result = null;
        while (flag){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(result==null || result.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒还未取到产品，退出消费");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    // 停止活动
    public void stop() throws Exception{
        this.flag = false;
    }

}

public class ProducerConsumer_BlockQueueDemo {

    public static void main(String[] args) {
        ShareResources shareResources = new ShareResources(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                shareResources.myProduce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Producer").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            System.out.println();
            System.out.println();

            try {
                shareResources.myConsume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        // 暂停一会
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();

        System.out.println("5秒时间到，停止活动");
        try {
            shareResources.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
