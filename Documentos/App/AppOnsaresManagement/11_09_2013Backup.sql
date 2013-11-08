-- MySQL dump 10.13  Distrib 5.5.32, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: onsareslog
-- ------------------------------------------------------
-- Server version	5.5.32-0ubuntu0.12.04.1

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
-- Table structure for table `arrendatario`
--

DROP TABLE IF EXISTS `arrendatario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arrendatario` (
  `NIF` varchar(10) NOT NULL COMMENT 'NIF del transportista.',
  `nombre` varchar(45) NOT NULL COMMENT 'Nombre del arrendatario.',
  `apellido1` varchar(45) NOT NULL COMMENT 'Primer apellido.',
  `apellido2` varchar(45) DEFAULT NULL COMMENT 'Segundo apellido.',
  `Direccion` varchar(70) DEFAULT NULL COMMENT 'Dirección del transportista.',
  `inicio_alquiler` date NOT NULL COMMENT 'Fecha inicio alquiler.',
  `fin_alquiler` date DEFAULT NULL COMMENT 'Fecha fin de alquiler.',
  `email` varchar(45) DEFAULT NULL COMMENT 'Email del transportista.',
  `movil` varchar(20) DEFAULT NULL COMMENT 'Móvil del transportista.',
  `telefono` varchar(20) DEFAULT NULL COMMENT 'Teléfono fijo del transportista.',
  `meses_retraso` int(11) DEFAULT NULL COMMENT 'Meses que acumula de retraso en pagos.',
  `deuda` decimal(20,2) DEFAULT NULL COMMENT 'Cantidad adeudada a la empresa en €.',
  PRIMARY KEY (`NIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrendatario`
--

LOCK TABLES `arrendatario` WRITE;
/*!40000 ALTER TABLE `arrendatario` DISABLE KEYS */;
/*!40000 ALTER TABLE `arrendatario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `averias`
--

DROP TABLE IF EXISTS `averias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `averias` (
  `idAveria` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL COMMENT 'Fecha en la que se produce la avería.',
  `Precio` decimal(10,2) DEFAULT NULL COMMENT 'Precio de la avería en €.',
  `tipo_averia` varchar(45) DEFAULT NULL COMMENT 'Tipo de avería (reventón ruedas...)',
  `Remolque_matricula` varchar(30) NOT NULL,
  PRIMARY KEY (`idAveria`,`Remolque_matricula`),
  KEY `fk_Averias_Remolque1_idx` (`Remolque_matricula`),
  CONSTRAINT `fk_Averias_Remolque1` FOREIGN KEY (`Remolque_matricula`) REFERENCES `remolque` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `averias`
--

LOCK TABLES `averias` WRITE;
/*!40000 ALTER TABLE `averias` DISABLE KEYS */;
/*!40000 ALTER TABLE `averias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `CifNif` varchar(15) NOT NULL COMMENT 'CIF/NIF del cliente.',
  `Nombre` varchar(45) DEFAULT NULL COMMENT 'Nombre del cliente.',
  `Calle` varchar(60) DEFAULT NULL,
  `NumViajes` int(11) DEFAULT NULL COMMENT 'Número de viajes realizados con este cliente.',
  `movil` varchar(20) DEFAULT NULL COMMENT 'Móvil del cliente. Como puede llamar desde centralita no lo limitamos a 9 caracteres.',
  `fijo` varchar(20) DEFAULT NULL COMMENT 'Teléfono fijo del cliente. No limitado a 9 caracteres porque pueden llamar desde centralita.',
  `email` varchar(45) DEFAULT NULL COMMENT 'Email del cliente.',
  `numero` varchar(45) DEFAULT NULL COMMENT 'Número de la sede.',
  `codigoPostal` int(11) DEFAULT NULL,
  `Poblacion` varchar(45) DEFAULT NULL COMMENT 'Población donde se encuentra la sede del cliente.',
  `Provincia` varchar(45) DEFAULT NULL COMMENT 'Provincia de la sede.',
  `contacto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CifNif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('12341j','naviera la vega',NULL,NULL,'135431','583164','elemail@correos.com','131',46310,'Su pueblo','Valencia',''),('1341f','Nuevo cliente','su calle',NULL,'12345677','13413413','prueba@prueba.com','12',80031,'Paiporta','Barcelona','Enrique'),('21561as','asdkfj',NULL,NULL,'113513221','351461','suemail@email.com','27',46200,'Paiporta','Valencia','Nacho'),('3135asdf','Soy Enrique',NULL,NULL,'3445','646464','enriquito@hotmail.com','54654',46210,'Picanya','Valencia','Heidi'),('321654s','bbb',NULL,NULL,'134564','56465465','suemail@email.com','22',46200,'Paiporta','Valencia','Nacho'),('46161561S','Mercadona','asdfser',NULL,'23135644','98465121','mercadona@mercadona.com','1654',96456,'Madrid','Madrid','Paco'),('564564asdf','asdfjjpi','poligono industrial',NULL,'986564545','98798456','aleel@hotmail.com','3',46200,'Paiporta','Valencia','Alejandro'),('alskdjf234J','Perico de los palotes',NULL,NULL,'32135135','13213515','notengo@email.com','97987',46123,'Picanya','Valencia','Batman');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `idFactura` int(11) NOT NULL COMMENT 'Número de factura-año (las dós últimas cifras del año).',
  `SerieFactura` varchar(1) DEFAULT NULL COMMENT 'Serie de la factura: Alquiler (A), transporte (T), otros...\n',
  `fecha` date DEFAULT NULL COMMENT 'Fecha en la que se emite la factura.',
  PRIMARY KEY (`idFactura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresos`
--

DROP TABLE IF EXISTS `ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingresos` (
  `idIngresos` int(11) NOT NULL,
  `fecha` date DEFAULT NULL COMMENT 'Fecha del ingreso relativo al remolque.',
  `cantidad` decimal(6,2) DEFAULT NULL COMMENT 'Cantidad del ingreso debido al alquiler/enganche del remolque.',
  `Remolque_matricula` varchar(30) NOT NULL,
  PRIMARY KEY (`idIngresos`,`Remolque_matricula`),
  KEY `fk_Ingresos_Remolque1_idx` (`Remolque_matricula`),
  CONSTRAINT `fk_Ingresos_Remolque1` FOREIGN KEY (`Remolque_matricula`) REFERENCES `remolque` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresos`
--

LOCK TABLES `ingresos` WRITE;
/*!40000 ALTER TABLE `ingresos` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mantenimiento`
--

DROP TABLE IF EXISTS `mantenimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mantenimiento` (
  `idMantenimiento` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL COMMENT 'Fecha en la que se realiza el mantenimiento.',
  `tipo` varchar(60) DEFAULT NULL COMMENT 'Tipo de mantenimiento a realizar: itv, seguros, impuesto circulación...',
  `precio` decimal(6,2) DEFAULT NULL COMMENT 'Precio pagado por el mantenimiento.',
  `Remolque_matricula` varchar(30) NOT NULL,
  PRIMARY KEY (`idMantenimiento`,`Remolque_matricula`),
  KEY `fk_Mantenimiento_Remolque1_idx` (`Remolque_matricula`),
  CONSTRAINT `fk_Mantenimiento_Remolque1` FOREIGN KEY (`Remolque_matricula`) REFERENCES `remolque` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mantenimiento`
--

LOCK TABLES `mantenimiento` WRITE;
/*!40000 ALTER TABLE `mantenimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `mantenimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remolque`
--

DROP TABLE IF EXISTS `remolque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remolque` (
  `matricula` varchar(30) NOT NULL COMMENT 'Matrícula del remolque propiedad de la empresa.',
  `itv` datetime DEFAULT NULL COMMENT 'Fecha en la que ha de pasar la itv.',
  `estado` varchar(20) DEFAULT NULL COMMENT 'Alquilado/Enganche/Ninguno',
  `num_averias` int(11) DEFAULT NULL COMMENT 'Número de averías totales del remolque.',
  `Arrendatario_NIF` varchar(10) NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `fk_Remolque_Arrendatario1_idx` (`Arrendatario_NIF`),
  CONSTRAINT `fk_Remolque_Arrendatario1` FOREIGN KEY (`Arrendatario_NIF`) REFERENCES `arrendatario` (`NIF`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remolque`
--

LOCK TABLES `remolque` WRITE;
/*!40000 ALTER TABLE `remolque` DISABLE KEYS */;
/*!40000 ALTER TABLE `remolque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportista`
--

DROP TABLE IF EXISTS `transportista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transportista` (
  `NIF` varchar(15) NOT NULL COMMENT '7 u 8 números con una letra.',
  `nombre` varchar(30) NOT NULL COMMENT 'Nombre del transportista.',
  `apellido1` varchar(30) NOT NULL COMMENT 'Primer apellido del transportista.',
  `apellido2` varchar(30) DEFAULT NULL COMMENT 'Segundo apellido del transportista.',
  `matricula_tractora` varchar(20) DEFAULT NULL COMMENT 'Matrícula camión.',
  `matricula_remolque` varchar(20) DEFAULT NULL COMMENT 'Matrícula del remolque.',
  `dirección` varchar(45) DEFAULT NULL,
  `num_viajes` int(11) DEFAULT NULL COMMENT 'Número de viajes realizados para la empresa.',
  `kms` int(11) DEFAULT NULL COMMENT 'Kilómetros realizados.',
  `ingresos_totales` decimal(20,2) DEFAULT NULL COMMENT 'Cantidad total pagada a transportista.',
  `valoración` int(11) DEFAULT NULL COMMENT 'Valoración por parte de la empresa del transportista.',
  `email` varchar(45) DEFAULT NULL COMMENT 'Email del transportista.',
  `telefono` varchar(20) DEFAULT NULL,
  `numCuenta` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`NIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportista`
--

LOCK TABLES `transportista` WRITE;
/*!40000 ALTER TABLE `transportista` DISABLE KEYS */;
INSERT INTO `transportista` VALUES ('2135425s','Antonio','Palenzuela','Prada','0987-ASD','1234-FDS','Avda. los naranjos',NULL,NULL,NULL,NULL,'antonioVal@correo.com','123456845','12344987221234567895'),('21456789L','Enrique','Marín ','Avilés','9087KLN','4567PIO','Parque central',NULL,NULL,NULL,NULL,'micorreo@correo.com','213654897','6548454541125674224'),('23456789K','Luis','Bauxauí','Barberá','8976ERT','1234-FRT','Calle San Pascual',NULL,NULL,NULL,NULL,'lubaubar@hotmail.com','123456789','1234454541125674224'),('26874522-D','Pedro','Picapiedra','Ruíz','3456-DSC','0954-BBC','Sin casa',NULL,NULL,NULL,NULL,'pedropicapiedra@hotmail.com','963568742',''),('29201894J','Manuel','Martinez','Carbonell','9054BVC','4564-BF','Cerca de tú casa',NULL,NULL,NULL,NULL,'tengoemail@tuno.com','617536621','12351235211236549870'),('32156987E','Enrique','Marín','Pérez','1234DSF','0021BSF','Avda. el metro nº13',NULL,NULL,NULL,NULL,'enrique@camiones.com','963976357','12340123011234567890'),('42156987E','Enrique','Marín','Pérez','1234DSF','4321BSF','Avda. el metro nº13',NULL,NULL,NULL,NULL,'enrique@camiones.com','963976357','12340123011234567890');
/*!40000 ALTER TABLE `transportista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `user` varchar(45) NOT NULL COMMENT 'usuario',
  `password` varchar(45) DEFAULT NULL COMMENT 'Contraseña usuario.',
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('Carmen','21102002');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaje`
--

DROP TABLE IF EXISTS `viaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `viaje` (
  `albaran` varchar(25) NOT NULL COMMENT 'Identificación del viaje por parte de la empresa. El formato es año/nº viajes. El año son las 2 últimas cifras.',
  `fecha` date DEFAULT NULL,
  `ingreso` decimal(30,2) DEFAULT NULL COMMENT 'Dinero que paga a la empresa el cliente en € sin IVA.',
  `pago_a_camionero` decimal(30,2) DEFAULT NULL COMMENT 'Dinero que paga la empresa al camionero. En €. Con el 1% de IRPF descontado.',
  `kms` varchar(45) DEFAULT NULL COMMENT 'Kilométros del viaje. ',
  `numero_contenedor` varchar(20) DEFAULT NULL COMMENT 'Número del contenedor.',
  `destinatario` varchar(45) DEFAULT NULL COMMENT 'Quien recibe la mercancia.',
  `poblacion` varchar(45) DEFAULT NULL COMMENT 'Lugar donde se realiza la entrega.',
  `tipo_pago` int(11) DEFAULT NULL COMMENT 'Número de días que tarda en realizarse el pago por parte del cliente.',
  `IVA` varchar(1) DEFAULT NULL COMMENT 'Si el cliente paga el iva (S) o no (N). En caso de que cliente pague el iva el pago es completo. En caso de que no pague el iva este tiene que abonarlo Hacienda al año siguiente.',
  `estado_cliente` varchar(45) NOT NULL COMMENT 'Si el cliente ha pagado o no. Posibles estados: PAGADO/PENDIENTE.',
  `estado_transportista` varchar(45) NOT NULL COMMENT 'Estado con el transportista, si se ha pagado o no al camionero. Posibles estados: PAGADO/PENDIENTE',
  `Factura_idFactura` int(11) DEFAULT NULL,
  `Cliente_CifNif` varchar(15) NOT NULL,
  `Transportista_NIF` varchar(15) NOT NULL,
  PRIMARY KEY (`albaran`,`Cliente_CifNif`,`Transportista_NIF`),
  KEY `fk_Viaje_Factura1_idx` (`Factura_idFactura`),
  KEY `fk_Viaje_Cliente1_idx` (`Cliente_CifNif`),
  KEY `fk_Viaje_Transportista1_idx` (`Transportista_NIF`),
  CONSTRAINT `fk_Viaje_Cliente1` FOREIGN KEY (`Cliente_CifNif`) REFERENCES `cliente` (`CifNif`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Viaje_Factura1` FOREIGN KEY (`Factura_idFactura`) REFERENCES `factura` (`idFactura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Viaje_Transportista1` FOREIGN KEY (`Transportista_NIF`) REFERENCES `transportista` (`NIF`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES ('0/12','2012-09-18',180.00,121.00,'125,36',NULL,'Mercadona','Aranda del Duero',25,'S','Pendiente','Pendiente',NULL,'321654s','21456789L'),('0/13','2013-09-25',150.56,121.00,'125,36',NULL,'Mercadona','Aranda del Duero',25,'S','Pendiente','Pendiente',NULL,'321654s','21456789L'),('0/14','2014-01-01',231.34,200.11,'235,34',NULL,'Imprentas Ruiz','Lliria',67,'N','Pagado','Pendiente',NULL,'46161561S','29201894J'),('1/13','2013-09-18',1800.00,121.00,'125,36',NULL,'',NULL,25,'S','Pendiente','Pendiente',NULL,'321654s','21456789L'),('2/13','2013-10-01',152.00,130.00,'100',NULL,'DHL','DHL',32,'S','Pagado','Pagado',NULL,'321654s','2135425s'),('3/13','2013-01-10',156.15,123.00,'250',NULL,'Carrefour','Paiporta',45,'S','Pagado','Pendiente',NULL,'321654s','26874522-D'),('4/13','2013-08-12',2004.00,100.00,'1000',NULL,'Mercedes-Benz','Marbella',52,'S','Pagado','Pendiente',NULL,'321654s','29201894J'),('5/13','2013-08-31',132.15,125.02,'50',NULL,'Fertiberia','Fertiberia',50,'S','Pagado','Pagado',NULL,'46161561S','2135425s'),('6/13','2013-09-09',153.12,115.25,'125','164861213SAS','Recambios Pepito','Recambios Pepito',9,'S','Pagado','Pagado',NULL,'12341j','23456789K'),('7/13','2013-08-01',1000.11,936.00,'150,8','646a4eas','Muebles Lara','Muebles Lara',7,'N','Pagado','Pagado',NULL,'alskdjf234J','2135425s'),('8/13','2013-01-01',152.15,123.15,'236,25','6486656161DRF','Consum','Mieres',8,'S','Pendiente','Pagado',NULL,'46161561S','23456789K');
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-11 11:38:47
