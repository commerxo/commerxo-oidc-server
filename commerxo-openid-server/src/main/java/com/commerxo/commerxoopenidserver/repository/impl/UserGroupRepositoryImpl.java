package com.commerxo.commerxoopenidserver.repository.impl;

import com.commerxo.commerxoopenidserver.models.UserGroup;
import com.commerxo.commerxoopenidserver.repository.UserGroupRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Repository
public class UserGroupRepositoryImpl extends BaseRepositoryImpl<UserGroup> implements UserGroupRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "user_group";

    private static final String TABLE_COLUMNS = "id, "
            + "group_name , "
            + "description, "
            + "is_enabled , "
            + "created_at , "
//            + "created_by , "
            + "updated_at  ";
//            + "updated_by   ";

    private static final String GROUP_INSERT_COLUMNS = "id, "
            + "group_name ,"
            + "description ,"
            + "is_enabled ";
//            + "created_by ,"
//            + "updated_by ";

    private static final String PK_FILTER = "id = ?";

    private static final String GROUP_INSERT_SQL = "INSERT INTO " + TABLE_NAME + "( " + GROUP_INSERT_COLUMNS + " ) VALUES( ?, ?, ?, ?, ?, ?)" ;

    private static final String LOAD_USER_GROUP_SQL = "SELECT " + TABLE_COLUMNS + " FROM " + TABLE_NAME + " WHERE ";

    private static final String UPDATE_USER_GROUP_SQL = "UPDATE " + TABLE_NAME
            + " SET  description = ?, is_enabled = ?, updated_by = ?"
            + " WHERE " + PK_FILTER;

    private final RowMapper<UserGroup> groupRowMapper;
    private final JdbcTemplate jdbcTemplate;
    private final Function<UserGroup, List<SqlParameterValue>> userGroupParameters;

//    @PostConstruct
//    public void post(){
//        UserGroup group = new UserGroup();
//        group.setGroupName("tetswsiaaodsdsajy");
//        group.setCreatedBy("sjain");
//        group.setDescription("desc");
//        group.setUpdatedBy("sjain21");
//        save(group);
//    }

    public UserGroupRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.groupRowMapper = new UserGroupRowMapper();
        this.userGroupParameters = new UserGroupParameter();
    }

    @Override
    public void save(UserGroup group) {
        UserGroup existGroup = findByGroupName(group.getGroupName());
        if (existGroup != null) {
            updateNewGroup(existGroup);
        }
        else {
            group.setId(UUID.randomUUID().toString());
            insertNewGroup(group);
        }
    }

    private void insertNewGroup(UserGroup group){
        this.saveOrUpdate(
            GROUP_INSERT_COLUMNS, (ps) ->{
                    ps.setString(1, group.getId());
                    ps.setString(2, group.getGroupName());
                    ps.setString(3, group.getDescription());
                    ps.setBoolean(4,false);
//                    ps.setString(5, group.getCreatedBy());
//                    ps.setString(6, group.getUpdatedBy());
            }
        );
    }

    private void updateNewGroup(UserGroup group){
        List<SqlParameterValue> parameters = new ArrayList<>(this.userGroupParameters.apply(group));
        SqlParameterValue id = parameters.remove(0);
        parameters.remove(0); // remove group_name
        parameters.add(id);
        PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameters.toArray());
        saveOrUpdate(UPDATE_USER_GROUP_SQL, pss);
    }

    @Override
    public UserGroup findByGroupName(String name) {
        return find(LOAD_USER_GROUP_SQL + " group_name = ?", this.groupRowMapper, name);
    }

    @Override
    public void delete(UserGroup group) {
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public static class UserGroupRowMapper implements RowMapper<UserGroup>{

        @Override
        public UserGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            UserGroup groupResult = new UserGroup();
            groupResult.setId(rs.getString("id"));
            groupResult.setCreatedAt(createdAt.toInstant());
            groupResult.setUpdatedAt(updatedAt.toInstant());
            groupResult.setGroupName(rs.getString("group_name"));
            groupResult.setDescription(rs.getString("description"));
            groupResult.setEnabled(rs.getBoolean("is_enabled"));
            return groupResult;
        }

    }

    public static class UserGroupParameter implements Function<UserGroup, List<SqlParameterValue>>{

        @Override
        public List<SqlParameterValue> apply(UserGroup group) {

            return Arrays.asList(
                    new SqlParameterValue(Types.VARCHAR, group.getId()),
                    new SqlParameterValue(Types.VARCHAR, group.getGroupName()),
                    new SqlParameterValue(Types.VARCHAR, group.getDescription()),
                    new SqlParameterValue(Types.BOOLEAN, group.isEnabled()),
                    new SqlParameterValue(Types.VARCHAR, group.getCreatedBy()),
                    new SqlParameterValue(Types.VARCHAR, group.getUpdatedBy())
            );
        }

    }


}