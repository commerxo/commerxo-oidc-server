package com.commerxo.commerxoopenidserver.service;

import com.commerxo.commerxoopenidserver.api.group.UserGroupCreateRequest;

import com.commerxo.commerxoopenidserver.models.UserGroup;

import java.util.List;

public interface GroupService {

    String register(UserGroupCreateRequest request);

    void delete(String groupName);
    List<UserGroup> get();

    UserGroup active(String groupName);

    UserGroup inActive(String groupName);
}