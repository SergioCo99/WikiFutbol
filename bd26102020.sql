CREATE DATABASE  IF NOT EXISTS `wikifutbolschema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `wikifutbolschema`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: wikifutbolschema
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `id_ciudad` int NOT NULL AUTO_INCREMENT,
  `nombre_ciudad` varchar(45) DEFAULT NULL,
  `pais_ciudad` int DEFAULT NULL,
  PRIMARY KEY (`id_ciudad`),
  KEY `pais_ciudad` (`pais_ciudad`),
  CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`pais_ciudad`) REFERENCES `pais` (`id_pais`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Bilbao',1),(2,'San Sebastian',1),(3,'Villarreal',1),(4,'Madrid',1),(5,'Getafe',1),(6,'Cadiz',1),(7,'Granada',1),(8,'Sevilla',1),(9,'Barcelona',1),(10,'Pamplona',1),(11,'Elche',1),(12,'Valencia',1),(13,'Eibar',1),(14,'Huesca',1),(15,'Vigo',1),(16,'Vitoria-Gasteiz',1),(17,'Valladolid',1),(18,'Orio',1),(19,'Hondarribia',1),(20,'La Castellane',2),(21,'Alicante',1),(22,'Malabo',3),(23,'Santiago',4),(24,'Buenos Aires',5),(25,'Zaandam',6),(26,'Asteasu',1),(27,'Silla',1),(28,'Hospitalet de Llobregat',1),(29,'Soria',1),(30,'Sabadell',1),(31,'Berriatua',1),(32,'San Miguel',5),(33,'Zaldibar',1);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `id_club` int NOT NULL AUTO_INCREMENT,
  `nombre_club` varchar(45) DEFAULT NULL,
  `ciudad_club` int DEFAULT NULL,
  `estadio_club` int DEFAULT NULL,
  `anoCreacion_club` int DEFAULT '0',
  `palmares_club` varchar(45) DEFAULT NULL,
  `entrenador_club` int DEFAULT NULL,
  PRIMARY KEY (`id_club`),
  KEY `ciudad_club` (`ciudad_club`),
  KEY `estadio_club` (`estadio_club`),
  KEY `entrenador_club` (`entrenador_club`),
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`ciudad_club`) REFERENCES `ciudad` (`id_ciudad`) ON DELETE CASCADE,
  CONSTRAINT `club_ibfk_2` FOREIGN KEY (`estadio_club`) REFERENCES `estadio` (`id_estadio`) ON DELETE CASCADE,
  CONSTRAINT `club_ibfk_3` FOREIGN KEY (`entrenador_club`) REFERENCES `entrenador` (`id_entrenador`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'Athletic Club',1,1,1898,'0',1),(2,'Real Sociedad',2,2,1908,'0',2),(3,'Villarreal',3,3,1923,'0',3),(4,'Real Madrid',4,5,1902,'0',4),(5,'Huesca',14,12,1960,'0',16),(6,'Elche',11,15,1922,'0',13),(7,'Getafe',5,6,1983,'0',5),(8,'Cadiz',6,4,1909,'0',6),(9,'Granada',7,7,1931,'0',7),(10,'Betis',8,20,1907,'0',8),(11,'Atl. Madrid',4,19,1903,'0',9),(12,'Barcelona',9,18,1899,'0',10),(13,'Sevilla',8,17,1890,'0',11),(14,'Celta',15,11,1927,'0',17),(15,'Alaves',16,10,1921,'0',18),(16,'Levante',12,8,1908,'0',20),(17,'Valladolid',17,9,1928,'0',19),(18,'Eibar',13,13,1940,'0',15),(19,'Valencia',12,14,1919,'0',14),(20,'Osasuna',10,16,1920,'0',12);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrenador`
--

