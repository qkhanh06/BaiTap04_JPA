package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.CategoryService;
import service.impl.CategoryServiceImpl;

@WebServlet("/admin/category/delete")
public class CategoryDeleteController
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

        cateService.delete(
                Integer.parseInt(id));

        response.sendRedirect(
                request.getContextPath()
                + "/admin/category/list");
    }
}