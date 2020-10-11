package com.example.study.core.model.entity.servant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenxiangweifeng
 * @Date: 2020/10/11 11:19
 * 考生信息实体类
 */
@Data
@Entity
@Table(name = "tb_student")
public class StudentEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "age")
    private Integer age;

//  申请职位，这个是联动的
    @Column(name = "apply_position")
    private Integer applyPosition;

    @Column(name = "apply_unit")
    private Integer applyUnit;

//    民族
    @Column(name = "nation")
    private Integer nation;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;



}