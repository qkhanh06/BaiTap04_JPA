<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt lại mật khẩu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=7">
</head>
<body>
<main class="auth-page">
    <section class="auth-panel">
        <h1>Đặt lại mật khẩu</h1>
        <p>Nhập OTP trong email và mật khẩu mới.</p>

        <% if (request.getAttribute("success") != null) { %>
            <div class="success-box"><%= request.getAttribute("success") %></div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error-box"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/reset-password" method="post" class="form-grid">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email"
                       id="email"
                       name="email"
                       value="${email}"
                       required>
            </div>

            <div class="form-group">
                <label for="otp">OTP</label>
                <input type="text" id="otp" name="otp" maxlength="6" required>
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu mới</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-actions">
                <button class="btn btn-primary" type="submit">Đổi mật khẩu</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </div>
        </form>
    </section>
</main>
</body>
</html>
