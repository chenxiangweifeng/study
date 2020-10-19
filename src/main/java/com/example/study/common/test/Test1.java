package com.example.study.common.test;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Random;

public class Test1 {

    @Test
    public void test1(){
        Random random = new Random();
        for (int i=0;i<100;i++) {
            double d = random.nextDouble();
            double d2 = d*100;
//            String str = new DecimalFormat("#.00").format(d2);
            String format = String.format("%.2f", d2);
            double res = Double.parseDouble(format);
            System.out.println(res);
        }
    }



}
