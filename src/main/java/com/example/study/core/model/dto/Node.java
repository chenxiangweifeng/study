package com.example.study.core.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: chenxiangweifeng
 * @Date: 2020/10/8 19:53
 */
@Data
public class Node {

    private String id;
    private String title;
    private List<Node> children;
    private Integer parentId;


}
