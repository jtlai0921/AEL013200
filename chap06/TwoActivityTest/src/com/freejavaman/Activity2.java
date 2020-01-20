package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity2 extends Activity {
 
 //統計有多少物件實體	
 /*public static int myCnt = 0;
 
 //建立物件實體時, 將計數值加一
 public Activity2() {  
  super();
  synchronized(Activity2.class) {
   Activity2.myCnt++;
   Log.v("DeepActivity", "constructor Activity2 cnt:" + Activity2.myCnt);
  }
 }
 
 //解構者函數
 protected void finalize() throws Throwable {
  synchronized(Activity2.class) {
   super.finalize();
   Activity2.myCnt--;
   Log.v("DeepActivity", "finalize Activity2 cnt:" + Activity2.myCnt);
  } 
 }*/
 
 public Activity2() {
  super();
  Log.v("DeepActivity", "Activity2 constructor");
 }
	
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   Log.v("DeepActivity", "Activity2 create");
   
   //一開始使用第2個版面配置
   setContentView(R.layout.layout2);
   
   //取得按鈕的物件實體
   Button btn2 = (Button)this.findViewById(R.id.btn2);
      
   //點選按鈕2，切換使用第1個版面配置檔
   btn2.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //建立Intent物件實體 	
     Intent intent = new Intent();
     
     //設定from與to的Activity
     intent.setClass(Activity2.this, Activity1.class);
     Activity2.this.startActivity(intent);
     //Activity2.this.finish();
	}
   });
  }
 
  protected void onDestroy() {
   super.onDestroy();
   Log.v("DeepActivity", "Activity2 destroy");
  }

  protected void onPause() {
   super.onPause();
   Log.v("DeepActivity", "Activity2 pause");
  }

  protected void onRestart() {
   super.onRestart();
   Log.v("DeepActivity", "Activity2 restart");
  }

  protected void onResume() {
   super.onResume();
   Log.v("DeepActivity", "Activity2 resume");
  }

  protected void onStart() {
   super.onStart();
   Log.v("DeepActivity", "Activity2 start");
  }

  protected void onStop() {
   super.onStop();
   Log.v("DeepActivity", "Activity2 stop");
  }
}
