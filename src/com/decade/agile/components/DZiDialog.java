package com.decade.agile.components;

import android.view.View;

public interface DZiDialog {
	public View create(DZBaseDialogParams params);
	public DZBaseDialogParams getParams();
	public void refresh();  // 刷新UI
	public void open();
	public void close();
}
