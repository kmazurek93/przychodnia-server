-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: przychodnia_test
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `telephone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `specialisations`
--

DROP TABLE IF EXISTS `specialisations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialisations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `window` int(10) unsigned NOT NULL,
  `happened` bit(1) DEFAULT b'0',
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
  CONSTRAINT `visits_doctors` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `visits_patients` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `visits_visits` FOREIGN KEY (`associated_visit`) REFERENCES `visits` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping events for database 'przychodnia_test'
--

--
-- Dumping routines for database 'przychodnia_test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-22  0:32:36
-- Dumping data for table `action_log`
--

LOCK TABLES `action_logs` WRITE;
/*!40000 ALTER TABLE `action_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `action_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `address`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` (id, country, province, city, street, house, apartment, postcode)
VALUES (1,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-600'),
(2,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-601'),(3,'Niemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','23','3','1233'),(4,'NIemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','12A','33','123124'),(5,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520'),(6,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520'),(7,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219'),(8,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219'),(9,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448'),(10,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448'),(11,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044'),(12,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044'),(13,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318'),(14,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318'),(15,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100'),(16,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `id_type`
--

LOCK TABLES `id_types` WRITE;
/*!40000 ALTER TABLE `id_types` DISABLE KEYS */;
INSERT INTO `id_types` (id, name) VALUES (1,'Dowód osobisty RP'),(2,'Paszport');
/*!40000 ALTER TABLE `id_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` (pesel, first_name, middle_name, last_name, birthdate, birthplace,
id_number, id_type, address_id, mailing_address_id, sex_id, telephone)
VALUES
('12345678901','Super','Ekstra','User','1990-01-18','Poznań','AAA123456',2,2,NULL,1,NULL),
('73110572051','Justyn',NULL,'Czarnecki','1973-11-05','Sosnowiec','SOJ886221',1,7,8,1,NULL),
('78062095548','Lucyna',NULL,'Kalinowska','1978-07-20','Wrocław','IJF345290',1,11,12,2,'+48884316341'),('86122267525','Zofia','','Olszewska','1986-12-22','Kielce','ACZ886332',1,5,6,2,NULL),('89022228751','Anastazy',NULL,'Kaczmarek','1989-02-22','Bydgoszcz','UJD882772',1,13,14,1,'+48677619852'),('92010610582','Klara',NULL,'Wojciechowska','1992-01-06','Gliwice','CXZ003243',1,15,16,2,'+48695573892'),('94122160151','Kacper','Jan','Kowalczyk','1994-12-21','Lublin','BDF127733',1,9,10,1,'+48882169975'),('XXXXXXXXXXX','Super',NULL,'Admin','1970-01-01','Poznań','AAA000000',1,1,NULL,1,'123456789');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (id, name) VALUES (1,'ROLE_ADMIN'),(3,'ROLE_DOCTOR'),(4,'ROLE_PATIENT'),(5,'ROLE_STAFF'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sex`
--

LOCK TABLES `sexes` WRITE;
/*!40000 ALTER TABLE `sexes` DISABLE KEYS */;
INSERT INTO `sexes` (id, name) VALUES (2,'K'),(1,'M');
/*!40000 ALTER TABLE `sexes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users`  (id, login, password,  email_address, active, pesel)
VALUES (1,'admin','JRqnd8lI1tMhnt3it8gY4w==','admin@przychodnia.com','','XXXXXXXXXXX'),(2,'user12','hWm3qKxSxTvZaz7P1gwYrg==','user@przychodnia.com','','12345678901'),(3,'user1973','O8pU7w+TimIQb4g6ZXg6mQ==','JustynCzarnecki@dayrep.com','','73110572051'),(4,'user1986','LfMVHKJd2w/tBHWAkr2slg==','Zofcia229@dayrep.com','','86122267525'),(5,'user1994','l50zIyttK1SMjz4nSkpVQg==','KacperKowalczyk@armyspy.com','','94122160151'),(6,'doctor78','806agdlV0to/NajelOi86g==','LucynaKalinowska@przychodnia.com','','78062095548'),(7,'doctor89','/W0FU3hK3p3yHYPEgxXe0A==','AnastazyKaczmarek@przychodnia.com','','89022228751'),(8,'staff92','Ja6TE0j2A4iV/IFSy3I2aQ==','KlaraWojciechowska@przychodnia.com','','92010610582');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_connection`
--

LOCK TABLES `user_connections` WRITE;
/*!40000 ALTER TABLE `user_connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_connections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (user_id, role_id) VALUES (1,1),(1,2),(1,5),(2,4),(2,2),(3,4),(3,2),(4,4),(4,2),(5,4),(5,2),(6,2),(7,2),(8,2),(8,5),(6,3),(7,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-22 17:34:05
