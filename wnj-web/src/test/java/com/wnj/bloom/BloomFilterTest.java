package com.wnj.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.wnj.common.Result;
import com.wnj.dto.UserResp;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BloomFilterTest {

    @Test
    public void testGuava(){
        // 创建布隆过滤器，Funnel=漏斗，预计插入100个元素，误判率为0.01
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels
                .stringFunnel(Charset.defaultCharset()), 100, 00.1);
        bloomFilter.put("a1");
        for (int i = 0; i < 10000; i++) {
            String value = "a"+i;
            if(bloomFilter.mightContain(value)){
                //误判：a1008	true
                System.out.println(value +"\t"+bloomFilter.mightContain(value));
            }
        }
    }

    @Test
    public void testRedisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("myfilter");
        bloomFilter.add("a1");
    }
}
