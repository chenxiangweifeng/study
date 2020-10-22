package com.example.study.core.model.vo.servant;

import lombok.Data;

/**
 * 报考职位实体类
 */
@Data
public class PositionVo {

    private String id;
    /**
     * 机关（单位）名称
     */
    private String unitName;
    /**
     * 区域代码，比如某某县市的某某职位
     */
    private String regionCode;
    /**
     * 职位代码
     */
    private String positionCode;
    /**
     * 职位名称
     */
    private String positionName;
    /**
     * 计划招聘人数
     */
    private Integer planHireNum;
    /**
     * 专业要求
     */
    private String specialityRequire;
    /**
     * 学位要求
     */
    private String degreeRequire;
    /**
     * 年龄要求
     */
    private String ageRequire;
    /**
     * 工作经验要求
     */
    private String workExperience;
    /**
     * 其他要求
     */
    private String otherRequire;
    /**
     * 备注
     */
    private String remark;

}
