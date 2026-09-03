<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=6">
</head>
<body>

<div class="admin-wrapper app-shell">
    <aside class="sidebar modern-sidebar">
        <div class="brand brand-stack">
            <span class="brand-mark">UT</span>
            <span>UTEx Store</span>
        </div>

        <div class="admin-box compact-user">
            <img src="${pageContext.request.contextPath}/assets/images/avatar.jpg?v=2"
                 class="avatar-img"
                 alt="Avatar">
            <div class="admin-text">Quan tri vien</div>
            <strong>${sessionScope.username}</strong>
        </div>

        <div class="menu modern-menu">
            <a href="${pageContext.request.contextPath}/home">Tong quan</a>
            <a href="${pageContext.request.contextPath}/profile">Ho so</a>
            <a class="active" href="${pageContext.request.contextPath}/admin/categories">Categories</a>
        </div>
    </aside>

    <div class="main modern-main">
        <div class="topbar modern-topbar">
            <div class="welcome">CRUD Category bang JPA API</div>
            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">Dang xuat</a>
        </div>

        <main class="content studio-content">
            <section class="catalog-hero">
                <div>
                    <span class="eyebrow">JPA Category</span>
                    <h1>Danh sach Category</h1>
                    <p>Du lieu duoc thao tac bang EntityManager va JPQL.</p>
                </div>

                <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/category/add">
                    Add Category
                </a>
            </section>

            <section class="card catalog-card">
                <div class="table-wrap product-table">
                    <table>
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Images</th>
                                <th>Category name</th>
                                <th>Status</th>
                                <th>Action</th>
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
                                    <td>
                                        <c:if test="${cate.status == 1}">Hoat dong</c:if>
                                        <c:if test="${cate.status != 1}">Khoa</c:if>
                                    </td>
                                    <td class="actions-cell">
                                        <a class="btn btn-warning"
                                           href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.categoryid}">
                                            Sua
                                        </a>
                                        <a class="btn btn-danger"
                                           onclick="return confirm('Xoa category nay?')"
                                           href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.categoryid}">
                                            Xoa
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty listcate}">
                                <tr>
                                    <td colspan="5" class="empty-text">Chua co category nao</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>
</div>

</body>
</html>
