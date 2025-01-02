-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: localhost    Database: service_station_db
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `service_station_db`
--

/*!40000 DROP DATABASE IF EXISTS `service_station_db`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `service_station_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `service_station_db`;

--
-- Table structure for table `access_role`
--

DROP TABLE IF EXISTS `access_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_role`
--

LOCK TABLES `access_role` WRITE;
/*!40000 ALTER TABLE `access_role` DISABLE KEYS */;
INSERT INTO `access_role` VALUES (1,'Admin'),(2,'Moderator'),(3,'HR-Account'),(4,'Cashier');
/*!40000 ALTER TABLE `access_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(20) DEFAULT NULL,
  `supplier_id` varchar(20) DEFAULT NULL,
  `lane1` varchar(45) NOT NULL,
  `lane2` varchar(45) NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_employee1_idx` (`employee_id`),
  KEY `fk_address_supplier1_idx` (`supplier_id`),
  KEY `fk_address_city1_idx` (`city_id`),
  CONSTRAINT `fk_address_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_address_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_address_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,NULL,'SUP01','Club Rd','Maradhana',1),(2,NULL,'SUP02','D.S Senannayaka St','null',2),(3,NULL,'SUP03','Molamurea Lane','Pilimathalawa',6),(4,NULL,'SUP04','Colombo Road','null',19),(5,NULL,'SUP05','Cross Street','Rajagiriya',1),(6,NULL,'SUP06','Mahawila Road','Dehiwala',3),(7,NULL,'SUP07','Yatinuwara Lane','',6),(8,NULL,'SUP08','Kalugalla Road','Maradana',1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` varchar(20) NOT NULL,
  `vehicle_number` varchar(20) NOT NULL,
  `services_id` int NOT NULL,
  `date` datetime NOT NULL,
  `note` text NOT NULL,
  `appointment_status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_appointment_vehicle1_idx` (`vehicle_number`),
  KEY `fk_appointment_services1_idx` (`services_id`),
  KEY `fk_appointment_appointment_status1_idx` (`appointment_status_id`),
  CONSTRAINT `fk_appointment_appointment_status1` FOREIGN KEY (`appointment_status_id`) REFERENCES `appointment_status` (`id`),
  CONSTRAINT `fk_appointment_services1` FOREIGN KEY (`services_id`) REFERENCES `services` (`id`),
  CONSTRAINT `fk_appointment_vehicle1` FOREIGN KEY (`vehicle_number`) REFERENCES `vehicle` (`vehicle_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES ('AP144635','KX-5791',1,'2024-12-18 00:00:00','-',1),('AP197708','KX-5791',3,'2024-12-18 00:00:00','-',1),('AP248892','KA-5891',3,'2024-12-06 00:00:00','Clean the tyres with tyre shiners',2),('AP299424','KA-5891',3,'2024-12-08 00:00:00','clean interior',2),('AP329893','KA-5891',1,'2024-12-06 00:00:00','Full body wash,Interior Clean,Oil Change',3),('AP361642','ND-3690',16,'2024-12-18 00:00:00','-',1),('AP376194','CBA-3256',1,'2024-12-17 00:00:00','Check the automatic gearbox',2),('AP377101','BBA-4109',2,'2024-12-06 00:00:00','Engine Oil,Brake Oil change',1),('AP424702','AAB-2580',17,'2024-12-10 00:00:00','Engine head should be repaired',3),('AP454197','KX-5791',1,'2024-12-06 00:00:00','Interior clean,oil change',2),('AP470912','KH-5922',3,'2024-12-17 00:00:00','Interior cleaning is also applied',3),('AP499265','52-5701',11,'2024-12-17 00:00:00','Check the transfer case',1),('AP551543','BBA-4109',2,'2024-12-10 00:00:00','Good condition',1),('AP583294','BBA-4109',7,'2024-12-11 00:00:00','-',1),('AP631137','PD-3568',10,'2024-12-19 00:00:00','sceduled',1);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_status`
--

DROP TABLE IF EXISTS `appointment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_status`
--

LOCK TABLES `appointment_status` WRITE;
/*!40000 ALTER TABLE `appointment_status` DISABLE KEYS */;
INSERT INTO `appointment_status` VALUES (1,'Pending'),(2,'Ongoing'),(3,'Closed');
/*!40000 ALTER TABLE `appointment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_date`
--

DROP TABLE IF EXISTS `attendance_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_date` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_date`
--

LOCK TABLES `attendance_date` WRITE;
/*!40000 ALTER TABLE `attendance_date` DISABLE KEYS */;
INSERT INTO `attendance_date` VALUES (15,'2024-12-06'),(16,'2024-12-05'),(17,'2024-12-08'),(18,'2024-12-09'),(19,'2024-12-10'),(20,'2024-12-07'),(21,'2024-12-11'),(22,'2024-12-16'),(23,'2024-12-17'),(24,'2024-12-18'),(25,'2024-12-20'),(26,'2024-12-25'),(27,'2024-12-29'),(28,'2025-01-02');
/*!40000 ALTER TABLE `attendance_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_status`
--

DROP TABLE IF EXISTS `attendance_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_status`
--

LOCK TABLES `attendance_status` WRITE;
/*!40000 ALTER TABLE `attendance_status` DISABLE KEYS */;
INSERT INTO `attendance_status` VALUES (1,'Present'),(2,'Absent');
/*!40000 ALTER TABLE `attendance_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `postalcode` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Colombo','00100'),(2,'Sri Jayawardenepura Kotte	','10100'),(3,'Dehiwala-Mount Lavinia	','10350'),(4,'Moratuwa	','10400'),(5,'Negombo','11500'),(6,'Kandy','20000'),(7,'Kalmunai','40000'),(8,'Vavuniya','43000'),(9,'Galle','80000'),(10,'Trincomalee','31000'),(11,'Jaffna','40000'),(12,'Matara','81000'),(13,'Batticaloa','30000'),(14,'Anuradhapura','50000'),(15,'Ratnapura','70000'),(16,'Nuwara Eliya','22200'),(17,'Hambantota','82000'),(18,'Puttalam','61000'),(19,'Kurunegala','60000'),(20,'Dambulla','21100');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `registered_date` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Ruwan','Fernando','0773489924','2024-12-16 11:49:28','ruwan@gmail.com'),(2,'Dinusha','Weerasinghe','0714589355','2024-12-16 11:49:37','dinu002@gmail.com'),(3,'Kusal','Dissanayaka','0729512968','2024-12-16 11:49:47','kusal@gmail.com'),(4,'Amara','Jayasinghe','0751350971','2024-12-16 11:49:56','amara123@gmail.com'),(5,'Tharanga','Gunawardena','0773479136','2024-12-16 11:50:04','redminote9s2nd@gmail.com'),(6,'Kasun','Silva','0815010182','2024-12-10 21:44:50','kasun@gmail.com'),(7,'Kumudini','Rathnayake','0714910839','2024-12-17 00:15:55','kumu@gmail.com'),(8,'Pabitha','Liyanage','0719410818','2024-12-17 00:16:43','liyan12@gmail.com'),(9,'Pabitha','Hemanta','0771048108','2024-12-17 00:18:11','ph1234@gmail.com'),(10,'Saman','Amarasinghe','0774521984','2024-12-17 00:18:54','saman@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drive_types`
--

DROP TABLE IF EXISTS `drive_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drive_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drive_types`
--

LOCK TABLES `drive_types` WRITE;
/*!40000 ALTER TABLE `drive_types` DISABLE KEYS */;
INSERT INTO `drive_types` VALUES (1,'Front Wheel Drive'),(2,'Rear Wheel Drive'),(3,'All Wheel Drive'),(4,'Four Wheel Drive');
/*!40000 ALTER TABLE `drive_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_attendance`
--

DROP TABLE IF EXISTS `emp_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_attendance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(20) NOT NULL,
  `checkin` time DEFAULT NULL,
  `checkout` time DEFAULT NULL,
  `attendance_date_id` int NOT NULL,
  `attendance_status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emp_attendance_employee1_idx` (`employee_id`),
  KEY `fk_emp_attendance_attendance_date1_idx` (`attendance_date_id`),
  KEY `fk_emp_attendance_attendance_status1_idx` (`attendance_status_id`),
  CONSTRAINT `fk_emp_attendance_attendance_date1` FOREIGN KEY (`attendance_date_id`) REFERENCES `attendance_date` (`id`),
  CONSTRAINT `fk_emp_attendance_attendance_status1` FOREIGN KEY (`attendance_status_id`) REFERENCES `attendance_status` (`id`),
  CONSTRAINT `fk_emp_attendance_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_attendance`
--

LOCK TABLES `emp_attendance` WRITE;
/*!40000 ALTER TABLE `emp_attendance` DISABLE KEYS */;
INSERT INTO `emp_attendance` VALUES (1,'EMP01',NULL,NULL,17,2),(2,'EMP02','22:57:32','22:58:52',17,1),(3,'EMP03',NULL,NULL,17,2),(4,'EMP04',NULL,NULL,17,2),(5,'EMP05',NULL,NULL,17,2),(6,'EMP06',NULL,NULL,17,2),(7,'EMP01',NULL,NULL,18,2),(8,'EMP02','10:36:15','10:36:21',18,1),(9,'EMP03','10:35:32','10:35:37',18,1),(10,'EMP04','10:36:29','10:36:33',18,1),(11,'EMP05',NULL,NULL,18,2),(12,'EMP06',NULL,NULL,18,2),(13,'EMP01',NULL,NULL,19,2),(14,'EMP02','10:38:00','10:38:05',19,1),(15,'EMP03','10:38:10','10:38:15',19,1),(16,'EMP04','10:38:23','10:38:27',19,1),(17,'EMP05',NULL,NULL,19,2),(18,'EMP06','10:38:32','10:38:35',19,1),(19,'EMP01',NULL,NULL,20,2),(20,'EMP02',NULL,NULL,20,2),(21,'EMP03',NULL,NULL,20,2),(22,'EMP04',NULL,NULL,20,2),(23,'EMP05',NULL,NULL,20,2),(24,'EMP06',NULL,NULL,20,2),(25,'EMP01',NULL,NULL,21,2),(26,'EMP02',NULL,NULL,21,2),(27,'EMP03',NULL,NULL,21,2),(28,'EMP04',NULL,NULL,21,2),(29,'EMP05',NULL,NULL,21,2),(30,'EMP06',NULL,NULL,21,2),(31,'EMP01','11:44:49','11:45:10',22,1),(32,'EMP02','11:45:28','11:45:35',22,1),(33,'EMP03','11:45:58',NULL,22,1),(34,'EMP04','11:46:08',NULL,22,1),(35,'EMP05','11:46:16',NULL,22,1),(36,'EMP06',NULL,NULL,22,2),(37,'EMP01','00:19:37','08:41:02',23,1),(38,'EMP02','00:19:40','08:41:07',23,1),(39,'EMP03','00:19:42','08:40:55',23,1),(40,'EMP04','00:19:45','08:41:11',23,1),(41,'EMP05','00:19:47','08:41:14',23,1),(42,'EMP06','00:19:49','08:41:17',23,1),(43,'EMP07','00:19:50','08:41:19',23,1),(44,'EMP01','09:29:16','09:29:21',24,1),(45,'EMP02','09:29:25','09:29:30',24,1),(46,'EMP03','09:29:34','09:29:37',24,1),(47,'EMP04','09:29:46','09:29:50',24,1),(48,'EMP05','11:03:34','11:03:38',24,1),(49,'EMP06','09:29:54','09:29:58',24,1),(50,'EMP07',NULL,NULL,24,2),(51,'EMP01','13:51:43','18:53:16',25,1),(52,'EMP02','13:52:44','20:53:13',25,1),(53,'EMP03','13:51:55','19:52:48',25,1),(54,'EMP04','13:52:00','19:52:51',25,1),(55,'EMP05','13:52:55','15:52:59',25,1),(56,'EMP06','13:52:05','19:53:02',25,1),(57,'EMP07','13:53:06','15:53:09',25,1),(58,'EMP01','20:12:21',NULL,26,1),(59,'EMP02',NULL,NULL,26,2),(60,'EMP03',NULL,NULL,26,2),(61,'EMP04',NULL,NULL,26,2),(62,'EMP05',NULL,NULL,26,2),(63,'EMP06',NULL,NULL,26,2),(64,'EMP07',NULL,NULL,26,2),(65,'EMP01',NULL,NULL,27,2),(66,'EMP02',NULL,NULL,27,2),(67,'EMP03',NULL,NULL,27,2),(68,'EMP04',NULL,NULL,27,2),(69,'EMP05',NULL,NULL,27,2),(70,'EMP06',NULL,NULL,27,2),(71,'EMP07',NULL,NULL,27,2),(72,'EMP01',NULL,NULL,28,2),(73,'EMP02',NULL,NULL,28,2),(74,'EMP03',NULL,NULL,28,2),(75,'EMP04',NULL,NULL,28,2),(76,'EMP05',NULL,NULL,28,2),(77,'EMP06',NULL,NULL,28,2),(78,'EMP07',NULL,NULL,28,2);
/*!40000 ALTER TABLE `emp_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` varchar(20) NOT NULL,
  `nic` varchar(12) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `registered_date` datetime NOT NULL,
  `employee_type_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_status1_idx` (`status_id`),
  KEY `fk_employee_employee_type1_idx` (`employee_type_id`),
  CONSTRAINT `fk_employee_employee_type1` FOREIGN KEY (`employee_type_id`) REFERENCES `employee_type` (`id`),
  CONSTRAINT `fk_employee_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('EMP01','200107117014','Dumidu','Sankalpa','dumidu@gmail.com','0713581380','2024-12-06 23:30:56',1,1),('EMP02','200400147913','Nimsara','Dayananda','nimsara@gmail.com','0714914028','2024-12-06 23:31:05',3,1),('EMP03','200260158201','Dinuka','Dilshan','Dilshan@gmail.com','0769914601','2024-12-06 23:31:12',5,1),('EMP04','200407117074','Kavindu','Udara','dumidu@gmail.com','0714810571','2024-12-06 23:31:20',2,1),('EMP05','200204618374','Harshana','Bandara','bandara123@gmail.com','0729149735','2024-12-06 23:47:22',6,1),('EMP06','200208510918','Nipuni','Samartha','nipuni@gmail.com','0716891409','2024-12-08 19:52:45',3,2),('EMP07','200409157421','Hanan','Ilma','ilma123@gmail.com','0710915815','2024-12-16 11:47:59',5,2);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_image`
--

DROP TABLE IF EXISTS `employee_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `path` varchar(100) NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_image_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_employee_image_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_image`
--

LOCK TABLES `employee_image` WRITE;
/*!40000 ALTER TABLE `employee_image` DISABLE KEYS */;
INSERT INTO `employee_image` VALUES (3,'EMP06200208510918NipuniSamartha.jpg','EMP06'),(4,'EMP01200107117014DumiduSankalpa.jpg','EMP01'),(5,'EMP05200204618374HarshanaBandara.jpg','EMP05'),(6,'EMP04200407117074KavinduUdara.jpg','EMP04');
/*!40000 ALTER TABLE `employee_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_salary`
--

DROP TABLE IF EXISTS `employee_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_salary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `employee_id` varchar(20) NOT NULL,
  `months_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_salary_employee1_idx` (`employee_id`),
  KEY `fk_employee_salary_months1_idx` (`months_id`),
  CONSTRAINT `fk_employee_salary_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_employee_salary_months1` FOREIGN KEY (`months_id`) REFERENCES `months` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_salary`
--

LOCK TABLES `employee_salary` WRITE;
/*!40000 ALTER TABLE `employee_salary` DISABLE KEYS */;
INSERT INTO `employee_salary` VALUES (5,'2024-12-11',27000,'EMP02',12),(6,'2024-12-11',53000,'EMP03',12),(7,'2024-12-17',75000,'EMP01',4),(16,'2024-12-21',75000,'EMP01',1),(17,'2024-12-21',75000,'EMP01',1),(18,'2024-12-21',75000,'EMP01',1),(20,'2024-12-21',75000,'EMP01',2),(21,'2024-12-21',75000,'EMP01',7),(22,'2024-12-23',25000,'EMP02',1),(23,'2024-12-23',51000,'EMP07',1),(24,'2024-12-23',35000,'EMP05',1),(25,'2024-12-25',50100,'EMP03',11);
/*!40000 ALTER TABLE `employee_salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type`
--

DROP TABLE IF EXISTS `employee_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `basic_salary` double NOT NULL,
  `leaves_for_month` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_type`
--

LOCK TABLES `employee_type` WRITE;
/*!40000 ALTER TABLE `employee_type` DISABLE KEYS */;
INSERT INTO `employee_type` VALUES (1,'Manager',75000,4),(2,'HR Officer',60000,4),(3,'Receptionist',25000,4),(4,'Billing Officer',40000,4),(5,'Supervisor',50000,4),(6,'Technician',35000,4);
/*!40000 ALTER TABLE `employee_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn`
--

DROP TABLE IF EXISTS `grn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grn` (
  `id` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `paid_amount` double NOT NULL,
  `supplier_id` varchar(20) NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_supplier1_idx` (`supplier_id`),
  KEY `fk_grn_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_grn_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_grn_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn`
--

LOCK TABLES `grn` WRITE;
/*!40000 ALTER TABLE `grn` DISABLE KEYS */;
INSERT INTO `grn` VALUES ('','2024-12-10 23:15:31',110000,'SUP03','EMP01'),('GRN075606','2024-12-10 23:22:21',8500,'SUP07','EMP01'),('GRN141089','2024-12-11 23:23:55',35000,'SUP06','EMP01'),('GRN235410','2024-12-11 23:25:54',9500,'SUP03','EMP01'),('GRN354616','2024-12-12 23:26:51',37500,'SUP02','EMP01'),('GRN411910','2024-12-15 23:28:02',187500,'SUP03','EMP01'),('GRN541338','2024-12-20 15:29:29',40000,'SUP03','EMP01'),('GRN664179','2024-12-20 15:13:02',30000,'SUP05','EMP01'),('GRN732074','2024-12-18 23:21:15',75000,'SUP01','EMP01');
/*!40000 ALTER TABLE `grn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn_items`
--

DROP TABLE IF EXISTS `grn_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grn_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grn_id` varchar(20) NOT NULL,
  `qty` double NOT NULL,
  `price` double NOT NULL,
  `stock_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_items_grn1_idx` (`grn_id`),
  KEY `fk_grn_items_stock1_idx` (`stock_id`),
  CONSTRAINT `fk_grn_items_grn1` FOREIGN KEY (`grn_id`) REFERENCES `grn` (`id`),
  CONSTRAINT `fk_grn_items_stock1` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn_items`
--

LOCK TABLES `grn_items` WRITE;
/*!40000 ALTER TABLE `grn_items` DISABLE KEYS */;
INSERT INTO `grn_items` VALUES (8,'',10,10800,1),(9,'GRN732074',6,12200,2),(10,'GRN075606',15,560,3),(11,'GRN141089',2,17500,4),(12,'GRN235410',5,1899,5),(13,'GRN354616',6,6250,6),(14,'GRN411910',15,12500,7),(15,'GRN664179',3,8500,8),(16,'GRN541338',2,19000,9);
/*!40000 ALTER TABLE `grn_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `access_role_id` int NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_login_access_role_idx` (`access_role_id`),
  KEY `fk_login_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_login_access_role` FOREIGN KEY (`access_role_id`) REFERENCES `access_role` (`id`),
  CONSTRAINT `fk_login_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('EMP01Dumidu','Dumidu@1234',1,'EMP01'),('EMP04Kavindu','Kavindu@123',3,'EMP04');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_history`
--

DROP TABLE IF EXISTS `login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_history`
--

LOCK TABLES `login_history` WRITE;
/*!40000 ALTER TABLE `login_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `months`
--

DROP TABLE IF EXISTS `months`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `months` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `months`
--

LOCK TABLES `months` WRITE;
/*!40000 ALTER TABLE `months` DISABLE KEYS */;
INSERT INTO `months` VALUES (1,'January'),(2,'February'),(3,'March'),(4,'April'),(5,'May'),(6,'June'),(7,'July'),(8,'August'),(9,'September'),(10,'October'),(11,'November'),(12,'December');
/*!40000 ALTER TABLE `months` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Cash'),(2,'Card');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `powertrain_types`
--

DROP TABLE IF EXISTS `powertrain_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `powertrain_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `powertrain_types`
--

LOCK TABLES `powertrain_types` WRITE;
/*!40000 ALTER TABLE `powertrain_types` DISABLE KEYS */;
INSERT INTO `powertrain_types` VALUES (1,'Patrol'),(2,'Diesel'),(3,'Electric'),(4,'Hybrid-Patrol'),(5,'Hybrid-Diesel');
/*!40000 ALTER TABLE `powertrain_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` varchar(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `brand_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_product_brand1_idx` (`brand_id`),
  CONSTRAINT `fk_product_product_brand1` FOREIGN KEY (`brand_id`) REFERENCES `product_brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('ITEM01','Toyota CHR 2GB Ram XY  Android Player',4),('ITEM010','TW FRONT HEAD LIGHT (GENUINE)',6),('ITEM011','Mobile Delvac Super 1400 15W-40 5 Liter',18),('ITEM012','Suzuki wagon R 44s Fender',17),('ITEM013','Nissan X-Trail Hybrid Filter Package',9),('ITEM014','Toyota Tarnsmission Gear Oil Filter',4),('ITEM015','BMW 520d Gas Shock Absorbers (full set)',8),('ITEM02','31/10.5/R15 TYRES M/T',3),('ITEM03','195/65 R15 MICHELIN ITALY TYRES ',5),('ITEM04','CT-100 CLUTCH CABLE',7),('ITEM05','10W-40 Havoline Patrol Engine Oil',8),('ITEM06','Mazda Demio Front Bumper',16),('ITEM07','1000AMP Car Jump ( Booster Cable)',1),('ITEM08','AX-1 TIMING CHAIN',2),('ITEM09','5-SPEED MANUAL GEARBOX',4);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_brand`
--

DROP TABLE IF EXISTS `product_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_brand`
--

LOCK TABLES `product_brand` WRITE;
/*!40000 ALTER TABLE `product_brand` DISABLE KEYS */;
INSERT INTO `product_brand` VALUES (1,'Unbranded'),(2,'Honda'),(3,'Toyo'),(4,'Toyota'),(5,'Michelin'),(6,'Yamaha'),(7,'Bajaj Auto'),(8,'BMW'),(9,'Nissan'),(10,'Mitshubishi'),(11,'NGK'),(12,'DSI'),(14,'Delo'),(15,'Havoline'),(16,'Mazda'),(17,'Suzuki'),(18,'Mobil');
/*!40000 ALTER TABLE `product_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_history`
--

DROP TABLE IF EXISTS `service_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `service_invoice_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_history_service_invoice1_idx` (`service_invoice_id`),
  CONSTRAINT `fk_service_history_service_invoice1` FOREIGN KEY (`service_invoice_id`) REFERENCES `service_invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_history`
--

LOCK TABLES `service_history` WRITE;
/*!40000 ALTER TABLE `service_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_invoice`
--

DROP TABLE IF EXISTS `service_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_invoice` (
  `id` varchar(20) NOT NULL,
  `vehicle_number` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `total` double NOT NULL,
  `paid_amount` double NOT NULL,
  `balance` double NOT NULL,
  `payment_method_id` int NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  `odometer` varchar(10) NOT NULL,
  `next_service` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_invoice_payment_method1_idx` (`payment_method_id`),
  KEY `fk_service_invoice_vehicle1_idx` (`vehicle_number`),
  KEY `fk_service_invoice_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_service_invoice_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_service_invoice_payment_method1` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `fk_service_invoice_vehicle1` FOREIGN KEY (`vehicle_number`) REFERENCES `vehicle` (`vehicle_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_invoice`
--

LOCK TABLES `service_invoice` WRITE;
/*!40000 ALTER TABLE `service_invoice` DISABLE KEYS */;
INSERT INTO `service_invoice` VALUES ('INV-12088137','PD-3568','2024-12-17 10:40:07',22000,22000,0,2,'EMP01','163789','171789'),('INV-68963163','PD-3568','2024-12-08 20:13:16',22000,22000,0,1,'EMP01','315000','323000'),('INV-77734715','28-6813','2024-12-09 22:39:13',40000,40000,0,2,'EMP01','27000','37000'),('INV-87654003','BBA-4109','2024-12-11 08:58:41',7500,7500,0,2,'EMP01','20000','24000'),('INV-87757617','BBA-4109','2024-12-21 08:59:46',5000,5000,0,1,'EMP01','26000','30000'),('INV-87885748','CAB-1507','2024-12-11 09:01:51',750,750,0,2,'EMP01','--------','--------');
/*!40000 ALTER TABLE `service_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_invoice_items`
--

DROP TABLE IF EXISTS `service_invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_invoice_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `service_invoice_id` varchar(20) NOT NULL,
  `services_id` int NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_invoice_items_services1_idx` (`services_id`),
  KEY `fk_service_invoice_items_service_invoice1_idx` (`service_invoice_id`),
  CONSTRAINT `fk_service_invoice_items_service_invoice1` FOREIGN KEY (`service_invoice_id`) REFERENCES `service_invoice` (`id`),
  CONSTRAINT `fk_service_invoice_items_services1` FOREIGN KEY (`services_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_invoice_items`
--

LOCK TABLES `service_invoice_items` WRITE;
/*!40000 ALTER TABLE `service_invoice_items` DISABLE KEYS */;
INSERT INTO `service_invoice_items` VALUES (6,'INV-68963163',10,'good'),(7,'INV-77734715',8,'good'),(8,'INV-87654003',7,'-'),(9,'INV-87654003',2,'-'),(10,'INV-87757617',7,'-'),(11,'INV-87885748',3,'-'),(12,'INV-12088137',10,'Engine Oil chaged \nTranfer case oil changed Fuel filter changed');
/*!40000 ALTER TABLE `service_invoice_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `vehicle_type_id` int NOT NULL,
  `charge` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_vehicle_type1_idx` (`vehicle_type_id`),
  CONSTRAINT `fk_service_vehicle_type1` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Full Service',2,15000),(2,'Oil Change',1,2500),(3,'Car Wash',2,750),(4,'Alignement Balance',6,3500),(5,'Interior Cleaning',8,1500),(6,'Oil change',2,8500),(7,'Full Service',1,5000),(8,'Full Service',3,40000),(9,'Oil Change',3,27800),(10,'Full Service',4,22000),(11,'Oil Change',4,15000),(12,'Full Service',6,24000),(13,'Oil Change',6,15000),(14,'Full Service',7,7500),(15,'Oil Change',7,3500),(16,'Full Service',8,52000),(17,'Oil Change',8,37000),(18,'EFI Tuning',1,3000);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_invoice`
--

DROP TABLE IF EXISTS `shop_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_invoice` (
  `id` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `total` double NOT NULL,
  `paid_amount` double NOT NULL,
  `balance` double NOT NULL,
  `payment_method_id` int NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shop_invoice_employee1_idx` (`employee_id`),
  KEY `fk_shop_invoice_payment_method1_idx` (`payment_method_id`),
  CONSTRAINT `fk_shop_invoice_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_shop_invoice_payment_method1` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_invoice`
--

LOCK TABLES `shop_invoice` WRITE;
/*!40000 ALTER TABLE `shop_invoice` DISABLE KEYS */;
INSERT INTO `shop_invoice` VALUES ('SINV-69036438','2024-12-01 20:14:14',550000,550000,0,1,'EMP01'),('SINV-69133122','2024-12-08 20:15:52',575,600,25,1,'EMP01'),('SINV-69185951','2024-12-11 20:16:38',550000,550000,0,2,'EMP01'),('SINV-69246134','2024-12-08 20:17:39',575,600,25,1,'EMP01'),('SINV-69337864','2024-12-08 20:19:14',2000,2000,0,1,'EMP01'),('SINV-77769553','2024-12-09 22:39:42',312000,312000,0,2,'EMP01'),('SINV-85467864','2024-12-20 14:35:33',56000,60000,4000,1,'EMP01'),('SINV-97501779','2024-12-18 10:22:24',26000,26000,0,2,'EMP01');
/*!40000 ALTER TABLE `shop_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_invoice_items`
--

DROP TABLE IF EXISTS `shop_invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_invoice_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stock_id` int NOT NULL,
  `qty` double NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `shop_invoice_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shop_invoice_items_shop_invoice1_idx` (`shop_invoice_id`),
  KEY `fk_shop_invoice_items_stock1_idx` (`stock_id`),
  CONSTRAINT `fk_shop_invoice_items_shop_invoice1` FOREIGN KEY (`shop_invoice_id`) REFERENCES `shop_invoice` (`id`),
  CONSTRAINT `fk_shop_invoice_items_stock1` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_invoice_items`
--

LOCK TABLES `shop_invoice_items` WRITE;
/*!40000 ALTER TABLE `shop_invoice_items` DISABLE KEYS */;
INSERT INTO `shop_invoice_items` VALUES (1,2,1,'good','SINV-69036438'),(2,3,1,'good','SINV-69133122'),(3,2,1,'good','SINV-69185951'),(4,3,1,'good','SINV-69246134'),(5,4,1,'good','SINV-69337864'),(6,7,4,'good','SINV-77769553'),(7,7,2,'-','SINV-97501779'),(8,1,5,'testing note','SINV-85467864');
/*!40000 ALTER TABLE `shop_invoice_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Active'),(2,'Inactive');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `qty` double NOT NULL,
  `product_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_product1_idx` (`product_id`),
  CONSTRAINT `fk_stock_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,11200,5,'ITEM011'),(2,12800,6,'ITEM014'),(3,575,15,'ITEM04'),(4,18000,2,'ITEM015'),(5,1999,5,'ITEM07'),(6,6500,6,'ITEM010'),(7,13000,13,'ITEM05'),(8,10000,3,'ITEM012'),(9,20000,2,'ITEM012');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` varchar(20) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_supplier_status1_idx` (`status_id`),
  CONSTRAINT `fk_supplier_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('SUP01','Toyota','Lanka','toyotalanka@gmail.com','0714729091',1),('SUP02','Yamaha','Group','yamahalk@gmail.com','0715910814',2),('SUP03','Mahadeen','Brothers','mahi@gmail.com','0714810951',1),('SUP04','Mitsubishi','Motors','mitsulk@gmail.com','0775819091',1),('SUP05','Amara','Brothers','amrea123@gmail.com','0715719108',2),('SUP06','BMW','Distributors','shalinBMW@gmail.com','0774288302',1),('SUP07','David','Pieris','Davidperiesmotorslk@gmail.com','0715910671',1),('SUP08','Michelin','Srilanka','machelinlanka@gmail.com','0774681973',2);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `vehicle_number` varchar(20) NOT NULL,
  `customer_id` int NOT NULL,
  `vehicle_brand_id` int NOT NULL,
  `model` varchar(60) NOT NULL,
  `chassis_no` varchar(45) NOT NULL,
  `engine_no` varchar(45) NOT NULL,
  `engine_name` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `vehicle_type_id` int NOT NULL,
  `powertrain_types_id` int NOT NULL,
  `drive_types_id` int NOT NULL,
  PRIMARY KEY (`vehicle_number`),
  KEY `fk_vehicle_vehicle_type1_idx` (`vehicle_type_id`),
  KEY `fk_vehicle_vehicle_brand1_idx` (`vehicle_brand_id`),
  KEY `fk_vehicle_customer1_idx` (`customer_id`),
  KEY `fk_vehicle_drive_types1_idx` (`drive_types_id`),
  KEY `fk_vehicle_powertrain_types1_idx` (`powertrain_types_id`),
  CONSTRAINT `fk_vehicle_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_vehicle_drive_types1` FOREIGN KEY (`drive_types_id`) REFERENCES `drive_types` (`id`),
  CONSTRAINT `fk_vehicle_powertrain_types1` FOREIGN KEY (`powertrain_types_id`) REFERENCES `powertrain_types` (`id`),
  CONSTRAINT `fk_vehicle_vehicle_brand1` FOREIGN KEY (`vehicle_brand_id`) REFERENCES `vehicle_brand` (`id`),
  CONSTRAINT `fk_vehicle_vehicle_type1` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES ('28-6813',3,2,'D21','D21-827672937','TD27-3572796','2.7 TD27',3,2,4),('52-5701',8,8,'L200','ML-200-34736','4d56-013845','4d56 2.4L',4,2,4),('AAB-2580',1,22,'KING','13978300LTM','TV3-6421257','200CC DOHC',7,1,2),('BBA-4109',2,6,'CT-100','BCT-C62146849','BCT124827498','DOHC-100cc',1,1,2),('CAB-1507',1,1,'Premio','NZC-5797250368','NZT-45789154','1.5 1NZ-FE',2,1,1),('CBA-3256',9,1,'CH-R','CH-T35726556',' 3ZR-29810',' 3ZR-FAE ',2,4,3),('KA-5891',6,8,'Lancer','4GC2V5891026','4G13-6250159107','1.3 4G13',2,1,1),('KH-5922',7,3,'Civic','HC24513587834','K24238456233','K20-Vtec',2,1,1),('KX-5791',5,1,'Prado','TRJ-46355625','1KZ-2680','2.8 L 1KZ-FE',2,2,4),('ND-3690',2,24,'VIKING ','LI12468L58B','TH-134779','6.8L Intercooler turbo',8,2,2),('PA-5891',3,1,'HIACE','THC-136852556','1KZ-524653','2.8L 1KZ-FE',6,2,2),('PD-3568',4,23,'D-MAX','IDM-3772277241','4JJJ1-480146','2.8 4JJ1',4,2,4);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_brand`
--

DROP TABLE IF EXISTS `vehicle_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_brand`
--

LOCK TABLES `vehicle_brand` WRITE;
/*!40000 ALTER TABLE `vehicle_brand` DISABLE KEYS */;
INSERT INTO `vehicle_brand` VALUES (1,'Toyota'),(2,'Nissan'),(3,'Honda'),(4,'BMW'),(5,'Mercedes-Benz'),(6,'Bajaj'),(7,'Maruti Suzuki'),(8,'Mitsubishi'),(9,'Subaru'),(10,'Ford'),(11,'Cherverlot'),(12,'Volkswagon'),(13,'Cherry'),(14,'KIA'),(15,'Hyundai'),(16,'Mahindra'),(17,'TATA'),(18,'Audi'),(19,'Volvo'),(20,'Mazda'),(21,'Land Rover'),(22,'TVS'),(23,'ISUZU'),(24,'LAYLAND'),(25,'EICHER');
/*!40000 ALTER TABLE `vehicle_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_type`
--

DROP TABLE IF EXISTS `vehicle_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `mileage` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_type`
--

LOCK TABLES `vehicle_type` WRITE;
/*!40000 ALTER TABLE `vehicle_type` DISABLE KEYS */;
INSERT INTO `vehicle_type` VALUES (1,'Motorcycle',4000),(2,'Motor Car',5000),(3,'Motor Lorry',10000),(4,'Dual Purpose',8000),(6,'Van',8000),(7,'Three Wheel',2500),(8,'Bus',10000);
/*!40000 ALTER TABLE `vehicle_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-02 14:31:23
