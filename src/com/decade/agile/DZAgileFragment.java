package com.decade.agile;

import android.support.v4.app.Fragment;

import com.decade.agile.components.DZRectToast;
import com.decade.agile.components.DZRectToast.ToastTheme;
import com.decade.framework.DZActivity;
import com.decade.framework.async.DZiAsyncTaskCallback;
import com.decade.framework.async.DZiResponse;

public class DZAgileFragment extends Fragment implements DZiAsyncTaskCallback {

	@Override
	public void onJsonPaserError(String message,int requestCode) {
		DZRectToast.showToastShort(getActivity(), "数据解析异常!", ToastTheme.ERROR);

	}

	@Override
	public void onNetDisconnected(int requestCode) {
		DZRectToast.showToastShort(getActivity(), "网络异常!", ToastTheme.WARNING);
	}

	@Override
	public void onServerResponseError(String message,int requestCode) {
		DZRectToast.showToastShort(getActivity(), "请求异常!", ToastTheme.ERROR);

	}

	@Override
	public void openTopLoadView(int requestCode) {
		((DZActivity) getActivity()).openLoading();
	}

	@Override
	public void closeTopLoadView(int requestCode) {
		((DZActivity) getActivity()).closeLoading();
	}

	@Override
	public void onComplete(DZiResponse response, int requestCode) {

	}

}
