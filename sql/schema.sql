CREATE DATABASE  IF NOT EXISTS `pay_my_buddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pay_my_buddy`;
-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: pay_my_buddy
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7m8ru44m93ukyb61dfxw0apf6` (`user_id`),
  CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `id` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `friend_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqhlqyd2eb3nmk9hvrfqslw918` (`friend_id`),
  KEY `FKivlhvh7odettdl818ml67apb9` (`user_id`),
  CONSTRAINT `FKivlhvh7odettdl818ml67apb9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqhlqyd2eb3nmk9hvrfqslw918` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `series` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `last_used` datetime(6) NOT NULL,
  `token` varchar(64) NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `charge` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `transacted` char(1) DEFAULT NULL,
  `from_account_id` int DEFAULT NULL,
  `to_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrff4jlxetafju1e5cks5mfcnk` (`from_account_id`),
  KEY `FKluqt8k2pa8d4gmggx4rhl5vgv` (`to_account_id`),
  CONSTRAINT `FKluqt8k2pa8d4gmggx4rhl5vgv` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKrff4jlxetafju1e5cks5mfcnk` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-21 14:25:47
