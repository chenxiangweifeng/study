package com.example.study.common.utils;

import java.util.Random;

public class MathUtil {

    public static double generateRandomDouble(int n){
        Random random = new Random();
        double d = random.nextDouble();
        double d2 = d*n;
        String format = String.format("%.2f", d2);
        double res = Double.parseDouble(format);
        return res;
    }

    public static String generateRandomPhoneNum(){
            String id = SnowFlakeUtil.ID.nextId();
            String phoneNum = id.substring(id.length() - 11, id.length());
            return phoneNum;
    }



}
