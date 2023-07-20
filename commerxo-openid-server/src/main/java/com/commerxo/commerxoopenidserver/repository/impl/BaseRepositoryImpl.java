package com.commerxo.commerxoopenidserver.repository.impl;

import com.commerxo.commerxoopenidserver.repository.BaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {

    @Override
    public void saveOrUpdate(String sql, PreparedStatementSetter pss) {
        this.getJdbcTemplate().update(sql, pss);
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void delete(T t) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public T find(String sql, RowMapper<T> rowMapper, Object... args) {
        List<T> result = this.getJdbcTemplate().query(sql, rowMapper, args);
        return  !result.isEmpty() ? result.get(0) : null;
    }

    public abstract JdbcTemplate getJdbcTemplate();
}
