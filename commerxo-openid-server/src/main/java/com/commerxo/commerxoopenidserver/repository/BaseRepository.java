package com.commerxo.commerxoopenidserver.repository;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface BaseRepository<T> {

    void saveOrUpdate(String sql, PreparedStatementSetter pss);

    List<T> findAll();

    void delete(T t);

    T find(String filter,RowMapper<T> rowMapper , Object... args);

}
