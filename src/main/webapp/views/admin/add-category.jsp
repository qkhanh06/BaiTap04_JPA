<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=6">
</head>
<body>

<div class="admin-wrapper app-shell">
    <aside class="sidebar modern-sidebar">
        <div class="brand brand-stack">
            <span class="brand-mark">UT</span>
            <span>UTEx Store</span>
        </div>

        <div class="menu modern-menu">
            <a href="${pageContext.request.contextPath}/home">Tong quan</a>
            <a class="active" href="${pageContext.request.contextPath}/admin/categories">Categories</a>
        </div>
    </aside>

    <div class="main modern-main">
        <div class="topbar modern-topbar">
            <div class="welcome">Add Category</div>
            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">Dang xuat</a>
        </div>

        <main class="content studio-content">
            <section class="form-hero">
                <span class="eyebrow">JPA Category</span>
                <h1>Them Category</h1>
                <p>Form dung cac field categoryname, images, images1 va status theo bai giang.</p>
            </section>

            <section class="card editor-card">
                <form action="<c:url value='/admin/category/insert'/>"
                      method="post"
                      enctype="multipart/form-data">

                    <div class="form-grid">
                        <div class="form-group">
                            <label for="categoryname">Category name</label>
                            <input type="text"
                                   id="categoryname"
                                   name="categoryname"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="images">Link images</label>
                            <input type="text"
                                   id="images"
                                   name="images">
                        </div>

                        <div class="form-group">
                            <label for="images1">Upload images</label>
                            <input type="file"
                                   id="images1"
                                   name="images1"
                                   accept="image/*">
                        </div>

                        <div class="form-group">
                            <label>Status</label>
                            <label>
                                <input type="radio" name="status" value="1" checked>
                                Hoat dong
                            </label>
                            <label>
                                <input type="radio" name="status" value="0">
                                Khoa
                            </label>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Insert</button>
                        <a class="btn btn-secondary"
                           href="${pageContext.request.contextPath}/admin/categories">
                            Quay lai
                        </a>
                    </div>
                </form>
            </section>
        </main>
    </div>
</div>

</body>
</html>
