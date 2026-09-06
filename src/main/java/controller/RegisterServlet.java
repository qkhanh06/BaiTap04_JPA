package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ValidationUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService =
            new UserServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/views/register.jsp")
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
        String password =
                request.getParameter("password");
        String fullname =
                request.getParameter("fullname");
        String phone =
                request.getParameter("phone");
        String email =
                request.getParameter("email");

        if (!ValidationUtil.hasLength(username, 3, 50)
                || !ValidationUtil.hasLength(password, 6, 100)
                || !ValidationUtil.hasLength(fullname, 2, 100)
                || !ValidationUtil.isPhone(phone)
                || !ValidationUtil.isEmail(email)) {

            request.setAttribute("error", "Vui lòng nhập đúng thông tin đăng ký.");
            request.getRequestDispatcher("/views/register.jsp")
                    .forward(request, response);
            return;
        }

        User user =
                userService.register(
                        username,
                        password,
                        fullname,
                        phone,
                        email);

        if (user == null) {
            request.setAttribute("error", "Username hoặc email đã tồn tại.");
            doGet(request, response);
            return;
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/activate?username="
                        + user.getUsername());
    }
}
