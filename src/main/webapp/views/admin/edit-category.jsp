<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật danh mục</title>
</head>
<body>
<section class="form-hero">
    <span class="eyebrow">JPA Category</span>
    <h1>Cập nhật danh mục</h1>
    <p>Điều chỉnh tên, trạng thái và ảnh hiển thị của danh mục.</p>
</section>

<section class="card editor-card">
    <c:if test="${not empty error}">
        <div class="error-box">${error}</div>
    </c:if>

    <form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
        <input type="hidden" name="categoryid" value="${category.categoryid}">

        <div class="form-grid">
            <div class="form-group">
                <label for="categoryname">Tên danh mục</label>
                <input type="text" id="categoryname" name="categoryname" value="${category.categoryname}" minlength="2" maxlength="50" required>
                <div class="invalid-feedback">Vui lòng nhập tên danh mục từ 2 ký tự.</div>
            </div>

            <div class="form-group">
                <label for="images">Link ảnh</label>
                <input type="text" id="images" name="images" value="${category.images}" maxlength="500">
            </div>

            <c:if test="${not empty category.images}">
                <div class="form-group">
                    <label>Ảnh hiện tại</label>
                    <div class="preview-box product-preview">
                        <c:choose>
                            <c:when test="${category.images.startsWith('http')}">
                                <c:url value="${category.images}" var="imgUrl" />
                            </c:when>
                            <c:otherwise>
                                <c:url value="/image" var="imgUrl">
                                    <c:param name="fname" value="${category.images}" />
                                </c:url>
                            </c:otherwise>
                        </c:choose>
                        <img src="${imgUrl}" alt="${category.categoryname}">
                    </div>
                </div>
            </c:if>

            <div class="form-group">
                <label for="images1">Upload ảnh</label>
                <input type="file" id="images1" name="images1" accept="image/*">
            </div>

            <div class="form-group">
                <label>Trạng thái</label>
                <div class="radio-group">
                    <label class="radio-option"><input type="radio" name="status" value="1" ${category.status == 1 ? 'checked' : ''}> Hoạt động</label>
                    <label class="radio-option"><input type="radio" name="status" value="0" ${category.status != 1 ? 'checked' : ''}> Khóa</label>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/categories">Quay lại</a>
        </div>
    </form>
</section>
</body>
</html>
