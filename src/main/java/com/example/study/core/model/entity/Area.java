package com.example.study.core.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "dcc_areas")
public class Area implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "areaid")
    private String areaid;

    @Column(name = "area")
    private String area;

    @Column(name = "cityid")
    private String cityid;

}
