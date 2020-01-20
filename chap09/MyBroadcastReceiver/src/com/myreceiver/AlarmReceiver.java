package com.myreceiver;

import android.content.*;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
  
  //接收到廣播的訊息	
  public void onReceive(Context context, Intent intent) {	  
   String alarmMsg = intent.getStringExtra("myAlarm");
   Toast.makeText(context, alarmMsg, Toast.LENGTH_SHORT).show();
   
   Log.v("broadcast", "收到告警訊息");
  }  
}