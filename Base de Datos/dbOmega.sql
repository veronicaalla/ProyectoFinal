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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecas`
--

LOCK TABLES `bibliotecas` WRITE;
/*!40000 ALTER TABLE `bibliotecas` DISABLE KEYS */;
INSERT INTO `bibliotecas` VALUES (1,1,'inicial',1),(2,1,'Saga H',1),(4,1,'Todo lo que somos',1),(5,49,'Inicial',0),(8,50,'Inicial',0),(9,51,'Inicial',0),(10,52,'Favoritos',1),(11,52,'Psicologicos',0),(12,52,'Romance',0),(13,54,'Inicial',0),(44,85,'Inicial',0),(45,86,'Inicial',0),(46,86,'pueba',0),(47,87,'Inicial',0),(48,88,'Inicial',0),(49,88,'Nueva',0),(50,89,'Inicial',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (1,1,1,'Me llevo a sus viajes a sus creencias','2024-05-13 18:36:23'),(6,51,86,'increible','2024-06-09 18:21:56'),(8,54,86,'wwooowww inceible','2024-06-09 18:23:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios_reportados`
--

LOCK TABLES `comentarios_reportados` WRITE;
/*!40000 ALTER TABLE `comentarios_reportados` DISABLE KEYS */;
INSERT INTO `comentarios_reportados` VALUES ('2024-05-13 18:48:10','admi','2024-05-13 18:48:10','admi',1,1,2,NULL),('2024-06-09 16:23:21','Profesores','2024-06-09 16:23:21','Profesores',5,8,86,NULL);
/*!40000 ALTER TABLE `comentarios_reportados` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `eliminar_comentario_ofensivo` AFTER UPDATE ON `comentarios_reportados` FOR EACH ROW BEGIN
    IF NEW.ofensivo = 1 THEN
        DELETE FROM comentarios WHERE id = NEW.id_comentario;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
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
INSERT INTO `generos` VALUES (1,'Accion'),(2,'Aventura'),(3,'Ciencia ficcion'),(4,'Clasicos'),(5,'Espiritual'),(6,'Fantasia'),(7,'Humor'),(8,'Misterio'),(9,'Paranormal'),(10,'Romance'),(11,'Suspense'),(12,'Terror'),(13,'Drama'),(14,'Literatura contemporanea'),(15,'Thriller'),(16,'Autoayuda'),(17,'Comics'),(18,'Deportes'),(19,'Desarrollo personal'),(20,'Economia'),(21,'Filosofia'),(22,'Ficcion'),(23,'Poesia'),(24,'Psicologia'),(25,'Policiaca'),(26,'Erotica'),(27,'Medicina');
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
  CONSTRAINT `generos_usuario_ibfk_1` FOREIGN KEY (`id_genero`) REFERENCES `generos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `generos_usuario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos_usuario`
--

LOCK TABLES `generos_usuario` WRITE;
/*!40000 ALTER TABLE `generos_usuario` DISABLE KEYS */;
INSERT INTO `generos_usuario` VALUES (10,52),(24,52),(4,85),(9,85),(10,85),(12,85),(13,85),(21,85),(10,86),(11,86),(24,86),(24,87),(5,88),(7,88),(15,88),(19,88),(24,88);
/*!40000 ALTER TABLE `generos_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
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
INSERT INTO `grupos` VALUES (1,'superadmi'),(2,'admi'),(3,'usuario'),(4,'escritor');
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
  `genero` int NOT NULL,
  `descripcion` varchar(2000) NOT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `paginas` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`),
  UNIQUE KEY `UK527hiej00hlnstbijkew52aus` (`ISBN`),
  KEY `fk_libros_generos_idx` (`genero`),
  CONSTRAINT `fk_libros_genero` FOREIGN KEY (`genero`) REFERENCES `generos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES ('2024-04-27 15:13:49','admi','2024-05-14 17:30:15','1',1,'9788408125617','Llévame a cualquier lugar','Alice Kellen',3,'descripcion libro','2015-10-15',320),('2024-04-27 15:13:49','admi','2024-05-25 08:08:15','1',2,'9788408192299','Un sueño real','Megan Maxwell',11,'La vida real no es un cuento de hadas, pero a veces lo parece...','2016-07-07',352),('2024-04-27 15:13:49','admi','2024-04-27 15:13:49','admi',3,'9788408154052','33 razones para volver a verte','Alice Kellen',3,'A veces, para que las cosas cambien, debemos ser valientes y tomar la iniciativa.','2017-05-25',336),('2024-04-27 15:13:49','admi','2024-04-27 15:13:49','admi',4,'9788417216130','Perdida en el tiempo','Alice Kellen',3,'Una historia de amor que trasciende las barreras del tiempo.','2018-09-13',384),('2024-05-27 18:53:11','admi','2024-06-15 09:10:43','2',8,'9788408262411','Save 1: Save me','Mona Kasten',10,'Ruby tiene dos objetivos para este curso: trabajar duro para lograr el acceso a Oxford y pasar desapercibida para sus elitistas compañeros de Maxton Hill.Pero cuando en su vida se cruce el atractivo, rico y arrogante James Beaufort, todo lo que había planeado saltara por los aires y su vida cambiara para siempre.','2024-05-08',416),('2024-05-27 18:56:15','admi','2024-05-27 18:56:15','admi',9,'9788408262428','Save 2: Save you','Mona Kasten',10,'¿Qué hacer cuando te rompen el corazon? Ruby no quiere volver a ver a James nunca más. Lo que le ha hecho es imperdonable. Pero James está destrozado, su mundo se tambalea y está a punto de romperse en pedazos. ¿Sera capaz Ruby de mantener su promesa sobre todo cuando su corazón le dicta algo muy distinto?','2024-05-08',400),('2024-05-27 18:56:15','admi','2024-05-27 18:56:15','admi',10,'9788408262435','Save 3: Save us','Mona Kasten',10,'Ruby no puede creer lo que ha pasado: la peor de sus pesadillas se ha hecho realidad y ha sido expulsada de Maxton Hall. Pero lo que la ha destrozado es saber que James parecer ser el responsable de todo. Descubre el gran final de la apasionante historia de amor y odio entre Ruby y James.','2024-05-08',400),('2024-05-27 19:06:41','admi','2024-05-27 19:06:41','admi',17,'9788408287797','El mapa de los anhelos','Alice Kellen',10,'¿Y si te diesen un mapa para descubrir quién eres? ¿Seguirías la ruta marcada hasta el final? Imagina que estás destinada a salvar a tu hermana, pero al final ella muere y la razón de tu existencia se desvanece. Eso es lo que le ocurre a Grace Peterson, la chica que siempre se ha sentido invisible, la que nunca ha salido de Nebraska, la que colecciona palabras y ve pasar los días refugiada en la monotonía. Hasta que llega a sus manos el juego de El mapa de los anhelos y, siguiendo las instrucciones, lo primero que debe hacer es encontrar a alguien llamado Will Tucker, del que nunca ha oído hablar y que está a punto de embarcarse con ella en un viaje directo al corazón, lleno de vulnerabilidades y sueños olvidados, anhelos y afectos inesperados.','2024-05-08',496),('2024-05-27 19:06:41','admi','2024-05-27 19:06:41','admi',18,'9788416622788','El dia que dejo de nevar en alaska','Alice Kellen',10,'Un chico con el corazón de hielo. Una chica que huye de sí misma. Dos destinos que se cruzan. Heather cree que solo hay tres cosas que sabe hacer: atraer problemas, salir huyendo y correr. Así es como termina en Alaska, en un pequeño pueblo perdido, trabajando de camarera mientras intenta llevar una vida nueva y tranquila. Su único problema es que uno de los dueños del restaurante parece odiarla y que ella nunca antes ha conocido a nadie que despierte tanto su curiosidad. Nilak es reservado, frío y distante, pero Heather puede ver a traves de todas las capas tras las que se esconde y sabe que en ocasiones hay recuerdos que pesan demasiado; como los de sus propios errores, esos que intenta dejar atrás. Pero, a veces, la vida te da una segunda oportunidad. La nieve empieza a derretirse. Y todo encaja.','2022-05-03',416),('2024-05-27 19:06:41','admi','2024-05-27 19:06:41','admi',19,'9788408283850','Donde todo brilla','Alice Kellen',10,'Nicki Aldrich y River Jackson han sido inseparables desde que llegaron al mundo con cuarenta y siete minutos de diferencia. Ella lo hizo envuelta en polvo de hadas. Él como si fuese un meteoro en llamas. El pequeño pueblo costero donde crecieron se convirtió en el escenario de sus paseos en bicicleta, las tardes en la casa del árbol y los primeros amores, secretos y dudas. Sin embargo, con el paso de los años, River sueña con escapar de aquel rincón perdido donde todo gira alrededor de la tradicional pesca de la langosta y Nicki anhela encontrar su lugar en el mundo. Pero ¿qué ocurre cuando nada sale como lo habían planeado? ¿Es posible elegir dos caminos distintos y, pese a todo, encontrarse en el final del trayecto? Para lograrlo, River y Nicki tendrán que bucear en las profundidades del corazón, rescatar pedazos de lo que fueron y entender aquello que rompieron. Y quizá así, uniendo y encajando cada fragmento, logren descubrir quiénes son ahora y recordar el brillo de las cosas intangibles. ','2024-01-31',592),('2024-05-27 19:06:41','admi','2024-05-27 19:06:41','admi',20,'9788408237389','Nosotros en la luna','Alice Kellen',10,'No hay nada más eterno que un encuentro fugaz. Una noche en París. Dos caminos entrelazándose. Cuando Rhys y Ginger se conocen en las calles de la ciudad de la luz, no imaginan que sus vidas se unirán para siempre, a pesar de la distancia y de que no puedan ser más diferentes. Ella vive en Londres y a veces se siente tan perdida que se ha olvidado hasta de sus propios sueños. Él es incapaz de quedarse quieto en ningún lugar y cree saber quién es. Y cada noche su amistad crece entre e-mails llenos de confidencias, dudas e inquietudes. Pero ¿qué ocurre cuando el paso del tiempo pone a prueba su relación? ¿Es posible colgarse de la luna junto a otra persona sin poner en riesgo el corazón? ','2021-01-27',480),('2024-05-27 19:10:35','admi','2024-05-27 19:10:35','admi',21,'9788408221951','Deja que ocurra 1: Todo lo que nunca fuimos','Alice Kellen',10,'Porque a veces basta con un «deja que ocurra» para arriesgarlo todo. Leah está rota. Leah ya no pinta. Leah es un espejismo desde el accidente que se llevó a sus padres.','2020-01-16',352),('2024-05-27 19:10:35','admi','2024-05-27 19:10:35','admi',22,'9788408221968','Deja que ocurra 2: Todo lo que somos juntos','Alice Kellen',10,'Han pasado tres años desde la última vez que se vieron. Ahora, Leah está a punto de cumplir su sueño de exponer en una galería. Y, pese al pasado, Axel necesita formar parte de un momento como ese. Cuando sus caminos vuelven a cruzarse, Leah tiene que tomar decisiones que pueden cambiarlo todo, porque, a pesar de lo que ocurrió, los recuerdos de toda su vida siguen ahí; intactos, bonitos, únicos. Colándose en cada grieta que aún no ha cerrado. Porque él sigue siendo el chico que aún no ha olvidado.','2020-01-16',400),('2024-05-27 19:24:09','admi','2024-05-27 19:24:09','admi',43,'9788491296874','Esnob','Elisabet Benavent',10,'Imagina: eres un tiburón de las finanzas estilo lobo de Wall Street, perteneces a una buena familia y siempre lo has tenido todo; por no hablar de que no hay chica que se te resista. Y cuando estás a punto de rozar la cumbre del exito con las yemas de los dedos, lo pierdes todo... por tu culpa. Tu única salida es volver a empezar y ahí estás, con tu traje esnob, en un polígono industrial en tu primer día como ceniciento. Pero, tranquilo, Alejo, que este no es el cuento de siempre, ¿o tal vez sí?','2024-06-04',600),('2024-05-27 19:24:09','admi','2024-05-27 19:24:09','admi',44,'9788466374040','Como (no) escribi nuestra historia','Elisabet Benavent',10,'Una nueva forma de leer el amor. Porque a veces la verdad (no) es solo aquello que queremos creer. Elsa Benavides es una escritora de exito con una crisis creativa y una obsesión: matar al personaje que la catapultó al exito. Pero la solución a sus problemas no pasa por electrocutar a Valentina con un móvil en la bañera. Es la punta del iceberg de una herida más profunda. Decidida a huir para volver a abrazar la escritura, se topa con Darío, un músico recien llegado de París que además es su vecino. Empieza así una nueva historia en la que Elsa es la protagonista. ¿Será capaz de contarlo todo?','2024-03-21',592),('2024-05-27 19:24:09','admi','2024-05-27 19:24:09','admi',45,'9788491291916','Un cuento perfecto','Elisabet Benavent',10,'¿Que sucede cuando descubres que el final de tu cuento no es como soñabas? - Érase una vez una mujer que lo tenía todo y un chico que no tenía nada. - Érase una vez una historia de amor entre el exito y la duda. - Érase una vez un cuento perfecto.','2020-02-20',640),('2024-05-27 19:24:09','admi','2024-05-27 19:24:09','admi',46,'9788491297192','Los abrazos lentos','Elisabet Benavent',10,'\"Las palabras son para mí un salvavidas; una forma de vivir, un puñado de abrazos lentos\".','2022-11-10',528),('2024-05-27 19:24:09','admi','2024-05-27 19:24:09','admi',47,'9788466359375','El arte de engañar al karma','Elisabet Benavent',10,'Una aspirante a actriz cansada de hacer castings... Un artista reconocido en plena crisis creativa... Unos valiosos cuadros encontrados en un desván... Y el arte del engaño para cambiar las leyes del karma.','2022-03-03',696),('2024-05-27 19:33:01','admi','2024-05-27 19:33:01','admi',48,'9788425432026','El hombre en busca de sentido','Viktor E. Frankl',24,'En esta obra, Viktor E. Frankl explica la experiencia que le llevó al descubrimiento de la logoterapia. Prisionero, durante mucho tiempo, en los desalmados campos de concentración, él mismo sintió en su propio ser lo que significaba una existencia desnuda. ¿Cómo pudo él que todo lo había perdido, que había visto destruir todo lo que valía la pena, que padeció hambre, frío, brutalidades sin fin, que tantas veces estuvo a punto del exterminio, cómo pudo aceptar que la vida fuera digna de vivirla? El psiquiatra que personalmente ha tenido que enfrentarse a tales rigores merece que se le escuche, pues nadie como él para juzgar nuestra condición humana sabia y compasivamente. Las palabras del doctor Frankl alcanzan un temple sorprendentemente esperanzador sobre la capacidad humana de trascender sus dificultades y descubrir la verdad conveniente y orientadora.','2015-12-04',168),('2024-05-27 19:33:01','admi','2024-05-27 19:33:01','admi',49,'9788466377119','El poder de las palabras','Mariano Sigman',24,'Háblate bien. Gestiona tus emociones y mejora tu vida a traves del poder de las palabras.Aprende con Mariano Sigman, uno de los neurocientíficos más destacados del mundo, cómo la conversación es la fábrica de ideas más extraordinaria para tu desarrollo personal. Nuestra mente es mucho más maleable de lo que pensamos. Aunque nos resulte sorprendente, conservamos durante toda la vida la misma capacidad de aprender que teníamos cuando eramos niños. Lo que sí perdemos con el paso del tiempo es la necesidad y la motivación para aprender, de modo que vamos construyendo sentencias sobre lo que no podemos ser: el que está convencido de que las matemáticas no son lo suyo, la que siente que no nació para la música, la que cree que no puede manejar su enfado y el que no puede superar sus miedos. Demoler estas creencias es el punto de partida para mejorar cualquier cosa, en cualquier momento de la vida. Esta es la buena noticia: se pueden cambiar ideas y sentimientos, aun aquellos que están profundamente arraigados. La mala noticia es que para transformarlos no basta con proponerselo. Así como concluimos a la velocidad de un rayo si una persona nos parece confiable, inteligente o divertida, tambien los juicios sobre nosotros mi.','2024-05-09',352),('2024-05-27 19:33:01','admi','2024-05-27 19:33:01','admi',50,'9788410079267','Prepárate para la vida','Alvaro Bilvao',24,'¿Cómo construir una vida feliz? ¿Qué camino tomar en los estudios? ¿Qué pasa con el alcohol y las drogas? ¿Cómo gestionar el primer amor o el primer rechazo? ¿Cómo sortear relaciones tóxicas y rodearse de buenos amigos? ¿Por qué es importante saber apagar las pantallas? ¿Cuánto debería dormir?  ','2024-02-28',232),('2024-05-27 19:33:01','admi','2024-05-27 19:33:01','admi',51,'9788490322505','Pensar rápido, pensar despacio','Daniel Kahneman',24,'En Pensar rápido, pensar despacio, un exito internacional, Kahneman nos ofrece una revolucionaria perspectiva del cerebro y explica los dos sistemas que modelan cómo pensamos. Daniel Kahneman, uno de los pensadores más importantes del mundo, recibió el premio Nobel de Economía por su trabajo pionero en psicología sobre el modelo racional de la toma de decisiones. Sus ideas han tenido un profundo impacto en campos tan diversos como la economía, la medicina o la política, pero hasta ahora no había reunido la obra de su vida en un libro. En este libro Kahneman expone la extraordinaria capacidad (y tambien los errores y los sesgos) del pensamiento rápido, y revela la duradera influencia de las impresiones intuitivas sobre nuestro pensamiento y nuestraconducta. Toca muchos temas que nos afectan en el día a día: el impacto de la aversión a la perdida y el exceso de confianza en las estrategias empresariales, la dificultad de predecir lo que nos hará felices en el futuro, el reto de enmarcar adecuadamente los riesgos en el trabajo y en el hogar','2013-09-19',672),('2024-05-27 19:33:01','admi','2024-05-27 19:33:01','admi',52,'9788416429561','El cerebbro del niño explicado a los padres','Alvaro Bilbao',24,'Durante los seis primeros años de vida el cerebro infantil tiene un potencial que no volverá a tener. Esto no quiere decir que debamos intentar convertir a los niños en pequeños genios, porque además de resultar imposible, un cerebro que se desarrolla bajo presión puede perder por el camino parte de su esencia. Este libro es un manual práctico que sintetiza los conocimientos que la neurociencia ofrece a los padres y educadores, con el fin de que puedan ayudar a los niños a alcanzar un desarrollo intelectual y emocional pleno. ','2015-09-10',296),('2024-05-27 19:40:40','admi','2024-05-27 19:40:40','admi',53,'9788408281566','El síndrome de la chica buena','Marta Martinez Novoa',24,'La psicóloga Marta Martínez Novoa nos invita a liberarnos del síndrome de la chica buena para priorizar nuestro bienestar emocional y establecer relaciones auténticas y equilibradas. Libérate del síndrome de la chica buena y reconecta contigo misma y con lo que realmente quieres tú. Si tienes la sensación de ser siempre la última de tu lista, si te cuesta tomar decisiones por lo que puedan pensar los demás, si te empeñas en cumplir siempre sus expectativas y la palabra «no» es inexistente en tu vocabulario, si te obligas a seguir en relaciones que no te aportan porque no quieres hacer daño? seguramente padeces el síndrome de la chica buena. En principio, ser buena no tiene nada de malo, pero puede convertirse en un problema cuando esa «bondad» se traduce en dificultades para poner límites, afrontar conflictos y defender tus valores; en definitiva, para ser quien tú quieres y no lo que quieren los demás. Pero ya está bien de vivir solo para dar, de no poder equivocarte, de tener que hacerte pequeña para que otros ocupen más espacio, de tener que apagar tu luz como si eso fuese a hacer que otros brillen más.','2024-02-07',336),('2024-05-27 19:40:40','admi','2024-05-27 19:40:40','admi',54,'9788418118036','Habitos atomicos','James Clear',24,'A menudo pensamos que para cambiar de vida tenemos que pensar en hacer cambios grandes. Nada más lejos de la realidad. Según el reconocido experto en hábitos James Clear, el cambio real proviene del resultado de cientos de pequeñas decisiones: hacer dos flexiones al día, levantarse cinco minutos antes o hacer una corta llamada telefónica. Clear llama a estas decisiones “hábitos atómicos”: tan pequeños como una partícula, pero tan poderosos como un tsunami. En este libro innovador nos revela exactamente cómo esos cambios minúsculos pueden crecer hasta llegar a cambiar nuestra carrera profesional, nuestras relaciones y todos los aspectos de nuestra vida.','2020-09-08',336),('2024-05-27 19:40:40','admi','2024-05-27 19:40:40','admi',55,'9788467071320','Recupera tu mente, reconquista tu vida','Marian Rojas',24,'Cada vez estamos más impacientes e irritables y toleramos menos el dolor. ¿Notas que te cuesta más prestar atención? ¿Quién no ha sentido ansiedad en el último año? ¿Quién no tolera peor el aburrimiento y el dolor?  ¿Notas que te cuesta más prestar atención? Vivimos en la era de la gratificación instantánea, en la cultura de la inmediatez y las recompensas, buscamos la felicidad a golpe de clic.  Llevamos una vida agitada e intensa, y con el modo fast activado. Somos drogodependientes emocionales inundados de múltiples distracciones. Todo esto tiene un impacto en nuestra capacidad de prestar atención a lo importante, de profundizar y de concentrarnos. La buena noticia es que podemos rescatar la atención perdida, volver a reconectar con nosotros mismos y con todo lo maravilloso que nos rodea para encontrar ese equilibrio emocional que tanto ansiamos.','2024-04-03',384),('2024-05-27 19:40:40','admi','2024-05-27 19:40:40','admi',56,'9788467053302','Cómo hacer que te pasen cosas buenas','Marian Rojas',24,'Uniendo el punto de vista científico, psicológico y humano, la autora nos ofrece una reflexión profunda, salpicada de útiles consejos y con vocación eminentemente didáctica, acerca de la aplicación de nuestras propias capacidades al empeño de procurarnos una existencia plena y feliz: conocer y optimizar determinadas zonas del cerebro, fijar metas y objetivos en la vida, ejercitar la voluntad, poner en marcha la inteligencia emocional, desarrollar la asertividad, evitar el exceso de autocrítica y autoexigencia, reivindicar el papel del optimismo…','2018-10-09',232),('2024-05-27 19:40:40','admi','2024-05-27 19:40:40','admi',57,'9788402429582','Me quiero, te quiero','Maria Esclapez',24,'\"El amor verdadero no se encuentra, se construye\". Ni el amor propio, ni el amor hacia los demás. Y ahora puedes empezar a hacerlo con este cuaderno. Un cuadernillo de psicología donde encontrarás diferentes ejercicios guiados por la autora para aliviar tu malestar, potenciar tu autoestima, conocerte mejor y cuidar de tu salud mental de la mano de la psicóloga del momento.','2024-06-06',128),('2024-05-27 19:40:40','admi','2024-05-27 19:40:40','admi',58,'9788402427793','Tu eres tu ligar seguro','Maria Esclapez',24,'Un nuevo libro para comprender tu historia, hacer las paces con el  pasado, reconectar contigo y los que te rodean y hacer de ti tu lugar seguro. «¿Por qué siento lo que siento? ¿De dónde viene mi malestar? ¿Cómo he  aprendido a relacionarme con los demás y conmigo mismo? ¿Cómo influye el  tipo de apego que desarrollamos en la infancia en nuestra vida adulta?  Y, lo más importante: ¿cómo puedo sanar mis heridas y vivir en paz en mi presente?»','2023-02-09',296),('2024-06-09 19:04:53','admi','2024-06-09 19:04:53','admi',64,'9788408268444','La hija del rey pirata','Tricia Levenseller',1,'No corras el peligro de menospreciar a Alosa: es la hija del Rey Pirata, y cuando se propone algo, lo consigue. Así que cuando su padre le asigna una importante misión, no tendrá el más mínimo reparo en manipular, mentir y pasar por encima de quien haga falta para llevarla a cabo. Y si para conseguir su objetivo debe dejarse atrapar por Riden, un pirata con muy mala reputación, lo hará. Porque Alosa está convencida de que Riden no es un rival a su altura... hasta que empieza a ver que es mucho más astuto de lo que creía. Y también le resulta inesperadamente atractivo... Pronto la tensión entre ellos se convierte en una tormenta de sentimientos en la que ninguno de los dos quiere hundirse. Porque cuando dos piratas se encuentran, solo hay una salida posible: uno de los dos ha de naufragar.','2023-04-26',288),('2024-06-09 19:04:53','admi','2024-06-09 19:04:53','admi',65,'9788412477009','Perdidos en nunca jamás','Aiden Thomas',1,'Cuando los niños de Astoria empiezan a desaparecer en extrañas circunstancias, el caso de los Darling vuelve a ser el foco de todas las miradas. Siguen preguntándose por qué, tras desaparecer hace cinco años, Wendy regresó pero sus hermanos no.Ahora, cuando esa herida parece más reciente que nunca, y con la investigación de nuevo sobre su familia, se encontrará con un chico que cae directamente del cielo.Un chico imposible. Uno que no debería existir fuera de las historias que Wendy les contaba a sus hermanos antes de dormir. Uno que le dice que, si no actúan pronto, los niños perdidos correrán la misma suerte que John y Michael.','2022-03-14',400),('2024-06-09 19:06:32','admi','2024-06-09 19:06:32','admi',66,'9788418027857','Una para todas','Lillie Lainoff',1,'Tania de Batz nunca se siente más ella misma que cuando tiene un florete en la mano. Todos la consideran una chica débil, esa pobrecita sacudida siempre por constantes mareos. Su madre está desesperada tratando de encontrarle un marido que la proteja. Pero lo que Tania quiere es convertirse una espadachina tan diestra como su padre, el exmosquetero. Cuando él es misteriosamente asesinado, su último deseo es que Tania estudie en una academia de señoritas. Pero resulta que L\'Académie des Mariées no es tal cosa: se trata de un cuartel secreto donde entrenan a un nuevo tipo de mosquetero: cortesanas que esconden dagas bajo sus faldas y seducen a los hombres para que les confíen peligrosos secretos, por el bien de Francia, claro. Y tampoco se acobardan si toca batirse en duelo... Con sus nuevas hermanas de armas a su lado, Tania por fin siente que ha encontrado su lugar en el mundo. Pero entonces conoce a Étienne, un misterioso joven con el que se cruza en su primera misión. Es amable, encantador, arrebatadoramente atractivo... y podría tener información sobre el asesinato del padre de Tania.','2023-04-20',400),('2024-06-09 19:08:41','admi','2024-06-09 19:08:41','admi',68,'9788491224112','Las durmientes','Nando Lopez',1,'Chloe, una chica de dieciséis años, aparece desnuda en medio de un descampado. Al recobrar la conciencia, descubre en su móvil un vídeo donde alguien la ha grabado mientras yacía dormida y, presumiblemente, drogada. La noticia atrae pronto lo atención de los medios, que acuden al pueblo de la víctima en busca de más datos sobre el suceso, una agresión que deja de ser un caso aislado en cuanto surgen más denuncias idénticas. Entre los periodistas recién llegados se encuentra Gael, un joven becario que espera valerse de la ventaja de sus orígenes para conseguir una buena exclusiva, ya que los hechos han tenido lugar en el mismo pueblo donde pasó su adolescencia y del que huyó en cuanto cumplió los dieciocho. En parte, por culpa de heridas que aún se está trabajando. Pero, sobre todo, por un trágico suceso que marcó su vida seis años atrás: la desaparición de su amiga Vega al salir del mismo local en donde ha sido secuestrada Chloe. La irrupción de «las durmientes», como él mismo las bautiza en uno de sus artículos, no solo reabrirá ese dolor pasado —y ese interrogante pendiente— sino que lo obligará a tomar partido. Y es que Gael no está dispuesto a que el destino vuelva decidir en su lugar. Esta vez no.','2023-09-05',28),('2024-06-09 19:08:41','admi','2024-06-09 19:08:41','admi',69,'9788412667868','Nos ha alcanzado el infierno','Andrew Joseph White',1,'Benji, un chico trans de dieciséis años, huye de la secta que lo crio, una secta fundamentalista que desató el Armagedón y diezmó a la población mundial. Desesperado, busca un lugar donde no lo puedan encontrar, aunque realmente lo importante es que no encuentren el arma biológica con la que lo infectaron. Pero cuando se ve acorralado por unos monstruos nacidos de la destrucción, Benji será rescatado por un grupo de adolescentes del Centro Acheson LGBTQ+ local, conocido cariñosamente como el CAL. El líder del CAL, un chico guapo, autista y con una puntería mortal llamado Nick, conoce el secreto más oscuro de Benji: El arma biológica de la secta que está dentro de su cuerpo lo está convirtiendo en un monstruo lo suficientemente letal como para borrar a la humanidad de la faz de la tierra. A pesar de saberlo, igualmente le ofrece cobijo entre su variopinto grupo de adolescentes homosexuales, siempre y cuando Benji pueda controlar al monstruo y usar su poder para defender al CAL. Ansioso por ser aceptado, Benji estará de acuerdo con todas las condiciones que le propone Nick… hasta que descubre que el misterioso líder del CAL tiene intenciones ocultas y varios secretos propios','2023-06-15',400),('2024-06-09 19:10:24','admi','2024-06-09 19:10:24','admi',70,'9788419472069','Dead boys','Paloma Gonzalez Rubio',1,'Un Dead Boy paga con la sangre la traición, con la sangre de los suyos y la sangre propia: si se delata a uno de los hermanos, si se abandona la hermandad… Me desangro, vieja, pero ¿cómo voy a llamarte a ti, a Ana, a cualquiera de los que podríais salvarme? ¿Por que la soledad duele tanto, tanto, tanto…? Tirado entre los contenedores de basura. Mi única esperanza es que llegue la policía. Ya es tarde. Está lo del chino y eso lo cambió todo. ¿De dónde salió esa pistola? Lo nuestro se convirtió en una cuestión de honor, del honor de los Dead Boys. Te llamo porque si encuentran mi móvil, si muero, van a rastrearte, a echarte el guante… Yo era carne de cañón. Saben que estoy vivo. Lo saben y vienen a buscarme para acabar conmigo de una vez por todas.','2022-09-21',248),('2024-06-09 19:10:24','admi','2024-06-09 19:10:24','admi',71,'9788418002151','Reino de almas','Rena Barron',1,'Descendiente de un linaje de poderosos hechiceros, Arrah anhela su propia magia. Sin embargo, falla en el ritual para obtener la magia de sus ancestros y no está a la altura del legado de su familia. Bajo la mirada de desaprobación de su madre, la sacerdotisa y vidente más poderosa del reino, teme nunca ser lo bastante buena. Pero cuando los niños del reino comienzan a desaparecer, Arrah está lo suficientemente desesperada como para recurrir a un ritual prohibido y peligroso. Si no tiene magia propia, tendrá que comprarla, intercambiando años de su propia vida. El poder prestado de Arrah revela una traición de pesadilla y una marea creciente de oscuridad que amenaza con consumirla a ella y a todos los que ama.','2022-01-31',544),('2024-06-09 19:15:07','admi','2024-06-09 19:15:07','admi',72,'9788419820211','El creativo origen de la vida humana','Ibone Olza',27,'Hay quien sostiene que las relaciones sexuales tienen los días contados, y que en el futuro toda la reproducción humana se llevará a cabo en los laboratorios. Tambien hay quien afirma que la duración del embarazo se irá reduciendo conforme mejore la tecnología de los úteros artificiales y que, cuestión de tiempo, la vida humana se concebirá y desarrollará sin que la estancia uterina sea condición sine qua non. Tal vez haya que empezar a reflexionar acerca de cómo estamos criando a los humanos del futuro empezando por cómo los concebimos y gestamos. El embarazo, el fabuloso proceso de gestar, requiere una mirada amplia en la que necesariamente debemos tener en cuenta varias perspectivas: la de la madre que gesta, la del ser humano que se está gestando, y la de la familia y la comunidad que los acompaña y espera. Ibone Olza nos invita a recorrer y repensar algunos de estos puntos de nuestra historia, con curiosidad y asombro, pero tambien desde una perspectiva crítica y científica. Así como a imaginar una sociedad que honre y celebre el embarazo y a las embarazadas, que en definitiva es lo mismo que el amor y la vida.','2024-05-09',272),('2024-06-09 19:15:07','admi','2024-06-09 19:15:07','admi',73,'9788427052499','Despacito y mala letra','Marius Slekker',27,'¿Qué es Despacito y mala letra y para qué se utiliza?','2024-05-29',192),('2024-06-09 19:17:20','admi','2024-06-09 19:17:20','admi',74,'9788410079939','El laberinto de cristal','Gabriel Rubio',27,'El alcoholismo es como un elefante que entra en nuestra casa y que, al principio, incluso nos hace gracia. Con el tiempo, el elefante crece, se apodera de casi todo el espacio de nuestro hogar y empieza a condicionar las conductas de todos los miembros de la familia. De todos. El problema es que se trata de nuestro elefantito, que ahora ha crecido, pero es nuestro. Y, a pesar de las complicaciones que provoca, nadie se atreve a decir nada. Esto es lo que ocurre con el alcoholismo familiar: la vergüenza de pacientes, familias y amigos provoca que no se atrevan a hablar de este problema. Creen que es mejor mirar para otro lado y se embarcan en una titánica, y casi siempre estéril, lucha por procurar que el adicto no beba. Gabriel Rubio nos explica, con un lenguaje accesible, en qué consiste la adicción al alcohol y cómo tratarla haciendo hincapié en los problemas de los adolescentes y jóvenes con el alcohol. ','2024-05-29',288),('2024-06-09 19:17:20','admi','2024-06-09 19:17:20','admi',75,'9788436849349','La batalla del sistema inmunitario','Rafael Toledo Navarro',27,'En este libro, de carácter divulgativo, se aborda la respuesta inmunitaria de una manera que esté al alcance de la población y que le permita entender su trascendencia y traducción a la vida diaria. Los conceptos se explican para que cualquier lector pueda entenderlos sin necesidad de conocimientos previos sobre estos aspectos ni conocimientos científicos generales, y están acompañados de figuras y paralelismos que permiten visualizar ideas más complejas con sencillez. Esta obra es de especial interés dado el foco que ha puesto en la inmunología el público general en los últimos cinco años y que seguirá vigente en el futuro. Los libros del mercado que tratan la inmunología o bien lo hacen desde un punto de vista muy técnico, para lectores más avanzados, o bien están escritos por divulgadores más generalistas que no tienen el perfil tan increíblemente especializado y riguroso del autor.','2024-04-18',192),('2024-06-09 19:18:45','admi','2024-06-09 19:18:45','admi',76,'9788413823980','Atlas de anatomía humana','Frank Henry Netter',27,'La nueva edición del Atlas Netter de Anatomía Humana ilustra el cuerpo, con un detalle claro y brillante desde la perspectiva del clínico.á No se parece a otros atlas de anatomía, ya que contiene ilustraciones que hacen hincapie en las relaciones anatómicas que son más importantes para el clínico en la formación y la práctica. Es el atlas de anatomía de primera elección para todos los estudiantes de medicina y para los de otras ciencias de la salud que incluyen la anatomía en sus planes de estudio','2023-01-26',100),('2024-06-09 19:26:01','admi','2024-06-09 19:26:01','admi',79,'9788423352883','Las mil y una noches','Anónimo',4,'Las mil y unas noches no sólo es un mosaico que contiene cuentos de los distintos pueblos que en un momento u otro los acogieron, sino que también es un mosaico de las diversas morales de los mismos. Y así, al lado de cuentos piadosos y de fábulas didácticas, se encuentran historias de tono subido, incluso de bestialismo y homosexualidad. Son, por tanto, un verdadero cajón de sastre, tanto en su temática como en su temática como en su actitud moral.','2017-10-03',656),('2024-06-09 19:26:01','admi','2024-06-09 19:26:01','admi',80,'9788467044812','Don Quijote de la Mancha','Miguel de Cervantes',4,'En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no hace mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. El clásico de las letras españolas entra a formar parte de esta colección única. En el año en que se celebra la efeméride, el cuarto centenario de la publicación de la Segunda parte de El Quijote, presentamos esta obra en edición especial, con la versión a cargo de Alberto Blecua.','1605-01-01',1568),('2024-06-09 19:28:06','admi','2024-06-09 19:28:06','admi',81,'9788467045642','Orgullo y prejuicio','Jane Austen',4,'Si la auténtica prueba para juzgar la calidad de una novela es la relectura, y los placeres que aporta la relectura, entonces Orgullo y prejuicio supera cualquier novela jamás escrita','2015-10-06',360),('2024-06-09 19:28:06','admi','2024-06-09 19:28:06','admi',82,'9788467043662','Frankenstein','Mary Shelley',4,'Aquel «verano húmedo y desapacible» de 1816 «me entretuve pensando una historia que consiguiera que el lector tuviera pavor a mirar a su alrededor, que le helara la sangre y que acelerara los latidos de su corazón», Mary Shelley en la introducción a la edición de 1831 sobre cómo se forjó Frankenstein.','2015-10-06',304),('2024-06-09 19:31:20','admi','2024-06-09 19:31:20','admi',83,'9788408269519','Los tres mosqueteros','Alexandre Dumas',4,'El joven D’Artagnan abandona su hogar y llega a París para cumplir su sueño: unirse al cuerpo de mosqueteros de Luis XIII. Nada más llegar, el joven se batirá en duelo con tres caballeros, Aramis, Porthos y Athos, los tres mosqueteros del rey. Pero de pronto la lucha se ve interrumpida por los guardias del Cardenal Richelieu, y a partir de ese momento unirán sus espadas para luchar siempre en el mismo bando.','2023-03-15',720),('2024-06-09 19:31:20','admi','2024-06-09 19:31:20','admi',84,'9788408181804','Cumbres borrascosas','Emily Brontë',4,'El caso de la escritora inglesa Emily Brontë es verdaderamente excepcional dentro de la literatura. Falleció muy joven, dejando tan sólo una novela, Cumbres borrascosas, la épica historia de Catherine y Heathcliff, situada en los sombríos y desolados páramos de Yorkshire, constituye una asombrosa visión metafísica del destino, la obsesión, la pasión y la venganza. Publicada por primera vez en 1847, un año antes de morir su autora, esta obra rompía por completo con los cánones del «decoro» que la Inglaterra victoriana exigía a toda novela.','2018-02-13',416),('2024-06-09 19:31:20','admi','2024-06-09 19:31:20','admi',85,'9788408154488','Jane Eyre','Charlotte Brontë',4,'Marcada por su temprana orfandad materna, la escritora británica Charlotte Brontë, que a lo largo de su corta vida (1816-1855) acumuló muchos lutos, revela en su obra el apasionado deseo de encontrar un lugar en el mundo. Jane Eyre, la obra que consagró su éxito fulminante, tiene los ingredientes de una novela gótica, pero rebasa con mucho las convenciones del género. Jane, la protagonista, nos muestra un nuevo modo de descubrir la realidad, y con su reflexión la acompañamos en un viaje hacia la autenticidad.','2016-06-07',672),('2024-06-09 19:34:08','admi','2024-06-09 19:34:08','admi',86,'9788408137221','Moby Dick','Herman Melville',4,'«El lector, sin duda algo aturdido por su larga navegación, se encuentra abrumado en el trágico final: más adelante, cuando vuelva a abrir Moby Dick, por el comienzo o no, aunque ya sepa todo el desarrollo, no dejará de sentirse de nuevo arrastrado por la voz de Melville a navegar de nuevo, páginas y páginas. Es eso, en definitiva, lo que hace que algunas raras obras sean verdaderamente “clásicas”, esto es, inolvidables y siempre nuevas.»','2015-02-05',704),('2024-06-09 19:34:08','admi','2024-06-09 19:34:08','admi',87,'9788467048520','Madame Bovary','Gustave Flaubert',4,'De todos modos no era feliz, ni tampoco lo había sido nunca. Cada sonrisa ocultaba un bostezo de hastío, cada alegría una maldición, todo placer su saciedad, y los mejores besos no dejaban en los labios más que el irrealizable anhelo de una más sofisticada voluptuosidad','2016-10-04',456),('2024-06-09 19:34:08','admi','2024-06-09 19:34:08','admi',88,'9788408234203','Grandes esperanzas','Charles Dickens',4,'«El corazón humano es un instrumento de muchas cuerdas; el perfecto conocedor de los hombres las sabe hacer vibrar todas, como un buen músico.»','2020-10-06',640),('2024-06-09 19:36:53','admi','2024-06-09 19:36:53','admi',91,'9788408155768','Crimen y castigo','Fiòdor M. Dostoievski',4,'Una obra maestra de la historia de la literatura rusa','2016-06-04',768),('2024-06-09 19:36:53','admi','2024-06-09 19:36:53','admi',92,'9788408195375','Guerra y paz','Liev N. Tolstói',4,'Guerra y paz se publicó entre los años 1865 y 1869. A lo largo de sus centenares de páginas, Tolstói nos cuenta el relato épico de cinco familias rusas durante la invasión napoleónica. Obra monumental, que incluye a más de quinientos personajes históricos y de ficción, Guerra y paz alterna en su magnífica trama historias familiares con las vicisitudes del ejército napoleónico, la vida en la corte de Alejandro y las batallas de Austerlitz y Borodinó. Como ya advirtió Isaiah Berlin, «nadie ha superado nunca a Tolstói en la expresión del sabor específico, la calidad precisa de un sentimiento, la amplitud de su \"oscilación\". Nadie ha superado su manera de describir la estructura de una situación determinada en todo un período, pasajes ininterrumpidos de la vida de individuos, familias, comunidades, naciones enteras». Guerra y paz, un clásico de la literatura universal, se ha traducido varias veces al español pero la edición que presentamos aquí está basada en la única versión completa y autorizada por Tolstói, en una traducción magistral y totalmente fiable al español por Lydia Kúper.','2018-10-04',1840),('2024-06-09 19:36:53','admi','2024-06-09 19:36:53','admi',93,'9788408216636','Mujercitas','Louisa May Alcott',4,'Yo intentaré ser lo que él llama una “mujercita”, y procuraré no ser tan tosca e indomable y cumpliré con mis obligaciones en casa en lugar de querer estar siempre en otra parte —explicó Jo, convencida de que dominar su temperamento era una misión mucho más ardua que la de mantener a raya a unos cuantos rebeldes sureños','2019-10-08',688),('2024-06-09 19:40:48','admi','2024-06-09 19:40:48','admi',94,'9788467049480','Ana Karenina','Liev N. Tolstói',4,'«Todas las familias felices se parecen unas a otras; pero cada familia infeliz tiene un motivo especial para sentirse desgraciada.»','2017-03-21',1040),('2024-06-09 19:40:48','admi','2024-06-09 19:40:48','admi',95,'9788467048537','El retrato de Dorian Gray','Oscar Wilde',4,'Una piedra angular en los debates entre la ética y la estética, en las relaciones que mantienen el bien y el mal, el alma y el cuerpo, el arte y la vida.','2016-10-04',288),('2024-06-09 19:40:48','admi','2024-06-09 19:40:48','admi',96,'9788408247524','La señora Dalloway','Virginia Woolf',4,'La novela más popular de la autora de Una habitación propia.','2021-10-20',240),('2024-06-09 19:42:22','admi','2024-06-09 19:42:22','admi',97,'9788408238218','El gran Gatsby','Francis Scott Fitzgerald',4,'Un clásico de la literatura norteamericana que retrata de manera brillante los felices años veinte.','2021-02-03',224),('2024-06-09 19:42:22','admi','2024-06-09 19:42:22','admi',98,'9788445012772','1984','George Orwell',4,'Un clásico sobre los totalitarismos y la manipulación de la verdad de una vigencia escalofriante.','2022-03-09',400),('2024-06-09 19:49:24','admi','2024-06-09 19:49:24','admi',99,'9788445016800','La materia de las sombras','Antonio Runa',12,'La nueva obra de terror Antonio Runa (La Órbita de Endor)','2024-03-27',496),('2024-06-09 19:49:24','admi','2024-06-09 19:49:24','admi',100,'9788445014899','Vendimos nuestras almas','Grady Hendrix',12,'Un viaje épico hacia el corazón de un país paranoico dominado por las pastillas y las conspiraciones que parece haber perdido su propia alma.','2024-01-17',344),('2024-06-09 19:49:24','admi','2024-06-09 19:49:24','admi',101,'9788445015582','Cómo vender una casa encantada','Grady Hendrix',12,'Cuando Louise se entera de que sus padres han muerto, teme volver a casa. No quiere dejar a su pequeña con su ex y volar a Charleston. No quiere enfrentarse al domicilio familiar, donde se amontonan los restos de la vida académica de su padre y de la constante obsesión de su madre por los títeres y los muñecos. No quiere aprender a vivir sin las dos personas que mejor la han conocido y más la han querido del mundo entero.','2023-10-18',440),('2024-06-09 19:49:24','admi','2024-06-09 19:49:24','admi',102,'9788445014806','Un legado de sangre','S.T. Gibson',12,'Un retelling maravillosamente oscuro de la historia de Drácula.','2023-09-13',264),('2024-06-09 19:50:41','admi','2024-06-09 19:50:41','admi',103,'9788423362905','Mala gente','Enrique FigueredoPere Cullell',12,'El exitoso pódcast de true crime de La Vanguardia, con más de un millón de escuchas, en formato libro.','2023-03-22',240);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `crear_valoracion_libro` AFTER INSERT ON `libros` FOR EACH ROW BEGIN
    INSERT INTO valoracion_libros (id_libro, valoracion_total) VALUES (NEW.id, 0);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  PRIMARY KEY (`id`),
  KEY `libros_biblioteca_ibfk_1` (`id_biblioteca`),
  KEY `libros_biblioteca_ibfk_2` (`id_libro`),
  CONSTRAINT `libros_biblioteca_ibfk_1` FOREIGN KEY (`id_biblioteca`) REFERENCES `bibliotecas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libros_biblioteca_ibfk_2` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros_biblioteca`
--

LOCK TABLES `libros_biblioteca` WRITE;
/*!40000 ALTER TABLE `libros_biblioteca` DISABLE KEYS */;
INSERT INTO `libros_biblioteca` VALUES (1,1,1),(3,4,3),(7,4,2),(8,11,54),(9,11,49),(10,11,47),(11,11,50),(12,12,45),(13,12,21),(14,12,22),(15,12,17),(16,10,45),(17,10,54),(18,49,47),(19,49,50);
/*!40000 ALTER TABLE `libros_biblioteca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros_erroneos`
--

DROP TABLE IF EXISTS `libros_erroneos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros_erroneos` (
  `audit_created` timestamp NOT NULL,
  `audit_creator` varchar(45) NOT NULL DEFAULT 'admi',
  `audit_updated` timestamp NOT NULL,
  `audit_updater` varchar(45) NOT NULL DEFAULT 'admi',
  `id` int NOT NULL AUTO_INCREMENT,
  `id_libro` int NOT NULL,
  `id_reportante` int NOT NULL,
  `resuelto` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_libroerroneo_libro_idx` (`id_libro`),
  KEY `fk_usuario_reportante_idx` (`id_reportante`),
  CONSTRAINT `fk_libroerroneo_libro` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`),
  CONSTRAINT `fk_usuario_reportante` FOREIGN KEY (`id_reportante`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros_erroneos`
--

LOCK TABLES `libros_erroneos` WRITE;
/*!40000 ALTER TABLE `libros_erroneos` DISABLE KEYS */;
INSERT INTO `libros_erroneos` VALUES ('2024-05-14 17:58:55','admi','2024-05-25 07:24:43','2',1,1,1,1),('2024-05-14 17:59:32','admi','2024-05-14 17:59:32','admi',2,2,1,1),('2024-05-14 16:24:06','juanpe','2024-05-14 16:11:06','veronicaalvarez',3,3,2,1),('2024-06-06 16:38:54','Lunava','2024-06-15 09:14:42','2',4,8,52,1),('2024-06-06 16:39:51','Lunava','2024-06-06 16:39:51','Lunava',5,9,52,NULL),('2024-06-09 16:21:40','Profesores','2024-06-09 16:21:40','Profesores',6,51,86,NULL);
/*!40000 ALTER TABLE `libros_erroneos` ENABLE KEYS */;
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
  `idusuario` int NOT NULL,
  PRIMARY KEY (`idperfil`),
  KEY `fk_perfil_usuario_idx` (`idusuario`),
  CONSTRAINT `fk_perfil_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_usuario`
