package com.hfkj.bbt.base.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-10-09.
 */
@Entity
@Table(name = "tab_role")
public class Role {

    public static final int TEACHER=1;

    public static final int ADMIN=2;

    public static final int SCHOOL_ADMIN=3;

    public static final int EDUCATION=4;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 角色名 TEACHER  ADMIN  SCHOOLADMIN  SPUERADMIN EDUCATION
     */
    @Column(name = "role_name", length = 30)
    private String roleName;
    /**
     * 角色等级 1 2 3 4
     */
    @Column(name = "role_level")
    private Integer roleLevel;
    /**
     * 角色描述 教师 管理员 学校管理员 超级管理员 教委
     */
    @Column(name = "description", length = 30)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
