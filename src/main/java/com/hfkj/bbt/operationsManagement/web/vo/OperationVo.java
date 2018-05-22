package com.hfkj.bbt.operationsManagement.web.vo;


/**
 * Created by Administrator on 2018/1/3 0003.
 */
public class OperationVo {

    /**产生时间*/
    private String operateDate;
    /**学校*/
    private String operateSchool;
    /**流程状态*/
    private String processStatus;
    /**产生方式 auto 自动  hand手动  scrap报废*/
    private String birthType;
    /**任务ID*/
    private String taskId;
    /**流程实例ID*/
    private String processId;

    public String getBirthType() {
        return birthType;
    }

    public void setBirthType(String birthType) {
        this.birthType = birthType;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateSchool() {
        return operateSchool;
    }

    public void setOperateSchool(String operateSchool) {
        this.operateSchool = operateSchool;
    }



    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
