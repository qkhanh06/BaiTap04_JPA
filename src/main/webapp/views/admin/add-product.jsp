<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=7">
</head>
<body>
<div class="admin-wrapper app-shell">
    <aside class="sidebar modern-sidebar">
        <div class="brand brand-stack">
            <span class="brand-mark">UT</span>
            <span>UTEx Store</span>
        </div>

        <div class="admin-box compact-user">
            <img src="${pageContext.request.contextPath}/assets/images/avatar.jpg?v=2"
                 class="avatar-img"
                 alt="Avatar">
            <div class="admin-text">Quản trị cửa hàng</div>
            <strong>${sessionScope.username}</strong>
        </div>

        <div class="menu modern-menu">
            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/profile">Hồ sơ</a>
            <a href="${pageContext.request.contextPath}/admin/categories">Categories</a>
            <a class="active" href="${pageContext.request.contextPath}/admin/products">Products</a>
            <a href="${pageContext.request.contextPath}/product">Trang bán hàng</a>
        </div>
    </aside>

    <div class="main modern-main">
        <div class="topbar modern-topbar">
            <div class="topbar-user">
                <img src="${pageContext.request.contextPath}/assets/images/avatar.jpg?v=2"
                     class="topbar-avatar"
                     alt="Avatar">
                <div class="welcome">Thêm <strong>Product</strong></div>
            </div>
            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>

        <main class="content studio-content">
            <section class="form-hero">
                <span class="eyebrow">Multipart Upload</span>
                <h1>Thêm Product</h1>
                <p>Chọn category, nhập thông tin sản phẩm và tải ảnh đại diện.</p>
            </section>

            <section class="card editor-card">
                <form action="<c:url value='/admin/product/insert'/>"
                      method="post"
                      enctype="multipart/form-data">

                    <div class="form-grid">
                        <div class="form-group">
                            <label for="productName">Product name</label>
                            <input type="text" id="productName" name="productName" required>
                        </div>

                        <div class="form-group">
                            <label for="categoryId">Category</label>
                            <select id="categoryId" name="categoryId" required>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.categoryid}">${category.categoryname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="price">Price</label>
                            <input type="number" id="price" name="price" min="0" step="1000" required>
                        </div>

                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea id="description" name="description" rows="5"></textarea>
                        </div>

                        <div class="form-group">
                            <label for="images">Link images</label>
                            <input type="text" id="images" name="images">
                        </div>

                        <div class="form-group">
                            <label for="images1">Upload images</label>
                            <input type="file" id="images1" name="images1" accept="image/*">
                        </div>

                        <div class="form-group">
                            <label>Status</label>
                            <label><input type="radio" name="status" value="1" checked> Hoạt động</label>
                            <label><input type="radio" name="status" value="0"> Khóa</label>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Insert</button>
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/products">Quay lại</a>
                    </div>
                </form>
            </section>
        </main>
    </div>
</div>
</body>
</html>
