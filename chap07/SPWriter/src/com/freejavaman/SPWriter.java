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
  
  //���oSharedPreferences����
  pre = this.getSharedPreferences("myPreference", Activity.MODE_WORLD_WRITEABLE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_PRIVATE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_WORLD_READABLE + Activity.MODE_WORLD_WRITEABLE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_PRIVATE);
  //pre = this.getSharedPreferences("myPreference", Activity.MODE_APPEND + Activity.MODE_WORLD_READABLE + Activity.MODE_WORLD_WRITEABLE);
  
  preEdit = pre.edit();
  
  //�I����s�A�������x�s
  saveBtn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
  	 //���o�ϥΪ̿�J�����
	 preEdit.putString("myName", edit.getText().toString());
		  
	 //�g�J�Ȧs�����
	 preEdit.commit();
	}
  });
 }

 protected void onStart() {
  super.onStart();
  
  //���^�Ȧs�����, ����ܪ���J��
  edit.setText(pre.getString("myName", ""));
 } 
}