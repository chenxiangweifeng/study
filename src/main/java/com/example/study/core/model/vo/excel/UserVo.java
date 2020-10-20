package com.example.study.core.model.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * excel 导入的类
 */
@Data
public class UserVo {
    @Excel(name = "用户名")
    private String  name;
    /**
     * 学生性别
     */
    @Excel(name = "性别")
    private String  sex;

}
