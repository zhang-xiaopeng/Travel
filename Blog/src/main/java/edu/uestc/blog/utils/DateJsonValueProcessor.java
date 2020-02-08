package edu.uestc.blog.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 此方法用于以日期格式显示日期
 * @author 张霄鹏
 *
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	private String format ;
	
	public  DateJsonValueProcessor(String format) {
		this.format = format ;
	}
	
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		
		if(value == null) {
			return "" ;
		}
		if(value instanceof Timestamp) {
			return new SimpleDateFormat(this.format).format((Timestamp)value) ;
		}
		if(value instanceof Date) {
			return new SimpleDateFormat(this.format).format((Date)value) ;
		}
		return value.toString() ;
	}

}
