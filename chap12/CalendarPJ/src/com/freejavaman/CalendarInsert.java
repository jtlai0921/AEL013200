package com.freejavaman;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class CalendarInsert extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try {      
  //取得行事曆位置
  Uri calendarURI = CalendarContract.Calendars.CONTENT_URI;
                 
  //為行事曆加入相關資訊
  ContentValues values = new ContentValues();
  
  values.put(CalendarContract.Calendars.ACCOUNT_NAME, "allan");
  values.put(CalendarContract.Calendars.ACCOUNT_TYPE, "allan");
  values.put(CalendarContract.Calendars.NAME, "allanCalendar");
  values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, "allanDisplayName");
  values.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.RED);
  values.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
  values.put(CalendarContract.Calendars.OWNER_ACCOUNT, "allanAcc");
  
  //加入新的行事曆
  ContentResolver cResolver = getContentResolver();
  Uri dataUri = cResolver.insert(calendarURI, values);
  Log.v("content", "insert Calendar RawURI:" + dataUri.toString());
  
  long id = ContentUris.parseId(dataUri);  
  Log.v("content", "insert Calendar id:" + id);
  }catch (Exception e) {
   Log.e("content", "insert Calendar error:" + e);	  
  }
 }
}