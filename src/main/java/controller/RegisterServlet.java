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

        User user =
                userService.register(
                        request.getParameter("username"),
                        request.getParameter("password"),
                        request.getParameter("fullname"),
                        request.getParameter("phone"),
                        request.getParameter("email"));

        if (user == null) {
            request.setAttribute("error", "Username hoac email da ton tai.");
            doGet(request, response);
            return;
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/activate?username="
                        + user.getUsername());
    }
}
