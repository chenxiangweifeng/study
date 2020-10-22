package com.example.study.common.utils;

import com.example.study.core.model.dto.servant.DefImportParams;
import com.example.study.core.model.vo.excel.UserVo;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章参考：[POI导入具有合并了单元格的Excel]  https://www.cnblogs.com/chenglang/p/9844219.html
 */

public class MergeCellExcelUtil {

        /**
     * 读取excel数据,调用这方法开始
     * @param is
     */
    public static<T> List<T> readExcelToObj(InputStream is, DefImportParams params, Class<T> dClass) {
        Workbook wb = null;
        List<T> resultList = null;
        try {
//            objArrList = new ArrayList<>();
            wb = WorkbookFactory.create(is);
//            readExcel(wb, 0, 0, 0,objArrList,indexNum);
//            resultList = readExcel(wb, 0, 1, 0, indexNum, dClass);
            resultList = readExcel(wb, params.getSheetIndex(), params.getStartReadLine(), params.getTailLine(), params.getIndexNum(), dClass);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 读取excel文件
     * @param wb
     * @param sheetIndex sheet页下标：从0开始
     * @param startReadLine 开始读取的行:从0开始
     * @param tailLine 去除最后读取的行
     */
    public static<T> List<T> readExcel(Workbook wb,int sheetIndex, int startReadLine, int tailLine, int indexNum, Class<T> dClass) {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        List<T> resultList = new ArrayList<>();
        Row row = null;
        for(int i=startReadLine; i<sheet.getLastRowNum()-tailLine+1; i++) {
            row = sheet.getRow(i);
            List<Object> objList = new ArrayList<>();
            for(int j = 0 ; j<row.getLastCellNum();j++) {
                //for(Cell c : row) {
                Cell c = row.getCell(j);
                if(c==null){
                    objList.add("");
                    continue;
                }
                boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
//              判断是否具有合并单元格
                if(isMerge) {
                    String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
                    objList.add(rs);
                }else {
                    objList.add(getCellValue(c));
                }
            }
            while(objList.size()<indexNum){
                objList.add("");
            }
            /**
             * 这里修改
             */
//            objArrList.add(objList.toArray());
            T t = transObjectFromTuple(dClass, objList);
            resultList.add(t);
        }
        return  resultList;
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();
        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }
        return null ;
    }
    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        if(cell == null){
            return "";
        }
        if(cell.getCellType() == CellType.STRING){
            return cell.getStringCellValue();
        }else if(cell.getCellType() == CellType.BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellType() == CellType.FORMULA){
            return cell.getCellFormula();
        }else if(cell.getCellType() == CellType.NUMERIC){
//            所有的数字类型会被解析为double类型
//            return String.valueOf(cell.getNumericCellValue());
//            return new BigDecimal(String.valueOf(cell.getNumericCellValue())).toPlainString();
/**
 * poi会将所有的数字类型解析为double,这里可以在回去该字段之后，将其转换为对应类型的字符串就不会有那些奇奇怪怪的问题了
 */
              return Double.valueOf(cell.getNumericCellValue()).longValue()+"";
        }
        return "";
    }

    @Test
    public void testTransFromTuple() throws Exception{
        String[] tuple = {"dfdfd","88"};
        Class<UserVo> userClass = UserVo.class;
        UserVo vo =  userClass.newInstance();
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field  field : declaredFields){
            System.out.println("===="+field.getName());
            BeanUtilsBean.getInstance().setProperty(vo,field.getName(),tuple[0]);
        }
        System.out.println(vo);
    }

    /**
     * 将结果封装成结果类
     * @param dClass
     * @param objList
     * @param <T>
     * @return
     */
    public static <T> T transObjectFromTuple(Class<T> dClass, List<Object> objList){
        BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
        T t = null;
        try {
            t = dClass.newInstance();
            Field[] fields = dClass.getDeclaredFields();
            for (int i=0; i<fields.length;i++){
                beanUtils.setProperty(t,fields[i].getName(),objList.get(i));
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }



}
