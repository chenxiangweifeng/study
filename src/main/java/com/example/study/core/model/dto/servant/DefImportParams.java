package com.example.study.core.model.dto.servant;

import lombok.Data;

/**
 * 自定义的导入参数设置，主要可以处理合并单元格的导入问题
 */
@Data
public class DefImportParams {

    /**
     * sheet索引号，默认从0 开始
     */
    private int sheetIndex = 0;

    /**
     * 开始读取的行数，默认从1开始，一般第一行是表头，不是真正的数据
     */
    private int startReadLine = 1;

    /**
     * 最后几行不读取，默认为0
     */
    private int tailLine = 0;

    /**
     * indexNum 至少需要多少列数据
     */
    private int indexNum = 5;

}
