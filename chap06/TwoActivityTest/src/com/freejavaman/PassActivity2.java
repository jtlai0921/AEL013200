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
   
   //�ϥβ�2�Ӫ����t�m
   setContentView(R.layout.pass2);
   
   //���o�������
   TextView txt = (TextView)this.findViewById(R.id.txt);
   Button btn = (Button)this.findViewById(R.id.btn);
   
   //���o�ǻ��L�Ӫ�Bundle����
   intent = getIntent();
   bundle = intent.getExtras();
   
   //���o��Ƥ��e
   userInput = bundle.getString("userInput");
   
   //��ܦbTextView����
   txt.setText("�ϥΪ̿�J�G" + userInput);
   
   //�I����s�A�N�Ҩ��o����ƥ[�u�A�A�Ǧ^��Activity1
   btn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 /*//�إ�Intent������� 	
     Intent intent = new Intent();
     
     //�]�wfrom�Pto��Activity
     intent.setClass(PassActivity2.this, PassActivity1.class);
     
     //�إ߱��^�Ǫ����
     Bundle bundle = new Bundle();
     bundle.putString("userReturn", "return to you�G" + userInput);
     
     //�N��Ƴ]�w��Intent���󤧤�
     intent.putExtras(bundle);     
     PassActivity2.this.startActivity(intent);*/
	 
	 //�]�w�^�Ǫ����	 
	 bundle.putString("userReturn", "return for result�G" + userInput);
	 intent.putExtras(bundle);
	 
	 Log.v("DeepActivity", "do return");
	 
	 //�^�Ǹ��
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
