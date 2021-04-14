package com.desktop.entity;

import java.time.LocalDateTime;

public class Exam {
    private Long id;

    private String courseNo;

    private Long teacherId;

    private Long softwareConfigId;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Short examTime;

    public Exam(Long id, String courseNo, Long teacherId, Long softwareConfigId, String name, LocalDateTime startTime, LocalDateTime endTime, Short examTime) {
        this.id = id;
        this.courseNo = courseNo;
        this.teacherId = teacherId;
        this.softwareConfigId = softwareConfigId;
        this.name = name;
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

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo == null ? null : courseNo.trim();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Short getExamTime() {
        return examTime;
    }

    public void setExamTime(Short examTime) {
        this.examTime = examTime;
    }
}