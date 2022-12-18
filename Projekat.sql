CREATE DATABASE  IF NOT EXISTS `sql7583670` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sql7583670`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql7.freemysqlhosting.net    Database: sql7583670
-- ------------------------------------------------------
-- Server version	5.5.62-0ubuntu0.14.04.1

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
-- Table structure for table `Media`
--

DROP TABLE IF EXISTS `Media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Media` (
  `idMedia` int(11) NOT NULL AUTO_INCREMENT,
  `mediaName` varchar(45) NOT NULL,
  `mediaCreator` varchar(45) NOT NULL,
  `typeId` int(11) NOT NULL,
  `Sales_pct` double DEFAULT NULL,
  `Price` double NOT NULL,
  `Description` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idMedia`),
  KEY `typeId_idx` (`typeId`),
  CONSTRAINT `typeId` FOREIGN KEY (`typeId`) REFERENCES `Types` (`idTypes`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Media`
--

LOCK TABLES `Media` WRITE;
/*!40000 ALTER TABLE `Media` DISABLE KEYS */;
INSERT INTO `Media` VALUES (1,'Don Quixote','Miguel de Cervantes',2,NULL,0,NULL),(2,'War and Peace','Leo Tolstoy',2,NULL,0,NULL),(3,'Hamlet','Wiliam Shakespeare',2,NULL,0,NULL),(4,'Madame Bovary','Gustave Flaaubert',2,NULL,0,NULL),(5,'Crime and Punishment','Fyodor Dostoyevsky',2,NULL,0,NULL),(6,'Mission Impossible: Selma in Abu Dhabi','Selma Ljuhar',3,NULL,0,NULL),(7,'Pulp Fiction','Quentin Tarantino',3,NULL,0,NULL),(8,'The Dark Knight','Christopher Nolan',3,NULL,0,NULL),(9,'One Piece: Film Red','Eichiro Oda',3,NULL,0,NULL),(10,'Star Wars: Episode IV - A New Hope','George Lucas',3,NULL,0,NULL),(11,'God Of War','SIE Santa Monica Studio',1,NULL,0,NULL),(12,'Red Dead Redemption 2','Rockstar Games',1,NULL,0,NULL),(13,'Kingdom Hearts','Square Enix',1,NULL,0,NULL),(14,'Pokemon','Gamefreak',1,NULL,0,NULL),(15,'Uncharted 2','Naughty Dog',1,NULL,0,NULL);
/*!40000 ALTER TABLE `Media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Types`
--

DROP TABLE IF EXISTS `Types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Types` (
  `idTypes` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(45) NOT NULL,
  PRIMARY KEY (`idTypes`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Types`
--

LOCK TABLES `Types` WRITE;
/*!40000 ALTER TABLE `Types` DISABLE KEYS */;
INSERT INTO `Types` VALUES (1,'Video Games'),(2,'Books'),(3,'Movies');
/*!40000 ALTER TABLE `Types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `idUsers` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `Balance` double NOT NULL DEFAULT '0',
  `PrivilegeLevel` varchar(45) NOT NULL DEFAULT 'User',
  PRIMARY KEY (`idUsers`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'HasimOwO','hasimowo@gmail.com','wizerd02',0,'user'),(2,'EkkoOwO','ajdinsuta8@gmail.com','lolxd358/2',0,'user'),(3,'SelmaLili','sljuhar2@etf.unsa.ba','javolimkerima123',0,'user');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `purchasesId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `mediaId` int(11) NOT NULL,
  `boughtDate` date NOT NULL,
  PRIMARY KEY (`purchasesId`),
  KEY `idUsers_idx` (`userId`),
  KEY `mediaId_idx` (`mediaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-18 23:04:12
