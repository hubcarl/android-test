package com.blue.sky.core.db;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blue.sky.animate.MyApplication;
import com.blue.sky.common.LogUtil;
import com.blue.sky.common.Reflection;

public class SQLiteHelper extends SQLiteOpenHelper {

	private Context context;
	
	public SQLiteHelper(Context context) {
		super(context, DBConfig.DB_NAME, null, DBConfig.DB_VERSION);
	}
	
    private static class HelperHolder {
        public static SQLiteHelper helper = new SQLiteHelper(MyApplication.getInstance());
     }
  
	public static SQLiteHelper newInstance(Context context){
		 return HelperHolder.helper;
	}

	
	public interface SQLiteTable{
		public void onCreate(SQLiteDatabase sqliteDatabase);
		public void onUpgrade(SQLiteDatabase sqliteDatabase, int oldversion, int newversion);
		public void onOpen(SQLiteDatabase db);
	}

	// �˴���ɶ��������ݱ����������
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		String[] tables = DBConfig.DBOPERATIONS;
		Reflection mReflection = new Reflection();
		for (int i = 0,len = tables.length; i < len; i++) {
			try {
				SQLiteOperation sqliteOperation = (SQLiteOperation)mReflection.newInstance(
						tables[i],
						new Object[]{context},
						new Class[]{Application.class});
				sqliteOperation.onCreate(sqliteDatabase);
			} catch (Exception e) {
				LogUtil.e("operate table fail! "+tables[i]);
				LogUtil.e(e);
			}
		}

	}
	
	// �����ݿ�汾��Ϣ���ֱ䶯ʱ���ø÷�������onUpgrade�����еĺ����������ֱ�Ϊ�ϰ汾�ţ��°汾�ţ�
	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, int oldversion, int newversion) {
		String[] tables = DBConfig.DBOPERATIONS;
		Reflection mReflection = new Reflection();
		for (int i = 0,len = tables.length; i < len; i++) {
			try {
				SQLiteOperation sqliteOperation = (SQLiteOperation)mReflection.newInstance(
						tables[i],
						new Object[]{context},
						new Class[]{Application.class});
				sqliteOperation.onUpgrade(sqliteDatabase, oldversion, newversion);
			} catch (Exception e) {
				LogUtil.e("update table fail! "+tables[i]);
				LogUtil.e(e);
			}

		}

		/** �Ժ��������İ汾����  **/
		// ��ȡ�����������ݱ�������
		// ɾ���������ݱ�
		// �������������ݱ�
		// ����ȡ�����ݲ��뵽�����ݱ�
	}
	//
	@Override
	public void onOpen(SQLiteDatabase db)
	{
		super.onOpen(db);
	}
}
