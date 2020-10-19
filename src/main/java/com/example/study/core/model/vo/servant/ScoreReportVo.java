package com.example.study.core.model.vo.servant;

import lombok.Data;

/**
 * 分数成绩单
 */
@Data
public class ScoreReportVo {

    private String realName;

    private String phoneNum;

    private double score;

    private String positionCode;

    /**
     * 排名
     */
    private int rank;






}
