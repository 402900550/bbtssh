package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.util.Date;

/**附属设备表
 * Created by Administrator on 2017-11-06.
 */
@Entity
@Table(name = "tab_accessory")
public class Accessory {

    /**id*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**对呀DevelopType 表中ID 设备类型*/
    @Column(name = "equipment_type")
    private Long equipmentType;
    /**parameterid
     * 品牌
     * */
    @Column(name = "brand")
    private Long brand;

    /**规格型号ID parameterid*/
    @Column(name = "size_type")
    private Long sizeType;

    /**采购时间*/
    @Column(name = "purchase_date")
    private Date purchaseDate;
    /**单价*/
    @Column(name = "price")
    private Integer price;
    /**维修次数*/
    @Column(name = "repair_times")
    private Integer repairTimes;

    /**使用总时间*/
    @Column(name = "used_time")
    private String usedTime;

    /**教室ID*/
    @Column(name = "room_id")
    private Long roomId;

    /**学校代码*/
    @Column(name = "school_code")
    private String schoolCode;
    /**完好情况 1表示完好 2表示维修中 3表示报废*/
    @Column(name = "facilities")
    private Integer facilities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(Long equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public Long getSizeType() {
        return sizeType;
    }

    public void setSizeType(Long sizeType) {
        this.sizeType = sizeType;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRepairTimes() {
        return repairTimes;
    }

    public void setRepairTimes(Integer repairTimes) {
        this.repairTimes = repairTimes;
    }


    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Integer getFacilities() {
        return facilities;
    }

    public void setFacilities(Integer facilities) {
        this.facilities = facilities;
    }
}
