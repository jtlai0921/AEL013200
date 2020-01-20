package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

 //顯示結果訊息	
 public TextView txt1;
 
 //排程按鈕
 private Button btn2;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
	     
  //顯示結果訊息物件之實體
  txt1 = (TextView)findViewById(R.id.txt1);
  txt1.setTextSize(30);
  
  //取得按鈕物件之實體 
  btn2 = (Button)findViewById(R.id.btn2);
  
  //取得傳遞過來的Bundle物件
  Bundle bundle = this.getIntent().getExtras();

  //取得資料內容
  String result = bundle.getString("result");

  //顯示在TextView之中
  txt1.setText(result);

  //委任按鈕事件
  btn2.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	//回上一頁
	Intent intent = new Intent();

	//設定from與to的Activity
	intent.setClass(ResultActivity.this, GeneActivity.class);
    
	//前往前一個Activity
	ResultActivity.this.startActivity(intent);
	ResultActivity.this.finish();
   }
  });  
 } 
}

	