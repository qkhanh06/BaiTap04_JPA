<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản phẩm</title>
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

    <section class="store-heading">
        <h1>Tất cả sản phẩm</h1>
        <p>Danh sách sản phẩm được sắp xếp theo thời gian tạo mới nhất.</p>
    </section>

    <section class="product-grid">
        <c:forEach items="${products}" var="product">
            <article class="product-card">
                <a href="${pageContext.request.contextPath}/product/detail?id=${product.productId}">
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
                    <span>${product.category.categoryname}</span>
                    <h2>${product.productName}</h2>
                    <strong><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"/></strong>
                </a>
            </article>
        </c:forEach>
    </section>

    <div class="pagination">
        <c:forEach begin="1" end="${totalPages}" var="page">
            <a class="${page == currentPage ? 'active' : ''}"
               href="${pageContext.request.contextPath}/product?page=${page}">
                ${page}
            </a>
        </c:forEach>
    </div>
</main>
</body>
</html>
