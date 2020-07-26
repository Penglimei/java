package com.plm.test;

/**
 *  交换排序
 *                     平均时间复杂度     最好时间复杂度     最坏时间复杂度     空间复杂度      稳 定 性
 *       冒泡排序          O(n^2)           O(n)            O(n^2)           O(1)          稳定
 *       快速排序          O(nlog2n)        O(nlog2n)         O(n^2)         O(nlog2n)     不稳定
 */
public class swapSort {

    /**
     *  冒泡排序
     *      比较两个相邻的元素，将值大的元素交换到右边，每趟排序最后的值放置在最终位置
     *      N个数字要排序完成，总共进行N-1趟排序，每i趟的排序次数为(N-i)次
     *
     *      如果数据正序，只需要走一趟即可完成排序
     *      如果数据是反序的，则需要进行n-1趟排序
     *
     *      每趟排序过程中，都会有一个元素被放置在其最终的位置上
     * @param nums
     * @return
     */
    public static int[] popSort(int[] nums){

        if(nums==null || nums.length<=1){
            return nums;
        }

        for(int i=0;i<nums.length;i++){
            for(int j=1;j<nums.length-i;j++){
                if(nums[j] < nums[j-1]){
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }


    /**
     *  快速排序
     *      每趟排序时选取一个数据（通常用数组的第一个数）作为关键数据，
     *      然后将所有比它小的数都放到它的左边，所有比它大的数都放到它的右边.
     *
     *      每趟排序过程中，都会有一个元素被放置在其最终的位置上
     * @param nums
     * @return
     */
    public static int[] fastSort(int[] nums){
        if (nums==null || nums.length<=1){
            return nums;
        }
        fSort(nums,0,nums.length-1);
        return nums;
    }

    private static void fSort(int[] nums, int left, int right) {

        if(left > right){
            return ;
        }

        int target = nums[left];
        int l = left;
        int r = right;

        // 只要不相遇就一直遍历
        while (l < r){
            // 如果选取最左边的数arr[left]作为基准数，
            // 那么先从右边开始可保证i,j在相遇时，相遇数是小于基准数的，
            // 交换之后temp所在位置的左边都小于temp。若先从左边开始,
            // 相遇数是大于基准数的，无法满足temp左边的数都小于它。
            while (l<r && target<=nums[r]){
                r--;
            }
            while (l<r && target>=nums[l]){
                l++;
            }
            if(l < r){
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        // 将 target 与 l,r相遇位置的值交换，target放到了最终位置
        // target = nums[left]
        nums[left] = nums[l];
        nums[l] = target;
        // 相遇位置的值已经固定，开始向左，向右排序
        fSort(nums,left,r-1);
        fSort(nums,r+1,right);
    }


    public static void main(String[] args) {
        int[] nums = {2,5,4,6,1,3,8};
        System.out.println("=======排序前=======");
        for(int i: nums){
            System.out.print(i+" ");
        }

        nums = fastSort(nums);

        System.out.println();
        System.out.println("=======排序后=======");
        for(int i: nums){
            System.out.print(i+" ");
        }
    }
}
