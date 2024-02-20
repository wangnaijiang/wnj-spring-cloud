package com.wnj.util.b;// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int len = in.nextInt();
        //跳过换行
        String str = in.nextLine().trim();
        if(str.length() < 2){
            str = in.nextLine().trim();
        }
        String[] ss = str.split(" ");
        int[] arr = new int[len];
        for (int i=0;i<len;i++) {
            arr[i] = Integer.valueOf(ss[i]);
        }

        //比较逻辑
        int leftSum = sum(arr,0,1);
        int rightSum = sum(arr,1,len);
        int max = Math.abs(leftSum - rightSum);
        for (int i=1;i<len-1;i++) {
            leftSum = leftSum + arr[i];
            rightSum = rightSum - arr[i];
            int tmp = Math.abs(leftSum - rightSum);
            max = Math.max(max, tmp);
        }
        System.out.println(max);
    }

    private static int sum(int[]arr, int start, int end){
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

}