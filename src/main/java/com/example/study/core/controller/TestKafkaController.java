package com.example.study.core.controller;
import com.example.study.common.enums.KafkaTopicEnum;
import com.example.study.core.model.entity.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 看不懂的东西就先放一放，回头就可以再学习
 */
@RestController
@RequestMapping(value = "/testKafka")
@Api(description = "kafka测试类类")
public class TestKafkaController {

    @Autowired
    private  KafkaTemplate<String,Object> KafkaTemplate;

    @ApiOperation(value = "测试kafka监听消息")
    @RequestMapping(value = "/testListenerMessage", method = RequestMethod.POST)
    public String testListenerMessage() {
        return null;
    }
    @ApiOperation(value = "向kafka中发送简单字符串信息")
    @RequestMapping(value = "/testSendSimpleMessage", method = RequestMethod.POST)
    public String testSendSimpleMessage(@RequestParam("msg") String msg) {
        KafkaTemplate.send(KafkaTopicEnum.SIMPLE_MSG.code,msg);
        return "发送消息成功";
    }

    @ApiOperation(value = "向kafka中发送一个对象消息")
    @RequestMapping(value = "/testSendMessage", method = RequestMethod.POST)
    public String testSendMessage(@RequestParam("personType") Integer personType) {
        Person p = new Person();
        p.setUserName("赵敏");
        p.setPersonType(personType);
        p.setPhoneNum("18018576105");
        // 向特定的topic发送消息
        KafkaTemplate.send(KafkaTopicEnum.TEST_PERSON_MESSAGE.code,p);
        return "发送一个对象消息成功";
    }

    @ApiOperation(value = "向具有kafka-2个partition的topic发送信息")
    @RequestMapping(value = "/testSendMessage2", method = RequestMethod.POST)
    public String testSendMessage(@RequestParam("msg") String msg) {
        KafkaTemplate.send(KafkaTopicEnum.TEST_TWO_PARTITION_MSG.code,msg);
        System.out.println("发送的消息是："+msg);
        return "2个partition的topic数据！--ok";
    }

    @ApiOperation(value = "创建有3个partition的topic")
    @RequestMapping(value = "/createTopic3Partition", method = RequestMethod.GET)
    public String createTopic3Partition() {
            KafkaTemplate.send("three-5par-send",5,System.currentTimeMillis(),"five","hello 55 partitions");
        return "创建3个partition的topic数据成功！ok";
    }




}
