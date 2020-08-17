package com.plm.test;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *  cglib 动态代理
 *
 *      基于子类的动态代理
 *
 *     要实现MethodInterceptor接口重写intercept()方法
 *     使用 Enhancer类的对象调用方法 enhancer.create(); 创建代理类对象
 */

public class cglibProxyTest {
    static class Target {
        public void fun1(){
            System.out.println("target class function fun1()");
            fun2();
        }

        public void fun2(){
            System.out.println("target class function fun2()");
        }
    }

    static class MyMethodInterception implements MethodInterceptor{

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("MethodInterceptor class intercept before");
            Object object = methodProxy.invokeSuper(o,objects);
            System.out.println("MethodInterceptor class intercept after");
            return object;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new MyMethodInterception());
        Target target = (Target) enhancer.create();
        target.fun1();
    }
}
