package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import service.CategoryService;
import service.impl.CategoryServiceImpl;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CategoryService categoryService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra đăng nhập
        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("username") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login");

            return;
        }

        // Đếm số lượng Category
        try {

            int categoryCount =
                    categoryService.getAll().size();

            request.setAttribute(
                    "categoryCount",
                    categoryCount);

        } catch (Exception e) {

            // Nếu database lỗi thì Dashboard vẫn mở
            request.setAttribute(
                    "categoryCount",
                    0);
        }

        request.getRequestDispatcher(
                "/views/index.jsp")
                .forward(request, response);
    }
}