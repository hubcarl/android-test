package com.blue.sky.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	// ��û��ʵ����,�ǲ����������๹�����Ĳ���,��������Ϊ��̬
	private static final String name = "bluesky"; // ���ݿ�����
	private static final int version = 1; // ���ݿ�汾
	private final static String TABLE_NAME = "user";
	public final static String FIELD_ID = "userId";
	public final static String FIELD_TITLE = "userName";

	public DatabaseHelper(Context context) {
		// ����������CursorFactoryָ����ִ�в�ѯʱ���һ���α�ʵ���Ĺ�����,����Ϊnull,����ʹ��ϵͳĬ�ϵĹ�����
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="Create table "+TABLE_NAME+"("+FIELD_ID+" integer primary key autoincrement,"+FIELD_TITLE+" varchar(20) );";
		db.execSQL(sql);
		db.execSQL("CREATE TABLE IF NOT EXISTS person(personid integer primary key autoincrement, name varchar(20), age INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(" ALTER TABLE person ADD phone VARCHAR(12) NULL "); // ����������һ��
		// DROP TABLE IF EXISTS person ɾ����
	}

	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null," _id desc");
		return cursor;
	}

	public long insert(String Title) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FIELD_TITLE, Title);
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
	}

	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}

	public void update(int id, String Title) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Integer.toString(id) };
		ContentValues cv = new ContentValues();
		cv.put(FIELD_TITLE, Title);
		db.update(TABLE_NAME, cv, where, whereValue);
	}
}