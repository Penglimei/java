package com.plm.test;

/**
 *  求除 1 以外最小的因数，如果不存在返回-1
 *
 *      因数是指整数a除以整数b(b≠0) 的商正好是整数而没有余数，我们就说b是a的因数。
 */
public class minFactor {

    public static int factorys(int num){

        for(int i=2;i<=num;i++){
            if(num%i == 0){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] nums = {2,3,5,7,8,9,10,14};

        for(int i=0;i<nums.length;i++){
            int res = factorys(nums[i]);
            System.out.println(nums[i] + "除 1 以外最小的因数: "+res);
        }
    }
}
