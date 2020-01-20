package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PassActivity1 extends Activity {
 
 Button btn; 
 EditText edit;
 TextView txt;
 
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   //使用第1個版面配置
   setContentView(R.layout.pass1);
   
   //取得物件實體
   btn = (Button)this.findViewById(R.id.btn);
   edit = (EditText)this.findViewById(R.id.edit);
   txt = (TextView)this.findViewById(R.id.txt);
   
   /*try {
    //取得回傳的Bundle物件
    Bundle bundle = this.getIntent().getExtras();
   
    //取得資料內容
    String userReturn = bundle.getString("userReturn");
   
    //顯示在TextView之中
    txt.setText("使用者傳回：" + userReturn);
   } catch (Exception e) {
	Log.e("DeepActivity", "error:" + e);   
   }*/
   
   //點選按鈕1，傳遞使用者輸入的資料
   btn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //建立Intent物件實體 	
     Intent intent = new Intent();
     
     //設定from與to的Activity
     intent.setClass(PassActivity1.this, PassActivity2.class);
     
     //建立欲傳遞的資料
     Bundle bundle = new Bundle();
     bundle.putString("userInput", edit.getText().toString());
     
     //將資料設定至Intent物件之中
     intent.putExtras(bundle);
     
     //PassActivity1.this.startActivity(intent);
     
     PassActivity1.this.startActivityForResult(intent, 16888);
     //PassActivity1.this.finish();
	}
   });
  }

  //取得回傳的Intent
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    Log.v("DeepActivity", "requestCode:" + requestCode);
    Log.v("DeepActivity", "resultCode:" + resultCode);
    
    //取得其中的資料
    if (resultCode == 16888) {
	  Bundle bundle = intent.getExtras();
	  String userReturn = bundle.getString("userReturn");
	  
	 //顯示在TextView之中
	 txt.setText("使用者傳回：" + userReturn);	    
    }	
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
