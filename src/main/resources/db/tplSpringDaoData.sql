# PUT DATA INTO TABLES
USE `tplspringdao`;
LOCK TABLES `users` WRITE;
INSERT IGNORE INTO `users` VALUES
  (1, 0, 'login1', 'email1@test.com', 'password1', null,null),
  (2, 0, 'login2', 'email2@test.com', 'password2', null,null),
  (3, 0, 'login3', 'email3@test.com', 'password3', null,null),
  (4, 0, 'login4', 'email4@test.com', 'password4', null,null),
  (5, 0, 'login5', 'email5@test.com', 'password5', null,null),
  (6, 0, 'login6', 'email6@test.com', 'password6', null,null),
  (7, 0, 'login7', 'email7@test.com', 'password7', null,null),
  (8, 0, 'login8', 'email8@test.com', 'password8', null,null),
  (9, 0, 'login9', 'email9@test.com', 'password9', null,null);
UNLOCK TABLES;