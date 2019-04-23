CREATE DATABASE IF NOT EXISTS AvaliacaoUol;

USE AvaliacaoUol;

CREATE TABLE Cliente (
	Id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(200) NOT NULL,
    Idade INT NOT NULL,
    Ip VARCHAR(12) NOT NULL,
    Cadastramento DATETIME NOT NULL,
    Temperatura_Minima_Cadastro INT NOT NULL,
    Temperatura_Maxima_Cadastro INT NOT NULL
);
