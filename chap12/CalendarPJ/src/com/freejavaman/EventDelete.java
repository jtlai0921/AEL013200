package com.freejavaman;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class EventDelete extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try {
	  
   //取得所有URI位置
   Uri eventURI = CalendarContract.Events.CONTENT_URI;	  
   
   //設定要查詢的欄位
   String[] projection = {CalendarContract.Events._ID};
  
   //設定刪除條件
   String where = CalendarContract.Events._ID + "=?";
   
   //設定刪除條件值 
   String[] selectionArgs = {"6"};
   
   //取得工具元件的實體, 並執行刪除   
   ContentResolver cResolver = this.getContentResolver();
   cResolver.delete(eventURI, where, selectionArgs);
   
  }catch (Exception e) {
   Log.e("content", "delete event error:" + e);	  
  }
 }
}