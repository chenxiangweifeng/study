package com.example.study.common.utils;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 得到本机的IP
 */
public class IPUtils {


    @Test
    public  void getIP(){
        String IP = null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
             IP = localHost.getHostAddress();
            System.out.println("本机IP:"+IP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
