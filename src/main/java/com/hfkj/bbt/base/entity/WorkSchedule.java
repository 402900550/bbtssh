package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Administrator on 2017-12-08.
 */
@Entity
@Table(name = "tab_work_schedule")
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "grade_id")
    private Long gradeId;

    /**学校*/
    @Column(name = "school_code")
    private String schoolCode;

    /**第几节课*/
    @Column(name = "number_course")
    private String numberCourse;
    /**
     * 上课开始时间
     */
    @Column(name = "start")
    private Time start;
    /**
     *
     * 结束时间
     */
    @Column(name = "end")
    private Time end;

    /**上午morning 下午afternoon*/
    @Column(name = "time_type")
    private String timeType;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
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

    public String getNumberCourse() {
        return numberCourse;
    }

    public void setNumberCourse(String numberCourse) {
        this.numberCourse = numberCourse;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
}





























