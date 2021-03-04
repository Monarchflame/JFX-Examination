package com.qxt.entity;

public class Software {
    private Long id;

    private String name;

    private String path;

    public Software(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
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
}