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
public class PowerNotification extends Activity {
  
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	//���oNotification�޲z����
	NotificationManager nMgr = (NotificationManager)this.getSystemService(this.NOTIFICATION_SERVICE);
	
	//�إ߭n�]�q�q�����e������
	Notification notice = new Notification();
	
	//�ϥή��ʳq��
	notice.defaults |= Notification.DEFAULT_VIBRATE;
	long[] dataArray = {0, 100, 200, 300};
	notice.vibrate = dataArray;
	
	//�o�eNotice
	Intent intent = new Intent(this, PowerNotification.class);
	PendingIntent pIntent = PendingIntent.getActivity(PowerNotification.this, 0, intent, 0);	
	notice.setLatestEventInfo(PowerNotification.this, "", "", pIntent);	
	nMgr.notify(168, notice);	 
  }
  
}