package com.commerxo.commerxoblogservice.api.post;

import com.commerxo.commerxoblogservice.domain.Post;

public class PostCreateRequest {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static Post mapToEntity(PostCreateRequest request){
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        return post;
    }
}
