package com.myreceiver;

import android.content.*;
import android.widget.Toast;

public class PowerReceiver extends BroadcastReceiver {
  
  //������s�����T��	
  public void onReceive(Context context, Intent intent) {   
   Toast.makeText(context, "�q���ƥ�o��", Toast.LENGTH_LONG).show();
  }  
}