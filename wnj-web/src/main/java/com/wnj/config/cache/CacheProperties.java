package com.wnj.config.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 对应上方配置文件中的第一段配置
 */
@Data
@Configuration
@ConfigurationProperties("cache")
@PropertySource(value = "config/cache.properties", encoding="UTF-8")
public class CacheProperties {

    public Map<String, CacheProperty> caches = new HashMap<String, CacheProperty>();

} 