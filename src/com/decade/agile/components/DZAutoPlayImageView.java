package com.decade.agile.components;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.decade.agile.R;
import com.decade.agile.volley.DZVolleyHelper;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-8
 */
public class DZAutoPlayImageView extends FrameLayout {

	private TextView autoplay_title_tv;
	private Context _context;
	private ViewPager _viewPager;
	private DZPageIndicator autoplay_indicator;
	private static int _currentIndex; // 当前图片的索引号
	private int _index;
	private List<String> _titles = new ArrayList<String>();
	private static ImageOnClickListener _imageListener;

	// 切换当前显示的图片
	private Handler handler = new Handler();

	public DZAutoPlayImageView(Context context) {
		super(context);
		init(context);
	}

	public DZAutoPlayImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		_context = context;
		_currentIndex = 0;
		_index = 0;
		initViewPager();
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		FrameLayout pagerLayout = (FrameLayout) LayoutInflater.from(_context)
				.inflate(R.layout.agile_autoplay_imageview, null);
		autoplay_title_tv = (TextView) pagerLayout
				.findViewById(R.id.autoplay_title_tv);
		_viewPager = (ViewPager) pagerLayout
				.findViewById(R.id.autoplay_image_vp);
		autoplay_indicator = (DZPageIndicator) pagerLayout
				.findViewById(R.id.autoplay_indicator);
		addView(pagerLayout);
		_viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	public void setAdapter(PagerAdapter adapter) {
		_viewPager.setAdapter(adapter);
	}

	public void initTitles(List<String> titles) {
		if (!_titles.isEmpty()) {
			_titles.clear();
		}
		_titles.addAll(titles);
		if (_titles != null && !_titles.isEmpty()) {
			autoplay_title_tv.setText(_titles.get(_currentIndex));
		}
		autoplay_indicator.loadIndicator(_titles.size(), 0);

	}

	public void addTitle(String title) {
		if (!TextUtils.isEmpty(title)) {
			_titles.add(title);
		}
	}

	public void setCurrentItem(int index) {
		_viewPager.setCurrentItem(index);
	}

	public ViewPager getViewPager() {
		return _viewPager;
	}

	/**
	 * 设置指示点图标，此ResId是一个selector，参照 R.drawable.agile_selector_guide_indicator
	 * PS：此方法需在{@link #initTitles(List)}方法之前调用
	 * 
	 * @param imageResId
	 */
	public void setPageIndicatorImageResId(int imageResId) {
		autoplay_indicator.setPageIndicatorImageResId(imageResId);
	}

	/**
	 * 开始切换图片
	 */
	public void start() {
		handler.postAtTime(loopRunable, 3000);
	}

	/**
	 * 停止切换图片
	 */
	public void stop() {
		handler.removeCallbacks(loopRunable);
	}

	/**
	 * 广告图片轮循
	 */
	private Runnable loopRunable = new Runnable() {
		@Override
		public void run() {
			if (_index < _titles.size()) {
				switchImage();
				_index++;
				handler.postDelayed(this, 3000);
			} else {
				_index = 0;
				handler.post(this);
			}
		}
	};

	public void switchImage() {
		_viewPager.setCurrentItem(_index);// 切换当前显示的图片
	}

	/**
	 * 设置图片点击事件
	 * 
	 * @param onClickListener
	 */
	public void setImageClickListener(ImageOnClickListener imageListener) {
		_imageListener = imageListener;
	}

	public interface ImageOnClickListener {
		public void onClick(int index, View view);
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		public void onPageSelected(int arg0) {
			autoplay_indicator.change(arg0);
			autoplay_title_tv.setText(_titles.get(arg0));
			_currentIndex = arg0;
			_index = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageScrollStateChanged(int arg0) {
		}
	}

	public static class DCAutoPlayAdapter extends PagerAdapter {
		private List<View> _views;

		public DCAutoPlayAdapter(List<View> views) {
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

	}

	public static class DCAutoPlayImage extends ImageView {

		/**
		 * @param context
		 */
		public DCAutoPlayImage(Context context) {
			super(context);
		}

		public DCAutoPlayImage(Context context, String url, int defaultImage) {
			super(context);
			initImageView(context, url, defaultImage);
		}

		public void initImageView(Context context, String url, int defaultImage) {
			LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT);
			setLayoutParams(params);
			setScaleType(ScaleType.FIT_XY);
			ImageListener listener = ImageLoader.getImageListener(this,
					defaultImage, defaultImage);
			DZVolleyHelper.getInstance(context).getmImageLoader()
					.get(url, listener);
			setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (_imageListener != null) {
						_imageListener.onClick(_currentIndex, v);
					}
				}
			});
		}
	}
}
