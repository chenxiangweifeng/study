package com.example.study.core.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@GenericGenerator(name = "jpa-uuid",strategy = "uuid")
@Table(name = "tb_person",indexes = {@Index(name = "idx_person_id",columnList = "id")})
public class Person {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_num",length = 32)
    private String phoneNum;
    @Column(name = "age")
    private Integer age;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date createTime;
    @Column(name = "finish")
    private Integer finish;
    @Column(name = "addr")
    private String addr;
    @Column(name = "height")
    private Double height;
    @Column(name = "introduction",columnDefinition = "text")
    private String introduction;
    @Column(name = "person_type")
    private Integer personType;
    public Person(String userName, Integer age, String addr) {
        this.userName = userName;
        this.age = age;
        this.addr = addr;
    }
    public Person(String id, String userName, Integer age, String addr) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.addr = addr;
    }

    public static int cpmpareByAge(Person p1,Person p2){
        return p1.getAge() - p2.getAge();
    }

    public Person() {
    }
//  公有方法
    public String test1(){
        System.out.println("test1,已经执行了！");
        return "hello world!";
    }
//    私有方法
    private String privateTest2(String name,Double d){
        System.out.println(name+"你好；工资："+d);
        return "hello"+name;
    }

}
