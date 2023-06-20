package com.commerxo.commerxoblogservice.service.impl;

import com.commerxo.commerxoblogservice.domain.Tag;
import com.commerxo.commerxoblogservice.http.APIResponse;
import com.commerxo.commerxoblogservice.repository.TagRepository;
import com.commerxo.commerxoblogservice.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public APIResponse<Tag> save(Tag tag) {
        if(!StringUtils.hasText(tag.getTagName()))
            throw new IllegalArgumentException("Tag Name does not contain text!");

        Optional<Tag> existTag = tagRepository.findByTagName(tag.getTagName());
        if (existTag.isPresent())
            throw new IllegalArgumentException("Tag already exist with name " + tag.getTagName());

        return new APIResponse<>(HttpStatus.CREATED, tagRepository.save(tag), "Successfully Created!");
    }

    @Override
    public void remove(Tag tag) {

    }

    @Override
    public APIResponse<List<Tag>> getAllTags() {
        return null;
    }


}
