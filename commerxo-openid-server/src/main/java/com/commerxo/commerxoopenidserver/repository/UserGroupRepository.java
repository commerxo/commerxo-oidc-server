package com.commerxo.commerxoopenidserver.repository;

import com.commerxo.commerxoopenidserver.models.UserGroup;

public interface UserGroupRepository {

    void save(UserGroup group);

    UserGroup findByGroupName(String name);

    void delete(UserGroup group);

}
