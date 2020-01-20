package com.myreceiver;

import android.content.*;
import android.os.Bundle;
import android.util.Log;

//沒有GUI，單純的廣播接收器
public class OrderedReceiver1 extends BroadcastReceiver {
  
  //接收到廣播的訊息	
  public void onReceive(Context context, Intent intent) {
   //取得收到的廣播	  
   String msg = intent.getStringExtra("myOrderedMsg");
   Log.v("broadcast", "receive(1):" + msg);
   
   //取得由上一個廣播鏈中的程式，所傳遞過來的訊息
   Bundle bundle = getResultExtras(false);
   
   if (bundle == null) {
	 Log.v("broadcast", "receive(1):無執行結果");
   } else {
	 Log.v("broadcast", "receive(1):執行結果:" + bundle.getString("myResult"));
   }   
  }
}