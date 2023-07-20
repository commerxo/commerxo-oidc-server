package com.commerxo.commerxoblogservice.api.post;

import com.commerxo.commerxoblogservice.domain.Post;

import java.util.List;

public interface PostService {

    Post findById(String id);

    Post findByTitle(String title);

    List<String> findAll();

    void saveOrUpdate(Post post);

    void delete(Post post);


}
