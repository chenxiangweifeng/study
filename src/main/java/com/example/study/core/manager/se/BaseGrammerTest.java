package com.example.study.core.manager.se;

import org.junit.Test;

/**
 * @author 沉香微风
 * @date 2020-10-16
 * java 基本语法测试, 后续统一使用junit进行测试
 */
public class BaseGrammerTest {


    @Test
    public void testSwitch() {
        int code = 3;
// 这边必须要使用switch 分支结构了。
        int a = -1;
        switch (code) {
            case 1: {
                System.out.println(1);
                a = 1;
                break;
            }
            case 2: {
                System.out.println(2);
                a = 2;
                break;
            }
            case 3: {
                System.out.println(3);
                a = 3;
                break;
            }
            default: {
                System.out.println("qita");
                break;
            }
        }
    }

}
