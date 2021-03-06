-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: przychodnia_test
-- ------------------------------------------------------
-- Server version	5.7.16-10

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
-- Table structure for table `action_logs`
--

DROP TABLE IF EXISTS `action_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_logs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `entity_id` varchar(30) CHARACTER SET utf8 NOT NULL,
  `entity_type` int(11) NOT NULL,
  `action_type` int(11) NOT NULL,
  `action_date` datetime(6) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `action_logs_fk1_idx` (`user_id`),
  KEY `action_logs_users2_idx` (`created_by`),
  CONSTRAINT `action_logs_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `action_logs_users2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_logs`
--

LOCK TABLES `action_logs` WRITE;
/*!40000 ALTER TABLE `action_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `action_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(50) CHARACTER SET utf8 NOT NULL,
  `province` varchar(50) CHARACTER SET utf8 NOT NULL,
  `city` varchar(75) CHARACTER SET utf8 NOT NULL,
  `street` varchar(100) CHARACTER SET utf8 NOT NULL,
  `house` varchar(10) CHARACTER SET utf8 NOT NULL,
  `apartment` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `postcode` varchar(15) CHARACTER SET utf8 NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-600',NULL,NULL,NULL,NULL,NULL,NULL),(2,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-601',NULL,NULL,NULL,NULL,NULL,NULL),(3,'Niemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','23','3','1233',NULL,NULL,NULL,NULL,NULL,NULL),(4,'NIemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','12A','33','123124',NULL,NULL,NULL,NULL,NULL,NULL),(5,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520',NULL,NULL,NULL,NULL,NULL,NULL),(6,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520',NULL,NULL,NULL,NULL,NULL,NULL),(7,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219',NULL,NULL,NULL,NULL,NULL,NULL),(8,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219',NULL,NULL,NULL,NULL,NULL,NULL),(9,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448',NULL,NULL,NULL,NULL,NULL,NULL),(10,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448',NULL,NULL,NULL,NULL,NULL,NULL),(11,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044',NULL,NULL,NULL,NULL,NULL,NULL),(12,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044',NULL,NULL,NULL,NULL,NULL,NULL),(13,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318',NULL,NULL,NULL,NULL,NULL,NULL),(14,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318',NULL,NULL,NULL,NULL,NULL,NULL),(15,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100',NULL,NULL,NULL,NULL,NULL,NULL),(16,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `availabilities`
--

DROP TABLE IF EXISTS `availabilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `availabilities` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `weekday` int(11) NOT NULL,
  `start_time_window` bigint(20) unsigned NOT NULL,
  `end_time_window` bigint(20) unsigned NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `availabilities_time_windows1_idx` (`start_time_window`),
  KEY `availabilities_time_windows2_idx` (`end_time_window`),
  CONSTRAINT `availabilities_time_windows1` FOREIGN KEY (`start_time_window`) REFERENCES `time_windows` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `availabilities_time_windows2` FOREIGN KEY (`end_time_window`) REFERENCES `time_windows` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availabilities`
--

LOCK TABLES `availabilities` WRITE;
/*!40000 ALTER TABLE `availabilities` DISABLE KEYS */;
INSERT INTO `availabilities` VALUES (1,1,1,16,'2016-01-01','2017-12-31'),(2,1,21,36,'2016-01-01','2017-12-31'),(3,2,1,16,'2016-01-01','2017-12-31'),(4,2,21,36,'2016-01-01','2017-12-31'),(5,3,1,16,'2016-01-01','2017-12-31'),(6,3,21,36,'2016-01-01','2017-12-31'),(7,4,1,16,'2016-01-01','2017-12-31'),(8,4,21,36,'2016-01-01','2017-12-31'),(9,5,1,16,'2016-01-01','2017-12-31'),(10,5,21,36,'2016-01-01','2017-12-31'),(11,6,1,48,'2016-01-01','2017-12-31'),(12,7,1,48,'2016-01-01','2017-12-31');
/*!40000 ALTER TABLE `availabilities` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`przychodnia_test`@`localhost`*/ /*!50003 TRIGGER `przychodnia_test`.`availabilities_BEFORE_INSERT` BEFORE INSERT ON `availabilities` FOR EACH ROW
  BEGIN
    IF NEW.WEEKDAY not in(
      1,
      2,
      3,
      4,
      5,
      6,
      7) then
      SIGNAL SQLSTATE '12345'
      SET MESSAGE_TEXT = 'check constraint on Availabilities.WEEKDAY failed';
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`przychodnia_test`@`localhost`*/ /*!50003 TRIGGER `przychodnia_test`.`availabilities_BEFORE_UPDATE` BEFORE UPDATE ON `availabilities` FOR EACH ROW
  BEGIN
    IF NEW.WEEKDAY not in(
      1,
      2,
      3,
      4,
      5,
      6,
      7) then
      SIGNAL SQLSTATE '12345'
      SET MESSAGE_TEXT = 'check constraint on Availabilities.WEEKDAY failed';
    END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctors` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doctors_employees_idx` (`employee_id`),
  CONSTRAINT `doctors_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors_availabilities`
--

DROP TABLE IF EXISTS `doctors_availabilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctors_availabilities` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint(20) unsigned NOT NULL,
  `availability_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doctors_availabilities_doctors_idx` (`doctor_id`),
  KEY `doctors_availabilities_availabilities_idx` (`availability_id`),
  CONSTRAINT `doctors_availabilities_availabilities` FOREIGN KEY (`availability_id`) REFERENCES `availabilities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `doctors_availabilities_doctors` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors_availabilities`
--

LOCK TABLES `doctors_availabilities` WRITE;
/*!40000 ALTER TABLE `doctors_availabilities` DISABLE KEYS */;
INSERT INTO `doctors_availabilities` VALUES (1,1,1),(2,1,4),(3,1,5),(4,1,7),(5,1,9),(6,2,2),(7,2,3),(8,2,6),(9,2,8),(10,2,10);
/*!40000 ALTER TABLE `doctors_availabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors_specialisations`
--

DROP TABLE IF EXISTS `doctors_specialisations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctors_specialisations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint(20) unsigned NOT NULL,
  `specialisation_id` bigint(20) unsigned NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `doctors_specialisations_doctors_idx` (`doctor_id`),
  KEY `doctors_specialisations_specialisations_idx` (`specialisation_id`),
  CONSTRAINT `doctors_specialisations_doctors` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `doctors_specialisations_specialisations` FOREIGN KEY (`specialisation_id`) REFERENCES `specialisations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors_specialisations`
--

LOCK TABLES `doctors_specialisations` WRITE;
/*!40000 ALTER TABLE `doctors_specialisations` DISABLE KEYS */;
INSERT INTO `doctors_specialisations` VALUES (1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(2,2,1,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `doctors_specialisations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pesel` varchar(11) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employees_persons_idx` (`pesel`),
  CONSTRAINT `employees_persons` FOREIGN KEY (`pesel`) REFERENCES `persons` (`pesel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'78062095548'),(2,'89022228751'),(3,'92010610582'),(4,'XXXXXXXXXXX');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hires`
--

DROP TABLE IF EXISTS `hires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hires` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) unsigned NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date DEFAULT NULL,
  `position_id` bigint(20) unsigned NOT NULL,
  `salary` decimal(13,2) NOT NULL,
  `supervisor` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hires_positions_idx` (`position_id`),
  KEY `hires_employees_idx` (`employee_id`),
  KEY `hires_employees2_idx` (`supervisor`),
  CONSTRAINT `hires_employees1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `hires_employees2` FOREIGN KEY (`supervisor`) REFERENCES `employees` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `hires_positions` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hires`
--

LOCK TABLES `hires` WRITE;
/*!40000 ALTER TABLE `hires` DISABLE KEYS */;
INSERT INTO `hires` VALUES (1,4,'2016-01-01','2040-12-31',1,8000.00,NULL),(2,3,'2016-01-01','2020-12-31',2,3000.00,1),(3,1,'2016-01-01','2030-12-31',4,5000.00,1),(4,2,'2016-01-01','2030-12-31',4,5000.00,1);
/*!40000 ALTER TABLE `hires` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_types`
--

DROP TABLE IF EXISTS `id_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_types` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_types`
--

LOCK TABLES `id_types` WRITE;
/*!40000 ALTER TABLE `id_types` DISABLE KEYS */;
INSERT INTO `id_types` VALUES (1,'Dowód osobisty RP'),(2,'Paszport');
/*!40000 ALTER TABLE `id_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pesel` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `patients_persons_idx` (`pesel`),
  CONSTRAINT `patients_persons` FOREIGN KEY (`pesel`) REFERENCES `persons` (`pesel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'12345678901',NULL,NULL,NULL,NULL,NULL,NULL),(2,'73110572051',NULL,NULL,NULL,NULL,NULL,NULL),(3,'86122267525',NULL,NULL,NULL,NULL,NULL,NULL),(4,'94122160151',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `pesel` varchar(11) CHARACTER SET utf8 NOT NULL,
  `first_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `middle_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `last_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `birthdate` date NOT NULL,
  `birthplace` varchar(100) CHARACTER SET utf8 NOT NULL,
  `id_number` varchar(40) CHARACTER SET utf8 NOT NULL,
  `id_type` bigint(20) unsigned NOT NULL,
  `address_id` bigint(20) unsigned NOT NULL,
  `mailing_address_id` bigint(20) unsigned DEFAULT NULL,
  `sex_id` bigint(20) unsigned NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 NOT NULL,
  `dead` bit(1) DEFAULT b'0',
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`pesel`),
  KEY `persons_addresses_idx` (`address_id`),
  KEY `persons_addresses2_idx` (`mailing_address_id`),
  KEY `persons_sexes_idx` (`sex_id`),
  CONSTRAINT `persons_addresses1` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `persons_addresses2` FOREIGN KEY (`mailing_address_id`) REFERENCES `addresses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `persons_id_types` FOREIGN KEY (`sex_id`) REFERENCES `id_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `persons_sexes` FOREIGN KEY (`sex_id`) REFERENCES `sexes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES ('12345678901','Super','Ekstra','User','1990-01-18','Poznań','AAA123456',2,2,NULL,1,'','\0',NULL,NULL,NULL,NULL,NULL,NULL),('73110572051','Justyn',NULL,'Czarnecki','1973-11-05','Sosnowiec','SOJ886221',1,7,8,1,'','\0',NULL,NULL,NULL,NULL,NULL,NULL),('78062095548','Lucyna',NULL,'Kalinowska','1978-07-20','Wrocław','IJF345290',1,11,12,2,'+48884316341','\0',NULL,NULL,NULL,NULL,NULL,NULL),('86122267525','Zofia','','Olszewska','1986-12-22','Kielce','ACZ886332',1,5,6,2,'','\0',NULL,NULL,NULL,NULL,NULL,NULL),('89022228751','Anastazy',NULL,'Kaczmarek','1989-02-22','Bydgoszcz','UJD882772',1,13,14,1,'+48677619852','\0',NULL,NULL,NULL,NULL,NULL,NULL),('92010610582','Klara',NULL,'Wojciechowska','1992-01-06','Gliwice','CXZ003243',1,15,16,2,'+48695573892','\0',NULL,NULL,NULL,NULL,NULL,NULL),('94122160151','Kacper','Jan','Kowalczyk','1994-12-21','Lublin','BDF127733',1,9,10,1,'+48882169975','\0',NULL,NULL,NULL,NULL,NULL,NULL),('XXXXXXXXXXX','Super',NULL,'Admin','1970-01-01','Poznań','AAA000000',1,1,NULL,1,'123456789','\0',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `positions` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
INSERT INTO `positions` VALUES (1,'administrator',NULL,NULL,NULL,NULL,NULL,NULL),(2,'rejestrator',NULL,NULL,NULL,NULL,NULL,NULL),(3,'dyrektor',NULL,NULL,NULL,NULL,NULL,NULL),(4,'lekarz',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',NULL,NULL,NULL,NULL,NULL,NULL,''),(2,'ROLE_USER',NULL,NULL,NULL,NULL,NULL,NULL,''),(3,'ROLE_DOCTOR',NULL,NULL,NULL,NULL,NULL,NULL,''),(4,'ROLE_PATIENT',NULL,NULL,NULL,NULL,NULL,NULL,''),(5,'ROLE_STAFF',NULL,NULL,NULL,NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sexes`
--

DROP TABLE IF EXISTS `sexes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sexes` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sexes`
--

LOCK TABLES `sexes` WRITE;
/*!40000 ALTER TABLE `sexes` DISABLE KEYS */;
INSERT INTO `sexes` VALUES (2,'K'),(1,'M');
/*!40000 ALTER TABLE `sexes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialisations`
--

DROP TABLE IF EXISTS `specialisations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialisations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialisations`
--

LOCK TABLES `specialisations` WRITE;
/*!40000 ALTER TABLE `specialisations` DISABLE KEYS */;
INSERT INTO `specialisations` VALUES (1,'pediatra');
/*!40000 ALTER TABLE `specialisations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_windows`
--

DROP TABLE IF EXISTS `time_windows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_windows` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order` int(11) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_UNIQUE` (`order`),
  UNIQUE KEY `time_unique` (`start_time`,`end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_windows`
--

LOCK TABLES `time_windows` WRITE;
/*!40000 ALTER TABLE `time_windows` DISABLE KEYS */;
INSERT INTO `time_windows` VALUES (1,0,'0000-00-00 08:00:00.000000','0000-00-00 08:15:00.000000'),(2,1,'0000-00-00 08:15:00.000000','0000-00-00 08:15:00.000000'),(3,2,'0000-00-00 08:30:00.000000','0000-00-00 08:15:00.000000'),(4,3,'0000-00-00 08:45:00.000000','0000-00-00 09:00:00.000000'),(5,4,'0000-00-00 09:00:00.000000','0000-00-00 09:15:00.000000'),(6,5,'0000-00-00 09:15:00.000000','0000-00-00 09:30:00.000000'),(7,6,'0000-00-00 09:30:00.000000','0000-00-00 09:45:00.000000'),(8,7,'0000-00-00 09:45:00.000000','0000-00-00 10:00:00.000000'),(9,8,'0000-00-00 10:00:00.000000','0000-00-00 10:15:00.000000'),(10,9,'0000-00-00 10:15:00.000000','0000-00-00 10:30:00.000000'),(11,10,'0000-00-00 10:30:00.000000','0000-00-00 10:45:00.000000'),(12,11,'0000-00-00 10:45:00.000000','0000-00-00 11:00:00.000000'),(13,12,'0000-00-00 11:00:00.000000','0000-00-00 11:15:00.000000'),(14,13,'0000-00-00 11:15:00.000000','0000-00-00 11:30:00.000000'),(15,14,'0000-00-00 11:30:00.000000','0000-00-00 11:45:00.000000'),(16,15,'0000-00-00 11:45:00.000000','0000-00-00 12:00:00.000000'),(17,16,'0000-00-00 12:00:00.000000','0000-00-00 12:15:00.000000'),(18,17,'0000-00-00 12:15:00.000000','0000-00-00 12:30:00.000000'),(19,18,'0000-00-00 12:30:00.000000','0000-00-00 12:45:00.000000'),(20,19,'0000-00-00 12:45:00.000000','0000-00-00 13:00:00.000000'),(21,20,'0000-00-00 13:00:00.000000','0000-00-00 13:15:00.000000'),(22,21,'0000-00-00 13:15:00.000000','0000-00-00 13:30:00.000000'),(23,22,'0000-00-00 13:30:00.000000','0000-00-00 13:45:00.000000'),(24,23,'0000-00-00 13:45:00.000000','0000-00-00 14:00:00.000000'),(25,24,'0000-00-00 14:00:00.000000','0000-00-00 14:15:00.000000'),(26,25,'0000-00-00 14:15:00.000000','0000-00-00 14:30:00.000000'),(27,26,'0000-00-00 14:30:00.000000','0000-00-00 14:45:00.000000'),(28,27,'0000-00-00 14:45:00.000000','0000-00-00 15:00:00.000000'),(29,28,'0000-00-00 15:00:00.000000','0000-00-00 15:15:00.000000'),(30,29,'0000-00-00 15:15:00.000000','0000-00-00 15:30:00.000000'),(31,30,'0000-00-00 15:30:00.000000','0000-00-00 15:45:00.000000'),(32,31,'0000-00-00 15:45:00.000000','0000-00-00 16:00:00.000000'),(33,32,'0000-00-00 16:00:00.000000','0000-00-00 16:15:00.000000'),(34,33,'0000-00-00 16:15:00.000000','0000-00-00 16:30:00.000000'),(35,34,'0000-00-00 16:30:00.000000','0000-00-00 16:45:00.000000'),(36,35,'0000-00-00 16:45:00.000000','0000-00-00 17:00:00.000000'),(37,36,'0000-00-00 17:00:00.000000','0000-00-00 17:15:00.000000'),(38,37,'0000-00-00 17:15:00.000000','0000-00-00 17:30:00.000000'),(39,38,'0000-00-00 17:30:00.000000','0000-00-00 17:45:00.000000'),(40,39,'0000-00-00 17:45:00.000000','0000-00-00 18:00:00.000000'),(41,40,'0000-00-00 18:00:00.000000','0000-00-00 18:15:00.000000'),(42,41,'0000-00-00 18:15:00.000000','0000-00-00 18:30:00.000000'),(43,42,'0000-00-00 18:30:00.000000','0000-00-00 18:45:00.000000'),(44,43,'0000-00-00 18:45:00.000000','0000-00-00 19:00:00.000000'),(45,44,'0000-00-00 19:00:00.000000','0000-00-00 19:15:00.000000'),(46,45,'0000-00-00 19:15:00.000000','0000-00-00 19:30:00.000000'),(47,46,'0000-00-00 19:30:00.000000','0000-00-00 19:45:00.000000'),(48,47,'0000-00-00 19:45:00.000000','0000-00-00 20:00:00.000000');
/*!40000 ALTER TABLE `time_windows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_connections`
--

DROP TABLE IF EXISTS `user_connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_connections` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) unsigned NOT NULL,
  `child_id` bigint(20) unsigned NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cn_parent_id` (`parent_id`),
  KEY `user_connectuions_user2_idx` (`child_id`),
  CONSTRAINT `user_connections_users1` FOREIGN KEY (`parent_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_connectuions_user2` FOREIGN KEY (`child_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_connections`
--

LOCK TABLES `user_connections` WRITE;
/*!40000 ALTER TABLE `user_connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_connections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `user_roles_users_idx` (`user_id`),
  KEY `user_roles_roles_idx` (`role_id`),
  CONSTRAINT `user_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_roles_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1,1),(1,2,2),(1,5,3),(2,4,4),(2,2,5),(3,4,6),(3,2,7),(4,4,8),(4,2,9),(5,4,10),(5,2,11),(6,2,12),(7,2,13),(8,2,14),(8,5,15),(6,3,16),(7,3,17);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(50) CHARACTER SET utf8 NOT NULL,
  `password` varchar(1000) CHARACTER SET utf8 NOT NULL,
  `email_address` varchar(100) CHARACTER SET utf8 NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0',
  `pesel` varchar(11) CHARACTER SET utf8 NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email_address` (`email_address`),
  KEY `fk_u_pesel` (`pesel`),
  CONSTRAINT `fk_u_pesel` FOREIGN KEY (`pesel`) REFERENCES `persons` (`pesel`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','JRqnd8lI1tMhnt3it8gY4w==','admin@przychodnia.com','','XXXXXXXXXXX',NULL,NULL,NULL,NULL,NULL,NULL),(2,'user12','hWm3qKxSxTvZaz7P1gwYrg==','user@przychodnia.com','','12345678901',NULL,NULL,NULL,NULL,NULL,NULL),(3,'user1973','O8pU7w+TimIQb4g6ZXg6mQ==','JustynCzarnecki@dayrep.com','','73110572051',NULL,NULL,NULL,NULL,NULL,NULL),(4,'user1986','LfMVHKJd2w/tBHWAkr2slg==','Zofcia229@dayrep.com','','86122267525',NULL,NULL,NULL,NULL,NULL,NULL),(5,'user1994','l50zIyttK1SMjz4nSkpVQg==','KacperKowalczyk@armyspy.com','','94122160151',NULL,NULL,NULL,NULL,NULL,NULL),(6,'doctor78','806agdlV0to/NajelOi86g==','LucynaKalinowska@przychodnia.com','','78062095548',NULL,NULL,NULL,NULL,NULL,NULL),(7,'doctor89','/W0FU3hK3p3yHYPEgxXe0A==','AnastazyKaczmarek@przychodnia.com','','89022228751',NULL,NULL,NULL,NULL,NULL,NULL),(8,'staff92','Ja6TE0j2A4iV/IFSy3I2aQ==','KlaraWojciechowska@przychodnia.com','','92010610582',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_employees_data`
--

DROP TABLE IF EXISTS `v_employees_data`;
/*!50001 DROP VIEW IF EXISTS `v_employees_data`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_employees_data` AS SELECT
                                             1 AS `id`,
                                             1 AS `login`,
                                             1 AS `email_address`,
                                             1 AS `pesel`,
                                             1 AS `user_id`,
                                             1 AS `name`,
                                             1 AS `address`,
                                             1 AS `mailing_address`,
                                             1 AS `telephone`,
                                             1 AS `doctor_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_patients_data`
--

DROP TABLE IF EXISTS `v_patients_data`;
/*!50001 DROP VIEW IF EXISTS `v_patients_data`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_patients_data` AS SELECT
                                            1 AS `id`,
                                            1 AS `login`,
                                            1 AS `email_address`,
                                            1 AS `pesel`,
                                            1 AS `user_id`,
                                            1 AS `name`,
                                            1 AS `address`,
                                            1 AS `mailing_address`,
                                            1 AS `telephone`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_users_data`
--

DROP TABLE IF EXISTS `v_users_data`;
/*!50001 DROP VIEW IF EXISTS `v_users_data`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_users_data` AS SELECT
                                         1 AS `id`,
                                         1 AS `login`,
                                         1 AS `email_address`,
                                         1 AS `pesel`,
                                         1 AS `user_id`,
                                         1 AS `name`,
                                         1 AS `address`,
                                         1 AS `mailing_address`,
                                         1 AS `telephone`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `visits`
--

DROP TABLE IF EXISTS `visits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visits` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `patient_id` bigint(20) unsigned NOT NULL,
  `doctor_id` bigint(20) unsigned NOT NULL,
  `date` date NOT NULL,
  `time_window_id` bigint(20) unsigned NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `associated_visit` bigint(20) unsigned DEFAULT NULL,
  `comment` longtext COLLATE utf8_polish_ci,
  `created_at` datetime(6) DEFAULT NULL,
  `dreated_by` bigint(20) unsigned DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `deleted_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `visits_patients_idx` (`patient_id`),
  KEY `visits_doctors_idx` (`doctor_id`),
  KEY `visits_visits_idx` (`associated_visit`),
  KEY `visits_time_windows_idx` (`time_window_id`),
  CONSTRAINT `visits_doctors` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `visits_patients` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `visits_time_windows` FOREIGN KEY (`time_window_id`) REFERENCES `time_windows` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `visits_visits` FOREIGN KEY (`associated_visit`) REFERENCES `visits` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visits`
--

LOCK TABLES `visits` WRITE;
/*!40000 ALTER TABLE `visits` DISABLE KEYS */;
/*!40000 ALTER TABLE `visits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'przychodnia_test'
--
/*!50003 DROP FUNCTION IF EXISTS `func_inc_var_session` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`przychodnia_test`@`localhost` FUNCTION `func_inc_var_session`() RETURNS int(11)
  begin
    SET @var := IFNULL(@var,0) + 1;
    return @var;
  end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `doctor_calendar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`przychodnia_test`@`localhost` PROCEDURE `doctor_calendar`(doctor_id BIGINT, date_start DATE, date_end DATE)
  BEGIN
    SELECT
      DATE_ADD(date_start, INTERVAL c.number DAY) AS DATE
    FROM
      (
        SELECT
          singles + tens + hundreds+thousands number
        FROM
          (
            SELECT 0 singles
            UNION ALL SELECT   1 UNION ALL SELECT   2 UNION ALL SELECT   3
            UNION ALL SELECT   4 UNION ALL SELECT   5 UNION ALL SELECT   6
            UNION ALL SELECT   7 UNION ALL SELECT   8 UNION ALL SELECT   9
          ) singles JOIN
          (
            SELECT 0 tens
            UNION ALL SELECT  10 UNION ALL SELECT  20 UNION ALL SELECT  30
            UNION ALL SELECT  40 UNION ALL SELECT  50 UNION ALL SELECT  60
            UNION ALL SELECT  70 UNION ALL SELECT  80 UNION ALL SELECT  90
          ) tens  JOIN
          (
            SELECT 0 hundreds
            UNION ALL SELECT  100 UNION ALL SELECT  200 UNION ALL SELECT  300
            UNION ALL SELECT  400 UNION ALL SELECT  500 UNION ALL SELECT  600
            UNION ALL SELECT  700 UNION ALL SELECT  800 UNION ALL SELECT  900
          ) hundreds
          JOIN
          (
            SELECT 0 thousands
            UNION ALL SELECT  1000 UNION ALL SELECT  2000 UNION ALL SELECT  3000
            UNION ALL SELECT  4000 UNION ALL SELECT  5000 UNION ALL SELECT  6000
            UNION ALL SELECT  7000 UNION ALL SELECT  8000 UNION ALL SELECT  9000
          ) thousands
        ORDER BY number DESC
      ) c
    WHERE
      c.number BETWEEN
      0 AND
      DATEDIFF(date_end, date_start);
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `v_employees_data`
--

/*!50001 DROP VIEW IF EXISTS `v_employees_data`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
  /*!50013 DEFINER=`przychodnia_test`@`localhost` SQL SECURITY DEFINER */
  /*!50001 VIEW `v_employees_data` AS select `e`.`id` AS `id`,`u`.`login` AS `login`,`u`.`email_address` AS `email_address`,`u`.`pesel` AS `pesel`,`u`.`id` AS `user_id`,concat(`pe`.`first_name`,' ',`pe`.`last_name`) AS `name`,concat(`a`.`street`,' ',`a`.`house`,if(isnull(`a`.`apartment`),'',concat('/',`a`.`apartment`)),' ',`a`.`postcode`,' ',`a`.`city`,' ',coalesce(`a`.`province`,''),' ',`a`.`country`) AS `address`,concat(`ma`.`street`,' ',`ma`.`house`,if(isnull(`ma`.`apartment`),'',concat('/',`ma`.`apartment`)),' ',`ma`.`postcode`,' ',`ma`.`city`,' ',coalesce(`ma`.`province`,''),' ',`ma`.`country`) AS `mailing_address`,`pe`.`telephone` AS `telephone`,`d`.`id` AS `doctor_id` from (((((`employees` `e` left join `users` `u` on((`u`.`pesel` = `e`.`pesel`))) left join `persons` `pe` on((`pe`.`pesel` = `e`.`pesel`))) left join `addresses` `a` on((`a`.`id` = `pe`.`address_id`))) left join `addresses` `ma` on((`ma`.`id` = `pe`.`mailing_address_id`))) left join `doctors` `d` on((`e`.`id` = `d`.`employee_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_patients_data`
--

/*!50001 DROP VIEW IF EXISTS `v_patients_data`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
  /*!50013 DEFINER=`przychodnia_test`@`localhost` SQL SECURITY DEFINER */
  /*!50001 VIEW `v_patients_data` AS select `pa`.`id` AS `id`,`u`.`login` AS `login`,`u`.`email_address` AS `email_address`,`u`.`pesel` AS `pesel`,`u`.`id` AS `user_id`,concat(`pe`.`first_name`,' ',`pe`.`last_name`) AS `name`,concat(`a`.`street`,' ',`a`.`house`,if(isnull(`a`.`apartment`),'',concat('/',`a`.`apartment`)),' ',`a`.`postcode`,' ',`a`.`city`,' ',coalesce(`a`.`province`,''),' ',`a`.`country`) AS `address`,concat(`ma`.`street`,' ',`ma`.`house`,if(isnull(`ma`.`apartment`),'',concat('/',`ma`.`apartment`)),' ',`ma`.`postcode`,' ',`ma`.`city`,' ',coalesce(`ma`.`province`,''),' ',`ma`.`country`) AS `mailing_address`,`pe`.`telephone` AS `telephone` from ((((`patients` `pa` left join `users` `u` on((`u`.`pesel` = `pa`.`pesel`))) left join `persons` `pe` on((`pe`.`pesel` = `pa`.`pesel`))) left join `addresses` `a` on((`a`.`id` = `pe`.`address_id`))) left join `addresses` `ma` on((`ma`.`id` = `pe`.`mailing_address_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_users_data`
--

/*!50001 DROP VIEW IF EXISTS `v_users_data`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
  /*!50013 DEFINER=`przychodnia_test`@`localhost` SQL SECURITY DEFINER */
  /*!50001 VIEW `v_users_data` AS select `u`.`id` AS `id`,`u`.`login` AS `login`,`u`.`email_address` AS `email_address`,`u`.`pesel` AS `pesel`,`u`.`id` AS `user_id`,concat(`pe`.`first_name`,' ',`pe`.`last_name`) AS `name`,concat(`a`.`street`,' ',`a`.`house`,if(isnull(`a`.`apartment`),'',concat('/',`a`.`apartment`)),' ',`a`.`postcode`,' ',`a`.`city`,' ',coalesce(`a`.`province`,''),' ',`a`.`country`) AS `address`,concat(`ma`.`street`,' ',`ma`.`house`,if(isnull(`ma`.`apartment`),'',concat('/',`ma`.`apartment`)),' ',`ma`.`postcode`,' ',`ma`.`city`,' ',coalesce(`ma`.`province`,''),' ',`ma`.`country`) AS `mailing_address`,`pe`.`telephone` AS `telephone` from (((`users` `u` left join `persons` `pe` on((`pe`.`pesel` = `u`.`pesel`))) left join `addresses` `a` on((`a`.`id` = `pe`.`address_id`))) left join `addresses` `ma` on((`ma`.`id` = `pe`.`mailing_address_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-17 12:40:50
