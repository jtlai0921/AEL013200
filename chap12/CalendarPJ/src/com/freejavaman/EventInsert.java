package com.freejavaman;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class EventInsert extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try {      
  //取得所有URI位置
  Uri eventURI = CalendarContract.Events.CONTENT_URI;
                 
  //為活動加入相關資訊
  ContentValues values = new ContentValues();
  
  //取得日曆元件
  Calendar calendar = Calendar.getInstance();
  
  //設定活動的開始時間(月份加一)
  calendar.set(2011, 11, 24, 19, 00);  
  long startMillis = calendar.getTimeInMillis();
  values.put(CalendarContract.Events.DTSTART, startMillis);
  
  //設定活動的開始時間(月份加一)
  calendar.set(2011, 11, 24, 22, 00);
  long endMillis = calendar.getTimeInMillis();
  values.put(CalendarContract.Events.DTEND, endMillis);
  
  //設定活動的標題
  values.put(CalendarContract.Events.TITLE, "聖誕節聯歡晚會");
  
  //設定活動相關描述
  values.put(CalendarContract.Events.DESCRIPTION, "社區管委會將舉辦聯歡晚會");
  
  //活動舉辦地點
  values.put(CalendarContract.Events.EVENT_LOCATION, "社區中庭");
  
  //設定同步帳號(行事曆)的ID
  values.put(CalendarContract.Events.CALENDAR_ID, 1);
  
  //加入新的活動
  ContentResolver cResolver = getContentResolver();
  Uri dataUri = cResolver.insert(eventURI, values);
  Log.v("content", "insert Event Raw URI:" + dataUri.toString());
  
  //Retrieve ID for new event
  String eventID = dataUri.getLastPathSegment();
  Log.v("content", "insert Event id :" + eventID);
  
  }catch (Exception e) {
   Log.e("content", "insert error:" + e);	  
  }
 }
}