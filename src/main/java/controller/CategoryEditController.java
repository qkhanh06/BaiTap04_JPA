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

@WebServlet(urlPatterns = {
        "/admin/category/edit",
        "/admin/category/update"
})
@MultipartConfig
public class CategoryEditController
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                request.getParameter("id");

        Category category =
                cateService.get(
                        Integer.parseInt(id));

        request.setAttribute(
                "category",
                category);

        request.getRequestDispatcher(
                "/views/admin/edit-category.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id =
                Integer.parseInt(
                        firstNotBlank(
                                request.getParameter("categoryid"),
                                request.getParameter("id")));

        String name =
                firstNotBlank(
                        request.getParameter("categoryname"),
                        request.getParameter("name"));

        int status =
                parseStatus(
                        request.getParameter("status"));

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

        category.setId(id);
        category.setName(name);
        category.setIcon(icon);
        category.setStatus(status);

        cateService.edit(category);

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
