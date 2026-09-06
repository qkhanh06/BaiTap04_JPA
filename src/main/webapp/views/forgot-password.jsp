<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=7">
</head>
<body>
<main class="auth-page">
    <section class="auth-panel">
        <h1>Quên mật khẩu</h1>
        <p>Nhập email để nhận mã OTP đặt lại mật khẩu.</p>

        <form action="${pageContext.request.contextPath}/forgot-password" method="post" class="form-grid">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-actions">
                <button class="btn btn-primary" type="submit">Gửi OTP</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </div>
        </form>
    </section>
</main>
</body>
</html>
