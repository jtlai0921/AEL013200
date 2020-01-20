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
   
   //一開始使用第1個版面配置
   setContentView(R.layout.layout1);
   
   //取得按鈕的物件實體
   btn1 = (Button)this.findViewById(R.id.btn1);
      
   //點選按鈕1，切換使用第2個版面配置檔
   btn1.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //選擇使用版面2	
	 setContentView(R.layout.layout2);	 
	 Button btn2 = (Button)OneActivityTwoLayout.this.findViewById(R.id.btn2);
	 
	 //點選按鈕2，切換使用第1個版面配置檔
	 btn2.setOnClickListener(new View.OnClickListener() {
	  public void onClick(View view) {
	   //選擇使用版面1
	   setContentView(R.layout.layout1);
	   
	   //再一次取得版面配置檔中的按鈕
	   Button btn1_1 = (Button)OneActivityTwoLayout.this.findViewById(R.id.btn1);
	   
	   if (btn1 == btn1_1)
		Log.v("MyTest", "its' same");
	   else
		Log.v("MyTest", "its' NOT same");
	   
	   //....無窮盡
	  }
	 }); 
	}
   });
  }
}
