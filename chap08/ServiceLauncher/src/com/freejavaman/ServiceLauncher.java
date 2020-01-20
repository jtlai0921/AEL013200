package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceLauncher extends Activity {
    
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    //�ҰʪA�Ȫ����s
    Button btn1 = (Button)this.findViewById(R.id.btn1);
    Button btn2 = (Button)this.findViewById(R.id.btn2);
    
    //�e�U�ƥ�A�ҰʪA��
    btn1.setOnClickListener(new OnClickListener(){
   	  public void onClick(View view) {
   	   try {
   		Intent intent = new Intent();
   		intent.setAction("MyServiceNickName");
   		ServiceLauncher.this.startService(intent);
   		Log.v("serviceTest", "try to start service");
   	   } catch (Exception e) {
   		Log.e("serviceTest", "try to start service, error:" + e);   		   
   	   }
	  }
    });
    
    //�e�U�ƥ�A����A��
    btn2.setOnClickListener(new OnClickListener(){
   	  public void onClick(View view) {
   	   try {
   		Intent intent = new Intent();
   		intent.setAction("MyServiceNickName");
   		ServiceLauncher.this.stopService(intent);
   		Log.v("serviceTest", "try to stop service");
   	   } catch (Exception e) {
   		Log.e("serviceTest", "try to stop service, error:" + e);   		   
   	   }
	  }
    });
    
  }
}