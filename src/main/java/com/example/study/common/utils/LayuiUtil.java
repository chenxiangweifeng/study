package com.example.study.common.utils;

import com.example.study.common.domain.LayuiResult;
import org.springframework.data.domain.Page;


/**
 * @Author: chenxiangweifeng
 * @Date: 2020/10/5 9:24
 */
public class LayuiUtil {

    public static <T> LayuiResult<T> buildLayuiPageResult(Page<T> pageEntities){
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("success");
        result.setCount((int) pageEntities.getTotalElements());
        result.setData(pageEntities.getContent());
        return result;
    }

}
