package com.freejavaman;

import com.freejavaman.BMIInterface.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BMIService extends Service{
	
  private Stub bmi = new BMIImplement();
	
  //�����n��@���������
  public IBinder onBind(Intent arg0) {
	Log.v("bmiService", "service bind");
	return bmi;	
  } 
}
