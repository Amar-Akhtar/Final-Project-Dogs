CREATE DATABASE IF NOT EXISTS dogdb;

DROP TABLE IF EXISTS dog CASCADE;

CREATE TABLE 'dogs'(
'id' BIGINT PRIMARY KEY AUTO_INCREMENT,
'name' VARCHAR(255) NOT NULL,
'age' SMALLINT(2) NOT NULL
);