package com.commerxo.commerxoblogservice.domain;


import java.util.Date;
import java.util.Set;


public class Tag {

    private String id;
    private Date createdAt;
    private String createdBy;
    private String tagName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
