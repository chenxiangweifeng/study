package com.example.study.core.manager.se;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 沉香微风
 * @date 2020-10-16
 * java 集合功能测试
 */
public class BaseCollectionTest {

    @Test
    public void testCollectionOut() {
        // lamda 表达式遍历list 集合
        List<String> items = new ArrayList<>();
        items.add("a");
        items.add("b");
        items.add("c");
        // lamda表达式进行list集合的遍历
        items.forEach(item -> {
            String c = item + "aaa";
            System.out.println(c);
        });
        // lamda 表达式进行map集合的遍历
        Map<String, Integer> maps = new HashMap<>();
        maps.put("alice1", 16);
        maps.put("alice2", 17);
        maps.put("alice3", 20);
        maps.put("alice4", 23);
        // 传统方式遍历, 利用entryset的方式进行集合遍历
        for (Map.Entry<String, Integer> map : maps.entrySet()) {
            System.out.println("key:  " + map.getKey() + " value:  " + map.getValue());
        }
//        lamda表达式遍历map集合
        maps.forEach((k, v) -> System.out.println("lamda:key" + k + "lamda:value" + v));
        maps.forEach((k, v) -> {
            if ("alice2".equals(k)) {
                System.out.println("hello!!" + k);
            }
        });
        List<String> list = new ArrayList<>();
        System.out.println("list的size（）是多少！" + list.size());
    }

}
