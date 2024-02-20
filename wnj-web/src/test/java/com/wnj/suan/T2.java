package com.wnj.suan;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class T2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];//创建数组
        for (int i = 0; i < n; i++) {//数组填入
            arr[i] = in.nextInt();
        }
        int type = in.nextInt();
        List<Integer> list = new ArrayList();
        for(Integer e : arr){
            list.add(e);
        }
        if(type == 0){
            list.sort((p1,p2) ->{
                    return p2 - p1;
            });
        }else{
            list.sort((p1,p2) ->{
                    return p1 - p2;
            });  
        }
        System.out.println(list);
    }
}