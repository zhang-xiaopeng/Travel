package edu.uestc.blog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {
	/**
	 * 用于MD5加密
	 * @param str 明文
	 * @param salt 密钥
	 * @return
	 */
	public static String MD5(String str,String salt) {
		return new Md5Hash(str,salt).toString() ;
	}
	public static void main(String[] args) {
		System.out.println(MD5("123456","java"));
	}
}
