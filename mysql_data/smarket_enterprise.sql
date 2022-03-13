-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: smarket_enterprise
-- ------------------------------------------------------
-- Server version	10.7.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprise` (
  `listed_co_id` int(11) NOT NULL,
  `isin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `art` float DEFAULT NULL,
  `cat` float DEFAULT NULL,
  `ci` float DEFAULT NULL,
  `fat` float DEFAULT NULL,
  `it` float DEFAULT NULL,
  `sett` float DEFAULT NULL,
  `tat` float DEFAULT NULL,
  `tnca` float DEFAULT NULL,
  `bussiness_capacity` float DEFAULT NULL,
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `car` float DEFAULT NULL,
  `grfa` float DEFAULT NULL,
  `grgoi` float DEFAULT NULL,
  `grop` float DEFAULT NULL,
  `grroe` float DEFAULT NULL,
  `grta` float DEFAULT NULL,
  `yyg` float DEFAULT NULL,
  `development_capacity` float DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `enterprise_vrisk` int(11) DEFAULT NULL,
  `establish_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `industry_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `industry_vrisk` int(11) DEFAULT NULL,
  `legal_representative` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ltb2ta` float DEFAULT NULL,
  `stb2ta` float DEFAULT NULL,
  `loan_rate` float DEFAULT NULL,
  `logo_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `main_business` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `office_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cgpr` float DEFAULT NULL,
  `cpm` float DEFAULT NULL,
  `egoi` float DEFAULT NULL,
  `gmos` float DEFAULT NULL,
  `nirs` float DEFAULT NULL,
  `npm` float DEFAULT NULL,
  `nrp` float DEFAULT NULL,
  `opm` float DEFAULT NULL,
  `roe` float DEFAULT NULL,
  `tnipoa` float DEFAULT NULL,
  `tocr` float DEFAULT NULL,
  `profitability` float DEFAULT NULL,
  `province` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `register_capital` bigint(20) DEFAULT NULL,
  `dtl` float DEFAULT NULL,
  `ol` float DEFAULT NULL,
  `risk_level` float DEFAULT NULL,
  `secretary` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `secretary_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `secretary_fax` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `secretary_tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `security_id` bigint(20) DEFAULT NULL,
  `short_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `alr` float DEFAULT NULL,
  `cficm` float DEFAULT NULL,
  `ncf` float DEFAULT NULL,
  `qr` float DEFAULT NULL,
  `rocfcl` float DEFAULT NULL,
  `rocfni` float DEFAULT NULL,
  `traim` float DEFAULT NULL,
  `solvency` float DEFAULT NULL,
  `symbol` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `website` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`listed_co_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `listed_co_id` int(11) NOT NULL,
  `control_list` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `industry_json` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `investment_list` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location_json` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`listed_co_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nop` int(11) DEFAULT NULL,
  `context` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `symbol` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-13 19:00:06
