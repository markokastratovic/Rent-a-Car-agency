/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.8-MariaDB : Database - bazarentacar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bazarentacar` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bazarentacar`;

/*Table structure for table `dodatnaopcija` */

DROP TABLE IF EXISTS `dodatnaopcija`;

CREATE TABLE `dodatnaopcija` (
  `OpcijaID` int(11) NOT NULL AUTO_INCREMENT,
  `NazivOpcije` varchar(50) NOT NULL,
  `Cena` double NOT NULL,
  PRIMARY KEY (`OpcijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `dodatnaopcija` */

insert  into `dodatnaopcija`(`OpcijaID`,`NazivOpcije`,`Cena`) values 
(1,'Full kasko',120),
(2,'Osiguranje od stete',40),
(3,'Osiguranje od kradje',40),
(4,'GPS navigacija',35),
(5,'Decije sediste',35),
(6,'Medjunarodno Osiguranje',40);

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `JMBG` varchar(50) NOT NULL,
  `Adresa` varchar(50) NOT NULL,
  `BrojTelefona` varchar(50) NOT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

insert  into `klijent`(`KlijentID`,`Ime`,`Prezime`,`JMBG`,`Adresa`,`BrojTelefona`) values 
(1,'Branko','Krsmanovic','12343445326','Brace Krsmanovic 23,Kraljevo','52433425342'),
(2,'Marko','Koprivica','745321532','Trgovacka 19,Beograd','6544643444'),
(3,'Nemanja','Kontic','6254652477','Potrosacka 3,Beograd','34354345'),
(4,'Aleksandra','Pesic','2546324754','Beogradska 222,Beograd','1345724935'),
(5,'Filip','Filipovic','59432538','Njegoseva 23,Beograd','65464523'),
(6,'Milos','Djeric','4373356845675','Kumodraz selo 3','5657357543'),
(7,'Boris','Tadinac','5324563452','Kumodraz selo','532153245'),
(8,'Isidora','Perovic','02546245545','Vojvodjanska 321, Subotica','6624653'),
(11,'Darko','Darkovic','625625462546','Viteska 55, Novi Sad','066453135'),
(12,'Petar','Petrovic','524369872654','Crnogorska 43, Beograd','655435325');

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `KorisnikID` int(11) NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` varchar(50) NOT NULL,
  `KorisnickaSifra` varchar(50) NOT NULL,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `JMBG` varchar(50) NOT NULL,
  `Adresa` varchar(50) NOT NULL,
  `BrojTelefona` varchar(50) NOT NULL,
  PRIMARY KEY (`KorisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `korisnik` */

insert  into `korisnik`(`KorisnikID`,`KorisnickoIme`,`KorisnickaSifra`,`Ime`,`Prezime`,`JMBG`,`Adresa`,`BrojTelefona`) values 
(1,'marko','marko','Marko','Kastratovic','432534457','Beogradska 323','0624321434');

/*Table structure for table `marka` */

DROP TABLE IF EXISTS `marka`;

CREATE TABLE `marka` (
  `MarkaID` int(11) NOT NULL AUTO_INCREMENT,
  `NazivMarke` varchar(50) NOT NULL,
  PRIMARY KEY (`MarkaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `marka` */

insert  into `marka`(`MarkaID`,`NazivMarke`) values 
(1,'Audi'),
(2,'BMW'),
(3,'Mercedes-benz'),
(4,'VW');

/*Table structure for table `model` */

DROP TABLE IF EXISTS `model`;

CREATE TABLE `model` (
  `ModelID` int(11) NOT NULL AUTO_INCREMENT,
  `NazivModela` varchar(50) NOT NULL,
  `BrojSedista` int(11) NOT NULL,
  `Marka` int(11) NOT NULL,
  PRIMARY KEY (`ModelID`),
  KEY `Marka` (`Marka`),
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`Marka`) REFERENCES `marka` (`MarkaID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `model` */

insert  into `model`(`ModelID`,`NazivModela`,`BrojSedista`,`Marka`) values 
(1,'A3',5,1),
(2,'A4',5,1),
(3,'A6',5,1),
(4,'3',5,2),
(5,'5',5,2),
(6,'A klasa',5,3),
(7,'C klasa',5,3),
(8,'E klasa',5,3),
(9,'Polo',5,4);

/*Table structure for table `opcijazaduzenje` */

DROP TABLE IF EXISTS `opcijazaduzenje`;

CREATE TABLE `opcijazaduzenje` (
  `OpcijaID` int(11) NOT NULL,
  `ZaduzenjeID` int(11) NOT NULL,
  PRIMARY KEY (`OpcijaID`,`ZaduzenjeID`),
  KEY `opcijazaduzenje_ibfk_1` (`ZaduzenjeID`),
  CONSTRAINT `opcijazaduzenje_ibfk_1` FOREIGN KEY (`ZaduzenjeID`) REFERENCES `zaduzenje` (`ZaduzenjeID`),
  CONSTRAINT `opcijazaduzenje_ibfk_2` FOREIGN KEY (`OpcijaID`) REFERENCES `dodatnaopcija` (`OpcijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `opcijazaduzenje` */

insert  into `opcijazaduzenje`(`OpcijaID`,`ZaduzenjeID`) values 
(2,2),
(2,3),
(2,5),
(2,13),
(2,14),
(2,24),
(3,1),
(3,2),
(3,5),
(3,13),
(3,23),
(3,24),
(4,2),
(4,3),
(4,4),
(4,5),
(4,6),
(4,10),
(4,13),
(4,23),
(5,2),
(5,5),
(5,9),
(5,23),
(6,2),
(6,5),
(6,6),
(6,9),
(6,13),
(6,24);

/*Table structure for table `vozilo` */

DROP TABLE IF EXISTS `vozilo`;

CREATE TABLE `vozilo` (
  `VoziloID` int(11) NOT NULL AUTO_INCREMENT,
  `RegistarskiBroj` varchar(50) NOT NULL,
  `BrojSasije` varchar(50) NOT NULL,
  `Godiste` int(11) NOT NULL,
  `ZapreminaMotora` int(11) NOT NULL,
  `Snaga` int(11) NOT NULL,
  `DnevnaCena` double NOT NULL,
  `ModelID` int(11) NOT NULL,
  PRIMARY KEY (`VoziloID`),
  KEY `ModelID` (`ModelID`),
  CONSTRAINT `vozilo_ibfk_1` FOREIGN KEY (`ModelID`) REFERENCES `model` (`ModelID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `vozilo` */

insert  into `vozilo`(`VoziloID`,`RegistarskiBroj`,`BrojSasije`,`Godiste`,`ZapreminaMotora`,`Snaga`,`DnevnaCena`,`ModelID`) values 
(1,'BG123AA','dfgj43tae',2015,1999,184,60,4),
(2,'BG432FD','sdf4t6uy',2019,1999,240,75,3),
(3,'BG444DD','dsf4766aak',2017,1600,124,45,1),
(4,'BG999KK','sfdsdf43',2012,1200,75,35,9),
(5,'BG321RE','hdfgh5ya77',2016,1600,105,60,1),
(6,'BG400TG','sdfgsdfg4t4wtrj',2008,1600,100,57,9),
(7,'BG876LL','dhbhtreuhjrw4j',2019,3000,380,150,8),
(9,'KS333TE','fhuhrw4hwr4eu',2012,6300,530,200,7),
(10,'NS555JJ','5345342465426',2020,1499,120,70,6),
(11,'BG999CG','fdgetyw54yeswghwser',2016,1999,240,100,2);

/*Table structure for table `zaduzenje` */

DROP TABLE IF EXISTS `zaduzenje`;

CREATE TABLE `zaduzenje` (
  `ZaduzenjeID` int(11) NOT NULL AUTO_INCREMENT,
  `DatumOd` date NOT NULL,
  `DatumDo` date DEFAULT NULL,
  `UkupnaCena` double DEFAULT NULL,
  `KlijentID` int(11) NOT NULL,
  `VoziloID` int(11) NOT NULL,
  `ZaduzioID` int(11) NOT NULL,
  `RazduzioID` int(11) DEFAULT NULL,
  KEY `ZaduzenjeID` (`ZaduzenjeID`),
  KEY `zaduzenje_ibfk_1` (`VoziloID`),
  KEY `zaduzenje_ibfk_2` (`ZaduzioID`),
  KEY `zaduzenje_ibfk_3` (`RazduzioID`),
  KEY `zaduzenje_ibfk_4` (`KlijentID`),
  CONSTRAINT `zaduzenje_ibfk_1` FOREIGN KEY (`VoziloID`) REFERENCES `vozilo` (`VoziloID`),
  CONSTRAINT `zaduzenje_ibfk_2` FOREIGN KEY (`ZaduzioID`) REFERENCES `korisnik` (`KorisnikID`),
  CONSTRAINT `zaduzenje_ibfk_3` FOREIGN KEY (`RazduzioID`) REFERENCES `korisnik` (`KorisnikID`),
  CONSTRAINT `zaduzenje_ibfk_4` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zaduzenje` */

insert  into `zaduzenje`(`ZaduzenjeID`,`DatumOd`,`DatumDo`,`UkupnaCena`,`KlijentID`,`VoziloID`,`ZaduzioID`,`RazduzioID`) values 
(1,'2019-12-10','2019-12-13',135,3,7,1,1),
(2,'2019-12-13','2019-12-13',265,1,2,1,1),
(3,'2019-12-13','2019-12-13',135,4,1,1,1),
(4,'2019-12-15','2019-12-15',95,7,1,1,1),
(5,'2020-01-19','2020-01-19',260,8,10,1,1),
(6,'2020-01-19','2020-01-19',225,6,2,1,1),
(9,'2020-01-19','2020-01-19',195,1,5,1,1),
(10,'2020-01-19','2020-01-19',92,3,6,1,1),
(13,'2020-01-20','2020-01-20',355,5,9,1,1),
(14,'2020-01-20','2020-01-20',115,5,2,1,1),
(15,'2020-01-20','2020-01-20',75,1,2,1,1),
(16,'2020-01-20','2020-01-20',45,1,3,1,1),
(17,'2020-01-20','2020-01-20',60,1,5,1,1),
(18,'2020-01-20','2020-01-20',180,1,5,1,1),
(19,'2020-01-20','2020-01-20',225,2,2,1,1),
(20,'2020-01-20','2020-01-20',90,1,3,1,1),
(22,'2020-01-20','2020-01-20',60,1,1,1,1),
(23,'2020-01-21','2020-01-21',230,1,1,1,1),
(24,'2020-01-22',NULL,NULL,7,5,1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
