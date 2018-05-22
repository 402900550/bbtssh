package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-10-09.
 */
@Entity
@Table(name = "tab_school",uniqueConstraints = @UniqueConstraint(columnNames = {"school_code","name"}))
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*学校单位代码*/
    @Column(name = "school_code",length = 30,nullable = false)
    private String schoolCode;

    /*学校名称*/
    @Column(name = "name",length = 50,nullable = false)
    private String name;

    /*学校描述*/
    @Column(name = "description",length = 255)
    private String description;

    /*区县码*/
    @Column(name = "qxm",length = 50,nullable = false)
    private String qxm;

    /*单位上下级关系*/
    @Column(name = "parent_code",length = 50)
    private String parentCode;

    /*单位电话 学校电话*/
    @Column(name = "telephone",length = 50)
    private String telephone;

    /*传真*/
    @Column(name = "fax",length = 50)
    private String fax;

    /*邮政编码*/
    @Column(name = "zip_code",length = 50)
    private String zip_code;

    /*学校地址*/
    @Column(name = "address",length = 50)
    private String address;

    /*url*/
    @Column(name = "url",length = 50)
    private String url;

    /*学校图片地址*/
    @Column(name = "viewpic",length = 50)
    private String viewPic;

    /*学校类型
     * -1教委
     * 0直属单位
     * 1小学
     * 2初中
     * 3高中
     * 4一贯制学校
     * 5完全中学
     * 6职高
     * 7幼儿园
     * 8实属中学
     * 9其他*/
    @Column(name = "type",nullable = false)
    private Integer type;

    /*学校状态*/
    @Column(name = "status")
    private Integer status;

    /*办事指南*/
    @Column(name = "guide",length = 255)
    private String guide;

    /*版本号*/
    @Column(name = "version",nullable = false)
    private Integer version;

    //网关IP
    @Column(name = "ip")
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQxm() {
        return qxm;
    }

    public void setQxm(String qxm) {
        this.qxm = qxm;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getViewPic() {
        return viewPic;
    }

    public void setViewPic(String viewPic) {
        this.viewPic = viewPic;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
