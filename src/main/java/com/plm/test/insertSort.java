package com.plm.test;

/**
 *  插入排序：O(nlog2n)
 *                      平均时间复杂度     最好时间复杂度     最坏时间复杂度     空间复杂度      稳 定 性
 *      直接插入排序       O(n^2)             O(n)            O(n^2)           O(1)          稳定
 *      希尔排序                              O(n)            O(n^2)           O(1)         不稳定
 */
public class insertSort {

    /** 直接插入排序
     *      在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
     *      好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
     *      也是排好顺序的。如此反复循环，直到全部排好顺序
     * @param nums
     * @return
     */
    public static int[] insertSort1(int[] nums){

        if(nums==null || nums.length<=1){
            return nums;
        }
        int len = nums.length;

        for(int i=1;i<len;i++){
            for(int j=i;j>0&&nums[j]<nums[j-1];j--){
                int temp = nums[j-1];
                nums[j-1] = nums[j];
                nums[j] = temp;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {2,5,4,6,1,3,8};
        System.out.println("=======排序前=======");
        for(int i: nums){
            System.out.print(i+" ");
        }

        nums = insertSort1(nums);

        System.out.println();
        System.out.println("=======排序后=======");
        for(int i: nums){
            System.out.print(i+" ");
        }
    }

}
