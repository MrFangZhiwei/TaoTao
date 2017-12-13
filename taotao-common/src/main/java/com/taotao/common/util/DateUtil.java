package com.taotao.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * <p>Title: DateUtil</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-1上午10:59:37
 * @version  1.0
 */
public class DateUtil {
	/**
	 * 日期转成字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String str, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(str);
	}
	
}
