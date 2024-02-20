package com.wnj.suan;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class T3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] ss = in.nextLine().split(" ");
        int n = Integer.valueOf(ss[0]);
        String[] dicts = new String[n];
        for(int i=0;i<n; i++){
            dicts[i] = ss[i+1];
        }
        String src = ss[n+1];
        int k = Integer.valueOf(ss[n+2]);
        int m = 0;
        List<String> list = new ArrayList<String>();
        for(String s : dicts){
            if(isBrother(s, src)){
                m++;
                list.add(s);
            }
        }
        Collections.sort(list);
        System.out.println(m);
        if(k<=m){
            System.out.println(list.get(k-1));
        }

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
    public static boolean isBrother(String a, String b){
        if(a.length() != b.length()){
            return false;
        }
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        Arrays.sort(as);
        Arrays.sort(bs);
        for(int i=0;i<as.length;i++){
            if(as[i] != bs[i]){
                return false;
            }
        }
        return true;
    }
}