DROP TABLE IF EXISTS `entrenador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenador` (
  `id_entrenador` int NOT NULL AUTO_INCREMENT,
  `nombre_entrenador` varchar(45) DEFAULT NULL,
  `fechaNac_entrenador` date DEFAULT NULL,
  `club_entrenador` int DEFAULT NULL,
  `ciudad_entrenador` int DEFAULT NULL,
  `formacion_entrenador` varchar(45) DEFAULT NULL,
  `mentalidad_entrenador` enum('Defensiva','Equilibrada','Atacante') DEFAULT 'Equilibrada',
  PRIMARY KEY (`id_entrenador`),
  KEY `club_entrenador` (`club_entrenador`),
  KEY `ciudad_entrenador` (`ciudad_entrenador`),
  CONSTRAINT `entrenador_ibfk_1` FOREIGN KEY (`club_entrenador`) REFERENCES `club` (`id_club`) ON DELETE CASCADE,
  CONSTRAINT `entrenador_ibfk_2` FOREIGN KEY (`ciudad_entrenador`) REFERENCES `ciudad` (`id_ciudad`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenador`
--

LOCK TABLES `entrenador` WRITE;
/*!40000 ALTER TABLE `entrenador` DISABLE KEYS */;
INSERT INTO `entrenador` VALUES (1,'Gaizka Garitano','1975-07-09',1,1,'4-3-3','Equilibrada'),(2,'Imanol Alguacil','1971-07-04',2,18,'4-3-3','Equilibrada'),(3,'Unai Emery','1971-11-03',3,19,'4-3-3','Equilibrada'),(4,'Zinedine Zidane','1972-06-23',4,20,'4-3-3','Equilibrada'),(5,'Jose Bordalas','1964-03-05',7,21,'4-3-3','Equilibrada'),(6,'Alvaro Cervera','1965-09-20',8,22,'4-3-3','Equilibrada'),(7,'Diego Martinez','1980-12-16',9,15,'4-3-3','Equilibrada'),(8,'Manuel Pellegrini','1953-09-16',10,23,'4-3-3','Equilibrada'),(9,'Diego Simeone','1970-04-28',11,24,'4-3-3','Equilibrada'),(10,'Ronald Koeman','1963-03-21',12,25,'4-3-3','Equilibrada'),(11,'Julen Lopetegui','1966-09-28',13,26,'4-3-3','Equilibrada'),(12,'Jagoba Arrasate','1978-04-22',20,31,'4-3-3','Equilibrada'),(13,'Jorge Almiron','1971-06-19',6,32,'4-3-3','Equilibrada'),(14,'Javi Gracia','1970-05-01',19,10,'4-3-3','Equilibrada'),(15,'Jose Luis Mendilibar','1961-03-14',18,33,'4-3-3','Equilibrada'),(16,'Michel','1975-10-30',5,4,'4-3-3','Equilibrada'),(17,'Oscar Garcia','1973-04-26',14,30,'4-3-3','Equilibrada'),(18,'Pablo Machin','1975-04-07',15,29,'4-3-3','Equilibrada'),(19,'Sergio','1976-11-10',17,28,'4-3-3','Equilibrada'),(20,'Paco Lopez','1967-09-19',16,27,'4-3-3','Equilibrada');
/*!40000 ALTER TABLE `entrenador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadio`
--

DROP TABLE IF EXISTS `estadio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadio` (
  `id_estadio` int NOT NULL AUTO_INCREMENT,
  `nombre_estadio` varchar(45) DEFAULT NULL,
  `aforo_estadio` int DEFAULT '0',
  `anoCreacion_estadio` int DEFAULT '0',
  `ciudad_estadio` int DEFAULT NULL,
  PRIMARY KEY (`id_estadio`),
  KEY `ciudad_estadio` (`ciudad_estadio`),
  CONSTRAINT `estadio_ibfk_1` FOREIGN KEY (`ciudad_estadio`) REFERENCES `ciudad` (`id_ciudad`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadio`
--

LOCK TABLES `estadio` WRITE;
/*!40000 ALTER TABLE `estadio` DISABLE KEYS */;
INSERT INTO `estadio` VALUES (1,'San Mames',53289,2013,1),(2,'Reale Arena',39500,1993,2),(3,'Estadio de la Ceramica',23500,1923,3),(4,'Estadio Ramon de Carranza',20724,1955,6),(5,'Santiago Bernabeu',81044,1947,4),(6,'Coliseum Alfonso Perez',17393,1998,5),(7,'Estadio Nuevo Los Carmenes',23156,1995,7),(8,'Cuidad de Valencia',26354,1969,12),(9,'Jose Zorrilla',27846,1982,17),(10,'Estadio de Mendizorroza',19840,1924,16),(11,'Municipal de Balaidos',31800,1928,15),(12,'El Alcoraz',7600,1972,14),(13,'Estadio Municipal de Ipurua',8164,1947,13),(14,'Mestalla',55000,1923,12),(15,'Estadio Martinez Valero',36017,1976,11),(16,'Estadio El Sadar',19800,1967,10),(17,'Ramon Sanchez Pizjuan',45500,1958,8),(18,'Camp Nou',98787,1957,9),(19,'Wanda Metropolitano',67942,2017,4),(20,'Benito Villamarin',60721,1929,8);
/*!40000 ALTER TABLE `estadio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id_feedback` int NOT NULL AUTO_INCREMENT,
  `usuario_feedback` int DEFAULT NULL,
  `valoracion_feedback` enum('1','2','3','4','5') DEFAULT '5',
  `recomendacion_feedback` enum('si','no') DEFAULT 'si',
  `opinion_feedback` varchar(501) DEFAULT NULL,
  PRIMARY KEY (`id_feedback`),
  KEY `usuario_feedback` (`usuario_feedback`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`usuario_feedback`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,5,'5','si','buena');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `id_jugador` int NOT NULL AUTO_INCREMENT,
  `nombre_jugador` varchar(45) DEFAULT NULL,
  `fechaNac_jugador` date DEFAULT '0000-00-00',
  `club_jugador` int DEFAULT NULL,
  `ciudad_jugador` int DEFAULT NULL,
  `posicion_jugador` enum('Delantero','Centrocampista','Defensa','Portero') DEFAULT NULL,
  `dorsal_jugador` int DEFAULT NULL,
  `goles_jugador` int DEFAULT NULL,
  `altura_jugador` int DEFAULT NULL,
  `peso_jugador` int DEFAULT NULL,
  `pieFav_jugador` enum('Diestro','Zurdo','Ambidiestro') DEFAULT NULL,
  `valoracion_jugador` int DEFAULT NULL,
  `descripcion_jugador` varchar(45) DEFAULT NULL,
  `voto_jugador` int DEFAULT '0',
  PRIMARY KEY (`id_jugador`),
  KEY `club_jugador` (`club_jugador`),
  KEY `ciudad_jugador` (`ciudad_jugador`),
  CONSTRAINT `jugador_ibfk_1` FOREIGN KEY (`club_jugador`) REFERENCES `club` (`id_club`) ON DELETE CASCADE,
  CONSTRAINT `jugador_ibfk_2` FOREIGN KEY (`ciudad_jugador`) REFERENCES `ciudad` (`id_ciudad`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (1,'Alex Berenguer','0000-00-00',NULL,NULL,'Delantero',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,'Kenan Kodro','0000-00-00',NULL,NULL,'Delantero',NULL,0,NULL,NULL,NULL,NULL,NULL,0),(3,'Asier Villalibre','0000-00-00',NULL,NULL,'Delantero',NULL,0,NULL,NULL,NULL,NULL,NULL,0),(4,'Iñaki Williams','0000-00-00',NULL,NULL,'Delantero',NULL,0,NULL,NULL,NULL,NULL,NULL,1),(5,'Raul Garcia','0000-00-00',NULL,NULL,'Centrocampista',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(6,'Iker Muniain','0000-00-00',NULL,NULL,'Centrocampista',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(7,'Unai Lopez','0000-00-00',NULL,NULL,'Centrocampista',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(8,'Dani Garcia','0000-00-00',NULL,NULL,'Centrocampista',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(9,'Iñigo Martinez','0000-00-00',NULL,NULL,'Defensa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(10,'Yuri Berchiche','0000-00-00',NULL,NULL,'Defensa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(11,'Unai Nuñez','0000-00-00',NULL,NULL,'Defensa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(12,'Yeray Alvarez','0000-00-00',NULL,NULL,'Defensa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(13,'Ander Capa','0000-00-00',NULL,NULL,'Defensa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(14,'Unai Simon','0000-00-00',NULL,NULL,'Portero',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(15,'Iago Herrerin','0000-00-00',NULL,NULL,'Portero',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `id_pais` int NOT NULL AUTO_INCREMENT,
  `nombre_pais` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'España'),(2,'Francia'),(3,'Guinea Ecuatorial'),(4,'Chile'),(5,'Argentina'),(6,'Paises Bajos');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teamoftheyear`
--

DROP TABLE IF EXISTS `teamoftheyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teamoftheyear` (
  `id_TeamOfTheYear` int NOT NULL AUTO_INCREMENT,
  `jugador_TeamOfTheYear` int DEFAULT NULL,
  PRIMARY KEY (`id_TeamOfTheYear`),
  KEY `jugador_TeamOfTheYear` (`jugador_TeamOfTheYear`),
  CONSTRAINT `teamoftheyear_ibfk_1` FOREIGN KEY (`jugador_TeamOfTheYear`) REFERENCES `jugador` (`id_jugador`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teamoftheyear`
--

LOCK TABLES `teamoftheyear` WRITE;
/*!40000 ALTER TABLE `teamoftheyear` DISABLE KEYS */;
INSERT INTO `teamoftheyear` VALUES (2,2),(3,3),(1,4),(5,5),(6,6),(4,8),(8,9),(9,10),(10,11),(7,13),(11,15);
/*!40000 ALTER TABLE `teamoftheyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `teamoftheyear_view`
--

DROP TABLE IF EXISTS `teamoftheyear_view`;
/*!50001 DROP VIEW IF EXISTS `teamoftheyear_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `teamoftheyear_view` AS SELECT 
 1 AS `id_teamoftheyear`,
 1 AS `id_jugador`,
 1 AS `nombre_jugador`,
 1 AS `posicion_jugador`,
 1 AS `voto_jugador`,
 1 AS `goles_jugador`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(45) DEFAULT NULL,
  `correo_usuario` varchar(45) DEFAULT NULL,
  `contrasena_usuario` varchar(45) DEFAULT NULL,
  `admin_usuario` int DEFAULT '0',
  `fechaNac_usuario` date DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'na','a','a',1,'1999-06-23'),(2,'nb','b','b',0,'1999-06-24'),(4,'nc','c','c',0,'1999-06-26'),(5,'Eneko','eneko.perez23@gmail.com','12345',1,'2020-10-01');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariovotacion`
--

DROP TABLE IF EXISTS `usuariovotacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuariovotacion` (
  `id_usuarioVotacion` int NOT NULL AUTO_INCREMENT,
  `usuario_usuarioVotacion` int DEFAULT NULL,
  `delanteroVotado_usuarioVotacion` int DEFAULT NULL,
  `centrocampistaVotado_usuarioVotacion` int DEFAULT NULL,
  `defensaVotado_usuarioVotacion` int DEFAULT NULL,
  `porteroVotado_usuarioVotacion` int DEFAULT NULL,
  PRIMARY KEY (`id_usuarioVotacion`),
  KEY `usuario_usuarioVotacion` (`usuario_usuarioVotacion`),
  KEY `delanteroVotado_usuarioVotacion` (`delanteroVotado_usuarioVotacion`),
  KEY `defensaVotado_usuarioVotacion` (`defensaVotado_usuarioVotacion`),
  KEY `porteroVotado_usuarioVotacion` (`porteroVotado_usuarioVotacion`),
  KEY `usuariovotacion_ibfk_3` (`centrocampistaVotado_usuarioVotacion`),
  CONSTRAINT `usuariovotacion_ibfk_1` FOREIGN KEY (`usuario_usuarioVotacion`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE,
  CONSTRAINT `usuariovotacion_ibfk_2` FOREIGN KEY (`delanteroVotado_usuarioVotacion`) REFERENCES `jugador` (`id_jugador`) ON DELETE CASCADE,
  CONSTRAINT `usuariovotacion_ibfk_3` FOREIGN KEY (`centrocampistaVotado_usuarioVotacion`) REFERENCES `jugador` (`id_jugador`) ON DELETE CASCADE,
  CONSTRAINT `usuariovotacion_ibfk_4` FOREIGN KEY (`defensaVotado_usuarioVotacion`) REFERENCES `jugador` (`id_jugador`) ON DELETE CASCADE,
  CONSTRAINT `usuariovotacion_ibfk_5` FOREIGN KEY (`porteroVotado_usuarioVotacion`) REFERENCES `jugador` (`id_jugador`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariovotacion`
--

LOCK TABLES `usuariovotacion` WRITE;
/*!40000 ALTER TABLE `usuariovotacion` DISABLE KEYS */;
INSERT INTO `usuariovotacion` VALUES (11,1,4,8,13,15);
/*!40000 ALTER TABLE `usuariovotacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `teamoftheyear_view`
--

/*!50001 DROP VIEW IF EXISTS `teamoftheyear_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `teamoftheyear_view` AS select `teamoftheyear`.`id_TeamOfTheYear` AS `id_teamoftheyear`,`jugador`.`id_jugador` AS `id_jugador`,`jugador`.`nombre_jugador` AS `nombre_jugador`,`jugador`.`posicion_jugador` AS `posicion_jugador`,`jugador`.`voto_jugador` AS `voto_jugador`,`jugador`.`goles_jugador` AS `goles_jugador` from (`teamoftheyear` join `jugador`) where (`jugador`.`id_jugador` = `teamoftheyear`.`jugador_TeamOfTheYear`) order by `teamoftheyear`.`id_TeamOfTheYear`,`jugador`.`voto_jugador`,`jugador`.`goles_jugador` */;
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

-- Dump completed on 2020-10-26 11:57:31
