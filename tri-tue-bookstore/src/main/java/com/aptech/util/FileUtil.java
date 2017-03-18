package com.aptech.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class FileUtil {

	public static String upload(HttpServletRequest request, MultipartFile file) throws ServletException {
		String newFileName = StringPool.BLANK;
		if (Validator.isNotNull(file)) {
			String rootPath = System.getProperty("catalina.home");
			try {
				byte[] bytes = file.getBytes();
				if (file.getSize() > 0) {
					File dir = new File(rootPath + File.separator + "data");
					if (!dir.exists())
						dir.mkdirs();
					newFileName = new Date().getTime() + file.getOriginalFilename();
					File serverFile = new File(dir.getAbsolutePath() + File.separator + newFileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.flush();
					stream.close();
				} else {
					return StringPool.BLANK;
				}
			} catch (IOException e) {
				_log.error(e);
			}
		}
		return newFileName;
	}

	private static Log _log = LogFactoryUtil.getLog(FileUtil.class.getName());
}
