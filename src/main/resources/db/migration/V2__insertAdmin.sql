ALTER TABLE admin
ADD column user_id INT;

ALTER table admin
ADD constraint fk_admin
foreign key (user_id) references admin (id);

ALTER TABLE client
    ADD column user_entity_id INT;

ALTER table client
    ADD constraint fk_global_user
        foreign key (user_entity_id) references global_user (id);

INSERT INTO global_user (username, email, password, user_role)
VALUES ('admin', 'admin@mail.com', '1234', 'ROLE_ADMIN');

INSERT INTO admin (id, admin_name, address)
VALUES (1, 'admin', 'Cluj-Napoca');