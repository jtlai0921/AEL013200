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
  //���o�Ҧ�URI��m
  Uri eventURI = CalendarContract.Events.CONTENT_URI;
                 
  //�����ʥ[�J������T
  ContentValues values = new ContentValues();
  
  //���o��䤸��
  Calendar calendar = Calendar.getInstance();
  
  //�]�w���ʪ��}�l�ɶ�(����[�@)
  calendar.set(2011, 11, 24, 19, 00);  
  long startMillis = calendar.getTimeInMillis();
  values.put(CalendarContract.Events.DTSTART, startMillis);
  
  //�]�w���ʪ��}�l�ɶ�(����[�@)
  calendar.set(2011, 11, 24, 22, 00);
  long endMillis = calendar.getTimeInMillis();
  values.put(CalendarContract.Events.DTEND, endMillis);
  
  //�]�w���ʪ����D
  values.put(CalendarContract.Events.TITLE, "�t�ϸ`�p�w�߷|");
  
  //�]�w���ʬ����y�z
  values.put(CalendarContract.Events.DESCRIPTION, "���Ϻީe�|�N�|���p�w�߷|");
  
  //�����|��a�I
  values.put(CalendarContract.Events.EVENT_LOCATION, "���Ϥ��x");
  
  //�]�w�P�B�b��(��ƾ�)��ID
  values.put(CalendarContract.Events.CALENDAR_ID, 1);
  
  //�[�J�s������
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