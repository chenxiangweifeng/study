package com.example.study.common.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: chenxiangweifeng
 * @Date: 2020/10/5 9:26
 */
@Data
public class LayuiResult<T> {
    private int code;
    private String msg;
    private int count;
    private List<T> data;

}
