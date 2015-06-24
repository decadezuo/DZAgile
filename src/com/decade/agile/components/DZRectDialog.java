package com.decade.agile.components;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.decade.agile.R;

public class DZRectDialog extends DZDialog{

	private DZBaseDialogParams _params;
	private TextView _content_tv;

	private static DZDialog _dialog;

	private DZRectDialog(Activity activity, DZBaseDialogParams params) {
		 super(activity);
		_params = params;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public synchronized static DZDialog getInstance(Activity activity,
			DZBaseDialogParams params) {
		if (_dialog == null) {
			return new DZRectDialog(activity, params);
		}
		return _dialog;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.agile_rect_dialog_view,container);
		_content_tv = (TextView) view.findViewById(R.id.agile_tips_loading_msg_tv);
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
