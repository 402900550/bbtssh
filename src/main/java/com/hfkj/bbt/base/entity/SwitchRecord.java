package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/7 0007.
 */
@Entity
@Table(name = "tab_switch_record")
public class SwitchRecord {

    /**id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*IC卡号*/
    @Column(name = "user_id", length = 50)
    private Long userId;

    /*设备编号*/
    @Column(name = "equipment_no", length = 50)
    private  String equipmentNo;

    /*设备编号*/
    @Column(name = "equipment_type", length = 50)
    private  int equipmentType;

    /*设备类型*/
    @Column(name = "device_type", length = 50)
    private String deviceType;

    /*类型开关*/
    @Column(name = "status")
    private Integer status;

    /*区县码*/
    @Column(name = "qxm")
    private String qxm;

    /*学校代码*/
    @Column(name = "school_code")
    private String schoolCode;

    /*当前时间*/
    @Column(name = "new_time")
    private Date newTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQxm() {
        return qxm;
    }

    public void setQxm(String qxm) {
        this.qxm = qxm;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
