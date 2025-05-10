-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.7.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for guitartab
CREATE DATABASE IF NOT EXISTS `guitartab` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci */;
USE `guitartab`;

-- Dumping structure for table guitartab.intervals
CREATE TABLE IF NOT EXISTS `intervals` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_name` varchar(50) NOT NULL,
  `i_val` int(11) NOT NULL,
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Dumping data for table guitartab.intervals: ~16 rows (approximately)
INSERT INTO `intervals` (`i_id`, `i_name`, `i_val`) VALUES
	(1, 'P1', 0),
	(2, 'm2', 1),
	(3, 'M2', 2),
	(4, 'm3', 3),
	(5, 'M3', 4),
	(6, 'P4', 5),
	(7, 'TT', 6),
	(8, 'P5', 7),
	(9, 'm6', 8),
	(10, 'M6', 8),
	(11, 'M6', 9),
	(12, 'A5', 11),
	(13, 'm7', 12),
	(14, 'M7', 13),
	(15, 'P8', 14),
	(16, 'M9', 15);

-- Dumping structure for table guitartab.notes
CREATE TABLE IF NOT EXISTS `notes` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `n_name` varchar(3) DEFAULT NULL,
  `n_val` int(2) DEFAULT NULL,
  `n_oct` int(1) DEFAULT NULL,
  `n_audio` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Dumping data for table guitartab.notes: ~60 rows (approximately)
INSERT INTO `notes` (`n_id`, `n_name`, `n_val`, `n_oct`, `n_audio`) VALUES
	(1, 'A2', 1, 2, NULL),
	(2, 'A#2', 2, 2, NULL),
	(3, 'B2', 3, 2, NULL),
	(4, 'C2', 4, 2, NULL),
	(5, 'C#2', 5, 2, NULL),
	(6, 'D2', 6, 2, NULL),
	(7, 'D#2', 7, 2, NULL),
	(8, 'E2', 8, 2, NULL),
	(9, 'F2', 9, 2, NULL),
	(10, 'F#2', 10, 2, NULL),
	(11, 'G2', 11, 2, NULL),
	(12, 'G#2', 12, 2, NULL),
	(13, 'A3', 1, 3, NULL),
	(14, 'A#3', 2, 3, NULL),
	(15, 'B3', 3, 3, NULL),
	(16, 'C3', 4, 3, NULL),
	(17, 'C#3', 5, 3, NULL),
	(18, 'D3', 6, 3, NULL),
	(19, 'D#3', 7, 3, NULL),
	(20, 'E3', 8, 3, NULL),
	(21, 'F3', 9, 3, NULL),
	(22, 'F#3', 10, 3, NULL),
	(23, 'G3', 11, 3, NULL),
	(24, 'G#3', 12, 3, NULL),
	(25, 'A4', 1, 4, NULL),
	(26, 'A#4', 2, 4, NULL),
	(27, 'B4', 3, 4, NULL),
	(28, 'C4', 4, 4, NULL),
	(29, 'C#4', 5, 4, NULL),
	(30, 'D4', 6, 4, NULL),
	(31, 'D#4', 7, 4, NULL),
	(32, 'E4', 8, 4, NULL),
	(33, 'F4', 9, 4, NULL),
	(34, 'F#4', 10, 4, NULL),
	(35, 'G4', 11, 4, NULL),
	(36, 'G#4', 12, 4, NULL),
	(37, 'A5', 1, 5, NULL),
	(38, 'A#5', 2, 5, NULL),
	(39, 'B5', 3, 5, NULL),
	(40, 'C5', 4, 5, NULL),
	(41, 'C#5', 5, 5, NULL),
	(42, 'D5', 6, 5, NULL),
	(43, 'D#5', 7, 5, NULL),
	(44, 'E5', 8, 5, NULL),
	(45, 'F5', 9, 5, NULL),
	(46, 'F#5', 10, 5, NULL),
	(47, 'G5', 11, 5, NULL),
	(48, 'G#5', 12, 5, NULL),
	(49, 'A6', 1, 6, NULL),
	(50, 'A#6', 2, 6, NULL),
	(51, 'B6', 3, 6, NULL),
	(52, 'C6', 4, 6, NULL),
	(53, 'C#6', 5, 6, NULL),
	(54, 'D6', 6, 6, NULL),
	(55, 'D#6', 7, 6, NULL),
	(56, 'E6', 8, 6, NULL),
	(57, 'F6', 9, 6, NULL),
	(58, 'F#6', 10, 6, NULL),
	(59, 'G6', 11, 6, NULL),
	(60, 'G#6', 12, 6, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Dumping data for table guitartab.scale: ~1 rows (approximately)
INSERT INTO `scale` (`s_id`, `s_name`, `s_i1`, `s_i2`, `s_i3`, `s_i4`, `s_i5`, `s_i6`, `s_i7`) VALUES
	(1, 'Major', 1, 3, 5, 6, 8, 11, 12);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Dumping data for table guitartab.tunings: ~3 rows (approximately)
INSERT INTO `tunings` (`t_id`, `t_name`, `t_s1_id`, `t_s2_id`, `t_s3_id`, `t_s4_id`, `t_s5_id`, `t_s6_id`) VALUES
	(1, 'EADGBE', 32, 15, 23, 18, 1, 8),
	(2, 'FACGCE', 32, 28, 23, 16, 14, 9),
	(3, 'DEDGBE', 32, 15, 23, 18, 1, 6);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
