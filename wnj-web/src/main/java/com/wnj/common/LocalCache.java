package com.wnj.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache {
    private static Map<String,Object> cache = new ConcurrentHashMap<>();

    public static <V> V getObject(String key){
        return (V)cache.get(key);
    }
    public static <V> void setObject(String key, V value){
        cache.put(key, value);
    }

}
