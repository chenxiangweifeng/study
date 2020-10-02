package com.example.study.common.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 目前来看，string object的序列化方式还是非常实用的！ 配合类非常的方便，可以省略一大堆的xml配置文件。
 * redisTemplate类的配置  配置类的作用就是讲特定的类放进spring ioc池子里面
 * 那么在适用方就可以方便地进行使用了
 */
@Configuration
public class RedisTemplateConfig {
    @Bean
    @Primary
    public RedisTemplate<String,Object> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // key 的序列化方式为 string 序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        template.setDefaultSerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
