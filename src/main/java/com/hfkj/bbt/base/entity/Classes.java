package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/9 0009.
 */
/*班级*/
@Entity
@Table(name = "tab_classes",uniqueConstraints = @UniqueConstraint(columnNames = {"school_code","class_name","grade_id"}))
public class Classes {
    /*主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /*学校*/
    @Column(name = "school_code", length = 50)
    private String schoolCode;

    /*班级名称*/
    @Column(name = "class_name", length = 50)
    private String className;

    /**年级*/
    @Column(name = "grade_id")
    private Long gradeId;

    /*班级状态表示是否绑定教室1为绑定 0为未绑定*/
    @Column(name = "status")
    private int status;


    @Column(name = "create_date")
    private Date createDate;

    /**教室ID*/
    @Column(name = "room_id")
    private Long roomId;


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
