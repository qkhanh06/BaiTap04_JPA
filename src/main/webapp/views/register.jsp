<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=7">
</head>
<body>
<main class="auth-page">
    <section class="auth-panel">
        <h1>Đăng ký tài khoản</h1>
        <p>Tạo tài khoản và nhận mã OTP qua email.</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error-box"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/register" method="post" class="form-grid">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="fullname">Họ tên</label>
                <input type="text" id="fullname" name="fullname" required>
            </div>

            <div class="form-group">
                <label for="phone">Số điện thoại</label>
                <input type="text" id="phone" name="phone">
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-actions">
                <button class="btn btn-primary" type="submit">Đăng ký</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </div>
        </form>
    </section>
</main>
</body>
</html>
