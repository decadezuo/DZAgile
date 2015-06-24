package com.decade.agile.kit;

import java.util.List;

import android.text.TextUtils;

import com.decade.agile.components.DZPickDialogView.DZPickItem;

public class DZPickDialogHelper {
	
	public static void setSelected(String selected, List<DZPickItem> items) {
		if (TextUtils.isEmpty(selected))
			return;
		for (int i = 0; i < items.size(); i++) {
			if (selected.equals(items.get(i).getTitle())) {
				items.get(i).setSelected(true);
			}
		}
	}
}
