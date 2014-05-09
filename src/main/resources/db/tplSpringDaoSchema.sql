CREATE DATABASE IF NOT EXISTS `tplspringdao`
  DEFAULT CHARACTER SET utf8;
USE `tplspringdao`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `ID`        INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `version`   BIGINT(11) DEFAULT NULL,
  `login`     VARCHAR(50) DEFAULT NULL,
  `email`     VARCHAR(50) DEFAULT NULL,
  `password`  VARCHAR(50) DEFAULT NULL,
  `photoName`  VARCHAR(20000) DEFAULT NULL,
  `createDate`   TIMESTAMP DEFAULT NOW(),
  `deleteDate`  DATETIME DEFAULT NULL
)
ENGINE =InnoDB
DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS `images`;
CREATE TABLE IF NOT EXISTS `images` (
  `ID`        BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `version`   BIGINT(11) DEFAULT NULL,
  `photoPath`  VARCHAR(20000) DEFAULT NULL,
  `createDate`   TIMESTAMP DEFAULT NOW(),
  `deleteDate`  DATETIME DEFAULT NULL
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;