package com.test;

import com.freejavaman.*;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceUser extends Activity {

 TextView txt;
 EditText editW;
 EditText editH;
 Button btn;
 
 //�إ�Server�s�u������
 private ServiceConnection conn = new ServiceConnection() {

  public void onServiceConnected(ComponentName cName, IBinder iBinder) {
   try {	  
	BMIInterface bmi = BMIInterface.Stub.asInterface(iBinder);
	if (bmi != null) {	  	
	   Log.v("bmiService", "service interface is NOT null");	   
	   
	   //���o�ϥΪ̿�J���魫�P�������	 
	   float w = new Float(editW.getText().toString()).floatValue();
	   float h = new Float(editH.getText().toString()).floatValue();
	 
	   //�]�w�����魫���
	   bmi.setWeight(w);
	   bmi.setHeight(h);
	   
	   //���o�p�⵲�G
	   float bmiResult = bmi.getBMI();
	   txt.setText("BMI:" + bmiResult);	
	} else {
	 Log.v("bmiService", "service interface is NULL");
	  txt.setText("BMI:�L�k���o�A��");
	}
   } catch (android.os.RemoteException e) {
	 Log.e("bmiService", "service connection error:" + e);  
   }	
  }

  public void onServiceDisconnected(ComponentName cName) {
  }	 
 };
 
 
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
   
   txt = (TextView)this.findViewById(R.id.txt);
   editW = (EditText)this.findViewById(R.id.editW);
   editH = (EditText)this.findViewById(R.id.editH);
   btn = (Button)this.findViewById(R.id.btn);
   
   //�e�U�I��ƥ�
   btn.setOnClickListener(new OnClickListener() {
	public void onClick(View arg0) {
	 try {
	  //���o�p��BMI���A��
	  Intent intent = new Intent();
 	  intent.setAction("BMIServiceNickName");
 	  //ServiceUser.this.bindService(intent, conn, Service.BIND_AUTO_CREATE);
 	  //ServiceUser.this.bindService(intent, conn, Service.BIND_DEBUG_UNBIND)
 	  if (ServiceUser.this.bindService(intent, conn, Service.BIND_NOT_FOREGROUND)) {
 		Log.v("bmiService", "ô�����\"); 		  
 	  } else {
 		Log.v("bmiService", "ô������");
 		txt.setText("BMI:�L�k���o�A��");
 	  }
 	  	
	 } catch (Exception e) {
	  Log.e("bmiService", "use bmi service error:" + e);	 
	 }
	}	   
   });
   
   
 }
}