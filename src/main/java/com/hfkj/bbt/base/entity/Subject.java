package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-06-30.
 */
@Entity
@Table(name = "tab_subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /**科目名称*/
    @Column(name = "subject_name")
    private String subjectName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
