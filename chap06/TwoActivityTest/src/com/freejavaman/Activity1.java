package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity1 extends Activity {
 
 //統計有多少物件實體
 /*public static int myCnt = 0;
 
 //建立物件實體時, 將計數值加一
 public Activity1() {
  super();
  synchronized(Activity1.class) {
   Activity1.myCnt++;
   Log.v("DeepActivity", "constructor Activity1 cnt:" + Activity1.myCnt);
  } 
 }
 
 //解構者函數
 protected void finalize() throws Throwable {
  super.finalize();
  synchronized(Activity1.class) {
   Activity1.myCnt--;
   Log.v("DeepActivity", "finalize Activity1 cnt:" + Activity1.myCnt);
  } 
 }*/
 
 public Activity1() {
  super();
  Log.v("DeepActivity", "Activity1 constructor");
 }
	
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   Log.v("DeepActivity", "Activity1 create");
   
   //一開始使用第1個版面配置
   setContentView(R.layout.layout1);
   
   //取得按鈕的物件實體
   Button btn1 = (Button)this.findViewById(R.id.btn1);
      
   //點選按鈕1，切換使用第2個版面配置檔
   btn1.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //建立Intent物件實體 	
     Intent intent = new Intent();
     
     //設定from與to的Activity
     intent.setClass(Activity1.this, Activity2.class);
     Activity1.this.startActivity(intent);
     //Activity1.this.finish();
	}
   });
  }
 
  protected void onDestroy() {
   super.onDestroy();
   Log.v("DeepActivity", "Activity1 destroy");
  }

  protected void onPause() {
   super.onPause();
   Log.v("DeepActivity", "Activity1 pause");
  }

  protected void onRestart() {
   super.onRestart();
   Log.v("DeepActivity", "Activity1 restart");
  }

  protected void onResume() {
   super.onResume();
   Log.v("DeepActivity", "Activity1 resume");
  }

  protected void onStart() {
   super.onStart();
   Log.v("DeepActivity", "Activity1 start");
  }

  protected void onStop() {
   super.onStop();
   Log.v("DeepActivity", "Activity1 stop");
  }
}
