package com.hfkj.bbt.assetmanage.web.vo;

import com.hfkj.bbt.base.entity.Accessory;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */
public class EquipmentVo {

    /**配备类型*/
    private Long typeLong;

    /**学校单位代码*/
    private String schoolCode;

    /**负责人项目经理*/
    private String manager;

    /**人工费*/
    private Integer personCost;

    /**耗材费*/
    private Integer datumCost;

    /**教室ID*/
    private Long roomId;


    /**设备号*/
    private String equipmentNo;

    /**班级ID*/
    private Long classId;

    /**设备集合*/
    private List<Accessory> accessories;


    private String inputDate;

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public Integer getDatumCost() {
        return datumCost;
    }

    public void setDatumCost(Integer datumCost) {
        this.datumCost = datumCost;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }


    public Long getTypeLong() {
        return typeLong;
    }

    public void setTypeLong(Long typeLong) {
        this.typeLong = typeLong;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Integer getPersonCost() {
        return personCost;
    }

    public void setPersonCost(Integer personCost) {
        this.personCost = personCost;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

}
