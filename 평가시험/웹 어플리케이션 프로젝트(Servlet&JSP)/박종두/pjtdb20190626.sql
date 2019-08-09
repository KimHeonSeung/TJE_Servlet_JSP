-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: pjtdb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  `image` varchar(45) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `category` int(11) NOT NULL,
  `add_time` date NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `cart_fk_1_idx` (`item_id`),
  KEY `cart_fk_2_idx` (`member_id`),
  CONSTRAINT `cart_fk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,2,'abc','man.jpg','tttttt',1,'2019-06-26'),(2,6,'abc','man.jpg','test4',3,'2019-06-26');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  `content` varchar(100) NOT NULL,
  `write_time` date NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_fk_1_idx` (`item_id`),
  KEY `comment_fk_2_idx` (`member_id`),
  CONSTRAINT `comment_fk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `comment_fk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `detailitem`
--

DROP TABLE IF EXISTS `detailitem`;
/*!50001 DROP VIEW IF EXISTS `detailitem`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `detailitem` AS SELECT 
 1 AS `item_id`,
 1 AS `category`,
 1 AS `title`,
 1 AS `content`,
 1 AS `image`,
 1 AS `write_time`,
 1 AS `update_time`,
 1 AS `read_count`,
 1 AS `comment_count`,
 1 AS `like_count`,
 1 AS `number`,
 1 AS `price`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `content` varchar(500) NOT NULL,
  `image` varchar(50) DEFAULT NULL,
  `write_time` date NOT NULL,
  `update_time` date DEFAULT NULL,
  `read_count` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `price` varchar(15) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (2,1,'tttttt','eeeeeeee','man.jpg','2019-06-21',NULL,292,0,''),(3,1,'test','test','man.jpg','2019-06-22',NULL,71,0,''),(4,1,'test2','test2','man.jpg','2019-06-22',NULL,31,0,''),(5,2,'test3','test3','man.jpg','2019-06-22',NULL,15,0,''),(6,3,'test4','test4','man.jpg','2019-06-22',NULL,103,100,'10000');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_table`
--

DROP TABLE IF EXISTS `like_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `like_table` (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `like_fk_1_idx` (`item_id`),
  KEY `like_fk_2_idx` (`member_id`),
  CONSTRAINT `like_fk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `like_fk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_table`
--

LOCK TABLES `like_table` WRITE;
/*!40000 ALTER TABLE `like_table` DISABLE KEYS */;
INSERT INTO `like_table` VALUES (14,2,'aaa'),(17,2,'abc');
/*!40000 ALTER TABLE `like_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `member` (
  `member_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `tel` varchar(30) NOT NULL,
  `address` varchar(45) NOT NULL,
  `regist_date` date NOT NULL,
  `last_access_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('aaa','abc','abc',1,NULL,'2019-06-18','123','213213','2019-06-18','2019-06-24 07:04:59'),('aabbcc','123','aabbcc',1,22,'2019-06-17','1123','213231','2019-06-18',NULL),('abc','123456','관리자',1,22,'2019-06-10','123','abc','2019-06-18','2019-06-26 07:43:08'),('bb','123','bb',2,23,'2019-06-18','33','33','2019-06-18',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellitem`
--

DROP TABLE IF EXISTS `sellitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sellitem` (
  `sell_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `sell_time` date NOT NULL,
  PRIMARY KEY (`sell_id`),
  KEY `sellitem_fk_1_idx` (`item_id`),
  CONSTRAINT `sellitem_fk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellitem`
--

LOCK TABLES `sellitem` WRITE;
/*!40000 ALTER TABLE `sellitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `sellitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `sellitemview`
--

DROP TABLE IF EXISTS `sellitemview`;
/*!50001 DROP VIEW IF EXISTS `sellitemview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `sellitemview` AS SELECT 
 1 AS `sell_id`,
 1 AS `item_id`,
 1 AS `category`,
 1 AS `title`,
 1 AS `image`,
 1 AS `number`,
 1 AS `price`,
 1 AS `sell_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `simpleitem`
--

DROP TABLE IF EXISTS `simpleitem`;
/*!50001 DROP VIEW IF EXISTS `simpleitem`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `simpleitem` AS SELECT 
 1 AS `item_id`,
 1 AS `category`,
 1 AS `title`,
 1 AS `content`,
 1 AS `image`,
 1 AS `read_count`,
 1 AS `comment_count`,
 1 AS `like_count`,
 1 AS `number`,
 1 AS `price`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `detailitem`
--

/*!50001 DROP VIEW IF EXISTS `detailitem`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `detailitem` AS select `i`.`item_id` AS `item_id`,`i`.`category` AS `category`,`i`.`title` AS `title`,`i`.`content` AS `content`,`i`.`image` AS `image`,`i`.`write_time` AS `write_time`,`i`.`update_time` AS `update_time`,`i`.`read_count` AS `read_count`,count(`c`.`item_id`) AS `comment_count`,count(`l`.`item_id`) AS `like_count`,`i`.`number` AS `number`,format(`i`.`price`,0) AS `price` from ((`item` `i` left join `comment` `c` on((`i`.`item_id` = `c`.`item_id`))) left join `like_table` `l` on((`i`.`item_id` = `l`.`item_id`))) group by `i`.`item_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `sellitemview`
--

/*!50001 DROP VIEW IF EXISTS `sellitemview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `sellitemview` AS select `s`.`sell_id` AS `sell_id`,`i`.`item_id` AS `item_id`,`i`.`category` AS `category`,`i`.`title` AS `title`,`i`.`image` AS `image`,`i`.`number` AS `number`,`i`.`price` AS `price`,`s`.`sell_time` AS `sell_time` from (`item` `i` join `sellitem` `s` on((`i`.`item_id` = `s`.`item_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `simpleitem`
--

/*!50001 DROP VIEW IF EXISTS `simpleitem`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `simpleitem` AS select `i`.`item_id` AS `item_id`,`i`.`category` AS `category`,`i`.`title` AS `title`,`i`.`content` AS `content`,`i`.`image` AS `image`,`i`.`read_count` AS `read_count`,count(`c`.`item_id`) AS `comment_count`,count(`l`.`item_id`) AS `like_count`,`i`.`number` AS `number`,format(`i`.`price`,0) AS `price` from ((`item` `i` left join `comment` `c` on((`i`.`item_id` = `c`.`item_id`))) left join `like_table` `l` on((`i`.`item_id` = `l`.`item_id`))) group by `i`.`item_id` */;
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

-- Dump completed on 2019-06-26 17:24:44
