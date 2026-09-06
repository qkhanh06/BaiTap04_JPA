<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Hồ sơ cá nhân | UTEx Store</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=8">
</head>
<body>

<c:set var="avatarUrl" value="${pageContext.request.contextPath}/assets/images/avatar.jpg?v=2" />
<c:if test="${not empty user.images}">
    <c:choose>
        <c:when test="${user.images.startsWith('http')}">
            <c:set var="avatarUrl" value="${user.images}" />
        </c:when>
        <c:otherwise>
            <c:url value="/image" var="avatarUrl">
                <c:param name="fname" value="${user.images}" />
            </c:url>
        </c:otherwise>
    </c:choose>
</c:if>

<div class="admin-wrapper app-shell">
    <aside class="sidebar modern-sidebar">
        <div class="brand brand-stack">
            <span class="brand-mark">UT</span>
            <span>UTEx Store</span>
        </div>

        <div class="admin-box compact-user">
            <img src="${avatarUrl}" class="avatar-img" alt="${user.fullname}">
            <div class="admin-text">Quản trị cửa hàng</div>
            <strong>${user.username}</strong>
        </div>

        <div class="menu modern-menu">
            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
            <a class="active" href="${pageContext.request.contextPath}/profile">Hồ sơ</a>
            <a href="${pageContext.request.contextPath}/admin/categories">Categories</a>
            <a href="${pageContext.request.contextPath}/admin/products">Products</a>
            <a href="${pageContext.request.contextPath}/product">Trang bán hàng</a>
        </div>
    </aside>

    <div class="main modern-main">
        <div class="topbar modern-topbar">
            <div class="topbar-user">
                <img src="${avatarUrl}" class="topbar-avatar" alt="${user.fullname}">
                <div class="welcome">Hồ sơ của <strong>${user.fullname}</strong></div>
            </div>
            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>

        <main class="content studio-content">
            <section class="profile-hero-v2">
                <div class="identity-card">
                    <img src="${avatarUrl}" class="profile-avatar-img" alt="${user.fullname}">
                    <div>
                        <span>User Profile</span>
                        <h1>${empty user.fullname ? user.username : user.fullname}</h1>
                        <p>${user.email}</p>
                    </div>
                </div>

                <div class="profile-summary-card">
                    <span>Thông tin liên hệ</span>
                    <strong>${empty user.phone ? 'Chưa cập nhật' : user.phone}</strong>
                    <p>Tài khoản ${user.active ? 'đang hoạt động' : 'chưa kích hoạt'}.</p>
                </div>
            </section>

            <section class="profile-layout-v2">
                <div class="profile-detail-panel">
                    <div class="panel-heading">
                        <span>Cập nhật hồ sơ</span>
                        <strong>Thông tin cá nhân</strong>
                    </div>

                    <c:if test="${param.success == 1}">
                        <div class="success-box">Cập nhật hồ sơ thành công.</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/profile/update"
                          method="post"
                          enctype="multipart/form-data">

                        <div class="form-grid">
                            <div class="form-group">
                                <label for="fullname">Họ và tên</label>
                                <input type="text"
                                       id="fullname"
                                       name="fullname"
                                       value="${user.fullname}"
                                       required>
                            </div>

                            <div class="form-group">
                                <label for="phone">Số điện thoại</label>
                                <input type="text"
                                       id="phone"
                                       name="phone"
                                       value="${user.phone}"
                                       placeholder="Nhập số điện thoại">
                            </div>

                            <div class="form-group">
                                <label for="images">Ảnh đại diện</label>
                                <input type="file"
                                       id="images"
                                       name="images"
                                       accept="image/*">
                            </div>
                        </div>

                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/home">Về trang chủ</a>
                        </div>
                    </form>
                </div>

                <aside class="access-panel">
                    <div class="panel-heading">
                        <span>Thông tin tài khoản</span>
                        <strong>${user.username}</strong>
                    </div>

                    <div class="detail-grid">
                        <div class="detail-item">
                            <span>Email</span>
                            <strong>${empty user.email ? 'Chưa cập nhật' : user.email}</strong>
                        </div>
                        <div class="detail-item">
                            <span>Số điện thoại</span>
                            <strong>${empty user.phone ? 'Chưa cập nhật' : user.phone}</strong>
                        </div>
                        <div class="detail-item">
                            <span>Trạng thái</span>
                            <strong>${user.active ? 'Hoạt động' : 'Chưa kích hoạt'}</strong>
                        </div>
                        <div class="detail-item">
                            <span>Username</span>
                            <strong>${user.username}</strong>
                        </div>
                    </div>
                </aside>
            </section>
        </main>
    </div>
</div>

</body>
</html>
