DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT,
    username                VARCHAR(255) UNIQUE NOT NULL,
    password                VARCHAR(255)        NOT NULL,
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
    balance INTEGER NOT NULL DEFAULT 0,
    card_id INTEGER NOT NULL REFERENCES cards
);

