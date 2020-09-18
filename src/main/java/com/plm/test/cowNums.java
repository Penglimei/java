package com.plm.test;

import java.util.Scanner;

/**
 *  有一头母牛，它每年年初生一头小母牛。
 *  每头小母牛从第四个年头开始，每年年初也生一头小母牛。
 *  请编程实现在第n年的时候，共有多少头母牛？
 *
 *  输入：2
 *  输出：2
 *
 *  输入：6
 *  输出：9
 */
public class cowNums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int res = cowNum(n);
        System.out.println(res);
    }

    public static int cowNum(int n){
        if(n == 0){
            return 0;

        }
        if(n <= 4){
            return n;
        }
        // i 年有多少成熟的母牛
        int[] dp = new int[100];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 1;
        int sum = 4;
        for(int i=5;i<=n;i++){
            // 小母牛每三年成熟一头
            dp[i] = dp[i-1]+dp[i-3];
            sum += dp[i];
        }
        return sum;
    }
}
