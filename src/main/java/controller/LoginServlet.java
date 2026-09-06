package controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import service.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // ==================================
        // 1. KIỂM TRA SESSION
        // ==================================

        HttpSession session =
                request.getSession(false);

        if (session != null
                && session.getAttribute("username") != null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/home");

            return;
        }

        // ==================================
        // 2. ĐỌC COOKIE
        // ==================================

        Cookie[] cookies =
                request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if ("username".equals(
                        cookie.getName())) {

                    String username =
                            cookie.getValue();

                    request.setAttribute(
                            "rememberUsername",
                            username);

                    break;
                }
            }
        }

        // ==================================
        // 3. HIỂN THỊ LOGIN
        // ==================================

        request.getRequestDispatcher(
                "/views/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ==================================
        // 1. NHẬN DỮ LIỆU FORM
        // ==================================

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        String remember =
                request.getParameter("remember");

        // Checkbox được tick sẽ trả về "on"
        boolean isRememberMe =
                "on".equals(remember);

        // ==================================
        // 2. KIỂM TRA LOGIN
        // ==================================

        User user =
                new User(username, password);

        User loginUser =
                loginService.login(user);

        if (loginUser != null) {

            // ==================================
            // 3. TẠO SESSION
            // ==================================

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "username",
                    loginUser.getUsername());

            session.setAttribute(
                    "fullname",
                    loginUser.getFullname());

            session.setAttribute(
                    "phone",
                    loginUser.getPhone());

            session.setAttribute(
                    "images",
                    loginUser.getImages());

            session.setMaxInactiveInterval(
                    15 * 60);

            // ==================================
            // 4. REMEMBER ME
            // ==================================

            if (isRememberMe) {

                saveRememberMe(
                        response,
                        request,
                        username);

            } else {

                // Nếu bỏ chọn Remember Me
                // thì xóa Cookie cũ
                deleteRememberMe(
                        response,
                        request);
            }

            // ==================================
            // 5. CHUYỂN ĐẾN HOME
            // ==================================

            response.sendRedirect(
                    request.getContextPath()
                    + "/home");

        } else {

            request.setAttribute(
                    "error",
                    "Tài khoản chưa kích hoạt hoặc mật khẩu không đúng!");

            request.setAttribute(
                    "rememberUsername",
                    username);

            request.getRequestDispatcher(
                    "/views/login.jsp")
                    .forward(request, response);
        }
    }

    // ==================================
    // TẠO COOKIE REMEMBER ME
    // ==================================

    private void saveRememberMe(
            HttpServletResponse response,
            HttpServletRequest request,
            String username) {

        Cookie cookie =
                new Cookie(
                        "username",
                        username);

        // 30 phút
        cookie.setMaxAge(
                30 * 60);

        // Cookie dùng cho toàn project
        String contextPath =
                request.getContextPath();

        if (contextPath == null
                || contextPath.isEmpty()) {

            contextPath = "/";
        }

        cookie.setPath(contextPath);

        response.addCookie(cookie);
    }

    // ==================================
    // XÓA COOKIE
    // ==================================

    private void deleteRememberMe(
            HttpServletResponse response,
            HttpServletRequest request) {

        Cookie cookie =
                new Cookie(
                        "username",
                        "");

        // Tuổi = 0 → xóa Cookie
        cookie.setMaxAge(0);

        String contextPath =
                request.getContextPath();

        if (contextPath == null
                || contextPath.isEmpty()) {

            contextPath = "/";
        }

        cookie.setPath(contextPath);

        response.addCookie(cookie);
    }
}
