package com.example.study.core.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "dcc_cities")
public class City implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "cityid")
    private String cityid;

    @Column(name = "city")
    private String city;

    @Column(name = "provinceid")
    private String provinceId;


}
