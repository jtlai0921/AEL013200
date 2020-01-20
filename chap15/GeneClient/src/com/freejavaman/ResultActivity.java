package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

 //��ܵ��G�T��	
 public TextView txt1;
 
 //�Ƶ{���s
 private Button btn2;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
	     
  //��ܵ��G�T�����󤧹���
  txt1 = (TextView)findViewById(R.id.txt1);
  txt1.setTextSize(30);
  
  //���o���s���󤧹��� 
  btn2 = (Button)findViewById(R.id.btn2);
  
  //���o�ǻ��L�Ӫ�Bundle����
  Bundle bundle = this.getIntent().getExtras();

  //���o��Ƥ��e
  String result = bundle.getString("result");

  //��ܦbTextView����
  txt1.setText(result);

  //�e�����s�ƥ�
  btn2.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	//�^�W�@��
	Intent intent = new Intent();

	//�]�wfrom�Pto��Activity
	intent.setClass(ResultActivity.this, GeneActivity.class);
    
	//�e���e�@��Activity
	ResultActivity.this.startActivity(intent);
	ResultActivity.this.finish();
   }
  });  
 } 
}

	