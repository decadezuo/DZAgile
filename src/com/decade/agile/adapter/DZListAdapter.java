package com.decade.agile.adapter;

import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * @description: 列表适配器
 * @author: Decade
 * @date: 2013-6-28
 * 
 */
public abstract class DZListAdapter<T> extends BaseAdapter {
	public Context _context;
	protected List<T> _list;

	public DZListAdapter(Context context, List<T> list) {
		_context = context;
		_list = list;
	}

	public int getCount() {
		return _list.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public void addMoreData(List<T> data) {
		_list.addAll(data);
		this.notifyDataSetChanged();
	}

	public void addMoreData(T data) {
		_list.add(data);
		this.notifyDataSetChanged();
	}

	public List<T> getList() {
		return _list;
	}

	public T getObjectWithPosition(int position) {
		return _list.get(position);
	}

	public int getColor(int resId) {
		return _context.getResources().getColor(resId);
	}
}
