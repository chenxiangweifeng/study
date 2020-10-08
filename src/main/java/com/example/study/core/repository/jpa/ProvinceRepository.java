package com.example.study.core.repository.jpa;

import com.example.study.core.model.entity.Person;
import com.example.study.core.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,String>, JpaSpecificationExecutor<Person> {



}
