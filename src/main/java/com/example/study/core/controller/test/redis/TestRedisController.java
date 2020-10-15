package com.example.study.core.controller.test.redis;
import com.example.study.common.enums.RedisKeyEnum;
import com.example.study.core.service.redis.RedisTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testRedis")
@Api(description = "redis以及Redis分布式锁的使用")
public class TestRedisController {

    @Autowired
    RedisTestService redisTestService;
    @Autowired
    private RedissonClient redissonClient;
    /**
     * @date 2020-4-24 学习Redission分布式锁的使用
     * 如果不加锁，那么连续请求三次都可以进入这个方法
     * 加锁实验：同时点击的时候是不能进入到这个方法的。
     * @return
     */
    @ApiOperation(value = "Redission三行代码基于Redis实现分布式锁的加锁与释放锁")
    @RequestMapping(value = "/redission", method = RequestMethod.POST)
    public String testRedission() throws Exception {
        RLock lock = redissonClient.getLock(RedisKeyEnum.MY_REDISSION_LOCK.key);
        lock.lock();
        System.out.println("============reidsson 起效果了吗？ 可以进入到这个方法吗？？？？=============");
        Thread.sleep(1000);
        System.out.println("============睡眠结束啦啦啦啦啦啦=============");
        lock.unlock();
        return "redission ok!";
    }
    @ApiOperation(value = "redis set list")
    @RequestMapping(value = "/redissetList", method = RequestMethod.PUT)
    public String redissetList(){
        redisTestService.setRedisList();
        return "redis set list";
    }
    @ApiOperation(value = "redis get list")
    @RequestMapping(value = "/redisGettList", method = RequestMethod.GET)
    public String redisGettList(){
        redisTestService.getRedisList();
        return "redis get list";
    }
    @ApiOperation(value = "redis set hash")
    @RequestMapping(value = "/redissetHash", method = RequestMethod.PUT)
    public String redissetHash(){
        redisTestService.setRedisHash();
        return "redis set hash";
    }
    @ApiOperation(value = "redis get hash")
    @RequestMapping(value = "/redisGettHash", method = RequestMethod.GET)
    public String redisGettHash(){
        redisTestService.getRedisHash();
        return "redis get hash";
    }

    @ApiOperation(value = "redis 设置 set")
    @RequestMapping(value = "/redissetSet", method = RequestMethod.PUT)
    public String redissetSet(){
        redisTestService.setRedisSet();
        return "redis 设置 不重复集合set";
    }
    @ApiOperation(value = "redis 设置 set")
    @RequestMapping(value = "/redisGettSet", method = RequestMethod.GET)
    public String redisGettSet(){
        redisTestService.getRedisSet();
        return "redis 得到 set集合并遍历！";
    }

    @ApiOperation(value = "有顺序集合")
    @RequestMapping(value = "/redissetzset", method = RequestMethod.PUT)
    public String redissetzset(){
        redisTestService.setRedisZset();
        return "redis 设置 有序集合，通过分数添加";
    }
    @ApiOperation(value = "redis 获取有序集合")
    @RequestMapping(value = "/redisGetZset", method = RequestMethod.GET)
    public String redisGetZset(){
        redisTestService.getRedisZset();
        return "redis 获取有序集合的遍历，zset";
    }

}