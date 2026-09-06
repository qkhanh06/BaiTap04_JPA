package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import service.impl.UserServiceImpl;

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

        boolean ok =
                userService.activate(
                        username,
                        request.getParameter("otp"));

        if (ok) {
            request.setAttribute("success", "Kich hoat thanh cong. Ban co the dang nhap.");
        } else {
            request.setAttribute("error", "OTP khong dung hoac da het han.");
            request.setAttribute("username", username);
        }

        request.getRequestDispatcher(
                "/views/activate.jsp")
                .forward(request, response);
    }
}
