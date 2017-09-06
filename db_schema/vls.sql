-- MySQL dump 10.16  Distrib 10.1.19-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.19-MariaDB

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `postal_code` int(11) unsigned DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `street` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `building` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `city` (`city`(10)),
  KEY `district` (`district`(10)),
  KEY `province` (`province`(3))
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (11,220059,'Минская','Минский','Минск','Панченко','60','94'),(12,224022,'Брестская','Брест','Брест','Ясеневая','22а','303'),(13,0,NULL,NULL,NULL,NULL,NULL,NULL),(14,0,NULL,NULL,NULL,NULL,NULL,NULL),(41,220090,'Минская','Минская','Минск','Калиновского','48-2','97'),(42,220059,'Минская','Минский','Минск','Панченко','60','94');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ageinsure_buildmach`
--

DROP TABLE IF EXISTS `ageinsure_buildmach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ageinsure_buildmach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `imargin_buildmach` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ageinsure_buildmach`
--

LOCK TABLES `ageinsure_buildmach` WRITE;
/*!40000 ALTER TABLE `ageinsure_buildmach` DISABLE KEYS */;
INSERT INTO `ageinsure_buildmach` VALUES (1,0,0),(2,1,0.002),(3,2,0.002),(4,3,0.002),(5,4,0.002),(6,5,0.004),(7,6,0.004),(8,7,0.004),(9,8,0.004),(10,9,0.004),(11,10,0.01);
/*!40000 ALTER TABLE `ageinsure_buildmach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ageinsure_car`
--

DROP TABLE IF EXISTS `ageinsure_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ageinsure_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `imargin_car` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ageinsure_car`
--

LOCK TABLES `ageinsure_car` WRITE;
/*!40000 ALTER TABLE `ageinsure_car` DISABLE KEYS */;
INSERT INTO `ageinsure_car` VALUES (1,0,0),(2,1,0.002),(3,2,0.002),(4,3,0.002),(5,4,0.002),(6,5,0.004),(7,6,0.004),(8,7,0.004),(9,8,0.004),(10,9,0.004),(11,10,0.01);
/*!40000 ALTER TABLE `ageinsure_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ageinsure_equip`
--

DROP TABLE IF EXISTS `ageinsure_equip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ageinsure_equip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `imargin_equip` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ageinsure_equip`
--

LOCK TABLES `ageinsure_equip` WRITE;
/*!40000 ALTER TABLE `ageinsure_equip` DISABLE KEYS */;
INSERT INTO `ageinsure_equip` VALUES (1,0,0),(2,1,0),(3,2,0),(4,3,0),(5,4,0),(6,5,0),(7,6,0),(8,7,0),(9,8,0),(10,9,0),(11,10,0);
/*!40000 ALTER TABLE `ageinsure_equip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ageinsure_farmmach`
--

DROP TABLE IF EXISTS `ageinsure_farmmach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ageinsure_farmmach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `imargin_farm` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ageinsure_farmmach`
--

LOCK TABLES `ageinsure_farmmach` WRITE;
/*!40000 ALTER TABLE `ageinsure_farmmach` DISABLE KEYS */;
INSERT INTO `ageinsure_farmmach` VALUES (1,0,0),(2,1,0.005),(3,2,0.005),(4,3,0.005),(5,4,0.005),(6,5,0.008),(7,6,0.008),(8,7,0.008),(9,8,0.008),(10,9,0.008),(11,10,0.01);
/*!40000 ALTER TABLE `ageinsure_farmmach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ageinsure_lorry`
--

DROP TABLE IF EXISTS `ageinsure_lorry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ageinsure_lorry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `imargin_lorry` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ageinsure_lorry`
--

LOCK TABLES `ageinsure_lorry` WRITE;
/*!40000 ALTER TABLE `ageinsure_lorry` DISABLE KEYS */;
INSERT INTO `ageinsure_lorry` VALUES (1,0,0),(2,1,0.002),(3,2,0.002),(4,3,0.002),(5,4,0.002),(6,5,0.004),(7,6,0.004),(8,7,0.004),(9,8,0.004),(10,9,0.004),(11,10,0.01);
/*!40000 ALTER TABLE `ageinsure_lorry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ageinsure_realestate`
--

DROP TABLE IF EXISTS `ageinsure_realestate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ageinsure_realestate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `imargin_realest` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ageinsure_realestate`
--

LOCK TABLES `ageinsure_realestate` WRITE;
/*!40000 ALTER TABLE `ageinsure_realestate` DISABLE KEYS */;
INSERT INTO `ageinsure_realestate` VALUES (1,0,0),(2,1,0),(3,2,0),(4,3,0),(5,4,0),(6,5,0),(7,6,0),(8,7,0),(9,8,0),(10,9,0),(11,10,0);
/*!40000 ALTER TABLE `ageinsure_realestate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ageinsure_truck`
--

DROP TABLE IF EXISTS `ageinsure_truck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ageinsure_truck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `imargin_truck` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ageinsure_truck`
--

LOCK TABLES `ageinsure_truck` WRITE;
/*!40000 ALTER TABLE `ageinsure_truck` DISABLE KEYS */;
INSERT INTO `ageinsure_truck` VALUES (1,0,0),(2,1,0.002),(3,2,0.002),(4,3,0.002),(5,4,0.002),(6,5,0.004),(7,6,0.004),(8,7,0.004),(9,8,0.004),(10,9,0.004),(11,10,0.01);
/*!40000 ALTER TABLE `ageinsure_truck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agemargin_buildmach`
--

DROP TABLE IF EXISTS `agemargin_buildmach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agemargin_buildmach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `margin_buildmach` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agemargin_buildmach`
--

LOCK TABLES `agemargin_buildmach` WRITE;
/*!40000 ALTER TABLE `agemargin_buildmach` DISABLE KEYS */;
INSERT INTO `agemargin_buildmach` VALUES (1,0,0),(2,1,0.01),(3,2,0.01),(4,3,0.01),(5,4,0.02),(6,5,0.02),(7,6,0.03),(8,7,0.03),(9,8,0.03),(10,9,0.05),(11,10,0.05);
/*!40000 ALTER TABLE `agemargin_buildmach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agemargin_car`
--

DROP TABLE IF EXISTS `agemargin_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agemargin_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `margin_car` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agemargin_car`
--

LOCK TABLES `agemargin_car` WRITE;
/*!40000 ALTER TABLE `agemargin_car` DISABLE KEYS */;
INSERT INTO `agemargin_car` VALUES (1,0,0),(2,1,0.01),(3,2,0.01),(4,3,0.01),(5,4,0.02),(6,5,0.02),(7,6,0.03),(8,7,0.03),(9,8,0.03),(10,9,0.04),(11,10,0.04);
/*!40000 ALTER TABLE `agemargin_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agemargin_equip`
--

DROP TABLE IF EXISTS `agemargin_equip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agemargin_equip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `margin_equip` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agemargin_equip`
--

LOCK TABLES `agemargin_equip` WRITE;
/*!40000 ALTER TABLE `agemargin_equip` DISABLE KEYS */;
INSERT INTO `agemargin_equip` VALUES (1,0,0),(2,1,0.02),(3,2,0.02),(4,3,0.02),(5,4,0.03),(6,5,0.03),(7,6,0.04),(8,7,0.04),(9,8,0.04),(10,9,0.06),(11,10,0.06);
/*!40000 ALTER TABLE `agemargin_equip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agemargin_farmmach`
--

DROP TABLE IF EXISTS `agemargin_farmmach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agemargin_farmmach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `margin_farm` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agemargin_farmmach`
--

LOCK TABLES `agemargin_farmmach` WRITE;
/*!40000 ALTER TABLE `agemargin_farmmach` DISABLE KEYS */;
INSERT INTO `agemargin_farmmach` VALUES (1,0,0),(2,1,0.02),(3,2,0.02),(4,3,0.02),(5,4,0.03),(6,5,0.03),(7,6,0.04),(8,7,0.04),(9,8,0.04),(10,9,0.04),(11,10,0.04);
/*!40000 ALTER TABLE `agemargin_farmmach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agemargin_lorry`
--

DROP TABLE IF EXISTS `agemargin_lorry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agemargin_lorry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `margin_lorry` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agemargin_lorry`
--

LOCK TABLES `agemargin_lorry` WRITE;
/*!40000 ALTER TABLE `agemargin_lorry` DISABLE KEYS */;
INSERT INTO `agemargin_lorry` VALUES (1,0,0),(2,1,0.01),(3,2,0.01),(4,3,0.01),(5,4,0.02),(6,5,0.02),(7,6,0.03),(8,7,0.03),(9,8,0.03),(10,9,0.04),(11,10,0.04);
/*!40000 ALTER TABLE `agemargin_lorry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agemargin_realestate`
--

DROP TABLE IF EXISTS `agemargin_realestate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agemargin_realestate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `margin_realest` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agemargin_realestate`
--

LOCK TABLES `agemargin_realestate` WRITE;
/*!40000 ALTER TABLE `agemargin_realestate` DISABLE KEYS */;
INSERT INTO `agemargin_realestate` VALUES (1,0,0),(2,1,0),(3,2,0),(4,3,0),(5,4,0),(6,5,0),(7,6,0),(8,7,0),(9,8,0),(10,9,0),(11,10,0);
/*!40000 ALTER TABLE `agemargin_realestate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agemargin_truck`
--

DROP TABLE IF EXISTS `agemargin_truck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agemargin_truck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` tinyint(3) unsigned NOT NULL,
  `margin_truck` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agemargin_truck`
