<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>H? so c� nh�n | UTEx Store</title>
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

<section class="profile-hero-v2">
    <div class="identity-card">
        <img src="${avatarUrl}" class="profile-avatar-img" alt="${user.fullname}">
        <div>
            <span>User Profile</span>
            <h1>${empty user.fullname ? user.username : user.fullname}</h1>
            <p>${empty user.email ? 'Chưa cập nhật email' : user.email}</p>
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
            <strong>Th�ng tin c� nh�n</strong>
        </div>

        <c:if test="${not empty error}">
            <div class="error-box">${error}</div>
        </c:if>

        <c:if test="${param.success == 1}">
            <div class="success-box">Cập nhật hồ sơ thành công.</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/profile/update"
              method="post"
              enctype="multipart/form-data"
              class="needs-validation"
              novalidate>

            <div class="form-grid">
                <div class="form-group">
                    <label for="fullname">Họ và tên</label>
                    <input type="text"
                           id="fullname"
                           name="fullname"
                           value="${user.fullname}"
                           minlength="2"
                           maxlength="100"
                           required>
                    <div class="invalid-feedback">Vui lòng nhập họ tên từ 2 ký tự.</div>
                </div>

                <div class="form-group">
                    <label for="phone">Số điện thoại</label>
                    <input type="text"
                           id="phone"
                           name="phone"
                           value="${user.phone}"
                           pattern="0[0-9]{9}"
                           placeholder="Nhập số điện thoại">
                    <div class="invalid-feedback">Số điện thoại phải gồm 10 số và bắt đầu bằng 0.</div>
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

</body>
</html>

