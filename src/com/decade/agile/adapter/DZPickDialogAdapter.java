package com.decade.agile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.decade.agile.R;
import com.decade.agile.components.DZPickDialogView.DCPickItem;

public class DZPickDialogAdapter extends DZListAdapter<DCPickItem> {

	private int _indicatorImageResId;

	/**
	 * @param context
	 * @param list
	 */
	public DZPickDialogAdapter(Context context, List<DCPickItem> list) {
		super(context, list);
	}

	public DZPickDialogAdapter(Context context, List<DCPickItem> list,
			int indicatorImageResId) {
		super(context, list);
		_indicatorImageResId = indicatorImageResId;
	}

	/**
	 * 设置指示点图标，此ResId是一个selector，参照 @drawable/agile_pick_dialog_checkbox_bg
	 * 
	 * @param indicatorImageResId
	 */
	public void setIndicatorImageResId(int indicatorImageResId) {
		_indicatorImageResId = indicatorImageResId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(_context).inflate(
					R.layout.agile_pick_dialog_item_view, null);
			holder.img_imv = (ImageView) view
					.findViewById(R.id.pick_dialog_item_img_imv);
			holder.title_tv = (TextView) view
					.findViewById(R.id.pick_dialog_item_title_txv);
			holder.flag_ckb = (CheckBox) view
					.findViewById(R.id.pick_dialog_item_flag_ckb);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		DCPickItem ss = _list.get(position);
		holder.title_tv.setText(ss.getTitle());
		if (ss.isSelected()) {
			holder.flag_ckb.setChecked(true);
		} else {
			holder.flag_ckb.setChecked(false);
		}
		if (ss.getIconId() != 0) {
			holder.img_imv.setImageResource(ss.getIconId());
			holder.img_imv.setVisibility(View.VISIBLE);
		} else {
			holder.img_imv.setVisibility(View.GONE);
		}

		if (_indicatorImageResId != 0) {
			holder.flag_ckb.setButtonDrawable(_indicatorImageResId);
		} else {
			holder.flag_ckb
					.setButtonDrawable(R.drawable.agile_pick_dialog_checkbox_bg);
		}

		return view;
	}

	static class ViewHolder {
		ImageView img_imv;
		TextView title_tv;
		CheckBox flag_ckb;

	}

}
