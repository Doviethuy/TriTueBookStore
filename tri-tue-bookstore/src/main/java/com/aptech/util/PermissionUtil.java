package com.aptech.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

public class PermissionUtil {

	public static boolean checkLogin(HttpSession session) {
		try {
			String userName = session.getAttribute(Constant.USERNAME).toString();
			if (userName == null || userName.equals("")) {
				return false;
			} else {
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkAdminRole(HttpSession session) {
		byte role = Byte.valueOf(session.getAttribute(Constant.ROLE).toString());
		if (role == Constant.ADMIN_ROLE) {
			return true;
		} else if (role == Constant.STAFF_ROLE) {
			return false;
		} else {
			return false;
		}
	}
	
	public static String toMd5(String text){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(text.getBytes());
	        BigInteger number = new BigInteger(1, messageDigest);
			return number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}
