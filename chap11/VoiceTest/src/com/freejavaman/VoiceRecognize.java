package com.freejavaman;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VoiceRecognize extends Activity {
 
 EditText myText;
 Button btn, clear;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
  
  //顯示語音辨識的結果
  myText = (EditText)this.findViewById(R.id.myText);
  btn = (Button)this.findViewById(R.id.btn);
  clear = (Button)this.findViewById(R.id.clear);
  
  //執行語音辨識的按鈕
  btn.setOnClickListener(new View.OnClickListener() {	
   public void onClick(View v) {
	//建立語音辨識的Intent
	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	
	intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	
	//設定一筆回傳結果
	intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
	
	//語音辨識視窗的顯示文字
	intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請說話");
	
	//送出Intent, 並取得回傳結果
	VoiceRecognize.this.startActivityForResult(intent, 16888);
   }
  });
  
  //清除文字框內容  
  clear.setOnClickListener(new View.OnClickListener() {	
   public void onClick(View v) {
	myText.setText("");
   }
  });  
 }

 //送出Intent後, 取得語言辨識結果
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	 
  //判斷是否為送出的Intent的辨識結果
  if (requestCode == 16888 && resultCode == this.RESULT_OK) {
   ArrayList<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
   
   StringBuffer resultStr = new StringBuffer("");
   
   for (int i = 0; i < list.size(); i++) {
	resultStr.append(list.get(i));   
   }
   
   myText.setText(resultStr);
  } else {
   myText.setText("無辨識結果");  
  }
  
  super.onActivityResult(requestCode, resultCode, data);
 }
 
}

