package com.decade.agile.components;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.decade.agile.R;

public class DZEmptyView {
	public static void setListEmptyView(Context context, ListView listView,
			String prompt, OnClickListener l) {
		if (context== null || listView == null)
			return;
		View emptyView = getEmptyView(context, prompt, l);
		((ViewGroup) listView.getParent()).addView(emptyView);
		listView.setEmptyView(emptyView);
	}

	public static void setGridEmptyView(Context context, GridView gridView,
			String prompt, OnClickListener l) {
		if (context== null || context == null)
			return;
		View emptyView = getEmptyView(context, prompt, l);
		((ViewGroup) gridView.getParent()).addView(emptyView);
		gridView.setEmptyView(new LinearLayout(context));
	}

	public static void setReloadView(Context context, View view, String prompt,
			OnClickListener l) {
		if (context== null || view == null)
			return;
		View emptyView = getEmptyView(context, prompt, l);
		((ViewGroup) view.getParent()).addView(emptyView);
	}

	private static View getEmptyView(Context context, String prompt,
			OnClickListener l) {
		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.setGravity(Gravity.CENTER);
		TextView emptyView = new TextView(context);
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		emptyView.setText(prompt);
		emptyView.setTextColor(Color.GRAY);
		emptyView.setTextSize(14);
		emptyView.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.agile_load_list_error_icon, 0, 0, 0);
		emptyView.setCompoundDrawablePadding(10);
		layout.addView(emptyView);
		if (l != null) {
			layout.setOnClickListener(l);
		}
		return layout;
	}

}
