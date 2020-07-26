package com.plm.test;

/**
 *  最小公倍数
 *      几个数共有的倍数叫做这几个数的公倍数，其中除0以外最小的一个公倍数，叫做这几个数的最小公倍数
 */
public class nlcmDemo {

    public static void main(String[] args) {

        int[] nums = {4,6,12,32};

        int res = nlcm(nums,nums.length);
        System.out.println(res);
    }

    private static int nlcm(int[] nums, int length) {
        if(length == 1){
            return nums[0];
        }

        return lcm(nums[length-1],nlcm(nums,length-1));
    }

    private static int lcm(int num, int nlcm) {
        return (nlcm*num)/gcd(num,nlcm);
    }

    private static int gcd(int num, int nlcm) {
        return nlcm==0?num:gcd(nlcm,num%nlcm);
    }


}
