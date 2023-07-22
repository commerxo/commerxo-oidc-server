package com.commerxo.commerxoopenidserver.repository;

import com.commerxo.commerxoopenidserver.models.User;
import com.commerxo.commerxoopenidserver.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UserRepositoryTest extends AuthorizationRepositoryJunit {

    private static final String SCHEMA = "/schema/user.sql";

    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void init() {
        super.init();
        this.userRepository = new UserRepositoryImpl(this.getJdbcTemplate());
    }

    @AfterEach
    public void destroy(){
        this.getDb().shutdown();
    }

    @Test
    public void findByUsernameWhenParameterUsernameIsNull() {
        Assertions.assertNull(this.userRepository.findByUsername(null));
    }

    @Test
    public void findByIdWhenIdIsNull(){
        Assertions.assertNull(this.userRepository.findById(null));
    }
    
    @Test
    public void saveNewUserAndFindByWrongUsername(){
        User newUser = createUser().build();
        this.userRepository.insert(newUser);
        User savedUser = this.userRepository.findByUsername("fake_username");
        Assertions.assertNull(savedUser);

    }
    @Test
    public void saveNewUserAndFindByUsername(){
        User newUser = createUser().build();
        this.userRepository.insert(newUser);
        User savedUser = this.userRepository.findByUsername(newUser.getUsername());
        Assertions.assertEquals(newUser.getId(), savedUser.getId());
        Assertions.assertEquals(newUser.getUsername(), savedUser.getUsername());
        Assertions.assertEquals(newUser.getPassword(), savedUser.getPassword());
        Assertions.assertEquals(newUser.getEmailId(), savedUser.getEmailId());
        Assertions.assertFalse(savedUser.isAccountExpired());
        Assertions.assertFalse(savedUser.isEnabled());
        Assertions.assertFalse(savedUser.isAccountLocked());
        Assertions.assertFalse(savedUser.isCredentialsExpired());
    }

    @Test
    public void saveNewUserAndFindByUsernameAndUpdate() {
        User newUser = createUser().build();
        this.userRepository.insert(newUser);
        User savedUser = this.userRepository.findByUsername(newUser.getUsername());
        User updatedUser = User.from(savedUser)
                .username("new_username")
                .createdAt(Instant.now())
                .password("test_password")
                .enabled(true)
                .accountExpired(true)
                .accountLocked(true)
                .credentialsExpired(true)
                .build();
        this.userRepository.update(updatedUser);
        savedUser = this.userRepository.findByUsername(newUser.getUsername());
        Assertions.assertEquals(newUser.getId(), savedUser.getId());
        Assertions.assertEquals(newUser.getUsername(), savedUser.getUsername());
        Assertions.assertEquals(updatedUser.getPassword(), savedUser.getPassword());
        Assertions.assertEquals(newUser.getEmailId(), savedUser.getEmailId());
        Assertions.assertTrue(savedUser.isAccountExpired());
        Assertions.assertTrue(savedUser.isEnabled());
        Assertions.assertTrue(savedUser.isAccountLocked());
        Assertions.assertTrue(savedUser.isCredentialsExpired());
    }

    private User.Builder createUser(){
        return User
                .withId(UUID.randomUUID().toString())
                .username("test")
                .password("password")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .emailId("email@test.com");
    }

    @Override
    Collection<String> getSchemas() {
        return Collections.singleton(SCHEMA);
    }

}