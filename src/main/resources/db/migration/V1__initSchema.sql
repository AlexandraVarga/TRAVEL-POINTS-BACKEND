CREATE TABLE global_user
(
    id        SERIAL PRIMARY key,
    username  TEXT,
    email     TEXT,
    password  TEXT,
    user_role TEXT
);


CREATE TABLE admin
(
    id         int references global_user(id) unique,
    admin_name TEXT,
    address    TEXT
);

CREATE TABLE client
(
    id           int references global_user(id) unique,
    first_name   TEXT,
    last_name    TEXT,
    address      TEXT,
    age_category TEXT
);

CREATE TABLE wishlist
(
    id        SERIAL PRIMARY key,
    client_id INT,
    constraint fk_client
        foreign key (client_id) references client (id)
);

CREATE TABLE tourist_attraction
(
    id               SERIAL PRIMARY key,
    name             TEXT,
    location         TEXT,
    text_description TEXT,
    nr_visits        INT,
    entry_price      float,
    discount         float,
    visiting_date    TEXT
);

CREATE TABLE tourist_attraction_wishlist
(
    id                    SERIAL PRIMARY key,
    wishlist_id           INT,
    tourist_attraction_id INT,
    constraint fk_wishlist
        foreign key (wishlist_id) references wishlist (id),
    constraint fk_tourist_attraction
        foreign key (tourist_attraction_id) references tourist_attraction (id)
);

CREATE TABLE review
(
    id                    SERIAL PRIMARY key,
    input_review          TEXT,
    client_id             INT,
    tourist_attraction_id INT,
    constraint fk_tourist_attraction
        foreign key (tourist_attraction_id) references tourist_attraction (id),
    constraint fk_client
        foreign key (client_id) references client (id)
);