--

LOCK TABLES `agemargin_truck` WRITE;
/*!40000 ALTER TABLE `agemargin_truck` DISABLE KEYS */;
INSERT INTO `agemargin_truck` VALUES (1,0,0),(2,1,0.005),(3,2,0.005),(4,3,0.005),(5,4,0.01),(6,5,0.01),(7,6,0.015),(8,7,0.015),(9,8,0.015),(10,9,0.015),(11,10,0.015);
/*!40000 ALTER TABLE `agemargin_truck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baserate`
--

DROP TABLE IF EXISTS `baserate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baserate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` char(3) NOT NULL,
  `rate` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baserate`
--

LOCK TABLES `baserate` WRITE;
/*!40000 ALTER TABLE `baserate` DISABLE KEYS */;
INSERT INTO `baserate` VALUES (1,'byn',0.18),(2,'eur',0.09),(3,'rub',0.16),(4,'usd',0.09);
/*!40000 ALTER TABLE `baserate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comp_name` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comp_form` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comp_registration_date` date DEFAULT NULL,
  `business_type` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `monthly_income` decimal(10,0) unsigned DEFAULT NULL,
  `number_of_staff` int(10) unsigned DEFAULT NULL,
  `number_of_cars` int(10) unsigned DEFAULT NULL,
  `number_of_lorrys` int(10) unsigned DEFAULT NULL,
  `number_of_trucks` int(10) unsigned DEFAULT NULL,
  `number_of_build_mach` int(10) unsigned DEFAULT NULL,
  `number_of_farm_mach` int(10) unsigned NOT NULL,
  `number_of_equipment_lines` int(10) unsigned DEFAULT NULL,
  `number_of_office_area` int(10) unsigned DEFAULT NULL,
  `number_of_other_area` int(10) unsigned DEFAULT NULL,
  `ceo_id` int(10) unsigned DEFAULT NULL,
  `contact_person_id` int(10) unsigned DEFAULT NULL,
  `address_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `compname` (`comp_name`(20)),
  KEY `businesstupe` (`business_type`(20)),
  FULLTEXT KEY `phonenumber` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Активлизинг','ООО','2003-06-13','Лизинг','375162339494',434534,70,45,1,3,4,5,6,500,555,28,29,12);
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insuretypebaserate`
--

DROP TABLE IF EXISTS `insuretypebaserate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insuretypebaserate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objtype` varchar(20) NOT NULL,
  `insureBaseRate` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insuretypebaserate`
--

LOCK TABLES `insuretypebaserate` WRITE;
/*!40000 ALTER TABLE `insuretypebaserate` DISABLE KEYS */;
INSERT INTO `insuretypebaserate` VALUES (1,'buildingmachines',0.02),(2,'car',0.0308),(3,'equipment',0.01),(4,'farmingmachinery',0.02),(5,'lorry',0.024),(6,'realestate',0.005),(7,'truck',0.019);
/*!40000 ALTER TABLE `insuretypebaserate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objtype`
--

DROP TABLE IF EXISTS `objtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objtype` (
  `objtype` varchar(20) DEFAULT NULL,
  `obj_id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objtype`
--

LOCK TABLES `objtype` WRITE;
/*!40000 ALTER TABLE `objtype` DISABLE KEYS */;
INSERT INTO `objtype` VALUES ('car',1),('lorry',2),('truck',3),('buildingmachines',4),('farmingmachinery',5),('equipment',6),('realestate',7);
/*!40000 ALTER TABLE `objtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objtypemargin`
--

DROP TABLE IF EXISTS `objtypemargin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objtypemargin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objtype` varchar(20) NOT NULL,
  `objtypemargin` float DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objtypemargin`
--

LOCK TABLES `objtypemargin` WRITE;
/*!40000 ALTER TABLE `objtypemargin` DISABLE KEYS */;
INSERT INTO `objtypemargin` VALUES (1,'buildingmachines',0.055),(2,'car',0.042),(3,'equipment',0.075),(4,'farmingmachinery',0.05),(5,'lorry',0.045),(6,'realestate',0.035),(7,'truck',0.025);
/*!40000 ALTER TABLE `objtypemargin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `gender` varchar(5) DEFAULT NULL,
  `firstname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `patronymicname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `phonenumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `employer` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `monthlyincome` int(10) unsigned DEFAULT NULL,
  `segment` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `gender` (`gender`(1)),
  KEY `firstname` (`firstname`(10)),
  KEY `lastname` (`lastname`(10)),
  KEY `patronum` (`patronymicname`(10)),
  KEY `phone` (`phonenumber`),
  FULLTEXT KEY `employer` (`employer`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (2,'муж','Марина','Василевская','Владимировна','1987-03-02',42,'375292964264','Аварийная служба','Бухгалтер',500,1),(27,'муж','Денис','Василевский','Михайлович','1984-10-03',11,'375296688832','Активлизинг','Специалист по лизингу',2000,2),(28,NULL,'Виктор','Кобяк','Александрович','1900-01-01',13,'375291234567',NULL,'Директор',0,0),(29,NULL,'Ольга','Ковальчук','Ивановна','1900-01-01',14,'375297654321',NULL,'Специалист',0,0),(42,'муж','Михаил','Василевский','Владимирович','1957-12-05',41,'375291234567','Пеленг','Слесарь',4000,3);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_login` varchar(128) DEFAULT NULL,
  `user_psw` varchar(128) DEFAULT NULL,
  `email_valid` tinyint(1) NOT NULL DEFAULT '0',
  `user_status_id` tinyint(4) DEFAULT NULL,
  `person_id` int(11) DEFAULT '0',
  `company_id` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  KEY `login_ind` (`user_login`(15)),
  FULLTEXT KEY `psw` (`user_psw`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (22,'4denver@mail.ru','KqtZQq7+swoVUEYtyCm36nBbIQvK691lYKdiGzVs6WQ=$o/icL80chTq4qmWtOGeGN5PHf+vazGJAu3Z53jU3+G4=',1,2,0,1),(24,'4sol@tut.by','ZvetJyrBgH8t3BSkklPEXY6IfnluFgrf6ujuUz2j1ho=$OJGzQzzxEqaJ2TMcSmTeppIJIPy+V70MBF4SSyvPQVk=',1,3,0,0),(39,'d.vasileuski@gmail.com','SfJBmf2GMxEv3sUcuAJXL4kEh1swS711niOqDOSNDMA=$B1VbL4CS0Y7wi6J8gbeo4FAAdc0jvuu02j2Is6vWF2g=',1,3,27,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-05  9:26:32
