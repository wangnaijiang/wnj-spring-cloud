package com.wnj.util.b;// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.Scanner;

public class Main22 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int arrLen = in.nextInt();
        int lineLen = in.nextInt();
        //原始数组
        String[] srcArr = new String[lineLen];
        for (int i = 0; i < lineLen; i++) {
            srcArr[i] = in.next();
        }

        int curIndex = 0;

        for (int i = 0; i < srcArr.length; i++) {
//            srcArr[i].in
//            if (srcArr[i].length() >= arrLen) {
//
//                srcArr[i].split();
//                System.out.print(srcArr[i]);
//            }
//            int subLen = Math.min(srcArr.length, arrLen);

//                srcArr[i] =
//                System.out.print(srcArr[i]);
        }
    }



}