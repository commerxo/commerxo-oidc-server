package com.commerxo.commerxoopenidserver.service;

import com.commerxo.commerxoopenidserver.api.group.UserGroupCreationRequest;
import com.commerxo.commerxoopenidserver.api.group.UserGroupUpdateRequest;
import com.commerxo.commerxoopenidserver.common.APIResponse;
import com.commerxo.commerxoopenidserver.domain.UserGroup;

import java.util.List;

public interface GroupService {

    UserGroup register(UserGroupCreationRequest request);

    void delete(String groupName);
    List<UserGroup> get();
    UserGroup update(UserGroupUpdateRequest request, String groupName);

    UserGroup active(String groupName);

    UserGroup inActive(String groupName);
}