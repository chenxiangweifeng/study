package com.example.study.core.repository.jpa;

import com.example.study.core.model.entity.City;
import com.example.study.core.model.entity.Person;
import com.example.study.core.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,String>, JpaSpecificationExecutor<Person> {



}
