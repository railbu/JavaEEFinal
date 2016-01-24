-- MySQL dump 10.10
--
-- Host: localhost    Database: javaeefinal
-- ------------------------------------------------------
-- Server version	5.0.18-nt

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `Id` varchar(100) NOT NULL default '',
  `Name` varchar(45) NOT NULL default '',
  `CreateTime` varchar(100) NOT NULL default '0000-00-00 00:00:00',
  `Tname` varchar(45) NOT NULL default '',
  `snumber` varchar(45) NOT NULL default '',
  `stucount` int(10) unsigned NOT NULL default '0',
  `pptcount` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `course`
--


/*!40000 ALTER TABLE `course` DISABLE KEYS */;
LOCK TABLES `course` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(45) NOT NULL default '',
  `Courseid` varchar(45) NOT NULL default '',
  `position` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `homework`
--


/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
LOCK TABLES `homework` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;

--
-- Table structure for table `ppt`
--

DROP TABLE IF EXISTS `ppt`;
CREATE TABLE `ppt` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(45) NOT NULL default '',
  `Type` varchar(45) NOT NULL default '',
  `Courseid` varchar(45) NOT NULL default '',
  `position` varchar(200) NOT NULL default '',
  `ClickCount` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `ppt`
--


/*!40000 ALTER TABLE `ppt` DISABLE KEYS */;
LOCK TABLES `ppt` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `ppt` ENABLE KEYS */;

--
-- Table structure for table `sp_teacher`
--

DROP TABLE IF EXISTS `sp_teacher`;
CREATE TABLE `sp_teacher` (
  `id` varchar(255) NOT NULL,
  `createTime` datetime default NULL,
  `name` varchar(255) default NULL,
  `school` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `sp_teacher`
--


/*!40000 ALTER TABLE `sp_teacher` DISABLE KEYS */;
LOCK TABLES `sp_teacher` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sp_teacher` ENABLE KEYS */;

--
-- Table structure for table `sp_user`
--

DROP TABLE IF EXISTS `sp_user`;
CREATE TABLE `sp_user` (
  `id` varchar(255) NOT NULL,
  `age` int(11) default NULL,
  `email` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `sp_user`
--


/*!40000 ALTER TABLE `sp_user` DISABLE KEYS */;
LOCK TABLES `sp_user` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sp_user` ENABLE KEYS */;

--
-- Table structure for table `stucourse`
--

DROP TABLE IF EXISTS `stucourse`;
CREATE TABLE `stucourse` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `sname` varchar(45) NOT NULL default '',
  `cname` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `stucourse`
--


/*!40000 ALTER TABLE `stucourse` DISABLE KEYS */;
LOCK TABLES `stucourse` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `stucourse` ENABLE KEYS */;

--
-- Table structure for table `sysinfo`
--

DROP TABLE IF EXISTS `sysinfo`;
CREATE TABLE `sysinfo` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `CreateTime` varchar(45) NOT NULL default '',
  `Log` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `sysinfo`
--


/*!40000 ALTER TABLE `sysinfo` DISABLE KEYS */;
LOCK TABLES `sysinfo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sysinfo` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(100) NOT NULL default '',
  `username` varchar(45) default NULL,
  `password` varchar(45) default NULL,
  `realname` varchar(45) default NULL,
  `nickname` varchar(45) default NULL,
  `school` varchar(100) default NULL,
  `className` varchar(45) default NULL,
  `email` varchar(100) default NULL,
  `type` varchar(45) default NULL,
  `regTime` varchar(100) default NULL,
  `stuNo` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `user`
--


/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES ('6dd888b6-ee0f-44a6-a538-03f0ecac95f2','123','123',NULL,NULL,'123','123','123','1','2013/11/08 18:12','123'),('aa95072c-daeb-4b1c-ad20-ff015fa497b0','visitor','123',NULL,NULL,NULL,NULL,'123','3','2013/11/09 10:07',NULL),('eb9fd520-7d2b-4607-ba43-4a7f4535f763','admin','123','123',NULL,'123',NULL,'123','2','2013/11/08 18:12',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

