package com.decade.agile.components;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.decade.agile.R;
import com.decade.agile.components.DZLazyViewPager.OnPageChangeListener;
import com.decade.framework.DZApplication;

/**
 * @author Decade
 * 
 */
public class DZTitleStillViewPager extends LinearLayout implements
		OnClickListener {
	private List<String> tabTexts;
	private TabClickListener mTabClickListener = null;
	private LayoutParams params;
	private LayoutParams tabNameTextParams;
	private RelativeLayout.LayoutParams tabMsgCountTextParams;
	private Context mContext;
	private DZLazyViewPager mPager;// 页卡内容
	private ImageView cursor;// 动画图片
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private int screenWidth;
	private LinearLayout mrootLayout;
	private LinearLayout tabLayout;
	private TextView[] tabTv;
	private TextView[] tabMsgCountTv;
	private int tabTvWidth;
	private LinearLayout subView;
	private int tabCount;
	private int titleSlectedColor = Color.RED;
	private int titleNormalColor = Color.BLACK;

	private float titleSlectedSize = 16f;
	private float titleNormalSize = 15f;

	private int indicateImg = R.drawable.agile_tab_cursor_image;
	public DZSyncHorizontalScrollView mHorizontalScrollView;// 上面的水平滚动控件
	private ProgressBar progressBar;
	private int progressDrawable = R.drawable.agile_tab_progress_layer;
	private int _leftBgResId;
	private int _centerBgResId;
	private int _rightBgResId;
	public ImageView tab_left_arrows_iv;
	public ImageView tab_right_arrows_iv;
	public int _tabTextPaddingTopBottom = 10;

	public interface TabClickListener {
		public void tabOnClick(int index);
	}

	/**
	 * 布局
	 */
	public void initView() {
		tabLayout = (LinearLayout) mrootLayout.findViewById(R.id.tablayout);
		int count = tabTexts.size();
		tabTvWidth = screenWidth / tabCount;
		params = new LayoutParams(tabTvWidth, LayoutParams.WRAP_CONTENT);
		tabNameTextParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		tabMsgCountTextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				RelativeLayout.TRUE);
		tabMsgCountTextParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,
				RelativeLayout.TRUE);
		tabMsgCountTv = new TextView[count];
		tabTv = new TextView[count];
		for (int i = 0; i < count; i++) {
			RelativeLayout tabRelativeLayout = new RelativeLayout(mContext);
			tabTv[i] = new TextView(mContext);
			tabMsgCountTv[i] = new TextView(mContext);
			tabTv[i].setGravity(Gravity.CENTER);
			tabTv[i].setOnClickListener(this);
			tabTv[i].setText(tabTexts.get(i));
			tabTv[i].setTag(i);
			tabTv[i].setPadding(0, _tabTextPaddingTopBottom, 0,
					_tabTextPaddingTopBottom);
			tabTv[i].setTextColor(titleNormalColor);
			if (i == 0)// 左边的按钮
			{
				tabTv[i].setBackgroundResource(_leftBgResId);
			} else if (i != 0 && i < (count - 1))// 右边的按钮
			{
				tabTv[i].setBackgroundResource(_rightBgResId);

			} else// 中间的按钮
			{
				tabTv[i].setBackgroundResource(_centerBgResId);
			}
			// tabMsgCountTv[i].setBackgroundResource(R.drawable.tab_unread_bg);
			tabMsgCountTv[i].setGravity(Gravity.CENTER);
			tabMsgCountTv[i].setTextColor(Color.WHITE);
			tabMsgCountTv[i].setTextSize(10);
			tabMsgCountTv[i].setVisibility(View.GONE);
			tabMsgCountTv[i].setLayoutParams(tabMsgCountTextParams);
			tabRelativeLayout.addView(tabTv[i], tabNameTextParams);
			tabRelativeLayout.addView(tabMsgCountTv[i]);
			tabLayout.addView(tabRelativeLayout, i, params);
		}
		addView(mrootLayout);
	}

	public DZTitleStillViewPager(Context context) {
		super(context);
		mContext = context;
	}

	public DZTitleStillViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		screenWidth = DZApplication.getApp().getWorkSpaceWidth();// 获取分辨率宽度
		initRootView();
	}

	private void initRootView() {
		LayoutInflater mInflater = LayoutInflater.from(mContext);
		mrootLayout = (LinearLayout) mInflater.inflate(
				R.layout.agile_title_still_pager, null);
		subView = (LinearLayout) mrootLayout.findViewById(R.id.subview);
		progressBar = (ProgressBar) mrootLayout.findViewById(R.id.progressBar);
		progressBar.setProgressDrawable(mContext.getResources().getDrawable(
				progressDrawable));
		mHorizontalScrollView = (DZSyncHorizontalScrollView) mrootLayout
				.findViewById(R.id.tab_scrollview);
		LinearLayout tab_nav_layout = (LinearLayout) mrootLayout
				.findViewById(R.id.tab_nav_layout);
		tab_left_arrows_iv = (ImageView) mrootLayout
				.findViewById(R.id.tab_left_arrows_iv);
		tab_right_arrows_iv = (ImageView) mrootLayout
				.findViewById(R.id.tab_right_arrows_iv);
		mHorizontalScrollView.setSomeParam(tab_nav_layout, tab_left_arrows_iv,
				tab_right_arrows_iv, (Activity) mContext);
		/*
		 * mHorizontalScrollView.setOnTouchListener(new OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) { return
		 * true; } });
		 */
		tabMsgCountTextParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	public void setTabButtonName(int index, String name) {
		tabTv[index].setText(name);
	}

	/**
	 * 此方法必须放在setAdapter方法之前调用。
	 */
	public void setTabMsgCountTextRightMargin(int px) {
		tabMsgCountTextParams.rightMargin = px;
	}

	public void setTabMsgCountText(int index, String count) {
		tabMsgCountTv[index].setText(count);
		tabMsgCountTv[index].setVisibility(View.VISIBLE);
	}

	public void setTabMsgCountTextVisibility(int index, int visible) {
		tabMsgCountTv[index].setVisibility(visible);
	}

	public void setTabMsgCountTextAllGone() {
		int length = tabMsgCountTv.length;
		for (int i = 0; i < length; i++) {
			tabMsgCountTv[i].setVisibility(View.GONE);
		}
	}

	/**
	 * 初始化动画
	 */
	private void initImageView() {
		cursor = (ImageView) mrootLayout.findViewById(R.id.cursor);
		cursor.setImageResource(indicateImg);
		bmpW = BitmapFactory.decodeResource(getResources(), indicateImg)
				.getWidth();// 获取图片宽度
		int count = tabTexts.size();
		if (count < 3) {
			tabCount = 2;
		} else if (count == 3) {
			tabCount = 3;
		} else if (count == 4) {
			tabCount = 4;
		} else if (count > 4) {
			tabCount = 3;
		}
		offset = (screenWidth / tabCount - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	/**
	 * 必须放在{@link #setData(List)}方法之hou调用
	 * 
	 * @param visibility
	 */
	public void setCursorVisibility(int visibility) {
		cursor.setVisibility(visibility);
	}

	/**
	 * 必须放在{@link #setData(List)}方法之hou调用
	 * 
	 * @param visibility
	 */
	public void setProgressBarVisibility(int visibility) {
		progressBar.setVisibility(visibility);
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		mPager = (DZLazyViewPager) mrootLayout.findViewById(R.id.vPager);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * 设置数据
	 * 
	 * @param adpater
	 */
	public void setData(List<String> tabName) {
		tabTexts = tabName;
		initImageView();
		initViewPager();
		initView();
	}
	
	
	public DZLazyViewPager getPager(){
		return mPager;
	}

	/**
	 * 必须放在{@link #setData(List)}方法之前调用
	 * 
	 * @param titleSlectedColor
	 */
	public void setTitleColor(int titleNormalColor, int titleSlectedColor) {
		this.titleNormalColor = titleNormalColor;
		this.titleSlectedColor = titleSlectedColor;

	}

	/**
	 * 必须放在{@link #setData(List)}方法之前调用
	 * 
	 * @param titleSlectedColor
	 */
	public void setIndicateImg(int indicateImg) {
		this.indicateImg = indicateImg;
	}

	/**
	 * 必须放在{@link #setData(List)}方法之前调用
	 * 
	 * @param tabTextPaddingTopBottom
	 */
	public void setTabTextPaddingTopBottom(int tabTextPaddingTopBottom) {
		_tabTextPaddingTopBottom = tabTextPaddingTopBottom;
	}

	/**
	 * 必须放在{@link #setData(List)}方法之前调用
	 * 
	 * @param titleNormalSize
	 * @param titleSlectedSize
	 */
	public void setTitleSize(float titleNormalSize, float titleSlectedSize) {
		this.titleNormalSize = titleNormalSize;
		this.titleSlectedSize = titleSlectedSize;
	}

	/**
	 * 必须放在{@link #setData(List)}方法之前调用
	 * 
	 * @param leftBgResId
	 * @param centerBgResId
	 * @param rightBgResId
	 */
	public void setTitleBackgroundResource(int leftBgResId, int centerBgResId,
			int rightBgResId) {
		_leftBgResId = leftBgResId;
		_centerBgResId = centerBgResId;
		_rightBgResId = rightBgResId;
	}

	public void setProgressDrawable(int d) {
		progressDrawable = d;
		progressBar.setProgressDrawable(mContext.getResources().getDrawable(
				progressDrawable));
	}

	public void setSubView(View sView) {
		subView.removeAllViews();
		subView.addView(sView);
	}

	/**
	 * 
	 * @param visibility
	 */
	public void setPromptViewVisibility(int visibility) {
		subView.setVisibility(visibility);
	}

	public void setPromptViewOnClickListener(OnClickListener l) {
		subView.setOnClickListener(l);
	}

	public void setAdapter(PagerAdapter mPagerAdapter) {
		mPager.setAdapter(mPagerAdapter);
	}

	public void setCurrentItem(int index) {
		mPager.setCurrentItem(index);
		tabSelected(index);
	}

	public void setOffscreenPageLimit(int limit) {
		mPager.setOffscreenPageLimit(limit);
	}

	/**
	 * 设置点击事件
	 * 
	 * @param onClickListener
	 */
	public void setTabClickListener(TabClickListener tabClickListener) {
		this.mTabClickListener = tabClickListener;
	}

	public void setBackgroundColor(int color) {
		tabLayout.setBackgroundColor(color);
	}

	public void setBackgroundResource(int resid) {
		tabLayout.setBackgroundResource(resid);
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量

		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(currIndex * one, arg0
					* one, 0, 0);
			tabSelected(arg0);
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
			int scrollX = tabTvWidth * arg0 - tabTvWidth * 1;
			mHorizontalScrollView.smoothScrollTo(scrollX, 0);
			if (mTabClickListener != null) {
				mTabClickListener.tabOnClick(arg0);
			}
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageScrollStateChanged(int arg0) {
		}
	}

	private void tabSelected(int index) {
		int length = tabTv.length;
		for (int i = 0; i < length; i++) {
			if (i == index) {
				tabTv[i].setTextColor(titleSlectedColor);
				tabTv[i].setTextSize(titleSlectedSize);
			} else {
				tabTv[i].setTextColor(titleNormalColor);
				tabTv[i].setTextSize(titleNormalSize);
			}
		}
	}

	@Override
	public void onClick(View v) {
		int index = (Integer) v.getTag();
		mPager.setCurrentItem(index);
	}

	public int getCurrentIndex() {
		return currIndex;
	}
}