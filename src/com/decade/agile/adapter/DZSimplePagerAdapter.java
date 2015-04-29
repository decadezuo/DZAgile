package com.decade.agile.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-5-8
 */
public class DZSimplePagerAdapter extends PagerAdapter {
	private List<String> _titles;
	private List<View> _views;

	public DZSimplePagerAdapter(List<String> title,List<View> views) {
		_titles = title;
		_views = views;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(_views.get(arg1));
	}

	@Override
	public int getCount() {
		return _views.size();
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {

		if (arg1 < getCount()) {
			((ViewPager) arg0).addView(_views.get(arg1 % getCount()), 0);
		}
		return _views.get(arg1 % getCount());
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return (_titles.size() > position) ? _titles.get(position) : "";
	}
	
}
