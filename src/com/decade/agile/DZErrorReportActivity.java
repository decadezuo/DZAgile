package com.decade.agile;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-6-5
 */
public class DZErrorReportActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ScrollView scrollView  = new ScrollView(this);
		TextView report_txv =  new TextView(this);
		scrollView.addView(report_txv);
		setContentView(scrollView);
		String report = (String)getIntent().getStringExtra("report");
		if(!TextUtils.isEmpty(report)){
			report_txv.setText(Html.fromHtml(report));
		}
	}
	
}
