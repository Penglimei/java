package com.plm.test;

/**
 *  装饰者模式
 *
 *      对已有的业务进一步封装，使其增加额外的功能。
 *
 *      装饰者拥有被装饰者的对象，当作构造参数传入
 *      在装饰者类中，调用被装饰者的方法，封装成新的方法
 *      利用多态，将子类作为参数相互传递，达到互相装饰效果
 *
 *          IO流利用装饰者模式和适配器模式
 */
class decorated {
    private String foodName;

    public decorated() {
    }

    public decorated(String foodName) {
        this.foodName = foodName;
    }

    public String make(){
        return foodName;
    }
}

class decorator1 extends decorated{

    private decorated decorated;

    public decorator1(decorated decorated) {
        this.decorated = decorated;
    }

    @Override
    public String make() {
        return decorated.make()+" + 苹果肉";
    }
}

class decorator2 extends decorated{
    private decorated decorated;

    public decorator2(decorated decorated) {
        this.decorated = decorated;
    }

    @Override
    public String make() {
        return decorated.make()+" + 苹果皮";
    }
}

public class decoratorPattern {

    public static void main(String[] args) {
        decorator2 decorator2 = new decorator2(new decorator1(new decorated("苹果核")));
        System.out.println(decorator2.make());// 苹果核 + 苹果肉 + 苹果皮
    }
}
