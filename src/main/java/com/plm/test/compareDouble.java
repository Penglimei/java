package com.plm.test;

import java.math.BigDecimal;

/**
 * 两个 double类型的数据如何比较大小
 *
 *      浮点数没有办法是用二进制进行精确表示。
 *      我们的CPU表示浮点数由两个部分组成：指数和尾数，
 *      这样的表示方法一般都会失去一定的精确度，
 *      有些浮点数运算也会产生一定的误差。
 *
 *      一般采用 java.math.BigDecimal 类来进行精确计算，使用compareTo()比较
 *
 *          BigDecimal 表示不可变的任意精度带符号十进制数，由两部分组成：
 *              intVal-未矫正精度的整数
 *              Scale-一个32位整数，表示小数点右边的位数
 *
 *      public BigDecimal(double val)    将double表示形式转换为BigDecimal *不建议使用
 *
 *          有人可能认为在Java中写入newBigDecimal(0.1)所创建的BigDecimal正好等于 0.1（非标度值 1，其标度为 1），
 *          但是它实际上等于0.1000000000000000055511151231257827021181583404541015625。
 *          这是因为0.1无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。
 *          这样，传入到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
 *
 *      public BigDecimal(int val)　　将int表示形式转换成BigDecimal
 *
 *      public BigDecimal(String val)　　将String表示形式转换成BigDecimal
 *
 *          String 构造方法是完全可预知的：写入 newBigDecimal("0.1") 将创建一个 BigDecimal，它正好等于预期的 0.1。
 *          因此，比较而言，通常建议优先使用String构造方法。
 *          当double必须用作BigDecimal的源时，请使用Double.toString(double)转成String，
 *          然后使用String构造方法，或使用BigDecimal的静态方法valueOf()
 */
public class compareDouble {

    public static void main(String[] args) {
        double d1 = 4.5;
        double d2 = 1.5;

        BigDecimal big1 = new BigDecimal(Double.toString(d1));
        BigDecimal big2 = BigDecimal.valueOf(d2);

        int res = big1.compareTo(big2);
        if(res == 0){
            System.out.println(d1 + " == " + d2);
        }else if(res < 0){
            System.out.println(d1 + " < " + d2);
        }else{
            System.out.println(d1 + " > " + d2);
        }

        // 加法
        System.out.println(d1+" + "+d2+" = "+big1.add(big2));
        // 减法
        System.out.println(d1+" - "+d2+" = "+big1.subtract(big2));
        // 乘法
        System.out.println(d1+" * "+d2+" = "+big1.multiply(big2));
        // 除法
        System.out.println(d1+" / "+d2+" = "+big1.divide(big2));
    }

}
