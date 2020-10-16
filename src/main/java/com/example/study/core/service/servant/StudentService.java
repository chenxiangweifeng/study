package com.example.study.core.service.servant;

import com.example.study.common.utils.MapperUtil;
import com.example.study.common.utils.SnowFlakeUtil;
import com.example.study.core.model.entity.servant.StudentEntity;
import com.example.study.core.model.vo.servant.StudentVo;
import com.example.study.core.repository.servant.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * person 类的多条件查询
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 保存一个考生
     */
    public void save(StudentVo vo) {
        // vo 转换成entity
        StudentEntity studentEntity = MapperUtil.mapperObject(StudentEntity.class, vo);
        studentEntity.setId(SnowFlakeUtil.ID.nextId());
        studentEntity.setCreateTime(new Date());
        studentEntity.setUpdateTime(new Date());
        studentRepository.save(studentEntity);
    }









}
