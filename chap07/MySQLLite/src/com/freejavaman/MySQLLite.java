package com.freejavaman;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MySQLLite extends Activity {
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  try {
   //建立資料庫
   //SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.freejavaman/mydb.db", null);
   SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/sdcard/mydb.db", null);
	  
   //建立資料表格
   String sql = "CREATE TABLE passenger(" +
   		                                "_id integer primary key autoincrement," +
   		                                "name text," +
   		                                "addr text)";
   try {
     db.execSQL(sql);
   } catch (Exception e){
	 Log.e("sql", "error(1):" + e);  
   }
   
   //新增一筆資料
   /*sql = "INSERT INTO passenger(name, addr) VALUES('Allan', 'TPE')";
   db.execSQL(sql);
   
   //新增一筆資料
   ContentValues values = new ContentValues();
   values.put("name", "freejavaman");
   values.put("addr", "BAC");
   db.insert("passenger", null, values);*/
   
   Log.v("sql", "==========================================");
   
   //查詢所有資料
   /*String[] columns = {"name,addr"};
   String selection = "name=? AND addr=?";   
   String[] selectionArgs = {"Allan","TPE"};
   Cursor cursor = db.query("passenger", columns, selection, selectionArgs, null, null, null);*/
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
   
   //刪除資料, 方法一
   /*sql = "DELETE FROM passenger WHERE addr='BAC'";
   db.execSQL(sql);*/
   
   //刪除資料, 方法二
   String whereClause = "addr=?";
   String[] whereArgs = {"TPE"};
   db.delete("passenger", whereClause, whereArgs);
   
   //修改資料, 方法一
   //sql = "UPDATE passenger set name='Allan' WHERE name='freejavaman'";
   //db.execSQL(sql);
   
   //修改資料, 方法二
   //ContentValues values = new ContentValues();
   /*values.put("name", "Allan"); //設定欄位值
   
   //設定修改的where條件
   String whereClause = "name=? AND addr=?";   
   String[] whereArgs = {"freejavaman","BAC"};
   db.update("passenger", values, whereClause, whereArgs);*/
   
   db.close();
   
  } catch (Exception e) {
   Log.e("sql", "error(2):" + e);
  }
 }
}