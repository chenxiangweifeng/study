package com.example.study.core.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "dcc_provinces")
public class Province implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "provinceid")
    private String provinceId;

    @Column(name = "province",length = 32)
    private String province;


}
