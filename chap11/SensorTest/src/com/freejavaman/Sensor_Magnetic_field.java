package com.freejavaman;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//磁場測試
public class Sensor_Magnetic_field extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 private TextView xTxt, yTxt, zTxt;
 
 //設定取得磁場感測器
 private int sensorType = Sensor.TYPE_MAGNETIC_FIELD;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
  
  //取得感測器管理元件
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
  
  //取得顯示結果元件
  xTxt = (TextView)this.findViewById(R.id.xTxt);
  yTxt = (TextView)this.findViewById(R.id.yTxt);
  zTxt = (TextView)this.findViewById(R.id.zTxt);
 }
 
 //Activity恢復時執行
 protected void onResume() {
  super.onResume();
  
  //取得感測器
  List<Sensor> sensors = sMgr.getSensorList(sensorType);
  
  //進行註冊
  if (sensors != null && sensors.size() > 0) {
    sMgr.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
   
    /*Log.v("sensor", "MaximumRange:" + sensors.get(0).getMaximumRange());
    Log.v("sensor", "MinDelay:" + sensors.get(0).getMinDelay());
    Log.v("sensor", "Name():" + sensors.get(0).getName());
    Log.v("sensor", "Power:" + sensors.get(0).getPower());
    Log.v("sensor", "Resolution:" + sensors.get(0).getResolution());
    Log.v("sensor", "Type:" + sensors.get(0).getType());
    Log.v("sensor", "Vendor:" + sensors.get(0).getVendor());
    Log.v("sensor", "Version:" + sensors.get(0).getVersion());*/
    //Log.v("sensor", "MaximumRange:" + sensors.get(0).getMaximumRange());
    
  } else {
   xTxt.setText("無支援之感測器");
   Log.v("sensor", "no suitable sensor");
  } 
 }
 
 //Activity停止時執行
 protected void onPause() {
  super.onPause();
  sMgr.unregisterListener(this);
 }
 
 //實作SensorEventListener, 所必須提供的函數
 public void onSensorChanged(SensorEvent event) {
  if (event.sensor.getType() == sensorType) {
	xTxt.setText("X軸 磁場：" + event.values[0] + " uT");
	yTxt.setText("Y軸 磁場：" + event.values[1] + " uT");
	zTxt.setText("Z軸 磁場：" + event.values[2] + " uT");
    //Log.v("sensor", "result X:" + event.values[0] + " uT");
    //Log.v("sensor", "result Y:" + event.values[1] + " uT");
    //Log.v("sensor", "result Z:" + event.values[2] + " uT");	
  } else {
    Log.v("sensor", "call back, but not register:" + event.sensor.getType());  
  }
 }
 
 //實作SensorEventListener, 所必須提供的函數
 public void onAccuracyChanged(Sensor arg0, int arg1) {
 } 
}

