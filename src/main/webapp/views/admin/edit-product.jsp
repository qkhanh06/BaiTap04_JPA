<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật sản phẩm</title>
</head>
<body>
<section class="form-hero">
    <span class="eyebrow">JPA Product</span>
    <h1>Cập nhật sản phẩm</h1>
    <p>Điều chỉnh thông tin, danh mục và ảnh hiển thị của sản phẩm.</p>
</section>

<section class="card editor-card">
    <c:if test="${not empty error}">
        <div class="error-box">${error}</div>
    </c:if>

    <form action="<c:url value='/admin/product/update'/>" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
        <input type="hidden" name="productId" value="${product.productId}">

        <div class="form-grid">
            <div class="form-group">
                <label for="productName">Tên sản phẩm</label>
                <input type="text" id="productName" name="productName" value="${product.productName}" minlength="2" maxlength="150" required>
                <div class="invalid-feedback">Vui lòng nhập tên sản phẩm từ 2 ký tự.</div>
            </div>

            <div class="form-group">
                <label for="categoryId">Danh mục</label>
                <select id="categoryId" name="categoryId" required>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.categoryid}" ${product.category.categoryid == category.categoryid ? 'selected' : ''}>${category.categoryname}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">Vui lòng chọn danh mục.</div>
            </div>

            <div class="form-group">
                <label for="price">Giá</label>
                <input type="number" id="price" name="price" value="${product.price}" min="1000" step="1000" required>
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

            <c:if test="${not empty product.images}">
                <div class="form-group">
                    <label>Ảnh hiện tại</label>
                    <div class="preview-box product-preview">
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
                    <label class="radio-option"><input type="radio" name="status" value="1" ${product.status == 1 ? 'checked' : ''}> Hoạt động</label>
                    <label class="radio-option"><input type="radio" name="status" value="0" ${product.status != 1 ? 'checked' : ''}> Khóa</label>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/products">Quay lại</a>
        </div>
    </form>
</section>
</body>
</html>
