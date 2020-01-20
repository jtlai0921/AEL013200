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

//�㦳GUI�A�i�H�M�w�O�_�}�������s���ƥ�
public class ReceiverSwitch extends Activity {
  
  private IntentFilter filter = null;
  private MyReceiver receiver = null;
	
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
		   
	//�}�ұ����s�������s
	Button onBtn = (Button)this.findViewById(R.id.onBtn);
	
	//���������s�������s
	Button offBtn = (Button)this.findViewById(R.id.offBtn);
	
	//�e���I����s���ƥ�
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
	
	//�e���I����s���ƥ�
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