-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: localhost    Database: productoracinematografica
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `idTrabajador` int(11) NOT NULL,
  `especialidad` varchar(20) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`especialidad`),
  CONSTRAINT `especialidad_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (6,'Accion'),(6,'comedia'),(8,'Drama'),(12,'Comedia'),(12,'Terror');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caracteristica`
--

DROP TABLE IF EXISTS `caracteristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caracteristica` (
  `idEquip` int(11) NOT NULL,
  `CARACTERISTICA` varchar(1000) NOT NULL,
  PRIMARY KEY (`idEquip`,`CARACTERISTICA`),
  CONSTRAINT `caracteristica_ibfk_1` FOREIGN KEY (`idEquip`) REFERENCES `equipamiento` (`idEquip`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caracteristica`
--

LOCK TABLES `caracteristica` WRITE;
/*!40000 ALTER TABLE `caracteristica` DISABLE KEYS */;
INSERT INTO `caracteristica` VALUES (14,'4K'),(14,'PANTALLA TACTIL'),(14,'RANURAS DE TARJETAS DOBLES'),(15,'4K'),(15,'MICROFONO INCORPORADO'),(15,'PANTALLA TACTIL'),(16,'CAMARA DIGITAL REFLEX'),(16,'MONTURA F'),(16,'TARJETA DE MEMORIA TIPO B'),(17,'NEGRO'),(17,'SUPERCARDIOIDE/LOBAR'),(17,'XLR'),(18,'48V'),(18,'CAÑON DE CONDENSADOR'),(18,'SUPERCARDIOIDE'),(19,'AISLANTE DE SONIDO'),(19,'COMPACTO'),(19,'FILTRO DE AUDIO');
/*!40000 ALTER TABLE `caracteristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `director` (
  `idTrabajador` int(11) NOT NULL,
  `categoria` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idTrabajador`),
  CONSTRAINT `director_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (3,'Accion'),(7,'Fantasia');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipamiento`
--

DROP TABLE IF EXISTS `equipamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipamiento` (
  `idEquip` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `imgPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEquip`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipamiento`
--

LOCK TABLES `equipamiento` WRITE;
/*!40000 ALTER TABLE `equipamiento` DISABLE KEYS */;
INSERT INTO `equipamiento` VALUES (14,'Canon XA45','Camara',NULL),(15,'Sony HXR-NX100','Camara',NULL),(16,'Nikon D6 Body','Camara',NULL),(17,'Neumann KMR','Microfono',NULL),(18,'DPA 4017B Rycote WS 3 Bundle','Microfono',NULL),(19,'Marantz Pro Sound Shield Compact','AislanteSonido',NULL);
/*!40000 ALTER TABLE `equipamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guionista`
--

DROP TABLE IF EXISTS `guionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guionista` (
  `idTrabajador` int(11) NOT NULL,
  `tipoGuion` varchar(20) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`tipoGuion`),
  CONSTRAINT `tipoguion_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guionista`
--

LOCK TABLES `guionista` WRITE;
/*!40000 ALTER TABLE `guionista` DISABLE KEYS */;
INSERT INTO `guionista` VALUES (1,'terror'),(2,'sitcom'),(9,'Accion');
/*!40000 ALTER TABLE `guionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obraaudiovisual`
--

DROP TABLE IF EXISTS `obraaudiovisual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obraaudiovisual` (
  `idObra` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `duracion` int(3) DEFAULT NULL,
  `FechaEstreno` date DEFAULT NULL,
  `presupuesto` int(10) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `imgPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idObra`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obraaudiovisual`
--

LOCK TABLES `obraaudiovisual` WRITE;
/*!40000 ALTER TABLE `obraaudiovisual` DISABLE KEYS */;
INSERT INTO `obraaudiovisual` VALUES (6,'The Last Airbender',103,'2010-08-06',150000000,'Pelicula',NULL),(7,'Avatar',162,'2009-12-18',237000000,'Pelicula',NULL),(8,'Guardianes de la Galaxia',122,'2014-08-14',232000000,'Pelicula',NULL),(9,'Peaky Blinders',60,'2013-09-12',NULL,'Serie',NULL),(10,'Lupin',60,'2021-01-08',NULL,'Serie',NULL),(11,'Vikingos: Valhalla',60,'2022-02-25',NULL,'Serie',NULL),(14,'Gravity Fall',250,'2012-11-05',100000,'Serie',NULL);
/*!40000 ALTER TABLE `obraaudiovisual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participa`
--

DROP TABLE IF EXISTS `participa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participa` (
  `idTrabajador` int(11) NOT NULL,
  `idObra` int(11) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`idObra`),
  KEY `idObra` (`idObra`),
  CONSTRAINT `participa_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE,
  CONSTRAINT `participa_ibfk_2` FOREIGN KEY (`idObra`) REFERENCES `obraaudiovisual` (`idObra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participa`
--

LOCK TABLES `participa` WRITE;
/*!40000 ALTER TABLE `participa` DISABLE KEYS */;
INSERT INTO `participa` VALUES (1,6),(2,6),(3,6),(4,6),(5,9),(6,9),(7,9),(9,9),(8,10);
/*!40000 ALTER TABLE `participa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patrocinador`
--

DROP TABLE IF EXISTS `patrocinador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrocinador` (
  `idPatro` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `cantDinero` int(10) DEFAULT NULL,
  `condicion` varchar(1000) DEFAULT NULL,
  `imgPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPatro`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patrocinador`
--

LOCK TABLES `patrocinador` WRITE;
/*!40000 ALTER TABLE `patrocinador` DISABLE KEYS */;
INSERT INTO `patrocinador` VALUES (4,'Cocacola',10000000,'Mostrar en todas las bebidas de la pelicula/serie',NULL),(5,'McDonals',2000000,'Poner la marca en 5 escenas de la Eplicula/serie',NULL),(6,'RedBull',2000000,'Mostrar la marca por cada 20 min de la pelicula/serie',NULL),(7,'Apple',5000000,'Todos los dispositivos electronicos de la Pelicula/Serie tiene que ser de la marca apple',NULL);
/*!40000 ALTER TABLE `patrocinador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pelicula` (
  `idObra` int(11) NOT NULL,
  `esTaquillera` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idObra`),
  CONSTRAINT `pelicula_ibfk_1` FOREIGN KEY (`idObra`) REFERENCES `obraaudiovisual` (`idObra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES (6,0),(7,0),(8,0);
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promociona`
--

DROP TABLE IF EXISTS `promociona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promociona` (
  `idObra` int(11) NOT NULL,
  `idPatro` int(11) NOT NULL,
  PRIMARY KEY (`idPatro`,`idObra`),
  KEY `idObra` (`idObra`),
  CONSTRAINT `promociona_ibfk_1` FOREIGN KEY (`idPatro`) REFERENCES `patrocinador` (`idPatro`),
  CONSTRAINT `promociona_ibfk_2` FOREIGN KEY (`idObra`) REFERENCES `obraaudiovisual` (`idObra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promociona`
--

LOCK TABLES `promociona` WRITE;
/*!40000 ALTER TABLE `promociona` DISABLE KEYS */;
INSERT INTO `promociona` VALUES (8,4),(8,6),(9,7),(10,5);
/*!40000 ALTER TABLE `promociona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serie`
--

DROP TABLE IF EXISTS `serie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serie` (
  `idObra` int(11) NOT NULL,
  `numTemporada` int(11) NOT NULL,
  `numCapitulo` int(11) NOT NULL,
  `nombreCap` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idObra`,`numTemporada`,`numCapitulo`),
  CONSTRAINT `serie_ibfk_1` FOREIGN KEY (`idObra`) REFERENCES `obraaudiovisual` (`idObra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serie`
--

LOCK TABLES `serie` WRITE;
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
INSERT INTO `serie` VALUES (10,1,1,'Promo'),(10,1,2,NULL),(10,1,3,NULL),(10,1,4,NULL),(10,1,5,NULL),(11,1,1,'Promo'),(11,1,2,NULL),(11,1,3,NULL),(11,1,4,NULL),(11,1,5,NULL),(11,1,6,NULL),(11,1,7,NULL),(11,1,8,NULL),(14,1,1,'Tourist Trapped'),(14,1,2,'The legends of the Gobblewonker'),(14,1,3,'Headhunters'),(14,1,4,'The Hand That Rocks the Mabel');
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnicoaudiovisual`
--

DROP TABLE IF EXISTS `tecnicoaudiovisual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tecnicoaudiovisual` (
  `idTrabajador` int(11) NOT NULL,
  `AreaTrabajo` varchar(20) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`AreaTrabajo`),
  CONSTRAINT `areatrabajo_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnicoaudiovisual`
--

LOCK TABLES `tecnicoaudiovisual` WRITE;
/*!40000 ALTER TABLE `tecnicoaudiovisual` DISABLE KEYS */;
INSERT INTO `tecnicoaudiovisual` VALUES (4,'Sonido'),(5,'Imagen');
/*!40000 ALTER TABLE `tecnicoaudiovisual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajador` (
  `idTrabajador` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) DEFAULT NULL,
  `nombre` varchar(15) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `numtel` int(11) DEFAULT NULL,
  `numPremios` int(11) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `FechaNac` date DEFAULT NULL,
  PRIMARY KEY (`idTrabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES (0,NULL,NULL,NULL,NULL,NULL,NULL,'Administrador',NULL),(1,'16090622Y','lander','guarrotxena',688601455,22,'paseo del puerto','guionista','2000-11-11'),(2,'16090788X','Iker','Perez',688603355,21,'tartanga','guionista','2004-11-11'),(3,'12080622Y','leo','gomez',688634555,11,'avenida algorta','director','2011-11-11'),(4,'16099875Y','ander','gonzalez',688334565,2,'basagoiti','tecnicoAudiovisual','2001-11-11'),(5,'79231515S','Josue','Vargas',688644145,0,'Txorierriko Etorbidea','TecnicoAudioVisual','1993-09-29'),(6,'17231515S','Henrique','Yeguo',778644145,2,'Avenida Zarautz','Actor','2000-09-10'),(7,'17255515S','Haizea','Franco',755441452,1,'Plaza Moyua','Director','2001-10-11'),(8,'17231215S','Elias','Etxebarria',722644145,3,'Astrubudua','Actor','2003-09-10'),(9,'20231515S','Jon','Novoa',776644145,2,'Barakaldo','Guionista','2005-09-10'),(12,'12345678Y','Manolo','Pipo',123456789,0,'Carretera pipo','actor','2020-10-20');
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usa`
--

DROP TABLE IF EXISTS `usa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usa` (
  `idObra` int(11) NOT NULL,
  `idEquip` int(11) NOT NULL,
  PRIMARY KEY (`idEquip`,`idObra`),
  KEY `idObra` (`idObra`),
  CONSTRAINT `usa_ibfk_1` FOREIGN KEY (`idEquip`) REFERENCES `equipamiento` (`idEquip`),
  CONSTRAINT `usa_ibfk_2` FOREIGN KEY (`idObra`) REFERENCES `obraaudiovisual` (`idObra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usa`
--

LOCK TABLES `usa` WRITE;
/*!40000 ALTER TABLE `usa` DISABLE KEYS */;
INSERT INTO `usa` VALUES (6,18),(7,15),(8,16),(10,19);
/*!40000 ALTER TABLE `usa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` varchar(20) NOT NULL,
  `passwd` varchar(50) DEFAULT NULL,
  `idTrabajador` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idTrabajador` (`idTrabajador`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('administrador','abcd*1234',0),('henr1','abcd*1234',6),('josue','abcd*1234',5);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `viewpelis`
--

DROP TABLE IF EXISTS `viewpelis`;
/*!50001 DROP VIEW IF EXISTS `viewpelis`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `viewpelis` AS SELECT 
 1 AS `ID`,
 1 AS `Nombre`,
 1 AS `ID_Director`,
 1 AS `ID_Guionista`,
 1 AS `NumTrabajadores`,
 1 AS `Presupuesto`,
 1 AS `FechaEstreno`,
 1 AS `EsTaquillera`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `viewseries`
--

DROP TABLE IF EXISTS `viewseries`;
/*!50001 DROP VIEW IF EXISTS `viewseries`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `viewseries` AS SELECT 
 1 AS `ID`,
 1 AS `Nombre`,
 1 AS `ID_Director`,
 1 AS `ID_Guionista`,
 1 AS `NumTrabajadores`,
 1 AS `Presupuesto`,
 1 AS `FechaEstreno`,
 1 AS `Temporadas`,
 1 AS `Capitulos`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'productoracinematografica'
--
/*!50003 DROP PROCEDURE IF EXISTS `deleteAtributo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `deleteAtributo`(in id int(11))
BEGIN

      DECLARE tipoT VARCHAR(20) DEFAULT NULL;

      SELECT t.tipo INTO @tipoT FROM trabajador t WHERE t.idTrabajador = id;

      CASE @tipoT
        WHEN 'actor' THEN
            DELETE FROM actor WHERE idtrabajador = id;
        
		WHEN 'director' THEN
            DELETE FROM director WHERE idtrabajador = id;

        WHEN 'guionista' THEN
            DELETE FROM guionista WHERE idtrabajador = id;

        WHEN 'tecnicoaudiovisual' THEN
            DELETE FROM tecnicoaudiovisual WHERE idtrabajador = id;

      END CASE;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertAtributo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `insertAtributo`(in id int(11),in atributo varchar(20))
BEGIN

      DECLARE tipoT VARCHAR(20) DEFAULT NULL;

      SELECT t.tipo INTO @tipoT FROM trabajador t WHERE t.idTrabajador = id;

      CASE @tipoT
        WHEN 'actor' THEN
            INSERT INTO actor value(id, atributo);
        
		WHEN 'director' THEN
            INSERT INTO director value(id, atributo);

        WHEN 'guionista' THEN
            INSERT INTO guionista value(id, atributo);

        WHEN 'tecnicoaudiovisual' THEN
            INSERT INTO tecnicoaudiovisual value(id, atributo);

      END CASE;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `logging` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `logging`(in usuario varchar(20), in passwd varchar(50))
BEGIN

  SELECT idTrabajador FROM usuario u

  WHERE BINARY u.idUsuario = "administrador"

  AND BINARY u.passwd = "abcd*1234";

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `searchObra` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `searchObra`(in id INT(11))
BEGIN

  SELECT
      o.*,
      s.numTemporada,
      s.numCapitulo,
      s.nombreCap,
      p.esTaquillera
  FROM obraaudiovisual o
  LEFT JOIN serie s USING(idObra)
  LEFT JOIN pelicula p USING(idObra)
  WHERE o.idObra = id;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `searchTrabajador` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `searchTrabajador`(in id int(11))
BEGIN

SELECT t.*,
      if(t.tipo = 'actor', especialidad, NULL) AS Especialidad,
      if(t.tipo = 'director', categoria, NULL) AS Categoria,
      if(t.tipo = 'tecnicoaudiovisual', areatrabajo, NULL) AS AreaTrabajo,
      if(t.tipo = 'guionista', tipoguion, NULL) AS TipoGuion
from trabajador t
LEFT JOIN actor a USING(idTrabajador)
LEFT JOIN director d USING(idTrabajador)
LEFT JOIN guionista g USING(idTrabajador)
LEFT JOIN tecnicoaudiovisual ta USING(idTrabajador)
WHERE t.tipo != "administrador"
GROUP BY t.idTrabajador, a.especialidad, d.categoria, ta.areatrabajo, g.tipoguion
Having t.idTrabajador = id;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showObras` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `showObras`()
BEGIN

  SELECT
      o.*,
      s.numTemporada AS Temporadas,
      s.numCapitulo as Capitulos,
      s.nombreCap NombreCap,
      p.esTaquillera
  FROM obraaudiovisual o
  LEFT JOIN serie s USING(idObra)
  LEFT JOIN pelicula p USING(idObra);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showTrabajadores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `showTrabajadores`()
BEGIN

SELECT t.*,
      if(t.tipo = 'actor', especialidad, NULL) AS Especialidad,
      if(t.tipo = 'director', categoria, NULL) AS Categoria,
      if(t.tipo = 'tecnicoaudiovisual', areatrabajo, NULL) AS AreaTrabajo,
      if(t.tipo = 'guionista', tipoguion, NULL) AS TipoGuion
from trabajador t
LEFT JOIN actor a USING(idTrabajador)
LEFT JOIN director d USING(idTrabajador)
LEFT JOIN guionista g USING(idTrabajador)
LEFT JOIN tecnicoaudiovisual ta USING(idTrabajador)
WHERE t.tipo != "administrador"
GROUP BY t.idTrabajador, especialidad, categoria, areatrabajo, tipoguion;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `viewpelis`
--

/*!50001 DROP VIEW IF EXISTS `viewpelis`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `viewpelis` AS select `oa`.`idObra` AS `ID`,`oa`.`nombre` AS `Nombre`,max(if((`tra`.`tipo` = 'director'),concat(`tra`.`idTrabajador`,'- ',`tra`.`nombre`,' ',`tra`.`apellido`),NULL)) AS `ID_Director`,max(if((`tra`.`tipo` = 'guionista'),concat(`tra`.`idTrabajador`,'- ',`tra`.`nombre`,' ',`tra`.`apellido`),NULL)) AS `ID_Guionista`,count(`tra`.`idTrabajador`) AS `NumTrabajadores`,`oa`.`presupuesto` AS `Presupuesto`,`oa`.`FechaEstreno` AS `FechaEstreno`,max(if((`peli`.`esTaquillera` = 1),'Si','No')) AS `EsTaquillera` from (((`obraaudiovisual` `oa` join `participa` `part` on((`oa`.`idObra` = `part`.`idObra`))) join `trabajador` `tra` on((`tra`.`idTrabajador` = `part`.`idTrabajador`))) join `pelicula` `peli` on((`peli`.`idObra` = `oa`.`idObra`))) where (`oa`.`tipo` = 'Pelicula') group by `oa`.`idObra` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `viewseries`
--

/*!50001 DROP VIEW IF EXISTS `viewseries`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `viewseries` AS select `oa`.`idObra` AS `ID`,`oa`.`nombre` AS `Nombre`,max(if((`tra`.`tipo` = 'director'),concat(`tra`.`idTrabajador`,'- ',`tra`.`nombre`,' ',`tra`.`apellido`),NULL)) AS `ID_Director`,max(if((`tra`.`tipo` = 'guionista'),concat(`tra`.`idTrabajador`,'- ',`tra`.`nombre`,' ',`tra`.`apellido`),NULL)) AS `ID_Guionista`,count(`tra`.`idTrabajador`) AS `NumTrabajadores`,`oa`.`presupuesto` AS `Presupuesto`,`oa`.`FechaEstreno` AS `FechaEstreno`,max(`s`.`numTemporada`) AS `Temporadas`,max(`s`.`numCapitulo`) AS `Capitulos` from (((`obraaudiovisual` `oa` join `participa` `part` on((`oa`.`idObra` = `part`.`idObra`))) join `trabajador` `tra` on((`tra`.`idTrabajador` = `part`.`idTrabajador`))) join `serie` `s` on((`s`.`idObra` = `oa`.`idObra`))) where (`oa`.`tipo` = 'Serie') group by `oa`.`idObra` */;
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

-- Dump completed on 2022-05-07 22:18:44