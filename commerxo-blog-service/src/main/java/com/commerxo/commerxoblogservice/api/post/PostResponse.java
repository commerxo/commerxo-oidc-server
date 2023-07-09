package com.commerxo.commerxoblogservice.api.post;

import com.commerxo.commerxoblogservice.common.BaseResponse;
import com.commerxo.commerxoblogservice.domain.Post;
import com.commerxo.commerxoblogservice.domain.Tag;

public class PostResponse extends BaseResponse<PostResponse, Post> {

    private String id;
    private String title;
    private String content;
    private Tag tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public PostResponse mapFromEntity(Post post) {
        return null;
    }
}
