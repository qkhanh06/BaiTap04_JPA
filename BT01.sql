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

IF OBJECT_ID(N'dbo.users', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.users (
        Username VARCHAR(50) PRIMARY KEY,
        Password VARCHAR(100) NOT NULL,
        Fullname NVARCHAR(100) NULL,
        Phone VARCHAR(20) NULL,
        Images NVARCHAR(500) NULL,
        Email VARCHAR(120) NULL,
        Active BIT NOT NULL DEFAULT 0,
        OtpCode VARCHAR(10) NULL,
        OtpExpireTime DATETIME2 NULL,
        ResetOtpCode VARCHAR(10) NULL,
        ResetOtpExpireTime DATETIME2 NULL
    );
END
GO

IF COL_LENGTH('dbo.users', 'Email') IS NULL
    ALTER TABLE dbo.users ADD Email VARCHAR(120) NULL;
GO
IF COL_LENGTH('dbo.users', 'Active') IS NULL
    ALTER TABLE dbo.users ADD Active BIT NOT NULL CONSTRAINT DF_users_Active DEFAULT 0;
GO
IF COL_LENGTH('dbo.users', 'OtpCode') IS NULL
    ALTER TABLE dbo.users ADD OtpCode VARCHAR(10) NULL;
GO
IF COL_LENGTH('dbo.users', 'OtpExpireTime') IS NULL
    ALTER TABLE dbo.users ADD OtpExpireTime DATETIME2 NULL;
GO
IF COL_LENGTH('dbo.users', 'ResetOtpCode') IS NULL
    ALTER TABLE dbo.users ADD ResetOtpCode VARCHAR(10) NULL;
GO
IF COL_LENGTH('dbo.users', 'ResetOtpExpireTime') IS NULL
    ALTER TABLE dbo.users ADD ResetOtpExpireTime DATETIME2 NULL;
GO

IF OBJECT_ID(N'dbo.products', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.products (
        ProductId INT IDENTITY(1,1) PRIMARY KEY,
        ProductName NVARCHAR(150) NOT NULL,
        Description NVARCHAR(1000) NULL,
        Price DECIMAL(18,2) NOT NULL DEFAULT 0,
        Images NVARCHAR(500) NULL,
        status INT NOT NULL DEFAULT 1,
        CreatedAt DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
        CategoryId INT NOT NULL,
        CONSTRAINT FK_products_categories
            FOREIGN KEY (CategoryId) REFERENCES dbo.categories(CategoryId)
    );
END
GO

DELETE FROM dbo.products;
DBCC CHECKIDENT ('dbo.products', RESEED, 0);
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

INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
VALUES
    (N'iPhone XS 64GB', N'Màn hình OLED 5.8 inch, thiết kế nhỏ gọn.', 6900000, N'category/iphone-xs.jfif', 1, 1),
    (N'iPhone 13 128GB', N'Chip A15 Bionic, camera kép, pin tốt.', 11900000, N'category/iphone-13.jfif', 1, 2),
    (N'iPhone 14 128GB', N'Hiệu năng ổn định, chống nước và chụp ảnh sắc nét.', 13900000, N'category/iphone-14.jfif', 1, 3),
    (N'iPhone 15 128GB', N'Cổng USB-C, Dynamic Island, camera 48MP.', 17900000, N'category/iphone-15.jfif', 1, 4),
    (N'iPhone 16 128GB', N'Dòng iPhone mới với hiệu năng cao và thiết kế tinh tế.', 21900000, N'category/iphone-16.jfif', 1, 5),
    (N'iPhone 17 256GB', N'Phiên bản dung lượng lớn cho nhu cầu lưu trữ thoải mái.', 26900000, N'category/iphone-17.jfif', 1, 6),
    (N'iPhone 15 Plus 128GB', N'Màn hình lớn, pin bền cho nhu cầu giải trí.', 19900000, N'category/iphone-15.jfif', 1, 4),
    (N'iPhone 14 Pro 256GB', N'Màn hình ProMotion và cụm camera chuyên nghiệp.', 20900000, N'category/iphone-14.jfif', 1, 3),
    (N'iPhone 13 Mini 128GB', N'Thiết kế nhỏ, hiệu năng mạnh trong thân máy gọn.', 9900000, N'category/iphone-13.jfif', 1, 2),
    (N'iPhone XS Max 256GB', N'Màn hình lớn, hoàn thiện cao cấp.', 8500000, N'category/iphone-xs.jfif', 1, 1),
    (N'iPhone 16 Pro 256GB', N'Hiệu năng cao, màn hình đẹp và camera nâng cấp.', 28900000, N'category/iphone-16.jfif', 1, 5),
    (N'iPhone 17 Pro Max 512GB', N'Dòng cao cấp với dung lượng lưu trữ lớn.', 35900000, N'category/iphone-17.jfif', 1, 6);
GO

SELECT * FROM dbo.categories;
SELECT * FROM dbo.products;
GO
