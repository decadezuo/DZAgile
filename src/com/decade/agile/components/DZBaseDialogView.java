package com.decade.agile.components;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

	protected View root_view;
	protected View line_lat;
	protected LinearLayout top_layout;
	protected LinearLayout center_layout;
	protected LinearLayout bottom_layout;

	/**
	 * @param activity
	 */
	public DZBaseDialogView(Activity activity) {
		super(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root_view = inflater
				.inflate(R.layout.agile_base_dialog_view, container);
		top_layout = (LinearLayout) root_view
				.findViewById(R.id.base_dialog_top_layout);
		line_lat = (View) root_view.findViewById(R.id.base_dialog_line_lat);
		center_layout = (LinearLayout) root_view
				.findViewById(R.id.base_dialog_center_layout);
		bottom_layout = (LinearLayout) root_view
				.findViewById(R.id.base_dialog_bottom_layout);
		addToTopView(inflater, top_layout);
		addToCenterView(inflater, center_layout);
		addToBottomView(inflater, bottom_layout);
		return root_view;
	}

	protected void setBaseParams(DZBaseDialogParams params) {

		if (params.getDialogBgResId() != 0) {
			root_view.setBackgroundResource(params.getDialogBgResId());
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
		if(params.getWidth() == 0){
			params.setWidth(getDefaultDialogWidth());
		}
		if (params.getHeight() == 0) {
			params.setHeight(getDefaultDialogHeight());
		}
	}

	/**
	 * 设置对话框的大小
	 * @param width
	 * @param height
	 */

	public void setDialogSize(int width, int height) {
		getDialog().getWindow().setLayout(width, height);
	}

	public void setDialogHeight(int height) {
		Window w = getDialog().getWindow();
		WindowManager.LayoutParams wl = w.getAttributes();
		wl.height = height;
		w.setAttributes(wl);
	}

	public void setDialogWidth(int width) {
		Window w = getDialog().getWindow();
		WindowManager.LayoutParams wl = w.getAttributes();
		wl.width = width;
		w.setAttributes(wl);
	}

	public int getDefaultDialogWidth() {
		return DZApplication.getApp().getWorkSpaceWidth() - 40;
	}

	public int getDefaultDialogHeight() {
		return DZApplication.getApp().getWorkSpaceHeight() / 4 * 1;
	}

	protected abstract void addToTopView(LayoutInflater inflater,
			LinearLayout container);

	protected abstract void addToCenterView(LayoutInflater inflater,
			LinearLayout container);

	protected abstract void addToBottomView(LayoutInflater inflater,
			LinearLayout container);

}
