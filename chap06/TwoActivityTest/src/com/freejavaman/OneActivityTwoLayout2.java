package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OneActivityTwoLayout2 extends Activity {
 	
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   //�@�}�l�ϥβ�1�Ӫ����t�m
   setContentView(R.layout.layout1);
   
   //���o���s���������
   Button btn1 = (Button)this.findViewById(R.id.btn1);
      
   //�I����s1�A�����ϥβ�2�Ӫ����t�m��
   btn1.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
     displayLayout2();	  
	}
   });
  }
  
  //��ܲ�1�ӵe��
  private void displayLayout1() {
	//���ܲ�1�ӵe��  
	setContentView(R.layout.layout1);
	//���o��1�ӵe���������s
	Button btn1 = (Button)this.findViewById(R.id.btn1);
	
	//�I����s�A�����ϥβ�2�Ӫ����t�m��
	btn1.setOnClickListener(new View.OnClickListener() {
	 public void onClick(View view) {
	  displayLayout2();	  
	 }
	});   
  } 
  
  //��ܲ�2�ӵe��
  private void displayLayout2() {
	//���ܲ�2�ӵe��  
	setContentView(R.layout.layout2);
	//���o��2�ӵe���������s
	Button btn2 = (Button)this.findViewById(R.id.btn2);
	
	//�I����s�A�����ϥβ�1�Ӫ����t�m��
	btn2.setOnClickListener(new View.OnClickListener() {
	 public void onClick(View view) {
	  displayLayout1();	  
	 }
	});   
  }
 
}
