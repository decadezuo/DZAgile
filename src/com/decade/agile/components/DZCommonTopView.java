package com.decade.agile.components;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.decade.agile.R;
import com.decade.framework.DZiTopView;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-13
 */
public class DZCommonTopView implements DZiTopView {
	private View _view;
	private View _leftView;
	private View _leftSecondView;
	private View _leftThirdView;
	private View _rightView;
	private View _rightSecondView;
	private View _rightThirdView;
	private View _loadView;
	private View _centerView;
	private View _leftSideView;

	public DZCommonTopView(Context context, ViewGroup root) {
		initViews(context, root);
	}

	public void initViews(Context context, ViewGroup root) {
		_view = View.inflate(context, R.layout.agile_layout_common_top, root);
		_leftSideView = _view.findViewById(R.id.top_left_side_view_layout);
		_leftView = _view.findViewById(R.id.top_left_view);
		_rightView = _view.findViewById(R.id.top_right_view);
		_loadView = _view.findViewById(R.id.top_loading_view);
		_centerView = _view.findViewById(R.id.top_center_layout);
		_leftSecondView = _view.findViewById(R.id.top_left_second_view);
		_leftThirdView = _view.findViewById(R.id.top_left_third_view);
		_rightSecondView = _view.findViewById(R.id.top_right_second_view);
		_rightThirdView = _view.findViewById(R.id.top_right_third_view);
	}

	@Override
	public View getTopView() {
		return _view;
	}

	@Override
	public View getTopLeftView() {
		return _leftView;
	}

	@Override
	public View getTopRightView() {
		return _rightView;
	}

	@Override
	public View getTopLoadView() {
		return _loadView;
	}

	@Override
	public View getTopCenterView() {
		return _centerView;
	}

	@Override
	public View getTopLeftSecondView() {
		return _leftSecondView;

	}

	@Override
	public View getTopLeftThirdView() {
		return _leftThirdView;

	}

	@Override
	public View getTopRightSecondView() {
		return _rightSecondView;

	}

	@Override
	public View getTopRightThirdView() {
		return _rightThirdView;

	}

	@Override
	public View getTopLeftSideView() {
		return _leftSideView;
	}

}
