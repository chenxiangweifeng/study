package com.example.study.core.model.entity.servant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 分数成绩单
 */
@Data
@Entity
@Table(name = "tb_score_report")
public class ScoreReportEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "score")
    private double score;
    /**
     * 职位代码
     */
    @Column(name = "position_code")
    private String positionCode;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
