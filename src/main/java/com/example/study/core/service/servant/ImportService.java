package com.example.study.core.service.servant;

import com.example.study.common.utils.MapperUtil;
import com.example.study.common.utils.MergeCellExcelUtil;
import com.example.study.common.utils.SnowFlakeUtil;
import com.example.study.core.model.ao.servant.PositionAo;
import com.example.study.core.model.dto.servant.DefImportParams;
import com.example.study.core.model.entity.servant.PositionEntity;
import com.example.study.core.repository.servant.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class ImportService {

    @Autowired
    private PositionRepository positionRepository;

    public void importPositionTable(){
        List<PositionEntity> positionEntityList = null;
        try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/servant/files/公务员拟录用职位表.xlsx");
            DefImportParams params = new DefImportParams();
            params.setStartReadLine(4);
            params.setIndexNum(10);
            List<PositionAo> positionAoList = MergeCellExcelUtil.readExcelToObj(new FileInputStream(file),params, PositionAo.class);
            positionEntityList = MapperUtil.mapperObjectList(PositionEntity.class, positionAoList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (PositionEntity entity: positionEntityList){
            entity.setId(SnowFlakeUtil.ID.nextId());
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            positionRepository.save(entity);
        }
    }


}
