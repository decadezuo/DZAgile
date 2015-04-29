package com.decade.agile.components;

import java.io.Serializable;

import com.decade.agile.components.DZDialog.PromptBtnCallback;


/**
 * @description: 对话框各项参数
 * @author: Decade
 * @date: 2013-6-3
 */
public class DZPromptDialogParams extends DZBaseDialogParams implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String _left;
	private String _center;
	private String _right;
	private int _eventCode;
	private int _btnTextSize;
	private int _leftBgResId;
	private int _centerBgResId;
	private int _rightBgResId;
	private int _leftBgColor;
	private int _centerBgColor;
	private int _rightBgColor;
	private int _leftTextColor;
	private int _centerTextColor;
	private int _rightTextColor;

	private PromptBtnCallback _promptButtonCallback;

	public DZPromptDialogParams() {
	}

	public DZPromptDialogParams(String title) {
		setTitle(title);
	}

	public DZPromptDialogParams(String title, String content) {
		setTitle(title);
		setContent(content);
	}
	
	public DZPromptDialogParams(String title, String content,String center) {
		setTitle(title);
		setContent(content);
		setCenterBtnText(center);
	}

	public DZPromptDialogParams(String title, String content, String left,
			String right) {
		setTitle(title);
		setContent(content);
		setLeftBtnText(left);
		setRightBtnText(right);
	}

	public String getLeftBtnText() {
		return _left;
	}

	public void setLeftBtnText(String left) {
		_left = left;
	}

	public String getCenterBtnText() {
		return _center;
	}

	public void setCenterBtnText(String center) {
		_center = center;
	}

	public String getrRightBtnText() {
		return _right;
	}

	public void setRightBtnText(String right) {
		_right = right;
	}

	public int getEventCode() {
		return _eventCode;
	}

	public void setEventCode(int eventCode) {
		_eventCode = eventCode;
	}

	public PromptBtnCallback getPromptBtnCallback() {
		return _promptButtonCallback;
	}

	public void setPromptBtnCallback(PromptBtnCallback promptBtnCallback) {
		_promptButtonCallback = promptBtnCallback;
	}

	public int getBtnTextSize() {
		return _btnTextSize;
	}

	public void setBtnTextSize(int btnTextSize) {
		_btnTextSize = btnTextSize;
	}

	public int getLeftBgResId() {
		return _leftBgResId;
	}

	public void setLeftBgResId(int leftBgResId) {
		_leftBgResId = leftBgResId;
	}

	public int getCenterBgResId() {
		return _centerBgResId;
	}

	public void setCenterBgResId(int centerBgResId) {
		_centerBgResId = centerBgResId;
	}

	public int getRightBgResId() {
		return _rightBgResId;
	}

	public void setRightBgResId(int rightBgResId) {
		_rightBgResId = rightBgResId;
	}

	public int getLeftTextColor() {
		return _leftTextColor;
	}

	public void setLeftTextColor(int leftTextColor) {
		_leftTextColor = leftTextColor;
	}

	public int getCenterTextColor() {
		return _centerTextColor;
	}

	public void setCenterTextColor(int centerTextColor) {
		_centerTextColor = centerTextColor;
	}

	public int getRightTextColor() {
		return _rightTextColor;
	}

	public void setRightTextColor(int rightTextColor) {
		_rightTextColor = rightTextColor;
	}

	public int getLeftBgColor() {
		return _leftBgColor;
	}

	public void setLeftBgColor(int leftBgColor) {
		_leftBgColor = leftBgColor;
	}

	public int getCenterBgColor() {
		return _centerBgColor;
	}

	public void setCenterBgColor(int centerBgColor) {
		_centerBgColor = centerBgColor;
	}

	public int getRightBgColor() {
		return _rightBgColor;
	}

	public void setRightBgColor(int rightBgColor) {
		_rightBgColor = rightBgColor;
	}

}

