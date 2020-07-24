package com.plm.test;

import java.util.ArrayList;
import java.util.List;

/**
 *  约瑟夫环：
 *      已知n个人(以编号1，2，3...n分别表示)围坐在一张圆桌周围。
 *      从编号为1的人开始报数1，数到m的那个人出列;他的下一个人又从1开始报数，
 *      最后出列的人的编号。
 *
 *      f(i)表示i个人玩游戏，报 m 退出，最后剩下的人的编号
 *      f(i) = (f(i)+m)%i
 */
public class JosefCircleDemo {

    public static void lastNum(int n,int m){

        // 初始化人数
        List<Integer> start = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            start.add(i);
        }
        //从第K个开始计数
        int k = 0;
        while (start.size() >0) {
            k = k + m;
            //第m人的索引位置
            k = k % (start.size()) - 1;
            // 判断是否到队尾
            if (k < 0) {
                System.out.println(start.get(start.size()-1));
                start.remove(start.size() - 1);
                k = 0;
            } else {
                System.out.println(start.get(k));
                start.remove(k);
            }
        }
    }


    public static void main(String[] args) {
        int n = 10;
        int m = 3;

        lastNum(n,m);
    }
}
