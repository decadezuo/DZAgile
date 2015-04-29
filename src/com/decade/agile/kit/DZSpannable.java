package com.decade.agile.kit;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;

/**
 * @description: 文字分块处理
 * @author: Decade
 * @date: 2013-9-16
 * 
 */
public class DZSpannable {

	public static SpannableString getClickableSpan(String text, int start, int end, int color, ClickableSpan clickable) {
		SpannableString span = new SpannableString(text);
		span.setSpan(clickable, start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		setForegroundColorSpan(span, start, end, color);
		return span;
	}
	
	public static SpannableString setForegroundColorSpan(String text, int start, int end, int color) {
		SpannableString span = new SpannableString(text);
		setForegroundColorSpan(span, start, end, color);
		return span;
	}
	
	private static void setForegroundColorSpan(SpannableString span,int start, int end, int color){
		span.setSpan(new ForegroundColorSpan(color), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

}
