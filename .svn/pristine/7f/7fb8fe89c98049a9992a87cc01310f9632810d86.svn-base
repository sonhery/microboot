package com.dee.xql.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created: Administrator Date: 2016/11/9 0009.
 */
public class DateHelper {

	public static String getStrDateFormat(Date date, String format) {
		DateFormat dataFormat = new SimpleDateFormat(format);
		return dataFormat.format(date);
	}

	public static String getStrDateFormat(String strdate, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return getStrDateFormat(df.parse(strdate), format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Calendar getCalenDarTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static int getYear(Date date) {
		Calendar c = getCalenDarTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar c = getCalenDarTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	public static String getMonth(Date date, boolean isHaveZero) {
		Calendar c = getCalenDarTime(date);
		int temp = c.get(Calendar.MONTH) + 1;
		if (!isHaveZero) {
			return String.valueOf(temp);
		}
		if (temp < 10) {
			return "0" + temp;
		}
		return String.valueOf(temp);
	}

	public static int getDay(Date date) {
		Calendar c = getCalenDarTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static String getDay(Date date, boolean isHaveZero) {
		Calendar c = getCalenDarTime(date);
		int temp = c.get(Calendar.DAY_OF_MONTH);
		if (!isHaveZero) {
			return String.valueOf(temp);
		}
		if (temp < 10) {
			return "0" + temp;
		}
		return String.valueOf(temp);
	}

	/**
	 * 2016-12-26 00:00:00（字符串） 转 2016-12-26(字符串) ZXW
	 */
	public static String conversionTime(String strTime) {
		if (strTime.length() < 10) {
			return null;
		}
		strTime = strTime.substring(0, 10);
		return strTime;
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
		if(str.equals("")){
			return null;
		}
		
	    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = null;
	    try {
	        date = format.parse(str);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return date;
	}
}
