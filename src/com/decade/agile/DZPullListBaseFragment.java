package com.decade.agile;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.decade.agile.pulltorefresh.PullToRefreshBase;
import com.decade.agile.pulltorefresh.PullToRefreshBase.Mode;
import com.decade.agile.pulltorefresh.PullToRefreshBase.OnLastItemVisibleListener;
import com.decade.agile.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.decade.agile.pulltorefresh.PullToRefreshListView;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-14
 */
public abstract class DZPullListBaseFragment extends DZAgileFragment implements
		OnItemClickListener, OnItemLongClickListener,
		OnRefreshListener2<ListView>, OnLastItemVisibleListener {
	private PullToRefreshListView _pullRefreshLv;

	public void init(PullToRefreshListView pullRefreshLv) {
		_pullRefreshLv = pullRefreshLv;
		_pullRefreshLv.setOnItemClickListener(this);
		_pullRefreshLv.setOnRefreshListener(this);
		_pullRefreshLv.setOnLastItemVisibleListener(this);
		ListView actualListView = _pullRefreshLv.getRefreshableView();
		actualListView.setOnItemLongClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		return false;
	}

	public PullToRefreshListView getListView() {
		return _pullRefreshLv;
	}

	public void setPullRefreshMode(Mode mode) {
		_pullRefreshLv.setMode(mode);
	}

	@Override
	public void onLastItemVisible() {

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		setRefreshDate(refreshView);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		setRefreshDate(refreshView);
	}

	private void setRefreshDate(PullToRefreshBase<ListView> refreshView) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
		// String date = format.format(new Date());
		// refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(date);
		String label = DateUtils.formatDateTime(getActivity(),
				System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL);
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
	}

}
