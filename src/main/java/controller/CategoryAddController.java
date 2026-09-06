package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import model.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import util.Constant;
import util.ValidationUtil;

@WebServlet(urlPatterns = {
        "/admin/category/add",
        "/admin/category/insert"
})
@MultipartConfig
public class CategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/views/admin/add-category.jsp")
                .include(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name =
                firstNotBlank(
                        request.getParameter("categoryname"),
                        request.getParameter("name"));

        int status =
                parseStatus(
                        request.getParameter("status"));

        if (!ValidationUtil.hasLength(name, 2, 50)) {
            Category invalidCategory =
                    new Category();
            invalidCategory.setCategoryname(name);
            invalidCategory.setImages(request.getParameter("images"));
            invalidCategory.setStatus(status);

            request.setAttribute("category", invalidCategory);
            request.setAttribute("error", "Vui lòng nhập tên danh mục từ 2 đến 50 ký tự.");
            request.getRequestDispatcher("/views/admin/add-category.jsp")
                    .include(request, response);
            return;
        }

        Part iconPart =
                getPart(request, "images1", "icon");

        String icon =
                request.getParameter("images");

        if (iconPart != null
                && iconPart.getSize() > 0) {

            String originalFileName =
                    iconPart.getSubmittedFileName();

            String extension = "";

            int dotIndex =
                    originalFileName.lastIndexOf(".");

            if (dotIndex >= 0) {

                extension =
                        originalFileName.substring(
                                dotIndex);
            }

            String fileName =
                    System.currentTimeMillis()
                    + extension;

            Path uploadDirectory =
                    Paths.get(
                            Constant.DIR,
                            "category");

            Files.createDirectories(
                    uploadDirectory);

            Path filePath =
                    uploadDirectory.resolve(
                            fileName);

            try (InputStream input =
                         iconPart.getInputStream()) {

                Files.copy(
                        input,
                        filePath,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            icon =
                    "category/" + fileName;
        }

        Category category =
                new Category();

        category.setName(name);
        category.setIcon(icon);
        category.setStatus(status);

        cateService.insert(category);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/category/list");
    }

    private String firstNotBlank(String first, String second) {

        if (first != null && !first.isBlank()) {
            return first;
        }

        return second;
    }

    private int parseStatus(String status) {

        if (status == null || status.isBlank()) {
            return 1;
        }

        return Integer.parseInt(status);
    }

    private Part getPart(
            HttpServletRequest request,
            String first,
            String second)
            throws IOException, ServletException {

        Part part =
                request.getPart(first);

        if (part != null && part.getSize() > 0) {
            return part;
        }

        return request.getPart(second);
    }
}
