package com.example.study.common.enums;

public enum KafkaTopicEnum {
    // 人员消息测试
    TEST_PERSON_MESSAGE("test-person-message", "测试人员信息介绍消息"),
    TEST_TWO_PARTITION_MSG("two-partition-msg","两个partition的topic消息");

    public String code;
    public String msg;

    KafkaTopicEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
