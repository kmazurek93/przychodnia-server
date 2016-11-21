DROP USER IF EXISTS przychodnia;
;
DROP DATABASE IF EXISTS `przychodnia`;
;
CREATE DATABASE IF NOT EXISTS `przychodnia` DEFAULT CHARACTER SET = 'utf8' DEFAULT COLLATE = 'utf8_polish_ci';
;
CREATE USER IF NOT EXISTS 'przychodnia'@'localhost' IDENTIFIED BY 'przychodnia' PASSWORD EXPIRE NEVER;
;
USE przychodnia;
;
GRANT ALL PRIVILEGES ON `przychodnia`.* TO 'przychodnia'@'%' WITH GRANT OPTION;
;

DROP TABLE IF EXISTS `action_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `entity_id` varchar(30) CHARACTER SET utf8 NOT NULL,
  `entity_type` int(11) NOT NULL,
  `action_type` int(11) NOT NULL,
  `action_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_log`
--

LOCK TABLES `action_log` WRITE;
/*!40000 ALTER TABLE `action_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `action_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country` varchar(50) CHARACTER SET utf8 NOT NULL,
  `province` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `city` varchar(75) CHARACTER SET utf8 NOT NULL,
  `street` varchar(100) CHARACTER SET utf8 NOT NULL,
  `house` varchar(8) CHARACTER SET utf8 NOT NULL,
  `apartment` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `postcode` varchar(15) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-600'),(2,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-601'),(3,'Niemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','23','3','1233'),(4,'NIemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','12A','33','123124'),(5,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520'),(6,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520'),(7,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219'),(8,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219'),(9,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448'),(10,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448'),(11,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044'),(12,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044'),(13,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318'),(14,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318'),(15,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100'),(16,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_type`
--

DROP TABLE IF EXISTS `id_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_type`
--

LOCK TABLES `id_type` WRITE;
/*!40000 ALTER TABLE `id_type` DISABLE KEYS */;
INSERT INTO `id_type` VALUES (1,'Dowód osobisty RP'),(2,'Paszport');
/*!40000 ALTER TABLE `id_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `pesel` varchar(11) CHARACTER SET utf8 NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `middle_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `last_name` varchar(75) CHARACTER SET utf8 NOT NULL,
  `birth_date` date NOT NULL,
  `birth_place` varchar(100) CHARACTER SET utf8 NOT NULL,
  `id_number` varchar(40) CHARACTER SET utf8 NOT NULL,
  `id_type` int(11) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `mailing_address_id` bigint(20) DEFAULT NULL,
  `sex_id` int(11) NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`pesel`),
  KEY `fk_p_adr_id` (`address_id`),
  KEY `fk_p_madr_id` (`mailing_address_id`),
  KEY `fk_p_sex_id` (`sex_id`),
  KEY `fk_p_idt_id` (`id_type`),
  CONSTRAINT `fk_p_adr_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_p_idt_id` FOREIGN KEY (`id_type`) REFERENCES `id_type` (`id`),
  CONSTRAINT `fk_p_madr_id` FOREIGN KEY (`mailing_address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_p_sex_id` FOREIGN KEY (`sex_id`) REFERENCES `sex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('12345678901','Super','Ekstra','User','1990-01-18','Poznań','AAA123456',2,2,NULL,1,NULL),('73110572051','Justyn',NULL,'Czarnecki','1973-11-05','Sosnowiec','SOJ886221',1,7,8,1,NULL),('78062095548','Lucyna',NULL,'Kalinowska','1978-07-20','Wrocław','IJF345290',1,11,12,2,'+48884316341'),('86122267525','Zofia','','Olszewska','1986-12-22','Kielce','ACZ886332',1,5,6,2,NULL),('89022228751','Anastazy',NULL,'Kaczmarek','1989-02-22','Bydgoszcz','UJD882772',1,13,14,1,'+48677619852'),('92010610582','Klara',NULL,'Wojciechowska','1992-01-06','Gliwice','CXZ003243',1,15,16,2,'+48695573892'),('94122160151','Kacper','Jan','Kowalczyk','1994-12-21','Lublin','BDF127733',1,9,10,1,'+48882169975'),('XXXXXXXXXXX','Super',NULL,'Admin','1970-01-01','Poznań','AAA000000',1,1,NULL,1,'123456789');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_DOCTOR'),(4,'ROLE_PATIENT'),(5,'ROLE_STAFF'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sex`
--

DROP TABLE IF EXISTS `sex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sex` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sex`
--

LOCK TABLES `sex` WRITE;
/*!40000 ALTER TABLE `sex` DISABLE KEYS */;
INSERT INTO `sex` VALUES (2,'K'),(1,'M');
/*!40000 ALTER TABLE `sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) CHARACTER SET utf8 NOT NULL,
  `password` varchar(1000) CHARACTER SET utf8 NOT NULL,
  `email_address` varchar(100) CHARACTER SET utf8 NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0',
  `pesel` varchar(11) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email_address` (`email_address`),
  KEY `fk_u_pesel` (`pesel`),
  CONSTRAINT `fk_u_pesel` FOREIGN KEY (`pesel`) REFERENCES `person` (`pesel`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','JRqnd8lI1tMhnt3it8gY4w==','admin@przychodnia.com','','XXXXXXXXXXX'),(2,'user12','hWm3qKxSxTvZaz7P1gwYrg==','user@przychodnia.com','','12345678901'),(3,'user1973','O8pU7w+TimIQb4g6ZXg6mQ==','JustynCzarnecki@dayrep.com','','73110572051'),(4,'user1986','LfMVHKJd2w/tBHWAkr2slg==','Zofcia229@dayrep.com','','86122267525'),(5,'user1994','l50zIyttK1SMjz4nSkpVQg==','KacperKowalczyk@armyspy.com','','94122160151'),(6,'doctor78','806agdlV0to/NajelOi86g==','LucynaKalinowska@przychodnia.com','','78062095548'),(7,'doctor89','/W0FU3hK3p3yHYPEgxXe0A==','AnastazyKaczmarek@przychodnia.com','','89022228751'),(8,'staff92','Ja6TE0j2A4iV/IFSy3I2aQ==','KlaraWojciechowska@przychodnia.com','','92010610582');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_connection`
--

DROP TABLE IF EXISTS `user_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_connection` (
  `parent_id` bigint(20) DEFAULT NULL,
  `child_id` bigint(20) DEFAULT NULL,
  KEY `fk_cn_parent_id` (`parent_id`),
  KEY `fk_cn_child_id` (`child_id`),
  CONSTRAINT `fk_cn_child_id` FOREIGN KEY (`child_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_cn_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_connection`
--

LOCK TABLES `user_connection` WRITE;
/*!40000 ALTER TABLE `user_connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  KEY `fk_ur_user_id` (`user_id`),
  KEY `fk_ur_role_id` (`role_id`),
  CONSTRAINT `fk_ur_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_ur_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(1,2),(1,5),(2,4),(2,2),(3,4),(3,2),(4,4),(4,2),(5,4),(5,2),(6,2),(7,2),(8,2),(8,5),(6,3),(7,3);
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