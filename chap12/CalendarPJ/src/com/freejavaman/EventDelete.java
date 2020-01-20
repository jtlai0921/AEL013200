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
	  
   //���o�Ҧ�URI��m
   Uri eventURI = CalendarContract.Events.CONTENT_URI;	  
   
   //�]�w�n�d�ߪ����
   String[] projection = {CalendarContract.Events._ID};
  
   //�]�w�R������
   String where = CalendarContract.Events._ID + "=?";
   
   //�]�w�R������� 
   String[] selectionArgs = {"6"};
   
   //���o�u�㤸�󪺹���, �ð���R��   
   ContentResolver cResolver = this.getContentResolver();
   cResolver.delete(eventURI, where, selectionArgs);
   
  }catch (Exception e) {
   Log.e("content", "delete event error:" + e);	  
  }
 }
}