package com.decade.agile.components;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.decade.agile.R;

/**
 * @description: 一般提示对话框
 * @author: Decade
 * @date: 2013-5-6
 */

public class DZCommonDialogView extends DZBaseDialogView {

	protected TextView prompt_content_tv;
	protected TextView prompt_title_tv;
	protected TextView prompt_left_bt;
	protected TextView prompt_center_bt;
	protected TextView prompt_right_bt;
	protected DZPromptDialogParams _params;

	/**
	 * @param params
	 */
	public DZCommonDialogView(Activity activity, DZPromptDialogParams params) {
		super(activity);
		_params = params;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		setBaseParams(_params);
		return view;
	}

	protected void addToCenterView(LayoutInflater inflater,
			LinearLayout container) {
		View center = inflater.inflate(R.layout.agile_common_center_view,
				container);
		prompt_content_tv = (TextView) center.findViewById(R.id.prompt_content);
		if (_params.getContentSize() != 0) {
			prompt_content_tv.setTextSize(_params.getContentSize());
		}
		if (_params.getContentColor() != 0) {
			prompt_content_tv.setTextColor(_params.getContentColor());
		}
	}

	private void setContentText() {
		if (!TextUtils.isEmpty(_params.getContent())) {
			if (_params.getContent().contains("\n")) {
				prompt_content_tv.setText(_params.getContent());
			} else if (_params.getContent().contains("<br>")) {
				prompt_content_tv.setText(Html.fromHtml(_params.getContent()));
			} else {
				prompt_content_tv.setText(_params.getContent());
			}
			if (_params.getContent().length() < 30) {
				LayoutParams params = new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.gravity = Gravity.CENTER_VERTICAL;
				prompt_content_tv.setLayoutParams(params);
			}
		}
	}

	private void setButton(String str, final TextView button) {
		if (!TextUtils.isEmpty(str)) {
			button.setVisibility(View.VISIBLE);
			button.setText(str);
			button.setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {

					if (_params.getPromptBtnCallback() != null) {
						if (button.getId() == R.id.prompt_left_bt) {
							_params.getPromptBtnCallback().onClick(
									DZCommonDialogView.this, button,
									Position.LEFT, _params.getEventCode());
						} else if (button.getId() == R.id.prompt_center_bt) {
							_params.getPromptBtnCallback().onClick(
									DZCommonDialogView.this, button,
									Position.CENTER, _params.getEventCode());
						} else if (button.getId() == R.id.prompt_right_bt) {
							_params.getPromptBtnCallback().onClick(
									DZCommonDialogView.this, button,
									Position.RIGHT, _params.getEventCode());
						}
					}
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		setContentText();
		setDialogSize(_params.getWidth(),_params.getHeight());
	}

	@Override
	protected void addToTopView(LayoutInflater inflater, LinearLayout container) {
		View top = inflater.inflate(R.layout.agile_common_top_view, container);
		prompt_title_tv = (TextView) top.findViewById(R.id.prompt_title);
		if (!TextUtils.isEmpty(_params.getTitle())) {
			prompt_title_tv.setText(_params.getTitle());
		} else {
			container.setVisibility(View.GONE);
		}

		if (_params.getTitleSize() != 0) {
			prompt_title_tv.setTextSize(_params.getTitleSize());
		}

		if (_params.getTitleColor() != 0) {
			prompt_title_tv.setTextColor(_params.getTitleColor());
		}
	}

	@Override
	protected void addToBottomView(LayoutInflater inflater,
			LinearLayout container) {
		View bottom = inflater.inflate(R.layout.agile_common_bottom_view,
				container);
		prompt_left_bt = (TextView) bottom.findViewById(R.id.prompt_left_bt);
		prompt_center_bt = (TextView) bottom
				.findViewById(R.id.prompt_center_bt);
		prompt_right_bt = (TextView) bottom.findViewById(R.id.prompt_right_bt);

		if (_params.getBtnTextSize() != 0) {
			prompt_left_bt.setTextSize(_params.getBtnTextSize());
			prompt_center_bt.setTextSize(_params.getBtnTextSize());
			prompt_right_bt.setTextSize(_params.getBtnTextSize());
		}
		if (_params.getLeftBgColor() != 0) {
			prompt_left_bt.setBackgroundColor(_params.getLeftBgColor());
		}
		if (_params.getLeftBgResId() != 0) {
			prompt_left_bt.setBackgroundResource(_params.getLeftBgResId());
		}

		if (_params.getCenterBgColor() != 0) {
			prompt_center_bt.setBackgroundColor(_params.getCenterBgColor());
		}
		if (_params.getCenterBgResId() != 0) {
			prompt_center_bt.setBackgroundResource(_params.getCenterBgResId());
		}

		if (_params.getRightBgColor() != 0) {
			prompt_right_bt.setBackgroundColor(_params.getRightBgColor());
		}
		if (_params.getRightBgResId() != 0) {
			prompt_right_bt.setBackgroundResource(_params.getRightBgResId());
		}

		if (_params.getLeftTextColor() != 0) {
			prompt_left_bt.setTextColor(_params.getLeftTextColor());
		}
		if (_params.getCenterTextColor() != 0) {
			prompt_center_bt.setTextColor(_params.getCenterTextColor());
		}
		if (_params.getRightTextColor() != 0) {
			prompt_right_bt.setTextColor(_params.getRightTextColor());
		}
		if (TextUtils.isEmpty(_params.getLeftBtnText())
				&& TextUtils.isEmpty(_params.getCenterBtnText())
				&& TextUtils.isEmpty(_params.getrRightBtnText())) {
			container.setVisibility(View.GONE);
			return;
		}
		setButton(_params.getLeftBtnText(), prompt_left_bt);
		setButton(_params.getCenterBtnText(), prompt_center_bt);
		setButton(_params.getrRightBtnText(), prompt_right_bt);

	}

	public DZBaseDialogParams getParams() {
		return _params;
	}

	public TextView getContentTextView() {
		return prompt_content_tv;
	}

	public TextView getTitleTextView() {
		return prompt_title_tv;
	}

	public TextView getLeftButton() {
		return prompt_left_bt;
	}

	public TextView getCenterButton() {
		return prompt_center_bt;
	}

	public TextView getRightButton() {
		return prompt_right_bt;
	}

}
