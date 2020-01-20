package com.myreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//具有GUI，可以決定是否開關接收廣播事件
public class ReceiverSwitch extends Activity {
  
  private IntentFilter filter = null;
  private MyReceiver receiver = null;
	
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
		   
	//開啟接收廣播的按鈕
	Button onBtn = (Button)this.findViewById(R.id.onBtn);
	
	//關閉接收廣播的按鈕
	Button offBtn = (Button)this.findViewById(R.id.offBtn);
	
	//委任點選按鈕的事件
	onBtn.setOnClickListener(new OnClickListener(){
	  public void onClick(View view) {
		
	   if (ReceiverSwitch.this.filter == null)
		 filter = new IntentFilter("com.mybroadcast.action.Action1");
		  
	   if (ReceiverSwitch.this.receiver == null)
	    receiver = new MyReceiver();
	     
	   Log.v("broadcast", "registerReceiver");
	   ReceiverSwitch.this.registerReceiver(receiver, filter);
	  }  
	});
	
	//委任點選按鈕的事件
	offBtn.setOnClickListener(new OnClickListener(){
	  public void onClick(View view) {
	   try {	  
	     ReceiverSwitch.this.unregisterReceiver(receiver);
	     Log.v("broadcast", "unregisterReceiver");
	   } catch (java.lang.IllegalArgumentException e) {
		 Log.e("broadcast", "receiverSwitch:" + e);
	   }
	  }  
	}); 
  }
  
}