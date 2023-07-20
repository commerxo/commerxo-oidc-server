package com.commerxo.commerxoopenidserver.repository.impl;

import com.commerxo.commerxoopenidserver.models.User;
import com.commerxo.commerxoopenidserver.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    private static final String EMPTY_STRING = "";

    private static final String TABLE_COLUM = "id, "
            + "user_name  , "
            + "email_id   , "
            + "password   , "
            + "created_at , "
            + "updated_at , "
            + "first_name , "
            + "middle_name, "
            + "last_name  , "
            + "is_enabled , "
            + "is_account_expired ,"
            + "is_account_locked  ,"
            + "is_credential_expired";

    private static final String TABLE_NAME = "users";

    private static final String LOAD_USER_SQL = "SELECT " + TABLE_COLUM + " FROM " + TABLE_NAME + " WHERE ";

    private static final String REGISTER_NEW_USER_SQL = "INSERT INTO " + TABLE_NAME + "(id, user_name, password, email_id)  VALUES(?, ?, ?, ?)";

    private static final String PK_FILTER = "id = ?";

    private static final String UN_KEY_FILTER = "user_name = ?";

    private final RowMapper<User> userRowMapper;
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = new UserRowMapper();
    }

    @Override
    public User findByUsername(String username) {
        return find(LOAD_USER_SQL + UN_KEY_FILTER, this.userRowMapper, username);
    }

    @Override
    public void save(User user) {
        this.saveOrUpdate(REGISTER_NEW_USER_SQL, ps -> {
            ps.setString(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmailId());
        });
    }

    @Override
    public User findById(String id) {
        return find(LOAD_USER_SQL + PK_FILTER, this.userRowMapper, id);
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public static class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            String id = rs.getString("id");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            String username = rs.getString("user_name");
            String password = rs.getString("password");
            String emailId = rs.getString("email_id");
            String firstName = rs.getString("first_name");
            String middleName = rs.getString("middle_name");
            String lastName = rs.getString("last_name");
            boolean enabled = rs.getBoolean("is_enabled");
            boolean accountLocked = rs.getBoolean("is_account_locked");
            boolean accountExpired = rs.getBoolean("is_account_expired");
            boolean credentialExpired = rs.getBoolean("is_credential_expired");

            return User.withId(id)
                    .createdAt(createdAt.toInstant())
                    .updatedAt(updatedAt.toInstant())
                    .username(username)
                    .password(password)
                    .emailId(emailId)
                    .firstName(StringUtils.hasText(firstName) ? firstName : EMPTY_STRING)
                    .middleName(StringUtils.hasText(middleName) ? lastName : EMPTY_STRING)
                    .lastName(StringUtils.hasText(lastName) ? lastName : EMPTY_STRING)
                    .enabled(enabled)
                    .accountLocked(accountLocked)
                    .accountExpired(accountExpired)
                    .credentialsExpired(credentialExpired)
                    .build();
        }

    }

}
