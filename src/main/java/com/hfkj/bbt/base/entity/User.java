package com.hfkj.bbt.base.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017-10-09.
 */
@Entity
@Table(name = "tab_user",uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**用户名 登陆系统的名*/
    @Column(name = "user_name",length = 30,nullable = false)
    private String userName;
    /**密码*/
    @Column(name = "password",length = 30,nullable = false)
    private String password;

    /**真实姓名*/
    @Column(name = "real_name",length = 30,nullable = false)
    private String realName;

    /*单位代码 即学校表中的schoolcode*/
    @Column(name = "school_code",length = 30,nullable = false)
    private String schoolCode;

    /*生日*/
    @Column(name = "birthday")
    private Date birthday;

    /*性别 1男 2女*/
    @Column(name = "sex")
    private Integer sex;

    /*职位 */
    @Column(name = "work",length = 30)
    private String work;

    /*头像地址*/
    @Column(name = "photo_url",length = 100)
    private String photoUrl;

    /*电话号码*/
    @Column(name = "iphone",length = 30)
    private String iphone;

    /*教师IC卡*/
    @Column(name = "icard_no",length = 30)
    private String icardNo;

    /*身份证号*/
    @Column(name = "cert_no",length = 30)
    private String certNo;

    /*兴趣爱好*/
    @Column(name = "educational",length = 30)
    private String educational;

    /*所教科目ID*/
    @Column(name = "subject_id")
    private Long subjectId;

    /*邮箱*/
    @Column(name = "email",length = 30)
    private String email;

    /*个人描述*/
    @Column(name = "professional")
    private String professional;

    /**用户状态 -1停用 0待审核 1启用 2审核未通过*/
    @Column(name = "status",nullable = false)
    private Integer status;

    /*账号创建时间*/
    @Column(name = "create_date",nullable = false)
    private Timestamp createDate;

    /*账号更新时间*/
    @Column(name = "update_date")
    private Timestamp updateDate;

    /*微信*/
    @Column(name = "open_id")
    private String openid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getIcardNo() {
        return icardNo;
    }

    public void setIcardNo(String icardNo) {
        this.icardNo = icardNo;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
