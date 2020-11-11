CREATE DATABASE  IF NOT EXISTS `shoppingdb_vmp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shoppingdb_vmp`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: shoppingdb_vmp
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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `ProductId` int NOT NULL,
  `DiscountPercentage` decimal(10,2) DEFAULT NULL,
  `IsCustomerCheckedOut` bit(1) DEFAULT NULL,
  `QtyOrdered` int NOT NULL,
  `FinalProductPrice` decimal(10,2) NOT NULL,
  `DateCreated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DateModified` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_USER_CART_idx` (`UserId`),
  KEY `FK_PROD_CART_idx` (`ProductId`),
  CONSTRAINT `FK_PROD_CART` FOREIGN KEY (`ProductId`) REFERENCES `products` (`Id`),
  CONSTRAINT `FK_USER_CART` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (12,3,1,0.16,_binary '',2,8.40,'2020-08-22 00:00:00','2020-08-22 20:02:54'),(13,3,2,0.16,_binary '',1,1.95,'2020-08-22 00:00:00','2020-08-22 20:02:54'),(14,3,2,0.16,_binary '',1,1.95,'2020-08-22 00:00:00','2020-08-22 20:02:54'),(15,3,3,0.16,_binary '',1,16.21,'2020-08-22 00:00:00','2020-08-22 20:02:54'),(17,3,6,0.16,_binary '',2,113.70,'2020-08-22 00:00:00','2020-08-22 20:02:54'),(18,2,2,0.16,_binary '\0',2,3.90,'2020-08-22 00:00:00','2020-08-22 20:03:34'),(19,2,4,0.16,_binary '\0',1,10.56,'2020-08-22 00:00:00',NULL),(20,3,1,0.16,_binary '',1,4.20,'2020-08-22 00:00:00','2020-08-22 20:02:54'),(21,3,2,0.16,_binary '',1,1.95,'2020-08-22 00:00:00','2020-08-22 20:02:54'),(22,3,1,0.16,_binary '\0',1,4.20,'2020-08-22 00:00:00',NULL);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `CategoryName` text NOT NULL,
  `DateCreated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DateModified` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Food','2020-08-19 23:39:56',NULL),(2,'Beauty','2020-08-19 23:39:56',NULL),(3,'Kids','2020-08-19 23:39:56',NULL),(4,'Sports','2020-08-19 23:39:56',NULL),(5,'Clothing','2020-08-19 23:39:56',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `ProductName` text NOT NULL,
  `CategoryId` int NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `Description` text NOT NULL,
  `Quantity` int NOT NULL,
  `ProductImagePath` text,
  `DateCreated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DateModified` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_CAT_PROD_idx` (`CategoryId`),
  CONSTRAINT `FK_CAT_PROD` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Pringles BBQ',1,5.00,'Tasty Baked wafers with BBQ flavour',4,NULL,'2020-08-19 23:58:11',NULL),(2,'Ah! Choy! Cookies',1,2.32,'Freshly baked cookies',13,NULL,'2020-08-19 23:58:11',NULL),(3,'Basmati Rice',1,19.30,'Best Rice in world',25,NULL,'2020-08-19 23:58:11',NULL),(4,'Garnier Face Wash',2,12.57,'Figths Acne',52,NULL,'2020-08-19 23:58:11',NULL),(5,'Nivea Body Lotion',2,24.68,'Keeps skin moistured',17,NULL,'2020-08-19 23:58:11',NULL),(6,'Head and Arm Protector',4,67.68,'Protects your arms and head',30,NULL,'2020-08-19 23:58:11',NULL),(7,'Star Wars Yoda',3,24.17,'Special collectible for kids',18,NULL,'2020-08-19 23:58:11',NULL),(8,'Denim Jeans',5,55.10,'Denim Jeans',98,NULL,'2020-08-19 23:58:11',NULL),(9,'Floral Shirt',5,25.00,'Outstanding designed shirt',15,NULL,'2020-08-19 23:58:11',NULL),(10,'French Vanilla',1,2.05,'Winter drink',5,NULL,'2020-08-19 23:58:11',NULL),(11,'Whimsical Wheel',3,12.05,'Toy having sound tracking',5,NULL,'2020-08-19 23:58:11',NULL),(12,'Hot Chocolate',1,3.00,'Winter drink',3,NULL,'2020-08-19 23:58:11',NULL),(13,'BBQ Rice Crackers',1,15.00,'Salty and Baked',10,NULL,'2020-08-19 23:58:11',NULL),(14,'Cheese Macaroni',1,1.50,'Cheesy Macaroniiiii',20,NULL,'2020-08-19 23:58:11',NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `PasswordHash` text NOT NULL,
  `PasswordSalt` text NOT NULL,
  `Role` varchar(45) NOT NULL,
  `DateCreated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DateModified` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','Z/Uj6uw+5hqMTGoZrGYz+slg/lAQoCNfUPz3mggNGKs=','k1sLzfqBeYoMtZaiFMruzNHBTrZ6nF','admin','2020-08-20 00:00:00',NULL),(2,'vatsal','41o3UCiauXUmi0KefoHwaihe/L2TPOa+0DxCXhLNeKU=','UAjXjbvJ9yGdaK7uwiyrUl0vQET0d5','customer','2020-08-20 00:00:00',NULL),(3,'meet','xDNo+9If7sse3wKFtFybWF72/rtMhijuiNTtc4Nkl+k=','4nVqdeNQzzAnyMammkTJQHCLAxJFt6','customer','2020-08-20 00:00:00',NULL),(4,'abc','iHfLezfN5kxV+O71pH8DrrZD42J5FPgTc0wEBzID6aY=','73h3BQLxtLUSadc0JtS4EVOmp6aW4v','customer','2020-08-22 00:00:00',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'shoppingdb_vmp'
--

--
-- Dumping routines for database 'shoppingdb_vmp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-22 20:10:02
