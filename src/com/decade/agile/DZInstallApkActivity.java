package com.decade.agile;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.decade.agile.components.DZRectToast;
import com.decade.agile.components.DZRectToast.ToastTheme;
import com.decade.framework.kit.DZSDCardOperate;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-5-24
 */
public class DZInstallApkActivity extends Activity{
	
	private String _apkName; //必须带上扩展名
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.agile_install_apk_view);
		_apkName = getIntent().getStringExtra("apk_name");
		TextView prompt_title = (TextView)findViewById(R.id.prompt_title);
		prompt_title.setText("提示");
		TextView prompt_content = (TextView)findViewById(R.id.prompt_content);
		prompt_content.setText("更新包已下载完成，是否现在安装？");
		Button install_btn = (Button)findViewById(R.id.prompt_left_bt);
		install_btn.setText("现在安装");
		install_btn.setVisibility(View.VISIBLE);
		Button later_btn = (Button)findViewById(R.id.prompt_right_bt);
		later_btn.setText("以后再说");
		later_btn.setVisibility(View.VISIBLE);
		install_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!TextUtils.isEmpty(_apkName)){
					installApk();
				}else {
					DZRectToast.showToastShort(DZInstallApkActivity.this, "没有找到安装文件",ToastTheme.ERROR);
				}
				finish();
			}
		});
		later_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	/**
	 * 安装apk
	 * 
	 * @param url
	 */
	private void installApk() {
		File apkfile;
		if (DZSDCardOperate.isSDCardMounted()) {
			apkfile = new File(
					Environment.getExternalStorageDirectory(),
					_apkName);
		} else {
			File dirfile = getDir("apk",
					Context.MODE_PRIVATE
							| Context.MODE_WORLD_READABLE
							| Context.MODE_WORLD_WRITEABLE);
			apkfile = new File(dirfile, _apkName);
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(apkfile),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}
}
