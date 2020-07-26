package com.plm.test;

/**
 *  工厂模式：
 *      简单工厂模式
 *          简单工厂模式就是把对类的创建初始化全都交给一个工厂来执行，
 *          而用户不需要去关心创建的过程是什么样的，
 *          只用告诉工厂我想要什么就行了。
 *          而这种方法的缺点也很明显，
 *          违背了设计模式的开闭原则，
 *          因为如果你要增加工厂可以初始化的类的时候，
 *          你必须对工厂进行改建
 */
interface Phone{
    void make();
}

class MiPhone implements Phone{
    @Override
    public void make() {
        System.out.println("this is mi phone");
    }
}

class HwPhone implements Phone{
    @Override
    public void make() {
        System.out.println("this is hw phone");
    }
}

class phoneFactory{
    public Phone makePhone(String str){
        if(str == "Mi"){
            return new MiPhone();
        }else if(str == "Hw"){
            return new HwPhone();
        }
        return null;
    }
}

public class simpleFactoryDemo {

    public static void main(String[] args) {
        phoneFactory phoneFactory = new phoneFactory();
        Phone phone = phoneFactory.makePhone("Mi");
        phone.make();

        System.out.println("=======");

        new phoneFactory().makePhone("Hw").make();
    }
}
