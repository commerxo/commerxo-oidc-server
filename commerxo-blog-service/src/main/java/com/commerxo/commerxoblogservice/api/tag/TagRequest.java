package com.commerxo.commerxoblogservice.api.tag;

import com.commerxo.commerxoblogservice.domain.Tag;

public class TagRequest {

    private String tagName;
    private String description;

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

    public static Tag mapToEntity(TagRequest request){
        Tag tag = new Tag();
        tag.setTagName(request.getTagName());
        tag.setDescription(request.getDescription());
        return tag;
    }
}
