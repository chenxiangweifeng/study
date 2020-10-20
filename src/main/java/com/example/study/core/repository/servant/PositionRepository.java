package com.example.study.core.repository.servant;

import com.example.study.core.model.entity.servant.PositionEntity;
import com.example.study.core.model.entity.servant.ScoreReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 沉香微风
 * 职位repository
 */
@Repository
public interface PositionRepository extends JpaRepository<PositionEntity,String>, JpaSpecificationExecutor<PositionEntity> {




}
