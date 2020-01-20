package com.freejavaman;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class EventUpdate extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try {
	  
   //取得所有URI位置
   Uri eventURI = CalendarContract.Events.CONTENT_URI;	  
   
   //設定要查詢的欄位
   String[] projection = {CalendarContract.Events._ID,
	   	                  CalendarContract.Events.TITLE,
		                  CalendarContract.Events.EVENT_LOCATION,
		                  CalendarContract.Events.DTSTART};
  
   //設定更新條件
   String where = CalendarContract.Events._ID + "=?";
   
   //設定更新條件值 
   String[] selectionArgs = {"6"};
      
   //設定要修改的欄位值
   ContentValues values = new ContentValues();
   values.put(CalendarContract.Events.EVENT_LOCATION, "社區活動中心");
   
   //取得工具元件的實體, 並執行更 新   
   ContentResolver cResolver = this.getContentResolver();
   cResolver.update(eventURI, values, where, selectionArgs);
   
  }catch (Exception e) {
   Log.e("content", "update event error:" + e);	  
  }
 }
}