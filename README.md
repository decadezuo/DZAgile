DZAgile
=======
####DZAgile 1.0主要功能：
1、特定UI样式快速搭建（比如Tab类UI），方便各位快速搭建常见应用框架；<br>
2、网络请求和图片异步加载这种框架标配就不说了(图片异步加载使用了Volley,你懂得)；<br>
3、应用更新，只需几行代码就搞定了，有木有？<br>
4、Toast、DIalog常见控件封装有木有？数据库有木有？这些肯定都有；<br>
5、控件注解绑定；<br>
6、自己去挖掘吧；<br>

可以先看看Sample，了解大概的架构。Sample现在只是一个简单的例子，很多框架的功能都没展示出来，会持续更新。

-----------------------------------------去吧，皮卡丘-------------------------------------------------------

## 1、网络请求

先创建一个C_Login类，这个类功能是发送网络请求。拿到数据后调用解析类解析，解析完了之后把数据传给使用的View。

public class C_Login extends DZAgileTask<Object, Object> { //继承 DZAgileTask,这个是请求任务，可查看源码，一目了然

	public C_Login(Context context, DZAsyncTaskParams taskParams) {
		super(context, taskParams);
	}

	@Override
	protected DZiResponse doInBackground(Object... params) {
		JSONObject p = new JSONObject(); 
		try {
			p.put("commandId", AppConstants.USER_LOGIN); //组装请求参数
			JSONObject body = new JSONObject();
			body.put("regNo", params[0]);
			body.put("pwdMD5", params[1]);
			p.put("body", body);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return doTask(AppConstants.MAIN_URL +"USER",p,HTTP_POST); //执行请求post请求，传入请求URL、参数和请求类型
	}

}

这里我们发送参数用了JSON，当然还有更多选择，可以看看DZAgileTask类：

	public DZiResponse doTask(String url, DZRequestParams params, int httpType) {
		return doRequestFromHttp(url, params, httpType);
	}
	
	public DZiResponse doTask(String url, JSONObject params, int httpType) {
		return doTask(url, params.toString(), httpType);
	}
	
	public DZiResponse doTask(String url, String params, int httpType) {
		return doRequestFromHttp(url, params.toString(), httpType);
	}
	
Sample里有个controller包，大家可以把这个类放进去，就统称这些类为Controller吧，创建好Controller后，再创建一个解析类：


下面这个类的功能是解析数据：

public class M_Login extends M_Base { //这个继承是为了解析共有属性。比如一般接口都会返回code,mesage字段。看源码吧。

	private M_User user = new M_User();//用户实体，不解释
	@Override
	public DZiResponse paser(String json) throws Exception { // 网络请求拿到数据后会调用此方法进行解析。看源码。
		super.paser(json);
		JSONObject body = new JSONObject(json).optJSONObject("body");
		user.setUid(body.optString("userId"));
		user.setName(body.optString("nickName"));
		user.setImg(body.optString("imgUrl"));
		return this;
	}
	
	/**
	 * @return the user
	 */
	public M_User getUser() {
		return user;
	}
	public void setUser(M_User user) {
		this.user = user;
	}
}

大家可以把这个类放在model包里，统称Model。

创建完这两个类就可以发起网络请求了，这个发送是在View里，一般就是Activity

		M_Login login = new M_Login(); //刚才建的Model
			DZAsyncTaskParams params = new DZAsyncTaskParams(this, login, null,
					0); //第一个参数：回调接口，第二个解析类，第三个，缓存策略。第四个，请求码。 不懂看源码。
			params.setPromptContent("正在登录"); //提示信息，不解释。
			C_Login task = new C_Login(this, params); //刚才创建的Controller。还记得吧？
			task.execute(getUserName(), getPassword()); //发送请求
			
完了！


不是吧，忘了接收收据了。数据现在还在Model手里呢，需要数据的是View啊。好吧，接收数据在这里：

