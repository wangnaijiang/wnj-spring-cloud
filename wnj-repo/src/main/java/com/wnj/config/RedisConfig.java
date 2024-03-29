//package com.wnj.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//import java.lang.reflect.Method;
//
//@EnableCaching
//@Configuration
//public class RedisConfig extends CachingConfigurerSupport {
//
//	@Bean
//	public KeyGenerator keyGenerator() {
//		return (Object target, Method method, Object... params) -> {
//			StringBuilder sb = new StringBuilder();
//			sb.append(target.getClass().getName());
//			sb.append(method.getName());
//			for (Object obj : params) {
//				sb.append(obj.toString());
//			}
//			return sb.toString();
//		};
//	}
//
//	@Bean
//	public CacheManager cacheManager(RedisTemplate redisTemplate, RedisConnectionFactory) {
//		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//		cacheManager.setDefaultExpiration(10000);
//		return cacheManager;
//	}
//
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//		StringRedisTemplate template = new StringRedisTemplate(factory);
//		template.setValueSerializer(getSerializer(template));
//		template.afterPropertiesSet();
//		return template;
//	}
//
//	private RedisSerializer getSerializer(StringRedisTemplate template) {
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//		Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
//		serializer.setObjectMapper(om);
//
//		return serializer;
//	}
//
//}