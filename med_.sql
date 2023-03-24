-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 22 Février 2023 à 04:35
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
-- Structure de la table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `n_patient` int(11) NOT NULL AUTO_INCREMENT,
  `nom_p` varchar(20) NOT NULL,
  `prenom_p` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  `n_tel` int(11) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  PRIMARY KEY (`n_patient`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `patient`
--

INSERT INTO `patient` (`n_patient`, `nom_p`, `prenom_p`, `age`, `sexe`, `n_tel`, `adresse`) VALUES
(3, 'mehdi', 'mehadji', 16, 'Homme', 55124214, 'kharouba'),
(4, 'rayan', 'mouloud', 15, 'Homme', 555234152, 'kharouba'),
(5, 'khalil', 'ben', 20, 'Homme', 55123456, 'kharouba'),
(6, 'zoubida', 'raad', 45, 'Femme', 51287545, 'mostaganem');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `date_recrut` date NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `Role` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `age`, `date_recrut`, `username`, `password`, `Role`) VALUES
(1, 'khalil', 'bnd', 21, '2023-01-11', 'admin', 'admin', 'Doctor'),
(2, 'Mohamed', 'Mehadji', 99, '2023-01-01', 'moh', 'moh', 'Nurse');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
