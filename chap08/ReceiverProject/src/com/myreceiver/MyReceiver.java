package com.myreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
 
 //����s�����T������A��ܦb�ù�����	
 public void onReceive(Context context, Intent intent) {  	 
  String msg = intent.getStringExtra("broadcastMsg");
  Log.v("broadcastMsg", "receive:" + msg);
  Toast.makeText(context, msg, Toast.LENGTH_LONG);
 } 	
}