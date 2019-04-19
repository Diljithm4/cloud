/*
SQLyog Community Edition- MySQL GUI v8.03 
MySQL - 5.1.32-community : Database - cloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloud` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cloud`;

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(25) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `phone` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`id`,`name`,`password`,`email`,`phone`) values (1,'hdhd','11','hdhd@gm','11'),(2,'ff','11','fff@gmail.com','11');

/*Table structure for table `offload` */

DROP TABLE IF EXISTS `offload`;

CREATE TABLE `offload` (
  `id` varchar(30) NOT NULL,
  `uid` varchar(30) DEFAULT NULL,
  `filename` varchar(30) DEFAULT NULL,
  `fsize` varchar(30) DEFAULT NULL,
  `ftype` varchar(30) DEFAULT NULL,
  `conbtry` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `offload` */

/*Table structure for table `storage` */

DROP TABLE IF EXISTS `storage`;

CREATE TABLE `storage` (
  `file_name` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `id` int(25) DEFAULT NULL,
  `upload_id` int(25) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `storage` */

insert  into `storage`(`file_name`,`date`,`id`,`upload_id`) values ('FB_IMG_1553609140324.jpg','2019-04-02',2,9),('VID-20190407-WA0059.mp4','2019-04-08',2,10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
