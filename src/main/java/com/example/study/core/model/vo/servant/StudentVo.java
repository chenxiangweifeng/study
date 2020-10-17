package com.example.study.core.model.vo.servant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



/**
 * @Author: chenxiangweifeng
 * @Date: 2020/10/11 11:19
 * 考生信息vo类
 */
@Data
@NoArgsConstructor
public class StudentVo implements Serializable {

    private String id;

    private String realName;

    private Integer sex;

    private Integer age;

//  申请职位，这个是联动的
    private Integer applyPosition;

    private Integer applyUnit;

//  民族
    private Integer nation;

//    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date birthday;



}