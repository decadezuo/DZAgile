package com.decade.agile;

import android.content.Intent;

import com.decade.framework.DZBaseViewActivity;

public abstract class DZAgileBaseViewActivity extends DZBaseViewActivity{

	@Override
	public void finish() {
		super.finish();
		finishTransitionAnim();
	}

	public void finishTransitionAnim() {
		overridePendingTransition(R.anim.agile_activity_close_enter,
				R.anim.agile_activity_close_exit);
	}

	public void startTransitionAnim() {
		overridePendingTransition(R.anim.agile_activity_open_enter,
				R.anim.agile_activity_open_exit);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		startTransitionAnim();
	}
}
