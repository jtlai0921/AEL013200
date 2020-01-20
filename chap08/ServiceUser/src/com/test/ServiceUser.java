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
 
 //建立Server連線的物件
 private ServiceConnection conn = new ServiceConnection() {

  public void onServiceConnected(ComponentName cName, IBinder iBinder) {
   try {	  
	BMIInterface bmi = BMIInterface.Stub.asInterface(iBinder);
	if (bmi != null) {	  	
	   Log.v("bmiService", "service interface is NOT null");	   
	   
	   //取得使用者輸入的體重與身高資料	 
	   float w = new Float(editW.getText().toString()).floatValue();
	   float h = new Float(editH.getText().toString()).floatValue();
	 
	   //設定身高體重資料
	   bmi.setWeight(w);
	   bmi.setHeight(h);
	   
	   //取得計算結果
	   float bmiResult = bmi.getBMI();
	   txt.setText("BMI:" + bmiResult);	
	} else {
	 Log.v("bmiService", "service interface is NULL");
	  txt.setText("BMI:無法取得服務");
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
   
   //委託點選事件
   btn.setOnClickListener(new OnClickListener() {
	public void onClick(View arg0) {
	 try {
	  //取得計算BMI的服務
	  Intent intent = new Intent();
 	  intent.setAction("BMIServiceNickName");
 	  //ServiceUser.this.bindService(intent, conn, Service.BIND_AUTO_CREATE);
 	  //ServiceUser.this.bindService(intent, conn, Service.BIND_DEBUG_UNBIND)
 	  if (ServiceUser.this.bindService(intent, conn, Service.BIND_NOT_FOREGROUND)) {
 		Log.v("bmiService", "繫結成功"); 		  
 	  } else {
 		Log.v("bmiService", "繫結失敗");
 		txt.setText("BMI:無法取得服務");
 	  }
 	  	
	 } catch (Exception e) {
	  Log.e("bmiService", "use bmi service error:" + e);	 
	 }
	}	   
   });
   
   
 }
}