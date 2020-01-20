package com.myreceiver;

import android.content.*;
import android.widget.Toast;

public class PowerReceiver extends BroadcastReceiver {
  
  //接收到廣播的訊息	
  public void onReceive(Context context, Intent intent) {   
   Toast.makeText(context, "電源事件發生", Toast.LENGTH_LONG).show();
  }  
}