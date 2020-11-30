package com.example.study.core.manager.delayqueue;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxiangweifeng
 * @date 2020-11-30
 */
@Data
public class Order implements Delayed {
    /**
     * 延迟时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private long time;

    String name;

    public Order(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Order order = (Order) o;
        long diff = this.time - order.time;
        if (diff <= 0){
            return -1;
        }else {
            return 1;
        }
    }
}
