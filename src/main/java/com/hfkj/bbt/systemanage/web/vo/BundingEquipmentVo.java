package com.hfkj.bbt.systemanage.web.vo;

/**
 * Created by Administrator on 2017-11-17.
 */
public class BundingEquipmentVo {

    /**教室房间ID*/
    private Long roomId;
    /**设备ID集合 如 1,2,3,4*/
    private String ids;
    /**设备号*/
    private String equipmentNo;
    /**配备类型*/
    private Long developType;
    /**负责人*/
    private String manager;
    /**人工及耗材费*/
    private Integer personCost;

    private String schoolCode;

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public Long getDevelopType() {
        return developType;
    }

    public void setDevelopType(Long developType) {
        this.developType = developType;
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
}
