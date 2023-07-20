package com.commerxo.commerxoblogservice.domain;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

public class Post {

    private String id;
    private String title;
    private String content;
    private String createdBy;
    private String updatedBy;
    private Instant updatedAt;
    private Instant createdAt;
    private Instant publishedOn;
    private boolean published;

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getPublishedOn() {
        return publishedOn;
    }

    public boolean isPublished() {
        return this.published;
    }

    public static Builder withId(String id){
        if(!StringUtils.hasText(id))
            throw new IllegalArgumentException("Id can't be null!");
        return new Builder(id);
    }

    public static Builder from(Post post){
        if(post == null)
            throw new IllegalArgumentException("post can't be null!");
        return new Builder(post);
    }


    public static class Builder implements Serializable{

        private String id;
        private String title;
        private String content;
        private String createdBy;
        private String updatedBy;
        private Instant updatedAt;
        private Instant createdAt;
        private Instant publishedOn;
        private boolean published;

        public Builder(String id){
            this.id = id;
        }

        public Builder(Post post){
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.createdBy = post.getCreatedBy();
            this.updatedBy = post.getUpdatedBy();
            this.published = post.isPublished();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getCreatedAt();
            this.publishedOn = post.getPublishedOn();
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder createdBy(String createdBy){
            this.createdBy = createdBy;
            return this;
        }

        public Builder updatedBy(String updatedBy){
            this.updatedBy = updatedBy;
            return this;
        }
        public Builder createdAt(Instant createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Instant updatedAt){
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder publishedOn(Instant publishedOn){
            this.publishedOn = publishedOn;
            return this;
        }

        public Builder isPublished(boolean published){
            this.published = published;
            return this;
        }

        public Post build(){

            Post post = new Post();
            if(!StringUtils.hasText(this.title))
                throw new IllegalArgumentException("title can't be empty!");
            post.id = this.id;
            post.title = this.title;
            post.content = this.content;
            post.createdBy = this.createdBy;
            post.updatedBy = this.updatedBy;
            post.createdAt = this.createdAt;
            post.updatedAt = this.updatedAt;
            post.published = this.published;
            post.publishedOn = this.publishedOn;

            return post;
        }
    }

}