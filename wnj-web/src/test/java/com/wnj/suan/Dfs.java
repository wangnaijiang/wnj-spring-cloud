package com.wnj.suan;

import java.util.*;

class Dfs {
    public static void main(String[] args) {
        List<List<Integer>> lists = combine(3, 2);
        System.out.println(lists);
    }
    private static List<List<Integer>> ans = new ArrayList<>();
    public static List<List<Integer>> combine(int n, int k) {
        getCombine(n, k, 1, new ArrayList<>());
        return ans;
    }
    
    public static void getCombine(int n, int k, int start, List<Integer> list) {
        if(k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for(int i = start;i <= n - k + 1;i++) {
            list.add(i);
            getCombine(n, k - 1, i+1, list);
            list.remove(list.size() - 1);
        }
    }
}
