package com.hfkj.bbt.application.web.vo;

import com.hfkj.bbt.base.util.ComUtil;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public class ApplicationVo {

    private String distinctName;

    private String schoolName;

    private String teacherName;

    private String className;

    private Integer workStatus;

    private String schoolCode;

    private int startNumber;

    private int sizeNumber;

    private int schoolType;

    private String gradeName;


    public void check() {
        setDistinctName(ComUtil.checkValue(distinctName));
        setSchoolName(ComUtil.checkValue(schoolName));
        setClassName(ComUtil.checkValue(className));
        setTeacherName(ComUtil.checkValue(teacherName));
        setGradeName(ComUtil.checkValue(gradeName));
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getDistinctName() {
        return distinctName;
    }

    public void setDistinctName(String distinctName) {
        this.distinctName = distinctName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getSizeNumber() {
        return sizeNumber;
    }

    public void setSizeNumber(int sizeNumber) {
        this.sizeNumber = sizeNumber;
    }

    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(int schoolType) {
        this.schoolType = schoolType;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
