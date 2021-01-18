package com.example.study.core.controller;

import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JdWanXiangTest {


    /**
     * 天气预报的测试：
     */
    @Test
    public void testWeather(){
        RequestModel model = new RequestModel();
        model.setGwUrl("https://way.jd.com/jisuapi/weather");
        model.setAppkey("511153c76623021f9aec98e061f1718d");
        Map queryMap = new HashMap<>();
        queryMap.put("city","杭州"); //访问参数
//        queryMap.put("cityid","111"); //访问参数
//        queryMap.put("citycode","101260301"); //访问参数
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);
        String response = call.request();
        System.out.println(response);
    }

    /**
     * 笑话
     */
    @Test
    public void testXiaoHua(){
        RequestModel model = new RequestModel();
        model.setGwUrl("https://way.jd.com/showapi/wbxh");
        model.setAppkey("511153c76623021f9aec98e061f1718d");
        Map queryMap = new HashMap();
        queryMap.put("time","2015-07-10"); //访问参数
        queryMap.put("page","1"); //访问参数
        queryMap.put("maxResult","20"); //访问参数
        queryMap.put("showapi_sign","bd0592992b4d4050bfc927fe7a4db9f3"); //访问参数
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);
//        call.request();
        String response = call.request();
        System.out.println(response);
    }

    @Test
    public void ZhougongJieMeng(){
        RequestModel model = new RequestModel();
        model.setGwUrl("https://way.jd.com/jisuapi/dreamSearch");
        model.setAppkey("511153c76623021f9aec98e061f1718d");
        Map queryMap = new HashMap();
        queryMap.put("keyword","美女"); //访问参数
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);
        call.request();
        String response = call.request();
        System.out.println(response);
    }


}
