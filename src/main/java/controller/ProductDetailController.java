package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/product/detail")
public class ProductDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductService productService =
            new ProductServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(request.getParameter("id"));

        Product product =
                productService.findById(id);

        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("product", product);
        request.getRequestDispatcher(
                "/views/product-detail.jsp")
                .forward(request, response);
    }
}
