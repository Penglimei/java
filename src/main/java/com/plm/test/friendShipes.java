package com.plm.test;

import java.util.Scanner;

/**
 * 输入
 *      5,3
 *      [[1,2],[2,3],[4,5]]
 *
 * 输出 2
 *
 *
 *  5,3     5代表总人数，3代表有数组行数
 * [[],[],[]]代表人际关系，
 *      [1,2],[2,3] 1和2是朋友，2和3是朋友，1和3通过2间接是朋友===》1，2，3属于一个朋友圈
 *      [4,5] 4和5是朋友===》4、5属于一个朋友圈
 *
 *      所以总共有 2 个朋友圈
 *
 *
 * 解题思路：并查集
 *
 *      并查集由一个整数型的数组和两个函数构成。
 *      数组father[]记录每个点的前导点，函数 find 查找根节点，mix 合并。
 *
 *        []      father[]
 *        []      0 1 2 3 4 5  --->初始化
 *        [1,2]   0 1 1 3 4 5  --->2是1的朋友
 *        [2,3]   0 1 1 1 4 5  --->3是2的朋友，2找到自己的前导节点(根节点)1，3也是1的朋友
 *        [4,5]   0 1 1 1 4 4  --->5是4的朋友
 *
 */
public class friendShipes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.next());// 总人数
        int m = Integer.parseInt(scanner.next());// 行数
        int[][] nums = new int[m][2];
        for(int i=0;i<m;i++){
            for(int j=0;j<2;j++){
                nums[i][j] = Integer.parseInt(scanner.next());
            }
        }
        int res = friendNum(n,m,nums);
        System.out.println(res);
    }

    private static int friendNum(int n,int m,int[][] nums) {
        int[] father = new int[n+1];
        initFather(father);
        int x=0;
        int y=0;
        for(int i=0;i<m;i++){
            x = find(father,nums[i][0]);
            y = find(father,nums[i][1]);
            mix(father,x,y);// 判断本边的两个father x和y是否是一个族
        }

        int cnt = 0;
        for (int i = 0; i < father.length; i++) {
            if(father[i] == i){
                cnt++;
            }
        }
        // 把0去掉，这里没有0这位朋友
        return cnt-=1;
    }

    private static void mix(int[] father, int x, int y) {
        if(father[x] == father[y]){
            return;
        }
        if(x < y){
            father[y] = x;
        }else{
            father[x] = y;
        }
    }

    private static int find(int[] father, int x) {
        int res = x;
        while (father[res] != res){// father[res]!=res表示r之前已经有父亲了
            res = father[res]; // 循环找到x的root节点
        }
        while (x != res){
            int temp = father[x];
            father[x] = res;
            x = temp;
        }
        return res;
    }

    // 初始化father[]
    private static void initFather(int[] father) {
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
    }
}
