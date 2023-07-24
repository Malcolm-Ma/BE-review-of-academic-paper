-- MySQL dump 10.13  Distrib 8.0.33, for macos13.3 (arm64)
--
-- Host: localhost    Database: apex
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `apex`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `apex` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `apex`;

--
-- Table structure for table `bidding_preference`
--

DROP TABLE IF EXISTS `bidding_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bidding_preference` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `submission_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `preference` tinyint DEFAULT '2',
  `org_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bidding_preference_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3502299 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `org_base`
--

DROP TABLE IF EXISTS `org_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `org_base` (
  `id` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `active_status` int DEFAULT '1',
  `description` text,
  `email` varchar(64) DEFAULT NULL,
  `submission_ddl` datetime DEFAULT NULL,
  `review_process` tinyint DEFAULT '0' COMMENT '0 -> preparing; 1 -> collecting; 2 -> bidding; 3 -> reviewing; 4 -> finished',
  `bidding_ddl` datetime DEFAULT NULL,
  `review_ddl` datetime DEFAULT NULL,
  `blind_mode` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_base_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Organization Basic Info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paper_allocation`
--

DROP TABLE IF EXISTS `paper_allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paper_allocation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `submission_id` varchar(64) NOT NULL,
  `org_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `paper_allocation_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11240 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review_evaluation`
--

DROP TABLE IF EXISTS `review_evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_evaluation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL,
  `review_id` varchar(64) NOT NULL,
  `review_date` datetime DEFAULT NULL,
  `review_index` tinyint DEFAULT NULL,
  `overall_evaluation` smallint DEFAULT NULL,
  `confidence` tinyint DEFAULT NULL,
  `evaluation_content` text,
  `confidence_remark` text,
  `as_short_paper` tinyint DEFAULT '0' COMMENT 'Accept as a short paper: 0->no; 1->yes',
  `active_status` tinyint DEFAULT NULL COMMENT 'status of review: 0->superseded by others; 1->active;',
  `type` tinyint NOT NULL DEFAULT '1' COMMENT 'evaluation type: 0->comment, 1->review',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_review_evaluation_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review_task_overall`
--

DROP TABLE IF EXISTS `review_task_overall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_task_overall` (
  `id` varchar(64) NOT NULL,
  `org_id` varchar(64) NOT NULL COMMENT 'attribution of tasks to the organization.',
  `submission_id` varchar(64) NOT NULL COMMENT 'Paper id which need to be reviewed',
  `status` tinyint NOT NULL DEFAULT '2' COMMENT 'reviewing status: 0-> finished; 1 -> ongoing; 2 -> preparing',
  `deadline` datetime DEFAULT NULL,
  `decision` double DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `blind_mode` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `review_task_overall_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Overall reviewing task';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submission_base`
--

DROP TABLE IF EXISTS `submission_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submission_base` (
  `id` varchar(64) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT 'Paper title',
  `abstracts` text,
  `keywords` varchar(128) DEFAULT NULL,
  `resource_url` varchar(128) DEFAULT NULL,
  `published_time` datetime DEFAULT NULL,
  `authors` varchar(255) DEFAULT NULL,
  `contact_email` varchar(128) DEFAULT NULL,
  `org_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `paper_base_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Basic paper info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submission_user_merge`
--

DROP TABLE IF EXISTS `submission_user_merge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submission_user_merge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `submission_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inter_user_paper_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6461 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_base`
--

DROP TABLE IF EXISTS `user_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_base` (
  `id` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL COMMENT 'Full name',
  `email` varchar(100) NOT NULL,
  `title` varchar(64) NOT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT 'create time',
  `enable_status` int DEFAULT '1' COMMENT 'Account enable status: 0 -> disabled; 1 -> enabled',
  `first_name` varchar(64) DEFAULT NULL COMMENT 'First name',
  `last_name` varchar(64) DEFAULT NULL COMMENT 'Last name',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_base_email_uindex` (`email`),
  UNIQUE KEY `user_base_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_org_merge`
--

DROP TABLE IF EXISTS `user_org_merge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_org_merge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `org_id` varchar(64) DEFAULT NULL,
  `type` int DEFAULT '1' COMMENT 'Relationship type: 0->disabled; 1->member; 2->manager',
  `create_time` datetime DEFAULT NULL,
  `anonymous_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inter_user_org_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6510 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Relationship of user and org';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-24 14:03:36
