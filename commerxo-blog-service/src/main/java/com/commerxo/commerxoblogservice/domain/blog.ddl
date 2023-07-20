CREATE TABLE post (
    id varchar(100) NOT NULL,
	title varchar(100) NOT NULL,
    created_by  varchar(100) NOT NULL,
    updated_by varchar(100) NOT NULL,
    content LONGTEXT NOT NULL,
    published_on TIMESTAMP,
    is_published BIT(1) NOT NULL DEFAULT 0,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT UC_Post UNIQUE (title)
);