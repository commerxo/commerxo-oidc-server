package com.commerxo.commerxoopenidserver.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.Set;

public class GroupTest {

    public static final Instant CREATED_AT = Instant.now();
    public static final Set<GrantedAuthority> GRANTED_AUTHORITIES = Set.of(new SimpleGrantedAuthority("READ"), new SimpleGrantedAuthority("WRITE"));
    @Test
    public void testWithAllParameters(){
        Group group = new Group();
        group.setGroupName("test_user");
        group.setUuid("113");
        group.setCreatedAt(CREATED_AT);
        group.setUserId("11001");
        group.setGroupAuthorities(GRANTED_AUTHORITIES);
        group.setDescription("group user's roles and authority");

        Assertions.assertEquals("113", group.getUuid());
        Assertions.assertEquals("11001", group.getUserId());
        Assertions.assertEquals("test_user", group.getGroupName());
        Assertions.assertEquals("group user's roles and authority", group.getDescription());
        Assertions.assertEquals(CREATED_AT, group.getCreatedAt());
        Assertions.assertEquals(GRANTED_AUTHORITIES, group.getGroupAuthorities());

    }
}
