<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Category</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=6">
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
            <a class="active" href="${pageContext.request.contextPath}/admin/categories">Categories</a>
            <a href="${pageContext.request.contextPath}/admin/products">Products</a>
            <a href="${pageContext.request.contextPath}/product">Trang bán hàng</a>
        </div>
    </aside>

    <div class="main modern-main">
        <div class="topbar modern-topbar">
            <div class="topbar-user">
                <img src="${pageContext.request.contextPath}/assets/images/avatar.jpg?v=2"
                     class="topbar-avatar"
                     alt="Avatar">
                <div class="welcome">Edit Category: <strong>${category.categoryname}</strong></div>
            </div>
            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">Dang xuat</a>
        </div>

        <main class="content studio-content">
            <section class="form-hero">
                <span class="eyebrow">JPA Category</span>
                <h1>Cap nhat Category</h1>
                <p>Cap nhat category bang EntityManager.merge().</p>
            </section>

            <section class="card editor-card">
                <form action="<c:url value='/admin/category/update'/>"
                      method="post"
                      enctype="multipart/form-data">

                    <input type="hidden" name="categoryid" value="${category.categoryid}">

                    <div class="form-grid editor-grid">
                        <div class="form-group">
                            <label for="categoryname">Category name</label>
                            <input type="text"
                                   id="categoryname"
                                   name="categoryname"
                                   value="${category.categoryname}"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="images">Link images</label>
                            <input type="text"
                                   id="images"
                                   name="images"
                                   value="${category.images}">
                        </div>

                        <c:if test="${not empty category.images}">
                            <div class="form-group">
                                <label>Current image</label>
                                <div class="preview-box product-preview">
                                    <c:choose>
                                        <c:when test="${category.images.startsWith('http')}">
                                            <c:url value="${category.images}" var="imgUrl" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:url value="/image" var="imgUrl">
                                                <c:param name="fname" value="${category.images}" />
                                            </c:url>
                                        </c:otherwise>
                                    </c:choose>
                                    <img src="${imgUrl}" alt="${category.categoryname}">
                                </div>
                            </div>
                        </c:if>

                        <div class="form-group">
                            <label for="images1">Upload images</label>
                            <input type="file"
                                   id="images1"
                                   name="images1"
                                   accept="image/*">
                        </div>

                        <div class="form-group">
                            <label>Status</label>
                            <label>
                                <input type="radio"
                                       name="status"
                                       value="1"
                                       ${category.status == 1 ? 'checked' : ''}>
                                Hoat dong
                            </label>
                            <label>
                                <input type="radio"
                                       name="status"
                                       value="0"
                                       ${category.status != 1 ? 'checked' : ''}>
                                Khoa
                            </label>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Update</button>
                        <a class="btn btn-secondary"
                           href="${pageContext.request.contextPath}/admin/categories">
                            Quay lai
                        </a>
                    </div>
                </form>
            </section>
        </main>
    </div>
</div>

</body>
</html>
