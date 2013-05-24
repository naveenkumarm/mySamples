/*******************************ADMIN_ROLE_MANAGEMENT*********************************************/


/****** Object:  StoredProcedure [dbo].[ADMIN_ROLE_MANAGEMENT]    Script Date: 05/02/2013 14:01:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 
CREATE PROCEDURE [dbo].[ADMIN_ROLE_MANAGEMENT] (@StartRecord INT,@RecordsToShow INT,@roleNameKey VARCHAR(50))
	 
AS
BEGIN

DECLARE @TotalRows INT;


SET @TotalRows = (SELECT COUNT(RoleId) FROM Roles
WHERE 
roleName LIKE CASE WHEN @roleNameKey!='' THEN '%'+@roleNameKey+'%' ELSE roleName END 
AND
isDeleted='false')


SELECT Row,RoleId,RoleName,Organization,Status,@TotalRows as TotalRows
FROM 
(SELECT ROW_NUMBER() OVER (ORDER BY RoleId DESC) AS Row,rs.RoleId,rs.RoleName,rs.Organization,rs.Status
FROM 
Roles rs  
WHERE 
rs.roleName LIKE CASE WHEN @roleNameKey!='' THEN '%'+@roleNameKey+'%' ELSE rs.roleName END 
AND 
isDeleted='false') AS UsersRows
WHERE (Row between (@StartRecord) AND @StartRecord + @RecordsToShow - 1)
END
/***********************************************************************************************/

/************************************ADMIN_USERS**************************************************/

/****** Object:  StoredProcedure [dbo].[ADMIN_USERS]    Script Date: 05/02/2013 14:02:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 CREATE PROCEDURE [dbo].[ADMIN_USERS] (@StartRecord INT, @RecordsToShow INT,@userNameKey VARCHAR(50))
	 
AS
BEGIN

DECLARE @TotalRows INT;


SET @TotalRows = (SELECT COUNT(UserId) FROM Users 
WHERE  
userName LIKE CASE WHEN @userNameKey!='' THEN '%'+@userNameKey+'%' ELSE userName END 
AND
isDeleted='false')


SELECT Row,userId,UserName,FirstName,LastName,@TotalRows as TotalRows,department
FROM 
(SELECT ROW_NUMBER() OVER (ORDER BY ModifiedTime DESC) AS Row,us.userId,us.UserName,us.FirstName,us.LastName,us.department
FROM 

Users us 
WHERE 
us.userName LIKE CASE WHEN @userNameKey!='' THEN '%'+@userNameKey+'%' ELSE us.userName END 
AND  
isDeleted='false') AS UsersRows
WHERE (Row between (@StartRecord) AND @StartRecord + @RecordsToShow - 1)
END

/***********************************************************************************************/


