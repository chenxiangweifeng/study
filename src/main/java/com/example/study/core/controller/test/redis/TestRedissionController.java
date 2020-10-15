package com.example.study.core.controller.test.redis;

import com.example.study.common.enums.RedisKeyEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 沉香微风
 * @date 2020-20-15
 */

@RestController
@RequestMapping(value = "/redission")
@Api(description = "redis使用以及Redis分布式锁的使用")
public class TestRedissionController {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 如果不加锁，那么连续请求三次都可以进入这个方法
     */
    @ApiOperation(value = "不加锁的情况")
    @RequestMapping(value = "/noRedission", method = RequestMethod.POST)
    public String testNoRedission() throws Exception{
        workMethod();
        return "执行成功！";
    }

    /**
     * @return
     * @date 2020-4-24 Redission分布式锁的使用
     * 加锁了也是要执行三次请求的（ lock.lock();），只不过暂时未获取到锁的请求处于休眠状态，后续会继续执行的
     * 但是每一次请求能保证锁住的内容作为一个整体进行执行。中途不会有新请求插入进来。
     */
    @ApiOperation(value = "Redission三行代码基于Redis实现分布式锁的加锁与释放锁")
    @RequestMapping(value = "/redission", method = RequestMethod.POST)
    public String testRedission() {
        RLock lock = redissonClient.getLock(RedisKeyEnum.MY_REDISSION_LOCK.key);
        lock.lock();
        try {
            workMethod();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "执行成功！";
    }

    /**
     * lock.tryLock() 这种模式可以实现当请求获取不到锁时直接返回，不必再执行相应业务代码。
     */
    @ApiOperation(value = "使用trylock()模式")
    @RequestMapping(value = "/redission2", method = RequestMethod.POST)
    public String testRedission2() throws Exception {
        RLock lock = redissonClient.getLock(RedisKeyEnum.MY_REDISSION_LOCK.key);
        if(lock.tryLock()){
            workMethod();
            lock.unlock();
            return "执行成功！";
        }else {
            return "已经有请求在执行，该请求获取不到锁，直接退出！";
        }
    }

    private void workMethod() throws Exception{
        System.out.println("============reidsson 起效果了吗？ 可以进入到这个方法吗？？？？=============");
        Thread.sleep(2000);
        System.out.println("============睡眠结束啦啦啦啦啦啦=============");
    }


}
