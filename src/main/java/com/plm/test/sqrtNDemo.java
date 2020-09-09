package com.plm.test;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * java 实现开根号的运算
 *
 *      某个数字正好可以开根号为2个整数，例如1,4,9等
 *      某个数字不可以正好开根号为2个整数，而且要保留几位精度，例如：2,3,5等
 *
 *      首先我们可以把这个数字分成整数部分和小数部分，分别计算。
 */
public class sqrtNDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.next());
        int digits = Integer.parseInt(scanner.next());

        System.out.println(Math.sqrt(num));
        System.out.println("=================");
        double res = sqrtN(num,digits);
        System.out.println(res);
    }

    /**
     *
     * @param num   要开方的数
     * @param digits    保留的小数位
     * @return
     */
    private static double sqrtN(int num, int digits) {
        double[] arr = new double[digits];
        if(digits > 0){
            // 计算保留几位小数
            arr = sqrtDigits(digits);
        }
        // 计算整数位
        int s = sqrtTen(num);

        // 开根号
        return mySqrt(num,s,arr);
    }

    /**
     * 计算保留几位小数
     * @param digits    位数
     * @return
     */
    private static double[] sqrtDigits(int digits) {
        double[] arr = new double[digits];
        int num= 0;
        // 数组每一位分别存储默认值 0.1 0.01 0.001 ... ...
        while(num != digits){
            double temp = 1;
            for(int i=0;i<=num;i++){
                temp = temp*10;
            }
            arr[num] = 1/temp;
            num++;
        }
        return arr;
    }

    /**
     * 计算整数位
     * @param num   源数据
     * @return
     */
    private static int sqrtTen(int num) {
        if(num == 1){
            return 1;
        }
        int temp = 0;
        for(int i=1;i<=num/2+1;i++){
            if(i*i == num){
                temp = i;
                break;
            }
            // 平方比源数据大的最小的数
            if(i*i > num){
                temp = i-1;
                break;
            }
        }
        return temp;
    }

    /**
     * 开根号
     * @param num   源数据
     * @param s     整数位
     * @param arr   小数位
     * @return
     */
    private static double mySqrt(int num, double s, double[] arr) {
        double temp = s;
        for(int i=0;i<arr.length;i++){
            if(i > 0){
                s = temp;
            }
            // 小数位只有九位
            for(int j=1;j<=9;j++){
                // 小数逐步增大与整数位拼接
                temp = j*arr[i]+s;
                if(temp*temp == num){
                    return temp;
                }
                if(temp*temp > num){
                    BigDecimal c1 = new BigDecimal(Double.toString(temp));
                    BigDecimal c2 = new BigDecimal(Double.toString(arr[i]));
                    // 减去对应小数位的基数
                    temp = c1.subtract(c2).doubleValue();
                    break;
                }
            }
        }
        return temp;
    }
}
