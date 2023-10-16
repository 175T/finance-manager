
-- ----------------------------
-- create database finance-manager
-- ----------------------------
CREATE DATABASE IF NOT EXISTS `finance-manager` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

-- ----------------------------
-- create user finance to database
-- ----------------------------
CREATE USER `finance`@`` IDENTIFIED BY 'finance20231016';

GRANT Alter, Alter Routine, Create, Create Routine, Create Temporary Tables, Create View, Delete, Drop, Event, Execute, Grant Option, Index, Insert, Lock Tables, References, Select, Show View, Trigger, Update ON `finance-manager`.* TO `finance`@``;

