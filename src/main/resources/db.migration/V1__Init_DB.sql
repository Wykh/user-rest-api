CREATE TABLE user_entity
(
    id         uuid NOT NULL DEFAULT gen_random_uuid(),
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255),
    phone      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);