package com.commerxo.commerxoblogservice.service;

import com.commerxo.commerxoblogservice.domain.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MessageServiceImpl implements MessageService{

    public static final String INSERT = "insert into message (id, message) values(?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public MessageServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Message save(Message message) {
        jdbcTemplate.update(INSERT, ps -> {
            ps.setString(1, message.getId());
            ps.setString(2, message.getMessage()
            );
        });
        return message;
    }
}
