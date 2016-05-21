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
-- Table structure for table `Asignaturas`
--

DROP TABLE IF EXISTS `Asignaturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Asignaturas` (
  `idAsignatura` int(11) NOT NULL AUTO_INCREMENT,
  `Carrera` int(11) NOT NULL,
  `Nombre` text NOT NULL,
  `Creditos` int(2) NOT NULL,
  `Cuatrimestre` int(1) NOT NULL,
  `Validado` tinyint(1) NOT NULL,
  `Time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idAsignatura`),
  KEY `idCarrera_idx` (`Carrera`),
  CONSTRAINT `idCarrera_A` FOREIGN KEY (`Carrera`) REFERENCES `Carrera` (`idCarrera`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Asignaturas`
--

LOCK TABLES `Asignaturas` WRITE;
/*!40000 ALTER TABLE `Asignaturas` DISABLE KEYS */;
INSERT INTO `Asignaturas` VALUES (1,1,'Bibendum Sed LLP',2,1,1,'2016-05-11 13:13:23'),(2,10,'Arcu Vestibulum Ante Industries',9,1,1,'2016-05-11 13:13:23'),(3,9,'Curabitur Massa Vestibulum Industries',3,2,1,'2016-05-11 13:13:23'),(4,2,'Ornare Tortor Corp.',2,1,1,'2016-05-11 13:13:23'),(5,10,'Ante Iaculis Nec Limited',3,2,1,'2016-05-11 13:13:23'),(6,10,'Aliquam Enim PC',9,2,1,'2016-05-11 13:13:23'),(7,10,'Nullam Incorporated',8,2,1,'2016-05-11 13:13:23'),(8,10,'Nunc Mauris Elit Consulting',2,2,1,'2016-05-11 13:13:23'),(9,5,'Magna Nec Quam Corp.',12,1,1,'2016-05-11 13:13:23'),(10,15,'Sapien Nunc Ltd',4,1,1,'2016-05-11 13:13:23'),(11,17,'Justo Sit Amet Inc.',8,2,1,'2016-05-11 13:13:23'),(12,4,'Elementum At Corp.',10,1,1,'2016-05-11 13:13:23'),(13,14,'Mi Enim Consulting',10,2,1,'2016-05-11 13:13:23'),(14,5,'Cursus Integer Mollis Institute',9,1,1,'2016-05-11 13:13:23'),(15,7,'Eu Company',3,1,1,'2016-05-11 13:13:23'),(16,5,'In At Pede Inc.',4,1,1,'2016-05-11 13:13:23'),(17,6,'Nulla Facilisis Suspendisse Limited',11,2,1,'2016-05-11 13:13:23'),(18,13,'Nec Cursus Corp.',10,2,1,'2016-05-11 13:13:23'),(19,9,'Auctor Inc.',9,2,1,'2016-05-11 13:13:23'),(20,7,'Sed Facilisis Vitae Corporation',6,2,1,'2016-05-11 13:13:23'),(21,12,'Diam Sed Diam Corp.',2,1,1,'2016-05-11 13:13:23'),(22,13,'Pede Incorporated',12,1,1,'2016-05-11 13:13:23'),(23,5,'Donec Non Justo Limited',8,2,1,'2016-05-11 13:13:23'),(24,12,'Pede Institute',2,1,1,'2016-05-11 13:13:23'),(25,15,'Vitae Mauris Incorporated',5,1,1,'2016-05-11 13:13:23'),(26,14,'Ipsum Ac Mi Inc.',11,2,1,'2016-05-11 13:13:23'),(27,15,'Lacus Varius Et Associates',4,2,1,'2016-05-11 13:13:23'),(28,15,'Laoreet Inc.',7,2,1,'2016-05-11 13:13:23'),(29,8,'Velit PC',4,2,1,'2016-05-11 13:13:23'),(30,3,'Nisi Mauris Corp.',7,1,1,'2016-05-11 13:13:23'),(31,19,'A Neque Nullam LLC',4,1,1,'2016-05-11 13:13:23'),(32,13,'Quis Pede Suspendisse Limited',11,1,1,'2016-05-11 13:13:23'),(33,14,'Quis Incorporated',6,1,1,'2016-05-11 13:13:23'),(34,9,'Diam Lorem Auctor Foundation',12,1,1,'2016-05-11 13:13:23'),(35,6,'Nulla Dignissim Maecenas Consulting',12,1,1,'2016-05-11 13:13:23'),(36,18,'Nullam Feugiat Institute',8,1,1,'2016-05-11 13:13:23'),(37,15,'Ut Erat Limited',10,1,1,'2016-05-11 13:13:23'),(38,16,'Risus At Fringilla Foundation',2,1,1,'2016-05-11 13:13:23'),(39,13,'Donec Limited',11,1,1,'2016-05-11 13:13:23'),(40,16,'Magna Corporation',12,2,1,'2016-05-11 13:13:23'),(41,6,'Et Associates',12,2,1,'2016-05-11 13:13:23'),(42,9,'Odio Semper Foundation',12,2,1,'2016-05-11 13:13:23'),(43,13,'Lobortis Augue LLP',2,2,1,'2016-05-11 13:13:23'),(44,9,'Ac Orci Corporation',10,1,1,'2016-05-11 13:13:23'),(45,20,'Blandit Enim Consequat Industries',7,1,1,'2016-05-11 13:13:23'),(46,9,'Pellentesque Ultricies Dignissim LLC',3,1,1,'2016-05-11 13:13:23'),(47,14,'Aliquam LLC',2,2,1,'2016-05-11 13:13:23'),(48,8,'Ullamcorper Velit In PC',12,2,1,'2016-05-11 13:13:23'),(49,20,'Donec Vitae PC',7,2,1,'2016-05-11 13:13:23'),(50,6,'Viverra LLP',3,2,1,'2016-05-11 13:13:23'),(51,12,'Posuere LLC',8,2,1,'2016-05-11 13:13:23'),(52,1,'Eu Incorporated',9,2,1,'2016-05-11 13:13:23'),(53,5,'Aliquam Arcu Aliquam Limited',11,1,1,'2016-05-11 13:13:23'),(54,7,'In Corporation',12,1,1,'2016-05-11 13:13:23'),(55,12,'Sem Pellentesque Ut Corp.',4,1,1,'2016-05-11 13:13:23'),(56,8,'Interdum Feugiat Sed Institute',4,2,1,'2016-05-11 13:13:23'),(57,17,'Elit Limited',2,1,1,'2016-05-11 13:13:23'),(58,4,'Suspendisse PC',11,1,1,'2016-05-11 13:13:23'),(59,5,'Ante Blandit Viverra Corporation',7,2,1,'2016-05-11 13:13:23'),(60,20,'In Consectetuer Ipsum Company',7,2,1,'2016-05-11 13:13:23'),(61,5,'Nunc Interdum Feugiat Institute',8,2,1,'2016-05-11 13:13:23'),(62,14,'A Magna Lorem LLP',6,1,1,'2016-05-11 13:13:23'),(63,17,'Donec Consectetuer Corporation',3,1,1,'2016-05-11 13:13:23'),(64,7,'Cras Ltd',3,2,1,'2016-05-11 13:13:23'),(65,5,'Erat Corp.',11,2,1,'2016-05-11 13:13:23'),(66,5,'Vitae Industries',5,2,1,'2016-05-11 13:13:23'),(67,13,'Eu Nibh Corporation',10,1,1,'2016-05-11 13:13:23'),(68,13,'Gravida Limited',2,2,1,'2016-05-11 13:13:23'),(69,16,'Tempus Eu LLC',11,1,1,'2016-05-11 13:13:23'),(70,6,'Erat Etiam Vestibulum Limited',12,1,1,'2016-05-11 13:13:23'),(71,13,'Et Incorporated',7,1,1,'2016-05-11 13:13:23'),(72,14,'Nonummy Inc.',12,1,1,'2016-05-11 13:13:23'),(73,20,'Quam Elementum Foundation',3,2,1,'2016-05-11 13:13:23'),(74,15,'Molestie Corp.',2,2,1,'2016-05-11 13:13:23'),(75,2,'Ipsum LLP',5,2,1,'2016-05-11 13:13:23'),(76,14,'Penatibus Et Corp.',12,2,1,'2016-05-11 13:13:23'),(77,10,'Ut Quam Vel Industries',5,1,1,'2016-05-11 13:13:23'),(78,19,'Donec Tempor Est Ltd',3,1,1,'2016-05-11 13:13:23'),(79,5,'Odio Etiam Ligula Corporation',7,2,1,'2016-05-11 13:13:23'),(80,7,'Mattis LLP',3,2,1,'2016-05-11 13:13:23'),(81,2,'Metus Urna Industries',9,1,1,'2016-05-11 13:13:23'),(82,10,'Sed Foundation',11,1,1,'2016-05-11 13:13:23'),(83,20,'In Lobortis Tellus Consulting',7,1,1,'2016-05-11 13:13:23'),(84,2,'Aliquam Associates',9,1,1,'2016-05-11 13:13:23'),(85,9,'Eros Associates',5,2,1,'2016-05-11 13:13:23'),(86,1,'Id Enim Limited',3,2,1,'2016-05-11 13:13:23'),(87,6,'Velit Egestas Industries',10,2,1,'2016-05-11 13:13:23'),(88,2,'Etiam Laoreet Libero Inc.',10,2,1,'2016-05-11 13:13:23'),(89,14,'A Ultricies Adipiscing LLC',4,1,1,'2016-05-11 13:13:23'),(90,3,'Eros Nam Consequat Ltd',9,2,1,'2016-05-11 13:13:23'),(91,13,'Consequat Purus Maecenas Corp.',9,1,1,'2016-05-11 13:13:23'),(92,18,'A Aliquet Vel PC',3,2,1,'2016-05-11 13:13:23'),(93,4,'Dictum Placerat Corp.',10,2,1,'2016-05-11 13:13:23'),(94,5,'Suspendisse Associates',11,1,1,'2016-05-11 13:13:23'),(95,3,'Ac Orci Foundation',3,2,1,'2016-05-11 13:13:23'),(96,20,'Convallis Foundation',3,1,1,'2016-05-11 13:13:23'),(97,10,'Eu Consulting',11,1,1,'2016-05-11 13:13:23'),(98,4,'Aliquam Fringilla Cursus Institute',12,1,1,'2016-05-11 13:13:23'),(99,11,'Justo Incorporated',11,2,1,'2016-05-11 13:13:23'),(100,4,'Ultrices Vivamus Company',2,2,1,'2016-05-11 13:13:23');
/*!40000 ALTER TABLE `Asignaturas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-21 15:35:41
