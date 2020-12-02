package com.desktop.entity;

import java.util.Date;

public class Course {
    private Long id;

    private Long teacherId;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;

    public Course(Long id, Long teacherId, String name, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Course() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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