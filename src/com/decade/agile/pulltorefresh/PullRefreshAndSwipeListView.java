package com.decade.agile.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.decade.agile.components.DZSwipeDismissListView;

public class PullRefreshAndSwipeListView extends
		PullToRefreshListView {

	public PullRefreshAndSwipeListView(Context context) {
		super(context);
	}

	public PullRefreshAndSwipeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullRefreshAndSwipeListView(Context context, Mode mode) {
		super(context, mode);
	}

	public PullRefreshAndSwipeListView(Context context, Mode mode,
			AnimationStyle style) {
		super(context, mode, style);
	}
	
	protected ListView createListView(Context context, AttributeSet attrs) {
		final ListView lv;
		lv = new DZSwipeDismissListView(context, attrs,this);
		return lv;
	}

	@Override
	protected ListView createRefreshableView(Context context, AttributeSet attrs) {
		ListView lv = createListView(context, attrs);
		// Set it to this so it can be used in ListActivity/ListFragment
		lv.setId(android.R.id.list);
		return lv;
	}


}
