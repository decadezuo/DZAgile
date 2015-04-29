package com.decade.agile;

import android.content.Intent;
import android.view.ViewGroup;

import com.decade.agile.components.DZCommonTopView;
import com.decade.agile.components.DZRectToast;
import com.decade.agile.components.DZRectToast.ToastTheme;
import com.decade.framework.DZActivity;
import com.decade.framework.DZiBottomView;
import com.decade.framework.DZiTopView;
import com.decade.framework.async.DZiAsyncTaskCallback;
import com.decade.framework.async.DZiResponse;

/**
 * @description: 项目activity 基类
 * @author: Decade
 * @date: 2013-5-20
 */
public class DZAgileActivity extends DZActivity implements DZiAsyncTaskCallback {

	@Override
	public void onJsonPaserError(String message, int requestCode) {
		DZRectToast.showToastShort(this, "数据解析异常!", ToastTheme.ERROR);

	}

	@Override
	public void onNetDisconnected(int requestCode) {
		DZRectToast.showToastShort(this, "网络异常!", ToastTheme.WARNING);
	}

	@Override
	public void onServerResponseError(String message, int requestCode) {
		DZRectToast.showToastShort(this, "请求无响应!", ToastTheme.ERROR);

	}

	@Override
	public void openTopLoadView(int requestCode) {
		openLoading();
	}

	@Override
	public void closeTopLoadView(int requestCode) {
		closeLoading();
	}

	@Override
	public void onComplete(DZiResponse response, int requestCode) {

	}

	@Override
	protected DZiTopView getTopViewLoader() {
		return new DZCommonTopView(this,
				((ViewGroup) findViewById(R.id.main_top_view)));
	}

	@Override
	protected DZiBottomView getBottomViewLoader() {
		return null;
	}

	@Override
	public void finish() {
		super.finish();
		finishTransitionAnim();
	}

	public void finishTransitionAnim() {
		overridePendingTransition(R.anim.agile_activity_close_enter,
				R.anim.agile_activity_close_exit);
	}

	public void startTransitionAnim() {
		overridePendingTransition(R.anim.agile_activity_open_enter,
				R.anim.agile_activity_open_exit);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		startTransitionAnim();
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		startTransitionAnim();
	}

}
