package com.commerxo.commerxoopenidserver.service;

import com.commerxo.commerxoopenidserver.api.group.UserGroupCreationRequest;
import com.commerxo.commerxoopenidserver.domain.UserGroup;

public interface GroupService {

    UserGroup register(UserGroupCreationRequest request);

}