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
   
   //�ϥβ�1�Ӫ����t�m
   setContentView(R.layout.pass1);
   
   //���o�������
   btn = (Button)this.findViewById(R.id.btn);
   edit = (EditText)this.findViewById(R.id.edit);
   txt = (TextView)this.findViewById(R.id.txt);
   
   /*try {
    //���o�^�Ǫ�Bundle����
    Bundle bundle = this.getIntent().getExtras();
   
    //���o��Ƥ��e
    String userReturn = bundle.getString("userReturn");
   
    //��ܦbTextView����
    txt.setText("�ϥΪ̶Ǧ^�G" + userReturn);
   } catch (Exception e) {
	Log.e("DeepActivity", "error:" + e);   
   }*/
   
   //�I����s1�A�ǻ��ϥΪ̿�J�����
   btn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //�إ�Intent������� 	
     Intent intent = new Intent();
     
     //�]�wfrom�Pto��Activity
     intent.setClass(PassActivity1.this, PassActivity2.class);
     
     //�إ߱��ǻ������
     Bundle bundle = new Bundle();
     bundle.putString("userInput", edit.getText().toString());
     
     //�N��Ƴ]�w��Intent���󤧤�
     intent.putExtras(bundle);
     
     //PassActivity1.this.startActivity(intent);
     
     PassActivity1.this.startActivityForResult(intent, 16888);
     //PassActivity1.this.finish();
	}
   });
  }

  //���o�^�Ǫ�Intent
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    Log.v("DeepActivity", "requestCode:" + requestCode);
    Log.v("DeepActivity", "resultCode:" + resultCode);
    
    //���o�䤤�����
    if (resultCode == 16888) {
	  Bundle bundle = intent.getExtras();
	  String userReturn = bundle.getString("userReturn");
	  
	 //��ܦbTextView����
	 txt.setText("�ϥΪ̶Ǧ^�G" + userReturn);	    
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
