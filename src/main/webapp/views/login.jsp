<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Đăng nhập hệ thống</title>

<style>

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    min-height: 100vh;

    display: flex;
    align-items: center;
    justify-content: center;

    padding: 30px;

    font-family: Arial, sans-serif;

    background:
        linear-gradient(
            135deg,
            #0f172a 0%,
            #1e3a8a 55%,
            #2563eb 100%
        );
}

/* ================= LOGIN WRAPPER ================= */

.login-wrapper {
    width: 950px;
    min-height: 560px;

    display: grid;
    grid-template-columns: 1fr 1fr;

    overflow: hidden;

    background: white;

    border-radius: 22px;

    box-shadow:
        0 25px 60px
        rgba(0, 0, 0, 0.25);
}


/* ================= LEFT ================= */

.login-left {
    position: relative;

    display: flex;
    flex-direction: column;
    justify-content: center;

    padding: 55px;

    color: white;

    background:
        linear-gradient(
            150deg,
            #111827,
            #1e3a8a
        );
}

.login-left::before {
    content: "";

    position: absolute;

    width: 260px;
    height: 260px;

    top: -100px;
    right: -100px;

    border-radius: 50%;

    background:
        rgba(255, 255, 255, 0.08);
}

.login-left::after {
    content: "";

    position: absolute;

    width: 180px;
    height: 180px;

    left: -70px;
    bottom: -70px;

    border-radius: 50%;

    background:
        rgba(255, 255, 255, 0.06);
}

.logo-box {
    position: relative;
    z-index: 1;

    width: 70px;
    height: 70px;

    margin-bottom: 26px;

    display: flex;
    align-items: center;
    justify-content: center;

    border-radius: 18px;

    background:
        rgba(255, 255, 255, 0.12);

    font-size: 34px;
}

.login-left h1 {
    position: relative;
    z-index: 1;

    margin-bottom: 15px;

    font-size: 38px;
}

.login-left p {
    position: relative;
    z-index: 1;

    max-width: 360px;

    color: #dbeafe;

    font-size: 16px;
    line-height: 1.7;
}

.feature-list {
    position: relative;
    z-index: 1;

    margin-top: 35px;

    display: grid;
    gap: 15px;
}

.feature-item {
    display: flex;
    align-items: center;

    gap: 12px;

    color: #e5e7eb;
}

.check-icon {
    width: 30px;
    height: 30px;

    display: flex;
    align-items: center;
    justify-content: center;

    border-radius: 9px;

    background:
        rgba(255, 255, 255, 0.12);

    color: #fbbf24;
}


/* ================= RIGHT ================= */

.login-right {
    padding: 55px;

    display: flex;
    flex-direction: column;
    justify-content: center;
}

.login-title {
    margin-bottom: 8px;

    color: #111827;

    font-size: 32px;
    font-weight: bold;
}

.login-subtitle {
    margin-bottom: 30px;

    color: #6b7280;

    font-size: 15px;
}


/* ================= ERROR ================= */

.error-box {
    margin-bottom: 20px;

    padding: 12px 15px;

    border: 1px solid #fecaca;

    border-radius: 10px;

    background: #fef2f2;

    color: #dc2626;

    font-size: 14px;
}


/* ================= FORM ================= */

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;

    margin-bottom: 8px;

    color: #374151;

    font-size: 14px;
    font-weight: bold;
}

.input-box {
    position: relative;
}

.input-box input {
    width: 100%;

    padding: 14px 15px 14px 45px;

    border: 1px solid #d1d5db;

    border-radius: 11px;

    outline: none;

    font-size: 15px;

    transition: 0.2s;
}

.input-box input:focus {
    border-color: #2563eb;

    box-shadow:
        0 0 0 3px
        rgba(37, 99, 235, 0.12);
}

.input-icon {
    position: absolute;

    left: 15px;
    top: 50%;

    transform: translateY(-50%);

    color: #6b7280;

    font-size: 18px;
}


/* ================= REMEMBER ================= */

.remember-row {
    display: flex;
    align-items: center;
    justify-content: space-between;

    margin-bottom: 24px;
}

