CREATE DATABASE  IF NOT EXISTS `omega` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `omega`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: omega
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `bibliotecas`
--

DROP TABLE IF EXISTS `bibliotecas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibliotecas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `publica` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `bibliotecas_ibfk_1` (`id_usuario`),
  CONSTRAINT `bibliotecas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecas`
--

LOCK TABLES `bibliotecas` WRITE;
/*!40000 ALTER TABLE `bibliotecas` DISABLE KEYS */;
INSERT INTO `bibliotecas` VALUES (1,1,'inicial',0);
/*!40000 ALTER TABLE `bibliotecas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_libro` int NOT NULL,
  `id_usuario` int NOT NULL,
  `comentario` text NOT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `comentarios_ibfk_1` (`id_libro`),
  KEY `comentarios_ibfk_2` (`id_usuario`),
  CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (1,1,1,'El libro es maravilloso, la escritora nunca defrauda','2024-02-29 10:51:06');
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios_reportados`
--

DROP TABLE IF EXISTS `comentarios_reportados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios_reportados` (
  `audit_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_creator` varchar(45) NOT NULL DEFAULT 'admi',
  `audit_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_updater` varchar(45) NOT NULL DEFAULT 'admi',
  `id` int NOT NULL AUTO_INCREMENT,
  `id_comentario` int NOT NULL,
  `id_reportante` int NOT NULL,
  `ofensivo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comentarios_reportados_ibfk_1` (`id_comentario`),
  KEY `comentarios_reportados_ibfk_2` (`id_reportante`),
  CONSTRAINT `comentarios_reportados_ibfk_1` FOREIGN KEY (`id_comentario`) REFERENCES `comentarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comentarios_reportados_ibfk_2` FOREIGN KEY (`id_reportante`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios_reportados`
--

LOCK TABLES `comentarios_reportados` WRITE;
/*!40000 ALTER TABLE `comentarios_reportados` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios_reportados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `audit_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_creator` varchar(45) NOT NULL DEFAULT 'admi',
  `audit_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_updater` varchar(45) NOT NULL DEFAULT 'admi',
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES ('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',1,'Accion'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',2,'Aventura'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',3,'Ciencia ficcion'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',4,'Clasicos'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',5,'Espiritual'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',6,'Fantasia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',7,'Humor'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',8,'Misterio'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',9,'Paranormal'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',10,'Romance'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',11,'Suspense'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',12,'Terror'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',13,'Drama'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',14,'Literatura contemporanea'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',15,'Thriller'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',16,'Autoayuda'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',17,'Comics'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',18,'Deportes'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',19,'Desarrollo personal'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',20,'Economia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',21,'Filosofia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',22,'Ficcion'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',23,'Poesia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',24,'Psicologia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',25,'Policiaca'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',26,'Erotica'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',27,'Autoayuda'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',28,'Comics'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',29,'Deportes'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',30,'Desarrollo personal'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',31,'Economia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',32,'Filosofia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',33,'Ficcion'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',34,'Poesia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',35,'Psicologia'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',36,'Policiaca'),('2024-04-27 15:11:20','admi','2024-04-27 15:11:20','admi',37,'Erotica');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos_usuario`
--

DROP TABLE IF EXISTS `generos_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos_usuario` (
  `id_genero` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_genero`,`id_usuario`),
  KEY `generos_usuario_ibfk_2` (`id_usuario`),
  CONSTRAINT `generos_usuario_ibfk_1` FOREIGN KEY (`id_genero`) REFERENCES `generos` (`id`),
  CONSTRAINT `generos_usuario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos_usuario`
--

LOCK TABLES `generos_usuario` WRITE;
/*!40000 ALTER TABLE `generos_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `generos_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `audit_created` timestamp NULL DEFAULT NULL,
  `audit_creator` varchar(45) NOT NULL,
  `audit_updated` timestamp NULL DEFAULT NULL,
  `audit_updater` varchar(45) NOT NULL,
  `id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES ('2024-04-24 19:22:57','omega','2024-04-24 19:22:57','omega',1,'superadmi'),('2024-04-24 19:24:04','omega','2024-04-24 19:24:04','omega',2,'admi'),('2024-04-24 19:24:25','omega','2024-04-24 19:24:25','omega',3,'usuario'),('2024-04-24 19:24:44','omega','2024-04-24 19:24:44','omega',4,'escritor');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `audit_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_creator` varchar(45) NOT NULL DEFAULT 'admi',
  `audit_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_updater` varchar(45) NOT NULL DEFAULT 'admi',
  `id` int NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(45) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `paginas` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES ('2024-04-27 15:13:49','admi','2024-04-27 15:13:49','admi',1,'9788408125617','Llévame a cualquier lugar','Alice Kellen','Una historia de amor y superación que te robará el corazón.','Romance','2015-10-15',320),('2024-04-27 15:13:49','admi','2024-04-27 15:13:49','admi',2,'9788408192299','Un sueño real','Alice Kellen','La vida real no es un cuento de hadas, pero a veces lo parece...','Romance','2016-07-07',352),('2024-04-27 15:13:49','admi','2024-04-27 15:13:49','admi',3,'9788408154052','33 razones para volver a verte','Alice Kellen','A veces, para que las cosas cambien, debemos ser valientes y tomar la iniciativa.','Romance','2017-05-25',336),('2024-04-27 15:13:49','admi','2024-04-27 15:13:49','admi',4,'9788417216130','Perdida en el tiempo','Alice Kellen','Una historia de amor que trasciende las barreras del tiempo.','Romance','2018-09-13',384);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros_biblioteca`
--

DROP TABLE IF EXISTS `libros_biblioteca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros_biblioteca` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_biblioteca` int NOT NULL,
  `id_libro` int NOT NULL,
  `valorado` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `libros_biblioteca_ibfk_1` (`id_biblioteca`),
  KEY `libros_biblioteca_ibfk_2` (`id_libro`),
  CONSTRAINT `libros_biblioteca_ibfk_1` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libros_biblioteca_ibfk_2` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros_biblioteca`
--

LOCK TABLES `libros_biblioteca` WRITE;
/*!40000 ALTER TABLE `libros_biblioteca` DISABLE KEYS */;
INSERT INTO `libros_biblioteca` VALUES (1,1,1,1),(2,1,2,0);
/*!40000 ALTER TABLE `libros_biblioteca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_usuario`
--

DROP TABLE IF EXISTS `perfil_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil_usuario` (
  `audit_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_creator` varchar(45) NOT NULL DEFAULT 'admi',
  `audit_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_updater` varchar(45) NOT NULL DEFAULT 'admi',
  `idperfil` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `informacion` varchar(100) DEFAULT NULL,
  `idUsuario` int NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`idperfil`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_perfil_usuario_idx` (`idUsuario`),
  CONSTRAINT `fk_perfil_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_usuario`
--

LOCK TABLES `perfil_usuario` WRITE;
/*!40000 ALTER TABLE `perfil_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `audit_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_creator` varchar(45) NOT NULL DEFAULT 'admi',
  `audit_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `audit_updater` varchar(45) NOT NULL DEFAULT 'admi',
  `id` int NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `tipo` int NOT NULL DEFAULT '3',
  `publico` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`),
  KEY `fk_grupo_usuario_idx` (`tipo`),
  CONSTRAINT `fk_grupo_usuario` FOREIGN KEY (`tipo`) REFERENCES `grupos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('2024-04-27 15:11:40','admi','2024-04-27 15:11:40','admi',1,'juanpe','Juan','Pérez','1990-01-01','juan@example.com','clave123','123456789',3,0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `crear_perfil_usuario` AFTER INSERT ON `usuarios` FOR EACH ROW BEGIN
  IF NEW.tipo IN (3, 4) THEN
    INSERT INTO perfil_usuario (nombre, informacion, idUsuario, username)
    VALUES ('Perfil inicial', 'Información del perfil', NEW.id, NEW.user);
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `crear_biblioteca_inicial` AFTER INSERT ON `usuarios` FOR EACH ROW BEGIN
  IF NEW.tipo IN (3, 4)  THEN
    INSERT INTO bibliotecas (id_usuario, nombre, publica)
    VALUES (NEW.id, 'Inicial', 0);
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `valoracion_libros`
--

DROP TABLE IF EXISTS `valoracion_libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valoracion_libros` (
  `id_libro` int NOT NULL,
  `valoracion_total` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_libro`),
  CONSTRAINT `valoracion_libros_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoracion_libros`
--

LOCK TABLES `valoracion_libros` WRITE;
/*!40000 ALTER TABLE `valoracion_libros` DISABLE KEYS */;
INSERT INTO `valoracion_libros` VALUES (1,4.5);
/*!40000 ALTER TABLE `valoracion_libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoraciones_usuarios`
--

DROP TABLE IF EXISTS `valoraciones_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valoraciones_usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_libro` int NOT NULL,
  `id_usuario` int NOT NULL,
  `playList` varchar(100) DEFAULT NULL,
  `personaje_fav` varchar(45) DEFAULT NULL,
  `personaje_odiado` varchar(45) DEFAULT NULL,
  `recomendacion` tinyint NOT NULL DEFAULT '1',
  `frase_iconica` varchar(45) DEFAULT NULL,
  `opinion` varchar(100) DEFAULT NULL,
  `puntuacion` double NOT NULL,
  `fechaValoracion` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `valoraciones_usuarios_ibfk_1` (`id_libro`),
  KEY `valoraciones_usuarios_ibfk_2` (`id_usuario`),
  CONSTRAINT `valoraciones_usuarios_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `valoraciones_usuarios_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoraciones_usuarios`
--

LOCK TABLES `valoraciones_usuarios` WRITE;
/*!40000 ALTER TABLE `valoraciones_usuarios` DISABLE KEYS */;
INSERT INTO `valoraciones_usuarios` VALUES (1,1,1,NULL,NULL,NULL,1,NULL,NULL,4.5,NULL);
/*!40000 ALTER TABLE `valoraciones_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `actualizar_puntuacion_libro` AFTER INSERT ON `valoraciones_usuarios` FOR EACH ROW BEGIN
    DECLARE total_puntuaciones INT;
    DECLARE suma_puntuaciones DOUBLE;
    DECLARE nueva_puntuacion DOUBLE;
    DECLARE libro_existente INT;
    
    -- Verificar si el libro ya está registrado en la tabla valoracion_libros
    SELECT COUNT(*) INTO libro_existente
    FROM valoracion_libros
    WHERE id_libro = NEW.id_libro;

    -- Si el libro no está registrado, insertarlo con una valoración inicial de cero
    IF libro_existente = 0 THEN
        INSERT INTO valoracion_libros (id_libro, valoracion_total)
        VALUES (NEW.id_libro, 0.0);
    END IF;

    -- Calcular la suma total de puntuaciones y el número total de puntuaciones para el libro
    SELECT COUNT(*), SUM(puntuacion) INTO total_puntuaciones, suma_puntuaciones
    FROM valoraciones_usuarios
    WHERE id_libro = NEW.id_libro;

    -- Calcular la nueva puntuación promedio
    IF total_puntuaciones > 0 THEN
        SET nueva_puntuacion = suma_puntuaciones / total_puntuaciones;
    ELSE
        SET nueva_puntuacion = 0.0;
    END IF;

    -- Actualizar la puntuación del libro en la tabla valoracion_libros
    UPDATE valoracion_libros
    SET valoracion_total = nueva_puntuacion
    WHERE id_libro = NEW.id_libro;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'omega'
--

--
-- Dumping routines for database 'omega'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-27 17:51:21
