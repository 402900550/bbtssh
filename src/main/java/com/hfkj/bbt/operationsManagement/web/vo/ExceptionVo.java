package com.hfkj.bbt.operationsManagement.web.vo;

/**
 * Created by Administrator on 2018-01-05.
 */
public class ExceptionVo {


    private String exceptionType;

    private String exceptionDescription;

    private Long classId;
    //指定任务人的单位
    private String schoolCode;


    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
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
}
