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
	  
   //���o�Ҧ�URI��m
   Uri eventURI = CalendarContract.Events.CONTENT_URI;	  
   
   //�]�w�n�d�ߪ����
   String[] projection = {CalendarContract.Events._ID,
	   	                  CalendarContract.Events.TITLE,
		                  CalendarContract.Events.EVENT_LOCATION,
		                  CalendarContract.Events.DTSTART};
  
   //�]�w��s����
   String where = CalendarContract.Events._ID + "=?";
   
   //�]�w��s����� 
   String[] selectionArgs = {"6"};
      
   //�]�w�n�ק諸����
   ContentValues values = new ContentValues();
   values.put(CalendarContract.Events.EVENT_LOCATION, "���Ϭ��ʤ���");
   
   //���o�u�㤸�󪺹���, �ð���� �s   
   ContentResolver cResolver = this.getContentResolver();
   cResolver.update(eventURI, values, where, selectionArgs);
   
  }catch (Exception e) {
   Log.e("content", "update event error:" + e);	  
  }
 }
}