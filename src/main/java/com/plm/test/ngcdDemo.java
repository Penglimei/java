package com.plm.test;

/**
 *  最大公约数
 *
 *      如果数a能被数b整除，a就叫做b的倍数，b就叫做a的约数
 *      几个整数中公有的约数，叫做这几个数的公约数；
 *      其中最大的一个，叫做这几个数的最大公约数
 */
public class ngcdDemo {

    public static void main(String[] args) {
        int[] nums = {4,6,12,32};

        int res = ngcd(nums,nums.length);

        System.out.println(res);
    }

    private static int ngcd(int[] nums, int length) {
        if(length == 1){
            return nums[0];
        }
        return gcd(nums[length-1],ngcd(nums,length-1));
    }

    private static int gcd(int num, int ngcd) {
        return ngcd==0?num:gcd(ngcd,num%ngcd);
    }
}
