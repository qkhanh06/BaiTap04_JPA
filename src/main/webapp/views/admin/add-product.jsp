<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
</head>
<body>
<section class="form-hero">
    <span class="eyebrow">Multipart Upload</span>
    <h1>Thêm sản phẩm</h1>
    <p>Chọn danh mục, nhập thông tin sản phẩm và tải ảnh đại diện.</p>
</section>

<section class="card editor-card">
    <c:if test="${not empty error}">
        <div class="error-box">${error}</div>
    </c:if>

    <form action="<c:url value='/admin/product/insert'/>" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
        <div class="form-grid">
            <div class="form-group">
                <label for="productName">Tên sản phẩm</label>
                <input type="text" id="productName" name="productName" value="${product.productName}" minlength="2" maxlength="150" required>
                <div class="invalid-feedback">Vui lòng nhập tên sản phẩm từ 2 ký tự.</div>
            </div>

            <div class="form-group">
                <label for="categoryId">Danh mục</label>
                <select id="categoryId" name="categoryId" required>
                    <option value="">Chọn danh mục</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.categoryid}" ${category.categoryid == selectedCategoryId ? 'selected' : ''}>${category.categoryname}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">Vui lòng chọn danh mục.</div>
            </div>

            <div class="form-group">
                <label for="price">Giá</label>
                <input type="number" id="price" name="price" value="${price}" min="1000" step="1000" required>
                <div class="invalid-feedback">Giá phải lớn hơn hoặc bằng 1.000.</div>
            </div>

            <div class="form-group">
                <label for="description">Mô tả</label>
                <textarea id="description" name="description" rows="5" maxlength="1000">${product.description}</textarea>
            </div>

            <div class="form-group">
                <label for="images">Link ảnh</label>
                <input type="text" id="images" name="images" value="${product.images}" maxlength="500">
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
            <button class="btn btn-primary" type="submit">Lưu sản phẩm</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/products">Quay lại</a>
        </div>
    </form>
</section>
</body>
</html>
