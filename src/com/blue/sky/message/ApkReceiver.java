package com.blue.sky.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*<receiver android:name=".BootReceiver"  
 android:label="@string/app_name">   
 <intent-filter>  
 <action android:name="android.intent.action.PACKAGE_ADDED" />  
 <action android:name="android.intent.action.PACKAGE_REMOVED" />  
 <data android:scheme="package" />  
 </intent-filter>  
 </receiver>  
 <uses-permission android:name="android.permission.RESTART_PACKAGES"/>  
 <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>*/
public class ApkReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// ���հ�װ�㲥
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
			String packageName = intent.getDataString();
			System.out.println("��װ��:" + packageName + "�����ĳ���");
		}
		// ����ж�ع㲥
		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
			String packageName = intent.getDataString();
			System.out.println("ж����:" + packageName + "�����ĳ���");

		}
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Intent newIntent = new Intent(context, null);
			newIntent.setAction("android.intent.action.MAIN");
			newIntent.addCategory("android.intent.category.LAUNCHER");
			newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(newIntent);
		}
	}
}