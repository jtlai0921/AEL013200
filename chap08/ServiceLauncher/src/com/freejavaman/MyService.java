package com.freejavaman;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{

  //必須要實作的成員函數
  public IBinder onBind(Intent arg0) {
	Log.v("serviceTest", "service bind");
	return null;	
  }

  public void onCreate() {
   super.onCreate();
   Log.v("serviceTest", "service create");
  }

  public void onStart(Intent intent, int startId) {
   super.onStart(intent, startId);
   Log.v("serviceTest", "service start");
  }
  
  public void onDestroy() {
   super.onDestroy();
   Log.v("serviceTest", "service destroy");
  }  
}
