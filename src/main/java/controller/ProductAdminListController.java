package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {
        "/admin/product/list",
        "/admin/products"
})
public class ProductAdminListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductService productService =
            new ProductServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "products",
                productService.findAll());

        request.getRequestDispatcher(
                "/views/admin/list-product.jsp")
                .forward(request, response);
    }
}
