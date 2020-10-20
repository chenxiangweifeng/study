package com.example.study.common.test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.example.study.core.model.vo.excel.UserVo;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class TestExcelInput {

    @Test
    public void testFileInput1() throws Exception {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/servant/files/hello.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    public void testExcelInput1() throws Exception {
        ImportParams params = new ImportParams();
        long start = System.currentTimeMillis();
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/servant/files/test.xlsx");
//        File file = new File("E:/test.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        List<UserVo> list = ExcelImportUtil.importExcel(file, UserVo.class, params);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(list);
    }



}
