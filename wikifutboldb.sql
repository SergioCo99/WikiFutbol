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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2020-10-30 17:47:13
