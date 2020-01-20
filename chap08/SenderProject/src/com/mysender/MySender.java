package com.mysender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MySender extends Activity {
 
 private static final String action1 = "com.mysender.ACTION1";	
	
  public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
		  
   Button btn = (Button)this.findViewById(R.id.btn);
   btn.setOnClickListener(new OnClickListener(){
    //按下按鈕會執行的工作
	public void onClick(View view) {
	 //建立intent物件	
	 Intent intent = new Intent();
	 
	 //設定訊息及其內容
	 intent.setAction(action1);
	 String msg = "天雨路滑，小心安全";
	 intent.putExtra("broadcastMsg", msg);
	 
	 Log.v("broadcastMsg", msg);
	 
	 //進行廣播
	 MySender.this.sendBroadcast(intent);
	} 
  });
 }
}