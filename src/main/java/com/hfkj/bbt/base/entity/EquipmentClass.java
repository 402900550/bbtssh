package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
/*设备课时*/
@Entity
@Table(name = "tab_equipment_class")
public class EquipmentClass {

    /*主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /*学校*/
    @Column(name = "school_code", length = 50)
    private String schoolCode;

    /*年级id*/
    @Column(name = "grade_id", length = 50)
    private String gradeId;

    /*班级ic*/
    @Column(name = "class_id", length = 50)
    private String classId;

    /*使用了几节课*/
    @Column(name = "number", length = 50)
    private String number;

    /*总共课数*/
    @Column(name = "number_all", length = 50)
    private String numberAll;

    /*当天时间*/
    @Column(name = "new_date", length = 50)
    private Date newDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
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

    public String getNumberAll() {
        return numberAll;
    }

    public void setNumberAll(String numberAll) {
        this.numberAll = numberAll;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
