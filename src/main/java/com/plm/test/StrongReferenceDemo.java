package com.plm.test;


import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 引用
 *      强引用
 *          宁愿 OOM 也不会回收不被引用的对象
 *      软引用
 *          内存空间不够时，GC回收不被引用的对象
 *      弱引用
 *          无论内存空间是否足够，GC回收不被引用的对象
 *      虚引用
 *          形同虚设，在任何时候都可能被GC回收，
 *          主要作用是跟踪对象被GC回收的状态
 *              phantomReference 的 get()方法总是返回null
 *
 *      软/弱/虚 引用对象在被GC回收之前，会被放到 referenceQueue中
 *
 *                                          Object
 *                          |                                   |
 *                      Reference                           ReferenceQueue
 *              |           |               |
 *      SoftReference   WeakReference   PhantomReference
 */
public class StrongReferenceDemo {

    // 强引用
    public static void strongTest(){
        // 这样定义的默认值是强引用
        Object object1 = new Object();
        // object2 引用赋值
        Object object2 = object1;
        // object1置空
        object1 = null;
        // 此时 object1会被回收，但是 object2不会被回收
        System.gc();
        // java.lang.Object@511d50c0
        System.out.println(object2);
    }

    // 软引用
    // 1、内存够用
    public static void softTest_enough(){
        Object o1 = new Object();
        // 软引用对象
        SoftReference<Object> softReference = new SoftReference<>(o1);
        // System.gc()前，o1 = java.lang.Object@60e53b93
        System.out.println("System.gc()前，o1 = "+o1);
        //System.gc()前，softReference = java.lang.Object@60e53b93
        System.out.println("System.gc()前，softReference = "+softReference.get());

        o1 = null;
        System.gc();

        //System.gc()后，o1 = null
        System.out.println("System.gc()后，o1 = "+o1);
        //System.gc()后，softReference = java.lang.Object@60e53b93
        System.out.println("System.gc()后，softReference = "+softReference.get());

    }
    // 2、内存不够用
    public static void softTest_unEnough(){

        Object o1 = new Object();
        // 软引用对象
        SoftReference<Object> softReference = new SoftReference<>(o1);
        // System.gc()前，o1 = java.lang.Object@5e2de80c
        System.out.println("System.gc()前，o1 = "+o1);
        //System.gc()前，softReference = java.lang.Object@5e2de80c
        System.out.println("System.gc()前，softReference = "+softReference.get());

        o1 = null;

        // 故易产生大对象并配置小的内存，让它的内存不够用了导致OOM
        // JVM 参数配置 -Xms5m -Xmx5m -XX:+PrintGCDetails
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        try {
            byte[] bytes = new byte[30*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.gc()后，o1 = null
            System.out.println("System.gc()后，o1 = "+o1);
            //System.gc()后，softReference = null
            System.out.println("System.gc()后，softReference = "+softReference.get());
        }
    }

    // 弱引用
    public static void weakTest(){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println("System.gc()前，o1 = "+o1);
        System.out.println("System.gc()前，weakReference = "+weakReference.get());

        o1 = null;
        System.gc();

        //System.gc()后，o1 = null
        System.out.println("System.gc()后，o1 = "+o1);
        // System.gc()后，weakReference = null
        System.out.println("System.gc()后，weakReference = "+weakReference.get());
    }

    // 虚引用
    public static void phantomTest() throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);
        // System.gc()前，o1 = java.lang.Object@511d50c0
        System.out.println("System.gc()前，o1 = "+o1);
        // System.gc()前，PhantomReference = null
        System.out.println("System.gc()前，PhantomReference = "+phantomReference.get());
        // 软/弱/虚 引用对象在被GC回收之前，会被放到 referenceQueue中
        // System.gc()前，ReferenceQueue = null
        System.out.println("System.gc()前，ReferenceQueue = "+referenceQueue.poll());

        o1 = null;
        System.gc();
        Thread.sleep(500);

        // System.gc()后，o1 = null
        System.out.println("System.gc()后，o1 = "+o1);
        // System.gc()后，weakReference = null
        System.out.println("System.gc()后，weakReference = "+phantomReference.get());
        // 软/弱/虚 引用对象在被GC回收之前，会被放到 referenceQueue中
        // System.gc()后，ReferenceQueue = java.lang.ref.PhantomReference@60e53b93
        System.out.println("System.gc()后，ReferenceQueue = "+referenceQueue.poll());
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println("========强引用===========");
        strongTest();
        System.out.println("========软引用-内存足够===========");
        softTest_enough();
        System.out.println("========软引用-内存不足===========");
        softTest_unEnough();
        System.out.println("========弱引用===========");
        weakTest();
        System.out.println("========虚引用===========");
        phantomTest();
    }
}
