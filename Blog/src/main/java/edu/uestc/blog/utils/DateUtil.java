package edu.uestc.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author 张霄鹏
 *
 */
public class DateUtil {
	/**
	 * 得到当前精确到秒的时间字符串
	 */
	public static String getCurrentDateStr() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) ;
	}
}
