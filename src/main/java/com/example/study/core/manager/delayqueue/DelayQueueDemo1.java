package com.example.study.core.manager.delayqueue;

import com.example.study.common.utils.DateUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxiangweifeng
 * @date 2020-11-30
 * java中的延迟队列
 * 1、jdk自带延迟队列,这是一种最为简单的延迟队列实现方式
 */
public class DelayQueueDemo1 {

    @Test
    public void testJdkDelayQueue() throws Exception{
        Order order1 = new Order("order1",5, TimeUnit.SECONDS);
        Order order2 = new Order("order2",10, TimeUnit.SECONDS);
        Order order3 = new Order("order3",15, TimeUnit.SECONDS);

        DelayQueue<Order> queue = new DelayQueue();
        queue.put(order1);
        queue.put(order2);
        queue.put(order3);

        System.out.println("订单延迟队列开始的时间："+ DateUtil.formatDate(new Date()));
        /**
         * 都是有一个轮询算法一直运算
         */
        while (queue.size() != 0){
            /**
             * 获取队列头部元素是否过期，如果过期移除的数据就是null了。
             */
            Order task = queue.poll();
            if (task != null){
                System.out.println("延迟队列执行"+task.name+"===="+DateUtil.formatDate(new Date(task.getTime())));
            }
            Thread.sleep(1000);
        }

    }

}
