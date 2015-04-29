package com.decade.agile.kit;

import android.text.method.ReplacementTransformationMethod;

/**
 * @description: 输入小写自动转换大写
 * @version: 1.0
 * @author: Decade
 * @date: 2015-2-6
 */
public class DZInputLowerToUpper extends ReplacementTransformationMethod {
	@Override
	protected char[] getOriginal() {
		char[] lower = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z' };
		return lower;
	}

	@Override
	protected char[] getReplacement() {
		char[] upper = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		return upper;
	}
}
