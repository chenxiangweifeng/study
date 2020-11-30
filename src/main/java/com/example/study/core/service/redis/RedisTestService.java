package com.example.study.core.service.redis;

import com.example.study.common.enums.RedisKeyEnum;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
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
    private RedisTemplate redisTemplate;

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

    /**
     * 模拟插入一些需要延迟关闭订单的任务，可以用（当前时间戳+需要延迟关闭的时间）充当该值的score
     */
    public void addZsetData(){
        long  nowSecond = System.currentTimeMillis() / 1000;
        redisTemplate.opsForZSet().add("order-close","id001",nowSecond + 30);
        redisTemplate.opsForZSet().add("order-close","id002",nowSecond + 50);
        redisTemplate.opsForZSet().add("order-close","id003",nowSecond + 60);
        redisTemplate.opsForZSet().add("order-close","id004",nowSecond + 80);
        redisTemplate.opsForZSet().add("order-close","id005",nowSecond + 90);
        redisTemplate.opsForZSet().add("order-close","id006",nowSecond + 100);
        redisTemplate.opsForZSet().add("order-close","id007",nowSecond + 120);
    }

    /**
     * 轮询redis 的zset 发现score过期，就可以关闭订单，并将该value从zset中移除
     */
    public void pollingListener(){
        int i = 1;
        while (true) {
//            当前时间戳，单位 s
            long nowStamp = System.currentTimeMillis() / 1000;
//          以当前时间为基准， 查询前10分钟到后1分钟之后，共计11分钟
            Set<DefaultTypedTuple<String>> orderList = redisTemplate.opsForZSet()
                    .rangeByScoreWithScores("order-close", nowStamp - 600, nowStamp + 60);

            if (orderList.size() > 0) {
//           使用轮序查询过期任务并处理
            for (DefaultTypedTuple<String> tuple : orderList) {
                String value = tuple.getValue();
                double score = tuple.getScore();
                long nowSecond = System.currentTimeMillis() / 1000;
                /**
                 * 如果当前时间毫秒数大于其得分，说明该订单value已经过期，就可以关闭订单并移除相应的任务了
                 */
                if (nowSecond > score) {
                    System.out.println("移除过期的订单value===" + value + "==相应的分数是：" + score);
                    redisTemplate.opsForZSet().remove("order-close", value);
                    System.out.println("do,do,do，关闭订单任务模拟，实际可以考虑使用异步");
                }
            }
        }
//            1s 轮询一次
            try {
                Thread.sleep(1000);
//                System.out.println("执行 "+(i++)+" 次==");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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