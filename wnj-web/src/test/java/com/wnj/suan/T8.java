package com.wnj.suan;

import java.util.*;

class T8 {
    public static void main(String[] args) {
        System.out.println(permutation("qe"));
    }
    public static Set<String> permutation(String S) {
        Set<String> set = new HashSet<>();
        char[] arr = S.toCharArray();
        backtrack(arr, 0, set);
        return set;
    }

    private static void backtrack(char[] arr, int idx, Set<String> set) {
        if (idx == arr.length - 1) {
            set.add(new String(arr));
            return;
        }
        for (int i = idx; i < arr.length; ++i) {
            char tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
            backtrack(arr, idx + 1, set);
            tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }
    }
}