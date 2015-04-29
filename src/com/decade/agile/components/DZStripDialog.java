package com.decade.agile.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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

	private Context _context;
	private DZBaseDialogParams _params;
	private TextView _content_tv;
	private static DZStripDialog _dialog;

	private DZStripDialog(Context context, DZBaseDialogParams params) {
		super(context, R.style.CustomProgressDialog);
		_context = context;
		_params = params;
		 create(params);
	}

	public synchronized static  DZiDialog getInstance(Context context,
			DZBaseDialogParams params) {
		if (_dialog == null) {
			return new DZStripDialog(context, params);
		}
		return _dialog;
	}

	@Override
	public View create(DZBaseDialogParams params) {
		View view = LayoutInflater.from(_context).inflate(
				R.layout.agile_strip_dialog_view, null);
		_content_tv = (TextView) view.findViewById(R.id.tv_msg);
		_content_tv.setText(_params.getContent());
		ImageView img_loading = (ImageView) view.findViewById(R.id.img_load);
		RelativeLayout img_close = (RelativeLayout) view
				.findViewById(R.id.img_cancel);
		RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils
				.loadAnimation(_context, R.anim.agile_strip_dialog_refresh); // 加载动画
		img_loading.setAnimation(rotateAnimation);
		setContentView(view);
		img_close.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
			}
		});
		return view;
	}

	@Override
	public DZBaseDialogParams getParams() {
		return _params;
	}

	@Override
	public void refresh() {
		_content_tv.setText(_params.getContent());
	}

}
