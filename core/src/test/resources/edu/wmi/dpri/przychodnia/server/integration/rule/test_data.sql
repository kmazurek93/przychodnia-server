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
-- Dumping data for table `action_logs`
--

LOCK TABLES `action_logs` WRITE;
/*!40000 ALTER TABLE `action_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `action_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-600',NULL,NULL,NULL,NULL,NULL,NULL),(2,'Polska','Wielkopolskie','Poznań','Umultowska','87',NULL,'61-601',NULL,NULL,NULL,NULL,NULL,NULL),(3,'Niemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','23','3','1233',NULL,NULL,NULL,NULL,NULL,NULL),(4,'NIemcy','Nordrhein-Westfallen','Hamburg','Unter den Linden','12A','33','123124',NULL,NULL,NULL,NULL,NULL,NULL),(5,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520',NULL,NULL,NULL,NULL,NULL,NULL),(6,'Polska','Świętokrzyskie','Kielce','Pocieszka','118',NULL,'25-520',NULL,NULL,NULL,NULL,NULL,NULL),(7,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219',NULL,NULL,NULL,NULL,NULL,NULL),(8,'Polska','Śląskie','Sosnowiec','Kowalskiego Piotra','82',NULL,'41-219',NULL,NULL,NULL,NULL,NULL,NULL),(9,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448',NULL,NULL,NULL,NULL,NULL,NULL),(10,'Polska','Lubelskie','Lublin','Brezy Tadeusza','140','8','20-448',NULL,NULL,NULL,NULL,NULL,NULL),(11,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044',NULL,NULL,NULL,NULL,NULL,NULL),(12,'Polska','Śląskie','Wrocław','Zielona','80','4','54-044',NULL,NULL,NULL,NULL,NULL,NULL),(13,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318',NULL,NULL,NULL,NULL,NULL,NULL),(14,'Polska','Kujawsko-Pomorskie','Bydgoszcz','Abrahama Romana','92','13d','85-318',NULL,NULL,NULL,NULL,NULL,NULL),(15,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100',NULL,NULL,NULL,NULL,NULL,NULL),(16,'Polska','Śląskie','Gliwice','Bakowa','18',NULL,'44-100',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `admission_windows`
--

LOCK TABLES `admission_windows` WRITE;
/*!40000 ALTER TABLE `admission_windows` DISABLE KEYS */;
INSERT INTO `admission_windows` (id, `hour`, `minute`)VALUES (1,7,0),(2,7,15),(3,7,30),(4,7,45),(5,8,0),(6,8,15),(7,8,30),(8,8,45),(9,9,0),(10,9,15),(11,9,30),(12,9,45),(13,10,0),(14,10,15),(15,10,30),(16,10,45),(17,11,0),(18,11,15),(19,11,30),(20,11,45),(21,12,0),(22,12,15),(23,12,30),(24,12,45),(25,13,0),(26,13,15),(27,13,30),(28,13,45),(29,14,0),(30,14,15),(31,14,30),(32,14,45),(33,15,0),(34,15,15),(35,15,30),(36,15,45),(37,16,0),(38,16,15),(39,16,30),(40,16,45);
/*!40000 ALTER TABLE `admission_windows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `availabilities`
--

LOCK TABLES `availabilities` WRITE;
/*!40000 ALTER TABLE `availabilities` DISABLE KEYS */;
INSERT INTO `availabilities` VALUES (1,1,1,16,'2016-01-01','2017-12-31'),(2,1,21,36,'2016-01-01','2017-12-31'),(3,2,1,16,'2016-01-01','2017-12-31'),(4,2,21,36,'2016-01-01','2017-12-31'),(5,3,1,16,'2016-01-01','2017-12-31'),(6,3,21,36,'2016-01-01','2017-12-31'),(7,4,1,16,'2016-01-01','2017-12-31'),(8,4,21,36,'2016-01-01','2017-12-31'),(9,5,1,16,'2016-01-01','2017-12-31'),(10,5,21,36,'2016-01-01','2017-12-31'),(11,6,1,48,'2016-01-01','2017-12-31'),(12,7,1,48,'2016-01-01','2017-12-31');
/*!40000 ALTER TABLE `availabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `doctors_availabilities`
--

LOCK TABLES `doctors_availabilities` WRITE;
/*!40000 ALTER TABLE `doctors_availabilities` DISABLE KEYS */;
INSERT INTO `doctors_availabilities` VALUES (1,1,1),(2,1,4),(3,1,5),(4,1,7),(5,1,9),(6,2,2),(7,2,3),(8,2,6),(9,2,8),(10,2,10);
/*!40000 ALTER TABLE `doctors_availabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `doctors_specialisations`
--

LOCK TABLES `doctors_specialisations` WRITE;
/*!40000 ALTER TABLE `doctors_specialisations` DISABLE KEYS */;
INSERT INTO `doctors_specialisations` VALUES (1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(2,2,1,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `doctors_specialisations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'78062095548'),(2,'89022228751'),(3,'92010610582'),(4,'XXXXXXXXXXX');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hires`
--

LOCK TABLES `hires` WRITE;
/*!40000 ALTER TABLE `hires` DISABLE KEYS */;
INSERT INTO `hires` VALUES (1,4,'2016-01-01','2040-12-31',1,8000.00,NULL),(2,3,'2016-01-01','2020-12-31',2,3000.00,1),(3,1,'2016-01-01','2030-12-31',4,5000.00,1),(4,2,'2016-01-01','2030-12-31',4,5000.00,1);
/*!40000 ALTER TABLE `hires` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `id_types`
--

LOCK TABLES `id_types` WRITE;
/*!40000 ALTER TABLE `id_types` DISABLE KEYS */;
INSERT INTO `id_types` VALUES (1,'Dowód osobisty RP'),(2,'Paszport');
/*!40000 ALTER TABLE `id_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'12345678901',NULL,NULL,NULL,NULL,NULL,NULL),(2,'73110572051',NULL,NULL,NULL,NULL,NULL,NULL),(3,'86122267525',NULL,NULL,NULL,NULL,NULL,NULL),(4,'94122160151',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES ('12345678901','Super','Ekstra','User','1990-01-18','Poznań','AAA123456',2,2,NULL,1,'','\0',NULL,NULL,NULL,NULL,NULL,NULL),('73110572051','Justyn',NULL,'Czarnecki','1973-11-05','Sosnowiec','SOJ886221',1,7,8,1,'','\0',NULL,NULL,NULL,NULL,NULL,NULL),('78062095548','Lucyna',NULL,'Kalinowska','1978-07-20','Wrocław','IJF345290',1,11,12,2,'+48884316341','\0',NULL,NULL,NULL,NULL,NULL,NULL),('86122267525','Zofia','','Olszewska','1986-12-22','Kielce','ACZ886332',1,5,6,2,'','\0',NULL,NULL,NULL,NULL,NULL,NULL),('89022228751','Anastazy',NULL,'Kaczmarek','1989-02-22','Bydgoszcz','UJD882772',1,13,14,1,'+48677619852','\0',NULL,NULL,NULL,NULL,NULL,NULL),('92010610582','Klara',NULL,'Wojciechowska','1992-01-06','Gliwice','CXZ003243',1,15,16,2,'+48695573892','\0',NULL,NULL,NULL,NULL,NULL,NULL),('94122160151','Kacper','Jan','Kowalczyk','1994-12-21','Lublin','BDF127733',1,9,10,1,'+48882169975','\0',NULL,NULL,NULL,NULL,NULL,NULL),('XXXXXXXXXXX','Super',NULL,'Admin','1970-01-01','Poznań','AAA000000',1,1,NULL,1,'123456789','\0',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
INSERT INTO `positions` VALUES (1,'administrator',NULL,NULL,NULL,NULL,NULL,NULL),(2,'rejestrator',NULL,NULL,NULL,NULL,NULL,NULL),(3,'dyrektor',NULL,NULL,NULL,NULL,NULL,NULL),(4,'lekarz',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',NULL,NULL,NULL,NULL,NULL,NULL,''),(2,'ROLE_USER',NULL,NULL,NULL,NULL,NULL,NULL,''),(3,'ROLE_DOCTOR',NULL,NULL,NULL,NULL,NULL,NULL,''),(4,'ROLE_PATIENT',NULL,NULL,NULL,NULL,NULL,NULL,''),(5,'ROLE_STAFF',NULL,NULL,NULL,NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sexes`
--

LOCK TABLES `sexes` WRITE;
/*!40000 ALTER TABLE `sexes` DISABLE KEYS */;
INSERT INTO `sexes` VALUES (2,'K'),(1,'M');
/*!40000 ALTER TABLE `sexes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `specialisations`
--

LOCK TABLES `specialisations` WRITE;
/*!40000 ALTER TABLE `specialisations` DISABLE KEYS */;
INSERT INTO `specialisations` VALUES (1,'pediatra');
/*!40000 ALTER TABLE `specialisations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `time_windows`
--

LOCK TABLES `time_windows` WRITE;
/*!40000 ALTER TABLE `time_windows` DISABLE KEYS */;
INSERT INTO `time_windows` VALUES (1,0,'08:00:00','08:15:00'),(2,1,'08:15:00','08:15:00'),(3,2,'08:30:00','08:15:00'),(4,3,'08:45:00','09:00:00'),(5,4,'09:00:00','09:15:00'),(6,5,'09:15:00','09:30:00'),(7,6,'09:30:00','09:45:00'),(8,7,'09:45:00','10:00:00'),(9,8,'10:00:00','10:15:00'),(10,9,'10:15:00','10:30:00'),(11,10,'10:30:00','10:45:00'),(12,11,'10:45:00','11:00:00'),(13,12,'11:00:00','11:15:00'),(14,13,'11:15:00','11:30:00'),(15,14,'11:30:00','11:45:00'),(16,15,'11:45:00','12:00:00'),(17,16,'12:00:00','12:15:00'),(18,17,'12:15:00','12:30:00'),(19,18,'12:30:00','12:45:00'),(20,19,'12:45:00','13:00:00'),(21,20,'13:00:00','13:15:00'),(22,21,'13:15:00','13:30:00'),(23,22,'13:30:00','13:45:00'),(24,23,'13:45:00','14:00:00'),(25,24,'14:00:00','14:15:00'),(26,25,'14:15:00','14:30:00'),(27,26,'14:30:00','14:45:00'),(28,27,'14:45:00','15:00:00'),(29,28,'15:00:00','15:15:00'),(30,29,'15:15:00','15:30:00'),(31,30,'15:30:00','15:45:00'),(32,31,'15:45:00','16:00:00'),(33,32,'16:00:00','16:15:00'),(34,33,'16:15:00','16:30:00'),(35,34,'16:30:00','16:45:00'),(36,35,'16:45:00','17:00:00'),(37,36,'17:00:00','17:15:00'),(38,37,'17:15:00','17:30:00'),(39,38,'17:30:00','17:45:00'),(40,39,'17:45:00','18:00:00'),(41,40,'18:00:00','18:15:00'),(42,41,'18:15:00','18:30:00'),(43,42,'18:30:00','18:45:00'),(44,43,'18:45:00','19:00:00'),(45,44,'19:00:00','19:15:00'),(46,45,'19:15:00','19:30:00'),(47,46,'19:30:00','19:45:00'),(48,47,'19:45:00','20:00:00');
/*!40000 ALTER TABLE `time_windows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_connections`
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
INSERT INTO `user_roles` VALUES (1,1,1),(1,2,2),(1,5,3),(2,4,4),(2,2,5),(3,4,6),(3,2,7),(4,4,8),(4,2,9),(5,4,10),(5,2,11),(6,2,12),(7,2,13),(8,2,14),(8,5,15),(6,3,16),(7,3,17);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','JRqnd8lI1tMhnt3it8gY4w==','admin@przychodnia.com','','XXXXXXXXXXX',NULL,NULL,NULL,NULL,NULL,NULL),(2,'user12','hWm3qKxSxTvZaz7P1gwYrg==','user@przychodnia.com','','12345678901',NULL,NULL,NULL,NULL,NULL,NULL),(3,'user1973','O8pU7w+TimIQb4g6ZXg6mQ==','JustynCzarnecki@dayrep.com','','73110572051',NULL,NULL,NULL,NULL,NULL,NULL),(4,'user1986','LfMVHKJd2w/tBHWAkr2slg==','Zofcia229@dayrep.com','','86122267525',NULL,NULL,NULL,NULL,NULL,NULL),(5,'user1994','l50zIyttK1SMjz4nSkpVQg==','KacperKowalczyk@armyspy.com','','94122160151',NULL,NULL,NULL,NULL,NULL,NULL),(6,'doctor78','806agdlV0to/NajelOi86g==','LucynaKalinowska@przychodnia.com','','78062095548',NULL,NULL,NULL,NULL,NULL,NULL),(7,'doctor89','/W0FU3hK3p3yHYPEgxXe0A==','AnastazyKaczmarek@przychodnia.com','','89022228751',NULL,NULL,NULL,NULL,NULL,NULL),(8,'staff92','Ja6TE0j2A4iV/IFSy3I2aQ==','KlaraWojciechowska@przychodnia.com','','92010610582',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `visits`
--

LOCK TABLES `visits` WRITE;
/*!40000 ALTER TABLE `visits` DISABLE KEYS */;
INSERT INTO `visits` VALUES (1,2,1,'2016-12-13',22,1,NULL,'Nieżyt nosa',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `visits` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-31 12:03:38
