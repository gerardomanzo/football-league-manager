
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `footballleaguemanager`
   CREATE DATABASE footballleaguemanager;
--

-- --------------------------------------------------------

--
-- Struttura della tabella `Campionato`
--

CREATE TABLE IF NOT EXISTS `Campionato` (
  `ID_Campionato` int(5) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `NumSquadre` int(5) NOT NULL,
  `Quota` float NOT NULL,
  PRIMARY KEY (`ID_Campionato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Giocatore`
--

CREATE TABLE IF NOT EXISTS `Giocatore` (
  `ID_Giocatore` int(5) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Cognome` varchar(25) NOT NULL,
  PRIMARY KEY (`ID_Giocatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Informazioni`
--

CREATE TABLE IF NOT EXISTS `Informazioni` (
  `ID_Partita` int(5) NOT NULL,
  `ID_Giocatore` int(5) NOT NULL,
  `Goal` smallint(6) NOT NULL,
  `Assist` int(5) NOT NULL,
  `Cartellino` varchar(15) NOT NULL,
  `Motivazione` varchar(30) NOT NULL,
  `Squalifica` int(5) NOT NULL,
  KEY `ID_Giocatore` (`ID_Giocatore`),
  KEY `ID_Partita` (`ID_Partita`),
  KEY `ID_Partita_2` (`ID_Partita`),
  KEY `ID_Partita_3` (`ID_Partita`),
  KEY `ID_Partita_4` (`ID_Partita`),
  KEY `ID_Partita_5` (`ID_Partita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Partecipazione`
--

CREATE TABLE IF NOT EXISTS `Partecipazione` (
  `ID_Squadra` int(5) NOT NULL,
  `ID_Giocatore` int(5) NOT NULL,
  KEY `ID_Squadra` (`ID_Squadra`),
  KEY `ID_Giocatore` (`ID_Giocatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Partita`
--

CREATE TABLE IF NOT EXISTS `Partita` (
  `ID_Partita` int(5) NOT NULL AUTO_INCREMENT,
  `ID_Campionato` int(5) NOT NULL,
  `ID_Casa` int(5) NOT NULL,
  `ID_Ospite` int(5) NOT NULL,
  `Giornata` int(5) NOT NULL,
  `Data` date NOT NULL,
  `GoalCasa` int(5) NOT NULL,
  `GoalOspite` int(5) NOT NULL,
  `ID_Arbitro` int(5) NOT NULL,
  PRIMARY KEY (`ID_Partita`),
  KEY `ID_Campionato` (`ID_Campionato`),
  KEY `ID_Arbitro` (`ID_Arbitro`),
  KEY `ID_Casa` (`ID_Casa`),
  KEY `ID_Casa_2` (`ID_Casa`),
  KEY `ID_Casa_3` (`ID_Casa`),
  KEY `ID_Ospite` (`ID_Ospite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Squadra`
--

CREATE TABLE IF NOT EXISTS `Squadra` (
  `ID_Squadra` int(5) NOT NULL AUTO_INCREMENT,
  `ID_Allenatore` int(5) NOT NULL,
  `ID_Campionato` int(5) NOT NULL,
  `NomeSquadra` varchar(25) NOT NULL,
  `Vittorie` int(5) NOT NULL,
  `Pareggi` int(5) NOT NULL,
  `Sconfitte` int(5) NOT NULL,
  `GoalFatti` int(5) NOT NULL,
  `GoalSubiti` int(5) NOT NULL,
  `StatoIscrizione` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_Squadra`),
  KEY `ID_Allenatore` (`ID_Allenatore`),
  KEY `ID_Campionato` (`ID_Campionato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Utenti`
--

CREATE TABLE IF NOT EXISTS `Utenti` (
  `ID_Arbitro` int(5) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Cognome` varchar(25) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `Ruolo` char(1) NOT NULL,
  PRIMARY KEY (`ID_Arbitro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Informazioni`
--
ALTER TABLE `Informazioni`
  ADD CONSTRAINT `Informazioni_ibfk_1` FOREIGN KEY (`ID_Partita`) REFERENCES `Partita` (`ID_Partita`),
  ADD CONSTRAINT `Informazioni_ibfk_2` FOREIGN KEY (`ID_Giocatore`) REFERENCES `Giocatore` (`ID_Giocatore`);

--
-- Limiti per la tabella `Partecipazione`
--
ALTER TABLE `Partecipazione`
  ADD CONSTRAINT `Partecipazione_ibfk_1` FOREIGN KEY (`ID_Squadra`) REFERENCES `Squadra` (`ID_Squadra`),
  ADD CONSTRAINT `Partecipazione_ibfk_2` FOREIGN KEY (`ID_Giocatore`) REFERENCES `Giocatore` (`ID_Giocatore`);

--
-- Limiti per la tabella `Partita`
--
ALTER TABLE `Partita`
  ADD CONSTRAINT `Partita_ibfk_1` FOREIGN KEY (`ID_Campionato`) REFERENCES `Campionato` (`ID_Campionato`),
  ADD CONSTRAINT `Partita_ibfk_2` FOREIGN KEY (`ID_Arbitro`) REFERENCES `Utenti` (`ID_Arbitro`),
  ADD CONSTRAINT `Partita_ibfk_3` FOREIGN KEY (`ID_Casa`) REFERENCES `Squadra` (`ID_Squadra`),
  ADD CONSTRAINT `Partita_ibfk_4` FOREIGN KEY (`ID_Ospite`) REFERENCES `Squadra` (`ID_Squadra`);

--
-- Limiti per la tabella `Squadra`
--
ALTER TABLE `Squadra`
  ADD CONSTRAINT `Squadra_ibfk_2` FOREIGN KEY (`ID_Campionato`) REFERENCES `Campionato` (`ID_Campionato`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
