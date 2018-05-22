package com.hfkj.bbt.assetmanage.web.vo;

import com.hfkj.bbt.base.util.ComUtil;

/**
 * Created by Administrator on 2017-11-02.
 */
public class AssetSearchVo {

    private String distinctName;

    private String schoolName;

    private int schoolType;

    private int startNumber;

    private int sizeNumber;

    private String schoolCode;

    private String gradeName;

    private String className;

    public void check() {
        setDistinctName(ComUtil.checkValue(distinctName));
        setSchoolName(ComUtil.checkValue(schoolName));
        setGradeName(ComUtil.checkValue(gradeName));
        setClassName(ComUtil.checkValue(className));
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

    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(int schoolType) {
        this.schoolType = schoolType;
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

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
