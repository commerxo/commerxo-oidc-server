package com.commerxo.commerxoopenidserver.repository;

import com.commerxo.commerxoopenidserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}
