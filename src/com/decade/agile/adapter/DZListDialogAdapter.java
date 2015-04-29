package com.decade.agile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.decade.agile.R;
import com.decade.agile.components.DZPickDialogView.DCPickItem;

public class DZListDialogAdapter extends DZListAdapter<DCPickItem> {

	/**
	 * @param context
	 * @param list
	 */
	public DZListDialogAdapter(Context context, List<DCPickItem> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(_context).inflate(
					R.layout.agile_list_dialog_item_view, null);
			holder.title_tv = (TextView) view
					.findViewById(R.id.list_dialog_item_title_tv);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		DCPickItem item = _list.get(position);
		holder.title_tv.setText(item.getTitle());
		return view;
	}

	static class ViewHolder {
		TextView title_tv;

	}

}
