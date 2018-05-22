package com.hfkj.bbt.systemanage.web.vo;


import com.hfkj.bbt.base.util.ComUtil;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class ClassVo {
    private String className;

    private String schoolCode;

    private Long gradeId;

    private Long classId;

    private Long buildingId;

    private String equipmentNo;


    private Long roomId;

    private String doType;

    private int roomType;

    private String roomCode;

    public boolean check(){
        if (gradeId==null|| !ComUtil.stringIsNotNull(className)){
            return false;
        }
        return true;
    }

    public boolean validate(){
        if (!ComUtil.stringIsNotNull(equipmentNo)){
            equipmentNo=null;
        }
        if (!ComUtil.stringIsNotNull(roomCode)){
            return false;
        }

        return true;
    }


    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}
