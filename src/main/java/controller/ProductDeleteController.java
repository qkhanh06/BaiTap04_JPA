package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/admin/product/delete")
public class ProductDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductService productService =
            new ProductServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        productService.delete(
                Integer.parseInt(request.getParameter("id")));

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/product/list");
    }
}
