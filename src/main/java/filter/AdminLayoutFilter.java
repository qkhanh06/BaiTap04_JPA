package filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class AdminLayoutFilter implements Filter {

    private static final Pattern BODY_PATTERN =
            Pattern.compile("(?is)<body[^>]*>(.*)</body>");
    private static final Pattern TITLE_PATTERN =
            Pattern.compile("(?is)<title>(.*?)</title>");

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;
        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        LayoutResponseWrapper wrapper =
                new LayoutResponseWrapper(httpResponse);

        chain.doFilter(request, wrapper);

        if (wrapper.isRedirected()
                || wrapper.getStatus() >= 300) {
            return;
        }

        String content =
                wrapper.getCapturedContent();

        if (content == null || content.isBlank()) {
            return;
        }

        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.getWriter().write(
                decorate(
                        httpRequest.getContextPath(),
                        httpRequest.getRequestURI(),
                        title(content),
                        body(content)));
    }

    private String title(String content) {
        Matcher matcher =
                TITLE_PATTERN.matcher(content);
        return matcher.find()
                ? matcher.group(1).trim()
                : "UTEx Store";
    }

    private String body(String content) {
        Matcher matcher =
                BODY_PATTERN.matcher(content);
        return matcher.find()
                ? matcher.group(1).trim()
                : content;
    }

    private String decorate(
            String contextPath,
            String requestUri,
            String title,
            String body) {

        return """
                <!DOCTYPE html>
                <html lang="vi">
                <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>%s</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet" href="%s/assets/css/admin.css?v=12">
                </head>
                <body>
                <div class="admin-wrapper app-shell">
                    <aside class="sidebar modern-sidebar">
                        <div class="brand brand-stack">
                            <span class="brand-mark">UT</span>
                            <span>UTEx Store</span>
                        </div>
                        <div class="admin-box compact-user">
                            <img src="%s/assets/images/avatar.jpg?v=2" class="avatar-img" alt="Avatar">
                            <div class="admin-text">Quản trị cửa hàng</div>
                            <strong>Administrator</strong>
                        </div>
                        <nav class="menu modern-menu">
                            %s
                        </nav>
                    </aside>
                    <div class="main modern-main">
                        <header class="topbar modern-topbar">
                            <div class="topbar-user">
                                <img src="%s/assets/images/avatar.jpg?v=2" class="topbar-avatar" alt="Avatar">
                                <div class="welcome">UTEx Store</div>
                            </div>
                            <a class="logout-btn" href="%s/logout">Đăng xuất</a>
                        </header>
                        <main class="content studio-content">
                            %s
                        </main>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
                <script>
                (() => {
                    document.querySelectorAll('.needs-validation').forEach((form) => {
                        form.addEventListener('submit', (event) => {
                            if (!form.checkValidity()) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                })();
                </script>
                </body>
                </html>
                """.formatted(
                title,
                contextPath,
                contextPath,
                menu(contextPath, requestUri),
                contextPath,
                contextPath,
                body);
    }

    private String menu(String contextPath, String requestUri) {
        return link(contextPath + "/home", "Trang chủ", requestUri.contains("/home"))
                + link(contextPath + "/profile", "Hồ sơ", requestUri.contains("/profile"))
                + link(contextPath + "/admin/categories", "Danh mục", requestUri.contains("/admin/category"))
                + link(contextPath + "/admin/products", "Sản phẩm", requestUri.contains("/admin/product"))
                + link(contextPath + "/product", "Trang bán hàng", false);
    }

    private String link(String href, String label, boolean active) {
        return "<a class=\"" + (active ? "active" : "") + "\" href=\"" + href + "\">"
                + label
                + "</a>";
    }

    private static final class LayoutResponseWrapper
            extends HttpServletResponseWrapper {

        private final CharArrayWriter buffer =
                new CharArrayWriter();
        private final PrintWriter writer =
                new PrintWriter(buffer);
        private boolean redirected;

        private LayoutResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() {
            return writer;
        }

        @Override
        public ServletOutputStream getOutputStream() {
            return new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {
                }

                @Override
                public void write(int value) {
                    buffer.write(value);
                }
            };
        }

        @Override
        public void sendRedirect(String location)
                throws IOException {
            redirected = true;
            super.sendRedirect(location);
        }

        private boolean isRedirected() {
            return redirected;
        }

        private String getCapturedContent() {
            writer.flush();
            return buffer.toString();
        }
    }
}
