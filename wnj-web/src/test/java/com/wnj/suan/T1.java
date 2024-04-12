package com.wnj.suan;


import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class T1 {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.stream().sorted().collect(Collectors.toList());
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Map<Character,Integer> map = new HashMap();
        char[] arr = line.toCharArray();
        for(Character c : arr){
            Integer cnt = map.get(c);
            if(cnt == null ){
                cnt = 1;
            }else{
                cnt++;
            }
            map.put(c, cnt);
        }
        int mix = 0;
        for(Integer cnt : map.values()){
            if(mix ==0){
                mix = cnt;
            }
            if(mix > cnt){
                mix = cnt;
            }
        }
        for(Character c : map.keySet()){
            if(map.get(c)==mix){
                line = line.replaceAll(String.valueOf(c), "");
            }
        }
        System.out.println(line);
    }
}