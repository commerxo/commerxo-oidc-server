package com.commerxo.commerxoopenidserver.repository.impl;

import com.commerxo.commerxoopenidserver.models.User;
import com.commerxo.commerxoopenidserver.repository.UserRepository;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    private static final String EMPTY_STRING = "";

    private static final String TABLE_COLUM = "id, "
            + "created_at , "
            + "updated_at , "
            + "user_name  , "
            + "email_id   , "
            + "password   , "
            + "first_name , "
            + "middle_name, "
            + "last_name  , "
            + "is_enabled , "
            + "is_account_expired ,"
            + "is_account_locked  ,"
            + "is_credential_expired";


    private static final String INSERT_TABLE_COLUM = "id, "
            + "user_name  , "
            + "email_id   , "
            + "password   , "
            + "first_name , "
            + "middle_name, "
            + "last_name  , "
            + "is_enabled , "
            + "is_account_expired ,"
            + "is_account_locked  ,"
            + "is_credential_expired";


    private static final String TABLE_NAME = "users";

    private static final String PK_FILTER = "id = ?";

    private static final String UN_KEY_FILTER = "user_name = ?";

    private static final String LOAD_USER_SQL = "SELECT " + TABLE_COLUM + " FROM " + TABLE_NAME + " WHERE ";

    private static final String INSERT_USER_SQL = "INSERT INTO " + TABLE_NAME + "( " + INSERT_TABLE_COLUM + " )  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_SQL = "UPDATE " + TABLE_NAME + " SET email_id = ?, password = ?, first_name = ?,"
            + " middle_name = ?,  last_name = ?, is_enabled = ?, is_account_expired = ?, is_account_locked = ?, is_credential_expired = ? "
            + "WHERE " + PK_FILTER;

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper;
    private final Function<User, List<SqlParameterValue>> userParameters;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = new UserRowMapper();
        this.userParameters = new UserParameters();
    }

    @Override
    public User findByUsername(String username) {
        return find(LOAD_USER_SQL + UN_KEY_FILTER, this.userRowMapper, username);
    }

    @Override
    public void insert(User user) {
        List<SqlParameterValue> parameters = new ArrayList<>(this.userParameters.apply(user));
        parameters.remove(1); // remove created_at
        parameters.remove(1); // remove updated_at
        PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameters.toArray());
        this.saveOrUpdate(INSERT_USER_SQL, pss);
    }

    @Override
    public void update(User user) {
        List<SqlParameterValue> parameters = new ArrayList<>(this.userParameters.apply(user));
        SqlParameterValue id = parameters.remove(0); // removed id
        parameters.remove(0); // remove created_at
        parameters.remove(0); // remove updated_at
        parameters.remove(0); // remove user_name
        parameters.add(id);
        PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameters.toArray());
        this.saveOrUpdate(UPDATE_USER_SQL, pss);
    }

    @Override
    public User findById(String id) {
        return find(LOAD_USER_SQL + PK_FILTER, this.userRowMapper, id);
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    private static class UserRowMapper implements RowMapper<User>{
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

    private static class UserParameters implements Function<User, List<SqlParameterValue>>{

        @Override
        public List<SqlParameterValue> apply(User user) {
            return Arrays.asList(
                    new SqlParameterValue(Types.VARCHAR, user.getId()),
                    new SqlParameterValue(Types.TIMESTAMP, user.getCreatedAt()),
                    new SqlParameterValue(Types.TIMESTAMP, user.getUpdatedAt()),
                    new SqlParameterValue(Types.VARCHAR, user.getUsername()),
                    new SqlParameterValue(Types.VARCHAR, user.getEmailId()),
                    new SqlParameterValue(Types.VARCHAR, user.getPassword()),
                    new SqlParameterValue(Types.VARCHAR, user.getFirstName()),
                    new SqlParameterValue(Types.VARCHAR, user.getMiddleName()),
                    new SqlParameterValue(Types.VARCHAR, user.getLastName()),
                    new SqlParameterValue(Types.BOOLEAN, user.isEnabled()),
                    new SqlParameterValue(Types.BOOLEAN, user.isAccountExpired()),
                    new SqlParameterValue(Types.BOOLEAN, user.isAccountLocked()),
                    new SqlParameterValue(Types.BOOLEAN, user.isCredentialsExpired())
            );
        }

    }

}
