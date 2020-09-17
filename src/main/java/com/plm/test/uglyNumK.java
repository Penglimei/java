package com.plm.test;

import java.util.Scanner;

/**
 *  输入： 10
 *  输出：12
 *
 *  解释：1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *
 *   我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
 *   求按从小到大的顺序的第 n 个丑数（1 是丑数）。
 */
public class uglyNumK {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int res = uglyNum(k);
        System.out.println(res);
    }

    private static int uglyNum(int k) {
        if(k == 1){
            return 1;
        }
        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        int i = 1;
        while(i < k){
            int temp = Math.min(dp[idx2]*2,Math.min(dp[idx3]*3,dp[idx5]*5));
            dp[i] = temp;
            if (dp[idx2]*2 == temp)idx2++;
            if (dp[idx3]*3 == temp)idx3++;
            if (dp[idx5]*5 == temp)idx5++;
            i++;
        }
        return dp[k-1];
    }
}
