package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
/*教室*/
@Entity
@Table(name = "tab_classroom",uniqueConstraints = {@UniqueConstraint(columnNames = {"room_code","building_id","equipment_no"})})
public class ClassRoom {
    /*主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*教学楼ID*/
    @Column(name = "building_id")
    private Long buildingId;

    /*教室代码 如2栋1楼1号房2101*/
    @Column(name = "room_code", length = 50)
    private  String roomCode;

    /*教室状态表示是否绑定班级*/
    @Column(name = "status")
    private Integer status;

    /*教室类型 1 表示教室 2 表示办公室*/
    @Column(name = "room_type")
    private Integer roomType;

    /**耗材费 人工时费*/
    @Column(name = "cost")
    private Integer cost;

    /**配备类型*/
    @Column(name = "develop_type_id")
    private Long developTypeId;

    /**设备号*/
    @Column(name = "equipment_no",unique = true)
    private String equipmentNo;

    @Column(name = "manager")
    private String manager;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Long getDevelopTypeId() {
        return developTypeId;
    }

    public void setDevelopTypeId(Long developTypeId) {
        this.developTypeId = developTypeId;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }
}
