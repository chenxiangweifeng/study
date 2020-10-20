package com.example.study.core.service.servant;

import com.example.study.common.utils.MapperUtil;
import com.example.study.common.utils.SnowFlakeUtil;
import com.example.study.core.model.entity.servant.ScoreReportEntity;
import com.example.study.core.model.vo.servant.ScoreReportVo;
import com.example.study.core.repository.servant.ScoreReportRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * ScoreReport查询
 */
@Service
public class ScoreReportService {


    @Autowired
    private ScoreReportRepository scoreReportRepository;

    public void saveEntity(ScoreReportVo vo){
        ScoreReportEntity entity = MapperUtil.mapperObject(ScoreReportEntity.class, vo);
        entity.setId(SnowFlakeUtil.ID.nextId());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        scoreReportRepository.save(entity);
    }

    /**
     *
     * @param phoneNum 用于唯一确认一个考生
     * @param positionCode 确定筛选的对象
     */
    public ScoreReportVo scoreRank(String phoneNum, String  positionCode){
        ScoreReportVo vo = new ScoreReportVo();
        List<ScoreReportEntity> scoreReportList = scoreReportRepository.findByPositionCodeOrderByScoreDesc(positionCode);
        int rank = 0;
        for (ScoreReportEntity entity : scoreReportList){
            rank++;
            if(phoneNum.equals(entity.getPhoneNum())){
                vo = MapperUtil.mapperObject(ScoreReportVo.class, entity);
//              直接取出该对象
                vo.setRank(rank);
                break;
            }
        }
        System.out.println(vo);
        return vo;
    }

    public ScoreReportVo rankAllStudents(String phoneNum){
        List<ScoreReportEntity> entityList = scoreReportRepository.findByPhoneNum(phoneNum);
        ScoreReportVo vo = new ScoreReportVo();
        if(CollectionUtils.isNotEmpty(entityList)){
            ScoreReportEntity entity = entityList.get(0);
            vo = MapperUtil.mapperObject(ScoreReportVo.class, entity);
            double score = entity.getScore();
//            根据分数超过这个的进行排名确定   http://help.ih5.cn/question/644.html
            long rank = scoreReportRepository.countByScoreGreaterThan(score);
            vo.setRank((int)rank+1);
        }
        return vo;
    }







}
