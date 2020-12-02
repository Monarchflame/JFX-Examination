package com.desktop.entity;

import java.util.Date;

public class SelectSoftware {
    private Long id;

    private Long softwareConfigId;

    private Long softwareId;

    private Date gmtCreate;

    private Date gmtModified;

    public SelectSoftware(Long id, Long softwareConfigId, Long softwareId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.softwareConfigId = softwareConfigId;
        this.softwareId = softwareId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public SelectSoftware() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoftwareConfigId() {
        return softwareConfigId;
    }

    public void setSoftwareConfigId(Long softwareConfigId) {
        this.softwareConfigId = softwareConfigId;
    }

    public Long getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(Long softwareId) {
        this.softwareId = softwareId;
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