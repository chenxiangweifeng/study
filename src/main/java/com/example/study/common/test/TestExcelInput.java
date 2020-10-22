package com.example.study.common.test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.study.common.utils.MergeCellExcelUtil;
import com.example.study.core.model.ao.servant.PositionAo;
import com.example.study.core.model.dto.servant.DefImportParams;
import com.example.study.core.model.vo.excel.UserVo;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

    @Test
    public void testMergeCell() throws Exception {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/servant/files/test.xlsx");
        DefImportParams params = new DefImportParams();
        List<UserVo> userVos = MergeCellExcelUtil.readExcelToObj(new FileInputStream(file),params,UserVo.class);
        System.out.println(userVos);
    }

    @Test
    public void testMergeCellServant() throws Exception {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/servant/files/公务员拟录用职位表.xlsx");
        DefImportParams params = new DefImportParams();
        params.setStartReadLine(4);
        params.setIndexNum(10);
        List<PositionAo> positionAoList = MergeCellExcelUtil.readExcelToObj(new FileInputStream(file),params, PositionAo.class);
//        System.out.println(positionAoList);
        positionAoList.forEach(System.out::println);

    }

    @Test
    public void testNumber(){
        String str = "3.6";
        Double a = Double.parseDouble(str);
        System.out.println(a);

        Integer b = a.intValue();
        System.out.println("转化后的int值是："+b);

    }



}
