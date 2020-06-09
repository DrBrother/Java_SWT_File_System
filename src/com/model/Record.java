package com.model;

import java.util.Date;

public class Record {
    private String path;
    private String name;
    private Date modified;
    private String extension;
    private long size;

    public Record(String path, String name, Date modified, String extension, long size) {
        this.path = path;
        this.name = name;
        this.modified = modified;
        this.extension = extension;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

}
