<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập thất bại</title>

<style>
    body {
        margin: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
    }

    .container {
        width: 380px;
        padding: 35px;
        background-color: white;
        border-radius: 15px;
        box-shadow: 0 4px 18px rgba(0, 0, 0, 0.15);
        text-align: center;
    }

    h2 {
        margin-top: 0;
        margin-bottom: 20px;
    }

    p {
        margin: 15px 0;
    }

    a {
        display: inline-block;
        margin-top: 15px;
        padding: 10px 25px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 7px;
    }

    a:hover {
        background-color: #0056b3;
    }
</style>

</head>

<body>

<div class="container">

    <h2>ĐĂNG NHẬP THẤT BẠI</h2>

    <p style="color:red;">
        <c:out value="${errorMessage}" />
    </p>

    <a href="${pageContext.request.contextPath}/login">
        Quay lại đăng nhập
    </a>

</div>

</body>
</html>