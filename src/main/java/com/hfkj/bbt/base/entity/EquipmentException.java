package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-10-09.
 */
@Entity
@Table(name="tab_equipment_exception")
public class EquipmentException {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*数据类型*/
    @Column(name = "equipment_type",length = 50)
    private int equipmentType;

    /*设备号*/
    @Column(name = "equipment_no",length = 50)
    private String equipmentNo;

    /*省市区县*/
    @Column(name = "ssqx",length = 50)
    private String ssqx;

    /*学校代码*/
    @Column(name = "school_code",length = 50)
    private String schoolCode;

    /*上报时间*/
    @Column(name = "create_date")
    private Timestamp createDate;

    /*ic卡*/
    @Column(name = "icard_no",length = 50)
    private String icardNo;

    /*异常类型0-255*/
    @Column(name = "exception_type",length = 50)
    private String exceptionType;

    /*异常参数*/
    @Column(name = "exception_temp",length = 50)
    private String exceptionTemp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getSsqx() {
        return ssqx;
    }

    public void setSsqx(String ssqx) {
        this.ssqx = ssqx;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getIcardNo() {
        return icardNo;
    }

    public void setIcardNo(String icardNo) {
        this.icardNo = icardNo;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionTemp() {
        return exceptionTemp;
    }

    public void setExceptionTemp(String exceptionTemp) {
        this.exceptionTemp = exceptionTemp;
    }
}
