package com.example.study.core.service.listener;

import com.alibaba.fastjson.JSON;
import com.example.study.core.model.entity.Person;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * kafka 两个服务监听，ListenKafkaMsg1，ListenKafkaMsg2，两个服务来共同监听一个消息，要使用不同的分组确保每个服务都可以介绍到
 */
@Component
public class ListenKafkaMsg2 {

    @KafkaListener(topics = "test-person-message",groupId ="serverGroup2",containerFactory = "ackContainerFactory" )
    public void receiveKafkaMsg2(ConsumerRecord<?,?> consumerRecord, Acknowledgment acknowledgment){
        System.out.println("监听服务接收到的消息是：22222222222222222222222");
        Person person = JSON.parseObject(consumerRecord.value().toString(), Person.class);
        System.out.println("接收到的person是："+person);
        System.out.println("人员类型是："+person.getPersonType()+"人员姓名是："+person.getUserName());
        System.out.println("=================== end =================");
        /**
         * ack 提交掉 监听服务222 已经被ack提交掉，历史消息不会被再次消费
         */
        acknowledgment.acknowledge();
    }

    /**
     * 这里模拟的就是同一个组的两个不同消费者
     * @param consumerRecord
     * @param acknowledgment
     */
    @KafkaListener(topics = "two-partition-msg",groupId ="serverGroup1",containerFactory = "ackContainerFactory")
    public void receiveKafkaTwoParMsg2(ConsumerRecord<?,?> consumerRecord, Acknowledgment acknowledgment){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("当前的IP地址是："+address.getHostAddress());

        System.out.println("监听服务B-收到的消息是::");
        System.out.println(consumerRecord.value().toString());
        System.out.println("=================== end =================");
        acknowledgment.acknowledge();
    }



}
