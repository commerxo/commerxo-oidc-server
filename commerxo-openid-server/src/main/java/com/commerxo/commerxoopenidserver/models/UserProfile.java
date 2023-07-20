package com.commerxo.commerxoopenidserver.models;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public class UserProfile {

    private String id;
    private String givenName;
    private String middleName;
    private String familyName;
    private String nickName;
    private String preferredUsername;
    private String profile; // profile page URL
    private String picture;
    private String gender;
    private String birthDate;
    private String zoneInfo;
    private String locale;
    private String address;
    private Date updatedAt;



}
