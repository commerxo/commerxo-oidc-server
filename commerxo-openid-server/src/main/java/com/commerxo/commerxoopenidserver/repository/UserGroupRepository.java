package com.commerxo.commerxoopenidserver.repository;

import com.commerxo.commerxoopenidserver.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, String> {

    Optional<UserGroup> findByGroupName(String groupName);
}