	@Override
	public void onComplete(DZiResponse response, int requestCode) {
		if (response != null) {
			if (requestCode == 0) {
				M_Login login = (M_Login) response;
				if ("Y".equals(login.getMsg())) {
                   //登录成功，后续处理。
				} else {
					if (!TextUtils.isEmpty(login.getMsg())) {
						DZRectToast.showToastShort(this, login.getMsg(),
								ToastTheme.ERROR);
					}
				}
			} 
		}
	}

整个过程应该清楚了吧。View里调用Controller请求数据，Controller拿到数据后调用Model进行解析，解析完了之后返回给View。还是有点MVC的感觉的，哇哈哈。


## 2、应用程序更新

 首先必须检测版本，这个用上面网络请求类做就可以了。
 判断有新版本后弹出对话框，看看DZAgile怎么呼出自定义的对话框：
 
 	public void showUpdateDialog() {

		String content = "版本号:" + version.getVerName() + "\t\t\t\t大小:"
				+ version.getApkSize() + "M\n" + version.getVerInfo();
		DZPromptDialogParams params = new DZPromptDialogParams("应用更新", content, "以后再说", "立即更新");
	// 第一个参数：对话框title文字，第二个参数：对话框提示文字，第三个参数：左边按钮文字，第四个参数：右边按钮文字
		setDialog(params); 
		DCZommonDialogView commonDialogView = new DZCommonDialogView(this,
				params); 
		int height = DZApplication.getApp().getWorkSpaceHeight() / 3 * 1;
		commonDialogView.setDialogSize(height); // 设置对话框高度
		commonDialogView.prompt_content.setLineSpacing(5f, 1f); //设置提示文字间距
		commonDialogView.open(); 

	}

	public void setDialog(DZPromptDialogParams params) {
		params.setPromptBtnCallback(this); //设置按钮点击事件
		params.setTopViewBgColor(0xff3a3c42); //对话框title背景颜色
		params.setLeftBgColor(getColor(R.color.dialog_gray_btn)); //左边按钮背景颜色
		params.setRightBgColor(getColor(R.color.dialog_green_btn)); // 右边按钮背景颜色
	}
	
	下来处理对话框点击事件：
	
		@Override
	public void onClick(DZiDialog dialog, View view, Position position,
			int eventCode) {
		if (position == Position.RIGHT) {
			downloadPackage();
		}
		dialog.close();
	}
	
	点击了右边按钮也就是立即更新时，开始下载：
	
	public void downloadPackage() {
		String url = version.getApkUrl();
		DZDownloadFileAsyncTask downloadFileAsyncTast = new DZDownloadFileAsyncTask(
				url, new DCInstallApk(DCPackageInfo.getAppName(this), url));
		downloadFileAsyncTast.execute();
	}
	
	就是这几行代码了，剩下的就不用管了。看看效果，是不是很帅啊。
![](https://github.com/decadezuo/DZAgile/blob/master/Res/dcagile_dialog_sample.jpg)

##    3、对话框
   DZAgile目前为止有4种对话框，分别为提示对话、选择对话框、操作对话、加载对话框。
   
###   3.1 提示对话框
   上面下载更新那个对话框就是提示对话框，`DZPromptDialogParams`类里可设置对话框的标题、提示语、按钮等样式。效果图如上图，就不多说了。

###   3.2 选择对话框
    选择对话框和提示对话框使用方法大同小异。

		DZPickDialogView dialgo = new DZPickDialogView(this, params); // 创建选择对话框
		DZPickDialogAdapter adapter = new DZPickDialogAdapter(this, getItems()); // 需要使用指定Adapter
		dialog.setAdapter(adapter);// 设置选择列表数据

![](https://github.com/decadezuo/DZAgile/blob/master/Res/dcagile_pick_dialog.jpg)

###   3.3 操作对话框
操作对话框需要使用`DZListDialogAdapter`适配器。

![](https://github.com/decadezuo/DZAgile/blob/master/Res/dcagile_operation_dialog.jpg)      
      
 ---------------------------------------------------------------------------------------------------------------------

## 引用到的开源项目
##### chrisbanes：Android-PullToRefresh
##### volley

##@作者
### Decade.Zuo (dz)
  联系方式
  QQ讨论群：204424199
