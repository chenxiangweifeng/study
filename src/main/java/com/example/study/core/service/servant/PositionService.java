package com.example.study.core.service.servant;

import com.example.study.common.utils.MapperUtil;
import com.example.study.core.model.entity.servant.PositionEntity;
import com.example.study.core.model.vo.servant.PositionVo;
import com.example.study.core.repository.servant.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * ScoreReport查询
 */
@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public PositionVo findPositionByPosCode(String positionCode) {
        PositionEntity entity = positionRepository.findByPositionCode(positionCode);
        PositionVo vo = MapperUtil.mapperObject(PositionVo.class, entity);
        return vo;
    }


    /**
     * 保存报考职位
     */
    public void savePosition() {


    }


}
