package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/product")
public class ProductListController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE = 6;

    private final ProductService productService =
            new ProductServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int page =
                parsePage(request.getParameter("page"));

        int totalItems =
                productService.count();

        int totalPages =
                Math.max(1, (int) Math.ceil(totalItems / (double) PAGE_SIZE));

        if (page > totalPages) {
            page = totalPages;
        }

        List<Product> products =
                productService.findAll(page - 1, PAGE_SIZE);

        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher(
                "/views/product.jsp")
                .forward(request, response);
    }

    private int parsePage(String value) {
        try {
            return Math.max(1, Integer.parseInt(value));
        } catch (Exception e) {
            return 1;
        }
    }
}
