-- MySQL dump 10.13  Distrib 9.6.0, for Win64 (x86_64)
--
-- Host: localhost    Database: big_homework
-- ------------------------------------------------------
-- Server version	9.6.0

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `club_id` int NOT NULL COMMENT '社团ID',
  `activity_name` varchar(100) NOT NULL COMMENT '活动名称',
  `description` text COMMENT '活动描述',
  `location` varchar(100) DEFAULT NULL COMMENT '活动地点',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `max_people` int DEFAULT NULL COMMENT '最大人数',
  `current_people` int DEFAULT '0' COMMENT '当前人数',
  `publisher_id` int DEFAULT NULL COMMENT '发布人ID',
  `status` int DEFAULT '1' COMMENT '状态：1未开始 2进行中 3已结束',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `club_id` (`club_id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`),
  CONSTRAINT `activity_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,1,'篮球社团新生友谊赛','组织新生篮球交流赛，增强团队凝聚力','体育馆三楼','2025-09-20 14:00:00','2025-09-20 17:00:00',40,12,4,1,'2026-04-24 11:16:59'),(2,2,'书法作品展览','展示社团优秀书法作品，面向全校师生参观','艺术楼大厅','2025-09-21 09:00:00','2025-09-25 17:00:00',100,35,5,1,'2026-04-24 11:16:59'),(3,3,'电竞校内选拔赛','选拔优秀选手代表社团参加市级电竞比赛','实训楼305','2025-09-22 13:00:00','2025-09-22 18:00:00',30,18,6,1,'2026-04-24 11:16:59'),(4,4,'舞蹈迎新表演排练','为迎新晚会准备舞蹈节目，全体成员排练','舞蹈房102','2025-09-19 18:00:00','2025-09-19 21:00:00',25,15,7,1,'2026-04-24 11:16:59'),(5,5,'编程入门公益讲座','面向新生开展编程基础知识讲解','计算机教室401','2025-09-23 10:00:00','2025-09-23 12:00:00',60,22,8,1,'2026-04-24 11:16:59'),(6,6,'吉他弹唱交流会','社团成员现场弹唱，音乐交流分享','综合楼203','2025-09-15 19:00:00','2025-09-15 21:30:00',40,31,9,2,'2026-04-24 11:16:59'),(7,7,'秋季夜跑活动','每周固定夜跑打卡，强身健体','操场跑道','2025-09-14 18:30:00','2025-09-28 21:00:00',80,56,10,2,'2026-04-24 11:16:59'),(8,8,'动漫主题cos活动','校园动漫cosplay展示与交流','社团活动室A','2025-09-16 10:00:00','2025-09-16 17:00:00',50,40,11,2,'2026-04-24 11:16:59'),(9,9,'校园辩论赛','社团内部辩论比拼，提升口才逻辑','教学楼502','2025-09-17 14:00:00','2025-09-17 17:30:00',30,24,12,2,'2026-04-24 11:16:59'),(10,10,'校园风光摄影活动','组织社员拍摄校园秋季风景','校园内指定地点','2025-09-13 08:00:00','2025-09-13 12:00:00',25,19,13,2,'2026-04-24 11:16:59'),(11,11,'羽毛球单打争霸赛','社团内部羽毛球单打比赛','风雨球场','2025-09-01 09:00:00','2025-09-01 12:00:00',35,35,14,3,'2026-04-24 11:16:59'),(12,12,'社区志愿服务活动','前往社区开展敬老、清洁志愿服务','校外社区','2025-09-02 08:30:00','2025-09-02 16:00:00',50,50,15,3,'2026-04-24 11:16:59'),(13,13,'汉服文化节','展示汉服文化、传统礼仪表演','多功能厅','2025-09-03 13:00:00','2025-09-03 17:00:00',60,60,16,3,'2026-04-24 11:16:59'),(14,14,'AI技术分享会','邀请学长分享人工智能学习经验','实验室602','2025-09-04 15:00:00','2025-09-04 17:30:00',40,40,17,3,'2026-04-24 11:16:59'),(15,15,'排球团队友谊赛','社团内部排球对抗赛','体育馆二楼','2025-09-05 14:00:00','2025-09-05 17:00:00',30,30,18,3,'2026-04-24 11:16:59'),(16,16,'手工创意作品大赛','社员手工制作作品评比活动','手工教室','2025-09-06 10:00:00','2025-09-06 15:00:00',25,25,19,3,'2026-04-24 11:16:59'),(17,17,'英语角交流活动','每周英语交流、口语练习','外语教室307','2025-09-07 19:00:00','2025-09-07 21:00:00',30,30,20,3,'2026-04-24 11:16:59'),(18,18,'棋艺争霸赛','象棋围棋对弈比赛','休闲活动室','2025-09-08 13:00:00','2025-09-08 17:00:00',20,20,4,3,'2026-04-24 11:16:59'),(19,19,'足球新生训练课','针对新生开展足球基础训练','足球场','2025-09-09 16:00:00','2025-09-09 18:00:00',45,45,5,3,'2026-04-24 11:16:59'),(20,20,'桌游推理派对','社团成员剧本杀、桌游休闲活动','社团活动室B','2025-09-10 18:00:00','2025-09-10 22:00:00',40,40,6,3,'2026-04-24 11:16:59');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_signup`
