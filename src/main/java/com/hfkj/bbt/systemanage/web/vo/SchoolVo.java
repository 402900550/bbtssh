package com.hfkj.bbt.systemanage.web.vo;

import com.hfkj.bbt.base.util.ComUtil;

/**
 * Created by Administrator on 2017-10-27.
 */
public class SchoolVo {

    private Long schoolId;

    private String schoolName;

    private String schoolCode;

    private int schoolType;

    private String schoolPhone;

    private String schoolAddress;

    private String schoolDescription;

    private String distinct;

    private String doType;


    public boolean check(){
        boolean flag = ComUtil.stringIsNotNull(schoolName, schoolCode);
        boolean flag2 = !"10086".equals(distinct);
        return flag&&flag2;
    }



    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(int schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolPhone() {
        return schoolPhone;
    }

    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolDescription() {
        return schoolDescription;
    }

    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }
}
