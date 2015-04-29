package com.decade.agile.components;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.decade.agile.R;

/**
 * @description: 滑动title
 * @author: Decade
 * @date: 2013-5-8
 */
public class DZTitleSlideViewPager extends LinearLayout {

	private TabClickListener _tabClickListener;
	private PagerTabStrip _pagerTitle;
	private Context _context;
	private ViewPager _viewPager;// 页卡内容

	public DZTitleSlideViewPager(Context context) {
		super(context);
		init(context);
	}

	public DZTitleSlideViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		_context = context;
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		initViewPager();
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		LinearLayout pagerLayout = (LinearLayout) LayoutInflater.from(_context)
				.inflate(R.layout.agile_title_slide_pager, null);
		_pagerTitle = (PagerTabStrip) pagerLayout
				.findViewById(R.id.slide_pager_title);
		_viewPager = (ViewPager) pagerLayout.findViewById(R.id.slide_viewpager);
		addView(pagerLayout);
		_viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	public void setAdapter(PagerAdapter adapter) {
		_viewPager.setAdapter(adapter);
	}

	public void setCurrentItem(int index) {
		_viewPager.setCurrentItem(index);
	}
	public PagerTabStrip getPagerTabStrip() {
		return _pagerTitle;
	}
	
	public ViewPager getViewPager() {
		return _viewPager;
	}

	/**
	 * 设置点击事件
	 * 
	 * @param onClickListener
	 */
	public void setTabClickListener(TabClickListener tabClickListener) {
		_tabClickListener = tabClickListener;
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		public void onPageSelected(int arg0) {
			if (_tabClickListener != null) {
				_tabClickListener.tabOnClick(arg0);
			}
		}
		
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageScrollStateChanged(int arg0) {
		}
	}

	public interface TabClickListener {
		public void tabOnClick(int index);
	}
}