--

DROP TABLE IF EXISTS `activity_signup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_signup` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '报名ID',
  `activity_id` int NOT NULL COMMENT '活动ID',
  `user_id` int NOT NULL COMMENT '学生ID',
  `signup_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
  `sign_status` int DEFAULT '0' COMMENT '签到状态：0未签到 1已签到',
  `status` int DEFAULT '1' COMMENT '报名状态：1正常 0取消',
  PRIMARY KEY (`id`),
  KEY `activity_id` (`activity_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `activity_signup_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `activity_signup_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_signup`
--

LOCK TABLES `activity_signup` WRITE;
/*!40000 ALTER TABLE `activity_signup` DISABLE KEYS */;
INSERT INTO `activity_signup` VALUES (1,1,8,'2025-09-10 09:20:00',0,1),(2,1,9,'2025-09-10 10:15:00',0,1),(3,2,10,'2025-09-11 14:30:00',0,1),(4,3,11,'2025-09-12 08:45:00',0,1),(5,4,12,'2025-09-12 16:10:00',0,1),(6,5,13,'2025-09-13 11:25:00',0,1),(7,6,14,'2025-09-14 18:20:00',1,1),(8,6,15,'2025-09-14 18:25:00',1,1),(9,7,16,'2025-09-14 19:00:00',1,1),(10,8,17,'2025-09-15 10:10:00',0,1),(11,9,18,'2025-09-15 14:20:00',1,1),(12,10,19,'2025-09-13 07:50:00',1,1),(13,10,20,'2025-09-13 08:00:00',0,1),(14,11,8,'2025-09-01 08:30:00',1,1),(15,12,9,'2025-09-02 07:50:00',1,1),(16,13,10,'2025-09-03 12:20:00',1,1),(17,14,11,'2025-09-04 14:10:00',0,0),(18,15,12,'2025-09-05 13:30:00',0,0),(19,16,13,'2025-09-06 09:15:00',0,0),(20,17,14,'2025-09-07 18:10:00',0,0);
/*!40000 ALTER TABLE `activity_signup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '社团ID',
  `club_name` varchar(100) NOT NULL COMMENT '社团名称',
  `club_description` text COMMENT '社团简介',
  `club_type` varchar(50) DEFAULT NULL COMMENT '社团类型',
  `leader_id` int DEFAULT NULL COMMENT '负责人ID',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `location` varchar(100) DEFAULT NULL COMMENT '活动地点',
  `member_count` int DEFAULT '0' COMMENT '成员人数',
  `status` int DEFAULT '1' COMMENT '状态：1正常 0停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `leader_id` (`leader_id`),
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`leader_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社团表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'篮球社团','专注篮球训练、校内联赛、日常运动团建','体育竞技',4,'13800138011','体育馆三楼',58,1,'2026-04-24 11:08:37'),(2,'书法协会','传承毛笔书法、硬笔练字、传统文化交流','文艺艺术',5,'13800138012','艺术楼201',32,1,'2026-04-24 11:08:37'),(3,'电竞社团','电子竞技交流、游戏赛事组织、团队对战','兴趣娱乐',6,'13800138013','实训楼305',66,1,'2026-04-24 11:08:37'),(4,'舞蹈社团','现代舞、爵士、民族舞教学与舞台表演','文艺艺术',7,'13800138014','舞蹈房102',45,1,'2026-04-24 11:08:37'),(5,'编程技术社','Java、前端、算法学习，项目实战分享','学术科技',8,'13800138100','计算机教室401',29,1,'2026-04-24 11:08:37'),(6,'吉他音乐社','乐器教学、校园弹唱、音乐分享会','文艺艺术',9,'13800138101','综合楼203',38,1,'2026-04-24 11:08:37'),(7,'跑步健身社','夜跑打卡、体能训练、户外慢跑活动','体育竞技',10,'13800138102','操场跑道',72,1,'2026-04-24 11:08:37'),(8,'动漫二次元社','动漫赏析、cosplay、同人创作交流','兴趣娱乐',11,'13800138103','社团活动室A',41,1,'2026-04-24 11:08:37'),(9,'辩论演讲社','辩论赛举办、口才训练、逻辑思维锻炼','学术科技',12,'13800138104','教学楼502',25,1,'2026-04-24 11:08:37'),(10,'摄影爱好者社','手机摄影、单反教学、校园风光拍摄','兴趣娱乐',13,'13800138105','图文中心',33,1,'2026-04-24 11:08:37'),(11,'羽毛球社团','羽毛球日常训练、双打单打友谊赛','体育竞技',14,'13800138106','风雨球场',52,1,'2026-04-24 11:08:37'),(12,'志愿者服务社','校园志愿、公益活动、社区帮扶行动','公益服务',15,'13800138107','行政楼108',89,1,'2026-04-24 11:08:37'),(13,'古风汉服社','汉服文化、传统礼仪、古风节目演出','传统文化',16,'13800138108','多功能厅',27,1,'2026-04-24 11:08:37'),(14,'人工智能研习社','AI基础学习、机器学习、科技研讨','学术科技',17,'13800138109','实验室602',22,1,'2026-04-24 11:08:37'),(15,'排球社团','排球基础教学、校内对抗赛、团队协作','体育竞技',18,'13800138110','体育馆二楼',44,1,'2026-04-24 11:08:37'),(16,'手工创意社','黏土、编织、手作DIY、创意作品制作','兴趣娱乐',19,'13800138111','手工教室',36,1,'2026-04-24 11:08:37'),(17,'英语口语社','日常口语交流、外文电影赏析、外语角','学术科技',20,'13800138112','外语教室307',30,1,'2026-04-24 11:08:37'),(18,'象棋围棋社','棋类对弈、棋艺切磋、智力竞赛活动','传统文化',4,'13800138011','休闲活动室',18,1,'2026-04-24 11:08:37'),(19,'足球社团','足球训练、校级球赛、户外集体运动','体育竞技',5,'13800138012','足球场',61,0,'2026-04-24 11:08:37'),(20,'桌游推理社','剧本杀、桌游、逻辑推理、休闲聚会','兴趣娱乐',6,'13800138013','社团活动室B',48,0,'2026-04-24 11:08:37');
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_apply`
--

