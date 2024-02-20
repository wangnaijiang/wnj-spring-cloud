package com.wnj.gof;

/**
 * @author WangNaiJiang
 * @since 2024-01-17 09:01
 */
public class FlyweightTest {
    public static void main(String[] args) {
        String a = new String("123");
        String b = new String("123");
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
