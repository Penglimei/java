package com.plm.test;


/**
 *  递归、迭代的区别
 *      递归：
 *          自身不断的调用自身，且必须要有递归出口。
 *
 *      迭代：
 *          每一次运行的结果作为下一次迭代的参数。
 *
 *
 *  例子：
 *      有 n 步台阶，一次只能上 1步或 2步，总共有多少种走法？
 *
 *  解：
 *              n , n=1或2
 *      f(n) =
 *              f(n-1)+f(n-2) ,n > 2
 */

public class JumpDemo {

    // 递归
    public static int fun(int n){

        if(n < 0){
            return 0;
        }
        if(n < 3){
            return n;
        }
        return fun(n-1)+fun(n-2);
    }

    // 迭代
    public static int fun2(int n){
        if(n < 0){
            return 0;
        }
        if(n < 2){
            return n;
        }
        int res = 0;
        int a = 1;
        int b = 1;
        for(int i=2;i<=n;i++){
            res = a+b;
            a = b;
            b = res;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println("=======递归=======");
        int res = fun(5);
        System.out.println("res = "+res);
        System.out.println();
        System.out.println("=======迭代=======");
        int res1 = fun2(5);
        System.out.println("res1 = "+res1);
    }
}
