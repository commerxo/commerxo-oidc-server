package com.commerxo.commerxoopenidserver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.util.StringUtils;

import java.util.Collection;

public abstract class AuthorizationRepositoryJunit {

    private EmbeddedDatabase db;
    private JdbcTemplate jdbcTemplate;

    public void init(){
        this.db = createDB();
        this.jdbcTemplate = new JdbcTemplate(this.db);
    }

    private EmbeddedDatabase createDB(){
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScripts(StringUtils.collectionToDelimitedString(getSchemas(), ", "))
                .build();
    }

    public JdbcTemplate getJdbcTemplate(){
        return this.jdbcTemplate;
    }

    public EmbeddedDatabase getDb(){
        return this.db;
    }

    abstract Collection<String> getSchemas();
}