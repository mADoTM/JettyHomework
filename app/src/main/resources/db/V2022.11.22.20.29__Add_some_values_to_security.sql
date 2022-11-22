INSERT INTO security.users (name, password)
VALUES ('maxim', 'super1'),
       ('evgeny', 'super2');

INSERT INTO security.roles (role)
VALUES ('user'), ('admin');

INSERT INTO security.user_roles (user_id, role_id)
VALUES (1, 1), (2, 1), (2, 2);