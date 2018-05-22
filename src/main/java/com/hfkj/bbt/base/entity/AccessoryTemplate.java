package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-11-13.
 */
@Entity
@Table(name = "tab_accessory_template")
public class AccessoryTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**配备类型*/
    @Column(name = "develop_type")
    private Long developType;
    /**设备ID集合 对呀Accessory表中ID*/
    @Column(name = "id_strings")
    private String idStrings;
    /**模板保存名称*/
    @Column(name = "template_name")
    private String templateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDevelopType() {
        return developType;
    }

    public void setDevelopType(Long developType) {
        this.developType = developType;
    }

    public String getIdStrings() {
        return idStrings;
    }

    public void setIdStrings(String idStrings) {
        this.idStrings = idStrings;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
