package com.wnj.javabase;

import java.util.TreeSet;

/**
 * @author WangNaiJiang
 * @since 2023-12-27 15:13
 */
public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("a");
        set.add("a");
        set.add("a");
        System.out.println(set.size());
    }
}
