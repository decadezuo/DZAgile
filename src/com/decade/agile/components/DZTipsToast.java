package com.decade.agile.components;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.decade.agile.R;
import com.decade.framework.kit.DZLog;

public class DZTipsToast extends Toast {

	public DZTipsToast(Context context) {
		super(context);
	}

	public static DZTipsToast makeText(Context context, CharSequence text,
			int iconResId, int duration) {
		DZTipsToast result = new DZTipsToast(context);
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.agile_toast_view, null);
		TextView tv = (TextView) view
				.findViewById(R.id.agile_toast_tips_msg_tv);
		ImageView iv = (ImageView) view
				.findViewById(R.id.agile_toast_tips_icon_iv);
		tv.setText(text);
		iv.setImageResource(iconResId);
		result.setView(view);
		// setGravity方法用于设置位置，此处为垂直居中
		result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		result.setDuration(duration);
		return result;
	}

	public static DZTipsToast makeText(Context context, int resId,
			int iconResId, int duration) throws Resources.NotFoundException {
		return makeText(context, context.getResources().getText(resId),
				iconResId, duration);
	}

	public void setIcon(int iconResId) {
		if (getView() == null) {
			DZLog.e(getClass(),
					"This Toast was not created with Toast.makeText()");
			return;
		}
		ImageView iv = (ImageView) getView().findViewById(
				R.id.agile_toast_tips_icon_iv);
		if (iv == null) {
			DZLog.e(getClass(),
					"This Toast was not created with Toast.makeText()");
			return;
		}
		iv.setImageResource(iconResId);
	}

	@Override
	public void setText(CharSequence s) {
		if (getView() == null) {
			DZLog.e(getClass(),
					"This Toast was not created with Toast.makeText()");
			return;
		}
		TextView tv = (TextView) getView().findViewById(
				R.id.agile_toast_tips_msg_tv);
		if (tv == null) {
			DZLog.e(getClass(),
					"This Toast was not created with Toast.makeText()");
			return;
		}
		tv.setText(s);
	}
}
