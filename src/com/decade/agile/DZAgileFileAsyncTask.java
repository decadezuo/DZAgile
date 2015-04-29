package com.decade.agile;

import java.io.File;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.decade.agile.kit.DZDialogHelper;
import com.decade.agile.kit.DZDialogHelper.DialogTheme;
import com.decade.framework.async.DZAsyncTaskParams;
import com.decade.framework.async.DZFileAsyncTask;
import com.decade.framework.async.DZiResponse;

/**
 * @description: 文件上传
 * @author: Decade
 * @date: 2014-7-5
 */
public class DZAgileFileAsyncTask extends DZFileAsyncTask<String, Integer>{

	public DZAgileFileAsyncTask(Context context, DZAsyncTaskParams params) {
		super(context, params);
	}
	
	public DZiResponse doTask(String url, Map<String, String> params,
			Map<String, File> files) {
		return doUpload(url, params, files);
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
				DZDialogHelper.openPrompt(getContext(), DialogTheme.RECT);
			} else {
				DZDialogHelper.openPrompt(getContext(), content,
						DialogTheme.RECT);
			}
		}
	}

}
