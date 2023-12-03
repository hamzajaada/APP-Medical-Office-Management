DROP DATABASE IF EXISTS GestionCabinetMedicale;
CREATE DATABASE GestionCabinetMedicale;
USE GestionCabinetMedicale;

CREATE TABLE Clients(
    id BIGINT(15) PRIMARY KEY AUTO_INCREMENT,
    version INT(15),
    Titre VARCHAR(5),
    Nom VARCHAR(15),
    Prenom VARCHAR(20)
);


CREATE TABLE Medecines(
    id BIGINT(15) PRIMARY KEY AUTO_INCREMENT,
    version INT(15),
    titre VARCHAR(20),
    prenom VARCHAR(20),
    nom VARCHAR(20)
);


CREATE TABLE Creneaux(
    id BIGINT(15) PRIMARY KEY AUTO_INCREMENT,
    version INT(15),
    HDEBUT INT(15),
    MDEBUT INT(15),
    HFIN INT(15),
    MFIN INT(15),
    id_Medecine BIGINT(20),
    FOREIGN KEY (id_Medecine) REFERENCES Medecines(id)
);


CREATE TABLE RendezVous(
    id BIGINT(15) PRIMARY KEY AUTO_INCREMENT,
    jour DATE,
    id_client BIGINT(20),
    id_creneau BIGINT(20),
    FOREIGN KEY (id_client) REFERENCES Clients(id),
    FOREIGN KEY (id_creneau) REFERENCES Creneaux(id)
);




