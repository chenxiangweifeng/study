package com.example.study.core.service.servant;

import com.example.study.common.utils.MapperUtil;
import com.example.study.common.utils.SnowFlakeUtil;
import com.example.study.core.model.entity.servant.StudentEntity;
import com.example.study.core.model.vo.servant.StudentVo;
import com.example.study.core.repository.servant.StudentRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * person 类的多条件查询
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 保存/更新一个考生
     */
    public void save(StudentVo vo) {
        String id = vo.getId();
        // vo 转换成entity
        StudentEntity studentEntity = MapperUtil.mapperObject(StudentEntity.class, vo);
        if(StringUtils.isBlank(id)){
//            save
            studentEntity.setId(SnowFlakeUtil.ID.nextId());
            studentEntity.setCreateTime(new Date());
            studentEntity.setUpdateTime(new Date());
            studentRepository.save(studentEntity);
        }else {
//            update
            studentEntity.setUpdateTime(new Date());
            studentRepository.save(studentEntity);
        }
    }

    public Page<StudentVo> queryPageStudentsNoArgs(int page, int limit){
        Pageable pageable = PageRequest.of(page-1,limit);
        Page<StudentEntity> pageStudent = studentRepository.findAll(pageable);
        List<StudentEntity> studentEntities = pageStudent.getContent();
        List<StudentVo> studentVoList = MapperUtil.mapperObjectList(StudentVo.class, studentEntities);
        return new PageImpl<>(studentVoList,pageable,pageStudent.getTotalElements());
    }

    public StudentVo findStudentById(String id){
        Optional<StudentEntity> optional = studentRepository.findById(id);
        StudentEntity studentEntity = new StudentEntity();
        while (optional.isPresent()){
            studentEntity = optional.get();
        }
        StudentVo studentVo = MapperUtil.mapperObject(StudentVo.class, studentEntity);
        return studentVo;
    }

    public void  deleteStudentById(String id){
        studentRepository.deleteById(id);
    }

}
