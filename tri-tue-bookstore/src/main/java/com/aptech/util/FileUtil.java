package com.aptech.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static void upload(HttpServletRequest request,MultipartFile file) throws ServletException {
		if (!file.isEmpty()) {
			ServletContext context = request.getServletContext();
			String filePath = context.getInitParameter("upload-folder");
			try {

	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	            System.out.println(file.getName()+":file name");
	            Path path = Paths.get(filePath + file.getOriginalFilename()+new Date().getTime());
	            Files.write(path, bytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		}else {
			System.out.println("empty");
		}
	}
}
