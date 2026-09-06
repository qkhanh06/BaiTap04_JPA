<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kích hoạt tài khoản</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=7">
</head>
<body>
<main class="auth-page">
    <section class="auth-panel">
        <h1>Kích hoạt tài khoản</h1>
        <p>Nhập mã OTP đã được gửi tới email đăng ký.</p>

        <% if (request.getAttribute("success") != null) { %>
            <div class="success-box"><%= request.getAttribute("success") %></div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error-box"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/activate" method="post" class="form-grid">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text"
                       id="username"
                       name="username"
                       value="${username}"
                       required>
            </div>

            <div class="form-group">
                <label for="otp">OTP</label>
                <input type="text" id="otp" name="otp" maxlength="6" required>
            </div>

            <div class="form-actions">
                <button class="btn btn-primary" type="submit">Kích hoạt</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </div>
        </form>
    </section>
</main>
</body>
</html>
