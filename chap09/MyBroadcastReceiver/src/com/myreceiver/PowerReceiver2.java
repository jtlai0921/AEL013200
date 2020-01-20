package com.myreceiver;

import android.content.*;
import android.widget.Toast;

public class PowerReceiver2 extends BroadcastReceiver {
  
  //接收到廣播的訊息	
  public void onReceive(Context context, Intent intent) {   
   
   Toast.makeText(context, "電源事件發生", Toast.LENGTH_LONG).show();
	  
   //開啟Activity, 以進行通知工作  
   Intent intent2 = new Intent();
   
   //在新task中啟動Activity
   intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   
   //設定所欲啟用的Activity
   intent2.setClass(context, PowerNotification.class);
   
   //啟動Activity
   context.startActivity(intent2);
  }  
}