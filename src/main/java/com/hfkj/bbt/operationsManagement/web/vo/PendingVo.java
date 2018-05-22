package com.hfkj.bbt.operationsManagement.web.vo;

/**
 * Created by Administrator on 2018-01-09.
 */
public class PendingVo {

    //处理意见
    private String approval;
    //处理方式
    private String pendingType;
    //处理单位
    private String pendingCompany;
    //产生方式
    private String birthType;

    private String processId;
    //任务ID
    private String taskId;
    //设备ID
    private Long accessoryId;

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getPendingType() {
        return pendingType;
    }

    public void setPendingType(String pendingType) {
        this.pendingType = pendingType;
    }

    public String getPendingCompany() {
        return pendingCompany;
    }

    public void setPendingCompany(String pendingCompany) {
        this.pendingCompany = pendingCompany;
    }

    public String getBirthType() {
        return birthType;
    }

    public void setBirthType(String birthType) {
        this.birthType = birthType;
    }
}
