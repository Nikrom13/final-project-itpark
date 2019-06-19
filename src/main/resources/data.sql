INSERT INTO users (id, username, password)
VALUES (1, 'Admin', 'password'),
       (2, 'Roman', 'password')
;

INSERT INTO authorities (id, user_id, authority)
VALUES (1, 1, 'ROLE_ADMIN')
;

INSERT INTO cards (id, cardname, rate)
VALUES (1, 'Master Card', 13),
       (2, 'Visa', 2),
       (3, 'Mir', 115)
;

INSERT INTO balance ()