package com.desktop.entity;

import java.util.Date;

public class Exam {
    private Long id;

    private Long courseId;

    private Long teacherId;

    private Long softwareConfigId;

    private Date startTime;

    private Date gmtCreate;

    private Date gmtModified;

    public Exam(Long id, Long courseId, Long teacherId, Long softwareConfigId, Date startTime, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.softwareConfigId = softwareConfigId;
        this.startTime = startTime;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Exam() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSoftwareConfigId() {
        return softwareConfigId;
    }

    public void setSoftwareConfigId(Long softwareConfigId) {
        this.softwareConfigId = softwareConfigId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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