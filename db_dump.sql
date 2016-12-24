CREATE DATABASE  IF NOT EXISTS `somefirmdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `somefirmdb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: somefirmdb
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `group_authorities`
--

DROP TABLE IF EXISTS `group_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_authorities` (
  `group_id` bigint(20) unsigned NOT NULL,
  `authority` varchar(64) NOT NULL,
  PRIMARY KEY (`group_id`,`authority`),
  CONSTRAINT `group_authorities_fk` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_authorities`
--

LOCK TABLES `group_authorities` WRITE;
/*!40000 ALTER TABLE `group_authorities` DISABLE KEYS */;
INSERT INTO `group_authorities` VALUES (1,'ROLE_USER'),(2,'ROLE_USER'),(2,'ROLE_WORKER'),(3,'ROLE_BRIGADIER'),(3,'ROLE_USER'),(3,'ROLE_WORKER'),(4,'ROLE_BRIGADIER'),(4,'ROLE_MANAGER'),(4,'ROLE_USER'),(4,'ROLE_WORKER');
/*!40000 ALTER TABLE `group_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_members` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `group_members_fk_idx` (`group_id`),
  CONSTRAINT `group_members_fk` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
INSERT INTO `group_members` VALUES (1,'Some user 1',1),(2,'Some user 2',1),(3,'Some user 3',1),(4,'Some user 4',1),(5,'Some worker 1',2),(6,'Some worker 2',2),(7,'Some worker 3',2),(8,'Some worker 4',2),(9,'Some worker 5',2),(10,'Some worker 6',2),(11,'Some brigadier 1',3),(12,'Some brigadier 2',3),(13,'Some manager 1',4);
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'users'),(2,'workers'),(3,'brigadiers'),(4,'managers');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal_operation_type`
--

DROP TABLE IF EXISTS `journal_operation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal_operation_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal_operation_type`
--

LOCK TABLES `journal_operation_type` WRITE;
/*!40000 ALTER TABLE `journal_operation_type` DISABLE KEYS */;
INSERT INTO `journal_operation_type` VALUES (1,'Закупка'),(2,'Списание');
/*!40000 ALTER TABLE `journal_operation_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(4096) DEFAULT NULL,
  `duration` bigint(20) NOT NULL,
  `plan_id` bigint(20) NOT NULL,
  `default_workshop` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `plan_fk_idx` (`plan_id`),
  KEY `operation_default_workshop_fk_idx` (`default_workshop`),
  CONSTRAINT `operation_default_workshop_fk` FOREIGN KEY (`default_workshop`) REFERENCES `workshop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `plan_fk` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (1,'Операция 1',4545,1,1),(2,'Операция 2',4546,1,1),(3,'Операция 3',4547,2,1),(4,'Операция 4 впып рпввпывп Операция 4 вапфп рываыпиры рппцжбыж папжфыаж ',4548,4,1);
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_sacrificial_material`
--

DROP TABLE IF EXISTS `operation_sacrificial_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation_sacrificial_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation_id` bigint(20) NOT NULL,
  `sacrificial_material_id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `operation_sacrificial_material_operation_idx` (`operation_id`),
  KEY `operation_sacrificial_material_sacrificial_material_fk_idx` (`sacrificial_material_id`),
  CONSTRAINT `operation_sacrificial_material_operation` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operation_sacrificial_material_sacrificial_material_fk` FOREIGN KEY (`sacrificial_material_id`) REFERENCES `sacrificial_material_type` (`pressmark`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_sacrificial_material`
--

LOCK TABLES `operation_sacrificial_material` WRITE;
/*!40000 ALTER TABLE `operation_sacrificial_material` DISABLE KEYS */;
INSERT INTO `operation_sacrificial_material` VALUES (3,1,385434,30),(4,2,385435,40),(5,3,385435,50),(14,1,385432,20),(15,1,385435,12);
/*!40000 ALTER TABLE `operation_sacrificial_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_tool`
--

DROP TABLE IF EXISTS `operation_tool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation_tool` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation_id` bigint(20) NOT NULL,
  `tool_type_id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `operation_tool_operation_fk_idx` (`operation_id`),
  KEY `operation_tool_tool_fk_idx` (`tool_type_id`),
  CONSTRAINT `operation_tool_operation_fk` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `operation_tool_tool_fk` FOREIGN KEY (`tool_type_id`) REFERENCES `tool_type` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_tool`
--

LOCK TABLES `operation_tool` WRITE;
/*!40000 ALTER TABLE `operation_tool` DISABLE KEYS */;
INSERT INTO `operation_tool` VALUES (2,1,385412,30),(3,2,385413,40),(4,3,385413,60),(5,1,385411,643);
/*!40000 ALTER TABLE `operation_tool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_thing`
--

DROP TABLE IF EXISTS `order_thing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_thing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `pressmark` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `description` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_thing_user_fk_idx` (`username`),
  KEY `order_thing_pressmarks_fk_idx` (`pressmark`),
  CONSTRAINT `order_thing_pressmarks_fk` FOREIGN KEY (`pressmark`) REFERENCES `pressmarks` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `order_thing_user_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_thing`
--

LOCK TABLES `order_thing` WRITE;
/*!40000 ALTER TABLE `order_thing` DISABLE KEYS */;
INSERT INTO `order_thing` VALUES (1,'Some brigadier 1',385432,20,'Для дела солому беру.'),(2,'Some brigadier 1',385434,30,'Для дела солому беру.'),(3,'Some brigadier 1',385435,30,'Для дела солому беру.');
/*!40000 ALTER TABLE `order_thing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,NULL),(2,NULL),(3,NULL),(4,NULL),(5,NULL);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pressmarks`
--

DROP TABLE IF EXISTS `pressmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pressmarks` (
  `pressmark` bigint(20) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`pressmark`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pressmarks`
--

LOCK TABLES `pressmarks` WRITE;
/*!40000 ALTER TABLE `pressmarks` DISABLE KEYS */;
INSERT INTO `pressmarks` VALUES (385411,2),(385412,2),(385413,2),(385432,1),(385434,1),(385435,1),(385462,0),(385463,0),(385464,0),(385465,0);
/*!40000 ALTER TABLE `pressmarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_type` (
  `pressmark` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4096) DEFAULT NULL,
  `cost` decimal(15,2) NOT NULL,
  PRIMARY KEY (`pressmark`),
  CONSTRAINT `product_type_pressmark_fk` FOREIGN KEY (`pressmark`) REFERENCES `pressmarks` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (385462,'Шкаф','Этот шкаф будет служить тебе!',6666.66),(385463,'Ковер','Этот ковер будет служить тебе!',5555.66),(385464,'Енот','Этот енот будет служить тебе!',5555.66),(385465,'Кружка','Этот кружка будет служить тебе!',555.00);
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type_operation`
--

DROP TABLE IF EXISTS `product_type_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_type_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pressmark` bigint(20) NOT NULL,
  `operation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_type_operation_product_type_fk_idx` (`pressmark`),
  KEY `product_type_operation_operation_fk_idx` (`operation_id`),
  CONSTRAINT `product_type_operation_operation_fk` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_type_operation_product_type_fk` FOREIGN KEY (`pressmark`) REFERENCES `product_type` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type_operation`
--

LOCK TABLES `product_type_operation` WRITE;
/*!40000 ALTER TABLE `product_type_operation` DISABLE KEYS */;
INSERT INTO `product_type_operation` VALUES (1,385462,1),(2,385462,2),(3,385462,3),(4,385464,3),(5,385464,4);
/*!40000 ALTER TABLE `product_type_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sacrificial_material_type`
--

DROP TABLE IF EXISTS `sacrificial_material_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sacrificial_material_type` (
  `pressmark` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`pressmark`),
  CONSTRAINT `sacrificial_material_type_pressmark_fk` FOREIGN KEY (`pressmark`) REFERENCES `pressmarks` (`pressmark`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sacrificial_material_type`
--

LOCK TABLES `sacrificial_material_type` WRITE;
/*!40000 ALTER TABLE `sacrificial_material_type` DISABLE KEYS */;
INSERT INTO `sacrificial_material_type` VALUES (385432,'Солома','Солома'),(385434,'Дерево','Дерево подойдет для строительства твоего домика!'),(385435,'Кирпич','Кирпич подойдет для строительства твоего дома!');
/*!40000 ALTER TABLE `sacrificial_material_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage_employe`
--

DROP TABLE IF EXISTS `storage_employe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage_employe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fio` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage_employe`
--

LOCK TABLES `storage_employe` WRITE;
/*!40000 ALTER TABLE `storage_employe` DISABLE KEYS */;
INSERT INTO `storage_employe` VALUES (1,'Семёныч'),(2,'Петрович');
/*!40000 ALTER TABLE `storage_employe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage_journal`
--

DROP TABLE IF EXISTS `storage_journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage_journal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `workshop_id` bigint(20) DEFAULT NULL,
  `pressmark` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `date_and_time` bigint(20) NOT NULL,
  `storage_employe_id` bigint(20) NOT NULL,
  `description` varchar(4096) DEFAULT NULL,
  `journal_operation_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `storage_journal_pressmark_fk_idx` (`pressmark`),
  KEY `storage_journal_storage_employe_fk_idx` (`storage_employe_id`),
  KEY `storage_journal_journal_operation_type_fk_idx` (`journal_operation_type`),
  KEY `storage_journal_workshop_fk_idx` (`workshop_id`),
  CONSTRAINT `storage_journal_journal_operation_type_fk` FOREIGN KEY (`journal_operation_type`) REFERENCES `journal_operation_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `storage_journal_pressmark_fk` FOREIGN KEY (`pressmark`) REFERENCES `pressmarks` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `storage_journal_storage_employe_fk` FOREIGN KEY (`storage_employe_id`) REFERENCES `storage_employe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `storage_journal_workshop_fk` FOREIGN KEY (`workshop_id`) REFERENCES `workshop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage_journal`
--

LOCK TABLES `storage_journal` WRITE;
/*!40000 ALTER TABLE `storage_journal` DISABLE KEYS */;
INSERT INTO `storage_journal` VALUES (1,NULL,385411,860,2546124152,1,'Привезли гаечные ключи',1),(3,NULL,385435,2506,2546124153,1,'Привезли кирпичи',1);
/*!40000 ALTER TABLE `storage_journal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storages_content`
--

DROP TABLE IF EXISTS `storages_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storages_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `workshop_id` bigint(20) DEFAULT NULL,
  `pressmark_id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `storages_content_pressmark_fk_idx` (`pressmark_id`),
  KEY `storages_content_workshop_fk_idx` (`workshop_id`),
  CONSTRAINT `storages_content_pressmark_fk` FOREIGN KEY (`pressmark_id`) REFERENCES `pressmarks` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `storages_content_workshop_fk` FOREIGN KEY (`workshop_id`) REFERENCES `workshop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storages_content`
--

LOCK TABLES `storages_content` WRITE;
/*!40000 ALTER TABLE `storages_content` DISABLE KEYS */;
INSERT INTO `storages_content` VALUES (1,NULL,385411,860),(2,NULL,385435,2506),(3,2,385411,543);
/*!40000 ALTER TABLE `storages_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pressmark` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `date_of_create` bigint(20) NOT NULL,
  `date_of_deadline` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `team_pressmark_fk_idx` (`pressmark`),
  CONSTRAINT `team_pressmark_fk` FOREIGN KEY (`pressmark`) REFERENCES `product_type` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,385462,2,563534,463562356),(2,385463,3,563535,4635623567);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_users`
--

DROP TABLE IF EXISTS `team_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `team_users_team_fk_idx` (`team_id`),
  KEY `team_users_users_fk_idx` (`username`),
  CONSTRAINT `team_users_team_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_users_users_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_users`
--

LOCK TABLES `team_users` WRITE;
/*!40000 ALTER TABLE `team_users` DISABLE KEYS */;
INSERT INTO `team_users` VALUES (1,1,'Some brigadier 1'),(2,1,'Some worker 1'),(3,1,'Some worker 2'),(4,2,'Some brigadier 2'),(5,2,'Some worker 3'),(6,2,'Some worker 5');
/*!40000 ALTER TABLE `team_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tool_type`
--

DROP TABLE IF EXISTS `tool_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tool_type` (
  `pressmark` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`pressmark`),
  CONSTRAINT `tool_type_pressmark_fk` FOREIGN KEY (`pressmark`) REFERENCES `pressmarks` (`pressmark`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_type`
--

LOCK TABLES `tool_type` WRITE;
/*!40000 ALTER TABLE `tool_type` DISABLE KEYS */;
INSERT INTO `tool_type` VALUES (385411,'Гаечный ключ','Этот гаечный ключ поможет тебе!'),(385412,'Пила','Пила не пила!'),(385413,'Топор','Топор тоже не пил... наверно...');
/*!40000 ALTER TABLE `tool_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(128) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`),
  CONSTRAINT `users_group_member_fk` FOREIGN KEY (`username`) REFERENCES `group_members` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Some brigadier 1','pass11',1),('Some brigadier 2','pass12',1),('Some manager 1','pass13',1),('Some user 1','pass1',1),('Some user 2','pass2',1),('Some user 3','pass3',1),('Some user 4','pass4',1),('Some worker 1','pass5',1),('Some worker 2','pass6',1),('Some worker 3','pass7',1),('Some worker 4','pass8',1),('Some worker 5','pass9',1),('Some worker 6','pass10',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workshop`
--

DROP TABLE IF EXISTS `workshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workshop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workshop`
--

LOCK TABLES `workshop` WRITE;
/*!40000 ALTER TABLE `workshop` DISABLE KEYS */;
INSERT INTO `workshop` VALUES (1,'Цех \"Колбаска\"'),(2,'Цех \"Пикачу\"'),(3,'Цех \"Пролетариат\"'),(4,'Цех \"Одинокие циклопы\"');
/*!40000 ALTER TABLE `workshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'somefirmdb'
--

--
-- Dumping routines for database 'somefirmdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-24 14:10:40
