package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ValidationUtil;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService =
            new UserServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "email",
                request.getParameter("email"));

        request.getRequestDispatcher(
                "/views/reset-password.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email =
                request.getParameter("email");

        if (!ValidationUtil.isEmail(email)
                || !ValidationUtil.isOtp(request.getParameter("otp"))
                || !ValidationUtil.hasLength(request.getParameter("password"), 6, 100)) {

            request.setAttribute("error", "Vui lòng nhập email, OTP và mật khẩu hợp lệ.");
            request.setAttribute("email", email);
            request.getRequestDispatcher("/views/reset-password.jsp")
                    .forward(request, response);
            return;
        }

        boolean ok =
                userService.resetPassword(
                        email,
                        request.getParameter("otp"),
                        request.getParameter("password"));

        if (ok) {
            request.setAttribute("success", "Đổi mật khẩu thành công. Bạn có thể đăng nhập.");
        } else {
            request.setAttribute("error", "OTP không đúng hoặc đã hết hạn.");
            request.setAttribute("email", email);
        }

        request.getRequestDispatcher(
                "/views/reset-password.jsp")
                .forward(request, response);
    }
}
