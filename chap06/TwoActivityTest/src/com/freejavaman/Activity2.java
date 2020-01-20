package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity2 extends Activity {
 
 //�έp���h�֪������	
 /*public static int myCnt = 0;
 
 //�إߪ�������, �N�p�ƭȥ[�@
 public Activity2() {  
  super();
  synchronized(Activity2.class) {
   Activity2.myCnt++;
   Log.v("DeepActivity", "constructor Activity2 cnt:" + Activity2.myCnt);
  }
 }
 
 //�Ѻc�̨��
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
   
   //�@�}�l�ϥβ�2�Ӫ����t�m
   setContentView(R.layout.layout2);
   
   //���o���s���������
   Button btn2 = (Button)this.findViewById(R.id.btn2);
      
   //�I����s2�A�����ϥβ�1�Ӫ����t�m��
   btn2.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //�إ�Intent������� 	
     Intent intent = new Intent();
     
     //�]�wfrom�Pto��Activity
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
