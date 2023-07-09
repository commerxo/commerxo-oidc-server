package com.commerxo.commerxoblogservice.service.impl;

import com.commerxo.commerxoblogservice.common.ConstantExtension;
import com.commerxo.commerxoblogservice.domain.Tag;
import com.commerxo.commerxoblogservice.exception.ResourceAlreadyExistException;
import com.commerxo.commerxoblogservice.exception.ResourceNotFoundException;
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
        if(!StringUtils.hasText(tag.getTagName())) {
            throw new IllegalArgumentException("Tag Name does not contain text!");
        }

        Optional<Tag> existTag = tagRepository.findByTagName(tag.getTagName());
        if (existTag.isPresent()) {
            throw new ResourceAlreadyExistException("Tag already exist with name [ " + tag.getTagName() + " ]");
        }

        return new APIResponse<>(HttpStatus.CREATED, tagRepository.save(tag), ConstantExtension.SUCCESS_CREATED);
    }

    @Override
    public APIResponse<Tag> getByTagName(String tagName) {
        Tag tag = getIfExist(tagName);
        return new APIResponse<>(HttpStatus.OK, tag, ConstantExtension.SUCCESS_FETCHED);
    }

    @Override
    public APIResponse<Tag> remove(String tagName) {
        Tag existTag = getIfExist(tagName);

        tagRepository.delete(existTag);

        return new APIResponse<>(HttpStatus.OK, existTag, ConstantExtension.SUCCESS_DELETED);
    }

    @Override
    public APIResponse<List<Tag>> getAllTags() {
        return new APIResponse<>(HttpStatus.OK, tagRepository.findAll(), ConstantExtension.SUCCESS_FETCHED);
    }


    private Tag getIfExist(String tagName){
        if(!StringUtils.hasText(tagName)) {
            throw new IllegalArgumentException("Tag Name does not contain text!");
        }

        return tagRepository.findByTagName(tagName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to delete due to unavailability of tag [ " + tagName + " ]")
                );
    }

}
