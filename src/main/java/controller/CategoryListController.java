package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {
        "/admin/category/list",
        "/admin/categories"
})
public class CategoryListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String keyword =
                request.getParameter("keyword");

        List<Category> cateList =
                keyword != null && !keyword.isBlank()
                        ? cateService.searchByName(keyword)
                        : cateService.findAll();

        request.setAttribute(
                "cateList",
                cateList);

        request.setAttribute(
                "listcate",
                cateList);

        request.getRequestDispatcher(
                "/views/admin/list-category.jsp")
                .forward(request, response);
    }
}
