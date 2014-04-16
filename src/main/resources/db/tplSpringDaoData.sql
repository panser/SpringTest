# PUT DATA INTO TABLES
USE `tplspringdao`;
LOCK TABLES `users` WRITE;
INSERT IGNORE INTO `users` VALUES
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