-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 23 mars 2020 à 23:00
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

DROP TABLE IF EXISTS `abonnement`;
CREATE TABLE IF NOT EXISTS `abonnement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `positionDepart` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `positionArrive` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `heureDepart` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Utilisateurs_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_351268BB892C575B` (`Utilisateurs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `abonnement`
--

INSERT INTO `abonnement` (`id`, `positionDepart`, `positionArrive`, `heureDepart`, `Utilisateurs_id`) VALUES
(1, 'aa', 'aa', '18h', 2),
(2, 'aa', 'aa', '18h', 2),
(3, 'aa', 'aa', '18h', 2),
(4, 'aa', 'aa', '18h', 2);

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE IF NOT EXISTS `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_FE38F844A76ED395` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

DROP TABLE IF EXISTS `commentaires`;
CREATE TABLE IF NOT EXISTS `commentaires` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `part` int(11) DEFAULT NULL,
  `commentaire` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D9BEC0C48D93D649` (`user`),
  KEY `IDX_D9BEC0C4490F70C6` (`part`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commentaires`
--

INSERT INTO `commentaires` (`id`, `user`, `part`, `commentaire`, `date`) VALUES
(1, 24, 1, 'commentaire', '2020-02-27 10:10:24'),
(2, 2, 1, 'testons', '2020-02-27 10:11:14');

-- --------------------------------------------------------

--
-- Structure de la table `commission`
--

DROP TABLE IF EXISTS `commission`;
CREATE TABLE IF NOT EXISTS `commission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partenaire_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `inventairec_id` int(11) DEFAULT NULL,
  `pourcentage` double NOT NULL,
  `date_commission` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_1C65015898DE13AC` (`partenaire_id`),
  KEY `IDX_1C650158591CC992` (`course_id`),
  KEY `IDX_1C6501582A74DE1F` (`inventairec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commission`
--

INSERT INTO `commission` (`id`, `partenaire_id`, `course_id`, `inventairec_id`, `pourcentage`, `date_commission`) VALUES
(1, 24, 1, 1, 0.15, '2015-01-01'),
(2, 24, 2, 1, 0.15, '2015-01-01'),
(3, 24, 3, 1, 0.15, '2020-02-14'),
(4, 24, 4, 1, 0.15, '2020-02-19'),
(5, 24, 5, 1, 0.15, '2020-02-19');

-- --------------------------------------------------------

--
-- Structure de la table `commission_r`
--

DROP TABLE IF EXISTS `commission_r`;
CREATE TABLE IF NOT EXISTS `commission_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partenaire_id` int(11) DEFAULT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  `pourcentage` double NOT NULL,
  `date_commission` datetime NOT NULL,
  `inventaireR_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_FCFA981398DE13AC` (`partenaire_id`),
  KEY `IDX_FCFA9813B83297E7` (`reservation_id`),
  KEY `IDX_FCFA981362E341DB` (`inventaireR_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commission_r`
--

INSERT INTO `commission_r` (`id`, `partenaire_id`, `reservation_id`, `pourcentage`, `date_commission`, `inventaireR_id`) VALUES
(36, 24, 60, 0.15, '2020-02-19 00:01:11', 12),
(39, 24, 63, 0.15, '2020-02-19 00:27:20', 12),
(41, 24, 65, 0.15, '2020-02-19 00:29:56', 12),
(45, 29, 69, 0.15, '2020-02-19 12:15:40', 13),
(46, 24, 70, 0.15, '2020-02-23 00:50:30', 12),
(47, 24, 71, 0.15, '2020-02-27 02:29:01', 12),
(48, 24, 72, 0.15, '2020-02-27 10:22:02', 12),
(49, 24, 73, 0.15, '2020-02-28 12:46:30', 12);

-- --------------------------------------------------------

--
-- Structure de la table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `partenaire_id` int(11) DEFAULT NULL,
  `depart` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_course` datetime NOT NULL,
  `prix` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_A9A55A4C19EB6921` (`client_id`),
  KEY `IDX_A9A55A4C98DE13AC` (`partenaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `courses`
--

INSERT INTO `courses` (`id`, `client_id`, `partenaire_id`, `depart`, `destination`, `date_course`, `prix`) VALUES
(1, 2, 24, 'u', 'aze', '2015-01-01 00:00:00', 200),
(2, 2, 24, 'Aaengveien, Trondheim, Norvège', 'ame', '2015-01-01 00:00:00', 42.532340636273),
(3, 2, 24, 'Tunis, Tunisie', 'Ames, Iowa, États-Unis', '2020-02-14 11:11:00', 0),
(4, 2, 24, 'Amsterdam, Pays-Bas', 'Tōkyō, Tokyo, Japon', '2020-02-19 11:25:16', 0),
(5, 2, 24, 'am', 'taa', '2020-02-19 11:25:38', 0);

-- --------------------------------------------------------

--
-- Structure de la table `inventaire_c`
--

DROP TABLE IF EXISTS `inventaire_c`;
CREATE TABLE IF NOT EXISTS `inventaire_c` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partenaire_id` int(11) DEFAULT NULL,
  `montant` double NOT NULL,
  `date_i` date NOT NULL,
  `paye` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_CD51832B98DE13AC` (`partenaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `inventaire_c`
--

INSERT INTO `inventaire_c` (`id`, `partenaire_id`, `montant`, `date_i`, `paye`) VALUES
(1, 24, 36.379851095441, '2015-01-01', 1);

-- --------------------------------------------------------

--
-- Structure de la table `inventaire_r`
--

DROP TABLE IF EXISTS `inventaire_r`;
CREATE TABLE IF NOT EXISTS `inventaire_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partenaire_id` int(11) DEFAULT NULL,
  `montant` double NOT NULL,
  `date_i` datetime NOT NULL,
  `done` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9C8F831198DE13AC` (`partenaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `inventaire_r`
--

INSERT INTO `inventaire_r` (`id`, `partenaire_id`, `montant`, `date_i`, `done`) VALUES
(12, 24, 73.821462258727, '2020-02-18 16:16:46', 0),
(13, 29, 4.3988634895846, '2020-02-19 12:15:40', 0);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B6BD307FE2904019` (`thread_id`),
  KEY `IDX_B6BD307FF624B39D` (`sender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `thread_id`, `sender_id`, `body`, `created_at`) VALUES
(1, 1, 2, 'aaa', '2020-02-27 09:20:19');

-- --------------------------------------------------------

--
-- Structure de la table `message_metadata`
--

DROP TABLE IF EXISTS `message_metadata`;
CREATE TABLE IF NOT EXISTS `message_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_4632F005537A1329` (`message_id`),
  KEY `IDX_4632F0059D1C3019` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message_metadata`
--

INSERT INTO `message_metadata` (`id`, `message_id`, `participant_id`, `is_read`) VALUES
(1, 1, 24, 0),
(2, 1, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `partenaire`
--

DROP TABLE IF EXISTS `partenaire`;
CREATE TABLE IF NOT EXISTS `partenaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taxi_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mdp` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` int(11) NOT NULL,
  `nb` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_32FFA373506FF81C` (`taxi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `partenaire`
--

INSERT INTO `partenaire` (`id`, `taxi_id`, `nom`, `prenom`, `mail`, `mdp`, `tel`, `nb`) VALUES
(1, 126, 'melek', 'aa', 'anestemani00@gmail.com', 'aa', 25555, 12),
(4, 126, 'tmeni', 'anes', 'temaniAnes@gmail.com', 'aze', 54654, 5),
(5, 126, 'ma', 'aa', 'anestemani00@gmail.com', 'ze', 65464564, 0);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Flag` int(11) NOT NULL,
  `Valeur` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`id`, `Description`, `Code`, `Flag`, `Valeur`) VALUES
(1, 'aaaa', '4', 0, 10);

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `part` int(11) DEFAULT NULL,
  `rate` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D88926228D93D649` (`user`),
  KEY `IDX_D8892622490F70C6` (`part`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `rating`
--

INSERT INTO `rating` (`id`, `user`, `part`, `rate`, `date`) VALUES
(1, 2, 1, 4, '2020-02-27 10:10:08');

-- --------------------------------------------------------

--
-- Structure de la table `reclammations`
--

DROP TABLE IF EXISTS `reclammations`;
CREATE TABLE IF NOT EXISTS `reclammations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `sujet` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `contenu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_757A45F18D93D649` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reponse_reclammation`
--

DROP TABLE IF EXISTS `reponse_reclammation`;
CREATE TABLE IF NOT EXISTS `reponse_reclammation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reclammation` int(11) DEFAULT NULL,
  `client` int(11) DEFAULT NULL,
  `sujet` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `contenu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateReponse` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_6FEF422D1F8C1D97` (`reclammation`),
  KEY `IDX_6FEF422DC7440455` (`client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `partenaire_id` int(11) DEFAULT NULL,
  `pointAchat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `destination` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `listAchats` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remarques` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_42C8495519EB6921` (`client_id`),
  KEY `IDX_42C8495598DE13AC` (`partenaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `client_id`, `partenaire_id`, `pointAchat`, `destination`, `date`, `prix`, `listAchats`, `remarques`, `etat`) VALUES
(60, 2, 24, 'Australia', 'Alexandria Nouvelle-Galles du Sud, Australie', '2020-02-19 00:01:11', 100, 'aze', 'aze', 'non traite'),
(63, 2, 24, 'Amsterdam, Pays-Bas', 'San Francisco, Californie, États-Unis', '2020-02-19 00:27:20', 63.23764701505, 'ds', 'sdqf', 'traite'),
(65, 2, 24, 'Tunis, Tunisie', 'hafood', '2020-02-19 00:29:56', 18, 'aa', 'aa', 'non traite'),
(69, 2, 29, 'Amsterdam, Pays-Bas', 'Paris, France', '2020-02-19 12:15:40', 29.32575659723, 'aaa', 'aaa', 'refusé'),
(70, 2, 24, '7', 'Amsterdam, Pays-Bas', '2020-02-23 00:50:30', 50, '22', 'rien', 'non traite'),
(71, 2, 24, '6', 'Atlanta, Géorgie, États-Unis', '2020-02-27 02:29:01', 90.947480126903, '20', 'aze', 'non traite'),
(72, 2, 24, '6', 'Amsterdam, Pays-Bas', '2020-02-27 10:22:02', 72.066609025521, '20', 'aaaa', 'traite'),
(73, 2, 24, '6', 'Atlanta, Géorgie, États-Unis', '2020-02-28 12:46:30', 97.891345557375, '20', 'aa', 'refusé');

-- --------------------------------------------------------

--
-- Structure de la table `taxi`
--

DROP TABLE IF EXISTS `taxi`;
CREATE TABLE IF NOT EXISTS `taxi` (
  `matricule` int(11) NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `marque` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `taxi`
--

INSERT INTO `taxi` (`matricule`, `etat`, `marque`, `image`) VALUES
(126, 'en_service', 'zs', '181701.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_31204C83B03A8386` (`created_by_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread`
--

INSERT INTO `thread` (`id`, `created_by_id`, `subject`, `created_at`, `is_spam`) VALUES
(1, 2, 'aa', '2020-02-27 09:20:19', 0);

-- --------------------------------------------------------

--
-- Structure de la table `thread_metadata`
--

DROP TABLE IF EXISTS `thread_metadata`;
CREATE TABLE IF NOT EXISTS `thread_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_40A577C8E2904019` (`thread_id`),
  KEY `IDX_40A577C89D1C3019` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread_metadata`
--

INSERT INTO `thread_metadata` (`id`, `thread_id`, `participant_id`, `is_deleted`, `last_participant_message_date`, `last_message_date`) VALUES
(1, 1, 24, 0, NULL, '2020-02-27 09:20:19'),
(2, 1, 2, 0, '2020-02-27 09:20:19', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_497B315E92FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_497B315EA0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_497B315EC05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `telephone`) VALUES
(2, 'anes013', 'anes013', 'anestemani00@gmail.com', 'anestemani00@gmail.com', 1, 'D.KYzcN0RIgidlNBEH5YjaHSvB8einVem0Vu75WlnX0', 'YFew6lwOvIy4jc18cvH2/t38UUYTbdj7LeDjh13uCGJcXodFhPKat1t7JZWNtj2cnusXVdQoyavAzB83Hvwt7w==', '2020-03-23 16:19:10', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', '', '', ''),
(24, 'arbi', 'arbi', 'arbisaidi8@gmail.com', 'arbisaidi8@gmail.com', 1, 'lRYMPUbfD6oX46R1Uav6kAHPAsEy3zm5uNqmXN1nQP8', 'fbmVLlC5JsRYXzImsLAfXgNPxkVO1+8pPgbs6HL1q9mtAriRNUdDM3seek6HfIHTwPIueUcyknflNk/2IC7fqQ==', '2020-02-27 09:20:50', 'G4DS3zzArBciTWXqPXkV_c8OiQUILPtfzNzpLXDDtQg', NULL, 'a:1:{i:0;s:15:\"ROLE_PARTENAIRE\";}', 'arbi', 'saidi', ''),
(29, 'aa', 'aa', 'anestemani00@gmail.tn', 'anestemani00@gmail.tn', 0, 'h0uKIi5NIUHMRdRJm4blFInhq90eS1kW88AUx8SFN1c', 'zMbewNiicSOHiO2RVNGfwqKVe2e88gdxB6d1HGckhji7r0UOC7xM86zMJdmTQLw59H9GuEaOFu/oFMUnjg3aWg==', NULL, 'HjWyiRIPxDHrdFZjGG1wjY1xJEvRSttQv5TTJEGrl7c', NULL, 'a:1:{i:0;s:15:\"ROLE_PARTENAIRE\";}', 'anes', 'temani', '');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `FK_351268BB892C575B` FOREIGN KEY (`Utilisateurs_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `FK_FE38F844A76ED395` FOREIGN KEY (`user_id`) REFERENCES `utilisateurs` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `FK_D9BEC0C4490F70C6` FOREIGN KEY (`part`) REFERENCES `partenaire` (`id`),
  ADD CONSTRAINT `FK_D9BEC0C48D93D649` FOREIGN KEY (`user`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `commission`
--
ALTER TABLE `commission`
  ADD CONSTRAINT `FK_1C6501582A74DE1F` FOREIGN KEY (`inventairec_id`) REFERENCES `inventaire_c` (`id`),
  ADD CONSTRAINT `FK_1C650158591CC992` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `FK_1C65015898DE13AC` FOREIGN KEY (`partenaire_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `commission_r`
--
ALTER TABLE `commission_r`
  ADD CONSTRAINT `FK_FCFA981362E341DB` FOREIGN KEY (`inventaireR_id`) REFERENCES `inventaire_r` (`id`),
  ADD CONSTRAINT `FK_FCFA981398DE13AC` FOREIGN KEY (`partenaire_id`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `FK_FCFA9813B83297E7` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`);

--
-- Contraintes pour la table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `FK_A9A55A4C19EB6921` FOREIGN KEY (`client_id`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `FK_A9A55A4C98DE13AC` FOREIGN KEY (`partenaire_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `inventaire_c`
--
ALTER TABLE `inventaire_c`
  ADD CONSTRAINT `FK_CD51832B98DE13AC` FOREIGN KEY (`partenaire_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `inventaire_r`
--
ALTER TABLE `inventaire_r`
  ADD CONSTRAINT `FK_9C8F831198DE13AC` FOREIGN KEY (`partenaire_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `partenaire`
--
ALTER TABLE `partenaire`
  ADD CONSTRAINT `FK_32FFA373506FF81C` FOREIGN KEY (`taxi_id`) REFERENCES `taxi` (`matricule`);

--
-- Contraintes pour la table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `FK_D8892622490F70C6` FOREIGN KEY (`part`) REFERENCES `partenaire` (`id`),
  ADD CONSTRAINT `FK_D88926228D93D649` FOREIGN KEY (`user`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `reclammations`
--
ALTER TABLE `reclammations`
  ADD CONSTRAINT `FK_757A45F18D93D649` FOREIGN KEY (`user`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `reponse_reclammation`
--
ALTER TABLE `reponse_reclammation`
  ADD CONSTRAINT `FK_6FEF422D1F8C1D97` FOREIGN KEY (`reclammation`) REFERENCES `reclammations` (`id`),
  ADD CONSTRAINT `FK_6FEF422DC7440455` FOREIGN KEY (`client`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C8495519EB6921` FOREIGN KEY (`client_id`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `FK_42C8495598DE13AC` FOREIGN KEY (`partenaire_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
