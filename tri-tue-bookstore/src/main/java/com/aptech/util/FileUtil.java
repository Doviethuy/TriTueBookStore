package com.aptech.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static void upload(HttpServletRequest request, MultipartFile file)
			throws ServletException {
		if (!file.isEmpty()) {
			String rootPath = System.getProperty("catalina.home");
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("Server File Location="
						+ serverFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("empty");
		}
	}
}
