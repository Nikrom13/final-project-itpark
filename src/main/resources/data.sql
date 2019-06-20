INSERT INTO users (id, username, password)
VALUES (1, 'Admin', 'password'),
       (2, 'Roman', 'password')
;

INSERT INTO authorities (id, user_id, authority)
VALUES (1, 1, 'ROLE_ADMIN')
;

INSERT INTO cards (id, cardname, rate, description)
VALUES (1, 'Master Card', 13, ' super master card'),
       (2, 'Visa', 2, 'super visa'),
       (3, 'Mir', 115, 'super mir')
;

INSERT INTO balance (id, user_id, balance,card_id)
VALUES (1, 1, 500,1),
       (2, 2, 100,3)
;