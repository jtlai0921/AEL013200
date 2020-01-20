package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BMIServiceLauncher extends Activity {
    
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main2);
    
    try {
   	 Intent intent = new Intent();
   	 intent.setAction("BMIServiceNickName");
   	 BMIServiceLauncher.this.startService(intent);
   	 Log.v("bmiService", "try to start service");
   	} catch (Exception e) {
   	 Log.e("bmiService", "try to start service, error:" + e);   		   
   	}	
   	
   	//¡Ù¬√Activity
   	this.finish();
  }
}