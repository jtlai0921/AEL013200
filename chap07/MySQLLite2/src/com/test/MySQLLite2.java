package com.test;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MySQLLite2 extends Activity {
  
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
		  
  try {
   //建立資料庫
   //SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.freejavaman/mydb.db", null);
   SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/sdcard/mydb.db", null);
			  
   //查詢所有資料
   Cursor cursor = db.query("passenger", null, null, null, null, null, null);
   //db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
		   
   //移到第一筆資料
   Log.v("sql", "cursor.getCount():" + cursor.getCount());
   if (cursor.moveToFirst()) {
	do {
	 String name = cursor.getString(1);
	 String addr = cursor.getString(2);
			 
	 //顯示在Log之中
	 Log.v("sql", "name:" + name + ", addr:" + addr);
	} while (cursor.moveToNext());
   }//if (cursor.moveToFirst()) ends	 
  } catch (Exception e) {
	Log.e("sql", "error(2):" + e);
  }
 }
} 