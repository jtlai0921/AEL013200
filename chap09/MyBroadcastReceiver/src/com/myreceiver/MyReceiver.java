package com.myreceiver;

import android.content.*;
import android.util.Log;
import android.widget.Toast;

//沒有GUI，單純的廣播接收器
public class MyReceiver extends BroadcastReceiver {
  
  private static int cnt = 0;
  
  public MyReceiver() {
   super();	  
   MyReceiver.cnt++;
   Log.v("broadcast", "MyReceiver contruct, count:" + MyReceiver.cnt);
  }
  
  //接收到廣播的訊息	
  public void onReceive(Context context, Intent intent) {
   String msg = intent.getStringExtra("myMsg");
   Log.v("broadcast", "receive:" + msg);
   Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
   
   //嘗試暫停30秒，用來模擬工作超過10秒鐘的限制
   try {	   
	new Thread().sleep(1000 * 30);   
	Log.v("broadcast", "wake up");
   } catch (Exception e) {
	Log.e("broadcast", "sleep err:" + e);   
   }
  }
  
  protected void finalize() throws Throwable {
   super.finalize();
   MyReceiver.cnt--;
   Log.v("broadcast", "MyReceiver finalize, count:" + MyReceiver.cnt);
  }
}