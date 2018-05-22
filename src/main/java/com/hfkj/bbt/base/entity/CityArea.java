package com.hfkj.bbt.base.entity;

import javax.persistence.*;

@Entity
@Table(name = "tab_cityarea")
public class CityArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*区域名称*/
    @Column(name = "name",length = 50)
    private String name;

    /*区域代码*/
    @Column(name = "detail_code",length = 50)
    private String detailCode;

    /*省份*/
    @Column(name = "province",length = 50)
    private String province;

    /*所属城市*/
    @Column(name = "city",length = 50)
    private String city;

    /*省份代码*/
    @Column(name = "area_code",length = 50)
    private String areaCode;

    /*企业 IP*/
    @Column(name = "qyip",length = 50)
    private String qyip;

    /*0表示省分 1表示市 2表示区县*/
    @Column(name="tatings")
    private Integer taTings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getQyip() {
        return qyip;
    }

    public void setQyip(String qyip) {
        this.qyip = qyip;
    }

    public Integer getTaTings() {
        return taTings;
    }

    public void setTaTings(Integer taTings) {
        this.taTings = taTings;
    }
}
