<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách danh mục</title>
</head>
<body>
<section class="catalog-hero">
    <div>
        <span class="eyebrow">JPA Category</span>
        <h1>Danh sách danh mục</h1>
        <p>Quản lý danh mục sản phẩm bằng EntityManager và JPQL.</p>
    </div>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/category/add">Thêm danh mục</a>
</section>

<section class="card catalog-card">
    <div class="table-wrap product-table">
        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Ảnh</th>
                    <th>Tên danh mục</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listcate}" var="cate" varStatus="stt">
                    <tr>
                        <td>${stt.index + 1}</td>
                        <td>
                            <c:if test="${not empty cate.images}">
                                <c:choose>
                                    <c:when test="${cate.images.startsWith('http')}">
                                        <c:url value="${cate.images}" var="imgUrl" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/image" var="imgUrl">
                                            <c:param name="fname" value="${cate.images}" />
                                        </c:url>
                                    </c:otherwise>
                                </c:choose>
                                <img src="${imgUrl}" alt="${cate.categoryname}">
                            </c:if>
                        </td>
                        <td class="product-name">${cate.categoryname}</td>
                        <td>${cate.status == 1 ? 'Hoạt động' : 'Khóa'}</td>
                        <td class="actions-cell">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.categoryid}">Sửa</a>
                            <a class="btn btn-danger" onclick="return confirm('Xóa danh mục này?')" href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.categoryid}">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty listcate}">
                    <tr>
                        <td colspan="5" class="empty-text">Chưa có danh mục nào</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
