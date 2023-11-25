package layer.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("run");
        try {
            // 獲取上傳的文件部分
            Part filePart = request.getPart("file"); // "file" 是 HTML 表單中文件上傳字段的名稱

            // 獲取文件名
            String fileName = getSubmittedFileName(filePart);

            // 將文件保存到指定的目錄（這裡使用應用的根目錄）
            Path savePath = getUploadsDirectory().resolve(fileName);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, savePath, StandardCopyOption.REPLACE_EXISTING);
            }

            response.getWriter().println("文件上傳成功：" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private Path getUploadsDirectory() throws IOException {
        // 在應用的根目錄下創建一個名為 "uploads" 的目錄，用於存儲上傳的文件
        Path path = Paths.get("upload");

        System.out.println(path);
        if (!Files.exists(path)) {
            Files.createDirectories(path);

        }

        return path;
    }
}
