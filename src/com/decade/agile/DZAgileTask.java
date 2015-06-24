package com.decade.agile;

import org.json.JSONObject;

import android.app.Activity;
import android.text.TextUtils;

import com.decade.agile.kit.DZDialogHelper;
import com.decade.agile.kit.DZDialogHelper.DialogTheme;
import com.decade.framework.async.DZAsyncTaskParams;
import com.decade.framework.async.DZHttpAsyncTask;
import com.decade.framework.async.DZiResponse;
import com.decade.framework.network.DZRequestParams;

/**
 * @description
 * @author Decade
 * @date 2013-4-23
 */
public abstract class DZAgileTask <Params, Progress> extends DZHttpAsyncTask<Params, Progress> {

	public DZAgileTask(Activity activity, DZAsyncTaskParams taskParams) {
		super(activity, taskParams);
	}

	public DZiResponse doTask(String url, DZRequestParams params, int httpType) {
		return doRequestFromHttp(url, params, httpType);
	}
	
	public DZiResponse doTask(String url, JSONObject params, int httpType) {
		return doTask(url, params.toString(), httpType);
	}
	
	public DZiResponse doTask(String url, String params, int httpType) {
		return doRequestFromHttp(url, params.toString(), httpType);
	}
	
	

	@Override
	protected void onFinish(boolean closePrompt) {
		super.onFinish(closePrompt);
		if (closePrompt) {
			DZDialogHelper.closePrompt();
		}
	}

	@Override
	protected void onStart(boolean openPrompt, String content) {
		super.onStart(openPrompt,content);
		if (openPrompt) {
			if (TextUtils.isEmpty(content)) {
				DZDialogHelper.openPrompt(getActivity(), DialogTheme.RECT);
			} else {
				DZDialogHelper.openPrompt(getActivity(), content,
						DialogTheme.RECT);
			}
		}
	}

}
