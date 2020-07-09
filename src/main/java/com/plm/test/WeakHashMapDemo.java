package com.plm.test;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *  WeakHashMap
 *
 */
public class WeakHashMapDemo {

    public static void myHashMap(){
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        // {1=HashMap}
        System.out.println(map);

        key = null;
        // {1=HashMap}
        System.out.println(map);

        System.gc();
        // {1=HashMap}	1
        System.out.println(map+"\t"+map.size());
    }

    public static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "WeakHashMap";

        map.put(key,value);
        // {1=HashMap}
        System.out.println(map);

        key = null;
        // {1=HashMap}
        System.out.println(map);

        System.gc();
        // {}	0
        System.out.println(map+"\t"+map.size());
    }

    public static void main(String[] args) {
        System.out.println("==========HashMap===========");
        myHashMap();
        System.out.println("==========WeakHashMap===========");
        myWeakHashMap();
    }
}
