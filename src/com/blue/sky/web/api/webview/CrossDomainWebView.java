package com.blue.sky.web.api.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.*;
import android.webkit.WebSettings.PluginState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CrossDomainWebView extends WebView {


	public CrossDomainWebView(Context context) {
		super(context);
	}
	
	public CrossDomainWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public CrossDomainWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@TargetApi(19)
	public void openCrossDomain()
	{
		
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//		    WebView.setWebContentsDebuggingEnabled(true);
//		}
		
		if (Build.VERSION.SDK_INT >= 16) {
			Class<?> clazz = this.getSettings().getClass();
			try {
				Method method = clazz.getMethod(
						"setAllowUniversalAccessFromFileURLs", boolean.class);
				if (method != null) {
					method.invoke(this.getSettings(), true);
				}
			} catch (Exception ex) {
				System.out.println("setAllowUniversalAccessFromFileURLs:"
						+ ex.toString());
			}
		}else{
			enableCrossDomain();
		}
	}
	
   
	
	public void openSetting(Context context){
		
		final WebSettings setting = this.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setPluginState(PluginState.ON);  
		setting.setLoadWithOverviewMode(true);
		setting.setAllowFileAccess(true);
		setting.setDomStorageEnabled(true);   
		setting.setAppCacheMaxSize(1024*1024*8);  
		setting.setAllowFileAccess(true);  
		setting.setAppCacheEnabled(true);
		setting.setBlockNetworkImage(true); 
		String appCachePath = context.getCacheDir().getAbsolutePath();  
		setting.setAppCachePath(appCachePath);
		
		this.setWebViewClient(new WebViewClient()
	    {
	        public boolean shouldOverrideUrlLoading(WebView view, String url)
	        {               
	            return false;                            
	        }

	        @Override
	        public void onPageFinished(WebView view, String url)
	        {
	        	Log.d("H5",">>>url:" + url);
	        	setting.setBlockNetworkImage(false); 
	            super.onPageFinished(view, url);
	        }

	        @Override
	        public void onPageStarted(WebView view, String url, Bitmap favicon)
	        {
	            super.onPageStarted(view, url, favicon);
	          
	        }  
	    }); 
		
		this.setWebChromeClient(new WebChromeClient() {
			public boolean onConsoleMessage(ConsoleMessage cm) {
				Log.d("H5",cm.message() + " (" + cm.lineNumber()+ ")" );
				return true;
			}
		});
	}
	
	
	private void enableCrossDomain() {
		try {
			Field field = WebView.class.getDeclaredField("mWebViewCore");
			field.setAccessible(true);
			Object webviewcore = field.get(this);
			Method method = webviewcore.getClass().getDeclaredMethod(
					"nativeRegisterURLSchemeAsLocal", String.class);
			method.setAccessible(true);
			method.invoke(webviewcore, "http");
			method.invoke(webviewcore, "https");
		} catch (Exception e) {
			Log.d("enableCrossDomain", "enablecrossdomain error");
			e.printStackTrace();
		}
	}
	
	

	private void enableCrossDomain41() {
		try {
			Field webviewclassic_field = WebView.class
					.getDeclaredField("mProvider");
			webviewclassic_field.setAccessible(true);
			Object webviewclassic = webviewclassic_field.get(this);
			Field webviewcore_field = webviewclassic.getClass()
					.getDeclaredField("mWebViewCore");
			webviewcore_field.setAccessible(true);
			Object mWebViewCore = webviewcore_field.get(webviewclassic);
			Field nativeclass_field = webviewclassic.getClass()
					.getDeclaredField("mNativeClass");
			nativeclass_field.setAccessible(true);
			Object mNativeClass = nativeclass_field.get(webviewclassic);

			Method method = mWebViewCore.getClass().getDeclaredMethod(
					"nativeRegisterURLSchemeAsLocal",
					new Class[] { int.class, String.class });
			method.setAccessible(true);
			method.invoke(mWebViewCore, mNativeClass, "http");
			method.invoke(mWebViewCore, mNativeClass, "https");
		} catch (Exception e) {
			Log.d("enableCrossDomain41", "enablecrossdomain error");
			e.printStackTrace();
		}
	}

}
