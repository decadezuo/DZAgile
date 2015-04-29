package com.decade.agile.components;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.decade.agile.kit.DZHandlerFactory;

/**
 * 
 * @description: 开场动画Activity (From AndroidKit)
 * @author: Decade
 * @date: 2013-5-24
 * 
 */
public abstract class DZIntroActivity extends Activity {
	/**
	 * 后台任务完成的标志。
	 */
	private static final byte BACKGROUND_FINISH = 0x01;
	/**
	 * 前台任务完成的标志。
	 */
	private static final byte FRONTGROUND_FINISH = 0x10;
	/**
	 * 表示要播放开场动画。
	 */
	private static final int INTRO_PLAY = 0;
	/**
	 * 开场动画的资源。
	 */
	private List<IntroImgResource> mResources;
	/**
	 * 图片背景颜色。默认为白色。
	 */
	private int mBackgroundColor = 0xFFFFFFFF;
	/**
	 * UI线程。
	 */
	private Handler mUiHandler;
	/**
	 * 用来显示动画。
	 */
	private ImageView mIntroImage;
	/**
	 * 屏幕方向。
	 */
	private int mOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		runOnMainThread();
		this.setRequestedOrientation(mOrientation);
		this.setContentView(createLayout());
		setIntroResources(mResources);
		showIntro();
		startOnBackground();
	}

	private void init() {
		mResources = new ArrayList<IntroImgResource>();
		mUiHandler = new UIHandler(this);
	}

	/**
	 * 设置开场动画的图片资源。
	 * 
	 * @param resources
	 *            开场动画的图片资源。
	 */
	protected abstract void setIntroResources(List<IntroImgResource> resources);

	/**
	 * 返回下一个要启动的Activity。
	 * 
	 * @return 下一个要启动的Activity。
	 */
	protected abstract Class<?> nextActivity();

	/**
	 * 显示开场动画。
	 */
	protected void showIntro() {
		int delayTime = 0;
		for (final IntroImgResource resource : mResources) {
			Message msg = mUiHandler.obtainMessage(INTRO_PLAY);
			msg.obj = resource;
			mUiHandler.sendMessageDelayed(msg, delayTime);
			delayTime += resource.playerTime;
		}
		mUiHandler.sendEmptyMessageDelayed(FRONTGROUND_FINISH, delayTime);

	}

	/**
	 * 执行耗时的操作。
	 */
	private void startOnBackground() {
		DZHandlerFactory.newBackgroundHandler("intro_bg").post(new Runnable() {

			public void run() {
				runOnBackground();
				mUiHandler.sendEmptyMessage(0x1);
			}
		});
	}

	/**
	 * 创建启动时的界面Layout。
	 * 
	 * @return 返回创建的界面Layout.
	 */
	private View createLayout() {
		FrameLayout layout = new FrameLayout(this);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layout.setLayoutParams(layoutParams);
		layout.setBackgroundColor(getBackgroundColor());
		mIntroImage = new ImageView(this);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.FILL_PARENT,
				FrameLayout.LayoutParams.FILL_PARENT);
		params.gravity = Gravity.CENTER;
		layout.addView(mIntroImage, params);

		return layout;
	}

	/**
	 * 获取背景颜色。
	 * 
	 * @return 背景颜色。默认为白色。
	 */
	public int getBackgroundColor() {
		return mBackgroundColor;
	}

	/**
	 * 设置背景颜色。
	 * 
	 * @param backgroundColor
	 *            要设置的背景颜色。
	 */
	public void setBackgroundColor(int backgroundColor) {
		this.mBackgroundColor = backgroundColor;
	}

	/**
	 * 返回屏幕方向。
	 * 
	 * @return 屏幕方向，横屏或竖屏。
	 */
	public int getmOrientation() {
		return mOrientation;
	}

	/**
	 * 设置屏幕的方向。默认是竖屏。
	 * 
	 * @param mOrientation
	 *            屏幕方向。ActivityInfo.SCREEN_ORIENTATION_PORTRAIT或者是ActivityInfo.
	 *            SCREEN_ORIENTATION_LANDSCAPE。
	 */
	public void setmOrientation(int mOrientation) {
		this.mOrientation = mOrientation;
	}

	/**
	 * 在前台中执行的代码。如需对界面进行横屏的重新设置，请此在执行setmOrientation()方法。
	 */
	protected void runOnMainThread() {
	}

	/**
	 * 在后台中执行的代码。在此进行比较耗时的操作。
	 */
	protected void runOnBackground() {
	}

	protected static class UIHandler extends Handler {
		/**
		 * 是否需要等待。
		 */
		private int isWaiting = 0;
		private WeakReference<DZIntroActivity> activity;

		public UIHandler(DZIntroActivity activity) {
			this.activity = new WeakReference<DZIntroActivity>(activity);
		}

		public void handleMessage(android.os.Message msg) {
			if (msg.what == INTRO_PLAY) {
				IntroImgResource resource = (IntroImgResource) msg.obj;
				AlphaAnimation animation = new AlphaAnimation(
						resource.startAlpha, 1f);
				animation.setDuration(resource.playerTime);
				DZIntroActivity intro = activity.get();
				if (intro != null) {
					if (resource.isExpand) {
						intro.mIntroImage.setScaleType(ScaleType.FIT_XY);
					} else {
						intro.mIntroImage.setScaleType(ScaleType.CENTER);
					}
					intro.mIntroImage.setImageResource(resource.mResId);
					intro.mIntroImage.startAnimation(animation);
				}
				return;
			}

			if (msg.what == BACKGROUND_FINISH || msg.what == FRONTGROUND_FINISH) {

				isWaiting |= msg.what;
				// 当后台或前台的任务未完成时，不执行Activity的跳转。
				if (isWaiting == (BACKGROUND_FINISH | FRONTGROUND_FINISH)) {
					DZIntroActivity intro = activity.get();
					if (intro != null) {
						intro.startActivity(new Intent(intro, intro
								.nextActivity()));
						intro.finish();
					}
				}
			}
		};
	};

	/**
	 * 开场动画的图片资源类。封装了图片、播放时间、开始时的透明程度。
	 * 
	 * @author msdx
	 * 
	 */
	protected class IntroImgResource implements Serializable {
		/**
		 * 序列化ID。
		 */
		private static final long serialVersionUID = -2257252088641281804L;
		/**
		 * 资源图片ID.
		 */
		private int mResId;
		/**
		 * 播放时间，单位为毫秒。
		 */
		private int playerTime;
		/**
		 * 开始时的透明程度。0-1之间。
		 */
		private float startAlpha;

		/**
		 * 图片是否扩展。
		 */
		private boolean isExpand;

		/**
		 * 开场动画资源的构造方法。
		 * 
		 * @param mResId
		 *            图片资源的ID。
		 * @param playerTime
		 *            图片资源的播放时间，单位为毫秒。。
		 * @param startAlpha
		 *            图片资源开始时的透明程度。0-255之间。
		 */
		public IntroImgResource(int mResId, int playerTime, float startAlpha,
				boolean isExpand) {
			super();
			this.mResId = mResId;
			this.playerTime = playerTime;
			this.startAlpha = startAlpha;
			this.isExpand = isExpand;
		}

		/**
		 * 获取资源图片ID。
		 * 
		 * @return 资源图片ID。
		 */
		public int getmResId() {
			return mResId;
		}

		/**
		 * 设置资源图片ID.
		 * 
		 * @param mResId
		 *            要设置的资源图片ID.
		 */
		public void setmResId(int mResId) {
			this.mResId = mResId;
		}

		/**
		 * 返回资源图片的播放时间。
		 * 
		 * @return 资源图片的播放时间。
		 */
		public int getPlayerTime() {
			return playerTime;
		}

		/**
		 * 设置资源图片的播放时间。
		 * 
		 * @param playerTime
		 *            资源图片的播放时间。
		 */
		public void setPlayerTime(int playerTime) {
			this.playerTime = playerTime;
		}

		/**
		 * 得到资源开始时的透明程度。
		 * 
		 * @return
		 */
		public float getStartAlpha() {
			return startAlpha;
		}

		/**
		 * 设置资源开始时的透明程度。
		 * 
		 * @param startAlpha
		 */
		public void setStartAlpha(float startAlpha) {
			this.startAlpha = startAlpha;
		}

		/**
		 * 返回图片是否设置扩展。
		 * 
		 * @return
		 */
		public boolean isExpand() {
			return isExpand;
		}

		/**
		 * 设置图片是否扩展。
		 * 
		 * @param isExpand
		 *            如果为true，则图片会被拉伸至全屏幕大小进行展示，否则按原大小展示。
		 */
		public void setExpand(boolean isExpand) {
			this.isExpand = isExpand;
		}
	}

}
