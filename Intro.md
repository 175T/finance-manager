# 财务管理系统说明书
财务管理系统是针对公司, 分公司进行财务收支记录系统.
系统有两种角色, 分别是管理员和普通用户.

## 1. 需求业务流程
#### 1.1 管理员业务流程
- 系统初始化管理员
- 管理员登录, 修改密码等操作
- 管理员添加操作用户 等管理操作
- 管理员添加分公司 等管理操作
- 管理员添加票据类型 等管理操作
- 管理员退出登录

#### 1.2 操作用户业务流程
- 用户登录, 修改密码等操作
- 用户添加财务订单 等管理操作
- 用户退出登录

## 2. 系统模块
### 2.1 用户模块
用户模块包含了用户基本信息, 如登录名, 密码, 角色.

#### 管理员功能设计
- 添加用户
- 删除用户
- 分公司管理
- 票据管理

#### 用户功能设计
- 用户登录
- 用户注销
- 修改密码
- 财务订单管理

### 2.2 财务模块
#### 财务订单模块
- 财务订单添加
- 财务订单更新
- 财务订单删除
- 财务订单分页查询
- 财务订单导出 Excel 文件
- 财务订单导入 Excel 文件

#### 财务票据模块
- 财务票据类型新增
- 财务票据类型删除

### 2.3 分公司模块
- 分公司添加
- 分公司更新
- 分公司删除

## 3. 数据库设计
数据库采用 MySQL数据库, MySQL 版本要求在6.0以上
### 3.1 用户表
详见: userlist
```agsl
CREATE TABLE `userlist` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员用户ID',
  `UserName` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称 (也为登录ID)',
  `Password` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码',
  `Content` varchar(500) NOT NULL DEFAULT '' COMMENT '用户描述, 备注信息',
  `ISSuperAdmin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否是超级管理员?',
  `ISValid` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效?',
  `CreateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '创建时间',
  `UpdateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
)
```
### 3.2 用户登录历史表
财务管理系统需要记录管理员和操作用户的登录历史, 登录成功, 生成一条 LoginToken, 每次前段后会话需要带上此token进行登录合法验证.
```agsl
CREATE TABLE `userlist_login_history` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '登录历史ID',
  `UserId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `LoginIp` varchar(100) NOT NULL DEFAULT '' COMMENT '用户登录时IP',
  `LoginToken` varchar(100) NOT NULL DEFAULT '' COMMENT '用户登录令牌',
  `ExpireTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '令牌过期时间',
  `ISValid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效?',
  `CreateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '创建时间',
  `UpdateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Index_LoginToken` (`LoginToken`) USING BTREE,
  KEY `Index_UserId` (`UserId`) USING BTREE
)
```
### 3.3 分公司表
```agsl
CREATE TABLE `subjectlist` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '分公司ID',
  `School` varchar(50) NOT NULL DEFAULT '' COMMENT '分公司名字',
  `Name` varchar(100) NOT NULL DEFAULT '' COMMENT '项目名称',
  `ISValid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `CreateUserId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建用户ID',
  `CreateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '创建时间',
  `UpdateUserId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '更新用户ID',
  `UpdateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) 
```

### 3.4 财务票据表
```agsl
CREATE TABLE `tickettype` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '票据类型ID',
  `Name` varchar(100) NOT NULL DEFAULT '' COMMENT '票据类型名称',
  `ISValid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效?',
  `CreateUserId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建用户ID',
  `CreateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '创建时间',
  `UpdateUserId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '更新用户ID',
  `UpdateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
)
```

### 3.5 财务订单表
```agsl
CREATE TABLE `orderlist` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `OrderAccount` varchar(50) NOT NULL DEFAULT '' COMMENT '缴费/收款人',
  `SubjectId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '项 目, 对应subjectlist表ID',
  `TicketTypeId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '票 据, 对应tickettypelist表ID',
  `TicketNo` varchar(50) NOT NULL DEFAULT '' COMMENT '票据号',
  `OrderPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '金 额',
  `PayType` varchar(50) NOT NULL DEFAULT '' COMMENT '收支方式',
  `OrderTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '业务订单时间',
  `OrderDes` varchar(500) NOT NULL DEFAULT '' COMMENT '备 注',
  `ISValid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `CreateUserId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建用户ID',
  `CreateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '创建时间',
  `UpdateUserId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '更新用户ID',
  `UpdateTime` datetime NOT NULL DEFAULT '2020-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) 
```

## 4. 技术方案
财务管理系统采用 BS 架构, 
技术栈为: java, Jsp, MySQL, springboot, maven, nginx, git, docker, k8s

### 4.1 java
java jdk 11

### 4.2 Jsp
前端页面使用 Jsp 技术. 通过springboot框架业务处理后, 由Jsp进行数据填充页面.

### 4.3 MySQL
MySQL 使用 8版本

### 4.4 springboot
springboot 为2.0.6版本, 同时集成 MyBatis来简化数据库 JDBC 操作.

### 4.5 maven
maven 提供统一的打包方式, 最终财务管理系统打包为 jar 包.

### 4.6 nginx
nginx 为反向代理服务器, 当nginx收到 www.myrealhost.com 线上请求时, 把请求全部转发到财务管理系统.

### 4.7 git 
提供版本管理

### 4.8 docker
java 打包 docker 镜像, 便于在容器平台中运行.

### 4.9 k8s
k8s 容器平台部署
