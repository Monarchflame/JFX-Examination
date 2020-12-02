package com.desktop.entity;

import java.util.Date;

public class Student {
    private Long id;

    private String name;

    private String college;

    private String major;

    private String mobile;

    private Date gmtCreate;

    private Date gmtModified;

    public Student(Long id, String name, String college, String major, String mobile, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.major = major;
        this.mobile = mobile;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Student() {
        super();
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}