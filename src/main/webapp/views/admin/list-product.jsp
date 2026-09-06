<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>
</head>
<body>
<section class="catalog-hero">
    <div>
        <span class="eyebrow">JPA Product</span>
        <h1>Danh sách sản phẩm</h1>
        <p>Quản lý sản phẩm, danh mục, giá bán và ảnh upload bằng Multipart.</p>
    </div>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/product/add">Thêm sản phẩm</a>
</section>

<section class="card catalog-card">
    <div class="table-wrap product-table">
        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Danh mục</th>
                    <th>Giá</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var="product" varStatus="stt">
                    <tr>
                        <td>${stt.index + 1}</td>
                        <td>
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
                        </td>
                        <td class="product-name">${product.productName}</td>
                        <td>${product.category.categoryname}</td>
                        <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"/></td>
                        <td>${product.status == 1 ? 'Hoạt động' : 'Khóa'}</td>
                        <td class="actions-cell">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/product/edit?id=${product.productId}">Sửa</a>
                            <a class="btn btn-danger" onclick="return confirm('Xóa sản phẩm này?')" href="${pageContext.request.contextPath}/admin/product/delete?id=${product.productId}">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty products}">
                    <tr>
                        <td colspan="7" class="empty-text">Chưa có sản phẩm nào</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
