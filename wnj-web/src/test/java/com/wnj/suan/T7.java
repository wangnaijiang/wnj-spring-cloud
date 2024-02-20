package com.wnj.suan;

public class T7 {
    public static void main(String[] args) {
        System.out.println(maxDepth("((2)(3 * (1)))"));
    }

    public static int maxDepth(String s) {
        int ans = 0, size = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == '(') {
                ++size;
                ans = Math.max(ans, size);
            } else if (ch == ')') {
                --size;
            }
        }
        return ans;
    }
}
