DROP TABLE IF EXISTS brand;

CREATE TABLE brand (
                       id   BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                       name  VARCHAR(255) NOT NULL UNIQUE
);