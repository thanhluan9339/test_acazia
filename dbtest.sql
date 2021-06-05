/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : SQL Server
 Source Server Version : 15002080
 Source Host           : localhost:1433
 Source Catalog        : testacazia
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002080
 File Encoding         : 65001

 Date: 05/06/2021 09:15:07
*/


-- ----------------------------
-- Table structure for category
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[category]') AND type IN ('U'))
	DROP TABLE [dbo].[category]
GO

CREATE TABLE [dbo].[category] (
  [tag] varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [name] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[category] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO [dbo].[category] ([tag], [name]) VALUES (N'dl', N'dien lanh')
GO

INSERT INTO [dbo].[category] ([tag], [name]) VALUES (N'mt', N'may tinh')
GO


-- ----------------------------
-- Table structure for product
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[product]') AND type IN ('U'))
	DROP TABLE [dbo].[product]
GO

CREATE TABLE [dbo].[product] (
  [name] varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [category_tag] varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [price] float(53)  NOT NULL
)
GO

ALTER TABLE [dbo].[product] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO [dbo].[product] ([name], [category_tag], [price]) VALUES (N'dieu hoa nhiet do b', N'dl', N'12')
GO

INSERT INTO [dbo].[product] ([name], [category_tag], [price]) VALUES (N'laptop X', N'mt', N'13')
GO

INSERT INTO [dbo].[product] ([name], [category_tag], [price]) VALUES (N'may tinh xach tay A', N'mt', N'12')
GO

INSERT INTO [dbo].[product] ([name], [category_tag], [price]) VALUES (N'pc 2', N'mt', N'13')
GO

INSERT INTO [dbo].[product] ([name], [category_tag], [price]) VALUES (N'tu lanh e', N'dl', N'11.5')
GO

INSERT INTO [dbo].[product] ([name], [category_tag], [price]) VALUES (N'ultrabook EZ', N'mt', N'16')
GO


-- ----------------------------
-- Primary Key structure for table category
-- ----------------------------
ALTER TABLE [dbo].[category] ADD CONSTRAINT [PK__category__3213E83F9C9162EF] PRIMARY KEY CLUSTERED ([tag])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table product
-- ----------------------------
ALTER TABLE [dbo].[product] ADD CONSTRAINT [PK__product__3213E83FBCC88806] PRIMARY KEY CLUSTERED ([name])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

