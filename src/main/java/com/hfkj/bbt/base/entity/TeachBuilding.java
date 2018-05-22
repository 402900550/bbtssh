package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
/*教学楼*/
@Entity
@Table(name = "tab_teach_building")
public class TeachBuilding {
    /*教学楼id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*教室名称*/
    @Column(name = "building_name", length = 50)
    private  String buildingName;

    /*学校代码*/
    @Column(name = "school_code", length = 50)
    private  String schoolCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
}
