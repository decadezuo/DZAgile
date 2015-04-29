package com.decade.agile.kit;

import java.util.LinkedList;
import java.util.List;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 目的：用list给一个view设置不同的样式<br />
 * 包括：1. 字体的颜色和大小，2. 点击效果
 * @author Decade
 */
public class DZTextSpanParser {
	private List<TextBean> textList;

	public DZTextSpanParser() {
		textList = new LinkedList<TextBean>();
	}

	/**
	 * 添加文字
	 * */
	public DZTextSpanParser append(String text, int size, int color) {
		if (text == null) {
			return this;
		}
		TextBean bean = new TextBean();
		bean.text = text;
		bean.size = size;
		bean.color = color;
		textList.add(bean);

		return this;
	}

	/**
	 * 添加带链接的文字
	 * */
	public DZTextSpanParser append(String text, int size, int color,
			OnClickListener onClickListener) {
		if (text == null) {
			return this;
		}
		TextBean bean = new TextBean();
		bean.text = text;
		bean.size = size;
		bean.color = color;
		bean.onClickListener = onClickListener;
		textList.add(bean);

		return this;
	}

	/**
	 * 写入TextView
	 * */
	public void parse(TextView textView) {
		// 先将文字放在一起，传入到SpannableBuilder中
		// 后面做的是对文本进行修饰和替换
		StringBuilder sBuilder = new StringBuilder();
		for (TextBean bean : textList) {
			sBuilder.append(bean.text);
		}

		// 所有的文字和效果都要写在Spannable中，SpanableStringBuilder用于创建Spannable，
		// 其实它也是Spannable的一个实现类
		SpannableStringBuilder style = new SpannableStringBuilder(sBuilder);
		int position = 0;
		for (TextBean bean : textList) {
			if (bean.onClickListener != null) {
				// 如果有点击，则在上面添加点击处理的Span
				style.setSpan(new MyClickableSpan(bean.onClickListener),// Span接口用于实现对文本的修饰的具体内容
						position,// 修饰的起始位置
						position + bean.text.length(),// 修饰的结束位置
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			}
			// 字体上色，字体的背景颜色也可以单独改变
			style.setSpan(new ForegroundColorSpan(bean.color), position,
					position + bean.text.length(),
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			// 改变字体大小
			style.setSpan(new AbsoluteSizeSpan(bean.size), position, position
					+ bean.text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			/*
			 * 如上所示，Spannalbe接口是说这个类可以被改变，Span说的是这个类可以去改变别的类
			 * 整个改变样式做的就是用Span去改变Spannable中的内容。
			 */
			position += bean.text.length();
		}

		// 设置TextView让文字可以被点击
		textView.setMovementMethod(LinkMovementMethod.getInstance());
		textView.setText(style);
	}

	/**
	 * 用于记录文本的内容，字体，大小和监听器
	 * */
	private class TextBean {
		public OnClickListener onClickListener;
		public String text;
		public int size;
		public int color;
	}

	/**
	 * 用于更改文字点击的事件和效果
	 * */
	private static class MyClickableSpan extends ClickableSpan {
		private OnClickListener mOnClickListener;

		public MyClickableSpan(OnClickListener onClickListener) {
			mOnClickListener = onClickListener;
		}

		@Override
		public void onClick(View widget) {
			if (mOnClickListener != null) {
				mOnClickListener.onClick(widget);
			}
		}

		@Override
		public void updateDrawState(TextPaint ds) {
		}

	}

}
