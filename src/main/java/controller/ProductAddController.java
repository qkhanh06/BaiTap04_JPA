package controller;

import java.io.IOException;

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
import util.ValidationUtil;

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
                .include(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Product product =
                readProduct(request);

        if (!isValid(request, product)) {
            request.setAttribute("error", "Vui lòng kiểm tra tên sản phẩm, danh mục và giá bán.");
            request.setAttribute("product", product);
            request.setAttribute("price", request.getParameter("price"));
            request.setAttribute("selectedCategoryId", request.getParameter("categoryId"));
            doGet(request, response);
            return;
        }

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
        product.setPrice(
                ValidationUtil.positiveMoney(
                        request.getParameter("price")));
        product.setImages(request.getParameter("images"));
        product.setStatus(parseInt(request.getParameter("status"), 1));

        int categoryId =
                parseInt(request.getParameter("categoryId"), 0);

        Category category =
                categoryId > 0
                        ? categoryService.findById(categoryId)
                        : null;

        product.setCategory(category);

        return product;
    }

    private boolean isValid(
            HttpServletRequest request,
            Product product) {

        return ValidationUtil.hasLength(product.getProductName(), 2, 150)
                && product.getPrice() != null
                && !ValidationUtil.isBlank(request.getParameter("categoryId"))
                && product.getCategory() != null;
    }

    private int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
