package com.freejavaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity1 extends Activity {
 
 //�έp���h�֪������
 /*public static int myCnt = 0;
 
 //�إߪ�������, �N�p�ƭȥ[�@
 public Activity1() {
  super();
  synchronized(Activity1.class) {
   Activity1.myCnt++;
   Log.v("DeepActivity", "constructor Activity1 cnt:" + Activity1.myCnt);
  } 
 }
 
 //�Ѻc�̨��
 protected void finalize() throws Throwable {
  super.finalize();
  synchronized(Activity1.class) {
   Activity1.myCnt--;
   Log.v("DeepActivity", "finalize Activity1 cnt:" + Activity1.myCnt);
  } 
 }*/
 
 public Activity1() {
  super();
  Log.v("DeepActivity", "Activity1 constructor");
 }
	
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   
   Log.v("DeepActivity", "Activity1 create");
   
   //�@�}�l�ϥβ�1�Ӫ����t�m
   setContentView(R.layout.layout1);
   
   //���o���s���������
   Button btn1 = (Button)this.findViewById(R.id.btn1);
      
   //�I����s1�A�����ϥβ�2�Ӫ����t�m��
   btn1.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 //�إ�Intent������� 	
     Intent intent = new Intent();
     
     //�]�wfrom�Pto��Activity
     intent.setClass(Activity1.this, Activity2.class);
     Activity1.this.startActivity(intent);
     //Activity1.this.finish();
	}
   });
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
