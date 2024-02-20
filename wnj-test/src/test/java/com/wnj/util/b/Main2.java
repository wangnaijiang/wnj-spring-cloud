package com.wnj.util.b;// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int arrLen = in.nextInt();
        int lineLen = in.nextInt();
        //整形数组
        int[][] srcArr = new int[lineLen][];
        for (int i = 0; i < lineLen; i++) {
            String str = in.next();
            String[] ss = str.split(",");
            int[] subArr = new int[ss.length];
            for (int j = 0; j < ss.length; j++) {
                subArr[j] = Integer.valueOf(ss[j]);
            }
            srcArr[i] = subArr;
        }


        int total = 0;
        for (int i = 0; i < srcArr.length; i++) {
            total = total + srcArr[i].length;
        }
        int loopLimit = total / arrLen;
        int needPrintCnt = loopLimit * arrLen;
        int cunPrintCnt = 0;
        //循环总次数
        for (int i = 0; i < loopLimit; i++) {
            int cnt = 0;
            //第N次循环, 所有数组
            for (int j = 0; j < srcArr.length; j++) {
                int[] subArr = srcArr[j];
                int loopStart = i * arrLen;
                int loopEnd = Math.min(loopStart + arrLen, subArr.length);
                for (int k = loopStart; k < loopEnd; k++) {
                    if(needPrintCnt == cunPrintCnt+1){
                        System.out.print(subArr[k]);
                    }else{
                        System.out.print(subArr[k]+",");
                    }
                    cnt++;
                    cunPrintCnt++;
                    if(cnt >= arrLen){
                        cnt = 0;
                        break;
                    }
                }
            }
        }
    }
}