package com.wnj.suan;

class HuiWen {
    public static void main(String[] args) {
        longestPalindrome("6123321");
    }
    public static String longestPalindrome(String s) {
        String a = "";
        String b = "";
        String result = "";
        for(int i = 0; i < s.length(); i++){
            a = huiwen(s,i,i);// 单个元素为中心点
            result = a.length() > result.length() ? a:result;
            b = huiwen(s,i,i+1);
            result = b.length() > result.length() ? b:result;
        }
        return result;
    }
    // 双指针 中心扩散法
    public static String huiwen(String s,int start,int end){
        String tmp = "";
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            // 如果首尾指针的字符相等 直接截取
            tmp = s.substring(start,end + 1);
            start--;
            end++;
        }
        return tmp;
    }
}

