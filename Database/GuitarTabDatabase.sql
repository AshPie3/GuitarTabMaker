-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.8.3-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for guitartab
CREATE DATABASE IF NOT EXISTS `guitartab` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `guitartab`;

-- Dumping structure for table guitartab.intervals
CREATE TABLE IF NOT EXISTS `intervals` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_name` varchar(50) NOT NULL,
  `i_val` int(11) NOT NULL,
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table guitartab.intervals: ~14 rows (approximately)
/*!40000 ALTER TABLE `intervals` DISABLE KEYS */;
INSERT INTO `intervals` (`i_id`, `i_name`, `i_val`) VALUES
	(1, 'P1', 0),
	(2, 'm2', 1),
	(3, 'M2', 2),
	(4, 'm3', 3),
	(5, 'M3', 4),
	(6, 'P4', 5),
	(7, 'TT', 6),
	(8, 'P5', 7),
	(9, 'A5', 8),
	(10, 'm6', 8),
	(11, 'M6', 9),
	(12, 'm7', 10),
	(13, 'M7', 11),
	(14, 'P8', 12);
/*!40000 ALTER TABLE `intervals` ENABLE KEYS */;

-- Dumping structure for table guitartab.notes
CREATE TABLE IF NOT EXISTS `notes` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `n_name` varchar(3) DEFAULT NULL,
  `n_val` int(2) DEFAULT NULL,
  `n_oct` int(1) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table guitartab.notes: ~60 rows (approximately)
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` (`n_id`, `n_name`, `n_val`, `n_oct`) VALUES
	(1, 'A2', 1, 2),
	(2, 'A#2', 2, 2),
	(3, 'B2', 3, 2),
	(4, 'C2', 4, 2),
	(5, 'C#2', 5, 2),
	(6, 'D2', 6, 2),
	(7, 'D#2', 7, 2),
	(8, 'E2', 8, 2),
	(9, 'F2', 9, 2),
	(10, 'F#2', 10, 2),
	(11, 'G2', 11, 2),
	(12, 'G#2', 12, 2),
	(13, 'A3', 1, 3),
	(14, 'A#3', 2, 3),
	(15, 'B3', 3, 3),
	(16, 'C3', 4, 3),
	(17, 'C#3', 5, 3),
	(18, 'D3', 6, 3),
	(19, 'D#3', 7, 3),
	(20, 'E3', 8, 3),
	(21, 'F3', 9, 3),
	(22, 'F#3', 10, 3),
	(23, 'G3', 11, 3),
	(24, 'G#3', 12, 3),
	(25, 'A4', 1, 4),
	(26, 'A#4', 2, 4),
	(27, 'B4', 3, 4),
	(28, 'C4', 4, 4),
	(29, 'C#4', 5, 4),
	(30, 'D4', 6, 4),
	(31, 'D#4', 7, 4),
	(32, 'E4', 8, 4),
	(33, 'F4', 9, 4),
	(34, 'F#4', 10, 4),
	(35, 'G4', 11, 4),
	(36, 'G#4', 12, 4),
	(37, 'A5', 1, 5),
	(38, 'A#5', 2, 5),
	(39, 'B5', 3, 5),
	(40, 'C5', 4, 5),
	(41, 'C#5', 5, 5),
	(42, 'D5', 6, 5),
	(43, 'D#5', 7, 5),
	(44, 'E5', 8, 5),
	(45, 'F5', 9, 5),
	(46, 'F#5', 10, 5),
	(47, 'G5', 11, 5),
	(48, 'G#5', 12, 5),
	(49, 'A6', 1, 6),
	(50, 'A#6', 2, 6),
	(51, 'B6', 3, 6),
	(52, 'C6', 4, 6),
	(53, 'C#6', 5, 6),
	(54, 'D6', 6, 6),
	(55, 'D#6', 7, 6),
	(56, 'E6', 8, 6),
	(57, 'F6', 9, 6),
	(58, 'F#6', 10, 6),
	(59, 'G6', 11, 6),
	(60, 'G#6', 12, 6);
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;

-- Dumping structure for table guitartab.projects
CREATE TABLE IF NOT EXISTS `projects` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_t_id` int(11) NOT NULL,
  `p_s_id` int(11) NOT NULL,
  `p_key_val` int(11) NOT NULL,
  `p_name` text CHARACTER SET utf8mb3 DEFAULT 'New Project',
  `p_cvs_val` mediumtext DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `FK_projects_tunings` (`p_t_id`),
  KEY `FK_projects_scale` (`p_s_id`),
  CONSTRAINT `FK_projects_scale` FOREIGN KEY (`p_s_id`) REFERENCES `scale` (`s_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_projects_tunings` FOREIGN KEY (`p_t_id`) REFERENCES `tunings` (`t_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf16;