--

LOCK TABLES `perfil_usuario` WRITE;
/*!40000 ALTER TABLE `perfil_usuario` DISABLE KEYS */;
INSERT INTO `perfil_usuario` VALUES ('2024-05-11 18:20:46','admi','2024-05-14 17:12:15','1',1,'Juan','Adoro la literatura',1),('2024-05-19 08:08:10','admi','2024-05-19 08:08:10','admi',2,'Perfil inicial','Información del perfil',49),('2024-05-25 10:27:57','admi','2024-05-25 10:27:57','admi',5,'Perfil inicial','Información del perfil',50),('2024-05-26 11:54:02','admi','2024-05-26 11:54:02','admi',6,'Perfil inicial','Información del perfil',51),('2024-05-26 12:14:06','admi','2024-06-04 15:22:57','52',7,'Luna','Soy una persona maravillosa',52),('2024-06-07 17:49:13','admi','2024-06-07 17:49:13','admi',8,'Perfil inicial','Información del perfil',54),('2024-06-09 10:21:39','admi','2024-06-09 10:21:39','admi',39,'Perfil inicial','Información del perfil',85),('2024-06-09 18:20:47','admi','2024-06-09 16:24:15','86',40,'Profe','Soy profesor',86),('2024-06-09 18:35:52','admi','2024-06-09 18:35:52','admi',41,'Perfil inicial','Información del perfil',87),('2024-06-14 14:54:01','admi','2024-06-14 13:51:18','88',42,'Luis Herrero','Soy profesor de DAM e imparto la clase de Acceso a Datos',88),('2024-06-15 10:52:41','admi','2024-06-15 10:52:41','admi',43,'Perfil inicial','Información del perfil',89);
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
  `alias` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(200) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `tipo` int NOT NULL DEFAULT '3',
  `publico` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  UNIQUE KEY `alias_UNIQUE` (`alias`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`),
  KEY `fk_grupo_usuario_idx` (`tipo`),
  CONSTRAINT `fk_grupo_usuario` FOREIGN KEY (`tipo`) REFERENCES `grupos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('2024-04-27 15:11:40','admi','2024-05-14 17:19:39','1',1,'juanLuispp','Juan Luis','Pérez Peon','1990-01-01','juan@example.com','clave123','123456789',3,_binary '\0'),('2024-05-11 16:06:50','admi','2024-05-11 16:06:50','admi',2,'veronicaalvarez','Veronica','Álvarez Lavin','2003-06-24','veronica@example.com','$2a$10$PLa8DPBwHJuHqtwN5iU0nOIe5/.zuAPtWQENH5lToyZuXluD.Df92','156482501',1,_binary ''),('2024-05-19 06:08:11','admi','2024-05-25 08:17:49','49',49,'Jacky','Jack','Ross','2024-05-19','jackross@example.com','string','666666666',3,_binary ''),('2024-05-25 08:27:46','admi','2024-06-09 10:02:22','2',50,'gemaplavin','Gemma Pilar','Lavin',NULL,'gemap@example.com','1234567890','365001100',3,_binary '\0'),('2024-05-26 11:54:02','admi','2024-05-26 11:54:02','admi',51,'veronicaal','Verónica','Álvarez','2003-06-24','veronica@e.example.com','pss123','657895102',3,_binary '\0'),('2024-05-26 10:14:07','admi','2024-06-15 08:28:49','2',52,'Lunava','Luna','Valente',NULL,'lunavalente@gmail.com','$2a$10$PLa8DPBwHJuHqtwN5iU0nOIe5/.zuAPtWQENH5lToyZuXluD.Df92','695223510',3,_binary ''),('2024-06-07 15:49:13','admi','2024-06-07 15:49:13','admi',54,'ambarBelson','ambas','Benson','2000-06-07','ambasbenson@example.com','$2a$10$3RI/2l.PtR9oxXBYSFEdoOvlBm15c/XZ4xAPlwo46wRfSPDrTD2Ni','123456',3,_binary ''),('2024-06-09 08:21:39','admi','2024-06-09 08:21:39','admi',85,'mateogg','Mateo','Arg',NULL,'mateo@ggggg.com','$2a$10$oEmhTJJSrIipRbLbxhCMZezHyZo826yEcsdjbF984pBYEvFJCaa.e',NULL,3,_binary '\0'),('2024-06-09 16:20:47','admi','2024-06-09 16:20:47','admi',86,'Profesores','Profesor','dam',NULL,'profesores@example.es','$2a$10$E5yM.XKzZXWAlgLbOPBnoO7vbEfDVQtbdOGI050dh1kiTkiBnH./y',NULL,3,_binary ''),('2024-06-09 16:35:53','admi','2024-06-09 16:35:53','admi',87,'AdmiProfesores','Admi','Profesores',NULL,'admiProfes@example.com','$2a$10$lVfjPieSluVeLPwOzJlyVu1oACPL.GDqmk9jyFspJXNom.1thbUe2',NULL,2,_binary '\0'),('2024-06-14 12:54:01','admi','2024-06-14 12:54:01','admi',88,'LuisHerrero','Luis','Herrero',NULL,'luisherrero@gmail.com','$2a$10$nd4RtTqq1sTkJGZw3Al0WuDu8k0L/Jwz0yYJLp3vJ0SW20Pvb2lKy',NULL,3,_binary ''),('2024-06-15 08:52:41','admi','2024-06-15 08:52:41','admi',89,'siimongtt','Simon','Gutierrez',NULL,'simongut@gmail.com','$2a$10$/RWZxHNiRp8W9gHZ1/s/Dejal0bZUS6lhjVmWXqAy7ls4CLFrc3zO','578601476',3,_binary '\0');
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
    INSERT INTO perfil_usuario (nombre, informacion, idUsuario)
    VALUES ('Perfil inicial', 'Información del perfil', NEW.id);
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
  CONSTRAINT `valoracion_libros_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoracion_libros`
--

LOCK TABLES `valoracion_libros` WRITE;
/*!40000 ALTER TABLE `valoracion_libros` DISABLE KEYS */;
INSERT INTO `valoracion_libros` VALUES (1,4),(2,7),(8,0),(9,3.4),(10,2.3),(17,5.5),(18,7.6),(19,8),(20,8),(21,8.7),(22,10),(43,8.6),(44,9.5),(45,2),(46,4),(47,3),(48,3.6),(49,5.4),(50,4),(51,4),(52,4.6),(53,5),(54,3),(55,7),(56,9),(57,5),(58,5),(64,0),(65,0),(66,0),(68,0),(69,0),(70,0),(71,0),(72,0),(73,0),(74,0),(75,0),(76,0),(79,0),(80,0),(81,0),(82,0),(83,0),(84,0),(85,0),(86,0),(87,0),(88,0),(91,0),(92,0),(93,0),(94,0),(95,0),(96,0),(97,0),(98,0),(99,0),(100,0),(101,0),(102,0),(103,0);
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
  `play_list` varchar(100) DEFAULT NULL,
  `personaje_fav` varchar(45) DEFAULT NULL,
  `personaje_odiado` varchar(45) DEFAULT NULL,
  `recomendacion` bit(1) NOT NULL DEFAULT b'1',
  `frase_iconica` varchar(200) DEFAULT NULL,
  `opinion` varchar(100) DEFAULT NULL,
  `puntuacion` double NOT NULL,
  `fecha_valoracion` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `valoraciones_usuarios_ibfk_1` (`id_libro`),
  KEY `valoraciones_usuarios_ibfk_2` (`id_usuario`),
  CONSTRAINT `valoraciones_usuarios_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `valoraciones_usuarios_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoraciones_usuarios`
--

LOCK TABLES `valoraciones_usuarios` WRITE;
/*!40000 ALTER TABLE `valoraciones_usuarios` DISABLE KEYS */;
INSERT INTO `valoraciones_usuarios` VALUES (1,1,1,NULL,NULL,NULL,_binary '',NULL,NULL,4.5,NULL),(18,21,1,'Blue Submarine',NULL,NULL,_binary '','A yelow submarine','El sentimiento que transmite es increible, como mezcla la psicologia y el amor',10,'2024-05-12 13:44:08'),(26,8,52,'siempre juntos','Name','Name',_binary '\0','Cuando estas a mi lado, siento que soy capaz de poderlo todo','sin leer',2,'2024-06-06 17:30:16'),(27,54,86,'Name','Name','Name',_binary '','Name','kkjk',3,'2024-06-09 18:22:37');
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

-- Dump completed on 2024-06-15 14:03:38
