package com.commerxo.commerxoblogservice.service;

import com.commerxo.commerxoblogservice.domain.Tag;
import com.commerxo.commerxoblogservice.http.APIResponse;

import java.util.List;

public interface TagService {

    APIResponse<Tag> save(Tag tag);

    void remove(Tag tag);

    APIResponse<List<Tag>> getAllTags();

}
