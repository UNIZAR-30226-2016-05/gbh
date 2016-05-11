-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `Destinos`
--

DROP TABLE IF EXISTS `Destinos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Destinos` (
  `idDestino` int(11) NOT NULL AUTO_INCREMENT,
  `Pais` text NOT NULL,
  `Ciudad` text NOT NULL,
  `Validado` tinyint(1) NOT NULL,
  `Time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idDestino`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Destinos`
--

LOCK TABLES `Destinos` WRITE;
/*!40000 ALTER TABLE `Destinos` DISABLE KEYS */;
INSERT INTO `Destinos` VALUES (1,'Italia','Turin',1,'2016-05-11 13:16:54'),(2,'Irlanda','Cork',1,'2016-05-11 13:16:54'),(3,'Austria','Viena',1,'2016-05-11 13:16:54'),(4,'Alemania','Berlin',1,'2016-05-11 13:16:54'),(5,'España','Zaragoza',1,'2016-05-11 13:16:54'),(6,'Dinamarca','Aalborg',1,'2016-05-11 13:16:54'),(7,'Francia','Nantes',1,'2016-05-11 13:16:54'),(8,'Noruega','Oslo',1,'2016-05-11 13:16:54'),(9,'Reino Unido','Oxford',1,'2016-05-11 13:16:54'),(10,'Suecia','Boras',1,'2016-05-11 13:16:54'),(11,'Elija pais','a',1,'2016-05-11 13:16:54'),(12,'Elija pais','b',1,'2016-05-11 13:16:54'),(13,'Alemania','a',1,'2016-05-11 13:16:54');
/*!40000 ALTER TABLE `Destinos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-11 13:17:36