DROP TABLE IF EXISTS `club_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_apply` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '报名ID',
  `user_id` int NOT NULL COMMENT '学生ID',
  `club_id` int NOT NULL COMMENT '社团ID',
  `recruitment_id` int NOT NULL COMMENT '招新ID',
  `apply_reason` text COMMENT '报名理由',
  `personal_strength` text COMMENT '个人优势',
  `status` int DEFAULT '0' COMMENT '状态：0待审核 1通过 2拒绝',
  `apply_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
  `review_time` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `club_id` (`club_id`),
  KEY `recruitment_id` (`recruitment_id`),
  CONSTRAINT `club_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `club_apply_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`),
  CONSTRAINT `club_apply_ibfk_3` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社团报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_apply`
--

LOCK TABLES `club_apply` WRITE;
/*!40000 ALTER TABLE `club_apply` DISABLE KEYS */;
INSERT INTO `club_apply` VALUES (1,8,1,1,'热爱篮球，想提升球技','体能好，有团队意识',0,'2025-09-02 10:15:00',NULL),(2,9,2,2,'喜欢书法，想系统学习','有耐心，写字工整',0,'2025-09-03 09:20:00',NULL),(3,10,3,3,'热爱电竞，想加入战队','游戏操作熟练，沟通能力强',0,'2025-09-03 14:30:00',NULL),(4,11,4,4,'喜欢跳舞，零基础想学习','节奏感好，学习能力强',0,'2025-09-04 08:40:00',NULL),(5,12,5,5,'对编程感兴趣，想提升技术','逻辑思维强，自学能力好',0,'2025-09-04 15:50:00',NULL),(6,13,6,6,'喜欢吉他，想系统学习','乐感好，能坚持练习',0,'2025-09-05 11:25:00',NULL),(7,14,7,7,'喜欢跑步，想加入健身','体能优秀，能坚持打卡',0,'2025-09-05 16:35:00',NULL),(8,15,8,8,'喜欢动漫，想认识同好','性格开朗，擅长交流',0,'2025-09-06 10:05:00',NULL),(9,16,9,9,'喜欢辩论，想锻炼口才','表达能力强，逻辑清晰',1,'2025-09-02 14:10:00','2025-09-03 09:00:00'),(10,17,10,10,'喜欢摄影，想学习技巧','有相机，审美能力好',1,'2025-09-03 11:20:00','2025-09-04 10:00:00'),(11,18,11,11,'喜欢羽毛球，想提升水平','反应快，有运动基础',1,'2025-09-03 16:30:00','2025-09-04 15:00:00'),(12,19,12,12,'想参加志愿活动，奉献爱心','有责任心，时间自由',1,'2025-09-04 09:40:00','2025-09-05 08:30:00'),(13,20,13,13,'喜欢汉服文化，想深入了解','热爱传统文化，遵守规则',1,'2025-09-04 13:50:00','2025-09-05 14:20:00'),(14,8,14,14,'对AI感兴趣，想学习知识','学习能力强，数学基础好',1,'2025-09-05 15:15:00','2025-09-06 11:00:00'),(15,9,15,15,'喜欢排球，想加入训练','团队意识强，体能较好',1,'2025-09-06 10:25:00','2025-09-07 09:30:00'),(16,10,16,16,'喜欢手工，想学习制作','动手能力强，细心认真',2,'2025-08-02 11:00:00','2025-08-10 15:00:00'),(17,11,17,17,'想提升英语口语能力','敢于开口，学习态度好',2,'2025-08-03 14:20:00','2025-08-12 16:30:00'),(18,12,18,18,'喜欢象棋，想切磋棋艺','思维敏捷，喜欢思考',2,'2025-08-04 09:10:00','2025-08-14 10:20:00'),(19,13,19,19,'热爱足球，想加入球队','有运动基础，能吃苦',2,'2025-08-05 16:40:00','2025-08-16 17:10:00'),(20,14,20,20,'喜欢桌游推理，想加入','逻辑思维强，善于沟通',2,'2025-08-06 13:30:00','2025-08-18 14:50:00');
/*!40000 ALTER TABLE `club_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_member`
--

DROP TABLE IF EXISTS `club_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_member` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '成员ID',
  `club_id` int NOT NULL COMMENT '社团ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `member_role` int DEFAULT '1' COMMENT '成员角色：1普通成员 2干部 3社长',
  `join_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `status` int DEFAULT '1' COMMENT '状态：1在团 0退出',
  PRIMARY KEY (`id`),
  KEY `club_id` (`club_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `club_member_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`),
  CONSTRAINT `club_member_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社团成员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_member`
--

LOCK TABLES `club_member` WRITE;
/*!40000 ALTER TABLE `club_member` DISABLE KEYS */;
INSERT INTO `club_member` VALUES (1,1,4,3,'2025-09-01 10:00:00',1),(2,2,5,3,'2025-09-01 10:00:00',1),(3,3,6,3,'2025-09-01 10:00:00',1),(4,4,7,3,'2025-09-01 10:00:00',1),(5,1,8,2,'2025-09-02 11:00:00',1),(6,2,9,2,'2025-09-02 11:00:00',1),(7,3,10,2,'2025-09-02 11:00:00',1),(8,4,11,2,'2025-09-02 11:00:00',1),(9,5,12,2,'2025-09-02 11:00:00',1),(10,1,12,1,'2025-09-03 14:00:00',1),(11,1,13,1,'2025-09-03 14:00:00',1),(12,2,14,1,'2025-09-03 14:00:00',1),(13,2,15,1,'2025-09-03 14:00:00',1),(14,3,16,1,'2025-09-03 14:00:00',1),(15,3,17,1,'2025-09-03 14:00:00',1),(16,4,18,1,'2025-09-03 14:00:00',1),(17,5,19,1,'2025-09-03 14:00:00',1),(18,5,20,1,'2025-09-03 14:00:00',1),(19,6,8,1,'2025-08-10 09:00:00',0),(20,7,9,1,'2025-08-15 10:00:00',0);
/*!40000 ALTER TABLE `club_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `publisher_id` int DEFAULT NULL COMMENT '发布人ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'2025秋季社团招新正式开启','全校各社团已开启秋季统一招新，同学们可自主浏览社团信息、提交报名申请，合理选择心仪社团。',3,'2025-08-28 08:00:00'),(2,'校园活动安全管理通知','所有线下社团活动需提前报备，遵守校园管理规定，注意出行安全与活动秩序。',1,'2025-08-29 09:10:00'),(3,'社团负责人例会召开提醒','定于每周三下午四点召开社团管理员例会，各社团负责人务必准时参加，不得无故缺席。',2,'2025-08-30 10:20:00'),(4,'活动室使用申请须知','社团活动室实行预约制度，使用前需在线提交申请，合理安排使用时间，保持场地整洁。',3,'2025-09-01 11:30:00'),(5,'招新报名截止时间通知','本学期社团招新报名将于9月20日正式截止，逾期不再接受补报名，请同学们及时完成报名。',1,'2025-09-02 14:00:00'),(6,'校级文艺汇演活动征集','现面向全校社团征集文艺节目，优秀节目将入选迎新汇演舞台，有意社团尽快报名报备。',2,'2025-09-03 15:20:00'),(7,'社团月度考核安排','学校将开展9月社团月度考核，考核内容包含活动开展、成员管理、场地规范等相关内容。',3,'2025-09-04 09:40:00'),(8,'户外活动天气预警提示','近期温差较大，户外活动社团需做好防寒保暖，遇恶劣天气及时调整活动计划。',1,'2025-09-05 16:10:00'),(9,'新成员入社审核规则说明','各社团需严格按照审核流程处理报名申请，公平公正审核，及时反馈审核结果给申请人。',2,'2025-09-06 10:00:00'),(10,'社团经费使用规范公告','社团经费仅限合法活动支出，做好收支记录，严禁违规消费，后期将统一抽查台账。',3,'2025-09-07 11:20:00'),(11,'体育类社团赛事报名通知','校内各类体育赛事即将开赛，篮球、羽毛球、足球等社团可组织队员统一报名参赛。',4,'2025-09-08 13:50:00'),(12,'学术科技类活动讲座预告','本周将开展多场编程、人工智能、新媒体技术公益讲座，全体学生可免费参与学习。',5,'2025-09-09 15:30:00'),(13,'社团违规行为处理条例','严禁社团私自组织校外危险活动、违规收费等行为，一经发现将暂停社团运营资格。',3,'2025-09-10 08:20:00'),(14,'优秀社团评选活动启动','2025年度优秀社团评选正式开始，根据活动质量、成员口碑、日常管理综合打分评选。',1,'2025-09-11 09:15:00'),(15,'活动签到功能上线通知','系统新增活动线上签到功能，各活动主办方需规范使用签到管理，统计参与人员信息。',2,'2025-09-12 14:30:00'),(16,'社团资料更新提醒','各社团及时更新社团简介、负责人信息、联系方式等资料，保证后台信息准确无误。',3,'2025-09-13 16:00:00'),(17,'志愿活动时长认定规则','参与社团志愿类活动可累计志愿时长，活动结束后统一提交材料进行时长审核认定。',6,'2025-09-14 10:40:00'),(18,'文艺类社团作品征集通知','书法、舞蹈、音乐、手工等社团可提交优秀作品，参与校园文化展览展示活动。',7,'2025-09-15 11:10:00'),(19,'国庆期间社团活动暂停通知','国庆假期期间，所有线下社团活动统一暂停，线上交流可正常开展，假期结束恢复活动。',1,'2025-09-16 15:00:00'),(20,'系统维护升级公告','本周日凌晨将进行系统维护升级，期间网站暂时无法访问，报名、审核功能临时关闭。',3,'2025-09-17 09:30:00');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruitment`
--

DROP TABLE IF EXISTS `recruitment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruitment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '招新ID',
  `club_id` int NOT NULL COMMENT '社团ID',
  `title` varchar(100) NOT NULL COMMENT '招新标题',
  `content` text COMMENT '招新内容',
  `requirement` text COMMENT '招新要求',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `limit_count` int DEFAULT NULL COMMENT '人数限制',
  `current_count` int DEFAULT '0' COMMENT '当前报名人数',
  `status` int DEFAULT '1' COMMENT '状态：1招新中 0已结束',
  `publisher_id` int DEFAULT NULL COMMENT '发布人ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `club_id` (`club_id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `recruitment_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`),
  CONSTRAINT `recruitment_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='招新表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruitment`
