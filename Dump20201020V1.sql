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
  PRIMARY KEY (`id_ciudad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
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
  `ciudad_club` varchar(45) DEFAULT NULL,
  `estadio_club` varchar(45) DEFAULT NULL,
  `anoCreacion_club` int DEFAULT NULL,
  `palmares_club` varchar(45) DEFAULT NULL,
  `entrenador_club` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_club`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'Deportivo Alavés','Vitoria','Mendizorroza',1921,'1','Pablo Machín'),(2,'Athletic Club','Bilbao','San Mamés',1898,'2','Gaizka Garitano'),(3,'Atlético de Madrid','Madrid','Wanda Metropolitano',1903,'3','Diego Simeone'),(4,'FC Barcelona','Barcelona','Camp Nou',1899,'4','Ronald Koeman'),(5,'Real Betis','Sevilla','Benito Villamarín',1907,'5','Manuel Pellegrini'),(6,'Cádiz CF','Cádiz','Ramón de Carranza',1910,'6','Álvaro Cervera'),(7,'RC Celta','Vigo','Balaidos',1923,'7','Óscar García'),(8,'SD Eibar','Eibar','Ipurúa',1940,'8','José Luis Mendilibar'),(9,'Elche CF','Elche','Manuel Martínez Valero',1922,'9','José Rojo'),(10,'Getafe CF','Getafe','Coliseum Alfonso Pérez',1983,'10','José Bordalás'),(11,'Granada CF','Granada','Nuevo Los Cármenes',1931,'9','Diego Martinez'),(12,'Huesca FC','Huesca','El Alcoraz',1960,'8','Francisco Rodríguez'),(13,'Levante UD','Valencia','Camilo Cano',1909,'7','Paco López'),(14,'CA Osasuna','Pamplona','El Sadar',1920,'6','Jagoba Arrasate'),(15,'Real Sociedad','San Sebastian','Reale Arena',1909,'5','Imanol Alguacil'),(16,'Real Madrid','Madrid','Santiago Bernabeu',1902,'4','Zinedine Zidane'),(17,'Real Valladolid','Valladolid','José Zorrilla',1928,'3','Sergio González'),(18,'Sevilla FC','Sevilla','Ramón Sánchez-Pizjuán',1890,'2','Julen Lopetegui'),(19,'Valencia CF','Valencia','Estadio de Mestalla',1919,'1','Javi Garcia'),(20,'Villareal CF','Villareal','Estadio de la Cerámica',1923,'1','Unai Emery');
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
  `club_entrenador` varchar(45) DEFAULT NULL,
  `ciudad_entrenador` varchar(45) DEFAULT NULL,
  `formacion_entrenador` varchar(45) DEFAULT NULL,
  `mentalidad_entrenador` enum('Defensiva','Equilibrada','Atacante') DEFAULT NULL,
  PRIMARY KEY (`id_entrenador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenador`
--

LOCK TABLES `entrenador` WRITE;
/*!40000 ALTER TABLE `entrenador` DISABLE KEYS */;
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
  `aforo_estadio` int DEFAULT NULL,
  `anoCreacion_estadio` date DEFAULT NULL,
  `ciudad_estadio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_estadio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadio`
--

LOCK TABLES `estadio` WRITE;
/*!40000 ALTER TABLE `estadio` DISABLE KEYS */;
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
  `valoracion_feedback` int DEFAULT NULL,
  `recomendacion_feedback` varchar(45) DEFAULT NULL,
  `opinion_feedback` varchar(501) DEFAULT NULL,
  PRIMARY KEY (`id_feedback`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,3,'si','holiiiiiiiiii'),(2,3,'si','holiiiiiiiiii'),(4,3,'si','holiiiiiiiiii'),(5,3,'si','holiiiiiiiiii');
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
  `fechaNac_jugador` date DEFAULT NULL,
  `club_jugador` varchar(45) DEFAULT NULL,
  `ciudad_jugador` varchar(45) DEFAULT NULL,
  `posicion_jugador` enum('Delantero','MeterMas') DEFAULT NULL,
  `dorsal_jugador` int DEFAULT NULL,
  `goles_jugador` int DEFAULT NULL,
  `altura_jugador` int DEFAULT NULL,
  `peso_jugador` int DEFAULT NULL,
  `pieFav_jugador` enum('Diestro','Zurdo','Ambidiestro') DEFAULT NULL,
  `valoracion_jugador` int DEFAULT NULL,
  `descripcion_jugador` varchar(45) DEFAULT NULL,
  `voto_jugador` int DEFAULT NULL,
  PRIMARY KEY (`id_jugador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (1,'Messi','1987-06-24','Barsaa','chikilandia','Delantero',10,3,17,15,'Zurdo',99,'e un makina',1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
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
  `jugador_TeamOfTheYear` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_TeamOfTheYear`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teamoftheyear`
--

LOCK TABLES `teamoftheyear` WRITE;
/*!40000 ALTER TABLE `teamoftheyear` DISABLE KEYS */;
/*!40000 ALTER TABLE `teamoftheyear` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=65656567 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'a','a','a',1,'1999-06-23'),(2,'b','b','b',0,'2002-05-03'),(3,'d','d','d',0,'2002-05-03'),(4,'c','c','c',0,'2002-05-03');
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
  `usuario_usuarioVotacion` varchar(45) DEFAULT NULL,
  `delanteroVotado_usuarioVotacion` varchar(45) DEFAULT NULL,
  `medioVotado_usuarioVotacion` varchar(45) DEFAULT NULL,
  `defensaVotado_usuarioVotacion` varchar(45) DEFAULT NULL,
  `porteroVotado_usuarioVotacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_usuarioVotacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariovotacion`
--

LOCK TABLES `usuariovotacion` WRITE;
/*!40000 ALTER TABLE `usuariovotacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuariovotacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-20 12:51:11
