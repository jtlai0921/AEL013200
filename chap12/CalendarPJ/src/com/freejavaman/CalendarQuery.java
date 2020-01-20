package com.freejavaman;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class CalendarQuery extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try {      
   //取得日曆位置
   Uri calendarURI = CalendarContract.Calendars.CONTENT_URI;
  
   //取得工具元件的實體 
   ContentResolver cResolver = this.getContentResolver();
  
   //設定要查詢的欄位
   String[] projection = {CalendarContract.Calendars._ID,
                          CalendarContract.Calendars.ACCOUNT_NAME,
                          CalendarContract.Calendars.ACCOUNT_TYPE,
                          CalendarContract.Calendars.NAME,
                          CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                          CalendarContract.Calendars.CALENDAR_COLOR,
                          CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
                          CalendarContract.Calendars.OWNER_ACCOUNT};
  
   //執行查詢
   Cursor cursor = cResolver.query(calendarURI, projection, null, null, null);
  
   if (cursor.moveToFirst()) {
    do {
     //取得欄位索引值	
	 int idInx = cursor.getColumnIndex(CalendarContract.Calendars._ID);
	 int accNameInx = cursor.getColumnIndex(CalendarContract.Calendars.ACCOUNT_NAME);
	 int accTypeInx = cursor.getColumnIndex(CalendarContract.Calendars.ACCOUNT_TYPE);
	 int nameInx = cursor.getColumnIndex(CalendarContract.Calendars.NAME);
	 int displayNameInx = cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME);
	 int colorInx = cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_COLOR);
	 int accessInx = cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL);
	 int ownerAccInx = cursor.getColumnIndex(CalendarContract.Calendars.OWNER_ACCOUNT);
     
	 //取得欄位值
	 String id = cursor.getString(idInx);	 
	 String accName = cursor.getString(accNameInx);	 
	 String accType = cursor.getString(accTypeInx);
	 String name = cursor.getString(nameInx);
	 String displayName = cursor.getString(displayNameInx);
	 String color = cursor.getString(colorInx);
	 String access = cursor.getString(accessInx);
	 String ownerAcc = cursor.getString(ownerAccInx);
	 
	 Log.v("content", "id:" + id);
	 Log.v("content", "accName:" + accName);	 
	 Log.v("content", "accType:" + accType);
	 Log.v("content", "name:" + name);
	 Log.v("content", "displayName:" + displayName);
	 Log.v("content", "color:" + color);
	 Log.v("content", "access:" + chkLevel(Integer.parseInt(access)));
	 Log.v("content", "ownerAcc:" + ownerAcc);
	 Log.v("content", "======================");
	
    } while (cursor.moveToNext()); 
   } else {
    Log.v("content", "no calendar data");	  
   }
  }catch (Exception e) {
   Log.e("content", "query Calendar error:" + e);	  
  }
 }
 
 private String chkLevel(int lvl) {
  String lvlStr = "";	 
  switch(lvl) {	 
  case CalendarContract.Calendars.CAL_ACCESS_CONTRIBUTOR:
	   lvlStr = "CONTRIBUTOR";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_EDITOR:
	   lvlStr = "EDITOR";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_FREEBUSY:
	   lvlStr = "FREEBUSY";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_NONE:
	   lvlStr = "NONE";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_OVERRIDE:
	   lvlStr = "OVERRIDE";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_OWNER:
	   lvlStr = "OWNER";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_READ:
	   lvlStr = "READ";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_RESPOND:
	   lvlStr = "RESPOND";
	   break;
  case CalendarContract.Calendars.CAL_ACCESS_ROOT:
	   lvlStr = "ROOT";
	   break;
  default: lvlStr = "unknow";
  }
  return lvlStr;
 }
 
 
 
}