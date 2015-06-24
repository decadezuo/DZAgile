package com.decade.agile.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.decade.agile.pulltorefresh.PullRefreshAndSwipeListView;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class DZSwipeDismissListView extends ListView {
	/**
	 * 认为是用户滑动的最小距离
	 */
	private int mSlop;
	/**
	 * 滑动的最小速度
	 */
	private int mMinFlingVelocity;
	/**
	 * 滑动的最大速度
	 */
	private int mMaxFlingVelocity;
	/**
	 * 执行动画的时间
	 */
	protected long mAnimationTime = 200;
	/**
	 * 用来标记用户是否正在滑动中
	 */
	private boolean mSwiping;
	/**
	 * 滑动速度检测类
	 */
	private VelocityTracker mVelocityTracker;
	/**
	 * 手指按下的position
	 */
	private int mDownPosition;
	/**
	 * 按下的item对应的View
	 */
	private View mDownView;
	private float mDownX;
	private float mDownY;
	private boolean mAddedLvFooter = false;

	/**
	 * item的宽度
	 */
	private int mViewWidth;
	/**
	 * 当ListView的Item滑出界面回调的接口
	 */
	private OnDismissCallback onDismissCallback;
	private PullRefreshAndSwipeListView _pullView;

	/**
	 * 设置动画时间
	 * 
	 * @param mAnimationTime
	 */
	public void setmAnimationTime(long mAnimationTime) {
		this.mAnimationTime = mAnimationTime;
	}

	/**
	 * 设置删除回调接口
	 * 
	 * @param onDismissCallback
	 */
	public void setOnDismissCallback(OnDismissCallback onDismissCallback) {
		this.onDismissCallback = onDismissCallback;
	}

	public DZSwipeDismissListView(Context context) {
		this(context, null);
	}

	public DZSwipeDismissListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DZSwipeDismissListView(Context context, AttributeSet attrs,
			PullRefreshAndSwipeListView pullView) {
		this(context, attrs, 0);
		_pullView = pullView;
	}

	public DZSwipeDismissListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		ViewConfiguration vc = ViewConfiguration.get(context);
		mSlop = vc.getScaledTouchSlop();
		mMinFlingVelocity = vc.getScaledMinimumFlingVelocity() * 8; // 获取滑动的最小速度
		mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity(); // 获取滑动的最大速度
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			handleActionDown(ev);
			break;
		case MotionEvent.ACTION_MOVE:
			return handleActionMove(ev);
		case MotionEvent.ACTION_UP:
			handleActionUp(ev);
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 按下事件处理
	 * 
	 * @param ev
	 * @return
	 */
	private void handleActionDown(MotionEvent ev) {
		mDownX = ev.getX();
		mDownY = ev.getY();

		mDownPosition = pointToPosition((int) mDownX, (int) mDownY);

		if (mDownPosition == AdapterView.INVALID_POSITION) {
			return;
		}

		mDownView = getChildAt(mDownPosition - getFirstVisiblePosition());

		if (mDownView != null) {
			mViewWidth = mDownView.getWidth();
		}

		// 加入速度检测
		mVelocityTracker = VelocityTracker.obtain();
		mVelocityTracker.addMovement(ev);
	}

	/**
	 * 处理手指滑动的方法
	 * 
	 * @param ev
	 * @return
	 */
	private boolean handleActionMove(MotionEvent ev) {
		if (mVelocityTracker == null || mDownView == null) {
			return super.onTouchEvent(ev);
		}

		float deltaX = ev.getX() - mDownX;
		float deltaY = ev.getY() - mDownY;

		// X方向滑动的距离大于mSlop并且Y方向滑动的距离小于mSlop，表示可以滑动
		if (Math.abs(deltaX) > mSlop && Math.abs(deltaY) < mSlop) {
			mSwiping = true;

			// 当手指滑动item,取消item的点击事件，不然我们滑动Item也伴随着item点击事件的发生
			MotionEvent cancelEvent = MotionEvent.obtain(ev);
			cancelEvent
					.setAction(MotionEvent.ACTION_CANCEL
							| (ev.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
			onTouchEvent(cancelEvent);
		}

		if (mSwiping) {
			// 跟谁手指移动item
			ViewHelper.setTranslationX(mDownView, deltaX);
			// 透明度渐变
			ViewHelper.setAlpha(
					mDownView,
					Math.max(
							0f,
							Math.min(1f, 1f - 2f * Math.abs(deltaX)
									/ mViewWidth)));

			// 手指滑动的时候,返回true，表示SwipeDismissListView自己处理onTouchEvent,其他的就交给父类来处理
			return true;
		}

		return super.onTouchEvent(ev);

	}

	/**
	 * 手指抬起的事件处理
	 * 
	 * @param ev
	 */
	private void handleActionUp(MotionEvent ev) {
		if (mVelocityTracker == null || mDownView == null || !mSwiping) {
			return;
		}

		float deltaX = ev.getX() - mDownX;

		// 通过滑动的距离计算出X,Y方向的速度
		mVelocityTracker.computeCurrentVelocity(1000);
		float velocityX = Math.abs(mVelocityTracker.getXVelocity());
		float velocityY = Math.abs(mVelocityTracker.getYVelocity());

		boolean dismiss = false; // item是否要滑出屏幕
//		boolean dismissRight = false;// 是否往右边删除

		// 当拖动item的距离大于item的一半，item滑出屏幕
		if (Math.abs(deltaX) > mViewWidth / 2) {
			dismiss = true;
//			dismissRight = deltaX > 0;

			// 手指在屏幕滑动的速度在某个范围内，也使得item滑出屏幕
		} else if (mMinFlingVelocity <= velocityX
				&& velocityX <= mMaxFlingVelocity && velocityY < velocityX) {
			dismiss = true;
//			dismissRight = mVelocityTracker.getXVelocity() > 0;
		}

		if (dismiss && onDismissCallback != null) {
	/*		ViewPropertyAnimator.animate(mDownView)
					.translationX(dismissRight ? mViewWidth : -mViewWidth)
					// X轴方向的移动距离
					.alpha(0).setDuration(mAnimationTime)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							// Item滑出界面之后执行删除
							if (onDismissCallback != null) {
								onDismissCallback.onDismiss(mDownPosition);
							}
						}
					});*/
			
				onDismissCallback.onDismiss(mDownPosition);
			
		} else {
			// 将item滑动至开始位置
			ViewPropertyAnimator.animate(mDownView).translationX(0).alpha(1)
					.setDuration(mAnimationTime).setListener(null);
		}

		// 移除速度检测
		if (mVelocityTracker != null) {
			mVelocityTracker.recycle();
			mVelocityTracker = null;
		}

		mSwiping = false;
	}

	public void recoverItemAnimator() {
		ViewPropertyAnimator.animate(mDownView).translationX(0).alpha(1)
				.setDuration(mAnimationTime).setListener(null);
	}

	public void doItemAnimator(AnimatorListenerAdapter listenerAdapter) {
		if (mDownView != null) {
			final ViewGroup.LayoutParams lp = mDownView.getLayoutParams();// 获取item的布局参数
			final int originalHeight = mDownView.getHeight();// item的高度
			ValueAnimator animator = ValueAnimator.ofInt(originalHeight, 0)
					.setDuration(mAnimationTime);
			animator.start();
			animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator valueAnimator) {
					// 这段代码的效果是ListView删除某item之后，其他的item向上滑动的效果
					lp.height = (Integer) valueAnimator.getAnimatedValue();
					mDownView.setLayoutParams(lp);
				}
			});
			animator.addListener(listenerAdapter);
		}else {
			listenerAdapter.onAnimationEnd(null);
		}
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// Add the Footer View at the last possible moment
		if (null != _pullView.mLvFooterLoadingFrame && !mAddedLvFooter) {
			addFooterView(_pullView.mLvFooterLoadingFrame, null, false);
			mAddedLvFooter = true;
		}

		super.setAdapter(adapter);
	}

	/**
	 * 删除的回调接口
	 */
	public interface OnDismissCallback {
		public void onDismiss(int dismissPosition);
	}
}
