package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClearEditText extends Activity {
 Button btn = null;
 EditText txt = null;
 EditText txt2 = null;
 
 public void onCreate(Bundle savedInstanceState) {	 
   super.onCreate(savedInstanceState);
   //�]�w���ϥΤ������t�m
   setContentView(R.layout.main);
        
   //�ھ�ID�A���o���s���󤧹���
   btn = (Button)findViewById(R.id.button1);
        
   //�ھ�ID�A���oEditText���󤧹���
   txt = (EditText)findViewById(R.id.editText1);
   
   //�e�U���s�ƥ�
   btn.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v) {
     txt.setText("Click Event");
     Log.d("ClearEditText", "Click Event");
    }
   });
   
   btn.setOnTouchListener(new View.OnTouchListener() {
	public boolean onTouch(View arg0, MotionEvent arg1) {
	 txt.setText("Touch Event");
	 Log.d("ClearEditText", "Touch Event");
	 return false;
	}	
   });
 }
}