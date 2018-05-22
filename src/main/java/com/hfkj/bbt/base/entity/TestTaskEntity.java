package com.hfkj.bbt.base.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "test_task_entity")
public class TestTaskEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "equipment_no")
    private String equipmentNo;

    @Column(name = "ip")
    private String ip;

    @Column(name = "time")
    private Date time;

    @Column(name = "work_status")
    private Integer workStatus;

    @Column(name = "collect_time")
    private Date collectTime;

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }
}
