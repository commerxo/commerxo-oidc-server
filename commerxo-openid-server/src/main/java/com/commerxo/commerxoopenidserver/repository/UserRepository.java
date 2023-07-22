package com.commerxo.commerxoopenidserver.repository;

import com.commerxo.commerxoopenidserver.models.User;

public interface UserRepository {

    User findByUsername(String username);

    void insert(User user);

    void update(User user);

    User findById(String id);

}
