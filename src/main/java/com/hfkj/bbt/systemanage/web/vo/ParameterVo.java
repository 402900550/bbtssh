package com.hfkj.bbt.systemanage.web.vo;

import com.hfkj.bbt.base.util.ComUtil;

/**
 * Created by Administrator on 2017-11-08.
 */
public class ParameterVo {


    private Long parameterTypeId;


    private Long parameterId;

    private String parameterValue;

    private String remark;

    private Integer startNum;

    private Integer pageSize;




    public void check(){
        setParameterValue(ComUtil.checkValue(parameterValue));

    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public Long getParameterTypeId() {
        return parameterTypeId;
    }

    public void setParameterTypeId(Long parameterTypeId) {
        this.parameterTypeId = parameterTypeId;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