-- Dumping data for table guitartab.projects: ~10 rows (approximately)
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` (`p_id`, `p_t_id`, `p_s_id`, `p_key_val`, `p_name`, `p_cvs_val`) VALUES
	(1, 2, 1, 4, 'FACGCE C MAJ', 'E ,C ,G ,C ,A ,F ,  ,|,|,|,|,|,|, ,--,--,--,--,--,0-,^^,--,--,--,--,10,--,  ,--,--,--,11,--,9-,  ,--,--,--,7-,--,--,  ,|,|,|,|,|,|, ,--,--,--,--,--,--,  ,--,--,--,--,--,12,  ,--,--,--,--,--,10,  ,--,--,7-,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,|,|,|,|,|,|, ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,10,--,  ,--,--,12,--,--,--,  ,--,--,12,--,--,--,  ,--,--,--,--,--,12,  ,--,--,--,--,--,12,  ,--,--,--,--,--,--,  ,|,|,|,|,|,|, ,--,--,--,--,15,--,  ,--,--,--,15,--,--,  ,--,--,15,--,--,--,  ,--,--,--,12,--,--,  ,--,--,11,--,--,--,  ,--,--,--,10,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,13,--,  ,--,--,--,--,13,--,  ,--,--,13,--,--,--,  ,--,--,14,--,--,--,  ,--,--,--,--,--,15,  ,--,--,--,--,--,--,  ,|,|,|,|,|,|, ,--,--,--,--,12,--,  ,--,--,--,--,12,--,  ,--,--,--,12,--,--,  ,--,--,--,--,--,12,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,15,  ,--,--,--,--,14,--,  ,--,--,--,--,14,--,  ,--,--,--,14,--,--,  ,--,--,14,--,--,--,  ,--,--,14,--,--,--,  ,--,16,--,--,--,--,  ,--,--,--,16,--,--,  ,--,--,--,16,--,--,  ,--,--,--,14,--,--,  ,--,--,14,--,--,--,  ,--,--,14,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,14,--,  ,--,--,14,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,|,|,|,|,|,|, ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,|,|,|,|,|,|, ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,12,  ,--,--,--,--,12,--,  ,--,--,--,12,--,--,  ,--,--,16,--,--,--,  ,--,--,--,16,--,--,  ,--,--,--,--,--,--,  ,|,|,|,|,|,|, ,--,--,--,--,--,14,  ,--,--,--,--,15,--,  ,--,--,--,16,--,--,  ,--,--,17,--,--,--,  ,--,17,--,--,--,--,  ,17,--,--,--,--,--,  ,--,16,--,--,--,--,  ,--,--,16,--,--,--,  ,--,--,--,16,--,--,  ,--,--,--,16,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,14,  ,--,--,--,--,--,14,  ,--,--,--,--,--,14,  ,--,--,--,--,14,--,  ,--,--,--,--,14,--,  ,--,--,--,--,14,--,  ,--,--,--,--,14,--,  ,--,--,--,14,--,--,  ,--,--,14,--,--,--,  ,--,14,--,--,--,--,  ,--,--,--,--,--,14,  ,--,--,--,--,14,--,  ,--,--,--,14,--,--,  ,--,--,14,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,14,  ,--,--,--,--,15,--,  ,--,--,--,--,--,16,  ,--,--,--,--,--,16,  ,--,--,--,--,17,--,  ,--,--,17,--,--,--,  ,--,--,--,--,--,14,  ,--,--,--,--,14,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,11,  ,--,--,--,--,12,--,  ,--,--,--,--,--,12,  ,--,--,--,--,--,9-,  ,--,--,--,11,--,--,  ,--,--,12,--,--,--,  ,--,--,--,12,--,--,  ,--,14,--,--,--,--,  ,--,--,14,--,--,--,  ,15,--,--,--,--,--,  ,--,--,16,--,--,--,  ,--,--,--,16,--,--,  ,--,--,--,--,--,14,  ,--,--,--,--,--,14,  ,--,--,--,--,--,--,  ,--,--,--,--,14,--,  ,--,--,--,14,--,--,  ,--,--,--,14,--,--,  ,--,--,--,14,--,--,  ,--,--,--,12,--,--,  ,--,--,16,--,--,--,  ,--,--,--,--,--,--,  ,'),
	(8, 3, 2, 1, 'Test 2', 'E ,B ,G ,D ,A ,D ,  ,|,|,|,|,|,|, ,--,--,--,--,5-,--,  ,--,--,--,5-,--,--,  ,--,--,5-,--,--,--,  ,--,--,--,--,--,--,  ,--,5-,--,--,--,--,  ,5-,--,--,--,--,--,  ,--,5-,--,--,--,--,  ,--,6-,--,--,--,--,  ,6-,--,--,--,--,--,  ,--,--,--,--,--,0-,  ,--,--,--,--,0-,--,  ,--,--,--,0-,--,--,  ,--,--,0-,--,--,--,  ,--,0-,--,--,--,--,  ,0-,--,--,--,--,12,  ,--,0-,--,--,12,12,  ,--,--,4-,--,12,--,  ,--,--,4-,12,--,--,  ,--,--,--,0-,--,--,  ,--,--,--,5-,--,--,  ,--,--,0-,--,--,--,  ,--,--,5-,--,--,--,  ,--,--,4-,--,--,--,  ,--,0-,--,--,--,--,  ,0-,--,--,--,--,--,  ,--,--,--,--,--,14,  ,--,--,--,--,14,--,  ,--,--,--,14,--,--,  ,--,--,14,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,12,  ,--,--,--,7-,8-,7-,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,12,  ,--,--,--,--,12,--,  ,--,--,--,12,--,--,  ,--,--,14,--,--,--,  ,--,--,--,14,--,--,  ,--,--,--,--,14,--,  ,--,--,--,15,--,--,  ,--,--,16,--,--,--,  ,--,17,--,--,--,--,  ,--,--,17,--,--,--,  ,17,--,--,--,--,--,  ,--,17,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,10,--,--,--,  ,--,--,--,10,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,12,  ,--,--,--,--,12,--,  ,--,--,12,--,--,--,  ,--,--,12,--,--,--,  ,--,10,--,--,--,--,  ,--,--,10,--,--,--,  ,--,10,--,--,--,--,  ,--,12,--,--,--,--,  ,--,--,--,--,--,--,^^,'),
	(11, 2, 1, 5, 'A new Project IN C#', 'E ,C ,G ,C ,A ,F ,  ,|,|,|,|,|,|, ,--,--,--,--,--,--,^^,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,10,--,--,--,--,  ,--,--,10,10,--,--,  ,--,--,10,--,--,--,  ,--,--,--,10,--,--,  ,--,--,--,--,11,--,  ,--,--,--,--,--,--,  ,--,--,--,--,--,--,  ,--,--,--,10,9-,--,  ,--,--,--,12,--,--,  ,--,--,--,--,--,--,  ,--,--,13,--,--,--,  ,--,13,--,--,--,--,  ,13,--,--,--,--,--,  ,--,--,8-,--,--,--,  ,--,8-,--,--,--,--,  ,--,--,--,--,--,0-,  ,--,--,--,--,--,3-,  ,--,--,--,--,--,5-,  ,--,--,--,--,--,0-,  ,--,--,--,--,--,3-,  ,--,--,--,--,--,6-,  ,--,--,--,--,--,5-,  ,--,--,--,--,--,0-,  ,--,--,--,--,--,3-,  ,--,--,--,--,--,5-,  ,--,--,--,--,--,3-,  ,--,--,--,--,--,1-,  ,--,--,--,--,--,0-,  ,--,--,--,--,--,--,  ,'),
	(13, 1, 1, 5, 'EADGBE', 'E ,B ,G ,D ,A ,E ,  ,|,|,|,|,|,|, ,--,--,--,--,--,0-,  ,--,--,--,--,--,12,  ,--,--,--,--,6-,--,  ,--,--,8-,--,--,--,  ,--,--,--,--,4-,--,  ,--,--,6-,--,--,--,  ,--,--,--,--,1-,--,  ,--,2-,--,--,--,--,  ,--,--,--,--,--,8-,  ,--,--,--,--,--,1-,  ,--,--,--,--,3-,--,  ,--,--,--,--,--,4-,  ,--,--,--,--,--,2-,  ,--,--,--,--,--,--,^^,'),
	(14, 1, 5, 5, 'Dorian scale', NULL),
	(17, 3, 7, 1, 'This is a project', NULL),
	(18, 1, 1, 5, 'Another filler poject', NULL),
	(19, 4, 3, 4, 'metall', NULL),
	(21, 1, 5, 5, 'A project', NULL),
	(22, 1, 1, 3, 'First not first project', NULL);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;

-- Dumping structure for table guitartab.scale
CREATE TABLE IF NOT EXISTS `scale` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(50) DEFAULT NULL,
  `s_i1` int(11) DEFAULT NULL,
  `s_i2` int(11) DEFAULT NULL,
  `s_i3` int(11) DEFAULT NULL,
  `s_i4` int(11) DEFAULT NULL,
  `s_i5` int(11) DEFAULT NULL,
  `s_i6` int(11) DEFAULT NULL,
  `s_i7` int(11) DEFAULT NULL,
  PRIMARY KEY (`s_id`),
  KEY `FK__intervals` (`s_i1`),
  KEY `FK__intervals_2` (`s_i2`),
  KEY `FK__intervals_3` (`s_i3`),
  KEY `FK__intervals_4` (`s_i4`),
  KEY `FK__intervals_5` (`s_i6`),
  KEY `FK__intervals_6` (`s_i7`),
  KEY `FK_scale_intervals` (`s_i5`),
  CONSTRAINT `FK__intervals` FOREIGN KEY (`s_i1`) REFERENCES `intervals` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__intervals_2` FOREIGN KEY (`s_i2`) REFERENCES `intervals` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__intervals_3` FOREIGN KEY (`s_i3`) REFERENCES `intervals` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__intervals_4` FOREIGN KEY (`s_i4`) REFERENCES `intervals` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__intervals_5` FOREIGN KEY (`s_i6`) REFERENCES `intervals` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__intervals_6` FOREIGN KEY (`s_i7`) REFERENCES `intervals` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_scale_intervals` FOREIGN KEY (`s_i5`) REFERENCES `intervals` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table guitartab.scale: ~7 rows (approximately)
/*!40000 ALTER TABLE `scale` DISABLE KEYS */;
INSERT INTO `scale` (`s_id`, `s_name`, `s_i1`, `s_i2`, `s_i3`, `s_i4`, `s_i5`, `s_i6`, `s_i7`) VALUES
	(1, 'Major', 1, 3, 5, 6, 8, 11, 13),
	(2, 'Natural Minor', 1, 3, 4, 6, 8, 10, 12),
	(3, 'Minor pentatonic', 1, 4, 6, 8, 12, 14, 14),
	(4, 'Argumented', 1, 3, 5, 6, 8, 10, 11),
	(5, 'Dorian mode', 1, 3, 4, 6, 8, 10, 11),
	(6, 'Harmonic major', 1, 3, 5, 6, 8, 9, 12),
	(7, 'Harmonic minor', 1, 3, 4, 6, 8, 9, 12);
/*!40000 ALTER TABLE `scale` ENABLE KEYS */;

-- Dumping structure for table guitartab.tunings
CREATE TABLE IF NOT EXISTS `tunings` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(50) DEFAULT NULL,
  `t_s1_id` int(11) DEFAULT NULL,
  `t_s2_id` int(11) DEFAULT NULL,
  `t_s3_id` int(11) DEFAULT NULL,
  `t_s4_id` int(11) DEFAULT NULL,
  `t_s5_id` int(11) DEFAULT NULL,
  `t_s6_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `FK__notes` (`t_s1_id`),
  KEY `FK__notes_2` (`t_s2_id`),
  KEY `FK_tunings_notes` (`t_s3_id`),
  KEY `FK_tunings_notes_2` (`t_s4_id`),
  KEY `FK_tunings_notes_3` (`t_s5_id`),
  KEY `FK_tunings_notes_4` (`t_s6_id`) USING BTREE,
  CONSTRAINT `FK__notes` FOREIGN KEY (`t_s1_id`) REFERENCES `notes` (`n_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__notes_2` FOREIGN KEY (`t_s2_id`) REFERENCES `notes` (`n_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tunings_notes` FOREIGN KEY (`t_s3_id`) REFERENCES `notes` (`n_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tunings_notes_2` FOREIGN KEY (`t_s4_id`) REFERENCES `notes` (`n_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tunings_notes_3` FOREIGN KEY (`t_s5_id`) REFERENCES `notes` (`n_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tunings_notes_4` FOREIGN KEY (`t_s6_id`) REFERENCES `notes` (`n_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table guitartab.tunings: ~4 rows (approximately)
/*!40000 ALTER TABLE `tunings` DISABLE KEYS */;
INSERT INTO `tunings` (`t_id`, `t_name`, `t_s1_id`, `t_s2_id`, `t_s3_id`, `t_s4_id`, `t_s5_id`, `t_s6_id`) VALUES
	(1, 'EADGBE', 32, 27, 23, 18, 13, 20),
	(2, 'FACGCE', 32, 28, 23, 16, 13, 21),
	(3, 'DEDGBE', 32, 27, 23, 18, 13, 18),
	(4, 'CGCFAD', 30, 25, 21, 28, 11, 16);
/*!40000 ALTER TABLE `tunings` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
