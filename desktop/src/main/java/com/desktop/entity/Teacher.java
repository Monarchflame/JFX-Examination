package com.desktop.entity;

public class Teacher {
    private Long id;

    private String teacherNo;

    private String password;

    private String name;

    private String college;

    private String mobile;

    private String email;

    public Teacher(Long id, String teacherNo, String password, String name, String college, String mobile, String email) {
        this.id = id;
        this.teacherNo = teacherNo;
        this.password = password;
        this.name = name;
        this.college = college;
        this.mobile = mobile;
        this.email = email;
    }

    public Teacher() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}