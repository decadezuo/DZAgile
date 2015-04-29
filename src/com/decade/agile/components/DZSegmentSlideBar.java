package com.decade.agile.components;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.decade.agile.R;
import com.decade.agile.kit.DZMoveBg;
import com.decade.framework.DZApplication;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-6-26
 */
public class DZSegmentSlideBar extends RelativeLayout implements OnClickListener{
	private List<String> _titles = new ArrayList<String>();
	private Context _context;
	private LinearLayout barLayout;
	private TextView slide_tv;
    private int slideIconId;
    private BarItemOnClickListener _listener;
    private int startX;//移动的起始位置
    private int itemWidth;
    private int slideIconWidth;
    
	/**
	 * @param context
	 * @param attrs
	 */
	public DZSegmentSlideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		_context = context;
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.SegmentSlideBar);
		init(a);
		a.recycle();
	}
	
	public void init(TypedArray typedArray){
		barLayout = new LinearLayout(_context);
		barLayout.setGravity(Gravity.CENTER);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		barLayout.setLayoutParams(params);
		addView(barLayout);
		slideIconId = typedArray.getResourceId(
				R.styleable.SegmentSlideBar_slideIcon, 0);
		Bitmap bitmap = BitmapFactory.decodeResource(_context.getResources(), slideIconId);
		if(bitmap != null){
			slideIconWidth = bitmap.getWidth();
		}
	}

	public void addTitle(String title){
		if(!TextUtils.isEmpty(title)){
			_titles.add(title);
		}
		itemWidth = DZApplication.getApp().getWorkSpaceWidth()/_titles.size();
		startX = itemWidth / 2 - slideIconWidth/2;
	}
    public void showBar(){
    	int titleSize = _titles.size();
    	for (int i = 0; i < titleSize; i++) {
			LinearLayout linear = new LinearLayout(_context);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
			param.setMargins(0, 15, 0, 15);
			param.weight =1;
			linear.setLayoutParams(param);
			linear.setGravity(Gravity.CENTER);
			
			TextView title_tv = new TextView(_context);
			title_tv.setText(_titles.get(i));
			title_tv.setTag(i);
			title_tv.setOnClickListener(this);
			linear.addView(title_tv);
			
			barLayout.addView(linear, i);
		}
    	slide_tv = new TextView(_context);
    	slide_tv.setBackgroundResource(slideIconId);
    	slide_tv.setTextColor(Color.WHITE);
    	if(titleSize > 1){
        	slide_tv.setText(_titles.get(0));
    	}
    	slide_tv.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		param.setMargins(startX, 0, 0, 0);
		addView(slide_tv, param);
    }

	public void setBarItemOnClickListener(BarItemOnClickListener listener){
		_listener = listener;
	}

	@Override
	public void onClick(View v) {
		int tag = (Integer)v.getTag();
		DZMoveBg.moveFrontBg(slide_tv, startX, itemWidth * tag, 0, 0);
		startX = itemWidth * tag;
		slide_tv.setText(_titles.get(tag % _titles.size()));
		_listener.itemOnClick(v, tag);
	}
	public interface BarItemOnClickListener{
		public void itemOnClick(View v,int index);
	}
}