--

LOCK TABLES `recruitment` WRITE;
/*!40000 ALTER TABLE `recruitment` DISABLE KEYS */;
INSERT INTO `recruitment` VALUES (1,1,'2025篮球社团秋季招新','欢迎热爱篮球的同学加入，日常训练+校内联赛','男女不限，有基本运动能力','2025-09-01 08:00:00','2025-09-15 18:00:00',30,12,1,4,'2026-04-24 11:10:21'),(2,2,'书法协会招新啦','零基础可学，每周练字+作品交流','热爱传统文化，态度认真','2025-09-02 09:00:00','2025-09-16 17:00:00',25,8,1,5,'2026-04-24 11:10:21'),(3,3,'电竞社团2025招新','主打英雄联盟、王者荣耀赛事组队','热爱电竞，时间充裕，服从团队安排','2025-09-03 10:00:00','2025-09-17 20:00:00',40,22,1,6,'2026-04-24 11:10:21'),(4,4,'舞蹈社团秋季纳新','现代舞、民族舞、爵士舞训练表演','男女不限，有无基础均可','2025-09-01 14:00:00','2025-09-14 18:00:00',20,15,1,7,'2026-04-24 11:10:21'),(5,5,'编程技术社招新','学习Java、Python、前端开发、算法','对编程感兴趣，愿意学习','2025-09-02 13:00:00','2025-09-20 17:00:00',35,18,1,8,'2026-04-24 11:10:21'),(6,6,'吉他音乐社招新','乐器教学、弹唱表演、音乐交流','喜欢音乐，能坚持练习','2025-09-04 09:00:00','2025-09-18 18:00:00',20,7,1,9,'2026-04-24 11:10:21'),(7,7,'跑步健身社招新','日常夜跑、体能训练、户外打卡','热爱运动，能坚持锻炼','2025-09-01 18:00:00','2025-09-20 21:00:00',50,31,1,10,'2026-04-24 11:10:21'),(8,8,'动漫二次元社招新','动漫交流、cos活动、同人创作','喜欢动漫，性格开朗好相处','2025-09-03 14:00:00','2025-09-17 17:00:00',30,19,1,11,'2026-04-24 11:10:21'),(9,9,'辩论演讲社招新','锻炼口才，参加校级辩论赛','敢于表达，逻辑清晰，积极参与','2025-09-02 10:00:00','2025-09-15 16:00:00',18,9,1,12,'2026-04-24 11:10:21'),(10,10,'摄影爱好者社招新','拍摄校园风光、人像、活动记录','有手机/相机均可，热爱记录','2025-09-05 09:00:00','2025-09-20 18:00:00',25,11,1,13,'2026-04-24 11:10:21'),(11,11,'羽毛球社团招新','日常训练+单打双打比赛','热爱羽毛球，能按时参加活动','2025-09-01 15:00:00','2025-09-14 18:00:00',30,20,1,14,'2026-04-24 11:10:21'),(12,12,'志愿者服务社招新','校园公益、社区服务、志愿活动','有爱心，有责任心，时间自由','2025-09-01 09:00:00','2025-09-30 17:00:00',60,42,1,15,'2026-04-24 11:10:21'),(13,13,'古风汉服社招新','汉服文化、传统礼仪、节目演出','喜欢汉服文化，遵守社团规定','2025-09-03 13:00:00','2025-09-18 17:00:00',22,13,1,16,'2026-04-24 11:10:21'),(14,14,'人工智能研习社招新','AI学习、机器学习、项目实践','对人工智能感兴趣，有学习热情','2025-09-06 10:00:00','2025-09-25 16:00:00',15,6,1,17,'2026-04-24 11:10:21'),(15,15,'排球社团招新','排球基础训练+校内对抗赛','能积极参与训练，团队意识强','2025-09-02 16:00:00','2025-09-16 18:00:00',25,14,1,18,'2026-04-24 11:10:21'),(16,16,'手工创意社招新已结束','黏土、编织、DIY手工制作','动手能力强，认真细心','2025-08-01 09:00:00','2025-08-15 17:00:00',20,20,0,19,'2026-04-24 11:10:21'),(17,17,'英语口语社招新已结束','口语交流、外文电影、外语角活动','敢于开口说英语，积极参与','2025-08-02 10:00:00','2025-08-20 18:00:00',25,25,0,20,'2026-04-24 11:10:21'),(18,18,'象棋围棋社招新已结束','棋艺切磋、智力竞赛、休闲对弈','喜欢象棋或围棋，遵守规则','2025-08-03 14:00:00','2025-08-18 17:00:00',15,15,0,4,'2026-04-24 11:10:21'),(19,19,'足球社团招新已结束','足球训练+校级比赛+团队活动','热爱足球，能吃苦耐劳','2025-08-01 16:00:00','2025-08-15 18:00:00',35,35,0,5,'2026-04-24 11:10:21'),(20,20,'桌游推理社招新已结束','剧本杀、狼人杀、逻辑推理休闲','喜欢推理，性格开朗，乐于社交','2025-08-04 13:00:00','2025-08-22 17:00:00',30,30,0,6,'2026-04-24 11:10:21');
/*!40000 ALTER TABLE `recruitment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `student_no` varchar(20) DEFAULT NULL COMMENT '学号',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `role` int NOT NULL COMMENT '角色：1学生 2社团管理员 3系统管理员',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `status` int DEFAULT '1' COMMENT '状态：1正常 0禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `student_no` (`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'gqs','123456','高群书','24003160412','男','18730807629','3348020057@qq.com',3,'/uploads/admin.jpg',1,'2026-04-17 10:20:12','2026-05-14 08:16:57'),(2,'admin1','123456','系统管理员1',NULL,'男','13800138001','admin1@test.com',3,'/uploads/admin1.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(3,'admin2','123456','系统管理员2',NULL,'女','13800138002','admin2@test.com',3,'/uploads/admin2.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(4,'admin3','123456','系统管理员3',NULL,'男','13800138003','admin3@test.com',3,'/uploads/admin3.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(5,'club_mgr1','123456','张三','2025001','男','13800138011','club1@test.com',2,'/uploads/club1.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(6,'club_mgr2','123456','李四','2025002','女','13800138012','club2@test.com',2,'/uploads/club2.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(7,'club_mgr3','123456','王五','2025003','男','13800138013','club3@test.com',2,'/uploads/club3.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(8,'club_mgr4','123456','赵六','2025004','女','13800138014','club4@test.com',2,'/uploads/club4.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(9,'student1','123456','陈明','2025010','男','13800138100','stu1@test.com',1,'/uploads/stu1.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(10,'student2','123456','刘华','2025011','女','13800138101','stu2@test.com',1,'/uploads/stu2.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(11,'student3','123456','周杰','2025012','男','13800138102','stu3@test.com',1,'/uploads/stu3.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(12,'student4','123456','吴磊','2025013','女','13800138103','stu4@test.com',1,'/uploads/stu4.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(13,'student5','123456','郑晓','2025014','男','13800138104','stu5@test.com',1,'/uploads/stu5.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(14,'student6','123456','王芳','2025015','女','13800138105','stu6@test.com',1,'/uploads/stu6.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(15,'student7','123456','钱明','2025016','男','13800138106','stu7@test.com',1,'/uploads/stu7.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(16,'student8','123456','孙丽','2025017','女','13800138107','stu8@test.com',1,'/uploads/stu8.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(17,'student9','123456','马强','2025018','男','13800138108','stu9@test.com',1,'/uploads/stu9.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(18,'student10','123456','胡静','2025019','女','13800138109','stu10@test.com',1,'/uploads/stu10.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(19,'student11','123456','林辉','2025020','男','13800138110','stu11@test.com',1,'/uploads/stu11.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(20,'student12','123456','高燕','2025021','女','13800138111','stu12@test.com',1,'/uploads/stu12.jpg',1,'2026-04-24 11:05:47','2026-05-14 08:17:59'),(21,'student13','123456','罗浩','2025022','男','13800138112','stu13@test.com',1,'/uploads/stu13.jpg',0,'2026-04-24 11:05:47','2026-05-14 08:17:59');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-19  9:59:30
