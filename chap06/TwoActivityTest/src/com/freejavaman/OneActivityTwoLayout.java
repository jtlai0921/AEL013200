package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OneActivityTwoLayout extends Activity {
 
	Button btn1;
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   //�@�}�l�ϥβ�1�Ӫ����t�m
   setContentView(R.layout.layout1);
   
   //���o���s���������
   btn1 = (Button)this.findViewById(R.id.btn1);
      
   //�I����s1�A�����ϥβ�2�Ӫ����t�m��
   btn1.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //��ܨϥΪ���2	
	 setContentView(R.layout.layout2);	 
	 Button btn2 = (Button)OneActivityTwoLayout.this.findViewById(R.id.btn2);
	 
	 //�I����s2�A�����ϥβ�1�Ӫ����t�m��
	 btn2.setOnClickListener(new View.OnClickListener() {
	  public void onClick(View view) {
	   //��ܨϥΪ���1
	   setContentView(R.layout.layout1);
	   
	   //�A�@�����o�����t�m�ɤ������s
	   Button btn1_1 = (Button)OneActivityTwoLayout.this.findViewById(R.id.btn1);
	   
	   if (btn1 == btn1_1)
		Log.v("MyTest", "its' same");
	   else
		Log.v("MyTest", "its' NOT same");
	   
	   //....�L�a��
	  }
	 }); 
	}
   });
  }
}
