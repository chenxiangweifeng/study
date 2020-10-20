package com.example.study.core.model.entity.servant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 报考职位实体类
 */
@Data
@Entity
@Table(name = "tb_position")
public class PositionEntity {

    @Id
    @Column(name = "id")
    private String id;
    /**
     * 机关（单位）名称
     */
    @Column(name = "unit_name")
    private String unitName;
    /**
     * 区域代码，比如某某县市的某某职位
     */
    @Column(name = "region_code")
    private String regionCode;
    /**
     * 职位代码
     */
    @Column(name = "position_code")
    private String positionCode;
    /**
     * 职位名称
     */
    @Column(name = "position_name")
    private String positionName;

    /**
     * 计划招聘人数
     */
    @Column(name = "plan_hire_num")
    private Integer planHireNum;

    /**
     * 专业要求
     */
    @Column(name = "speciality_require")
    private String specialityRequire;

    /**
     * 学位要求
     */
    @Column(name = "degree_require")
    private String degreeRequire;

    /**
     * 年龄要求
     */
    @Column(name = "age_require")
    private String ageRequire;

    /**
     * 工作经验要求
     */
    @Column(name = "work_experience")
    private String workExperience;

    /**
     * 其他要求
     */
    @Column(name = "other_require")
    private String otherRequire;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
