CREATE DATABASE IF NOT EXISTS `tplspringdao`
  DEFAULT CHARACTER SET utf8;
USE `tplspringdao`;

#DROP TABLE IF EXISTS `user`;

CREATE TABLE IF NOT EXISTS `user` (
  `ID`        INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login`     VARCHAR(50) DEFAULT NULL,
  `email`     VARCHAR(50) DEFAULT NULL,
  `password`  VARCHAR(50) DEFAULT NULL
)
ENGINE =InnoDB
DEFAULT CHARSET =utf8;

# PUT DATA INTO TABLES
LOCK TABLES `user` WRITE;
INSERT IGNORE INTO `user` VALUES
  (0, 'login0', 'email0', 'password0'),
  (1, 'login1', 'email1', 'password1'),
  (2, 'login2', 'email2', 'password2'),
  (3, 'login3', 'email3', 'password3'),
  (4, 'login4', 'email4', 'password4'),
  (5, 'login5', 'email5', 'password5'),
  (6, 'login6', 'email6', 'password6'),
  (7, 'login7', 'email7', 'password7'),
  (8, 'login8', 'email8', 'password8'),
  (9, 'login9', 'email9', 'password9');
UNLOCK TABLES;
