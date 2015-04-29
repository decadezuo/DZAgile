package com.decade.agile.kit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 字符串处理工具类
 * @author: Decade
 * @date: 2013-6-14
 * 
 */
public class DZStringUtils {

	/**
	 * 模仿C#格式化字符串,用法：fomat("我是{0}",decade)
	 * 
	 * @param str
	 *            需要格式化的字符串模板
	 * @param args
	 *            参数
	 * @return 格式化后的字符串
	 */
	public static String format(String str, String... args) {
		for (int i = 0; i < args.length; i++) {
			str = str.replaceFirst("\\{" + i + "\\}", args[i]);
		}
		return str;
	}

	/**
	 * 将给定的字符串截取，得到指定长度的字符串(解决了对中文字符截取一半的问题)。
	 * 
	 * @param source
	 *            给定的源字符串 如“伟大的领袖毛主席”
	 * @param subLength
	 *            截取的长度 如“10”
	 * @return 截取后的字符串 如“伟大的领袖...”
	 */
	public static String cut(String source, int subLength) {
		String newStr = trim(source);
		byte[] data = newStr.getBytes();
		if (data.length <= subLength) {
			return newStr;
		}
		byte[] strTemp = new byte[subLength];
		System.arraycopy(data, 0, strTemp, 0, subLength);
		data = strTemp;
		int count = 0;
		for (int i = 0; i < data.length && data[data.length - 1 - i] < 0; i++) {
			count++;
		}

		if (count % 2 == 1) {
			byte tmp2[] = new byte[data.length - 1];
			System.arraycopy(data, 0, tmp2, 0, data.length - 1);
			data = tmp2;
		}
		String retString = new String(data) + "...";
		return retString;
	}

	/**
	 * 将给定的字符串做去空处理。 如果给定字符串为null、"null"、"Null"等，返回空字符串。
	 * 如果给定字符串为" str"、"str "或" str "，则返回"str";
	 * 
	 * @param str
	 *            需要转换的字符串 如null,"null"。
	 * @return 处理后的字符串 如""。
	 */
	public static String trim(String str) {
		if (str == null) {
			return "";
		}
		// 比较str是否等于"null",忽略大小写。
		if (str.trim().equalsIgnoreCase("null")) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 只允许字母、数字、下划线
	 * 
	 * @param str
	 *            需要验证的字符串
	 * @return true:符合规则，false：不符合
	 */
	public static boolean filter(String str) {
		String newStr = trim(str);
		String regEx = "[0-9A-Za-z_]*";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(newStr);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否以0开头的数字
	 * 
	 * @param str
	 * @return true:符合规则，false：不符合
	 */
	public static boolean isStartZeroNumeric(String str) {
		String newStr = trim(str);
		Pattern pattern = Pattern.compile("^0\\d*$");
		Matcher isNum = pattern.matcher(newStr);
		if (isNum.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 车牌号校验
	 * 
	 * @param str
	 * @return true:符合规则，false：不符合
	 */
	public static boolean validateCarNo(String str) {
		String newStr = trim(str);
		Pattern pattern = Pattern
				.compile("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
		Matcher matcher = pattern.matcher(newStr);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 从短信内容获取6位纯数字验证码
	 * 
	 * @param smsBody
	 * @return
	 */
	public static String getVerifyCodeFromSms(String smsBody) {
		Pattern pattern = Pattern.compile("\\d{6}");
		Matcher matcher = pattern.matcher(smsBody);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/**
	 * 过滤字符串前面的零
	 * 
	 * @param str
	 * @return
	 */
	public static String filterFirstZero(String str) {
		if (!str.contains(".")) {
			return str.replaceFirst("^0*", "");
		}
		return str;
	}

	/**
	 * 把给定的可解析成整数的字符串转换成整数,如果为0长度的字符串，则返回0。
	 * 
	 * @param str
	 *            需要转换成格式的字符串，如"122  "。
	 * @return 得到相对应的整数 如122。
	 * @throws CommonException
	 *             如果给定字符串不是可解析的整数,如果发生异常，则返回0。
	 */
	public static int parseInt(String str) {
		String strTemp = "";
		int ret = 0;

		try {
			if (str == null) {
				System.out.println("你给定的参数不可以为null");
				return 0;
			} else {
				strTemp = trim(str);
				if (strTemp.equals("")) {
					System.out.println("你给定的参数不可以为空字符串");
					return 0;
				}
			}
			ret = Integer.parseInt(strTemp);
			return ret;
		} catch (Exception e) {
			System.out.println("你给定的参数，不能转换为数字");
			return 0;
		}

	}

	/**
	 * 判断该字符串是否全部由可解析成数字格式的字符组成。
	 * 
	 * @param validString
	 *            给定字符串
	 * @return boolean
	 */
	public static boolean isNumber(String validString) {
		byte tempbyte[] = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			if (tempbyte[i] < 48 || tempbyte[i] > 57) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验银行卡卡号
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId
				.substring(0, cardId.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * 
	 * @param nonCheckCodeCardId
	 * @return
	 */
	private static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null
				|| nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

}
