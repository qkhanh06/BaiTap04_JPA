<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.productName}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=7">
</head>
<body>
<main class="store-page">
    <nav class="store-nav">
        <a class="brand-link" href="${pageContext.request.contextPath}/home">UTEx Store</a>
        <div>
            <a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
            <c:choose>
                <c:when test="${not empty sessionScope.username}">
                    <a href="${pageContext.request.contextPath}/admin/products">Quản trị</a>
                    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>

    <section class="detail-layout">
        <div class="detail-media">
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
        </div>

        <div class="detail-info">
            <span>${product.category.categoryname}</span>
            <h1>${product.productName}</h1>
            <strong><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"/></strong>
            <p>${product.description}</p>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/product">Xem thêm sản phẩm</a>
        </div>
    </section>
</main>
</body>
</html>
