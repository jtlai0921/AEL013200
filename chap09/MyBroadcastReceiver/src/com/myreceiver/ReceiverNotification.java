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
public class ReceiverNotification extends Activity {
  
  private NotificationManager nMgr = null;
  private Notification notice = null;
	
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main2);
		   
	//開啟Notification
	Button sendBtn = (Button)this.findViewById(R.id.sendBtn);
	
	//關閉Notification
	Button cancelBtn = (Button)this.findViewById(R.id.cancelBtn);
	
	//取得Notification管理元件
	nMgr = (NotificationManager)this.getSystemService(this.NOTIFICATION_SERVICE);
	
	//建立要包裹通知內容的元件
	notice = new Notification();
	
	//設定顯示於狀態欄的圖示
	notice.icon = R.drawable.icon;
	
	//設定顯示於狀態欄的提示文字
	notice.tickerText = "Notification測試";
	
	//設定顯示於狀態欄時間
	notice.when = System.currentTimeMillis();
	
	//委任點選按鈕的事件
	sendBtn.setOnClickListener(new OnClickListener(){
	  public void onClick(View view) {
		
	   Intent intent = new Intent(ReceiverNotification.this, ReceiverNotification.class);
	   PendingIntent pIntent = PendingIntent.getActivity(ReceiverNotification.this, 0, intent, 0);
	   notice.setLatestEventInfo(ReceiverNotification.this, "我是content標題", "我是content文字", pIntent);
	   
	   //發送Notice
	   nMgr.notify(168, notice);
	  }  
	});
	
	//委任點選按鈕的事件
	cancelBtn.setOnClickListener(new OnClickListener(){
	  public void onClick(View view) {
	   nMgr.cancel(168);
	  }  
	}); 
  }
  
}