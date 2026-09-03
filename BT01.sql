IF DB_ID(N'ServletCRUDMVC') IS NULL
BEGIN
    CREATE DATABASE ServletCRUDMVC;
END
GO

USE ServletCRUDMVC;
GO

IF OBJECT_ID(N'dbo.categories', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.categories (
        CategoryId INT IDENTITY(1,1) PRIMARY KEY,
        CategoryName NVARCHAR(50) NOT NULL,
        Images NVARCHAR(500) NULL,
        status INT NOT NULL DEFAULT 1
    );
END
GO

DELETE FROM dbo.categories;
DBCC CHECKIDENT ('dbo.categories', RESEED, 0);
GO

INSERT INTO dbo.categories (CategoryName, Images, status)
VALUES
    (N'iPhone XS', N'category/iphone-xs.jfif', 1),
    (N'iPhone 13', N'category/iphone-13.jfif', 1),
    (N'iPhone 14', N'category/iphone-14.jfif', 1),
    (N'iPhone 15', N'category/iphone-15.jfif', 1),
    (N'iPhone 16', N'category/iphone-16.jfif', 1),
    (N'iPhone 17', N'category/iphone-17.jfif', 1);
GO

SELECT * FROM dbo.categories;
GO
