package com.hfkj.bbt.dataAnalysis.web.vo;

import com.hfkj.bbt.base.util.ComUtil;

/**
 * Created by Administrator on 2017/12/18 0018.
 */
public class DataAnalysisSearchVo {

    private String schoolName;

    private int startNumber;

    private int sizeNumber;

    private Long schoolCode;

    private String ending;

    public void check() {
        setSchoolName(ComUtil.checkValue(schoolName));
        setEnding(ComUtil.checkValue(ending));
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
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

    public Long getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Long schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }
}
