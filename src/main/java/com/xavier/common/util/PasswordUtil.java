package com.xavier.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 密码加密工具类
 *
 * @author NewGr8Player
 */
public class PasswordUtil {

	/**
	 * 获取加密后的密码
	 *
	 * @param password
	 * @return
	 */
	public static String EncryptPassword(String password) {
		try {
			return DigestUtils.md5Hex(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
