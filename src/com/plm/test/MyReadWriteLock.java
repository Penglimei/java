package com.plm.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *   理解 ReentrantReadWriteLock 锁
 *      其读锁是共享的，写锁是独占的
 */
public class MyReadWriteLock {

    // volatile 保证共享资料的可见性
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 模拟 写操作
    public void put(String key,Object value){

        // 写锁加锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            // 休息一会
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成：");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 释放写锁
            readWriteLock.writeLock().unlock();
        }
    }

    // 模拟 读操作
    public void get(String key){

        // 读锁加锁
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取："+key);
            // 休息一会
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成：");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 释放读锁
            readWriteLock.readLock().unlock();
        }
    }

    // 清除 map中的缓存
    public void clearMap(){
        map.clear();
    }

    public static void main(String[] args) {

        MyReadWriteLock myReadWriteLock = new MyReadWriteLock();

        // 模拟并发写线程
        for (int i=1;i<=5;i++){
            final int tempInt = i;
            new Thread(() -> {
                myReadWriteLock.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        // 模拟并发读线程
        for (int i=1;i<=5;i++){
            final int tempInt = i;
            new Thread(() -> {
                myReadWriteLock.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
