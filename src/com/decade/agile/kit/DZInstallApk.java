package com.decade.agile.kit;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.decade.agile.DZInstallApkActivity;
import com.decade.agile.R;
import com.decade.agile.components.DZRectToast;
import com.decade.agile.components.DZRectToast.ToastTheme;
import com.decade.agile.kit.DZDownloadFileAsyncTask.DZDownloadCallback;
import com.decade.framework.DZApplication;

public class DZInstallApk implements DZDownloadCallback {
	private NotificationManager nm;
	private Context _context;
	private int noteId;
	private String _appName;

	public DZInstallApk(String appName, String url) {
		_context = DZApplication.getAppContext();
		_appName = appName;
	     nm = (NotificationManager) _context
					.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@Override
	public void onDownloadStart() {
		startDownLoadNotification();
	}

	@Override
	public void onDownload(int progress) {
		sendNotification(progress);
	}

	@Override
	public void onDownloadEnd(int resultCode, File file) {
		if (resultCode == DZDownloadFileAsyncTask.DONE) {
			finishDownLoadNotification();
			installApk(file);
		} else if (resultCode == DZDownloadFileAsyncTask.ERROR) {
			nm.cancel(noteId);
			DZRectToast.showToastLong(_context, "下载失败", ToastTheme.ERROR);
		}
	}

	/**
	 * 安装apk
	 * 
	 * @param url
	 */
	public void installApk(File file) {
		nm.cancel(noteId);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 用AppContext启动Activity必须添加
		_context.startActivity(intent);
	}

	private void finishDownLoadNotification() {
		Intent intent = new Intent(_context, DZInstallApkActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(_context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);// 这个非要不可
		Notification n = new Notification(R.drawable.agile_end_download,
				"下载完成", System.currentTimeMillis());
		n.when = 0;
		n.setLatestEventInfo(_context, _appName + "下载", "下载完成", contentIntent);
		n.defaults = Notification.DEFAULT_SOUND;
		n.flags = Notification.FLAG_AUTO_CANCEL;
		nm.notify(noteId, n);
	}

	private void startDownLoadNotification() {
		Notification note = new Notification();
		note.when = 0;
		// note.flags = Notification.FLAG_NO_CLEAR;
		RemoteViews contentView = new RemoteViews(_context.getPackageName(),
				R.layout.agile_custom_notification_layout);
		contentView.setTextViewText(R.id.notificationTitle, _appName + "下载");
		contentView.setTextViewText(R.id.notificationPercent, "0%");
		contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);
		contentView.setImageViewResource(R.id.notificationImage,
				android.R.drawable.stat_sys_download);
		PendingIntent contentIntent = PendingIntent.getActivity(_context, 0,
				new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);// 这个非要不可
		note.contentView = contentView;
		note.contentIntent = contentIntent;
		note.tickerText = "正在下载";
		note.icon = android.R.drawable.stat_sys_download;
		// note.sound=Uri.parse("file:///sdcard/good.mp3");
		nm.notify(noteId, note);
	}

	private void sendNotification(int progress) {
		Notification note = new Notification();
		note.when = 0;
		// note.flags = Notification.FLAG_NO_CLEAR;
		RemoteViews contentView = new RemoteViews(_context.getPackageName(),
				R.layout.agile_custom_notification_layout);
		contentView.setTextViewText(R.id.notificationTitle, _appName + "下载");
		contentView.setTextViewText(R.id.notificationPercent, progress + "%");
		contentView.setProgressBar(R.id.notificationProgress, 100, progress,
				false);
		contentView.setImageViewResource(R.id.notificationImage,
				android.R.drawable.stat_sys_download);
		PendingIntent contentIntent = PendingIntent.getActivity(_context, 0,
				new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);// 这个非要不可
		note.contentView = contentView;
		note.contentIntent = contentIntent;
		note.icon = android.R.drawable.stat_sys_download;
		nm.notify(noteId, note);
	}

}
