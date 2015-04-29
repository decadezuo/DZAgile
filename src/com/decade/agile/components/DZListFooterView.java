package com.decade.agile.components;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.decade.agile.R;
import com.decade.agile.adapter.DZListAdapter;
import com.decade.agile.pulltorefresh.PullToRefreshListView;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-6-28
 */
public class DZListFooterView<T> {
 private Context _context;
 public View _footerView;
 private boolean _isLoading;
 private PullToRefreshListView _listView;
 private ImageView _loadImage;
	public DZListFooterView(Context context,PullToRefreshListView listView){
		_footerView = View.inflate(context,R.layout.agile_foot_loading_progressbar, null);
		_listView = listView;
		_context = context;
		initLoadImage();
	}
	
	private void initLoadImage(){
		_loadImage =(ImageView)_footerView.findViewById(R.id.loading_progressbar);
		Animation operatingAnim = AnimationUtils.loadAnimation(_context, R.anim.agile_foot_loading_image_rotate);  
		LinearInterpolator lin = new LinearInterpolator();  
		operatingAnim.setInterpolator(lin);  
		if (operatingAnim != null) {  
			_loadImage.startAnimation(operatingAnim);  
		}  
	}
	
	public void showFooterView(int page,int size,List<T> data, DZListAdapter<T> adapter) {
		if (page == 1) {
			setAdapter(adapter);
			if (data.size() < size) {
				removeFooterLoadingView();
				_isLoading = false;
			} else {
				addFooterLoadingView();
				_isLoading = true;
			}
		} else {
			if (data.size() < size) {
				removeFooterLoadingView();
				_isLoading = false;
			} else {
				_isLoading = true;
			}
			adapter.addMoreData(data);
		}
	}
	
	
	/**
	 * 获取数据后刷新列表
	 * 
	 * @param response
	 */
	public void setAdapter(BaseAdapter adapter) {
		_listView.setAdapter(adapter);
		TextView empty_tx = new TextView(_context);
		empty_tx.setText("无内容显示");
		_listView.setEmptyView(empty_tx);
	}
	
	public boolean isLoading(){
		return _isLoading;
	}
	/**
	 * 移除底部加载进度条
	 * 
	 * @param list
	 */
	public  void removeFooterLoadingView() {
		if (_listView.getRefreshableView().getFooterViewsCount() > 1 && _footerView != null) {
			_listView.getRefreshableView().removeFooterView(_footerView);
		}
	}

	/**
	 * 添加底部加载进度条
	 * 
	 * @param list
	 */
	public void addFooterLoadingView() {
		System.out.println(_listView.getRefreshableView().getFooterViewsCount());
		if (_listView.getRefreshableView().getFooterViewsCount() == 1 && _footerView != null) {
			_listView.getRefreshableView().addFooterView(_footerView);
		}
	}

}
