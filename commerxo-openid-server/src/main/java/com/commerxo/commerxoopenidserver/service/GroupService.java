package com.commerxo.commerxoopenidserver.service;

import com.commerxo.commerxoopenidserver.api.group.GroupRequest;
import com.commerxo.commerxoopenidserver.domain.Group;

public interface GroupService {

    Group register(GroupRequest request);

}
