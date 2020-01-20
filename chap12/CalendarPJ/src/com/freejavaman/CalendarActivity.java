package com.freejavaman;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class CalendarActivity extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
        
  //取得所有URI位置
  Uri calendarURI = CalendarContract.Calendars.CONTENT_URI;
  Uri eventURI = CalendarContract.Events.CONTENT_URI;
  Uri instanceURI = CalendarContract.Instances.CONTENT_URI;
  Uri attendURI = CalendarContract.Attendees.CONTENT_URI;
  Uri reminderURI = CalendarContract.Reminders.CONTENT_URI;
  Uri extendURI = CalendarContract.ExtendedProperties.CONTENT_URI;
  
  Log.v("content", "calendarURI:" + calendarURI.toString());
  Log.v("content", "eventURI:" + eventURI.toString());
  Log.v("content", "instanceURI:" + instanceURI.toString());
  Log.v("content", "attendURI:" + attendURI.toString());
  Log.v("content", "reminderURI:" + reminderURI.toString());
  Log.v("content", "extendURI:" + extendURI.toString());
 }
}