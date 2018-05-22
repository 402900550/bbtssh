package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**参数类型表
 * Created by Administrator on 2017-11-06.
 */
@Entity
@Table(name = "tab_parameter_type",uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ParameterType {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /**名称*/
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
