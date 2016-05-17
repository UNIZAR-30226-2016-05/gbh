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
-- Table structure for table `Carrera`
--

DROP TABLE IF EXISTS `Carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Carrera` (
  `idCarrera` int(11) NOT NULL AUTO_INCREMENT,
  `Destino` int(11) NOT NULL,
  `Rama` varchar(100) NOT NULL,
  `Carrera` text NOT NULL,
  `Universidad` text NOT NULL,
  `Idioma` text NOT NULL,
  `Imagen` text,
  `Validado` tinyint(1) NOT NULL,
  `Time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idCarrera`),
  KEY `Destino_idx` (`Destino`),
  KEY `Rama_idx` (`Rama`),
  CONSTRAINT `Destino_Car` FOREIGN KEY (`Destino`) REFERENCES `Destinos` (`idDestino`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Rama_Car` FOREIGN KEY (`Rama`) REFERENCES `Rama` (`idRama`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Carrera`
--

LOCK TABLES `Carrera` WRITE;
/*!40000 ALTER TABLE `Carrera` DISABLE KEYS */;
INSERT INTO `Carrera` VALUES (1,1,'Ingeniería','Computer Science','UNITO','Italiano','http://en.unito.it/sites/sten/files/styles/slider/public/rettorato_dimsliderhp.jpg?itok=cQpWFEGc',1,'2016-05-11 13:13:52'),(2,1,'Ingeniería','Ingeniería Mecánica','POlITO','Italiano','http://internacionalizacion.unitecnologica.edu.co/sites/internacionalizacion.unitecnologica.edu.co/files/poliyoo.jpg',1,'2016-05-11 13:13:52'),(3,2,'Matemáticas','Matemáticas','UCC','Inglés','http://www.bluedolphin.ie/images/ucc.jpg',1,'2016-05-11 13:13:52'),(4,2,'Historia','Computer Science','UCC','Inglés','http://www.bluedolphin.ie/images/ucc.jpg',1,'2016-05-11 13:13:52'),(5,3,'Economía','ADE','Universität Wien','Alemán','http://geschichte.univie.ac.at/files/styles/large/public/bildsammlung/xf4o8011_date061214_pfluegl.jpg?itok=UuEvzogS',1,'2016-05-11 13:13:52'),(6,3,'Matemáticas','Matemáticas','Universität Wien','Alemán','http://geschichte.univie.ac.at/files/styles/large/public/bildsammlung/xf4o8011_date061214_pfluegl.jpg?itok=UuEvzogS',1,'2016-05-11 13:13:52'),(7,4,'Historia','Historia de Artes','Universität der Künste Berlín','Alemán','http://www.hunter.cuny.edu/art/repository/images/udk_berlin2.jpg',1,'2016-05-11 13:13:52'),(8,4,'Filología','Filología Alemana','Freie Universität Berlin','Alemán','http://www.bcp.fu-berlin.de/chemie/chemie/forschung/InorgChem/agmueller/_elemente_fuer_startseite/bilder_slide/FU-Berlin.jpg',1,'2016-05-11 13:13:52'),(9,5,'Ciencias sociales','Trabajo Social','Unizar','Español','http://www.maveres.jazztel.es/fotozaragoza/paraninfo.jpg',1,'2016-05-11 13:13:52'),(10,5,'Ciencias de la salud','Medicina','Unizar','Español','http://www.maveres.jazztel.es/fotozaragoza/paraninfo.jpg',1,'2016-05-11 13:13:52'),(11,6,'Derecho','Derecho','Aalborg University','Inglés','http://mladiinfo.com/wp-content/uploads/2009/12/aalborg_university.jpg',1,'2016-05-11 13:13:52'),(12,6,'Física','Física','Aalborg University','Inglés','http://mladiinfo.com/wp-content/uploads/2009/12/aalborg_university.jpg',1,'2016-05-11 13:13:52'),(13,7,'Geografía','Geografía','Université de Nantes','Francés','https://upload.wikimedia.org/wikipedia/commons/e/ea/Nantes_Universit%C3%A9-ch%C3%A2teau_Tertre.jpg',1,'2016-05-11 13:13:52'),(14,7,'Química','Química','Université de Nantes','Francés','https://upload.wikimedia.org/wikipedia/commons/e/ea/Nantes_Universit%C3%A9-ch%C3%A2teau_Tertre.jpg',1,'2016-05-11 13:13:52'),(15,8,'Veterinaria','Veterinaria','University of Oslo','Inglés','https://upload.wikimedia.org/wikipedia/commons/3/31/Oslo_Universitet_2.jpg',1,'2016-05-11 13:13:52'),(16,8,'Matemáticas','Matemáticas','University of Oslo','Inglés','https://upload.wikimedia.org/wikipedia/commons/3/31/Oslo_Universitet_2.jpg',1,'2016-05-11 13:13:52'),(17,9,'Historia','Historia del Arte','Oxford University','Inglés','http://www.veritas.org/wp-content/uploads/2013/09/University-Oxford.jpg',1,'2016-05-11 13:13:52'),(18,9,'Ingeniería','Ingeniería Informática','Oxford University','Inglés','http://www.veritas.org/wp-content/uploads/2013/09/University-Oxford.jpg',1,'2016-05-11 13:13:52'),(19,10,'Matemáticas','Matemáticas','University of Borås','Inglés','https://studyinsweden.se/wp-content/uploads/2014/03/H%C3%B6gskolan-i-Bor%C3%A5s-_Textilh%C3%B6gskolan_Anna-Sigge-870x514.jpg',1,'2016-05-11 13:13:52'),(20,10,'Historia','Historia ','University of Borås','Inglés','https://studyinsweden.se/wp-content/uploads/2014/03/H%C3%B6gskolan-i-Bor%C3%A5s-_Textilh%C3%B6gskolan_Anna-Sigge-870x514.jpg',1,'2016-05-11 13:13:52'),(21,11,'Elija rama','a','a','Elija idioma','a',1,'2016-05-11 13:13:52'),(22,12,'Elija rama','b','b','Elija idioma','b',1,'2016-05-11 13:13:52'),(23,13,'Ciencias de la salud','a','a','Checo','a',1,'2016-05-11 13:13:52');
/*!40000 ALTER TABLE `Carrera` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-11 13:17:35
