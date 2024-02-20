package com.wnj.config.cache;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CacheProperty {
    private String user;
    private String password;
    private Map<String, Integer> expires = new HashMap<>();
}
