CREATE TABLE users
(
    id          INTEGER,
    first_name  VARCHAR(150) NOT NULL,
    last_name   VARCHAR(150) NOT NULL,
    role   VARCHAR(150) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password     VARCHAR NOT NULL UNIQUE,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE SEQUENCE users_id_sequence START 1 INCREMENT 1 OWNED BY users.id;