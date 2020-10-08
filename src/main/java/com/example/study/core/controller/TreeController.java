package com.example.study.core.controller;
import com.example.study.core.model.dto.Node;
import com.example.study.core.service.jpa.TreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 沉香微风
 */

@RestController
@RequestMapping(value = "/tree")
@Api(description = "测试树的实体类")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @ApiOperation(value = "获取树节点")
    @CrossOrigin
    @RequestMapping(value = "/getnodes", method = RequestMethod.GET)
    public  List<Node>  getnodes() {
        List<Node> list = new ArrayList<>();
        List<Node> list1 = new ArrayList<>();
        Node node1 = new Node();
        node1.setId("1");
        node1.setTitle("中国");
        node1.setChildren(list1);
        list.add(node1);

        Node node11 = new Node();
        node11.setId("11");
        node11.setTitle("江苏");
        list1.add(node11);

        Node node12 = new Node();
        node12.setId("12");
        node12.setTitle("湖北");
        list1.add(node12);
        return list;
    }

    @ApiOperation(value = "中国所有省市区数据")
    @CrossOrigin
    @RequestMapping(value = "/chinaData", method = RequestMethod.GET)
    public  List<Node>  chinaData() {
        return treeService.getChinaProvinceCities();
    }

}
