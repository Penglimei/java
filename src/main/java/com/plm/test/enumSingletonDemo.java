package com.plm.test;

/**
 *  单例模式：
 *      单例类在任何情况下都只存在一个实例，
 *      构造方法必须是私有的、
 *      由自己创建一个静态变量存储实例，
 *      对外提供一个静态公有方法获取实例
 *
 *
 *      枚举实现单例模式
 *
 *          枚举类实际上是一个继承Enum的一个final类
 *          枚举实现单例模式是线程安全的。
 *          单元素的枚举类型被 《Effective Java》作者认为是实现Singleton的最佳方法
 *
 *          Java规范中规定，每一个枚举类型极其定义的枚举变量在JVM中都是唯一的.
 *
 *          因此在枚举类型的序列化和反序列化上，Java做了特殊的规定。
 *          在序列化的时候Java仅仅是将枚举对象的name属性输出到结果中，
 *          反序列化的时候则是通过 java.lang.Enum 的 valueOf() 方法来根据名字查找枚举对象。
 */
public enum  enumSingletonDemo {

    enumSin;

    private enumSingletonDemo() {
    }

    public void test(){
        System.out.println("test()");
    }
}

class testEnum {
    public static void main(String[] args) {
        enumSingletonDemo.enumSin.test();
    }
}
