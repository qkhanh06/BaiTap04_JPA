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
    ALTER TABLE dbo.users ADD Active BIT NOT NULL CONSTRAINT DF_users_Active_additive DEFAULT 0;
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

IF NOT EXISTS (SELECT 1 FROM dbo.categories WHERE CategoryName = N'iPhone')
    INSERT INTO dbo.categories (CategoryName, Images, status) VALUES (N'iPhone', N'product/iphone-15.svg', 1);
IF NOT EXISTS (SELECT 1 FROM dbo.categories WHERE CategoryName = N'iPad')
    INSERT INTO dbo.categories (CategoryName, Images, status) VALUES (N'iPad', N'product/ipad-air.svg', 1);
IF NOT EXISTS (SELECT 1 FROM dbo.categories WHERE CategoryName = N'MacBook')
    INSERT INTO dbo.categories (CategoryName, Images, status) VALUES (N'MacBook', N'product/macbook-air.svg', 1);
IF NOT EXISTS (SELECT 1 FROM dbo.categories WHERE CategoryName = N'Phụ kiện')
    INSERT INTO dbo.categories (CategoryName, Images, status) VALUES (N'Phụ kiện', N'product/airpods-pro.svg', 1);
GO

DECLARE @iphone INT = (SELECT TOP 1 CategoryId FROM dbo.categories WHERE CategoryName = N'iPhone');
DECLARE @ipad INT = (SELECT TOP 1 CategoryId FROM dbo.categories WHERE CategoryName = N'iPad');
DECLARE @macbook INT = (SELECT TOP 1 CategoryId FROM dbo.categories WHERE CategoryName = N'MacBook');
DECLARE @accessory INT = (SELECT TOP 1 CategoryId FROM dbo.categories WHERE CategoryName = N'Phụ kiện');

IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'iPhone 15 128GB')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'iPhone 15 128GB', N'Dynamic Island, USB-C, camera 48MP.', 17990000, N'product/iphone-15.svg', 1, @iphone);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'iPhone 15 Pro 256GB')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'iPhone 15 Pro 256GB', N'Khung titan, chip A17 Pro, màn hình ProMotion.', 25990000, N'product/iphone-15-pro.svg', 1, @iphone);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'iPhone 14 128GB')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'iPhone 14 128GB', N'Hiệu năng ổn định, camera kép sắc nét.', 13990000, N'product/iphone-14.svg', 1, @iphone);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'iPhone 13 128GB')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'iPhone 13 128GB', N'Chip A15 Bionic, pin tốt, thiết kế gọn.', 11990000, N'product/iphone-13.svg', 1, @iphone);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'iPad Air M2')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'iPad Air M2', N'Màn hình Liquid Retina, phù hợp học tập và sáng tạo.', 16990000, N'product/ipad-air.svg', 1, @ipad);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'iPad Pro 11 inch')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'iPad Pro 11 inch', N'Màn hình sắc nét, hiệu năng cao cho công việc.', 23990000, N'product/ipad-pro.svg', 1, @ipad);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'MacBook Air M2')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'MacBook Air M2', N'Mỏng nhẹ, pin lâu, phù hợp học tập và văn phòng.', 22990000, N'product/macbook-air.svg', 1, @macbook);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'MacBook Pro 14 inch')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'MacBook Pro 14 inch', N'Màn hình XDR, hiệu năng mạnh cho lập trình và đồ họa.', 42990000, N'product/macbook-pro.svg', 1, @macbook);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'AirPods Pro 2')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'AirPods Pro 2', N'Chống ồn chủ động, âm thanh trong trẻo.', 5490000, N'product/airpods-pro.svg', 1, @accessory);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'Apple Watch Series 9')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'Apple Watch Series 9', N'Theo dõi sức khỏe, luyện tập và thông báo tiện lợi.', 8990000, N'product/apple-watch.svg', 1, @accessory);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'Magic Keyboard')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'Magic Keyboard', N'Bàn phím không dây gọn đẹp cho hệ sinh thái Apple.', 2490000, N'product/magic-keyboard.svg', 1, @accessory);
IF NOT EXISTS (SELECT 1 FROM dbo.products WHERE ProductName = N'Apple Pencil Pro')
    INSERT INTO dbo.products (ProductName, Description, Price, Images, status, CategoryId)
    VALUES (N'Apple Pencil Pro', N'Bút cảm ứng chính xác cho ghi chú và thiết kế.', 3490000, N'product/apple-pencil.svg', 1, @accessory);
GO

SELECT ProductId, ProductName, Images, CategoryId FROM dbo.products ORDER BY ProductId DESC;
GO
