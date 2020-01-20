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
  
  //���oTextView�������
  txt = (TextView)this.findViewById(R.id.txt);
  
  try {
   //���o���w��Content
   saveContext = createPackageContext("com.freejavaman", Activity.MODE_WORLD_READABLE);
	  
   //���oSharedPreferences����
   pre = saveContext.getSharedPreferences("myPreference", Activity.MODE_WORLD_READABLE);
   
  } catch (Exception e){
	Log.e("sp", "error:" + e);  
  }
 }

 protected void onStart() {
  super.onStart();
  
  //���^�Ȧs�����, ����ܪ���J��
  if (pre != null)
    txt.setText("�x�s����ơG" + pre.getString("myName", ""));
  else
	txt.setText("�x�s����ơG�L�k���o");
 } 
}