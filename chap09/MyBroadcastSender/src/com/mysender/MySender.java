package com.mysender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MySender extends Activity {

 private static final String MyAction1 = "com.mybroadcast.action.Action1";	
	
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
   
   //取得發送廣播系統的按鈕
   Button btn = (Button)this.findViewById(R.id.btn);
   
   //委任點選按鈕的事件
   btn.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {
	 //建立包裹廣播資訊的物件	
	 Intent intent = new Intent();
	 
	 //設定物件的識別碼
	 intent.setAction(MyAction1);
	 
	 //設定要廣播的資訊
	 intent.putExtra("myMsg", "天雨路滑，放慢車速~");
	 
	 //進行廣播
	 MySender.this.sendBroadcast(intent);
	}  
   });   
 }
}