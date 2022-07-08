-- DROP DATABASE helpme_iud;

CREATE DATABASE IF NOT EXISTS helpme_iud;

USE helpme_iud;

CREATE TABLE IF NOT EXISTS roles(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion TEXT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NO EXISTS usuarios(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(120) NOT NULL,
    nombre VARCHAR(120) NOT NULL,
    apellido VARCHAR(120) NULL,
    password VARCHAR(250) NULL,
    fecha_nacimiento DATE NULL,
    enabled TINYINT NULL DEFAULT 1,
    red_social TINYINT NULL DEFAULT 0,
    image TEXT NULL DEFAULT 'https://happytravel.viajes/wp-content/uploads/2020/04/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png',
    PRIMARY KEY(id),
    UNIQUE(username)
);



