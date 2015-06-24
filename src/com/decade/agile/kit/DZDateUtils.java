package com.decade.agile.kit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-31
 * 
 */
public class DZDateUtils {

	/**
	 * 返回格式为 yyyyMMddHHmmss 的时间
	 * 
	 * @return
	 */
	public static String getDate() {
		return getDate("yyyyMMddHHmmss");
	}

	/**
	 * 返回格式为 yyyy-MM-dd HH:mm:ss 的时间
	 * 
	 * @return
	 */
	public static String getDateLine() {
		return getDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 输入需要的时间格式，得到该格式的即时时间 如参数为"yyyyMMddHHmmss",则返回"20060401235959"。
	 * 如参数为"yyyy-M-d H:m:s"，则返回"2006-4-1 21:33:53"。
	 * 如参数为"zzzz,yyyy年MMM dd日 a hh:mm:ss  EEE"
	 * 则返回"中国标准时间,2004年四月 04日 上午 01:09:45  星期日"
	 * 注意：y为年，M为月，d为日。H为小时(24小时)，h为小时（12小时）m为分钟，s为秒。注意大小写。
	 * zzzz为时区，EEE为星期，MMMM为月份(大写)，a为上下午。
	 * 
	 * @param timeFormat
	 *            输入需要的时间格式 如参数为"yyyy-MM-dd HH:mm:ss
	 * @return 该格式的即时时间 如"2006-04-01 21:33:53"。
	 */
	public static String getDate(String timeFormat) {
		try {
			Date newTime = new Date(System.currentTimeMillis());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
			String newDate = simpleDateFormat.format(newTime);
			return newDate;
		} catch (Exception ex) {
			System.out.println("得到该格式的时间错误");
			return "";
		}
	}

	/**
	 * 输入时间字符串，原时间字符串格式，和需要得到的时间字符串格式,得到需要格式的时间字符串。
	 * 注意：y为年，M为月，d为日。H为小时(24小时)，h为小时（12小时）m为分钟，s为秒。注意大小写。
	 * zzzz为时区，EEE为星期，MMMM为月份(大写)，a为上下午。
	 * 
	 * @param datetime
	 *            需要转换格式的时间字符 如2006040123595。
	 * @param oldFormat
	 *            原时间字符串格式 如"yyyyMMddHHmmss"。注意，该格式必须与给定时间字符串的格式一致。
	 * @param newFormat
	 *            原时间字符串格式 如"yyyy-M-d H:m:s"。
	 * 
	 * @return 需要格式的时间字符串 如：2006-04-01 23:59:59
	 */
	public static String formatDate(String datetime, String oldFormat,
			String newFormat) {
		String dateStr = "";
		if (datetime == null || oldFormat == null || newFormat == null) {
			System.out.println("你输入的参数错误，请检查datetime,oldFormat,newFormat参数");
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(newFormat);
		Calendar calendar = getCalendar(datetime, oldFormat);
		if (calendar == null) {
			System.out.println("初始化时间错误");
			return null;
		}
		Date newDate = calendar.getTime();
		dateStr = simpleDateFormat.format(newDate);

		return dateStr;

	}

	private static Calendar getCalendar(String datetime, String dateFormat) {
		Calendar calendar = null;
		if (datetime == null || dateFormat == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		// 从给定时间字符串的第一位开始分析,返回Date类型
		Date newdate = simpleDateFormat.parse(datetime, new ParsePosition(0));
		if (newdate == null) {
			return null;
		}
		calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(newdate);
		return calendar;

	}

	/**
	 * 返回两个时间点之间的间隔描述，如：刚刚、1分钟前等。
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static String getDiff(long start, long end) {
		long l = end - start;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String text = "";
		text = "刚刚";
		if (day > 10) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date(start);
			text = sdf.format(d);
			return text;
		}
		if (day > 0) {
			text = day + "天前";
			return text;
		}
		if (hour > 0) {
			text = hour + "小时前";
			return text;
		}
		if (min > 0) {
			text = min + "分钟前";
			return text;
		}
		if (s > 0) {
			text = s + "秒前";
			return text;
		}
		return text;
	}

	/**
	 * 天数转化为毫秒
	 * 
	 * @param amount
	 *            具体天数
	 * @return 1天 = 1 * 24 * 60 * 60 * 1000
	 */
	public static long getDaysOfMSEL(int amount) {
		return amount * 24 * 60 * 60 * 1000;
	}

	/**
	 * 小时转化为毫秒
	 * 
	 * @param amount
	 *            具体天数
	 * @return 1小时 = 1 * 60 * 60 * 1000
	 */
	public static long getHoursOfMSEL(int amount) {
		return amount * 60 * 60 * 1000;
	}

	/**
	 * 分钟转化为毫秒
	 * 
	 * @param amount
	 *            具体天数
	 * @return 1分钟 = 1 * 60 * 1000
	 */
	public static long getMinuteOfMSEL(int amount) {
		return amount * 60 * 1000;
	}

}
