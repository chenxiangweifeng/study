package com.example.study.core.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: chenxiangweifeng
 * @Date: 2020/10/7 16:04
 */
@Data
public class FormVO {
    private String title;
    private String password;
    private String city;
    private String province;
    private String area;
    private List<String> thing;
    private String sex;
    private String desc;
}
