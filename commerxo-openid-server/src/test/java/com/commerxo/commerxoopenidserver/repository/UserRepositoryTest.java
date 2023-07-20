package com.commerxo.commerxoopenidserver.repository;

import com.commerxo.commerxoopenidserver.models.User;
import com.commerxo.commerxoopenidserver.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.io.IOException;
import java.util.UUID;

public class UserRepositoryTest {

    private static final String USER_SCHEMA = "/schema/user.sql";


    private EmbeddedDatabase db;
    private JdbcTemplate jdbcTemplate;
    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void setUp() throws IOException {
        this.db = createDB(USER_SCHEMA);
        this.jdbcTemplate = new JdbcTemplate(this.db);
        this.userRepository = new UserRepositoryImpl(this.jdbcTemplate);
    }

    @AfterEach
    public void destroy(){
        this.db.shutdown();
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
    public void saveNewUserAndFindByUsername(){
        User newUser = createUser().build();
        this.userRepository.save(newUser);
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

    private User.Builder createUser(){
        return User
                .withId(UUID.randomUUID().toString())
                .username("test")
                .password("password")
                .emailId("email@test.com");
    }

    @Test
    public void test(){
        Assertions.assertTrue(true);
    }


    private EmbeddedDatabase createDB(String schema){
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript(schema)
                .build();
    }
}
