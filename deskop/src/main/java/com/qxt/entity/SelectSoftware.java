package com.qxt.entity;

public class SelectSoftware {
    private Long id;

    private Long softwareConfigId;

    private Long softwareId;

    public SelectSoftware(Long id, Long softwareConfigId, Long softwareId) {
        this.id = id;
        this.softwareConfigId = softwareConfigId;
        this.softwareId = softwareId;
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
}