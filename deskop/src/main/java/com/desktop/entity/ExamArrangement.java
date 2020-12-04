package com.desktop.entity;

public class ExamArrangement {
    private Long id;

    private Long examId;

    private Long studentId;

    public ExamArrangement(Long id, Long examId, Long studentId) {
        this.id = id;
        this.examId = examId;
        this.studentId = studentId;
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
}