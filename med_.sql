-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 01 Juin 2023 à 12:39
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `med+`
--

-- --------------------------------------------------------

--
-- Structure de la table `doctor`
--

CREATE TABLE IF NOT EXISTS `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `n_patient` int(11) NOT NULL AUTO_INCREMENT,
  `nom_p` varchar(50) NOT NULL,
  `prenom_p` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `sexe` varchar(15) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `date_r` date NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `n_tel` varchar(25) NOT NULL,
  PRIMARY KEY (`n_patient`),
  KEY `doctor_id` (`doctor_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `patient`
--

INSERT INTO `patient` (`n_patient`, `nom_p`, `prenom_p`, `age`, `sexe`, `adresse`, `date_r`, `doctor_id`, `n_tel`) VALUES
(2, 'dqssdsq', 'qsdqsdqsd', 14, 'Homme', 'dggdsfs', '2023-05-26', 2, '55454');

--
-- Déclencheurs `patient`
--
DROP TRIGGER IF EXISTS `refresh`;
DELIMITER //
CREATE TRIGGER `refresh` AFTER UPDATE ON `patient`
 FOR EACH ROW BEGIN
        IF NEW.date_r <> OLD.date_r THEN
            DELETE FROM waiting_room WHERE n_patient = NEW.n_patient;
        END IF;
    END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `Role` varchar(50) NOT NULL,
  `date_recrut` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `username`, `password`, `age`, `Role`, `date_recrut`) VALUES
(1, 'admin', 'unknown', 'admin', 'admin', 50, 'Chef', '2023-05-06'),
(2, 'khalil', 'bnd', 'docteur', 'docteur', 21, 'Doctor', '2023-01-11'),
(4, 'fatiha', 'khalida', 'nurse', 'nurse', 25, 'Nurse', '2023-05-11'),
(5, 'mehadji', 'abdou', 'kalix', '123', 58, 'Doctor', '2023-05-06'),
(7, 'mkharbech', 'joueur', 'power', '456', 44, 'Doctor', '2023-05-06');

-- --------------------------------------------------------

--
-- Structure de la table `waiting_room`
--

CREATE TABLE IF NOT EXISTS `waiting_room` (
  `doctor_id` int(11) NOT NULL,
  `n_patient` int(11) NOT NULL,
  `place` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  KEY `waiting_room_ibfk_2` (`n_patient`),
  KEY `doctor_id` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `waiting_room`
--

INSERT INTO `waiting_room` (`doctor_id`, `n_patient`, `place`, `price`) VALUES
(2, 2, 1, 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `waiting_room`
--
ALTER TABLE `waiting_room`
  ADD CONSTRAINT `waiting_room_ibfk_2` FOREIGN KEY (`n_patient`) REFERENCES `patient` (`n_patient`),
  ADD CONSTRAINT `waiting_room_ibfk_3` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
