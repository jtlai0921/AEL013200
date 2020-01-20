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
   //�إ߸�Ʈw
   //SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.freejavaman/mydb.db", null);
   SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/sdcard/mydb.db", null);
	  
   //�إ߸�ƪ��
   String sql = "CREATE TABLE passenger(" +
   		                                "_id integer primary key autoincrement," +
   		                                "name text," +
   		                                "addr text)";
   try {
     db.execSQL(sql);
   } catch (Exception e){
	 Log.e("sql", "error(1):" + e);  
   }
   
   //�s�W�@�����
   /*sql = "INSERT INTO passenger(name, addr) VALUES('Allan', 'TPE')";
   db.execSQL(sql);
   
   //�s�W�@�����
   ContentValues values = new ContentValues();
   values.put("name", "freejavaman");
   values.put("addr", "BAC");
   db.insert("passenger", null, values);*/
   
   Log.v("sql", "==========================================");
   
   //�d�ߩҦ����
   /*String[] columns = {"name,addr"};
   String selection = "name=? AND addr=?";   
   String[] selectionArgs = {"Allan","TPE"};
   Cursor cursor = db.query("passenger", columns, selection, selectionArgs, null, null, null);*/
   Cursor cursor = db.query("passenger", null, null, null, null, null, null);
   //db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
   
   //����Ĥ@�����
   Log.v("sql", "cursor.getCount():" + cursor.getCount());
   
   if (cursor.moveToFirst()) {
	do {	 
	 String name = cursor.getString(1);
	 String addr = cursor.getString(2);
	 
	 //��ܦbLog����
	 Log.v("sql", "name:" + name + ", addr:" + addr);
	} while (cursor.moveToNext());
   }//if (cursor.moveToFirst()) ends
   
   //�R�����, ��k�@
   /*sql = "DELETE FROM passenger WHERE addr='BAC'";
   db.execSQL(sql);*/
   
   //�R�����, ��k�G
   String whereClause = "addr=?";
   String[] whereArgs = {"TPE"};
   db.delete("passenger", whereClause, whereArgs);
   
   //�ק���, ��k�@
   //sql = "UPDATE passenger set name='Allan' WHERE name='freejavaman'";
   //db.execSQL(sql);
   
   //�ק���, ��k�G
   //ContentValues values = new ContentValues();
   /*values.put("name", "Allan"); //�]�w����
   
   //�]�w�ק諸where����
   String whereClause = "name=? AND addr=?";   
   String[] whereArgs = {"freejavaman","BAC"};
   db.update("passenger", values, whereClause, whereArgs);*/
   
   db.close();
   
  } catch (Exception e) {
   Log.e("sql", "error(2):" + e);
  }
 }
}