package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
/*设备课时*/
@Entity
@Table(name = "tab_equipment_subject")
public class EquipmentSubject {

    /*主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*学校*/
    @Column(name = "school_code", length = 50)
    private String schoolCode;

    /*科目*/
    @Column(name = "subject_id", length = 50)
    private String subjectId;

    /*使用了几节课*/
    @Column(name = "number", length = 50)
    private String number;

    /*当天时间*/
    @Column(name = "new_date", length = 50)
    private Date newDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getNewDate() {
        return newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
}
