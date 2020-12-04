package com.desktop.entity;

public class Course {
    private Long id;

    private Long teacherId;

    private String name;

    public Course(Long id, Long teacherId, String name) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
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
}