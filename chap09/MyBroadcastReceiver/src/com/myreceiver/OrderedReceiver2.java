package com.myreceiver;

import android.content.*;
import android.os.Bundle;
import android.util.Log;

//沒有GUI，單純的廣播接收器
public class OrderedReceiver2 extends BroadcastReceiver {
  
  //接收到廣播的訊息	
  public void onReceive(Context context, Intent intent) {
   //取得收到的廣播	  
   String msg = intent.getStringExtra("myOrderedMsg");
   Log.v("broadcast", "receive(2):" + msg);
   
   //準備傳遞給下一個接收程式的訊息
   Bundle bundle = new Bundle();
   bundle.putString("myResult", "11 x 8 = 88");
   setResultExtras(bundle);
  }
}