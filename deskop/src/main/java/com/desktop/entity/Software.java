package com.desktop.entity;

import java.util.Date;

public class Software {
    private Long id;

    private String name;

    private String path;

    private Date gmtCreate;

    private Date gmtModified;

    public Software(Long id, String name, String path, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Software() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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