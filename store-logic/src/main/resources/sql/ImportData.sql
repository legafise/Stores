CREATE DATABASE  IF NOT EXISTS `nonamestore` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `nonamestore`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: nonamestore
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
-- Dumping data for table `baskets`
--

LOCK TABLES `baskets` WRITE;
/*!40000 ALTER TABLE `baskets` DISABLE KEYS */;
INSERT INTO `baskets` VALUES (17,1,1),(17,3,1),(17,5,3),(17,6,1),(18,3,1),(18,4,3),(18,6,1),(19,1,1),(19,2,1),(19,3,1),(20,1,1),(20,3,2),(20,5,2),(21,2,2),(21,4,4);
/*!40000 ALTER TABLE `baskets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Minsk',1),(2,'Gomel',1),(3,'Mogilev',1),(4,'Vitebsk',1),(5,'Moscow',2),(6,'Petersburg',2),(7,'Smolensk',2),(8,'Voronezh',2),(9,'Kiev',3),(10,'Dnieper',3),(11,'Odessa',3),(12,'Uman',3),(13,'Warsaw',4),(14,'Krakow',4),(15,'Gdansk',4),(16,'Poznan',4);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'Belarus',2),(2,'Russia',3),(3,'Ukraine',5),(4,'Poland',4),(5,'USA',1);
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (1,'USD',1,'$'),(2,'BYN',2.6,'br'),(3,'RUB',76.8,'₽'),(4,'PLN',3.91,'zł'),(5,'UAH',28.31,'₴');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (17,'Apple AirPods',146.21,'Headphones','Plugs for majors','https://www.freepngimg.com/thumb/apple/94073-hardware-airpods-tap-apple-headphones-free-transparent-image-hd.png'),(18,'Xiaomi Redmi Note 7 4/64GB',190.37,'Smartphone','Best for its value','https://gbvisionsolutions.com/wp-content/uploads/2019/06/232-abf7d1-1000x1000.jpeg'),(19,'Sony PlayStation 4 Pro',437.86,'Game console','Living classic','https://tehnot.com/wp-content/uploads/2017/01/340949-sony-playstation-4.jpg'),(20,'Apple MacBook Pro 13',1047.31,'Laptop','Bullshit on which bloggers mount','https://i2.wp.com/www.tek4.me/wp-content/uploads/2012/10/13MBPR_34_Canyon_WEB.png'),(21,'Xiaomi Smart Scale 2',25,'Libra','Useful thing for home','https://images.by.prom.st/151482275_w640_h640_umnye-vesy-xiaomi.jpg');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Roma','Lashkevich','legafise','12340987','lash@ya.ru','2004-02-12',1,1),(2,'Ivan','Yarochkin','Yaran','AlinaTheBest','yar@gmail.com','1997-07-08',1,5),(3,'Alina','Lashkevich','Alinus','VanyaTheBest','allash@ya.by','1997-12-20',2,11),(4,'Maxim','Rimashevskiy','Mr.Makentosh','Salo','MaxRim@mail.ru','2003-10-24',2,14),(5,'Ruslan','Lashkevich','Rusel','qwertasdfg','lashkevich1974@gmail.com','1974-04-09',2,9),(6,'Evgeniy','Lanin','ELanin','elan123409qwer','elanin04@mail.ru','2004-09-17',2,4);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-30 16:39:32
