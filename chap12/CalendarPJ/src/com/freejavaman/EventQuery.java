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
   //���o�u�㤸�󪺹���   
   ContentResolver cResolver = this.getContentResolver();
  
   //�]�w�n�d�ߪ����
   String[] projection = {CalendarContract.Events._ID,
	   	                  CalendarContract.Events.TITLE,
		                  CalendarContract.Events.EVENT_LOCATION,
		                  CalendarContract.Events.DTSTART};
  
   //�]�w�d�߱���
   String selection = CalendarContract.Events.DTSTART + ">=?";
  
   //�]�w�d�߱����ƭ�
   //���o��䤸��
   Calendar calendar = Calendar.getInstance();
  
   //�]�w���ʪ��}�l�ɶ�(����[�@)
   calendar.set(2011, 11, 24, 19, 00);  
   long startMillis = calendar.getTimeInMillis();
  
   String[] selectionArgs = {"" + startMillis};
  
   //����d��
   Cursor cursor = cResolver.query(CalendarContract.Events.CONTENT_URI, projection, selection, selectionArgs, null);
   
   if (cursor.moveToFirst()) {
	do {
	 //���o�����ޭ�	
	 int idInx = cursor.getColumnIndex(CalendarContract.Events._ID);
	 int titleInx = cursor.getColumnIndex(CalendarContract.Events.TITLE);
	 int locationInx = cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);
	     
	 //���o����
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