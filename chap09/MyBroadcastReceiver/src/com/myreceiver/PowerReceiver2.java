package com.myreceiver;

import android.content.*;
import android.widget.Toast;

public class PowerReceiver2 extends BroadcastReceiver {
  
  //������s�����T��	
  public void onReceive(Context context, Intent intent) {   
   
   Toast.makeText(context, "�q���ƥ�o��", Toast.LENGTH_LONG).show();
	  
   //�}��Activity, �H�i��q���u�@  
   Intent intent2 = new Intent();
   
   //�b�stask���Ұ�Activity
   intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   
   //�]�w�ұ��ҥΪ�Activity
   intent2.setClass(context, PowerNotification.class);
   
   //�Ұ�Activity
   context.startActivity(intent2);
  }  
}