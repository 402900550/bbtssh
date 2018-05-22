package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
@Entity
@Table(name = "tab_operations")
public class Operations {
    /**手动异常流程定义KEY*/
    public static final String HANDEXCEPTIONKEY="HandException";
    /**自动异常流程定义KEY*/
    public static final String AUTOEXCEPTIONAKEY="autoExceptionA";
    /**自动异常流程定义KEY*/
    public static final String SCRAPKEY="scarpException";
    /**选择给哪种管理员*/
    public static final String CHOOSEMANAGERS="chooseManagers";

    public static final String PENDINGUSER="pendingUser";
    /**学校管理员*/
    public static final String SCHOOLMANAGERS="schoolManagers";

    public static final String SCHOOLMANAGER="schoolManager";

    public static final String CHOOSE="choose";

    public static final String EDUATIONMANAGER="eduUserId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**异常产生时间*/
    @Column(name = "start_date")
    private Date startDate;
    /**异常类型*/
    @Column(name = "exception_type")
    private String exceptionType;
    /**异常描述*/
    @Column(name = "exception_description")
    private String exceptionDescription;
    /**出现问题的班级*/
    @Column(name = "class_id")
    private Long classId;
    /**异常学校*/
    @Column(name = "school_code")
    private String schoolCode;

    @Column(name = "submit_user")
    private Long submitUser;

    /**流程实例ID*/
    @Column(name = "process_id")
    private String processId;

    @Column(name = "end_date")
    private Date endDate;

    /**手动产生：hand 自动产生：auto*/
    @Column(name = "birth_type")
    private String birthType;

    /**需要报废的设备ID*/
    @Column(name = "accessory_id")
    private Long accessoryId;

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getBirthType() {
        return birthType;
    }

    public void setBirthType(String birthType) {
        this.birthType = birthType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(Long submitUser) {
        this.submitUser = submitUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
