package com.plm.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 */
public class frequencySort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String res = sortByFrequence(str);
        System.out.println(res);
    }

    private static String sortByFrequence(String str) {
        if(str==null || str.length()<3){
            return str;
        }
        int[][] ch = new int[26][2];
        for(int i=0;i<26;i++){
            ch[i][0] = -1;
        }
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            ch[c-'a'][0] = c-'a';
            ch[c-'a'][1] += 1;
        }
        Arrays.sort(ch, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
        String res = "";
        for(int i=0;i<26;i++){
            int temp = ch[i][1];
            while(ch[i][0]!=-1 && temp>0){
                res += (char)(ch[i][0]+'a');
                temp--;
            }
            if(ch[i][0] == -1){
                break;
            }
        }
        return res;
    }
}
