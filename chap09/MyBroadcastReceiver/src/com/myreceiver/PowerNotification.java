package com.myreceiver;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

//發送通知訊息的範例程式
public class PowerNotification extends Activity {
  
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	//取得Notification管理元件
	NotificationManager nMgr = (NotificationManager)this.getSystemService(this.NOTIFICATION_SERVICE);
	
	//建立要包裹通知內容的元件
	Notification notice = new Notification();
	
	//使用振動通知
	notice.defaults |= Notification.DEFAULT_VIBRATE;
	long[] dataArray = {0, 100, 200, 300};
	notice.vibrate = dataArray;
	
	//發送Notice
	Intent intent = new Intent(this, PowerNotification.class);
	PendingIntent pIntent = PendingIntent.getActivity(PowerNotification.this, 0, intent, 0);	
	notice.setLatestEventInfo(PowerNotification.this, "", "", pIntent);	
	nMgr.notify(168, notice);	 
  }
  
}