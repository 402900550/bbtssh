package com.hfkj.bbt.systemanage.web.vo;


import javax.persistence.Column;
import java.sql.Time;

/**
 * Created by Administrator on 2017-12-08.
 */
public class WorkScheduleVo {

    private Long id;

    private String schoolCode;

    private String timeType;

    private String start;

    private String end;

    private String numberCourse;

    private Long gradeId;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

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

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNumberCourse() {
        return numberCourse;
    }

    public void setNumberCourse(String numberCourse) {
        this.numberCourse = numberCourse;
    }
}
