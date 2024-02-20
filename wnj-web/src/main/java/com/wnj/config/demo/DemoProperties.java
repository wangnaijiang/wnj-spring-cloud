package com.wnj.config.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对应上方配置文件中的第一段配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.demo")
// PropertySource默认取application.properties
@PropertySource(value = "config/demo.properties", encoding="UTF-8")
public class DemoProperties {

    public String type;
    public String title;
    public Map<String, String> login = new HashMap<String, String>();
    public List<String> urls = new ArrayList<>();

} 