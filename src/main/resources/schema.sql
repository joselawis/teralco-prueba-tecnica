DROP TABLE IF EXISTS PRECIO;
DROP TABLE IF EXISTS COCHE;
DROP TABLE IF EXISTS MARCA;

CREATE TABLE MARCA
(
    id   int auto_increment primary key,
    name varchar(250) not null
);



CREATE TABLE COCHE
(
    id       int auto_increment primary key,
    name     varchar(250) not null,
    color    varchar(250) not null,
    marca_id int          not null
);

ALTER TABLE COCHE
    ADD FOREIGN KEY (marca_id) REFERENCES MARCA (id);



CREATE TABLE PRECIO
(
    id        int auto_increment primary key,
    startDate timestamp not null,
    endDate   timestamp not null,
    price     double    not null,
    coche_id  int       not null
);

ALTER TABLE PRECIO
    ADD FOREIGN KEY (coche_id) REFERENCES COCHE (id);