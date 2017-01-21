CREATE DATABASE  IF NOT EXISTS `footballleaguemanager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `footballleaguemanager`;
-- MySQL dump 10.13  Distrib 5.7.13, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: footballleaguemanager
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Campionato`
--

DROP TABLE IF EXISTS `Campionato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Campionato` (
  `ID_Campionato` int(5) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `NumSquadre` int(5) NOT NULL,
  `Quota` float NOT NULL,
  PRIMARY KEY (`ID_Campionato`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Giocatore`
--

DROP TABLE IF EXISTS `Giocatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Giocatore` (
  `ID_Giocatore` int(5) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Cognome` varchar(25) NOT NULL,
  PRIMARY KEY (`ID_Giocatore`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Informazioni`
--

DROP TABLE IF EXISTS `Informazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Informazioni` (
  `ID_Partita` int(5) NOT NULL,
  `ID_Giocatore` int(5) NOT NULL,
  `Goal` smallint(6) NOT NULL,
  `Assist` int(5) NOT NULL,
  `Cartellino` int(5) NOT NULL,
  `Motivazione` varchar(30) NOT NULL,
  `Squalifica` int(5) NOT NULL,
  KEY `Informazioni_ibfk_1` (`ID_Partita`),
  KEY `Informazioni_ibfk_2` (`ID_Giocatore`),
  CONSTRAINT `Informazioni_ibfk_1` FOREIGN KEY (`ID_Partita`) REFERENCES `Partita` (`ID_Partita`),
  CONSTRAINT `Informazioni_ibfk_2` FOREIGN KEY (`ID_Giocatore`) REFERENCES `Giocatore` (`ID_Giocatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Partecipazione`
--

DROP TABLE IF EXISTS `Partecipazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Partecipazione` (
  `ID_Squadra` int(5) NOT NULL,
  `ID_Giocatore` int(5) NOT NULL,
  KEY `Partecipazione_ibfk_1` (`ID_Squadra`),
  KEY `Partecipazione_ibfk_2` (`ID_Giocatore`),
  CONSTRAINT `Partecipazione_ibfk_1` FOREIGN KEY (`ID_Squadra`) REFERENCES `Squadra` (`ID_Squadra`),
  CONSTRAINT `Partecipazione_ibfk_2` FOREIGN KEY (`ID_Giocatore`) REFERENCES `Giocatore` (`ID_Giocatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Partita`
--

DROP TABLE IF EXISTS `Partita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Partita` (
  `ID_Partita` int(5) NOT NULL AUTO_INCREMENT,
  `ID_Campionato` int(5) NOT NULL,
  `ID_Casa` int(5) NOT NULL,
  `ID_Ospite` int(5) NOT NULL,
  `Giornata` int(5) NOT NULL,
  `Data` date DEFAULT NULL,
  `GoalCasa` int(5) DEFAULT NULL,
  `GoalOspite` int(5) DEFAULT NULL,
  `ID_Arbitro` int(5) DEFAULT NULL,
  PRIMARY KEY (`ID_Partita`),
  KEY `Partita_ibfk_1` (`ID_Campionato`),
  KEY `Partita_ibfk_3` (`ID_Casa`),
  KEY `Partita_ibfk_4` (`ID_Ospite`),
  KEY `Partita_ibfk_2` (`ID_Arbitro`),
  CONSTRAINT `Partita_ibfk_1` FOREIGN KEY (`ID_Campionato`) REFERENCES `Campionato` (`ID_Campionato`),
  CONSTRAINT `Partita_ibfk_2` FOREIGN KEY (`ID_Arbitro`) REFERENCES `Utente` (`ID_Utente`),
  CONSTRAINT `Partita_ibfk_3` FOREIGN KEY (`ID_Casa`) REFERENCES `Squadra` (`ID_Squadra`),
  CONSTRAINT `Partita_ibfk_4` FOREIGN KEY (`ID_Ospite`) REFERENCES `Squadra` (`ID_Squadra`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Squadra`
--

DROP TABLE IF EXISTS `Squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Squadra` (
  `ID_Squadra` int(5) NOT NULL AUTO_INCREMENT,
  `ID_Allenatore` int(5) NOT NULL,
  `ID_Campionato` int(5) DEFAULT NULL,
  `NomeSquadra` varchar(25) NOT NULL,
  `Vittorie` int(5) DEFAULT '0',
  `Pareggi` int(5) DEFAULT '0',
  `Sconfitte` int(5) DEFAULT '0',
  `GoalFatti` int(5) DEFAULT '0',
  `GoalSubiti` int(5) DEFAULT '0',
  `StatoIscrizione` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID_Squadra`),
  KEY `Squadra_ibfk_1` (`ID_Allenatore`),
  KEY `Squadra_ibfk_2` (`ID_Campionato`),
  CONSTRAINT `Squadra_ibfk_1` FOREIGN KEY (`ID_Allenatore`) REFERENCES `Utente` (`ID_Utente`),
  CONSTRAINT `Squadra_ibfk_2` FOREIGN KEY (`ID_Campionato`) REFERENCES `Campionato` (`ID_Campionato`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Utente`
--

DROP TABLE IF EXISTS `Utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Utente` (
  `ID_Utente` int(5) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Cognome` varchar(25) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `Ruolo` char(1) NOT NULL,
  PRIMARY KEY (`ID_Utente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-21 19:23:15
