package com.mysender;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyAlarmSender extends Activity {

 private static final String MyAction = "com.mybroadcast.alarmAction";	
 private AlarmManager alarmMgr = null;
 private PendingIntent pIntent = null;
 
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main2);
   
   //取得告警系統的按鈕
   Button btn = (Button)this.findViewById(R.id.btn);
   Button btn2 = (Button)this.findViewById(R.id.btn2);
   
   //取得Alarm管理器
   alarmMgr = (AlarmManager)this.getSystemService(ALARM_SERVICE);
   
   //建立包裹廣播資訊的物件	
   Intent intent = new Intent();
	 
   //設定物件的識別碼
   intent.setAction(MyAction);
	 
   //設定要廣播的資訊
   intent.putExtra("myAlarm", "水滾了，要記得關！！");
   
   pIntent = PendingIntent.getBroadcast(MyAlarmSender.this, 0, intent, 0);
   
   //委任點選按鈕的事件(開啟告警)
   btn.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {	 
	 
     //取得系統目前時間
	 long triggerAtTime = System.currentTimeMillis(); 
	 
	 //每隔5秒
	 long interval = 5 * 1000;
	 
	 //設定Alarm通知
	 alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, interval, pIntent);
	 
	 Log.v("broadcast", "開啟告警功能");
	}  
   });   
   
   //委任點選按鈕的事件(關閉告警)
   btn2.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {	 
	 //關閉Alarm通知
	 alarmMgr.cancel(pIntent);
	 
	 Log.v("broadcast", "關閉告警功能");
	}  
   });
   
 }
}