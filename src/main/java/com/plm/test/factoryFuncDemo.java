package com.plm.test;

/**
 *  工厂模式：
 *      工厂方法模式
 *          设计一个工厂的接口，你想要什么东西，就写个类继承于这个工厂
 */

interface Pc{
    void makePc();
}

class MiPc implements Pc{
    @Override
    public void makePc() {
        System.out.println("this is mi pc");
    }
}

class HwPc implements Pc{
    @Override
    public void makePc() {
        System.out.println("tihs is hw pc");
    }
}

interface pcFactory{
    Pc getPc();
}

class miFactory implements pcFactory{
    @Override
    public Pc getPc() {
        return new MiPc();
    }
}

class hwFactory implements pcFactory{
    @Override
    public Pc getPc() {
        return new HwPc();
    }
}

public class factoryFuncDemo {
    public static void main(String[] args) {
        pcFactory factory = new miFactory();
        Pc pc = factory.getPc();
        pc.makePc();

        System.out.println("=======");

        new hwFactory().getPc().makePc();
    }
}
