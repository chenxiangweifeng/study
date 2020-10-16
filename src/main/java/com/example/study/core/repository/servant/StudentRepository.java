package com.example.study.core.repository.servant;

import com.example.study.core.model.entity.servant.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 沉香微风
 * 考生信息的保存
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,String>, JpaSpecificationExecutor<StudentEntity> {




}
