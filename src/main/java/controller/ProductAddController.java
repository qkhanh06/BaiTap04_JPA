package controller;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import util.UploadUtil;

@WebServlet(urlPatterns = {
        "/admin/product/add",
        "/admin/product/insert"
})
@MultipartConfig
public class ProductAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductService productService =
            new ProductServiceImpl();
    private final CategoryService categoryService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("categories", categoryService.findAll());
        request.getRequestDispatcher(
                "/views/admin/add-product.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Product product =
                readProduct(request);

        String uploadImage =
                UploadUtil.saveImage(request, "product", "images1");

        if (uploadImage != null) {
            product.setImages(uploadImage);
        }

        productService.insert(product);

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/product/list");
    }

    private Product readProduct(HttpServletRequest request) {
        Product product = new Product();
        product.setProductName(request.getParameter("productName"));
        product.setDescription(request.getParameter("description"));
        product.setPrice(new BigDecimal(request.getParameter("price")));
        product.setImages(request.getParameter("images"));
        product.setStatus(Integer.parseInt(request.getParameter("status")));

        Category category =
                categoryService.findById(
                        Integer.parseInt(request.getParameter("categoryId")));

        product.setCategory(category);

        return product;
    }
}
