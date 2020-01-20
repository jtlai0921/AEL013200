package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SPWriter extends Activity {
 
 EditText edit;
 Button saveBtn;
 SharedPreferences pre;
 SharedPreferences.Editor preEdit;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  edit = (EditText)this.findViewById(R.id.edit);
  saveBtn = (Button)this.findViewById(R.id.saveBtn);
  
  //取得SharedPreferences物件
  pre = this.getSharedPreferences("myPreference", Activity.MODE_WORLD_WRITEABLE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_PRIVATE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_WORLD_READABLE + Activity.MODE_WORLD_WRITEABLE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_PRIVATE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_APPEND + Activity.MODE_WORLD_READABLE + Activity.MODE_WORLD_WRITEABLE);
  
  preEdit = pre.edit();
  
  //點選按鈕，執行資料儲存
  saveBtn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
  	 //取得使用者輸入的資料
	 preEdit.putString("myName", edit.getText().toString());
		  
	 //寫入暫存的資料
	 preEdit.commit();
	}
  });
 }

 protected void onStart() {
  super.onStart();
  
  //取回暫存的資料, 並顯示的輸入框
  edit.setText(pre.getString("myName", ""));
 } 
}