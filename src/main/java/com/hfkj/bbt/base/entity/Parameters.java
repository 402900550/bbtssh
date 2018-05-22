package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**参数表
 * Created by Administrator on 2017-11-06.
 */
@Entity
@Table(name = "tab_parameters",uniqueConstraints = @UniqueConstraint(columnNames = {"type_id","detail"}))
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**参数类型ID*/
    @Column(name = "type_id")
    private Long typeId;


    /**参数详情*/
    @Column(name = "detail")
    private String detail;

    /**备注可以填写 联系电话等信息*/
    @Column(name = "remark")
    private String remark;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
