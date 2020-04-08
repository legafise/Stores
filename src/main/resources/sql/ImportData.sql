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

USE nonamestore;
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
INSERT INTO `countries` VALUES (1,'Belarus'),(2,'Russia'),(3,'Ukraine'),(4,'Poland');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `good_prices`
--

LOCK TABLES `good_prices` WRITE;
/*!40000 ALTER TABLE `good_prices` DISABLE KEYS */;
INSERT INTO `good_prices` VALUES (1,17,160),(2,17,180.5),(3,17,170.2),(4,17,149.99),(1,18,220.78),(2,18,230),(3,18,220.5),(4,18,200),(1,19,500),(2,19,555.55),(3,19,490),(4,19,450),(1,20,1699.9),(2,20,1750.78),(3,20,1700),(4,20,1625.68),(1,21,29.99),(2,21,35),(3,21,25.5),(4,21,22.4);
/*!40000 ALTER TABLE `good_prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (17,'Apple AirPods','Headphones','Plugs for majors'),(18,'Xiaomi Redmi Note 7 4/64GB','Smartphone','Best for its value'),(19,'Sony PlayStation 4 Pro','Game console','Living classic'),(20,'Apple MacBook Pro 13','Laptop','Bullshit on which bloggers mount'),(21,'Xiaomi Smart Scale 2','Libra','Useful thing for home');
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

-- Dump completed on 2020-02-09 21:22:58
