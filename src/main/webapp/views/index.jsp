<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tổng quan cửa hàng</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/admin.css?v=6">
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
            <div class="admin-text">Quản trị cửa hàng</div>
            <strong>${sessionScope.username}</strong>
        </div>

        <div class="menu modern-menu">
            <a class="active" href="${pageContext.request.contextPath}/home">Tổng quan</a>
            <a href="${pageContext.request.contextPath}/profile">Hồ sơ cá nhân</a>
            <a href="${pageContext.request.contextPath}/admin/category/list">Sản phẩm</a>
        </div>
    </aside>

    <div class="main modern-main">
        <div class="topbar modern-topbar">
            <div class="topbar-user">
                <img src="${pageContext.request.contextPath}/assets/images/avatar.jpg?v=2"
                     class="topbar-avatar"
                     alt="Avatar">
                <div class="welcome">
                    Xin chào <strong>${sessionScope.fullname}</strong>
                </div>
            </div>

            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>

        <main class="content studio-content">
            <section class="overview-hero store-hero">
                <div>
                    <span class="eyebrow">Store Manager</span>
                    <h1>Điều hành danh mục iPhone</h1>
                    <p>
                        Quản lý sản phẩm, hình ảnh và thông tin hiển thị cho cửa hàng từ một
                        bảng điều khiển gọn gàng.
                    </p>
                </div>

                <div class="hero-account">
                    <img src="${pageContext.request.contextPath}/assets/images/avatar.jpg?v=2"
                         alt="Avatar">
                    <div>
                        <span>Quản trị viên</span>
                        <strong>${sessionScope.fullname}</strong>
                        <small>${sessionScope.studentId}</small>
                    </div>
                </div>
            </section>

            <section class="metric-strip">
                <div class="metric-tile accent">
                    <span>Sản phẩm</span>
                    <strong>${categoryCount}</strong>
                    <small>Đang hiển thị</small>
                </div>

                <div class="metric-tile">
                    <span>Bộ sưu tập</span>
                    <strong>iPhone</strong>
                    <small>Dòng sản phẩm chính</small>
                </div>

                <div class="metric-tile">
                    <span>Tài khoản</span>
                    <strong>${sessionScope.username}</strong>
                    <small>Quản trị viên</small>
                </div>

                <div class="metric-tile">
                    <span>Ảnh sản phẩm</span>
                    <strong>Upload</strong>
                    <small>Lưu trong thư viện</small>
                </div>
            </section>

            <section class="workspace-grid">
                <div class="command-panel">
                    <div class="panel-heading">
                        <span>Quản lý nhanh</span>
                        <strong>Công việc hôm nay</strong>
                    </div>

                    <a class="command-row primary-command"
                       href="${pageContext.request.contextPath}/admin/category/list">
                        <span class="command-icon">01</span>
                        <div>
                            <strong>Mở danh sách sản phẩm</strong>
                            <small>Xem toàn bộ iPhone, ảnh đại diện và thao tác chỉnh sửa.</small>
                        </div>
                    </a>

                    <a class="command-row"
                       href="${pageContext.request.contextPath}/admin/category/add">
                        <span class="command-icon">02</span>
                        <div>
                            <strong>Thêm sản phẩm mới</strong>
                            <small>Tạo một mẫu iPhone mới kèm hình ảnh hiển thị.</small>
                        </div>
                    </a>

                    <a class="command-row"
                       href="${pageContext.request.contextPath}/profile">
                        <span class="command-icon">03</span>
                        <div>
                            <strong>Xem hồ sơ quản trị</strong>
                            <small>Kiểm tra thông tin người đang vận hành cửa hàng.</small>
                        </div>
                    </a>
                </div>

                <div class="system-panel store-note">
                    <div class="panel-heading">
                        <span>Ghi chú vận hành</span>
                        <strong>UTEx Store</strong>
                    </div>

                    <p>
                        Cập nhật tên và ảnh rõ ràng giúp danh mục dễ xem hơn khi mở trên
                        trang quản trị. Những sản phẩm không còn dùng có thể xóa trực tiếp
                        trong bảng danh sách.
                    </p>
                    <a class="btn btn-secondary"
                       href="${pageContext.request.contextPath}/admin/category/list">
                        Kiểm tra danh mục
                    </a>
                </div>
            </section>
        </main>
    </div>
</div>

</body>
</html>
