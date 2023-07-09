package com.commerxo.commerxoblogservice.service;

import com.commerxo.commerxoblogservice.api.post.PostCreateRequest;
import com.commerxo.commerxoblogservice.domain.Post;

import java.util.List;

public interface PostService {
    Post create(PostCreateRequest request);

    Post remove(String title);

    Post getById(String id);

    Post getByTitle(String title);

    List<Post> getAll();
}