package com.desktop.entity;

import java.util.Date;

public class ExamArrangement {
    private Long id;

    private Long examId;

    private Long studentId;

    private Date gmtCreate;

    private Date gmtModified;

    public ExamArrangement(Long id, Long examId, Long studentId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.examId = examId;
        this.studentId = studentId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public ExamArrangement() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
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