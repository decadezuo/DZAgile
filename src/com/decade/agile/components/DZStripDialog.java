package com.decade.agile.components;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.decade.agile.R;

/**
 * @description: 条状加载数据提示框
 * @author: Decade
 * @date: 2013-5-16
 */
public class DZStripDialog extends DZDialog {

	private DZBaseDialogParams _params;
	private TextView _content_tv;
	private static DZDialog _dialog;

	private DZStripDialog(Activity activity, DZBaseDialogParams params) {
		super(activity);
		_params = params;
	}

	public synchronized static DZDialog getInstance(Activity activity,
			DZBaseDialogParams params) {
		if (_dialog == null) {
			return new DZStripDialog(activity, params);
		}
		return _dialog;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.agile_strip_dialog_view,
				container);
		_content_tv = (TextView) view.findViewById(R.id.tv_msg);
		ImageView img_loading = (ImageView) view.findViewById(R.id.img_load);
		RelativeLayout img_close = (RelativeLayout) view
				.findViewById(R.id.img_cancel);
		RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils
				.loadAnimation(_activity, R.anim.agile_strip_dialog_refresh); // 加载动画
		img_loading.setAnimation(rotateAnimation);
		img_close.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
			}
		});
		return view;
	}

	public DZBaseDialogParams getParams() {
		return _params;
	}

	@Override
	public void onResume() {
		super.onResume();
		getDialog().setCanceledOnTouchOutside(false);
		_content_tv.setText(_params.getContent());
	}

}
