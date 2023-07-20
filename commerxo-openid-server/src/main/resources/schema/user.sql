CREATE TABLE users (
    id varchar(100) NOT NULL,
	user_name varchar(20) NOT NULL,
	first_name varchar(30) DEFAULT NULL,
   	middle_name varchar(30) DEFAULT NULL,
	last_name varchar(30) DEFAULT NULL,
	password varchar(255) NOT NULL,
	email_id varchar(50) NOT NULL,
	is_enabled BIT(1) DEFAULT 0,
	is_account_expired BIT(1) DEFAULT 0 ,
	is_account_locked BIT(1) DEFAULT 0 ,
	is_credential_expired BIT(1) DEFAULT 0 ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT UC_User UNIQUE (user_name)
);