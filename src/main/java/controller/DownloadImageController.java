package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import util.Constant;

@WebServlet("/image")
public class DownloadImageController
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String fileName =
                request.getParameter("fname");

        if (fileName == null
                || fileName.isBlank()) {

            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND);

            return;
        }

        Path root =
                Paths.get(Constant.DIR)
                     .toAbsolutePath()
                     .normalize();

        Path file =
                root.resolve(fileName)
                    .normalize();

        if (!file.startsWith(root)
                || !Files.exists(file)) {

            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND);

            return;
        }

        String contentType =
                Files.probeContentType(file);

        if (contentType == null) {

            contentType =
                    "application/octet-stream";
        }

        response.setContentType(
                contentType);

        Files.copy(
                file,
                response.getOutputStream());
    }
}