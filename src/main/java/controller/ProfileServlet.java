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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.Constant;
import util.ValidationUtil;

@WebServlet(urlPatterns = {
        "/profile",
        "/profile/update"
})
@MultipartConfig
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService =
            new UserServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("username") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login");

            return;
        }

        String username =
                (String) session.getAttribute("username");

        User user =
                userService.findByUsername(username);

        if (user == null) {
            response.sendRedirect(
                    request.getContextPath()
                    + "/logout");

            return;
        }

        request.setAttribute("user", user);

        request.getRequestDispatcher("/views/profile.jsp")
               .include(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("username") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login");

            return;
        }

        String username =
                (String) session.getAttribute("username");

        User user =
                userService.findByUsername(username);

        if (user == null) {
            response.sendRedirect(
                    request.getContextPath()
                    + "/logout");

            return;
        }

        user.setFullname(
                request.getParameter("fullname"));

        user.setPhone(
                request.getParameter("phone"));

        if (!ValidationUtil.hasLength(user.getFullname(), 2, 100)
                || !ValidationUtil.isPhone(user.getPhone())) {

            request.setAttribute("user", user);
            request.setAttribute("error", "Vui lòng kiểm tra họ tên và số điện thoại.");
            request.getRequestDispatcher("/views/profile.jsp")
                    .include(request, response);
            return;
        }

        Part imagePart =
                request.getPart("images");

        if (imagePart != null
                && imagePart.getSize() > 0) {

            user.setImages(
                    saveImage(imagePart));
        }

        userService.updateProfile(user);
        syncSession(session, user);

        response.sendRedirect(
                request.getContextPath()
                + "/profile?success=1");
    }

    private String saveImage(Part imagePart)
            throws IOException {

        String submittedFileName =
                imagePart.getSubmittedFileName();

        String extension = "";

        int dotIndex =
                submittedFileName.lastIndexOf(".");

        if (dotIndex >= 0) {
            extension =
                    submittedFileName.substring(dotIndex);
        }

        String fileName =
                System.currentTimeMillis()
                + extension;

        Path uploadDirectory =
                Paths.get(Constant.DIR, "user");

        Files.createDirectories(uploadDirectory);

        Path filePath =
                uploadDirectory.resolve(fileName);

        try (InputStream input =
                     imagePart.getInputStream()) {

            Files.copy(
                    input,
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);
        }

        return "user/" + fileName;
    }

    private void syncSession(HttpSession session, User user) {

        session.setAttribute("fullname", user.getFullname());
        session.setAttribute("phone", user.getPhone());
        session.setAttribute("images", user.getImages());
    }
}
