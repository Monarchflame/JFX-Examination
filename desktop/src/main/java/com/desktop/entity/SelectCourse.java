package com.desktop.entity;

public class SelectCourse {
    private Long id;

    private String courseNo;

    private Long studentId;

    public SelectCourse(Long id, String courseNo, Long studentId) {
        this.id = id;
        this.courseNo = courseNo;
        this.studentId = studentId;
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

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo == null ? null : courseNo.trim();
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}