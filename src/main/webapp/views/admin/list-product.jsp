<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
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
                <div class="welcome">Quản lý <strong>Products</strong></div>
            </div>
            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>

        <main class="content studio-content">
            <section class="catalog-hero">
                <div>
                    <span class="eyebrow">JPA Product</span>
                    <h1>Danh sách Product</h1>
                    <p>Quản lý sản phẩm và ảnh upload bằng Multipart.</p>
                </div>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/product/add">Add Product</a>
            </section>

            <section class="card catalog-card">
                <div class="table-wrap product-table">
                    <table>
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Images</th>
                                <th>Product name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${products}" var="product" varStatus="stt">
                                <tr>
                                    <td>${stt.index + 1}</td>
                                    <td>
                                        <c:if test="${not empty product.images}">
                                            <c:choose>
                                                <c:when test="${product.images.startsWith('http')}">
                                                    <c:url value="${product.images}" var="imgUrl" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:url value="/image" var="imgUrl">
                                                        <c:param name="fname" value="${product.images}" />
                                                    </c:url>
                                                </c:otherwise>
                                            </c:choose>
                                            <img src="${imgUrl}" alt="${product.productName}">
                                        </c:if>
                                    </td>
                                    <td class="product-name">${product.productName}</td>
                                    <td>${product.category.categoryname}</td>
                                    <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"/></td>
                                    <td>${product.status == 1 ? 'Hoạt động' : 'Khóa'}</td>
                                    <td class="actions-cell">
                                        <a class="btn btn-warning"
                                           href="${pageContext.request.contextPath}/admin/product/edit?id=${product.productId}">
                                            Sửa
                                        </a>
                                        <a class="btn btn-danger"
                                           onclick="return confirm('Xóa product này?')"
                                           href="${pageContext.request.contextPath}/admin/product/delete?id=${product.productId}">
                                            Xóa
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty products}">
                                <tr>
                                    <td colspan="7" class="empty-text">Chưa có product nào</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>
</div>
</body>
</html>
