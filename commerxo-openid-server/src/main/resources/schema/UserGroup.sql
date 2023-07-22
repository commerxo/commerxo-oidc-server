CREATE TABLE user_group (
    id varchar(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    group_name varchar(50) NOT NULL,
    created_by varchar(20) DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL,
    description varchar(50) DEFAULT NULL,
    is_enabled BIT(1) DEFAULT 0,
    PRIMARY KEY(id),
    CONSTRAINT UC_User_Group UNIQUE(group_name)
);