package com.example.study.common.enums;

import java.util.concurrent.TimeUnit;

/**
 * redis key 的定义规范
 * @author 沉香微风
 */
public enum RedisKeyEnum {

    USER_LOGIN("user:login:%s",120,TimeUnit.SECONDS,null),
    MY_REDISSION_LOCK("my:redission:lock",null,null,null),
    MY_REDIS_VALUE("my:redis:value",null,null,null),
    MY_REDIS_LIST("my:redis:list",null,null,null),
    MY_REDIS_SET("my:redis:set",null,null,null),
    MY_REDIS_ZSET("my:redis:zset",null,null,null),
    MY_REDIS_HASH("my:redis:hash",null,null,null);
    // redis key
    public String key;
    //过期时间
    public Integer time;
    // 时间单位
    public TimeUnit timeUnit;
    // key 的描述
    public String description;
    RedisKeyEnum(String key, Integer time, TimeUnit timeUnit, String description) {
        this.key = key;
        this.time = time;
        this.timeUnit = timeUnit;
        this.description = description;
    }


}
