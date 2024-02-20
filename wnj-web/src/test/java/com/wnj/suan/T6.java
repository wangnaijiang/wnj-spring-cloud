package com.wnj.suan;

import java.util.*;
public class T6 {
    public static void main(String[] args) {
        System.out.println(isValid("{(})[]"));
        System.out.println(isValid("{()[]"));
        System.out.println(isValid("{()}[]"));
    }
    public static boolean isValid (String s) {
        //辅助栈
        Stack<Character> st = new Stack<Character>(); 
        //遍历字符串
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            //遇到左小括号
            if(c == '('){
                //期待遇到右小括号
                st.push(')');
            }
            //遇到左中括号
            else if(c == '[') {
                //期待遇到右中括号
                st.push(']');
            }
            //遇到左打括号
            else if(c == '{') {
                //期待遇到右打括号
                st.push('}');
            }
            //必须有左括号的情况下才能遇到右括号
            else if(st.isEmpty() || st.pop() != c) {
                return false;
            }

        }
        //栈中是否还有元素
        return st.isEmpty(); 
    }
}
