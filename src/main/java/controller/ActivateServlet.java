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

@WebServlet("/activate")
public class ActivateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService =
            new UserServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "username",
                request.getParameter("username"));

        request.getRequestDispatcher(
                "/views/activate.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username =
                request.getParameter("username");

        if (!ValidationUtil.hasLength(username, 3, 50)
                || !ValidationUtil.isOtp(request.getParameter("otp"))) {

            request.setAttribute("error", "Vui lòng nhập username và OTP gồm 6 số.");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/activate.jsp")
                    .forward(request, response);
            return;
        }

        boolean ok =
                userService.activate(
                        username,
                        request.getParameter("otp"));

        if (ok) {
            request.setAttribute("success", "Kích hoạt thành công. Bạn có thể đăng nhập.");
        } else {
            request.setAttribute("error", "OTP không đúng hoặc đã hết hạn.");
            request.setAttribute("username", username);
        }

        request.getRequestDispatcher(
                "/views/activate.jsp")
                .forward(request, response);
    }
}
