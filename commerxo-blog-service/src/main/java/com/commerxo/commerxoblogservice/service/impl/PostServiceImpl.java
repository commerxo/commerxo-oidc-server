package com.commerxo.commerxoblogservice.service.impl;

import com.commerxo.commerxoblogservice.api.post.PostCreateRequest;
import com.commerxo.commerxoblogservice.domain.Post;
import com.commerxo.commerxoblogservice.exception.ResourceAlreadyExistException;
import com.commerxo.commerxoblogservice.exception.ResourceNotFoundException;
import com.commerxo.commerxoblogservice.repository.PostRepository;
import com.commerxo.commerxoblogservice.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post create(PostCreateRequest request) {
        Post post = PostCreateRequest.mapToEntity(request);

        if(!StringUtils.hasText(post.getTitle()))
            throw new IllegalArgumentException("Title must contain text!");

        if(!StringUtils.hasText(post.getContent()))
            throw new IllegalArgumentException("Content must contain text!");

        Optional<Post> existExist = this.postRepository.findByTitle(post.getTitle());
        if(existExist.isPresent())
            throw new ResourceAlreadyExistException("Post already exist with title " +  post.getTitle());

        return this.postRepository.save(post);
    }

    @Override
    public Post remove(String title) {
        Post existPost = getByTitle(title);
        this.postRepository.delete(existPost);
        return existPost;
    }

    @Override
    public Post getById(String id) {
        if(!StringUtils.hasText(id))
            throw new IllegalArgumentException("Id must contain text!");
        return this.postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post does not with ID " + id));
    }

    @Override
    public Post getByTitle(String title) {
        if(!StringUtils.hasText(title))
            throw new IllegalArgumentException("Title must contain text!");
        return this.postRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Post does not exist with title " + title));
    }

    @Override
    public List<Post> getAll() {
        return this.postRepository.findAll();
    }
}
