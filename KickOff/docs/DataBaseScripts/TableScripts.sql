/*******Table Scripts***********/

/***********************************Users Table************************************/



/****** Object:  Table [dbo].[Users]    Script Date: 05/02/2013 13:55:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Users](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](30) NOT NULL,
	[LastName] [varchar](30) NOT NULL,
	[RoleId] [int] NOT NULL,
	[EmployeeId] [varchar](10) NOT NULL,
	[Department] [varchar](50) NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[WorkingPhone1] [varchar](20) NOT NULL,
	[WorkingPhone2] [varchar](20) NULL,
	[Extn1] [varchar](6) NULL,
	[Extn2] [varchar](6) NULL,
	[MobileNumber1] [varchar](20) NOT NULL,
	[MobileNumber2] [varchar](20) NULL,
	[EmailId] [varchar](50) NOT NULL,
	[Status] [bit] NOT NULL,
	[ModifiedTime] [datetime] NOT NULL,
	[IsDeleted] [bit] NOT NULL,
 CONSTRAINT [PK_User1] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_User1_Role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([RoleId])
GO

ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_User1_Role]
GO


/*********************************************************************************************/

/******************************** ROles Table ************************************************/



/****** Object:  Table [dbo].[Roles]    Script Date: 05/02/2013 13:57:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Roles](
	[RoleId] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [varchar](50) NOT NULL,
	[Organization] [varchar](50) NOT NULL,
	[Status] [bit] NOT NULL,
	[IsDeleted] [bit] NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


/************************************************************************************************/

/********************************Role Access permission Table ***********************************/



/****** Object:  Table [dbo].[RoleAccessPermission]    Script Date: 05/02/2013 13:58:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[RoleAccessPermission](
	[PermissionId] [bigint] IDENTITY(1,1) NOT NULL,
	[RoleId] [int] NOT NULL,
	[MenuId] [bigint] NOT NULL,
	[SubMenuId] [bigint] NOT NULL,
	[IsViewEnabled] [bit] NOT NULL,
	[IsCreateEnabled] [bit] NOT NULL,
	[IsEditEnabled] [bit] NOT NULL,
	[IsDeleteEnabled] [bit] NOT NULL,
	[ModifiedDate] [datetime] NOT NULL,
 CONSTRAINT [PK_UserAccessPermission] PRIMARY KEY CLUSTERED 
(
	[PermissionId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[RoleAccessPermission]  WITH CHECK ADD  CONSTRAINT [FK_UserAccessPermission_Role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([RoleId])
GO

ALTER TABLE [dbo].[RoleAccessPermission] CHECK CONSTRAINT [FK_UserAccessPermission_Role]
GO

/************************************************************************************************/

/*********************************Menus Table*****************************************************/



/****** Object:  Table [dbo].[Menus]    Script Date: 05/02/2013 13:59:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Menus](
	[MenuId] [bigint] IDENTITY(1,1) NOT NULL,
	[MenuName] [varchar](50) NOT NULL,
	[MenuDescription] [varchar](150) NULL,
	[ModifiedDate] [datetime] NOT NULL,
	[OrderId] [int] NOT NULL,
 CONSTRAINT [PK_Menus] PRIMARY KEY CLUSTERED 
(
	[MenuId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[Menus] ADD  CONSTRAINT [DF_Menus_ModifiedDate]  DEFAULT (getdate()) FOR [ModifiedDate]
GO

/************************************************************************************************/

/********************************SubMenus Table************************************************/



/****** Object:  Table [dbo].[SubMenus]    Script Date: 05/02/2013 13:59:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[SubMenus](
	[SubMenuId] [bigint] IDENTITY(1,1) NOT NULL,
	[MenuId] [bigint] NOT NULL,
	[SubMenuName] [varchar](50) NOT NULL,
	[SubMenuDescription] [varchar](150) NULL,
	[ModifiedDate] [datetime] NOT NULL,
	[OrderId] [int] NOT NULL,
 CONSTRAINT [PK_SubMenus] PRIMARY KEY CLUSTERED 
(
	[SubMenuId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[SubMenus]  WITH CHECK ADD  CONSTRAINT [FK_SubMenus_Menus] FOREIGN KEY([MenuId])
REFERENCES [dbo].[Menus] ([MenuId])
GO

ALTER TABLE [dbo].[SubMenus] CHECK CONSTRAINT [FK_SubMenus_Menus]
GO

ALTER TABLE [dbo].[SubMenus] ADD  CONSTRAINT [DF_SubMenus_ModifiedDate]  DEFAULT (getdate()) FOR [ModifiedDate]
GO


/************************************************************************************************/





