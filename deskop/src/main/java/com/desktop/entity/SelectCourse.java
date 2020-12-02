package com.desktop.entity;

import java.util.Date;

public class SelectCourse {
    private Long id;

    private Long courseId;

    private Long studentId;

    private Date gmtCreate;

    private Date gmtModified;

    public SelectCourse(Long id, Long courseId, Long studentId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public SelectCourse() {
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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