package com.decade.agile.kit;

public class DZFileUtils {
	public static String getFileNameFromUrl(String url) {
		return url.substring(url.lastIndexOf("/"), url.length());
	}
}
