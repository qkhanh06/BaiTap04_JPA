package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CategoryService categoryService =
            new CategoryServiceImpl();

    private final ProductService productService =
            new ProductServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Đếm số lượng Category
        try {

            int categoryCount =
                    categoryService.getAll().size();

            request.setAttribute(
                    "categoryCount",
                    categoryCount);

            request.setAttribute(
                    "productCount",
                    productService.count());

            request.setAttribute(
                    "latestProducts",
                    productService.findLatest(10));

        } catch (Exception e) {

            request.setAttribute(
                    "categoryCount",
                    0);

            request.setAttribute(
                    "productCount",
                    0);
        }

        request.getRequestDispatcher(
                "/views/index.jsp")
                .forward(request, response);
    }
}
