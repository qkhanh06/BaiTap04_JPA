package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import service.impl.UserServiceImpl;

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

        boolean ok =
                userService.resetPassword(
                        email,
                        request.getParameter("otp"),
                        request.getParameter("password"));

        if (ok) {
            request.setAttribute("success", "Doi mat khau thanh cong. Ban co the dang nhap.");
        } else {
            request.setAttribute("error", "OTP khong dung hoac da het han.");
            request.setAttribute("email", email);
        }

        request.getRequestDispatcher(
                "/views/reset-password.jsp")
                .forward(request, response);
    }
}
