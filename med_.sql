-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 16, 2023 at 12:12 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `med+`
--

-- --------------------------------------------------------

--
-- Table structure for table `2fa`
--

DROP TABLE IF EXISTS `2fa`;
CREATE TABLE IF NOT EXISTS `2fa` (
  `question` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `id` int(11) NOT NULL,
  KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `2fa`
--

INSERT INTO `2fa` (`question`, `answer`, `id`) VALUES
('Dans quelle ville êtes-vous né(e)', 'remchi', 4),
('Quelle est la marque de votre première voiture', '308', 1),
('Quel est le prénom de votre meilleur(e) ami(e) d enfance', 'khalil', 2),
('Quel est le prénom de votre meilleur(e) ami(e) d enfance', 'leila', 8),
('Quel est le prénom de votre meilleur(e) ami(e) d enfance', 'abdou', 9),
('Dans quelle ville êtes-vous né(e)', 'jiji', 10),
('Quel est le prénom de votre meilleur(e) ami(e) d enfance', 'kikii', 11);

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
CREATE TABLE IF NOT EXISTS `consultation` (
  `n_patient` int(11) NOT NULL,
  `anc_maladie` varchar(500) NOT NULL,
  `notes_cons` varchar(500) NOT NULL,
  `ordonnance` varchar(500) NOT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctor_id`, `nom`, `prenom`) VALUES
(1, 'khalil', 'ben'),
(2, 'mohamed', 'hbib');

-- --------------------------------------------------------

--
-- Table structure for table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `N` varchar(200) NOT NULL,
  `Nom` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicament`
--

INSERT INTO `medicament` (`N`, `Nom`) VALUES
('1', 'Doliprane'),
('2', 'Paracétamole'),
('3', 'dwa');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `n_patient` int(11) NOT NULL AUTO_INCREMENT,
  `nom_p` varchar(50) NOT NULL,
  `prenom_p` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `sexe` varchar(15) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `date_r` date NOT NULL,
  `first_day` date DEFAULT NULL,
  `doctor_id` int(11) NOT NULL,
  `n_tel` varchar(25) NOT NULL,
  PRIMARY KEY (`n_patient`),
  KEY `doctor_id` (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`n_patient`, `nom_p`, `prenom_p`, `age`, `sexe`, `adresse`, `date_r`, `first_day`, `doctor_id`, `n_tel`) VALUES
(10, 'raid', 'haddi', 21, 'Femme', 'kharouba ', '2023-06-12', '2023-06-08', 11, '5555555555'),
(11, 'sidroho', 'rafik', 23, 'Homme', 'sison', '2023-06-12', '2022-06-01', 11, '9282'),
(12, 'moh', 'moh', 12, 'Homme', 'kharouba', '2023-06-16', '2023-06-15', 2, '152');

--
-- Triggers `patient`
--
DROP TRIGGER IF EXISTS `refresh`;
DELIMITER $$
CREATE TRIGGER `refresh` AFTER UPDATE ON `patient` FOR EACH ROW BEGIN
        IF NEW.date_r <> OLD.date_r THEN
            DELETE FROM waiting_room WHERE n_patient = NEW.n_patient;
        END IF;
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `username`, `password`, `age`, `Role`, `date_recrut`) VALUES
(1, 'admin', 'unknown', 'admin', 'admin', 50, 'Chef', '2023-05-06'),
(2, 'mehadji', 'bnd', 'doc', 'doc', 21, 'Doctor', '2023-01-11'),
(8, 'khalida', 'farida', 'inf', 'inf', 44, 'Nurse', '2023-06-12'),
(11, 'benachenho', 'moh', 'moh', 'moh', 22, 'Doctor', '2023-06-12');

-- --------------------------------------------------------

--
-- Table structure for table `waiting_room`
--

DROP TABLE IF EXISTS `waiting_room`;
CREATE TABLE IF NOT EXISTS `waiting_room` (
  `doctor_id` int(11) NOT NULL,
  `n_patient` int(11) NOT NULL,
  `place` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  KEY `waiting_room_ibfk_2` (`n_patient`),
  KEY `doctor_id` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `waiting_room`
--

INSERT INTO `waiting_room` (`doctor_id`, `n_patient`, `place`, `price`) VALUES
(11, 10, 3, 0),
(11, 11, 4, 0),
(2, 12, 5, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `waiting_room`
--
ALTER TABLE `waiting_room`
  ADD CONSTRAINT `waiting_room_ibfk_3` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `waiting_room_ibfk_4` FOREIGN KEY (`n_patient`) REFERENCES `patient` (`n_patient`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
