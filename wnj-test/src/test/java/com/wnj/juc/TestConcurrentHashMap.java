package com.wnj.juc;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangNaiJiang
 * @since 2024-01-29 18:29
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        HashMap<String,Integer> map2 = new HashMap(3);
        for (int i = 0; i < 1024; i++) {
            map2.put("k-"+i, i);
        }
        ConcurrentHashMap map = new ConcurrentHashMap(16);
        for (int i = 0; i < 10249; i++) {
            map.put("k-"+i, i);
            if(i > 10240){
                map.put("k-"+i, i);
            }
        }
    }
}
