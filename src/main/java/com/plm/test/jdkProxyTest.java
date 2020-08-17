package com.plm.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  jdk动态代理
 *
 *      基于接口的动态代理
 *
 *      要实现InvocationHandler接口重写invoke()方法
 *      使用 Proxy类的Proxy.newProxyInstance()创建代理对象
 */
public class jdkProxyTest {

    interface Target {
        void fun1();
        void fun2();
    }

    static class targetImpl implements Target{

        @Override
        public void fun1() {
            System.out.println("targetImpl class function fun1()");
            fun2();
        }

        @Override
        public void fun2() {
            System.out.println("targetImpl class function fun2()");
        }
    }

    static class MyInvoctionHandler implements InvocationHandler {

        private Target tar;

        public MyInvoctionHandler(Target tar) {
            this.tar = tar;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("InvocationHandler class function invoke before");
            Object object = method.invoke(tar,args);
            System.out.println("InvocationHandler class function invoke after");
            return object;
        }
    }

    public static void main(String[] args) {
        targetImpl impl = new targetImpl();
        Target target = (Target) Proxy.newProxyInstance(
                impl.getClass().getClassLoader(),
                impl.getClass().getInterfaces(),
                new MyInvoctionHandler(impl)
                );
        target.fun1();
    }

}
