CREATE DATABASE  IF NOT EXISTS `paredb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `paredb`;
-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: paredb
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `autos`
--

DROP TABLE IF EXISTS `autos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autos` (
  `id` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) DEFAULT NULL,
  `progresivo` char(6) DEFAULT NULL,
  `entrada_salida` char(1) DEFAULT 'E' COMMENT '(E) - PARA ENTRADA   \r(\nS) - PARA SALIDA',
  `fecha_entrada` varchar(10) DEFAULT NULL,
  `fecha_salida` varchar(10) DEFAULT NULL,
  `hora_entrada` varchar(5) DEFAULT NULL,
  `hora_salida` varchar(5) DEFAULT NULL,
  `horas_estadia` int(3) DEFAULT NULL,
  `minutos_estadia` int(3) DEFAULT NULL,
  `monto` double DEFAULT '0',
  `turno_entrada_id` int(11) DEFAULT NULL,
  `turno_salida_id` int(11) DEFAULT NULL,
  `id_tarifa` int(6) unsigned zerofill DEFAULT NULL,
  `id_caseta` int(3) DEFAULT NULL,
  `operador_entrada` int(10) DEFAULT NULL,
  `operador_salida` int(10) DEFAULT '0',
  `id_boleto_perdido` int(11) DEFAULT '0',
  `id_boleto_cancelado` int(11) DEFAULT '0',
  `id_boleto_contra` int(11) DEFAULT '0',
  `id_boleto_manual` int(11) DEFAULT '0',
  `boleto_perdido` varchar(4) DEFAULT 'NO',
  `boleto_cancelado` varchar(4) DEFAULT 'NO',
  `boleto_contra` varchar(4) DEFAULT 'NO',
  `boleto_manual` varchar(4) DEFAULT 'NO',
  `boleto_pendiente` varchar(4) DEFAULT 'NO',
  `recibo` varchar(5) DEFAULT 'NO',
  `marca` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `clave` char(6) DEFAULT NULL,
  `descuento` decimal(10,0) DEFAULT '0',
  `serie` char(2) DEFAULT '0',
  `notas` varchar(80) DEFAULT NULL,
  `id_cliente` int(5) DEFAULT NULL,
  `estado_servidor` varchar(4) DEFAULT '0',
  `id_remoto` varchar(45) DEFAULT NULL,
  `boleto_oficina` varchar(4) CHARACTER SET dec8 DEFAULT 'NO',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autos`
--

