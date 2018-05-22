package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-07-13.
 */
@Table(name = "tab_school_type")
@Entity
public class SchoolType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**类型值*/
    @Column(name = "type_int")
    private Integer typeInt;

    /**类型名称*/
    @Column(name = "type_name")
    private String typeName;

    public Integer getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(Integer typeInt) {
        this.typeInt = typeInt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
