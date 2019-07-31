DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT,
    username                VARCHAR(255) UNIQUE NOT NULL,
    password                VARCHAR(255)        NOT NULL,
    first_name              VARCHAR(255),
    second_name             VARCHAR(255),
    country                 VARCHAR(255),
    city                    VARCHAR(255),
    address                 VARCHAR(255),
    phone_number            VARCHAR(255),
    second_phone_number     VARCHAR(255),
    enabled                 BOOLEAN             NOT NULL DEFAULT TRUE,
    account_non_expired     BOOLEAN             NOT NULL DEFAULT TRUE,
    account_non_locked      BOOLEAN             NOT NULL DEFAULT TRUE,
    credentials_non_expired BOOLEAN             NOT NULL DEFAULT TRUE
);

DROP TABLE IF EXISTS authorities;
CREATE TABLE IF NOT EXISTS authorities
(
    id        INTEGER primary key AUTO_INCREMENT,
    user_id   INTEGER      NOT NULL REFERENCES users,
    authority VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS cards;
CREATE TABLE IF NOT EXISTS cards
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    cardname    VARCHAR(255) NOT NULL,
    rate        INTEGER      NOT NULL CHECK (rate >= 0),
    description TEXT         NOT NULL
);

DROP TABLE IF EXISTS userCards;
CREATE TABLE IF NOT EXISTS userCards
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER NOT NULL REFERENCES users,
    card_id INTEGER NOT NULL REFERENCES cards,
    balance INTEGER NOT NULL DEFAULT 0
);

