package com.wnj.suan;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class T5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        HashMap<Integer,String> map = new HashMap<>();
        while(sc.hasNextLine()){
            int n = Integer.parseInt(sc.nextLine());
            int flag = Integer.parseInt(sc.nextLine());//1是升序，0是降序
            int[][] score = new int[n][2];//姓名编号，成绩
            for(int i=0;i<n;i++){
                String[] nameAndScore = sc.nextLine().split("\\s+");
                score[i][0] = i;
                score[i][1] = Integer.parseInt(nameAndScore[1]);
                map.put(i,nameAndScore[0]);
            }
            Arrays.sort(score,(o1,o2) ->{
                System.out.println("==========");
                System.out.println(o1);
                System.out.println(o1[1]);
                System.out.println(o2[1]);
                if(flag==0){
                    return o2[1] - o1[1];//按第二列降序排列,如果相等的话，返回0，顺序不变
                }else{
                    return o1[1] - o2[1];//按第二列升序
                }
            });
            for(int i=0;i<n;i++){
                System.out.println(map.get(score[i][0]) + " " + score[i][1]);
            }
        }
    }
}
