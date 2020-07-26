package com.plm.test;

/**
 *  工厂模式：
 *      抽象工厂模式
 *          为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类
 *
 *      抽象工厂模式是工厂方法模式区别：
 *          抽象工厂模式是工厂方法模式的升级版本，
 *          他用来创建一组相关或者相互依赖的对象。
 *          他与工厂方法模式的区别就在于，
 *          工厂方法模式针对的是一个产品等级结构；
 *          而抽象工厂模式则是针对的多个产品等级结构。
 */

interface Fruit{
    void productFruit();
}

class appleFruit implements Fruit{
    @Override
    public void productFruit() {
        System.out.println("product apple");
    }
}

class orangeFruit implements Fruit{
    @Override
    public void productFruit() {
        System.out.println("product orange");
    }
}

interface Vegetables{
    void profuceVegetables();
}

class tomatoVeg implements Vegetables{
    @Override
    public void profuceVegetables() {
        System.out.println("product tomato");
    }
}

class potatoVege implements Vegetables{
    @Override
    public void profuceVegetables() {
        System.out.println("product potato");
    }
}

interface foodFactory{
    Fruit saleFruit();
    Vegetables saleVegetables();
}

class f1Factory implements foodFactory{
    @Override
    public Fruit saleFruit() {
        return new appleFruit();
    }

    @Override
    public Vegetables saleVegetables() {
        return new potatoVege();
    }
}

class f2Factory implements foodFactory{
    @Override
    public Fruit saleFruit() {
        return new orangeFruit();
    }

    @Override
    public Vegetables saleVegetables() {
        return new tomatoVeg();
    }
}

public class abstractFactoryDemo {
    public static void main(String[] args) {
        foodFactory factory = new f2Factory();
        Fruit fruit = factory.saleFruit();
        fruit.productFruit();
        Vegetables vegetables = factory.saleVegetables();
        vegetables.profuceVegetables();

        System.out.println("===========");

        new f1Factory().saleFruit().productFruit();
        new f1Factory().saleVegetables().profuceVegetables();

    }
}
