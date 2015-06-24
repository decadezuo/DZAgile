package com.decade.agile.components;

import android.app.Activity;

import com.decade.agile.components.DZDialog.PromptBtnCallback;

/**
 * @description:
 * @version:
 * @author: Decade
 * @date: 2015-6-17
 */
public class DZDialogBuilder {

	private DZPromptDialogParams _params;
	private Activity _activity;
	private DZDialog _dialog;

	/**
	 * @param activity
	 */
	public DZDialogBuilder(Activity activity) {
		_activity = activity;
		_params = new DZPromptDialogParams();
	}

	public DZDialogBuilder(Activity activity, String title) {
		this(activity);
		setTitle(title);
	}

	public DZDialogBuilder(Activity activity, String title, String content) {
		this(activity);
		setTitle(title).setContent(content);
	}

	public DZDialogBuilder(Activity activity, String title, String content,
			String center) {
		this(activity);
		setCenterBtnText(center).setTitle(title).setContent(content);
	}

	public DZDialogBuilder(Activity activity, String title, String content,
			String left, String right) {
		this(activity);
		setRightBtnText(right).setLeftBtnText(left).setTitle(title)
				.setContent(content);
	}

	public DZDialog create() {
		DZCommonDialogView dialog = new DZCommonDialogView(_activity, _params) {
			@Override
			public void onResume() {
				super.onResume();
				getContentTextView().setLineSpacing(5f, 1f);
			}
		};
		return dialog;
	}

	public DZDialog show() {
		_dialog = create();
		_dialog.show();
		return _dialog;
	}

	public void dismiss() {
		_dialog.dismiss();
	}

	public DZDialogBuilder setTitle(String title) {
		_params.setTitle(title);
		return this;
	}

	public DZDialogBuilder setContent(String content) {
		_params.setContent(content);
		return this;
	}

	public DZDialogBuilder setTopViewBgResId(int topViewBgResId) {
		_params.setTopViewBgResId(topViewBgResId);
		return this;
	}

	public DZDialogBuilder setDialogBgResId(int dialogBgResId) {
		_params.setDialogBgResId(dialogBgResId);
		return this;
	}

	public DZDialogBuilder setBottomViewBgResId(int bottomViewBgResId) {
		_params.setBottomViewBgResId(bottomViewBgResId);
		return this;
	}

	public DZDialogBuilder setTopViewBgColor(int topViewBgColor) {
		_params.setTopViewBgColor(topViewBgColor);
		return this;
	}

	public DZDialogBuilder setTitleSize(float titleSize) {
		_params.setTitleSize(titleSize);
		return this;
	}

	public DZDialogBuilder setTitleColor(int titleColor) {
		_params.setTitleColor(titleColor);
		return this;
	}

	public DZDialogBuilder setContentSize(float contentSize) {
		_params.setContentSize(contentSize);
		return this;
	}

	public DZDialogBuilder setContentColor(int contentColor) {
		_params.setContentColor(contentColor);
		return this;
	}

	public DZDialogBuilder setLineColor(int lineColor) {
		_params.setLineColor(lineColor);
		return this;
	}

	public DZDialogBuilder setWidth(int width) {
		_params.setWidth(width);
		return this;
	}

	public DZDialogBuilder setHeight(int height) {
		_params.setHeight(height);
		return this;
	}

	public DZDialogBuilder setLeftBtnText(String left) {
		_params.setLeftBtnText(left);
		return this;
	}

	public DZDialogBuilder setCenterBtnText(String center) {
		_params.setCenterBtnText(center);
		return this;
	}

	public DZDialogBuilder setRightBtnText(String right) {
		_params.setRightBtnText(right);
		return this;
	}

	public DZDialogBuilder setEventCode(int eventCode) {
		_params.setEventCode(eventCode);
		return this;
	}

	public DZDialogBuilder setPromptBtnCallback(
			PromptBtnCallback promptBtnCallback) {
		_params.setPromptBtnCallback(promptBtnCallback);
		return this;
	}

	public DZDialogBuilder setBtnTextSize(int btnTextSize) {
		_params.setBtnTextSize(btnTextSize);
		return this;
	}

	public DZDialogBuilder setLeftBgResId(int leftBgResId) {
		_params.setLeftBgResId(leftBgResId);
		return this;
	}

	public DZDialogBuilder setCenterBgResId(int centerBgResId) {
		_params.setCenterBgResId(centerBgResId);
		return this;
	}

	public DZDialogBuilder setRightBgResId(int rightBgResId) {
		_params.setRightBgResId(rightBgResId);
		return this;
	}

	public DZDialogBuilder setLeftTextColor(int leftTextColor) {
		_params.setLeftTextColor(leftTextColor);
		return this;
	}

	public DZDialogBuilder setCenterTextColor(int centerTextColor) {
		_params.setCenterTextColor(centerTextColor);
		return this;
	}

	public DZDialogBuilder setRightTextColor(int rightTextColor) {
		_params.setRightTextColor(rightTextColor);
		return this;
	}

	public DZDialogBuilder setLeftBgColor(int leftBgColor) {
		_params.setLeftBgColor(leftBgColor);
		return this;
	}

	public DZDialogBuilder setCenterBgColor(int centerBgColor) {
		_params.setCenterBgColor(centerBgColor);
		return this;
	}

	public DZDialogBuilder setRightBgColor(int rightBgColor) {
		_params.setRightBgColor(rightBgColor);
		return this;
	}

	public DZPromptDialogParams getParams() {
		return _params;
	}

	public Activity getActivity() {
		return _activity;
	}

}
