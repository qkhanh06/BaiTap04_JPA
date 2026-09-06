package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public final class UploadUtil {

    private UploadUtil() {
    }

    public static String saveImage(
            HttpServletRequest request,
            String folder,
            String fileField)
            throws IOException, ServletException {

        Part part =
                request.getPart(fileField);

        if (part == null || part.getSize() == 0) {
            return null;
        }

        String originalFileName =
                part.getSubmittedFileName();

        String extension = "";
        int dotIndex =
                originalFileName.lastIndexOf(".");

        if (dotIndex >= 0) {
            extension =
                    originalFileName.substring(dotIndex);
        }

        String fileName =
                System.currentTimeMillis() + extension;

        Path uploadDirectory =
                Paths.get(Constant.DIR, folder);

        Files.createDirectories(uploadDirectory);

        Path filePath =
                uploadDirectory.resolve(fileName);

        try (InputStream input = part.getInputStream()) {
            Files.copy(
                    input,
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);
        }

        return folder + "/" + fileName;
    }
}
