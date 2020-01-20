package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OneActivityTwoLayout2 extends Activity {
 	
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   //一開始使用第1個版面配置
   setContentView(R.layout.layout1);
   
   //取得按鈕的物件實體
   Button btn1 = (Button)this.findViewById(R.id.btn1);
      
   //點選按鈕1，切換使用第2個版面配置檔
   btn1.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
     displayLayout2();	  
	}
   });
  }
  
  //顯示第1個畫面
  private void displayLayout1() {
	//跳至第1個畫面  
	setContentView(R.layout.layout1);
	//取得第1個畫面中的按鈕
	Button btn1 = (Button)this.findViewById(R.id.btn1);
	
	//點選按鈕，切換使用第2個版面配置檔
	btn1.setOnClickListener(new View.OnClickListener() {
	 public void onClick(View view) {
	  displayLayout2();	  
	 }
	});   
  } 
  
  //顯示第2個畫面
  private void displayLayout2() {
	//跳至第2個畫面  
	setContentView(R.layout.layout2);
	//取得第2個畫面中的按鈕
	Button btn2 = (Button)this.findViewById(R.id.btn2);
	
	//點選按鈕，切換使用第1個版面配置檔
	btn2.setOnClickListener(new View.OnClickListener() {
	 public void onClick(View view) {
	  displayLayout1();	  
	 }
	});   
  }
 
}
