package com.decade.agile.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.decade.agile.R;

public class DZRectDialog extends DZDialog{

	private Context _context;
	private DZBaseDialogParams _params;
	private TextView _content_tv;

	private static DZiDialog _dialog;

	private DZRectDialog(Context context, DZBaseDialogParams params) {
		super(context, R.style.CustomProgressDialog);
		_context = context;
		_params = params;
		 create(params);
	}

	public synchronized static DZiDialog getInstance(Context context,
			DZBaseDialogParams params) {
		if (_dialog == null) {
			return new DZRectDialog(context, params);
		}
		return _dialog;
	}

	@Override
	public View create(DZBaseDialogParams params) {
		View view = LayoutInflater.from(_context).inflate(
				R.layout.agile_rect_dialog_view, null);
		_content_tv = (TextView) view.findViewById(R.id.agile_tips_loading_msg_tv);
		_content_tv.setText(_params.getContent());
		setContentView(view);
		setCanceledOnTouchOutside(false);
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
