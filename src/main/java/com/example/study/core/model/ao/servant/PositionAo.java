package com.example.study.core.model.ao.servant;
import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 导入excel实体类
 */
@Data
public class PositionAo {

    /**
     * 机关（单位）名称
     */
    @Excel(name = "机关（单位）名称")
    private String unitName;

    /**
     * 职位名称
     */
    @Excel(name = "职位名称")
    private String positionName;

    /**
     * 区域代码，比如某某县市的某某职位
     */
//    @Excel(name = "职位代码")
//    private String regionCode;
    /**
     * 职位代码
     */
    @Excel(name = "职位代码")
    private String positionCode;
    /**
     * 拟录用人数
     */
    @Excel(name = "拟录用人员")
    private Integer planHireNum;
    /**
     * 专业要求
     */
    @Excel(name = "专业")
    private String specialityRequire;
    /**
     * 学位要求
     */
    @Excel(name = "学历、学位")
    private String degreeRequire;
    /**
     * 年龄要求
     */
    @Excel(name = "年龄")
    private String ageRequire;
    /**
     * 工作经验要求
     */
    @Excel(name = "工作经历")
    private String workExperience;
    /**
     * 其他要求
     */
    @Excel(name = "其他")
    private String otherRequire;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;













}
