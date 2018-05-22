package com.hfkj.bbt.systemanage.web.vo;

import com.hfkj.bbt.base.util.ComUtil;

/**
 * Created by Administrator on 2017-10-24.
 */
public class SearchVo {

    private String distinctName;

    private String schoolName;

    private String buildingName;

    private int startNumber;

    private int sizeNumber;

    private int schoolType;

    private String userName;

    private String schoolCode;

    private String roomCode;

    private Long buildingId;

    private String className;

    public void check() {
        setSchoolCode(ComUtil.checkValue(schoolCode));
        setDistinctName(ComUtil.checkValue(distinctName));
        setSchoolName(ComUtil.checkValue(schoolName));
        setBuildingName(ComUtil.checkValue(buildingName));
        setUserName(ComUtil.checkValue(userName));
        setRoomCode(ComUtil.checkValue(roomCode));
        setClassName(ComUtil.checkValue(className));
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getDistinctName() {
        return distinctName;
    }

    public void setDistinctName(String distinctName) {
        this.distinctName = distinctName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getSizeNumber() {
        return sizeNumber;
    }

    public void setSizeNumber(int sizeNumber) {
        this.sizeNumber = sizeNumber;
    }

    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(int schoolType) {
        this.schoolType = schoolType;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}