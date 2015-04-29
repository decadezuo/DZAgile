package com.decade.agile.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.decade.agile.R;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-16
 */
public class DZPageIndicator extends LinearLayout {
	private Context _context;
	private ImageView[] _images;
	private int _resId;

	public DZPageIndicator(Context context) {
		super(context);
		_context = context;
	}

	public DZPageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		_context = context;
		_resId = R.drawable.agile_selector_guide_indicator;
	}

	/**
	 * 设置指示点图标，此ResId是一个selector，参照 R.drawable.agile_selector_guide_indicator
	 * 
	 * @param imageResId
	 */
	public void setPageIndicatorImageResId(int imageResId) {
		_resId = imageResId;
	}

	public void loadIndicator(int size, int position) {
		_images = new ImageView[size];
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.leftMargin = 10;
		for (int i = 0; i < _images.length; i++) {
			_images[i] = new ImageView(_context);
			_images[i].setImageResource(_resId);
			addView(_images[i], params);
		}
		change(position);
	}

	public void change(int position) {
		for (int i = 0; i < _images.length; i++) {
			_images[i].setSelected(position == i);
		}

	}
}
