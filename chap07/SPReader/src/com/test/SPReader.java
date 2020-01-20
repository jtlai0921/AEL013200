package com.test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SPReader extends Activity {
 
 TextView txt;
 Context saveContext;
 SharedPreferences pre;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //取得TextView物件實體
  txt = (TextView)this.findViewById(R.id.txt);
  
  try {
   //取得指定的Content
   saveContext = createPackageContext("com.freejavaman", Activity.MODE_WORLD_READABLE);
	  
   //取得SharedPreferences物件
   pre = saveContext.getSharedPreferences("myPreference", Activity.MODE_WORLD_READABLE);
   
  } catch (Exception e){
	Log.e("sp", "error:" + e);  
  }
 }

 protected void onStart() {
  super.onStart();
  
  //取回暫存的資料, 並顯示的輸入框
  if (pre != null)
    txt.setText("儲存的資料：" + pre.getString("myName", ""));
  else
	txt.setText("儲存的資料：無法取得");
 } 
}