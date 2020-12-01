package com.example.study.core.manager.delayqueue;

import com.example.study.common.utils.DateUtil;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxiangweifeng
 * 基于时间轮的延迟队列的实现
 */
public class TimerWheelDemo {

    public static void main(String[] args) {
/**
 * threadFactory: 用于生成工作线程，一般采用线程池;
 * tickDuration和unit: 每格的时间间隔，默认是100ms;
 * ticksPerWheel 一圈下来有几格，默认是512，而如果传入数值的不是2的N次方，则会调整为大于等于该参数的一个2的N次方数值，有利于优化hash值的计算
 */
//      final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 100, TimeUnit.MILLISECONDS, 512);
        Timer timer = new HashedWheelTimer();
        System.out.println("开始时间==" + DateUtil.formatDate(new Date()));
        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) {
                System.out.println("任务1每间隔5s执行一次，执行时间为:" + DateUtil.formatDate(new Date()));
                timer.newTimeout(this, 5, TimeUnit.SECONDS);//结束时候再注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) {
                System.out.println("任务2仅运行一次，执行时间====" + DateUtil.formatDate(new Date()));
            }
        };
        timer.newTimeout(task2, 15, TimeUnit.SECONDS);
    }
}
