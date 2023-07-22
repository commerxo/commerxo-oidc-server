package com.commerxo.commerxoopenidserver.repository;

import com.commerxo.commerxoopenidserver.models.UserGroup;
import com.commerxo.commerxoopenidserver.repository.impl.UserGroupRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;


public class UserGroupRepositoryTest extends AuthorizationRepositoryJunit {

    private static final String USER_GROUP_SCHEMA = "schema/UserGroup.sql";

    private UserGroupRepositoryImpl userGroupRepository;

    @BeforeEach
    public void init(){
        super.init();
        this.userGroupRepository = new UserGroupRepositoryImpl(this.getJdbcTemplate());
    }

    @AfterEach
    public void destroy(){
        this.getDb().shutdown();
    }

    @Test
    public void saveGroupAndFindByGroupName(){
        UserGroup newGroup = createGroup();
        this.userGroupRepository.save(newGroup);
        UserGroup savedGroup = this.userGroupRepository.findByGroupName(newGroup.getGroupName());
        Assertions.assertNotNull(savedGroup.getId());
        Assertions.assertNotNull(savedGroup.getCreatedAt());
        Assertions.assertNotNull(savedGroup.getUpdatedAt());
        Assertions.assertEquals(newGroup.getGroupName(), savedGroup.getGroupName());
        Assertions.assertEquals(newGroup.getDescription(), savedGroup.getDescription());
        Assertions.assertEquals(newGroup.isEnabled(), savedGroup.isEnabled());
        Assertions.assertEquals(newGroup.getCreatedBy(), savedGroup.getCreatedBy());
        Assertions.assertEquals(newGroup.getUpdatedBy(), savedGroup.getUpdatedBy());
    }

    private UserGroup createGroup(){
        UserGroup group = new UserGroup();
        group.setCreatedBy("test");
        group.setUpdatedBy("test");
        group.setGroupName("test_group");
        group.setEnabled(true);
        group.setDescription("Sample Description");
        return group;
    }

    @Override
    Collection<String> getSchemas() {
        return Collections.singleton(USER_GROUP_SCHEMA);
    }
}