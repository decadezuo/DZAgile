package com.decade.agile.kit;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * @description:
 * @author: Decade
 * @date: 2013-6-14
 * 
 */
public class DZMobileUtils {

	/**
	 * 获取手机CPU序列号
	 * 
	 * @return CPU序列号(16位) 读取失败为"0000000000000000"
	 */
	public static String getCPUSerial() {
		String str = "";
		String strCPU = "";
		String cpuAddress = "0000000000000000";
		try {
			// 读取CPU信息
			Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			// 查找CPU序列号
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					// 查找到序列号所在行
					if (str.indexOf("Serial") > -1) {
						// 提取序列号
						strCPU = str.substring(str.indexOf(":") + 1,
								str.length());
						// 去空格
						cpuAddress = strCPU.trim();
						break;
					}
				} else {
					// 文件结尾
					break;
				}
			}
		} catch (IOException ex) {
			// 赋予默认值
			ex.printStackTrace();
		}
		return cpuAddress;
	}

	/**
	 * 获取手机IMEI
	 * 
	 * @param context
	 * @return IMEI号 (15位)，读取失败时返回 "000000000000000";
	 */
	public static String getIMEI(Context context) {
		String imeiStr = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		if (TextUtils.isEmpty(imeiStr)) {
			imeiStr = "000000000000000";
		}
		return imeiStr;
	}

	/**
	 * 调用电话
	 * 
	 * @param context
	 * @param phone
	 */
	public static void callTell(Context context, String phone) {
		if (TextUtils.isEmpty(phone))
			return;
		Uri uri = Uri.parse("tel:" + phone);
		context.startActivity(new Intent(Intent.ACTION_DIAL, uri));

	}

	/**
	 * 发送短信
	 * 
	 * @param context
	 * @param phone
	 */
	public static void sendSmsBySystem(Context context, String phone,
			String body) {
		if (TextUtils.isEmpty(phone))
			return;
		Uri uri = Uri.parse("smsto:" + phone);
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra("sms_body", body);
		context.startActivity(intent);

	}

}
