package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/9 0009.
 */
/*年级*/
@Entity
@Table(name = "tab_grade",uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Grade {
    /*主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*年级名称*/
    @Column(name = "name", length = 50)
    private String name;

    /*创建时间*/
    @Column(name = "create_date")
    private Date createDate;

    /*状态 已删除*/
    @Column(name = "status")
    private int status;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
