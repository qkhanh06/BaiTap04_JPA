<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
</head>
<body>
<section class="form-hero">
    <span class="eyebrow">JPA Category</span>
    <h1>Thêm danh mục</h1>
    <p>Nhập tên danh mục và chọn ảnh đại diện nếu cần.</p>
</section>

<section class="card editor-card">
    <c:if test="${not empty error}">
        <div class="error-box">${error}</div>
    </c:if>

    <form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
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

            <div class="form-group">
                <label for="images1">Upload ảnh</label>
                <input type="file" id="images1" name="images1" accept="image/*">
            </div>

            <div class="form-group">
                <label>Trạng thái</label>
                <div class="radio-group">
                    <label class="radio-option"><input type="radio" name="status" value="1" checked> Hoạt động</label>
                    <label class="radio-option"><input type="radio" name="status" value="0"> Khóa</label>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <button class="btn btn-primary" type="submit">Lưu danh mục</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/categories">Quay lại</a>
        </div>
    </form>
</section>
</body>
</html>
