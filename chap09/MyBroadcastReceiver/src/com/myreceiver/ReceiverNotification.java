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

//�o�e�q���T�����d�ҵ{��
public class ReceiverNotification extends Activity {
  
  private NotificationManager nMgr = null;
  private Notification notice = null;
	
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main2);
		   
	//�}��Notification
	Button sendBtn = (Button)this.findViewById(R.id.sendBtn);
	
	//����Notification
	Button cancelBtn = (Button)this.findViewById(R.id.cancelBtn);
	
	//���oNotification�޲z����
	nMgr = (NotificationManager)this.getSystemService(this.NOTIFICATION_SERVICE);
	
	//�إ߭n�]�q�q�����e������
	notice = new Notification();
	
	//�]�w��ܩ󪬺A�檺�ϥ�
	notice.icon = R.drawable.icon;
	
	//�]�w��ܩ󪬺A�檺���ܤ�r
	notice.tickerText = "Notification����";
	
	//�]�w��ܩ󪬺A��ɶ�
	notice.when = System.currentTimeMillis();
	
	//�e���I����s���ƥ�
	sendBtn.setOnClickListener(new OnClickListener(){
	  public void onClick(View view) {
		
	   Intent intent = new Intent(ReceiverNotification.this, ReceiverNotification.class);
	   PendingIntent pIntent = PendingIntent.getActivity(ReceiverNotification.this, 0, intent, 0);
	   notice.setLatestEventInfo(ReceiverNotification.this, "�ڬOcontent���D", "�ڬOcontent��r", pIntent);
	   
	   //�o�eNotice
	   nMgr.notify(168, notice);
	  }  
	});
	
	//�e���I����s���ƥ�
	cancelBtn.setOnClickListener(new OnClickListener(){
	  public void onClick(View view) {
	   nMgr.cancel(168);
	  }  
	}); 
  }
  
}