LOCK TABLES `autos` WRITE;
/*!40000 ALTER TABLE `autos` DISABLE KEYS */;
INSERT INTO `autos` VALUES (000001,'','000048','S','2015-01-28','2015-01-28','13:26','13:28',0,2,0,1,1,000001,1,1,0,0,1,0,0,'NO','SI','NO','NO','NO','SI','',NULL,NULL,'PETBR',0,'0','',NULL,'2','54c81527faa6ce7c0a4cb669','NO'),(000002,'','000049','S','2015-01-28','2015-01-28','13:27','13:27',0,0,34,1,1,000001,1,1,0,0,0,0,0,'NO','NO','NO','NO','NO','SI','',NULL,NULL,'HUAVI',0,'0','',NULL,'2','54c81530faa6ce7c0a4cb66a','NO'),(000003,'','000050','E','2015-01-28',NULL,'13:30',NULL,NULL,NULL,0,2,NULL,000001,1,1,0,0,0,0,0,'NO','NO','NO','NO','NO','NO','',NULL,NULL,'TANXO',0,'0','',NULL,'1','54c8156dfaa6ce7c0a4cb66b','NO'),(000004,'','000051','E','2015-02-05',NULL,'12:52',NULL,NULL,NULL,0,3,NULL,000001,1,1,0,0,0,0,0,'NO','NO','NO','NO','NO','NO','',NULL,NULL,'CNCIS',0,'0','',NULL,'1','54d3bc0efdf6490300469632','NO'),(000005,'','000052','S','2015-02-05','2015-02-05','12:56','12:58',0,2,234,3,3,000001,1,1,0,1,0,0,0,'SI','NO','NO','NO','NO','SI','11','1','1','HLUIQ',0,'0','',NULL,'2','54d3bcccfdf649030046963d','NO'),(000006,'','000053','S','2015-02-05','2015-02-05','13:02','13:03',0,1,200,3,3,000001,1,1,0,2,0,0,0,'SI','NO','NO','NO','NO','SI','1','1','1','SUUFX',0,'0','',NULL,'2','54d3be4dfdf6490300469651','NO');
/*!40000 ALTER TABLE `autos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto_cancelado`
--

DROP TABLE IF EXISTS `boleto_cancelado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto_cancelado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_turno` int(11) DEFAULT NULL,
  `id_auto` int(11) DEFAULT NULL,
  `razon` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto_cancelado`
--

LOCK TABLES `boleto_cancelado` WRITE;
/*!40000 ALTER TABLE `boleto_cancelado` DISABLE KEYS */;
INSERT INTO `boleto_cancelado` VALUES (1,1,1,'El cliente no se quedo');
/*!40000 ALTER TABLE `boleto_cancelado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto_manual`
--

DROP TABLE IF EXISTS `boleto_manual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto_manual` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_entrada` varchar(10) DEFAULT NULL,
  `fecha_salida` varchar(10) DEFAULT NULL,
  `hora_entrada` varchar(10) DEFAULT NULL,
  `hora_salida` varchar(10) DEFAULT NULL,
  `razon` varchar(45) DEFAULT NULL,
  `id_auto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto_manual`
--

LOCK TABLES `boleto_manual` WRITE;
/*!40000 ALTER TABLE `boleto_manual` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleto_manual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto_pendiente`
--

DROP TABLE IF EXISTS `boleto_pendiente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto_pendiente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_auto` int(11) DEFAULT '0',
  `id_turno_pendiente` int(11) DEFAULT '0',
  `serie` varchar(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto_pendiente`
--

LOCK TABLES `boleto_pendiente` WRITE;
/*!40000 ALTER TABLE `boleto_pendiente` DISABLE KEYS */;
INSERT INTO `boleto_pendiente` VALUES (1,3,2,'0'),(2,3,3,'0'),(3,4,3,'0');
/*!40000 ALTER TABLE `boleto_pendiente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto_perdido`
--

DROP TABLE IF EXISTS `boleto_perdido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto_perdido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `progresivo` int(11) DEFAULT NULL,
  `id_auto` int(11) DEFAULT NULL,
  `id_turno` int(11) DEFAULT NULL,
  `id_propietario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto_perdido`
--

LOCK TABLES `boleto_perdido` WRITE;
/*!40000 ALTER TABLE `boleto_perdido` DISABLE KEYS */;
INSERT INTO `boleto_perdido` VALUES (1,1,5,3,1),(2,2,6,3,2);
/*!40000 ALTER TABLE `boleto_perdido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caja`
--

DROP TABLE IF EXISTS `caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caja` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monto` decimal(10,0) DEFAULT NULL,
  `id_caseta` int(11) DEFAULT NULL,
  `fondo` varchar(45) DEFAULT NULL,
  `monto_alarma` int(11) DEFAULT '500',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja`
--

LOCK TABLES `caja` WRITE;
/*!40000 ALTER TABLE `caja` DISABLE KEYS */;
INSERT INTO `caja` VALUES (1,0,1,'1000',2000);
/*!40000 ALTER TABLE `caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caseta`
--

DROP TABLE IF EXISTS `caseta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caseta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `id_estacionameinto` int(11) DEFAULT NULL,
  `id_tarifa` int(11) DEFAULT NULL,
  `series` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caseta`
--

LOCK TABLES `caseta` WRITE;
/*!40000 ALTER TABLE `caseta` DISABLE KEYS */;
INSERT INTO `caseta` VALUES (1,'Caseta 1',1,1,'0');
/*!40000 ALTER TABLE `caseta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descuentos`
--

DROP TABLE IF EXISTS `descuentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descuentos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `folio` varchar(45) DEFAULT NULL,
  `descuento` decimal(10,0) DEFAULT NULL,
  `activo` varchar(20) DEFAULT NULL,
  `clave` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descuentos`
--

LOCK TABLES `descuentos` WRITE;
/*!40000 ALTER TABLE `descuentos` DISABLE KEYS */;
INSERT INTO `descuentos` VALUES (1,'2001',16,'si','MMFDJC'),(2,'2002',16,'si','CWIYYR'),(3,'2003',16,'si','YJTFOM'),(4,'2004',16,'si','CKPQYM'),(5,'2005',16,'si','FKECND'),(6,'2006',16,'si','CEGEAU'),(7,'2007',16,'si','LARJFM'),(8,'2008',16,'si','TFBUAU'),(9,'2009',16,'si','WRHXUG'),(10,'2010',16,'si','DRIJCI'),(11,'2011',16,'si','EAEKYQ'),(12,'2012',16,'si','SMEUVC'),(13,'2013',16,'si','XYCOJR'),(14,'2014',16,'si','EBPQHA'),(15,'2015',16,'si','BTHHDY'),(16,'2016',16,'si','JLEBSQ'),(17,'2017',16,'si','YOKIAY'),(18,'2018',16,'si','ILOXIF'),(19,'2019',16,'si','EXCIFW'),(20,'2020',16,'si','IGYWJB'),(21,'2021',16,'si','FFJGFO'),(22,'2022',16,'si','VGBGJC'),(23,'2023',16,'si','KMDMEV'),(24,'2024',16,'si','USPBYV'),(25,'2025',16,'si','YVHFIQ'),(26,'2026',16,'si','YFJKJK'),(27,'2027',16,'si','GKEDIN'),(28,'2028',16,'si','HUGMSI'),(29,'2029',16,'si','WNLVWA'),(30,'2030',16,'si','VSPPMB'),(31,'2031',16,'si','HMJHRU'),(32,'2032',16,'si','BOEVAF'),(33,'2033',16,'si','UFSBYO'),(34,'2034',16,'si','WMQIMX'),(35,'2035',16,'si','FXUJFM'),(36,'2036',16,'si','WAWPYF'),(37,'2037',16,'si','YBSSOS'),(38,'2038',16,'si','EOTFVB'),(39,'2039',16,'si','TBIQBI'),(40,'2040',16,'si','MKQQYT'),(41,'2041',16,'si','JMKLXH'),(42,'2042',16,'si','LRUICG'),(43,'2043',16,'si','XQMLLQ'),(44,'2044',16,'si','KIVTBN'),(45,'2045',16,'si','HDTYNR'),(46,'2046',16,'si','BLPGMA'),(47,'2047',16,'si','BVXPPF'),(48,'2048',16,'si','BNCOEP'),(49,'2049',16,'si','IKBMGP'),(50,'2050',16,'si','QHQFRE'),(51,'2051',16,'si','RLAKAD'),(52,'2052',16,'si','AYXKQC'),(53,'2053',16,'si','MLCOSS'),(54,'2054',16,'si','ADDXQG'),(55,'2055',16,'si','UDYRCL'),(56,'2056',16,'si','UKQRBL'),(57,'2057',16,'si','ENICAK'),(58,'2058',16,'si','MJKGLD'),(59,'2059',16,'si','FOWEMX'),(60,'2060',16,'si','ESFMXD'),(61,'2061',16,'si','XXNWAE'),(62,'2062',16,'si','UPHKTH'),(63,'2063',16,'si','FXGIRP'),(64,'2064',16,'si','WSWCSD'),(65,'2065',16,'si','TXPCOJ'),(66,'2066',16,'si','TIGXTE'),(67,'2067',16,'si','KOSANJ'),(68,'2068',16,'si','YCWOQA'),(69,'2069',16,'si','SWSOJO'),(70,'2070',16,'si','NBYMDG'),(71,'2071',16,'si','YRIRTG'),(72,'2072',16,'si','FNIUWW'),(73,'2073',16,'si','WMBVKG'),(74,'2074',16,'si','DGBAUQ'),(75,'2075',16,'si','BCQJDD'),(76,'2076',16,'si','SYMWTT'),(77,'2077',16,'si','XUGEII'),(78,'2078',16,'si','USTVOO'),(79,'2079',16,'si','KTHCGH'),(80,'2080',16,'si','CLEWJP'),(81,'2081',16,'si','TDUCLC'),(82,'2082',16,'si','LVKGAY'),(83,'2083',16,'si','SWUAMA'),(84,'2084',16,'si','UPWBFX'),(85,'2085',16,'si','WADLTN'),(86,'2086',16,'si','UUSVGF'),(87,'2087',16,'si','RYAAIF'),(88,'2088',16,'si','PCGMJS'),(89,'2089',16,'si','KBNFAY'),(90,'2090',16,'si','KCTCGI'),(91,'2091',16,'si','DKAOBF'),(92,'2092',16,'si','PKJLTH'),(93,'2093',16,'si','NISNYM'),(94,'2094',16,'si','TQRIQO'),(95,'2095',16,'si','OLTSFA'),(96,'2096',16,'si','LDMUBY'),(97,'2097',16,'si','NHPITY'),(98,'2098',16,'si','KGHDJX'),(99,'2099',16,'si','PFDKGG'),(100,'2100',16,'si','PSOPTU'),(101,'2101',16,'si','CUBQTS'),(102,'2102',16,'si','VKYOUI'),(103,'2103',16,'si','RRWLBM'),(104,'2104',16,'si','ATAMRC'),(105,'2105',16,'si','UNLLVI'),(106,'2106',16,'si','YQLTFJ'),(107,'2107',16,'si','QHAPCT'),(108,'2108',16,'si','RISJLQ'),(109,'2109',16,'si','NUTRAP'),(110,'2110',16,'si','AAIFOJ'),(111,'2111',16,'si','LYAFBU'),(112,'2112',16,'si','VGFHPF'),(113,'2113',16,'si','PTUIAY'),(114,'2114',16,'si','DXDHXB'),(115,'2115',16,'si','FQXSTS'),(116,'2116',16,'si','QLNJGF'),(117,'2117',16,'si','MOXCID'),(118,'2118',16,'si','VHIWKR'),(119,'2119',16,'si','CCIAJO'),(120,'2120',16,'si','XYKSII'),(121,'2121',16,'si','VFNRIE'),(122,'2122',16,'si','IYIMUA'),(123,'2123',16,'si','NQMHXE'),(124,'2124',16,'si','WEDYUF'),(125,'2125',16,'si','XIEFAU'),(126,'2126',16,'si','NWFCTW'),(127,'2127',16,'si','QFOXNH'),(128,'2128',16,'si','TCGRPE'),(129,'2129',16,'si','MOCTLO'),(130,'2130',16,'si','PHPJNT'),(131,'2131',16,'si','USLJYC'),(132,'2132',16,'si','FBDJSD'),(133,'2133',16,'si','INHMEM'),(134,'2134',16,'si','TUREGI'),(135,'2135',16,'si','BWRMRB'),(136,'2136',16,'si','QHYDPL'),(137,'2137',16,'si','EDTQLO'),(138,'2138',16,'si','ADYPVR'),(139,'2139',16,'si','TPJPAI'),(140,'2140',16,'si','TQSUJI'),(141,'2141',16,'si','OPYBGF'),(142,'2142',16,'si','XAVSOK'),(143,'2143',16,'si','YYKXEW'),(144,'2144',16,'si','NTGWEL'),(145,'2145',16,'si','HHQTAR'),(146,'2146',16,'si','JBNDRB'),(147,'2147',16,'si','QCWMCS'),(148,'2148',16,'si','IDKMDM'),(149,'2149',16,'si','JEPLQX'),(150,'2150',16,'si','BTMNSP'),(151,'2151',16,'si','VSVQXL'),(152,'2152',16,'si','AIVCCQ'),(153,'2153',16,'si','MXAYYR'),(154,'2154',16,'si','JFPFQW'),(155,'2155',16,'si','GKFBQH'),(156,'2156',16,'si','RQEGUD'),(157,'2157',16,'si','JORSMR'),(158,'2158',16,'si','XTSJNS'),(159,'2159',16,'si','UNHSIT'),(160,'2160',16,'si','KVCGKI'),(161,'2161',16,'si','KFTATG'),(162,'2162',16,'si','SWOAXN'),(163,'2163',16,'si','JAMUOQ'),(164,'2164',16,'si','ASGULE'),(165,'2165',16,'si','TQKNYH'),(166,'2166',16,'si','PHGKHU'),(167,'2167',16,'si','QLMMWU'),(168,'2168',16,'si','HSUQTT'),(169,'2169',16,'si','AIANYR'),(170,'2170',16,'si','EIIIQV'),(171,'2171',16,'si','MXTEKP'),(172,'2172',16,'si','TRIHMO'),(173,'2173',16,'si','PKCKMK'),(174,'2174',16,'si','DUOEGH'),(175,'2175',16,'si','UHFEHI'),(176,'2176',16,'si','LULOVJ'),(177,'2177',16,'si','WOCPNK'),(178,'2178',16,'si','BCHYXE'),(179,'2179',16,'si','NPBYAT'),(180,'2180',16,'si','LACVBA'),(181,'2181',16,'si','TINONI'),(182,'2182',16,'si','HYTAYV'),(183,'2183',16,'si','DFATFX'),(184,'2184',16,'si','JIMOYP'),(185,'2185',16,'si','JHHIIW'),(186,'2186',16,'si','WEAHPW'),(187,'2187',16,'si','XHKPRK'),(188,'2188',16,'si','TKMSPS'),(189,'2189',16,'si','JWEPFE'),(190,'2190',16,'si','PSVUAN'),(191,'2191',16,'si','YTJPTT'),(192,'2192',16,'si','AAWRIU'),(193,'2193',16,'si','QFKNOT'),(194,'2194',16,'si','RQNJKX'),(195,'2195',16,'si','GXJCKJ'),(196,'2196',16,'si','TMIKDL'),(197,'2197',16,'si','BSONQP'),(198,'2198',16,'si','DDYLPL'),(199,'2199',16,'si','XICSKK'),(200,'2200',16,'si','FRACXV'),(201,'2201',16,'si','LXRGDG'),(202,'2202',16,'si','YQDJSQ'),(203,'2203',16,'si','IPAASJ'),(204,'2204',16,'si','JLNGEI'),(205,'2205',16,'si','OWGUBE'),(206,'2206',16,'si','KCNRWT'),(207,'2207',16,'si','URDIPG'),(208,'2208',16,'si','CTSAWN'),(209,'2209',16,'si','DJSFVA'),(210,'2210',16,'si','UOXLDT'),(211,'2211',16,'si','LNIFRW'),(212,'2212',16,'si','VPOIRX'),(213,'2213',16,'si','EUYDKY'),(214,'2214',16,'si','JPIITK'),(215,'2215',16,'si','MWMJEK'),(216,'2216',16,'si','MBPJIU'),(217,'2217',16,'si','UJXJGY'),(218,'2218',16,'si','JVYVKT'),(219,'2219',16,'si','FIRLOA'),(220,'2220',16,'si','RKRIRO'),(221,'2221',16,'si','TFMQJA'),(222,'2222',16,'si','PODYKA'),(223,'2223',16,'si','ULOGTR'),(224,'2224',16,'si','HVREBD'),(225,'2225',16,'si','HUNHKP'),(226,'2226',16,'si','PLVRUA'),(227,'2227',16,'si','AFAPLQ'),(228,'2228',16,'si','LJQXQF'),(229,'2229',16,'si','GCSEAC'),(230,'2230',16,'si','GKRQQH'),(231,'2231',16,'si','HTVLWF'),(232,'2232',16,'si','NDXVGB'),(233,'2233',16,'si','AEMLQN'),(234,'2234',16,'si','JAEWWT'),(235,'2235',16,'si','FJMDQK'),(236,'2236',16,'si','YIKPTD'),(237,'2237',16,'si','UKLWIA'),(238,'2238',16,'si','GPXAVF'),(239,'2239',16,'si','EVTRKL'),(240,'2240',16,'si','QAMOKH'),(241,'2241',16,'si','PDARSC'),(242,'2242',16,'si','UFIQSS'),(243,'2243',16,'si','IAYVBI'),(244,'2244',16,'si','OHGFBY'),(245,'2245',16,'si','TRBVUM'),(246,'2246',16,'si','MYWYJD'),(247,'2247',16,'si','BEAIPM'),(248,'2248',16,'si','NLYAGH'),(249,'2249',16,'si','OLQLHS'),(250,'2250',16,'si','VFFFLG');
/*!40000 ALTER TABLE `descuentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_turno`
--

DROP TABLE IF EXISTS `detalle_turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_turno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_remoto` varchar(45) DEFAULT NULL,
  `folio_inicial` bigint(20) DEFAULT '0',
  `folio_final` bigint(20) DEFAULT '0',
  `no_bol_turno_a` int(11) DEFAULT '0',
  `no_bol_cancelados` int(11) DEFAULT '0',
  `no_bol_perdidos` int(11) DEFAULT '0',
  `no_bol_cobrados` int(11) DEFAULT '0',
  `no_bol_turno_s` int(11) DEFAULT '0',
  `total` decimal(10,0) DEFAULT '0',
  `no_bol` int(11) DEFAULT '0',
  `id_turno` int(11) DEFAULT NULL,
  `no_bol_manual` int(11) DEFAULT '0',
  `no_bol_contra` int(11) DEFAULT '0',
  `serie` varchar(45) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_turno`
--

LOCK TABLES `detalle_turno` WRITE;
/*!40000 ALTER TABLE `detalle_turno` DISABLE KEYS */;
INSERT INTO `detalle_turno` VALUES (1,'54c81792faa6ce7c0a4cb66d',48,50,0,1,0,1,0,34,2,1,0,0,'0'),(2,'54c92ebde281e58d0d639cf9',50,51,0,0,0,0,1,0,1,2,0,0,'0'),(3,'54c92efce281e58d0d639cfa',51,54,1,0,2,0,2,434,3,3,0,0,'0'),(4,'54d3bf3efdf649030046965e',54,54,2,0,0,0,0,0,0,4,0,0,'0');
/*!40000 ALTER TABLE `detalle_turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacionamiento`
--

DROP TABLE IF EXISTS `estacionamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estacionamiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `centro_costos` int(11) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `caseta_actual` int(11) DEFAULT NULL,
  `id_tarifa` int(11) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `contra` varchar(45) DEFAULT NULL,
  `id_remoto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacionamiento`
--

LOCK TABLES `estacionamiento` WRITE;
/*!40000 ALTER TABLE `estacionamiento` DISABLE KEYS */;
INSERT INTO `estacionamiento` VALUES (1,34,'oficina',1,1,'Museo de antropologia S/N','ValetMasivo','admin@pare.com.mx','5UxM0k','54c80f9424d4877a71d5f007');
/*!40000 ALTER TABLE `estacionamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progresivos`
--

DROP TABLE IF EXISTS `progresivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `progresivos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `ultimo_progresivo` char(12) DEFAULT NULL,
  `id_cajero` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progresivos`
--

LOCK TABLES `progresivos` WRITE;
/*!40000 ALTER TABLE `progresivos` DISABLE KEYS */;
INSERT INTO `progresivos` VALUES (1,'0','54',1),(2,'PERDIDO','3',1),(3,'RETIRO_PARCIAL','4',1),(4,'RECIBO_PAGO','36',1);
/*!40000 ALTER TABLE `progresivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propietario_perdido`
--

DROP TABLE IF EXISTS `propietario_perdido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propietario_perdido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `tipo_identificacion` varchar(45) DEFAULT NULL,
  `numero_identificacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propietario_perdido`
--

LOCK TABLES `propietario_perdido` WRITE;
/*!40000 ALTER TABLE `propietario_perdido` DISABLE KEYS */;
INSERT INTO `propietario_perdido` VALUES (1,'1','1','1','1','1'),(2,'1','1','11','11','11');
/*!40000 ALTER TABLE `propietario_perdido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retiro_parcial`
--

DROP TABLE IF EXISTS `retiro_parcial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retiro_parcial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `progresivo` int(11) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `hora` varchar(45) DEFAULT NULL,
  `id_caseta` int(11) DEFAULT NULL,
  `id_turno` int(11) DEFAULT NULL,
  `monto` decimal(10,0) DEFAULT NULL,
  `monto_real` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retiro_parcial`
--

LOCK TABLES `retiro_parcial` WRITE;
/*!40000 ALTER TABLE `retiro_parcial` DISABLE KEYS */;
INSERT INTO `retiro_parcial` VALUES (1,2,'2015-01-28','13:28',1,1,34,34),(2,3,'2015-02-05','13:03',1,3,434,434);
/*!40000 ALTER TABLE `retiro_parcial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarifa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fracciones` int(11) DEFAULT NULL,
  `costos` varchar(100) DEFAULT NULL,
  `precio_hora` decimal(10,0) DEFAULT NULL,
  `tarifa_maxima` decimal(10,0) DEFAULT NULL,
  `boleto_perdido` decimal(10,0) DEFAULT NULL,
  `hora_inicial` varchar(10) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `tarifa_unica` decimal(10,0) DEFAULT NULL,
  `monto_inicial` decimal(10,0) DEFAULT '0',
  `perdido_tiempo` varchar(4) DEFAULT 'SI',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES (1,4,'9.0@9.0@8.0@8.0',34,0,200,'1','Tarifa regular',0,0,'NO');
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turnos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_turno` varchar(45) DEFAULT NULL,
  `fecha_apertura` varchar(45) DEFAULT NULL,
  `fecha_cierre` varchar(45) DEFAULT NULL,
  `hora_apertura` varchar(5) DEFAULT NULL,
  `hora_cierre` varchar(5) DEFAULT NULL,
  `id_empleado_apertura` int(11) DEFAULT '0',
  `id_empleado_cierre` int(11) DEFAULT '0',
  `activo` varchar(4) DEFAULT 'NO',
  `id_remoto` varchar(45) DEFAULT NULL,
  `estado_servidor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos`
--

LOCK TABLES `turnos` WRITE;
/*!40000 ALTER TABLE `turnos` DISABLE KEYS */;
INSERT INTO `turnos` VALUES (1,'Primer turno','2015-01-28','2015-01-28','13:26','13:28',1,0,'NO','54c80f9524d4877a71d5f008','2'),(2,'Segundo turno','2015-01-28','2015-01-28','13:30','13:30',1,0,'NO','54c8149bfaa6ce7c0a4cb668','2'),(3,'Tercer turno','2015-01-28','2015-02-05','13:31','13:03',1,0,'NO','54c92efce281e58d0d639cfb','0'),(4,'Primer turno','2015-02-05',NULL,'13:06',NULL,1,0,'NO','54d3bf3ffdf649030046965f','1');
/*!40000 ALTER TABLE `turnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` tinyint(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_caseta` tinyint(3) unsigned zerofill DEFAULT NULL,
  `nombre` char(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `contras` char(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(12) DEFAULT NULL,
  `nombre_completo` varchar(80) NOT NULL,
  `tipo_empleado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (001,001,'Oscar','admin','.QWERT','Oscar Paredes','Administrador'),(002,001,'Valery','JYFIGJ','.LPHXX','Valery Vigueras','Supervisor');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-09 17:10:25
