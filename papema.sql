-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: papema
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `userhash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`adminid`),
  UNIQUE KEY `userhash_UNIQUE` (`userhash`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'o','op','ops','1','2',NULL),(2,'sean','naes','mah','boi','@kek',NULL),(3,'0','9','8','7','6',NULL),(4,'a','b','c','d','e',NULL),(5,'adminsean','2444','Sean','Paragas','sean@sean.com',NULL),(6,'adminacc','399f8043559e7ca79cd7a56715547a8f','caleb','pensica','securde@lmao.com','37d331af596c4e42b4fff701d7f7cfbd'),(7,'adminacc','AdminP.1','Jarod','Martinez','jarod@email.com','941f2b38554e4bf19e336575cd8241d2');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cartid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cartid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1),(2),(3),(4),(5),(7),(8),(9),(10),(11),(16),(17),(18),(20),(21),(22);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_items` (
  `cartid` int(11) NOT NULL,
  `citemid` int(11) NOT NULL,
  PRIMARY KEY (`cartid`,`citemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartitem`
--

DROP TABLE IF EXISTS `cartitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartitem` (
  `citemid` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `cartid` int(11) NOT NULL,
  `product_productid` int(11) DEFAULT NULL,
  PRIMARY KEY (`citemid`),
  KEY `FKtc9npvycs1rruynyqdhyrybqw` (`cartid`),
  KEY `FKdn0h3m726afuk0yv9u2ogrs6q` (`product_productid`),
  CONSTRAINT `FKdn0h3m726afuk0yv9u2ogrs6q` FOREIGN KEY (`product_productid`) REFERENCES `product` (`productid`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `FKtc9npvycs1rruynyqdhyrybqw` FOREIGN KEY (`cartid`) REFERENCES `cart` (`cartid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartitem`
--

LOCK TABLES `cartitem` WRITE;
/*!40000 ALTER TABLE `cartitem` DISABLE KEYS */;
INSERT INTO `cartitem` VALUES (1,1,4,NULL),(2,1,5,NULL),(4,1,11,NULL),(5,1,11,NULL),(6,1,11,NULL),(15,1,20,NULL),(16,1,21,NULL);
/*!40000 ALTER TABLE `cartitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `clientid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contactNo` varchar(100) NOT NULL,
  `homeAdd` varchar(255) DEFAULT NULL,
  `userhash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`clientid`),
  UNIQUE KEY `userhash_UNIQUE` (`userhash`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'seanprgs','@2Kboihehe','Sean','Paragas','heyhey@email.com','09179013836','here',NULL),(2,'cripplingDep','f5a961a7d26d00bd60e636671e825b85','hey','no','stop@now.com','234','asifuaif',NULL),(4,'calebca','a8b6bb3b34339f5a6d439a0ef7fc7878','aksdljaslkdj','alksdjaslkdj','aklsjdalksdj@gmail.com','123132','kajfdlkajdlkas','33279f899f66421c8ee2bd601c95c28f'),(5,'qwertyuiop','cefbff00ac887ed468e16e4561534dfd','asufhsdf','asdasdasda','asdasdas@uni.com','12312312312','dlasdunsfyhsf','2c4d159788a14a59ab31692217ec2903'),(6,'calebacc','dfea43cb165fc0edf3faba8165c731b2','Caleb','Pensica','calebpensica@gmail.com','09567213387','1C Diamond Street, Pilar Village, Brgy. Pilar (Near SM Southmall)','39ef66508e974013814ed67b62b12d74');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventorystaff`
--

DROP TABLE IF EXISTS `inventorystaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventorystaff` (
  `staffid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `userhash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`staffid`),
  UNIQUE KEY `userhash_UNIQUE` (`userhash`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventorystaff`
--

LOCK TABLES `inventorystaff` WRITE;
/*!40000 ALTER TABLE `inventorystaff` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventorystaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `source` varchar(255) NOT NULL,
  `log` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'2018-04-12 15:19:49','qwertyuiop','Logged in successfully.'),(2,'2018-04-12 18:32:59','qwertyuiop','Logged in successfully.'),(3,'2018-04-12 19:13:18','qwertyuiop','Logged in successfully.'),(4,'2018-04-12 21:16:38','calebca','Logged in successfully.'),(5,'2018-04-12 21:17:53','adminacc','Logged in successfully.'),(6,'2018-04-12 21:50:09','calebca','Logged in successfully.'),(7,'2018-04-12 21:52:48','calebca','Logged in successfully.'),(8,'2018-04-12 22:04:09','calebca','Logged in successfully.'),(9,'2018-04-12 22:05:40','calebca','Logged in successfully.'),(10,'2018-04-12 22:06:57','calebca','Logged in successfully.'),(11,'2018-04-12 22:08:59','adminacc','Logged in successfully.'),(12,'2018-04-12 22:13:40','calebacc','Logged in successfully.'),(13,'2018-04-12 22:19:54','calebacc','Logged in successfully.'),(14,'2018-04-12 22:29:17','calebacc','Logged in successfully.'),(15,'2018-04-12 22:29:59','adminacc','Logged in successfully.'),(16,'2018-04-12 22:41:54','adminacc','Logged in successfully.'),(17,'2018-04-12 22:45:33','calebacc','Logged in successfully.'),(18,'2018-04-12 22:46:14','adminacc','Logged in successfully.'),(19,'2018-04-12 22:49:39','adminacc','Logged in successfully.'),(20,'2018-04-12 22:53:40','adminacc','Logged in successfully.');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `imagepath` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (4,'Old Product',NULL,'',100);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_tags`
--

DROP TABLE IF EXISTS `product_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_tags` (
  `productid` int(11) NOT NULL,
  `tagid` int(11) NOT NULL,
  PRIMARY KEY (`productid`,`tagid`),
  KEY `FKti6l89ifjqxk92qi6t2vs78c3` (`tagid`),
  CONSTRAINT `FK1b1ldc9ol1t4piayqrbsi0c8p` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`),
  CONSTRAINT `FKti6l89ifjqxk92qi6t2vs78c3` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tags`
--

LOCK TABLES `product_tags` WRITE;
/*!40000 ALTER TABLE `product_tags` DISABLE KEYS */;
INSERT INTO `product_tags` VALUES (4,8);
/*!40000 ALTER TABLE `product_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storemanager`
--

DROP TABLE IF EXISTS `storemanager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storemanager` (
  `managerid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `userhash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`managerid`),
  UNIQUE KEY `userhash_UNIQUE` (`userhash`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storemanager`
--

LOCK TABLES `storemanager` WRITE;
/*!40000 ALTER TABLE `storemanager` DISABLE KEYS */;
/*!40000 ALTER TABLE `storemanager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'boi'),(2,'boi'),(3,'q'),(4,'q'),(5,'po'),(6,'po'),(7,'Nice'),(8,'Nice');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'the newest label');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT,
  `sum` float NOT NULL,
  `deliveryAdd` varchar(255) NOT NULL,
  `timeOrder` varchar(45) DEFAULT NULL,
  `timeReceived` varchar(45) DEFAULT NULL,
  `cart` int(11) DEFAULT NULL,
  `buyer_clientid` int(11) DEFAULT NULL,
  `cart_cartid` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionid`),
  KEY `FKkdchb4tr4t84axfksv4wdh952` (`buyer_clientid`),
  KEY `FKlvn2ts142rqedwdr3mrscjeiw` (`cart_cartid`),
  CONSTRAINT `FKkdchb4tr4t84axfksv4wdh952` FOREIGN KEY (`buyer_clientid`) REFERENCES `client` (`clientid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKlvn2ts142rqedwdr3mrscjeiw` FOREIGN KEY (`cart_cartid`) REFERENCES `cart` (`cartid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (2,4,'sdasd','ah',NULL,NULL,NULL,11),(4,7,'dlasdunsfyhsf','ah',NULL,NULL,5,NULL),(5,4,'dlasdunsfyhsf','ah',NULL,NULL,5,NULL),(9,2,'1C Diamond Street, Pilar Village, Brgy. Pilar (Near SM Southmall)','ah',NULL,NULL,6,20),(10,2,'1C Diamond Street, Pilar Village, Brgy. Pilar (Near SM Southmall)','2018.04.13.06.20.29',NULL,NULL,6,21);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-13  7:03:48
