package com.plm.test;

/**
 *  选择排序：
 *
 *                    平均时间复杂度     最好时间复杂度     最坏时间复杂度     空间复杂度      稳 定 性
 *      简单选择排序     O(n^2)           O(n^2)            O(n^2)           O(1)         不稳定
 *      堆排序          O(nlog2n)        O(nlog2n)         O(nlog2n)        O(1)         不稳定
 *
 */
public class selectSort {

    /**
     *  简单选择排序：
     *      第1次，从n个记录中找出关键码最小的记录与第1个记录交换，
     *      第2次，，从第2个记录开始的n-1个记录中再选出关键码最小的记录与第2个记录交换；
     *      如此，第i次，则从第i个记录开始的n-i+1个记录中选出关键码最小的记录与第i个记录交换，
     *      直到整个序列按关键码有序
     *
     *      每趟排序过程中，都会有一个元素被放置在其最终的位置上
     * @param nums
     * @return
     */
    public static int[] selectSort1(int[] nums){
        if(nums==null || nums.length<=1){
            return nums;
        }
        for(int i=0;i<nums.length;i++){
            int minIndex = i;
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]<nums[minIndex]){
                    minIndex = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }

        return nums;
    }

    /**
     *  堆排序：
     *      构建初始堆，将待排序列构成一个大顶堆(或者小顶堆)，升序大顶堆，降序小顶堆
     *      将堆顶元素与堆尾元素交换，并断开(从待排序列中移除)堆尾元素
     *      重新构建堆
     *      重复上述步骤，直到待排序列中只剩下一个元素(堆顶元素)
     *
     *      每趟排序过程中，都会有一个元素被放置在其最终的位置上
     *  堆：
     *      arr[i]：父节点
     *      arr[2*i+1]：左孩子节点
     *      arr[2*i+2]：右孩子节点
     *
     *      arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]
     *
     * @param nums
     * @return
     */
    public static int[] heapSort(int[] nums){
        if(nums==null || nums.length<=1){
            return nums;
        }
        // 建大顶堆
        // 从(n-1)/2开始向上调整
        for(int i=(nums.length-1)/2;i>=0;i--){
            buildHeap(nums,i,nums.length);
        }

        // 排序
        for (int i=nums.length-1;i>0;i--){
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            buildHeap(nums,0,i);
        }
        return nums;
    }

    private static void buildHeap(int[] nums, int parent, int length) {
        int temp = nums[parent];
        int lChild = 2*parent+1;

        while(lChild < length){
            int rChild = lChild+1;
            if(rChild<length && nums[lChild]<nums[rChild]){
                lChild++;
            }
            if(temp > nums[lChild]){
                break;
            }

            nums[parent] = nums[lChild];
            parent = lChild;
            lChild = 2*parent+1;
        }
        nums[parent] = temp;
    }

    public static void main(String[] args) {

        int[] nums = {2,5,4,6,1,3,8};
        System.out.println("=======排序前=======");
        for(int i: nums){
            System.out.print(i+" ");
        }

        nums = heapSort(nums);

        System.out.println();
        System.out.println("=======排序后=======");
        for(int i: nums){
            System.out.print(i+" ");
        }
    }
}
