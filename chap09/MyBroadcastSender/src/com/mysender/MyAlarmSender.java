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
   
   //���o�iĵ�t�Ϊ����s
   Button btn = (Button)this.findViewById(R.id.btn);
   Button btn2 = (Button)this.findViewById(R.id.btn2);
   
   //���oAlarm�޲z��
   alarmMgr = (AlarmManager)this.getSystemService(ALARM_SERVICE);
   
   //�إߥ]�q�s����T������	
   Intent intent = new Intent();
	 
   //�]�w�����ѧO�X
   intent.setAction(MyAction);
	 
   //�]�w�n�s������T
   intent.putExtra("myAlarm", "���u�F�A�n�O�o���I�I");
   
   pIntent = PendingIntent.getBroadcast(MyAlarmSender.this, 0, intent, 0);
   
   //�e���I����s���ƥ�(�}�ҧiĵ)
   btn.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {	 
	 
     //���o�t�Υثe�ɶ�
	 long triggerAtTime = System.currentTimeMillis(); 
	 
	 //�C�j5��
	 long interval = 5 * 1000;
	 
	 //�]�wAlarm�q��
	 alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, interval, pIntent);
	 
	 Log.v("broadcast", "�}�ҧiĵ�\��");
	}  
   });   
   
   //�e���I����s���ƥ�(�����iĵ)
   btn2.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {	 
	 //����Alarm�q��
	 alarmMgr.cancel(pIntent);
	 
	 Log.v("broadcast", "�����iĵ�\��");
	}  
   });
   
 }
}