package com.cxdai.console.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DateUtils {
	/** yyyy-MM-dd HH:mm:ss */
	public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
	/** yyyyMMddHHmmss */
	public static final String YMDHMS = "yyyyMMddHHmmss";
	/** yyyyMMdd */
	public static final String YMD = "yyyyMMdd";
	/** yyyy-MM */
	public static final String YM = "yyyy-MM";
	/** MMdd */
	public static final String MD = "MMdd";
	/** MM-dd */
	public static final String MD_DASH = "MM-dd";
	/** yyyy/MM/dd */
	public static final String YMD_SLASH = "yyyy/MM/dd";
	/** yyyy-MM-dd */
	public static final String YMD_DASH = "yyyy-MM-dd";
	/** yyyy-MM-dd H:m */
	public static final String YMD_DASH_WITH_TIME = "yyyy-MM-dd H:m";
	/** yyyy-MM-dd HH:mm */
	public static final String DATETIME_YMD_DASH = "yyyy-MM-dd HH:mm";
	/** yyyy/dd/MM */
	public static final String YDM_SLASH = "yyyy/dd/MM";
	/** yyyy/MM/dd HH:mm:ss */
	public static final String YMD_SLAHMS = "yyyy/MM/dd HH:mm:ss";
	/** yyyy-dd-MM */
	public static final String YDM_DASH = "yyyy-dd-MM";
	/** HHmm */
	public static final String HM = "HHmm";
	/** HH:mm */
	public static final String HM_COLON = "HH:mm";
	/** HH:mm:ss */
	public static final String HMS_COLON = "HH:mm:ss";
	/** yyyy-MM */
	public static final String YM_DASH = "yyyy-MM";
	/** yyyy年MM月dd日 */
	public static final String YMD_NYRSH = "yyyy年MM月dd日";
	
	public static final String YMDH_M_S = "yyyyMMdd HH:mm:ss";
	
	public static final long DAY = 24 * 60 * 60 * 1000L;
	public static final long MINUTE = 60 * 1000L;
	public static final long SECOND = 1000L;

	private static final Map<String, DateFormat> DFS = new HashMap<String, DateFormat>();

	/**
	 * 获取当前时间的时间戳（精确到秒）
	 * <p>
	 * Description:获取当前时间的时间戳（精确到秒）<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return String
	 */
	public static String getCurrentTimeStamp() {
		return getTime();
	}

	/**
	 * 获取当前时间的时间戳（精确到毫秒）
	 * <p>
	 * Description:获取当前时间的时间戳（精确到毫秒）<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return Long
	 */
	public static Long getCurrentTimeStamps() {
		return new Date().getTime();
	}

	/**
	 * 将指定的日期格式字符串转换为时间戳(精确到秒)
	 * <p>
	 * Description:将指定的日期格式字符串转换为时间戳(精确到秒)<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 格式：yyyy-MM-dd
	 * @return String
	 */
	public static String date2TimeStamp(String date) {
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date s = dateformat.parse(date);
			return String.valueOf(s.getTime() / 1000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将指定的日期格式字符串转换为时间戳(精确到秒)
	 * <p>
	 * Description:将指定的日期格式字符串转换为时间戳(精确到秒)<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param time 格式：yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String dateTime2TimeStamp(String date) {
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date s = dateformat.parse(date);
			return String.valueOf(s.getTime() / 1000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定时间的时间戳（精确到毫秒）
	 * <p>
	 * Description:获取指定时间的时间戳（精确到毫秒）<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date
	 * @return Long
	 */
	public static Long getTimeStampsByDate(Date date) {
		return date.getTime();
	}

	/**
	 * 当前日期的前N天或后N天的时间戳(精确到秒)
	 * <p>
	 * Description:当前日期的前N天或后N天的时间戳(精确到秒)<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param days 当为负整数时，为前N天，当为正整数时，为后N天
	 * @return String
	 */
	public static String getCurrentDayTime(int days) {
		return getDayTime(days);
	}

	/**
	 * 指定日期的前N天或后N天的时间戳(精确到秒)
	 * <p>
	 * Description:指定日期的前N天或后N天的时间戳(精确到秒)<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param days 当为负整数时，为前N天，当为正整数时，为后N天
	 * @return String
	 */
	public static String getDayTime(Date date, int days) {
		String dateStr = format(dayOffset(date, days), YMD_HMS);
		return dateTime2TimeStamp(dateStr);
	}

	/**
	 * 当前日期最后一秒的时间戳（精确到秒）
	 * <p>
	 * Description:当前日期最后一秒的时间戳（精确到秒）<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return String 如:2013-10-30 23:59:59的时间戳
	 */
	public static String getTimeDayLastSecond() {
		return dateTime2TimeStamp(TimeStamp2Date(String.valueOf(System.currentTimeMillis())) + " 23:59:59");
	}

	/**
	 * 将指定的时间戳字符串转换为指定日期格式字符串
	 * <p>
	 * Description:将指定的时间戳字符串转换为指定日期格式字符串<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param timestampStr 时间戳字符串（精确到秒）
	 * @param pattern 日期时间格式
	 * @return String
	 */
	public static String timeStampToDate(String timestampStr, String pattern) {
		Long timestamp = Long.parseLong(timestampStr) * 1000;
		String date = new java.text.SimpleDateFormat(pattern).format(new java.util.Date(timestamp));
		return date;
	}

	/**
	 * 将指定的时间戳字符串转换为日期格式
	 * <p>
	 * Description:将指定的时间戳字符串转换为日期格式<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param timestampStr 时间戳字符串（精确到秒）
	 * @return Date
	 */
	public static Date timeStampToDate(String timestampStr) {
		Long timestamp = Long.parseLong(timestampStr) * 1000;
		return new java.util.Date(timestamp);
	}

	/**
	 * 将指定的时间戳字符串转换为日期格式字符串
	 * <p>
	 * Description:将指定的时间戳字符串转换为日期格式字符串<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param timestamp 时间戳字符串 （精确到秒）
	 * @return 转换后格式为 yyyy-MM-dd HH:mm:ss String
	 */
	public static String TimeStamp2DateTime(String timestampString) {
		Long timestamp = Long.valueOf(Long.parseLong(timestampString));
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp.longValue()));
		return date;
	}

	/**
	 * 将指定的时间戳字符串转换为日期格式字符串
	 * <p>
	 * Description:将指定的时间戳字符串转换为日期格式字符串<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param timestamp 时间戳字符串 （精确到秒）
	 * @return 转换后格式为 yyyy-MM-dd String
	 */
	public static String TimeStamp2Date(String timestampString) {
		Long timestamp = Long.valueOf(timestampString.length() < 13 ? Long.parseLong(timestampString) * 1000L : Long.parseLong(timestampString));
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp.longValue()));
		return date;
	}

	/**
	 * 将日期的字符串形式按格式转换成日期
	 * <p>
	 * Description:将日期的字符串形式按格式转换成日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param source 日期格式字符串
	 * @param pattern 日期格式
	 * @return Date
	 */
	public static Date parse(String source, String pattern) {
	
		if (source == null) {
			return null;
		}
		Date date;
		try {
			date = getFormat(pattern).parse(source);
			System.out.println(date);
		} catch (ParseException e) {
			e.getStackTrace();
			return null;
		}
		return date;
	}
   
	/**
	 * 按格式要求，将日期按字符串格式输出
	 * <p>
	 * Description:按格式要求，将日期按字符串格式输出<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param pattern 日期格式
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return getFormat(pattern).format(date);
	}

	/**
	 * 按格式要求，按DateFormat格式输出
	 * <p>
	 * Description:按格式要求，按DateFormat格式输出<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param pattern
	 * @return DateFormat
	 */
	public static DateFormat getFormat(String pattern) {
		DateFormat format = DFS.get(pattern);
		if (format == null) {
			format = new SimpleDateFormat(pattern);
			DFS.put(pattern, format);
		}
		return format;
	}

	/**
	 * 按格式要求，获得当前时间，按字符串格式输出
	 * <p>
	 * Description:按格式要求，获得当前时间，按字符串格式输出<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param pattern 日期格式
	 * @return String
	 */
	public static String getNow(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 判断输入的年、月、日是否是有效日期
	 * <p>
	 * Description:判断输入的年、月、日是否是有效日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param year 年
	 * @param month 月（1-12）
	 * @param day 日（1-31）
	 * @return boolean
	 */
	public static boolean isValid(int year, int month, int day) {
		if (month > 0 && month < 13 && day > 0 && day < 32) {
			// month of calendar is 0-based
			int mon = month - 1;
			Calendar calendar = new GregorianCalendar(year, mon, day);
			if (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == mon && calendar.get(Calendar.DAY_OF_MONTH) == day) {
				return true;
			}
		}
		return false;
	}

	public static Date convert2StartDate(Date date) {
		if (date == null) {
			return null;
		}
		return parse(format(date, YDM_DASH), YDM_DASH);
	}

	public static Date convert2EndDate(Date date) {
		if (date == null) {
			return null;
		}
		return parse((format(date, YMD_DASH) + " 23:59:59"), YMD_HMS);
	}

	/**
	 * 将指定日期转换为日历格式的日期对象
	 * <p>
	 * Description:将指定日期转换为日历格式的日期对象<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @return Calendar
	 */
	private static Calendar convert(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 返回指定日期按年份【前移】或【后移】后的日期
	 * <p>
	 * Description:返回指定日期按年份【前移】或【后移】后的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param offset 当为正整数，则后移，当为负整数，则前移
	 * @return Date
	 */
	public static Date yearOffset(Date date, int offset) {
		return offsetDate(date, Calendar.YEAR, offset);
	}

	/**
	 * 返回指定日期按月份【前移】或【后移】后的日期
	 * <p>
	 * Description:返回指定日期按月份【前移】或【后移】后的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param offset 当为正整数，则后移，当为负整数，则前移
	 * @return Date
	 */
	public static Date monthOffset(Date date, int offset) {
		return offsetDate(date, Calendar.MONTH, offset);
	}

	/**
	 * 返回指定日期按天数【前移】或【后移】后的日期
	 * <p>
	 * Description:返回指定日期按天数【前移】或【后移】后的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param offset 当为正整数，则后移，当为负整数，则前移
	 * @return Date
	 */
	public static Date dayOffset(Date date, int offset) {
		return offsetDate(date, Calendar.DATE, offset);
	}

	/**
	 * 返回指定日期按小时数【前移】或【后移】后的日期
	 * <p>
	 * Description:返回指定日期按小时数【前移】或【后移】后的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param offset 当为正整数，则后移，当为负整数，则前移
	 * @return Date
	 */
	public static Date hoursOffset(Date date, int offset) {
		return offsetDate(date, Calendar.HOUR, offset);
	}

	/**
	 * 返回指定日期按分钟数【前移】或【后移】后的日期
	 * <p>
	 * Description:返回指定日期按分钟数【前移】或【后移】后的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param offset 当为正整数，则后移，当为负整数，则前移
	 * @return Date
	 */
	public static Date minuteOffset(Date date, int offset) {
		return offsetDate(date, Calendar.MINUTE, offset);
	}

	/**
	 * 返回指定日期按秒数【前移】或【后移】后的日期
	 * <p>
	 * Description:返回指定日期按秒数【前移】或【后移】后的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param offset 当为正整数，则后移，当为负整数，则前移
	 * @return Date
	 */
	public static Date secondOffset(Date date, int offset) {
		return offsetDate(date, Calendar.SECOND, offset);
	}

	/**
	 * 返回指定日期按指定方式【前移】或【后移】后的日期
	 * <p>
	 * Description:返回指定日期按指定方式【前移】或【后移】后的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @param field 指定方式，如：Calendar.YEAR 按年份，Calendar.MONTH 按月份，Calendar.DATE
	 *            按天数
	 * @param offset 当为正整数，则后移，当为负整数，则前移
	 * @return Date
	 */
	public static Date offsetDate(Date date, int field, int offset) {
		Calendar calendar = convert(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期所在当月的第一天的日期
	 * <p>
	 * Description:返回指定日期所在当月的第一天的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @return Date
	 */
	public static Date firstDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期所在当月的最后一天的日期
	 * <p>
	 * Description:返回指定日期所在当月的最后一天的日期<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期
	 * @return Date
	 */
	public static Date lastDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	/**
	 * 返回两个日期间的差异天数
	 * <p>
	 * Description:返回两个日期间的差异天数<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date1 参照日期
	 * @param date2 比较日期
	 * @return 参照日期与比较日期之间的天数差异，正数表示参照日期在比较日期之后，0表示两个日期同天，负数表示参照日期在比较日期之前 int
	 */
	public static int dayDiff(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		return (int) (diff / DAY);
	}

	/**
	 * 返回两个日期间的差异分钟数
	 * <p>
	 * Description:返回两个日期间的差异分钟数<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date1 参照日期
	 * @param date2 比较日期
	 * @return 参照日期与比较日期之间的分钟数差异，正数表示参照日期在比较日期之后，0表示两个日期同分，负数表示参照日期在比较日期之前 int
	 */
	public static int minuteDiff(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		return (int) (diff / MINUTE);
	}

	/**
	 * 返回两个日期间的差异秒数
	 * <p>
	 * Description:返回两个日期间的差异秒数<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date1 参照日期
	 * @param date2 比较日期
	 * @return 参照日期与比较日期之间的秒数差异，正数表示参照日期在比较日期之后，0表示两个日期同分，负数表示参照日期在比较日期之前 int
	 */
	public static int secondDiff(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		return (int) (diff / SECOND);
	}

	/**
	 * 将指定日期转换为小时数
	 * <p>
	 * Description:将指定日期转换为小时数<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date
	 * @return Double
	 */
	public static Double hour(Date date) {
		SimpleDateFormat st = new SimpleDateFormat("yyyyMMddHH");
		return Double.parseDouble(st.format(date));
	}

	/**
	 * 将日期转化成 mysql 的 str_to_date(date,'%Y-%m-%d %H:%i:%s') 格式
	 * <p>
	 * Description:将日期转化成 mysql 的 str_to_date(date,'%Y-%m-%d %H:%i:%s') 格式<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 指定日期格式字符串
	 * @param hqlFormat mysql的日期格式，例如：'%Y-%m-%d %H:%i:%s'
	 * @return String
	 */
	public static String toDate(String date, String hqlFormat) {
		StringBuffer bf = new StringBuffer();
		bf.append("str_to_date('");
		bf.append(date);
		bf.append("','");
		bf.append(hqlFormat);
		bf.append("')");
		return bf.toString();
	}

	/**
	 * 将日期转化成 时间戳（mysql语法）
	 * <p>
	 * Description:将日期转化成 时间戳（mysql语法）<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param date 日期格式字符串，如 yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String unixTimestamp(String date) {
		StringBuffer bf = new StringBuffer();
		bf.append("unix_timestamp('");
		bf.append(date);
		bf.append("')");
		return bf.toString();
	}

	/**
	 * <p>
	 * Description:判断当前时间是否超过某个指定日期一天的时间<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param end 指定日期
	 * @return 当前时间大于指定时间一天时返回true boolean
	 */
	public static boolean isDateExpired(Date end) {
		if (end == null)
			return false;
		if (new Date().after(new Date(end.getTime() + 24 * 60 * 60 * 1000))) {
			return true;
		}
		return false;

	}

	/**
	 * <p>
	 * Description:获取当月的最后一天是几号<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return int
	 */
	public static int getMonthDays() {
		Calendar calendar = Calendar.getInstance();
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return maxDay;
	}

	/**
	 * <p>
	 * Description:获取今天是几号<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return int
	 */
	public static int getDateByDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}

	/**
	 * <p>
	 * Description:获取今天是几月<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return int
	 */
	public static int getMonthByDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * <p>
	 * Description:获取今天是那年<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return int
	 */
	public static int getYearByDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * <p>
	 * Description:获取指定日期是星期几<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param dt 指定日期
	 * @return String
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * <p>
	 * Description:获取给定日期当月的工作日集合<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param year 指定年份
	 * @param month 指定月份
	 * @return List<Date>
	 */
	public static List<Date> getDates(int year, int month) {
		List<Date> dates = new ArrayList<Date>();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);

		while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);

			if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
				dates.add((Date) cal.getTime().clone());
			}
			cal.add(Calendar.DATE, 1);
		}
		return dates;

	}

	/**
	 * <p>
	 * Description:取得当月天数<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @return int
	 */
	public static int getCurrentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * <p>
	 * Description:得到指定月的天数<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月30日
	 * @param year 指定年份
	 * @param month 指定月份
	 * @return int
	 */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	private static Calendar getCalendar() {
		TimeZone tz = TimeZone.getDefault();
		return Calendar.getInstance(tz);
	}

	public static String getYear() {
		return String.valueOf(getCalendar().get(1));
	}

	public static String getMonth() {
		return strFormat(getCalendar().get(2) + 1);
	}

	public static String getDay() {
		return strFormat(getCalendar().get(5));
	}

	public static String getFormatDate(String separator) {
		return getYear() + separator + getMonth() + separator + getDay();
	}

	public static String getHour() {
		return strFormat(getCalendar().get(11));
	}

	public static String getMinute() {
		return strFormat(getCalendar().get(12));
	}

	public static String getSecond() {
		return strFormat(getCalendar().get(13));
	}

	public static String getDateTime(String s1, String s2) {
		return getYear() + s1 + getMonth() + s1 + getDay() + " " + getHour() + s2 + getMinute() + s2 + getSecond();
	}

	public static String getDateTime2(String s1, String s2) {
		return getYear() + s1 + getMonth() + s1 + getDay() + getHour() + s2 + getMinute() + s2 + getSecond();
	}

	public static String getTime(String s1) {
		return getHour() + s1 + getMinute() + s1 + getSecond();
	}

	public static String getTime() {
		return String.valueOf(System.currentTimeMillis() / 1000L);
	}

	private static String strFormat(int data) {
		String str = String.valueOf(data);
		if (data <= 9) {
			str = "0" + str;
		}
		return str;
	}

	public static String TimeStamp2Date_month(String timestamp2String) {
		Long timestamp = Long.valueOf(Long.parseLong(timestamp2String) * 1000L);
		String date = new SimpleDateFormat("yyyy-MM").format(new Date(timestamp.longValue()));
		return date;
	}

	public static String getMonthTime(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(2, month);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static String getDayTime(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(6, days);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static String getYearTime(int years) {
		Calendar cal = Calendar.getInstance();
		cal.add(1, years);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static String getYearTime(long timeStamp1, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeStamp1 * 1000L);
		cal.add(1, years);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000L);
		return timeStamp;
	}

	public static int lateDays(long time) {
		Calendar c = Calendar.getInstance();
		Calendar nowTime = Calendar.getInstance();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(format.format(Long.valueOf(String.valueOf(time).length() < 13 ? time * 1000L : time)));
			c.setTime(date);
			long now = System.currentTimeMillis();
			Date date2 = format.parse(format.format(Long.valueOf(now)));
			nowTime.setTime(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return countDays(c, nowTime);
	}

	public static int lateDays(long time1, long time2) {
		Calendar c = Calendar.getInstance();
		Calendar nowTime = Calendar.getInstance();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(format.format(Long.valueOf(String.valueOf(time1).length() < 13 ? time1 * 1000L : time1)));
			c.setTime(date);

			Date date2 = format.parse(format.format(Long.valueOf(String.valueOf(time2).length() < 13 ? time2 * 1000L : time2)));
			nowTime.setTime(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return countDays(c, nowTime);
	}

	public static int countDays(Calendar c_b, Calendar c_e) {
		int days = 0;
		while (c_b.before(c_e)) {
			days++;
			c_b.add(6, 1);
		}
		return days;
	}

	public static String getOffsetMonthDate(Date protoDate, int monthOffset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		cal.add(2, -monthOffset);
		System.out.println(cal.get(2));
		Date date = cal.getTime();
		return new SimpleDateFormat("yyyy-MM").format(date);
	}

	public static String getLastMonth(int month) {
		return getYear() + "-" + strFormat(getCalendar().get(2) + 1 - month);
	}

	/**
	 * <p>
	 * Description:将秒（sec）转成date<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2013年12月24日
	 * @param sec
	 * @return Date
	 */
	public static Date fromSecToDate(long sec) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(sec);
		Date date = null;
		try {
			date = formatter.parse(formatter.format(calendar.getTime()));
		} catch (ParseException e) {
		}
		return date;
	}
	/**
	 * 
	 * <p>
	 * Description:将日期的字符串形式按格式转换成日期<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月7日
	 * @param str
	 * @return
	 * Date
	 */
    public static Date strToDate(String str){
    	DateFormat   format= new SimpleDateFormat("yyyy-MM-dd"); 
    	Date date = null;  
    	 try {
			date = format.parse(str);
		} catch (ParseException e) {
			 
			e.printStackTrace();
		}
    	 return date;
    }
    /**
	 * <p>
	 * Description:日期格式化 <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年5月21日
	 * @param dateParam
	 * @return
	 * String
	 */
	public static String formatDateYmd(Date dateParam) {
		String dateStr = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateStr = formatter.format(dateParam);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	/**
	 * <p>
	 * Description: 日期最后一秒 23:59:59<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月9日
	 * @param dateParam
	 * @return
	 * String
	 */
	public static String formatDateYmdLastSecond(Date dateParam) {
		String dateStr = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateStr = formatter.format(dateParam);
			dateStr = dateStr.concat(" 23:59:59");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	/**
	  * 日期格式化yyMMdd
	  * <p>
	  * Description:这里写描述<br />
	  * </p>
	  * @author 陈建国
	  * @version 0.1 2015年6月17日
	  * @param dateParam
	  * @return
	  * String
	  */
	public static String formatDateyyMMdd(Date dateParam) {
		String dateStr = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		try {
			dateStr = formatter.format(dateParam);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	
	public static String formatDateForCustody() {
		String dateStr = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		try {
			dateStr = formatter.format(now());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	
	public static Date now()
	{
		return (new Date());
	}

	/**
	 * <p>
	 * Description: 将日期加上某些天或减去天数 <br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月11日
	 * @param date
	 * @param day
	 * @return Date
	 */
	public static Date dateFormatReturnDate(Date dateParam, int day) {
		Date retDate = null;
		try {

			Calendar c = Calendar.getInstance();
			c.setTime(dateParam); // 设置当前日期
			c.add(Calendar.DAY_OF_MONTH, day); // -1天
			retDate = c.getTime(); // 结果

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retDate;

	}

	public static Date parseForYMD_DASH(String source) {

		if (source == null) {
			return null;
		}
		Date date;
		try {
			date = getFormat(DateUtils.YMD_DASH).parse(source);
			System.out.println(date);
		} catch (ParseException e) {
			e.getStackTrace();
			return null;
		}
		return date;
	}
	
	/**
	 * 
	 * <p>
	 * Description:日期格式化<br />
	 * </p>
	 * @author Shijin.Yang
	 * @version 0.1 2016年6月29日
	 * @param date
	 * @param pattern
	 * @return
	 * Date
	 */
	public static Date formateDate(Date date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
		try {
			date = parse(DateUtils.format(date, pattern), pattern);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
		return date;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(DateUtils.formatDateForCustody());
		
	}
}
