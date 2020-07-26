package com.plm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  归并排序
 *          平均时间复杂度     最好时间复杂度     最坏时间复杂度     空间复杂度      稳 定 性
 *           O(nlog2n)        O(nlog2n)         O(nlog2n)       O(1)           稳定
 *
 *
 *     把待排序序列分为若干个子序列，每个子序列是有序的。
 *     然后再把有序子序列合并为整体有序序列。
 *     即先划分为两个部分，最后进行合并。
 */
public class mergeSort {


    public static int[] mergeSort(int[] nums){
        if(nums.length <= 1){
            return nums;
        }

        int mid = nums.length/2;
        int[] left = mergeSort(Arrays.copyOfRange(nums,0,mid));
        int[] right = mergeSort(Arrays.copyOfRange(nums,mid,nums.length));

        return merge(left,right);
    }

    private static int[] merge(int[] left, int[] right) {
        List<Integer> list = new ArrayList<>();
        int l = 0;
        int r = 0;

        while(l<left.length && r<right.length){
            if(left[l] < right[r]){
                list.add(left[l]);
                l++;
            }else{
                list.add(right[r]);
                r++;
            }

        }

        while (r < right.length){
            list.add(right[r]);
            r++;
        }

        while(l < left.length){
            list.add(left[l]);
            l++;
        }

        int[] res = new int[list.size()];

        for(int k=0;k<list.size();k++){
            res[k] = list.get(k);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,5,4,6,1,3,8};
        System.out.println("=======排序前=======");
        for(int i: nums){
            System.out.print(i+" ");
        }

         nums = mergeSort(nums);

        System.out.println();
        System.out.println("=======排序后=======");
        for(int i: nums){
            System.out.print(i+" ");
        }
    }
}
