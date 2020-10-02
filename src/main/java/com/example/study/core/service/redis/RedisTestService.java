package com.example.study.core.service.redis;

import com.example.study.common.enums.RedisKeyEnum;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
/**
 * redis test1
 * redis 一共有4种类型，string  list hash  set sortedSet
 * redis remote dictionary server 远程字典服务
 * 就是用redisTemplate 这一个核心类基本上就够了
 */
@Component
public class RedisTestService {

    // 存入得数据类型如要和配置的数据类型保持一致！
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void testRedisValue(){
        redisTemplate.opsForValue().set(RedisKeyEnum.MY_REDIS_VALUE.key,"中华人民共和国");
        Object o = redisTemplate.opsForValue().get(RedisKeyEnum.MY_REDIS_VALUE.key);
        if(Objects.nonNull(o)){
            System.out.println(o.toString());
        }
    }
    public void setRedisList(){
        List<Object> language = Lists.newArrayList("java","c++","python","js");
        redisTemplate.opsForList().leftPushAll(RedisKeyEnum.MY_REDIS_LIST.key,language);
    }
    public void getRedisList(){
        List<Object> languageList = redisTemplate.opsForList().range(RedisKeyEnum.MY_REDIS_LIST.key, 0, -1);
//        可以直接转换成string
        if(CollectionUtils.isNotEmpty(languageList)){
            List<String> languageStrs = languageList.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.println("遍历输出到表中！");
            languageStrs.forEach(System.out::println);
        }
    }
    public void setRedisHash(){
        Map<String,String> nameAgeMap = new HashMap<>();
        nameAgeMap.put("name","alice");
        nameAgeMap.put("address","beijing");
        nameAgeMap.put("age","18");
        redisTemplate.opsForHash().putAll(RedisKeyEnum.MY_REDIS_HASH.key,nameAgeMap);
    }
    public void getRedisHash(){
        Map<Object,Object> nameAgeMap =  redisTemplate.opsForHash().entries(RedisKeyEnum.MY_REDIS_HASH.key);
        nameAgeMap.forEach((k,v)->{
            System.out.println("k===："+k+"  v===： "+v);
        });
        Object o =  redisTemplate.opsForHash().get(RedisKeyEnum.MY_REDIS_HASH.key,"name");
        System.out.println("name是："+o);
    }
    /**
     * 无序集合，不可重复
     */
    public void setRedisSet(){
        redisTemplate.opsForSet().add(RedisKeyEnum.MY_REDIS_SET.key,"aa");
        redisTemplate.opsForSet().add(RedisKeyEnum.MY_REDIS_SET.key,"bb");
        redisTemplate.opsForSet().add(RedisKeyEnum.MY_REDIS_SET.key,"cc");
//        这里加入一个重复的值，set集合自动去重
        redisTemplate.opsForSet().add(RedisKeyEnum.MY_REDIS_SET.key,"aa");
    }
    public void getRedisSet(){
        Set<Object> members = redisTemplate.opsForSet().members(RedisKeyEnum.MY_REDIS_SET.key);
        Iterator<Object> it = members.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    /*
     * 有序集合 zset
     */
    public void setRedisZset(){
        redisTemplate.opsForZSet().add(RedisKeyEnum.MY_REDIS_ZSET.key,"Harvard大学",100);
        redisTemplate.opsForZSet().add(RedisKeyEnum.MY_REDIS_ZSET.key,"中山大学",80);
        redisTemplate.opsForZSet().add(RedisKeyEnum.MY_REDIS_ZSET.key,"清华大学",98);
        redisTemplate.opsForZSet().add(RedisKeyEnum.MY_REDIS_ZSET.key,"北京大学",99);
        redisTemplate.opsForZSet().add(RedisKeyEnum.MY_REDIS_ZSET.key,"复旦大学",87);
        redisTemplate.opsForZSet().add(RedisKeyEnum.MY_REDIS_ZSET.key,"上海大学",76);
        redisTemplate.opsForZSet().add(RedisKeyEnum.MY_REDIS_ZSET.key,"西藏大学",63);
    }
    public void getRedisZset(){
        Set<Object> range1 = redisTemplate.opsForZSet().rangeByScore(RedisKeyEnum.MY_REDIS_ZSET.key, 90, 100);
        range1.forEach((v) -> System.out.println("90分以上的大学: "+v));
        System.out.println("=====================================================");
        Set<Object> range2 = redisTemplate.opsForZSet().rangeByScore(RedisKeyEnum.MY_REDIS_ZSET.key, 80, 100);
        range2.forEach((v) -> System.out.println("80分以上的大学: "+v));
        System.out.println("=====================================================");
        Set<Object> range3 = redisTemplate.opsForZSet().rangeByScore(RedisKeyEnum.MY_REDIS_ZSET.key, 0, 100);
        range3.forEach((v) -> System.out.println("60分以上的大学: "+v));
        System.out.println("排名后三位的大学:::");
        Set<Object> rank = redisTemplate.opsForZSet().range(RedisKeyEnum.MY_REDIS_ZSET.key, 0, 2);
        rank.forEach(System.out::println);
        System.out.println("排名前三位的大学:::");
        Set<Object> rank2 = redisTemplate.opsForZSet().reverseRange(RedisKeyEnum.MY_REDIS_ZSET.key, 0, 2);
        rank2.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new ArrayList<String>(Arrays.asList("hello","world"));
        Integer a = 88;
        Integer result;
        result =  Optional.ofNullable(a).orElse(98);
//        Integer result = null==a ? 90 : a;
        if(null == a){
            result = 90;
        }else {
            result = a;
        }
        System.out.println(result);
    }

}