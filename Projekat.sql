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
  `Description` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`idMedia`),
  KEY `typeId_idx` (`typeId`),
  CONSTRAINT `typeId` FOREIGN KEY (`typeId`) REFERENCES `Types` (`idTypes`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Media`
--

LOCK TABLES `Media` WRITE;
/*!40000 ALTER TABLE `Media` DISABLE KEYS */;
INSERT INTO `Media` VALUES (1,'Don Quixote','Miguel de Cervantes',2,0,18.54,'Don Quixote has become so entranced reading tales of chivalry that he decides to turn knight errant himself. In the company of his faithful squire, Sancho Panza, these exploits blossom in all sorts of wonderful ways. While Quixote\'s fancy often leads him astray—he tilts at windmills, imagining them to be giants—Sancho acquires cunning and a certain sagacity. Sane madman and wise fool, they roam the world together-and together they have haunted re'),(2,'War and Peace','Leo Tolstoy',2,0,18.9,'War and Peacebroadly focuses on Napoleon’s invasion of Russia in 1812 and follows three of the most well-known characters in literature: Pierre Bezukhov, the illegitimate son of a count who is fighting for his inheritance and yearning for spiritual fulfillment; Prince Andrei Bolkonsky, who leaves his family behind to fight in the war against Napoleon; and Natasha Rostov, the beautiful young daughter of a nobleman who intrigues both men.'),(3,'Hamlet','Wiliam Shakespeare',2,12.5,6.29,'Hamlet is Shakespeare\'s most popular, and most puzzling, play. It follows the form of a \"revenge tragedy,\" in which the hero, Hamlet, seeks vengeance against his father\'s murderer, his uncle Claudius, now the king of Denmark. Much of its fascination, however, lies in its uncertainties.'),(4,'Madame Bovary','Gustave Flaaubert',2,0,5.95,'Hamlet is Shakespeare\'s most popular, and most puzzling, play. It follows the form of a \"revenge tragedy,\" in which the hero, Hamlet, seeks vengeance against his father\'s murderer, his uncle Claudius, now the king of Denmark. Much of its fascination, however, lies in its uncertainties.'),(5,'Crime and Punishment','Fyodor Dostoyevsky',2,0,5.49,'Hamlet is Shakespeare\'s most popular, and most puzzling, play. It follows the form of a \"revenge tragedy,\" in which the hero, Hamlet, seeks vengeance against his father\'s murderer, his uncle Claudius, now the king of Denmark. Much of its fascination, however, lies in its uncertainties.'),(6,'Mission Impossible: Selma in Abu Dhabi','Selma Ljuhar',3,25,33.29,'Selma goes on an adventure in Abu Dhabi.'),(7,'Pulp Fiction','Quentin Tarantino',3,0,7.79,'Pulp Fiction\'s narrative is told out of chronological order and follows three main interrelated stories that each have a different protagonist: Vincent Vega, a hitman; Butch Coolidge, a prizefighter; and Jules Winnfield, Vincent\'s business partner.\n\nThe film begins with a diner hold-up staged by a couple, then begins to shift from one storyline to another before returning to the diner for the conclusion. There are seven narrative sequences.'),(8,'The Dark Knight','Christopher Nolan',3,0,23.33,'rrrrr'),(9,'One Piece: Film Red','Eichiro Oda',3,0,10.55,'The One Piece is real!'),(10,'Star Wars: Episode IV - A New Hope','George Lucas',3,25,8.99,'Amid a galactic civil war, Rebel Alliance spies have stolen plans to the Galactic Empire\'s Death Star, a massive space station capable of destroying entire planets. Imperial Senator Princess Leia Organa of Alderaan, secretly one of the Rebellion\'s leaders, has obtained its schematics, but her starship is intercepted by an Imperial Star Destroyer under the command of the ruthless Darth Vader. Before she is captured, Leia hides the plans in the mem'),(11,'God Of War','SIE Santa Monica Studio',1,0,60,'God of War is an action-adventure game franchise created by David Jaffe at Sony\'s Santa Monica Studio. It began in 2005 on the PlayStation 2 video game console and has become a flagship series for PlayStation, consisting of nine installments across multiple platforms.'),(12,'Red Dead Redemption 2','Rockstar Games',1,0,60,'Red Dead Redemption is a 2010 action-adventure game developed by Rockstar San Diego and published by Rockstar Games. A spiritual successor to 2004\'s Red Dead Revolver, it is the second game in the Red Dead series.'),(13,'Kingdom Hearts','Square Enix',1,0,60,'Kingdom Hearts is a crossover of various Disney properties based in an original fictional universe. The series centers on the main character, Sora, and his journey and experiences with various Disney and Pixar characters, as well as some from Square Enix properties, such as Final Fantasy, The World Ends With You and Einhänder, in addition to original characters and locations created specifically for the series.'),(14,'Pokemon','Gamefreak',1,12.5,60,'Pokémon is set in the fictional Pokémon universe. There are numerous regions that have appeared in the various media of the Pokémon franchise. There are 9 main series regions set in the main series games: Kanto, Johto, Hoenn, Sinnoh/Hisui, Unova, Kalos, Alola, Galar, and Paldea. Each of the nine generations of the main series releases focuses on a new region.'),(15,'Uncharted 2','Naughty Dog',1,NULL,60,NULL),(24,'Forza Horizon 5','Xbox Game Studios',1,18.75,59.99,'Your Ultimate Horizon Adventure awaits! Explore the vibrant open world landscapes of Mexico with limitless, fun driving action in the world\'s greatest cars.');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Hasim123','hasimowo@gmail.com','wizerd02',206.26,'user'),(2,'EkkoOwO','ajdinsuta8@gmail.com','lolxd358/2',0,'user'),(3,'SelmaLili','sljuhar2@etf.unsa.ba','javolimkerima123',0,'user'),(4,'Deaplox','bilivanili@gmail.com','lolxd123',5,'user'),(5,'NoviKorisnik','nvtest@outlook.com','hasim123',0,'user'),(9,'admin','admin@admin.com','admin',99999,'admin'),(12,'almina','almina@etf.unsa.ba','12345678',0,'user'),(13,'testUser','test@test.com','testic123',10,'user'),(14,'Niko','niko.nikic@gmail.com','hasimowo123',1000,'User');
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (19,1,12,'2023-01-06'),(20,1,1,'2023-01-06'),(21,1,11,'2023-01-06'),(25,1,9,'2023-01-10'),(26,3,6,'2023-01-12'),(28,1,15,'2023-01-18'),(29,1,24,'2023-02-02'),(30,3,12,'2023-02-03'),(31,14,1,'2023-02-03'),(34,3,5,'2023-02-03');
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

-- Dump completed on 2023-02-04 22:52:55
