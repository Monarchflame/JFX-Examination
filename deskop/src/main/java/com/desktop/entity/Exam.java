package com.desktop.entity;

import java.util.Date;

public class Exam {
    private Long id;

    private Long courseId;

    private Long teacherId;

    private Long softwareConfigId;

    private Date startTime;

    private Date endTime;

    private Short examTime;

    public Exam(Long id, Long courseId, Long teacherId, Long softwareConfigId, Date startTime, Date endTime, Short examTime) {
        this.id = id;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.softwareConfigId = softwareConfigId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.examTime = examTime;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Short getExamTime() {
        return examTime;
    }

    public void setExamTime(Short examTime) {
        this.examTime = examTime;
    }
}