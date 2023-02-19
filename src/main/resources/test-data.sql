/*
-- Query: Create user table
-- Date: 2023-02-19 16:37
*/
CREATE TABLE `users` (
  `userid` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `UKmmns67o5v4bfippoqitu4v3t6` (`user_name`)
);
/*
-- Query: Create appointment table
-- Date: 2023-02-19 16:37
*/
CREATE TABLE `appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(255) NOT NULL,
  `end_time` datetime(6) NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
/*
-- Query: Create doctor table
-- Date: 2023-02-19 16:37
*/
CREATE TABLE `doctor` (
  `Id` int NOT NULL,
  `Name` varchar(45) NOT NULL,
  `specialization` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
);
/*
-- Query: SELECT * FROM db_doctor.users
LIMIT 0, 1000

-- Date: 2023-02-19 16:41
*/
INSERT INTO `users` (`userid`,`password`,`role`,`user_name`) VALUES (1,'$2a$10$GgAWs9ydx2mtYxJzy0hq3ew3vU.ORBGME8ITaHNEIHfjR/ams3Cga','DOCTOR','doctor1');
INSERT INTO `users` (`userid`,`password`,`role`,`user_name`) VALUES (2,'$2a$10$JoJ2jzT82d7Y/P5PnSr3zO5SmedFKUaqozGYAv2jyl27f1CL/Chj.','DOCTOR','doctor2');
INSERT INTO `users` (`userid`,`password`,`role`,`user_name`) VALUES (3,'$2a$10$OnYCWRBQGgb5aYt.PnSXB.8kCs1ajQrq38F7zk/sQfzH.ZlDi466m','ASSISTANT','assistant1');

/*
-- Query: SELECT * FROM db_doctor.appointment
-- Date: 2023-02-19 16:37
*/
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (1,'doctor1','2023-02-20 10:16:30.000000','2023-02-20 10:15:30.000000','Follow up Appointment',1,'Mr. John');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (2,'doctor2','2023-02-20 10:17:30.000000','2023-02-20 10:16:30.000000','Fever and Cold',2,'Mrs. Rita');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (3,'doctor3','2023-02-20 11:00:00.000000','2023-02-20 10:30:30.000000','Joint pain',2,'Mr. Raj');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (4,'doctor3','2023-02-20 12:15:00.000000','2023-02-20 12:00:30.000000','Stomach Ache',1,'Mrs. Tina');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (5,'doctor2','2023-02-18 10:00:00.000000','2023-02-18 09:30:00.000000','Neck Pain',1,'Miss Arina');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (6,'doctor3','2023-02-18 10:00:00.000000','2023-02-18 09:30:00.000000','Appointment for fever',0,'Mr. Ivan');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (7,'doctor1','2023-02-18 10:30:00.000000','2023-02-18 10:01:00.000000','Hedache',0,'Mrs. Divya');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (8,'doctor1','2023-02-21 10:00:00.000000','2023-02-21 09:30:00.000000','Eyel Checkup',0,'Mr. Aditya');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (10,'doctor2','2023-02-19 11:30:00.000000','2023-02-19 11:00:00.000000','General Checkup',0,'Miss. Tina');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (11,'doctor3','2023-02-19 11:00:00.000000','2023-02-19 10:30:00.000000','Appointment for cold',2,'Mr. Abhi');
INSERT INTO `appointment` (`id`,`doctor_name`,`end_time`,`start_time`,`title`,`status`,`client_name`) VALUES (12,'doctor1','2023-02-19 11:00:00.000000','2023-02-19 10:30:00.000000','Hedache',0,'Sandy');
/*
-- Query: SELECT * FROM db_doctor.doctor
-- Date: 2023-02-19 16:40
*/
INSERT INTO `doctor` (`Id`,`Name`,`specialization`) VALUES (1,'doctor1','Opthalmalogist');
INSERT INTO `doctor` (`Id`,`Name`,`specialization`) VALUES (2,'doctor2','Cardiologist');
INSERT INTO `doctor` (`Id`,`Name`,`specialization`) VALUES (3,'doctor3','Physician');
