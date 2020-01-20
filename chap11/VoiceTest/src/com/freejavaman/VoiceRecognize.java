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
  
  //��ܻy�����Ѫ����G
  myText = (EditText)this.findViewById(R.id.myText);
  btn = (Button)this.findViewById(R.id.btn);
  clear = (Button)this.findViewById(R.id.clear);
  
  //����y�����Ѫ����s
  btn.setOnClickListener(new View.OnClickListener() {	
   public void onClick(View v) {
	//�إ߻y�����Ѫ�Intent
	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	
	intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	
	//�]�w�@���^�ǵ��G
	intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
	
	//�y�����ѵ�������ܤ�r
	intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "�л���");
	
	//�e�XIntent, �è��o�^�ǵ��G
	VoiceRecognize.this.startActivityForResult(intent, 16888);
   }
  });
  
  //�M����r�ؤ��e  
  clear.setOnClickListener(new View.OnClickListener() {	
   public void onClick(View v) {
	myText.setText("");
   }
  });  
 }

 //�e�XIntent��, ���o�y�����ѵ��G
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	 
  //�P�_�O�_���e�X��Intent�����ѵ��G
  if (requestCode == 16888 && resultCode == this.RESULT_OK) {
   ArrayList<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
   
   StringBuffer resultStr = new StringBuffer("");
   
   for (int i = 0; i < list.size(); i++) {
	resultStr.append(list.get(i));   
   }
   
   myText.setText(resultStr);
  } else {
   myText.setText("�L���ѵ��G");  
  }
  
  super.onActivityResult(requestCode, resultCode, data);
 }
 
}

