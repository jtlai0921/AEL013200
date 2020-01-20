package com.freejavaman;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class EventQuery extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try {
   //取得工具元件的實體   
   ContentResolver cResolver = this.getContentResolver();
  
   //設定要查詢的欄位
   String[] projection = {CalendarContract.Events._ID,
	   	                  CalendarContract.Events.TITLE,
		                  CalendarContract.Events.EVENT_LOCATION,
		                  CalendarContract.Events.DTSTART};
  
   //設定查詢條件
   String selection = CalendarContract.Events.DTSTART + ">=?";
  
   //設定查詢條件資料值
   //取得日曆元件
   Calendar calendar = Calendar.getInstance();
  
   //設定活動的開始時間(月份加一)
   calendar.set(2011, 11, 24, 19, 00);  
   long startMillis = calendar.getTimeInMillis();
  
   String[] selectionArgs = {"" + startMillis};
  
   //執行查詢
   Cursor cursor = cResolver.query(CalendarContract.Events.CONTENT_URI, projection, selection, selectionArgs, null);
   
   if (cursor.moveToFirst()) {
	do {
	 //取得欄位索引值	
	 int idInx = cursor.getColumnIndex(CalendarContract.Events._ID);
	 int titleInx = cursor.getColumnIndex(CalendarContract.Events.TITLE);
	 int locationInx = cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);
	     
	 //取得欄位值
	 String id = cursor.getString(idInx);	 
	 String title = cursor.getString(titleInx);	 
	 String location = cursor.getString(locationInx);
		 
	 Log.v("content", "event id:" + id);
	 Log.v("content", "event title:" + title);	 
	 Log.v("content", "event location:" + location);
	 Log.v("content", "======================");
    } while (cursor.moveToNext()); 
   } else {
    Log.v("content", "no event data");	  
   }
  }catch (Exception e) {
   Log.e("content", "query event error:" + e);	  
  }
 }
}