package edu.uestc.blog.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	/**
	 * 在字符串前后加上%
	 * @param str
	 * @return
	 */
	public static String formatLike(String str) {
		if(!isEmpty(str)) {
			return "%" + str + "%" ;
		}else {
			return null ;			
		}
	}
	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true ;
		}
		return false ;
	}
	/**
	 * 对传入的字符串进行处理
	 */
	public static List<String> filterWhite(List<String> list){
		List<String> resultList = new ArrayList<String>() ;
		for(int i = 0 ; i < list.size() ; i ++) {
			if(!isEmpty(list.get(i))) {
				resultList.add(list.get(i)) ;
			}
		}
		return resultList ;
	}
}

