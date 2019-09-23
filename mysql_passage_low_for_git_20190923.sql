-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: passage_low_for_git
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `pass_comment`
--

DROP TABLE IF EXISTS `pass_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pass_comment` (
  `Cnumber` int(11) NOT NULL AUTO_INCREMENT,
  `Ccount` longtext,
  `Ctime` datetime DEFAULT NULL,
  `Ctitle` varchar(255) DEFAULT NULL,
  `Pnumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`Cnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass_comment`
--

LOCK TABLES `pass_comment` WRITE;
/*!40000 ALTER TABLE `pass_comment` DISABLE KEYS */;
INSERT INTO `pass_comment` VALUES (190,'评论相关内容','2019-09-23 22:23:09','评论测试',1049);
/*!40000 ALTER TABLE `pass_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pass_image`
--

DROP TABLE IF EXISTS `pass_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pass_image` (
  `Inumber` int(11) NOT NULL AUTO_INCREMENT,
  `Ititle` varchar(50) DEFAULT NULL,
  `Iurl` varchar(50) DEFAULT NULL,
  `Imark` varchar(50) DEFAULT NULL,
  `Itime` datetime DEFAULT NULL,
  PRIMARY KEY (`Inumber`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass_image`
--

LOCK TABLES `pass_image` WRITE;
/*!40000 ALTER TABLE `pass_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `pass_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pass_kind`
--

DROP TABLE IF EXISTS `pass_kind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pass_kind` (
  `AKid` int(11) NOT NULL AUTO_INCREMENT,
  `AKvalue` varchar(50) DEFAULT NULL,
  `AKdetail` varchar(225) DEFAULT NULL,
  `AKstate` smallint(6) DEFAULT NULL,
  `AKfather` smallint(6) DEFAULT NULL,
  `AKkind` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`AKid`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass_kind`
--

LOCK TABLES `pass_kind` WRITE;
/*!40000 ALTER TABLE `pass_kind` DISABLE KEYS */;
INSERT INTO `pass_kind` VALUES (1,'发布平台---自身跟目录',NULL,NULL,NULL,1),(2,'水文的来源',NULL,0,0,1),(3,'',NULL,NULL,NULL,NULL),(22,'','',0,NULL,1),(107,'水文种类','',0,0,1),(168,'种类1','',0,0,107),(169,'种类2','',0,0,107),(170,'种类3','',0,0,107),(171,'种类4','',0,0,107),(172,'种类5','',0,0,107),(208,'分类1','',0,0,2),(209,'分类2','',0,0,2),(210,'分类3','',0,0,2),(211,'分类4','',0,0,2),(212,'分类5','',0,0,2);
/*!40000 ALTER TABLE `pass_kind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pass_passage`
--

DROP TABLE IF EXISTS `pass_passage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pass_passage` (
  `Pnumber` int(11) NOT NULL AUTO_INCREMENT,
  `Ptitle` varchar(100) DEFAULT NULL,
  `Ptime` datetime DEFAULT NULL,
  `Plabel` varchar(50) DEFAULT NULL,
  `Pcount` longtext,
  `Pcoming` varchar(50) DEFAULT NULL,
  `PcomingUrl` varchar(255) DEFAULT NULL,
  `Pimage` varchar(255) DEFAULT NULL,
  `Pkind` smallint(11) DEFAULT NULL,
  `Pbelong` smallint(11) DEFAULT NULL,
  `Pdescribe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Pnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=1050 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass_passage`
--

LOCK TABLES `pass_passage` WRITE;
/*!40000 ALTER TABLE `pass_passage` DISABLE KEYS */;
INSERT INTO `pass_passage` VALUES (1049,'系统说明-新增文章','2019-09-23 22:20:58','关键字','<p>\r\n	简单实现博客的基本功能。包括：\r\n</p>\r\n<p>\r\n	文章增、删、改、查。\r\n</p>\r\n<p>\r\n	评论添加修改。\r\n</p>\r\n<p>\r\n	文章种类包含两个下拉框选项，可以用来分类。有对应的分类管理界面。\r\n</p>\r\n<p>\r\n	<br />\r\n</p>','参考内容','外链地址','添加图片',170,208,'简单实现博客的基本功能。包括：\r\n\r\n\r\n	文章增、删、改、查。\r\n\r\n\r\n	评论添加修改。\r\n\r\n\r\n	文章种类包含两个下拉框选项，可以用来分类。有对应的分类管理界面。');
/*!40000 ALTER TABLE `pass_passage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-23 22:25:45
