CREATE DATABASE IF NOT EXISTS `tplspringdao`
  DEFAULT CHARACTER SET utf8;
USE `tplspringdao`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `images`;
SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `images`;

CREATE TABLE IF NOT EXISTS `users` (
  `ID`        INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `version`   BIGINT(11) DEFAULT NULL,
  `login`     VARCHAR(50) DEFAULT NULL,
  `email`     VARCHAR(50) DEFAULT NULL,
  `password`  VARCHAR(50) DEFAULT NULL,
   enabled TINYINT NOT NULL DEFAULT 1,
   role VARCHAR(45) NOT NULL,
  `avatorPath`  VARCHAR(20000) DEFAULT NULL,
  `createDate`   TIMESTAMP DEFAULT NOW(),
  `deleteDate`  DATETIME DEFAULT NULL,
  `birthDay`  DATETIME DEFAULT NULL
)
ENGINE =InnoDB
DEFAULT CHARSET =utf8;

CREATE TABLE IF NOT EXISTS `images` (
  `ID`        BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `version`   BIGINT(11) DEFAULT NULL,
  `user`  INT(11) NOT NULL,
  `imagePath`  VARCHAR(2000) DEFAULT NULL,
  `image`  VARBINARY(2000) DEFAULT NULL,
  `createDate`   TIMESTAMP DEFAULT NOW(),
  `deleteDate`  DATETIME DEFAULT NULL
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

# ADD FOREIGN KEYS
ALTER TABLE `images` ADD CONSTRAINT `fk_image_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
