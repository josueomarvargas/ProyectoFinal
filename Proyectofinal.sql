-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: localhost    Database: proyectofinal
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
-- Table structure for table `areatrabajo`
--

DROP TABLE IF EXISTS `areatrabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areatrabajo` (
  `idTrabajador` int(11) NOT NULL,
  `AreaTrabajo` varchar(20) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`AreaTrabajo`),
  CONSTRAINT `areatrabajo_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areatrabajo`
--

LOCK TABLES `areatrabajo` WRITE;
/*!40000 ALTER TABLE `areatrabajo` DISABLE KEYS */;
INSERT INTO `areatrabajo` VALUES (1,'tecnico Sonido');
/*!40000 ALTER TABLE `areatrabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caracteristica`
--

DROP TABLE IF EXISTS `caracteristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caracteristica` (
  `idEquip` int(11) NOT NULL,
  `caracteristica` varchar(20) NOT NULL,
  PRIMARY KEY (`idEquip`,`caracteristica`),
  CONSTRAINT `caracteristica_ibfk_1` FOREIGN KEY (`idEquip`) REFERENCES `equipamiento` (`idEquip`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caracteristica`
--

LOCK TABLES `caracteristica` WRITE;
/*!40000 ALTER TABLE `caracteristica` DISABLE KEYS */;
INSERT INTO `caracteristica` VALUES (1,'electricidad');
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
  `nacionalidad` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idTrabajador`),
  CONSTRAINT `director_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (1,'Brasileño');
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
  `nombre` varchar(20) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idEquip`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipamiento`
--

LOCK TABLES `equipamiento` WRITE;
/*!40000 ALTER TABLE `equipamiento` DISABLE KEYS */;
INSERT INTO `equipamiento` VALUES (1,'camara54K','kklre');
/*!40000 ALTER TABLE `equipamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad`
--

DROP TABLE IF EXISTS `especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `especialidad` (
  `idTrabajador` int(11) NOT NULL,
  `especialidad` varchar(20) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`especialidad`),
  CONSTRAINT `especialidad_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad`
--

LOCK TABLES `especialidad` WRITE;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` VALUES (1,'comedia');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;
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
  PRIMARY KEY (`idObra`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obraaudiovisual`
--

LOCK TABLES `obraaudiovisual` WRITE;
/*!40000 ALTER TABLE `obraaudiovisual` DISABLE KEYS */;
INSERT INTO `obraaudiovisual` VALUES (6,'The Last Airbender',103,'2010-08-06',150000000,'Pelicula'),(7,'Avatar',162,'2009-12-18',237000000,'Pelicula'),(8,'Guardianes de la Galaxia',122,'2014-08-14',232000000,'Pelicula'),(9,'Peaky Blinders',60,'2013-09-12',NULL,'Serie'),(10,'Lupin',60,'2021-01-08',NULL,'Serie'),(11,'Vikingos: Valhalla',60,'2022-02-25',NULL,'Serie');
/*!40000 ALTER TABLE `obraaudiovisual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participa`
--

DROP TABLE IF EXISTS `participa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participa` (
  `idObra` int(11) NOT NULL,
  `idtrabajador` int(11) NOT NULL,
  PRIMARY KEY (`idtrabajador`,`idObra`),
  KEY `idObra` (`idObra`),
  CONSTRAINT `participa_ibfk_1` FOREIGN KEY (`idtrabajador`) REFERENCES `trabajador` (`idTrabajador`),
  CONSTRAINT `participa_ibfk_2` FOREIGN KEY (`idObra`) REFERENCES `obraaudiovisual` (`idObra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participa`
--

LOCK TABLES `participa` WRITE;
/*!40000 ALTER TABLE `participa` DISABLE KEYS */;
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
  `nombre` varchar(20) DEFAULT NULL,
  `cantDinero` int(10) DEFAULT NULL,
  `condicion` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idPatro`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patrocinador`
--

LOCK TABLES `patrocinador` WRITE;
/*!40000 ALTER TABLE `patrocinador` DISABLE KEYS */;
INSERT INTO `patrocinador` VALUES (2,'Cocacola',10000000,'Mostrar en todas las bebidas de la pelicula/serie'),(3,'McDonals',2000000,'Poner la marca en 5 escenas de la Eplicula/serie'),(4,'Cocacola',10000000,'Mostrar en todas las bebidas de la pelicula/serie'),(5,'McDonals',2000000,'Poner la marca en 5 escenas de la Eplicula/serie');
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
  `numtemporada` int(11) DEFAULT NULL,
  `numCapitulo` int(11) DEFAULT NULL,
  `nombreCap` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idObra`),
  CONSTRAINT `serie_ibfk_1` FOREIGN KEY (`idObra`) REFERENCES `obraaudiovisual` (`idObra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serie`
--

LOCK TABLES `serie` WRITE;
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoguion`
--

DROP TABLE IF EXISTS `tipoguion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoguion` (
  `idTrabajador` int(11) NOT NULL,
  `tipoGuion` varchar(20) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`tipoGuion`),
  CONSTRAINT `tipoguion_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoguion`
--

LOCK TABLES `tipoguion` WRITE;
/*!40000 ALTER TABLE `tipoguion` DISABLE KEYS */;
INSERT INTO `tipoguion` VALUES (1,'terror');
/*!40000 ALTER TABLE `tipoguion` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES (1,'16090622Y','lander','guarrotxena',688601455,22,'paseo del puerto','actor','2000-11-11'),(2,'16090788X','Iker','Perez',688603355,21,'tartanga','guionista','2004-11-11'),(3,'12080622Y','leo','gomez',688634555,11,'avenida algorta','director','2011-11-11'),(4,'16099875Y','ander','gonzalez',688334565,2,'basagoiti','tecnicoAudiovisual','2001-11-11'),(5,'79231515S','Josue','Vargas',688644145,0,'Txorierriko Etorbidea','TecnicoAudioVisual','1993-09-29'),(6,'17231515S','Henrique','Yeguo',778644145,2,'Avenida Zarautz','Actor','2000-09-10'),(7,'17255515S','Haizea','Franco',755441452,1,'Plaza Moyua','Director','2001-10-11'),(8,'17231215S','Elias','Etxebarria',722644145,3,'Astrubudua','Actor','2003-09-10'),(9,'20231515S','Jon','Novoa',776644145,2,'Barakaldo','Guionista','2005-09-10');
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador_usuario`
--

DROP TABLE IF EXISTS `trabajador_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajador_usuario` (
  `idTrabajador` int(11) NOT NULL,
  `idUsuario` varchar(20) NOT NULL,
  `tipoUsuario` varchar(20) NOT NULL,
  PRIMARY KEY (`idTrabajador`,`idUsuario`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `trabajador_usuario_ibfk_1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE CASCADE,
  CONSTRAINT `trabajador_usuario_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador_usuario`
--

LOCK TABLES `trabajador_usuario` WRITE;
/*!40000 ALTER TABLE `trabajador_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `trabajador_usuario` ENABLE KEYS */;
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
  `passwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('haizea.franco','abcd*1234'),('henrique.yeguo','abcd*1234'),('ikerperez','abcd*1234'),('jon.novoa','abcd*1234'),('josue.vargas','abcd*1234'),('lander.guarrotxena','abcd*1234');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-08 14:09:56
