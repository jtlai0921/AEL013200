package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassActivity2 extends Activity {

 String userInput;
 Intent intent;
 Bundle bundle;
 
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   //使用第2個版面配置
   setContentView(R.layout.pass2);
   
   //取得物件實體
   TextView txt = (TextView)this.findViewById(R.id.txt);
   Button btn = (Button)this.findViewById(R.id.btn);
   
   //取得傳遞過來的Bundle物件
   intent = getIntent();
   bundle = intent.getExtras();
   
   //取得資料內容
   userInput = bundle.getString("userInput");
   
   //顯示在TextView之中
   txt.setText("使用者輸入：" + userInput);
   
   //點選按鈕，將所取得的資料加工，再傳回給Activity1
   btn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 /*//建立Intent物件實體 	
     Intent intent = new Intent();
     
     //設定from與to的Activity
     intent.setClass(PassActivity2.this, PassActivity1.class);
     
     //建立欲回傳的資料
     Bundle bundle = new Bundle();
     bundle.putString("userReturn", "return to you：" + userInput);
     
     //將資料設定至Intent物件之中
     intent.putExtras(bundle);     
     PassActivity2.this.startActivity(intent);*/
	 
	 //設定回傳的資料	 
	 bundle.putString("userReturn", "return for result：" + userInput);
	 intent.putExtras(bundle);
	 
	 Log.v("DeepActivity", "do return");
	 
	 //回傳資料
	 PassActivity2.this.setResult(16888, intent);	
     PassActivity2.this.finish();
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
