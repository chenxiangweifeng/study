//package com.example.study.common.utils;
//
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 文章参考：[POI导入具有合并了单元格的Excel]  https://www.cnblogs.com/chenglang/p/9844219.html
// */
//
//public class MergeCellExcelUtil {
//
//
//    /**
//     * 读取excel数据,调用这方法开始
//     * @param is
//     * @param indexNum 至少需要多少列数据
//     */
//    public static List<Object[]> readExcelToObj(InputStream is, int indexNum) {
//        Workbook wb = null;
//        List<Object[]> objArrList = null;
//        try {
//            objArrList = new ArrayList<>();
//            wb = WorkbookFactory.create(is);
//            readExcel(wb, 0, 0, 0,objArrList,indexNum);
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return objArrList;
//    }
//
//
//
//    /**
//     * 读取excel数据,调用这方法开始
//     * @param is
//     * @param indexNum 至少需要多少列数据
//     */
////    public static <T> List<T> readExcelToObj(InputStream is, int indexNum,T t) {
////
////        Workbook wb = null;
////        List<T> objArrList = null;
////        try {
////            objArrList = new ArrayList<>();
////            wb = WorkbookFactory.create(is);
////            readExcel(wb, 0, 0, 0,objArrList,indexNum);
////        } catch (InvalidFormatException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return objArrList;
////    }
//
//
//
//
//    /**
//     * 读取excel文件
//     * @param wb
//     * @param sheetIndex sheet页下标：从0开始
//     * @param startReadLine 开始读取的行:从0开始
//     * @param tailLine 去除最后读取的行
//     */
//    public static void readExcel(Workbook wb,int sheetIndex, int startReadLine, int tailLine, List<Object[]> objArrList, int indexNum) {
//        Sheet sheet = wb.getSheetAt(sheetIndex);
//        Row row = null;
//        for(int i=startReadLine; i<sheet.getLastRowNum()-tailLine+1; i++) {
//            row = sheet.getRow(i);
//            List<Object> objList = new ArrayList<>();
//            for(int j = 0 ; j<row.getLastCellNum();j++) {
//                //for(Cell c : row) {
//                Cell c = row.getCell(j);
//                if(c==null){
//                    objList.add("");
//                    continue;
//                }
//                boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
////              判断是否具有合并单元格
//                if(isMerge) {
//                    String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
//                    objList.add(rs);
//                }else {
//                    objList.add(getCellValue(c));
//                }
//            }
//            while(objList.size()<indexNum){
//                objList.add("");
//            }
//            objArrList.add(objList.toArray());
//        }
//    }
//
//    /**
//     * 获取合并单元格的值
//     * @param sheet
//     * @param row
//     * @param column
//     * @return
//     */
//    public static String getMergedRegionValue(Sheet sheet ,int row , int column){
//        int sheetMergeCount = sheet.getNumMergedRegions();
//
//        for(int i = 0 ; i < sheetMergeCount ; i++){
//            CellRangeAddress ca = sheet.getMergedRegion(i);
//            int firstColumn = ca.getFirstColumn();
//            int lastColumn = ca.getLastColumn();
//            int firstRow = ca.getFirstRow();
//            int lastRow = ca.getLastRow();
//
//            if(row >= firstRow && row <= lastRow){
//
//                if(column >= firstColumn && column <= lastColumn){
//                    Row fRow = sheet.getRow(firstRow);
//                    Cell fCell = fRow.getCell(firstColumn);
//                    return getCellValue(fCell) ;
//                }
//            }
//        }
//
//        return null ;
//    }
//    /**
//     * 判断指定的单元格是否是合并单元格
//     * @param sheet
//     * @param row 行下标
//     * @param column 列下标
//     * @return
//     */
//    public static boolean isMergedRegion(Sheet sheet,int row ,int column) {
//        int sheetMergeCount = sheet.getNumMergedRegions();
//        for (int i = 0; i < sheetMergeCount; i++) {
//            CellRangeAddress range = sheet.getMergedRegion(i);
//            int firstColumn = range.getFirstColumn();
//            int lastColumn = range.getLastColumn();
//            int firstRow = range.getFirstRow();
//            int lastRow = range.getLastRow();
//            if(row >= firstRow && row <= lastRow){
//                if(column >= firstColumn && column <= lastColumn){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * 获取单元格的值
//     * @param cell
//     * @return
//     */
//    public static String getCellValue(Cell cell){
//        if(cell == null){
//            return "";
//        }
//        if(cell.getCellType() == CellType.STRING){
//            return cell.getStringCellValue();
//        }else if(cell.getCellType() == CellType.BOOLEAN){
//            return String.valueOf(cell.getBooleanCellValue());
//        }else if(cell.getCellType() == CellType.FORMULA){
//            return cell.getCellFormula() ;
//        }else if(cell.getCellType() == CellType.NUMERIC){
//            return String.valueOf(cell.getNumericCellValue());
//        }
//        return "";
//    }
//
//
//
///**
// * 枚举类型有问题，进行改正
// */
////    /**
////     * 获取单元格的值
////     * @param cell
////     * @return
////     */
////    public static String getCellValue(Cell cell){
////        if(cell == null) return "";
////
////        if(cell.getCellType() == Cell.CELL_TYPE_STRIN){
////
////            return cell.getStringCellValue();
////
////        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
////
////            return String.valueOf(cell.getBooleanCellValue());
////
////        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
////
////            return cell.getCellFormula() ;
////
////        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
////
////            return String.valueOf(cell.getNumericCellValue());
////
////        }
////        return "";
////    }
//
//
//
//
//}