.remember-box {
    display: flex;
    align-items: center;

    gap: 9px;

    color: #4b5563;

    font-size: 14px;
}

.remember-box input {
    width: 17px;
    height: 17px;

    accent-color: #2563eb;
}


/* ================= BUTTON ================= */

.login-btn {
    width: 100%;

    padding: 14px;

    border: none;

    border-radius: 11px;

    background:
        linear-gradient(
            135deg,
            #2563eb,
            #1e40af
        );

    color: white;

    font-size: 16px;
    font-weight: bold;

    cursor: pointer;

    transition: 0.2s;
}

.login-btn:hover {
    transform: translateY(-2px);

    box-shadow:
        0 8px 20px
        rgba(37, 99, 235, 0.3);
}

.login-note {
    margin-top: 25px;

    text-align: center;

    color: #9ca3af;

    font-size: 13px;
}


/* ================= RESPONSIVE ================= */

@media (max-width: 800px) {

    .login-wrapper {
        width: 100%;

        grid-template-columns: 1fr;
    }

    .login-left {
        display: none;
    }

    .login-right {
        padding: 35px 25px;
    }

}

</style>

</head>


<body>

<div class="login-wrapper">


    <!-- ================= LEFT ================= -->

    <div class="login-left">

        <div class="logo-box">
            🛡️
        </div>

        <h1>
            Welcome!
        </h1>

        <p>
            �ang nh?p d? qu?n l� c?a h�ng, s?n ph?m v� h? so c� nh�n.
        </p>


        <div class="feature-list">

            <div class="feature-item">

                <div class="check-icon">
                    ✓
                </div>

                <span>
                    Quản lý danh mục và sản phẩm
                </span>

            </div>


            <div class="feature-item">

                <div class="check-icon">
                    ✓
                </div>

                <span>
                    C?p nh?t h? so c� nh�n
                </span>

            </div>


            <div class="feature-item">

                <div class="check-icon">
                    ✓
                </div>

                <span>
                    Giao diện quản trị đồng bộ
                </span>

            </div>

        </div>

    </div>


    <!-- ================= RIGHT ================= -->

    <div class="login-right">

        <div class="login-title">
            Đăng nhập
        </div>

        <div class="login-subtitle">
            Chào mừng bạn quay trở lại hệ thống
        </div>


        <!-- ERROR -->

        <% if (request.getAttribute("error") != null) { %>

            <div class="error-box">

                ⚠
                <%= request.getAttribute("error") %>

            </div>

        <% } %>


        <!-- FORM -->

        <form
            action="${pageContext.request.contextPath}/login"
            method="post">


            <!-- USERNAME -->

            <div class="form-group">

                <label for="username">
                    Tên đăng nhập
                </label>

                <div class="input-box">

                    <span class="input-icon">
                        👤
                    </span>

                    <input
                        type="text"
                        id="username"
                        name="username"
                        value="${rememberUsername}"
                        placeholder="Nhập tên đăng nhập"
                        autocomplete="username"
                        required>

                </div>

            </div>


            <!-- PASSWORD -->

            <div class="form-group">

                <label for="password">
                    Mật khẩu
                </label>

                <div class="input-box">

                    <span class="input-icon">
                        🔒
                    </span>

                    <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Nhập mật khẩu"
                        autocomplete="current-password"
                        required>

                </div>

            </div>


            <!-- REMEMBER -->

            <div class="remember-row">

                <label class="remember-box">

                    <input
                        type="checkbox"
                        name="remember"
                        id="remember">

                    <span>
                        Ghi nhớ đăng nhập
                    </span>

                </label>

            </div>

            <div class="remember-row">
                <a href="${pageContext.request.contextPath}/forgot-password">
                    Quên mật khẩu?
                </a>
                <a href="${pageContext.request.contextPath}/register">
                    Đăng ký
                </a>
            </div>


            <!-- BUTTON -->

            <button
                type="submit"
                class="login-btn">

                Đăng nhập

            </button>

        </form>


        <div class="login-note">

            UTEx Store

        </div>

    </div>

</div>

</body>

</html>

