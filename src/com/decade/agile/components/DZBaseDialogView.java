package com.decade.agile.components;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.decade.agile.R;
import com.decade.framework.DZApplication;

/**
 * @description: 基础对话框
 * @author: Decade
 * @date: 2013-5-6
 */

public abstract class DZBaseDialogView extends DZDialog {

	public Context _context;
	public View _baseView;

	public DZBaseDialogView(Context context) {
		super(context, R.style.notitle_dialog_style);
		_context = context;
	}

	@Override
	public View create(DZBaseDialogParams params) {
		_baseView = View.inflate(_context, R.layout.agile_base_dialog_view,
				null);
		LinearLayout top_layout = (LinearLayout) _baseView
				.findViewById(R.id.base_dialog_top_layout);
		View line_lat = (View) _baseView
				.findViewById(R.id.base_dialog_line_lat);
		LinearLayout center_layout = (LinearLayout) _baseView
				.findViewById(R.id.base_dialog_center_layout);
		LinearLayout bottom_layout = (LinearLayout) _baseView
				.findViewById(R.id.base_dialog_bottom_layout);

		if (params.getDialogBgResId() != 0) {
			_baseView.setBackgroundResource(params.getDialogBgResId());
		}
		if (params.getTopViewBgColor() != 0) {
			top_layout.setBackgroundColor(params.getTopViewBgColor());
		}
		if (params.getTopViewBgResId() != 0) {
			top_layout.setBackgroundResource(params.getTopViewBgResId());
		}
		if (params.getBottomViewBgResId() != 0) {
			bottom_layout.setBackgroundResource(params.getBottomViewBgResId());
		}
		if (params.getLineColor() != 0) {
			line_lat.setBackgroundColor(params.getLineColor());
		}
		addToTopView(top_layout);
		addToCenterView(center_layout);
		addToBottomView(bottom_layout);
		setContentView(_baseView);
		return _baseView;
	}

	/**
	 * 设置对话框的大小
	 * 
	 * @param width
	 * @param height
	 */
	public void setDialogSize(int width, int height) {
		Window w = getWindow();
		WindowManager.LayoutParams wl = w.getAttributes();
		wl.width = width;
		wl.height = height;
		w.setAttributes(wl);
	}

	public void setDialogHeight(int height) {
		Window w = getWindow();
		WindowManager.LayoutParams wl = w.getAttributes();
		wl.height = height;
		w.setAttributes(wl);
	}

	public void setDialogWidth(int width) {
		Window w = getWindow();
		WindowManager.LayoutParams wl = w.getAttributes();
		wl.width = width;
		w.setAttributes(wl);
	}

	public int getDefaultDialogWidth() {
		return DZApplication.getApp().getWorkSpaceWidth() - 60;
	}

	public int getDefaultDialogHeight() {
		return DZApplication.getApp().getWorkSpaceHeight() / 4 * 1;
	}

	protected abstract void addToTopView(LinearLayout layout);

	protected abstract void addToCenterView(LinearLayout layout);

	protected abstract void addToBottomView(LinearLayout layout);

}
