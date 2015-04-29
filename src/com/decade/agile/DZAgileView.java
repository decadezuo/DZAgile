package com.decade.agile;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.decade.agile.components.DZRectToast;
import com.decade.agile.components.DZRectToast.ToastTheme;
import com.decade.framework.DZBaseView;
import com.decade.framework.DZBaseViewActivity;
import com.decade.framework.async.DZiAsyncTaskCallback;
import com.decade.framework.async.DZiResponse;
import com.decade.framework.kit.DZBuild;

public abstract class DZAgileView extends DZBaseView implements DZiAsyncTaskCallback  {

	protected void open() {
	};// 3.打开，获取数据打开视图

	protected void resume() {
	};// 4.恢复视图

	protected void pause() {
		breakTask();
	};// 5.中止

	protected void destroy() {
	};// 7.销毁

	public DZAgileView(ViewGroup root, DZBaseViewActivity parent) {
		super(root, parent);
	}

	private final void breakTask() {
		resetLoading();
		setTopRightViewEnabled(true);
		
	}
	

	@Override
	public void closeTopLoadView(int requestCode) {
		closeLoading();
	}

	@Override
	public void openTopLoadView(int requestCode) {
		openLoading();
	}

	@Override
	public void onJsonPaserError(String message,int requestCode) {
		if(DZBuild.isDebugMode()){
			DZRectToast.showToastLong(getContext(), "数据解析异常!"+ message,ToastTheme.ERROR);

		}else {
			DZRectToast.showToastShort(getContext(), "数据解析异常!",ToastTheme.ERROR);
		}
	}

	@Override
	public void onNetDisconnected(int requestCode) {
		DZRectToast.showToastShort(getContext(), "网络连接异常!",ToastTheme.WARNING);
	}

	@Override
	public void onServerResponseError(String message,int requestCode) {
		if(DZBuild.isDebugMode()){
			DZRectToast.showToastLong(getContext(), "请求异常!"+message,ToastTheme.ERROR);

		}else {
			DZRectToast.showToastShort(getContext(), "请求异常!",ToastTheme.ERROR);

		}
	}

	@Override
	public void onComplete(DZiResponse response, int requestCode) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	protected void startActivity(Intent intent) {
		super.startActivity(intent);
		getParent().overridePendingTransition(R.anim.agile_activity_open_enter, R.anim.agile_activity_open_exit);

	}

	@Override
	protected boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			preViewLastPage();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
