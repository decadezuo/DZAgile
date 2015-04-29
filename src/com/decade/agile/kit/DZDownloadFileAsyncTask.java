package com.decade.agile.kit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.decade.framework.DZApplication;
import com.decade.framework.kit.DZSDCardOperate;

/**
 * @description: 文件下载类
 * @author: Decade
 * @date: 2013-10-30
 */
public class DZDownloadFileAsyncTask extends
		AsyncTask<String, Integer, Integer> {
	private boolean interceptFlag = true;
	public static final int DONE = 1;
	public static final int UNDONE = 0;
	public static final int ERROR = -1;
	private String _url;
	private DCDownloadCallback _callback;
	private File apkfile;
	private Context _context;

	public DZDownloadFileAsyncTask(String url, DCDownloadCallback callback) {
		_context = DZApplication.getAppContext();
		_url = url;
		_callback = callback;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (_callback != null) {
			_callback.onDownloadStart();
		}
	}

	@Override
	protected Integer doInBackground(String... params) {
		int status = UNDONE;
		int progress = 0;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(_url);
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			long length = entity.getContentLength();
			InputStream is = entity.getContent();
			FileOutputStream fileOutputStream = null;
			if (is != null) {
				apkfile = getSaveFile();
				fileOutputStream = new FileOutputStream(apkfile);
				byte[] buf = new byte[1024];
				int ch = -1;
				int count = 0;
				do {
					ch = is.read(buf);
					if (ch != -1) {
						count += ch;
					}
					progress = (int) (((float) count / length) * 100);
					if (progress % 10 == 0) {
						if (_callback != null) {
							_callback.onDownload(progress);
						}
					}
					if (ch == -1) {
						break;
					}
					fileOutputStream.write(buf, 0, ch);
				} while (interceptFlag);

			}
			fileOutputStream.flush();
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
			is.close();
			status = DONE;
		} catch (Exception e) {
			e.printStackTrace();
			status = ERROR;
		}
		return status;
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		if (_callback != null) {
			_callback.onDownloadEnd(result, apkfile);
		}
	}

	protected String getFileName() {
		return DZFileUtils.getFileNameFromUrl(_url);
	}

	protected File getSaveFile() {
		File file = null;
		if (DZSDCardOperate.isSDCardMounted()) {
			file = new File(Environment.getExternalStorageDirectory(),
					getFileName());
		} else {
			/*
			 * File dirfile = _context.getDir("apk", Context.MODE_PRIVATE |
			 * Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE); if
			 * (!dirfile.exists()) { dirfile.mkdir(); }
			 */
			file = new File(_context.getFilesDir(), getFileName());
			setFileLimits();
		}
		return file;
	}

	public void setFileLimits() {
		String[] command = { "chmod", "777", apkfile.getPath() };
		ProcessBuilder builder = new ProcessBuilder(command);
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cancelDownload() {
		interceptFlag = false;
	}

	public interface DCDownloadCallback {
		public void onDownloadStart();

		public void onDownload(int progress);

		public void onDownloadEnd(int resultCode, File file);

	}
}