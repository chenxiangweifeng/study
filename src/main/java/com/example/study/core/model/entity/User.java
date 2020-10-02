package com.example.study.core.model.entity;
import java.io.Serializable;
/**
 * @author 沉香微风
 */
public class User implements Serializable {
    private String name;
    private Integer age;
    private Long number;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Long getNumber() {
        return number;
    }
    public void setNumber(Long number) {
        this.number = number;
    }
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public User(String name, Integer age, Long number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                '}';
    }
    public User(){};
}
