package com.commerxo.commerxoblogservice.api.post;

import com.commerxo.commerxoblogservice.domain.Post;
import com.commerxo.commerxoblogservice.exception.ResourceAlreadyExistException;
import com.commerxo.commerxoblogservice.security.JwtPrincipal;
import org.springframework.jdbc.core.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.io.StringReader;
import java.sql.*;
import java.util.*;
import java.util.function.Function;

@Service
public class PostServiceImpl implements PostService {

    private final static Collection<? extends GrantedAuthority> ALLOWED_GRANTED_AUTHORITY = AuthorityUtils.createAuthorityList("ADMIN");

    private static final String COLUMN_NAMES = "id, "
            + "title, "
            + "content, "
            + "created_by, "
            + "updated_by, "
            + "published_on, "
            + "is_published, "
            + "created_at, "
            + "updated_at ";

    private static final String INSERT_COLUMN_NAMES = "id, "
            + "title, "
            + "content, "
            + "created_by, "
            + "updated_by, "
            + "is_published ";

    private static final String TABLE_NAME = "post";

    private static final String PK_FILTER = "id = ?";

    private static final String LOAD_POST_SQL = "SELECT " + COLUMN_NAMES + " FROM " + TABLE_NAME + " WHERE ";

    private static final String INSERT_POST_SQL = "INSERT INTO " + TABLE_NAME
            + " (" + INSERT_COLUMN_NAMES + ") VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_POST_SQL = "UPDATE " + TABLE_NAME
            + " SET content = ?, published_on = ?, is_published = ? "
            + " WHERE " + PK_FILTER;

    private static final String POST_COUNT_SQL = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE ";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Post> postRowMapper;
    private final Function<Post, List<SqlParameterValue>> postParameterMapper;

    public PostServiceImpl(JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null)
            throw new IllegalArgumentException("jdbcTemplate can't be null!");
        this.jdbcTemplate = jdbcTemplate;
        this.postRowMapper = new PostRowMapper();
        this.postParameterMapper = new PostParameterMapper();
    }

    @Override
    public Post findById(String id) {
        return find(PK_FILTER, id);
    }

    @Override
    public Post findByTitle(String title) {
        return find("title = ?", title);
    }

    @Override
    public List<String> findAll() {
        return null;
    }

    @Override
    public void saveOrUpdate(Post post) {
        if(post == null)
            throw new IllegalArgumentException("post can't be null!");
        Post existingPost = findById(post.getId());
        if(existingPost != null)
            updatePost(existingPost);
        else
            insertNewPost(post);
    }

    private void insertNewPost(Post post){
        Integer count = this.jdbcTemplate.queryForObject(POST_COUNT_SQL + "title = ?",
                Integer.class,
                post.getTitle());
        if(count != null && count > 0)
            throw new ResourceAlreadyExistException("Post is already exist with title " + post.getTitle());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.jdbcTemplate.update(INSERT_POST_SQL, (ps) ->{
                ps.setString(1,UUID.randomUUID().toString());
                ps.setString(2, post.getTitle());
                Reader reader = new StringReader(post.getContent());
                ps.setClob(3, reader);
                ps.setString(4, authentication.getName());
                ps.setString(5, authentication.getName());
                ps.setBoolean(6, Boolean.FALSE);
            }
        );
    }

    private void updatePost(Post post){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // check if Published do not allow to Post Owners
        //
        // Owner can update
        // Authorized User can update like Admin, Blog Manager with Write Permission
    }

    @Override
    public void delete(Post post) {
    }

    private Post find(String filter, Object... args){
        List<Post> result = this.jdbcTemplate
                .query(LOAD_POST_SQL + filter, this.postRowMapper, args);
        return !result.isEmpty() ?  result.get(0) : null;
    }

    public static class PostRowMapper implements RowMapper<Post>{

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            String id = rs.getString("id");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String createdBy = rs.getString("created_by");
            String updatedBy = rs.getString("updated_by");
            boolean published = rs.getBoolean("is_published");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            Timestamp publishedOn = rs.getTimestamp("published_on");

            return Post.withId(id)
                    .title(title)
                    .content(content)
                    .isPublished(published)
                    .createdBy(createdBy)
                    .updatedBy(updatedBy)
                    .createdAt(createdAt != null ? createdAt.toInstant() : null)
                    .updatedAt(updatedAt != null ? updatedAt.toInstant() : null)
                    .publishedOn(publishedOn != null ? publishedOn.toInstant() : null)
                    .build();
        }

    }

    public static class PostParameterMapper implements Function<Post, List<SqlParameterValue>>{

        @Override
        public List<SqlParameterValue> apply(Post post) {

            return Arrays.asList(
                    new SqlParameterValue(Types.VARCHAR, post.getId()),
                    new SqlParameterValue(Types.VARCHAR, post.getTitle()),
                    new SqlParameterValue(Types.CLOB, post.getContent()),
                    new SqlParameterValue(Types.BOOLEAN, post.isPublished())
            );
        }

    }
}