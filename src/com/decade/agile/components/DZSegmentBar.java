package com.decade.agile.components;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.decade.agile.R;

public class DZSegmentBar extends LinearLayout implements OnClickListener {
	private String[] stringArray;
	private Context mContext;
	private int leftResId; 
	private int centerResId; 
	private int rightResId; 
	private int indicateResId; 

	public DZSegmentBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.SegmentBar);
		init(a);
		a.recycle();
	}

	public DZSegmentBar(Context context) {
		super(context);
		mContext = context;
	}

	private void init(TypedArray typedArray) {
		indicateResId = typedArray.getResourceId(
				R.styleable.SegmentBar_indicateIcon, 0);
		 leftResId = typedArray.getResourceId(
				R.styleable.SegmentBar_leftBackground, 0);
		 centerResId = typedArray.getResourceId(
				R.styleable.SegmentBar_centerBackground, 0);
		 rightResId = typedArray.getResourceId(
				R.styleable.SegmentBar_rightBackground, 0);
		int itemResId = typedArray.getResourceId(
					R.styleable.SegmentBar_itemBackground, 0);
		if(itemResId != 0){
			leftResId = itemResId;
			centerResId = itemResId;
			rightResId = itemResId;
		}
	}
	
	/**
	 * determine the number of segment bar items by the string array.
	 * 
	 * 根据传递进来的数组，决定分段的数量
	 * 
	 * 
	 * */
	public void setValue(Context context, String[] stringArray) {
		if (stringArray.length > 1) {
			this.stringArray = stringArray;
			resolveStringArray(context);
		}
	}

	/**
	 * resolve the array and generate the items.
	 * 
	 * 对数组进行解析，生成具体的每个分段
	 * 
	 * */
	private void resolveStringArray(Context context) {
		int length = this.stringArray.length;
		for (int i = 0; i < length; i++) {
			Button button = new Button(context);
			button.setText(stringArray[i]);
			button.setGravity(Gravity.CENTER);
			// button.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.icon_arraw_bottom),
			// null, null, null);
			button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 1));
			button.setTag(i);
			button.setOnClickListener(this);

			if (i == 0)// 左边的按钮
			{
				button.setBackgroundResource(leftResId);
			} else if (i != 0 && i < (length - 1))// 右边的按钮
			{
				button.setBackgroundResource(rightResId);

			} else// 中间的按钮
			{
				button.setBackgroundResource(centerResId);

			}

			this.addView(button);
		}
	}

	private int lastIndex = 0;// 记录上一次点击的索引

	public void onClick(View v) {
		int index = (Integer) v.getTag();
		onSegmentBarChangedListener.onBarItemChanged(index);

		Button lastButton = (Button) this.getChildAt(lastIndex);
		lastButton.setSelected(false);
		lastButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
				null);// android:drawableTop

		Button currButton = (Button) this.getChildAt(index);
		currButton.setSelected(true);
		try {
			if(indicateResId != 0){
				currButton.setCompoundDrawablesWithIntrinsicBounds(mContext
						.getResources().getDrawable(indicateResId),
						null, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	

		lastIndex = index;
	}

	/**
	 * set the default bar item of selected
	 * 
	 * 设置默认选中的SegmentBar
	 * 
	 * */
	public void setDefaultBarItem(int index) {
		if (index > stringArray.length) {
			return;
		}
		lastIndex = index;
		Button btn = (Button) this.getChildAt(index);
		btn.setSelected(true);
		try {
			if(indicateResId != 0){
			btn.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources()
					.getDrawable(indicateResId), null, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		if (onSegmentBarChangedListener != null) {
			onSegmentBarChangedListener.onBarItemChanged(index);
		}
	}

	/**
	 * set the text size of every item
	 * 
	 * 设置文字的大小
	 * 
	 * */
	public void setTextSize(float sizeValue) {
		int index = this.getChildCount();
		for (int i = 0; i < index; i++) {
			((Button) this.getChildAt(i)).setTextSize(sizeValue);
		}
	}

	/**
	 * set the text color of every item
	 * 
	 * 设置文字的颜色
	 * 
	 * */
	public void setTextColor(int color) {
		int index = this.getChildCount();
		for (int i = 0; i < index; i++) {
			((Button) this.getChildAt(i)).setTextColor(color);
		}
	}

	private OnSegmentBarChangedListener onSegmentBarChangedListener;

	/**
	 * define a interface used for listen the change event of Segment bar
	 * 
	 * 定义一个接口，用来实现分段控件Item的监听
	 * 
	 * */
	public interface OnSegmentBarChangedListener {
		public void onBarItemChanged(int segmentItemIndex);
	}

	/**
	 * set the segment bar item changed listener,if the bar item changed, the
	 * method onBarItemChanged()will be called.
	 * 
	 * 设置分段控件的监听器，当分段改变的时候，onBarItemChanged()会被调用
	 * 
	 * */
	public void setOnSegmentBarChangedListener(
			OnSegmentBarChangedListener onSegmentBarChangedListener) {
		this.onSegmentBarChangedListener = onSegmentBarChangedListener;
	}
}
