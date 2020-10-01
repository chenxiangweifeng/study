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
 * kafka 两个服务监听，ListenKafkaMsg1，ListenKafkaMsg2，两个服务来共同监听一个消息，要使用不同的分组确保每个服务都可以接收到
 * 将groupId 两个变成一样的，这样是不是一个服务收到之后，然后另一个就收不到了，一定要避免出现这样的情况，有可能会出现
 * 难以预测的错误。
 *
 * @date 2020-09-24
 * 即同一个partition内的消息只能被同一个组中的一个consumer消费。当消费者数量多于partition的数量时，多余的消费者空闲
 */
@Component
public class ListenKafkaMsg1 {

    @KafkaListener(topics = "simple-msg",containerFactory = "ackContainerFactory")
    public void receiveSimpleKafkaMsg(ConsumerRecord<?,?> consumerRecord, Acknowledgment acknowledgment){
        System.out.println("监听服务接收到的简单字符串消息是======"+consumerRecord.value().toString());
        System.out.println("=================== end =================");
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "test-person-message",groupId ="serverGroup1",containerFactory = "ackContainerFactory")
    public void receiveKafkaMsg1(ConsumerRecord<?,?> consumerRecord, Acknowledgment acknowledgment){
        System.out.println("监听服务接收到的消息是：111111111111111111111111");
        Person person = JSON.parseObject(consumerRecord.value().toString(), Person.class);
        System.out.println(consumerRecord.value().toString());
        System.out.println("接收到的person是："+person);
        System.out.println("人员类型是："+person.getPersonType()+"人员姓名是："+person.getUserName());
        System.out.println("=================== end =================");
    }

    /**
     * @date 2020-09-24
     * 两个partition的topic，同一个组的两个消费者就可以并行的消费了，需要kafka也是集群才行，单机版并不支持
     * @param consumerRecord
     * @param acknowledgment
     */
    @KafkaListener(topics = "two-partition-msg",groupId ="serverGroup1",containerFactory = "ackContainerFactory")
    public void receiveKafkaTwoParMsg(ConsumerRecord<?,?> consumerRecord, Acknowledgment acknowledgment){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("当前的IP地址是："+address.getHostAddress());
        System.out.println("监听服务A-收到的消息是::");
        System.out.println(consumerRecord.value().toString());
        System.out.println("=================== end =================");
//        ack 提交掉，避免服务重启再次拉取到消息
        acknowledgment.acknowledge();
    }


}
