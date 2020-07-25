package com.plm.test;


import java.util.*;

/**
 *
 * leetcode 146 LRU缓存机制
 *  运用你所掌握的数据结构，设计和实现一个 LRU (Least Recently Used，最近最少使用) 缓存机制。
 *  它应该支持以下操作： 获取数据 get 和 写入数据 put ，时间复杂度要求为O(1).
 *
 *  获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 *
 *  写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限 capacity 时，
 *  它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */

public class LRUCacheDemo {
    // 利用 双向链表LinkedList + HashMap

    // 用于存放数据
    private Map<Integer,Integer> map;
    // 双向链表保存最近最少使用之间的关系
    private LinkedList<Integer> list;
    // 缓存容量
    private int capacity;

    public LRUCacheDemo(int capacity) {
        map = new HashMap<>();
        list = new LinkedList<>();
        this.capacity = capacity;
    }

    public int get(int key){
        if(map.containsKey(key)){
            list.remove(Integer.valueOf(key));
            list.add(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key,int value){
        if(map.entrySet().contains(key)){
            list.remove(Integer.valueOf(key));
        }else if(list.size() >= capacity){
            // 头部放置的是长时间未使用的数据
            int removeKey = list.removeFirst();
            map.remove(removeKey);
        }
        // 尾部是最近使用过的数据
        list.addLast(key);
        map.put(key,value);
    }
}


class LRUCacheDemo1 {
    // 利用 LinkedHashMap

    private Map<Integer,Integer> map;
    private int capacity;

    public LRUCacheDemo1(int capacity) {
        map = new LinkedHashMap<>();
        this.capacity = capacity;
    }

    public int get(int key){
        if(map.containsKey(key)){
            int val = map.get(key);
            map.remove(key);
            // key对应的值存在，且被访问了，也即不是一直未被访问的数据，需要更新访问的早晚
            map.put(key,val);
            return val;
        }
        return -1;
    }

    public void put(int key,int value){
        if(map.keySet().contains(key)){
            map.remove(key);
        }else if(map.size() >= capacity){
                // 删除最久未使用的数据值，即头节点
                Iterator<Map.Entry<Integer,Integer>> iterator = map.entrySet().iterator();
                iterator.next();
                iterator.remove();
        }
        map.put(key,value);
    }
}

class Test{

    public static void main(String[] args) {
        LRUCacheDemo1 lruCacheDemo1 = new LRUCacheDemo1(2);// 1 -1 -1 3 4
//        LRUCacheDemo lruCacheDemo1 = new LRUCacheDemo(2);// 1 -1 -1 3 4
        lruCacheDemo1.put(1,1);
        lruCacheDemo1.put(2,2);
        System.out.print(lruCacheDemo1.get(1) + " ");
        lruCacheDemo1.put(3,3);
        System.out.print(lruCacheDemo1.get(2) + " ");
        lruCacheDemo1.put(4,4);
        System.out.print(lruCacheDemo1.get(1) + " ");
        System.out.print(lruCacheDemo1.get(3) + " ");
        System.out.print(lruCacheDemo1.get(4) + " ");

    }
}