package com.example.study.core.repository.servant;

import com.example.study.core.model.entity.servant.ScoreReportEntity;
import com.example.study.core.model.entity.servant.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 沉香微风
 * 分数报告单实体类
 */
@Repository
public interface ScoreReportRepository extends JpaRepository<ScoreReportEntity,String>, JpaSpecificationExecutor<ScoreReportEntity> {

    List<ScoreReportEntity> findByPositionCodeOrderByScoreDesc(String positionCode);

    List<ScoreReportEntity> findByPhoneNum(String phoneNum);

    /**
     *
     * @param score
     * @return 超过该分数的人数有多少个
     */
    long countByScoreGreaterThan(double score);

}
