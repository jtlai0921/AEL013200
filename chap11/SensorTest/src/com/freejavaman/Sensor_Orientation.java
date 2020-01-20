package com.freejavaman;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//磁場測試
public class Sensor_Orientation extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 private TextView xTxt, yTxt, zTxt;
 
 //儲存加速感測器,所取得的資料
 private float[] aValues = null;
 
 //儲存磁場感測器,所取得的資料
 private float[] mValues = null;
 
 //設定取得磁場感測器
 private int sensorType = Sensor.TYPE_ALL;
 
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
  
  //取得加速度感測器
  Sensor accelerometer_sensor = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
  
  //取得磁場感測器
  Sensor magnetic_sensor = sMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
  
  //進行資料取得註冊
  if (accelerometer_sensor != null && magnetic_sensor != null){
    sMgr.registerListener(this, accelerometer_sensor, SensorManager.SENSOR_DELAY_UI);
    sMgr.registerListener(this, magnetic_sensor, SensorManager.SENSOR_DELAY_UI);
  } else {   
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
  if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
    //取得加速度感測器的資料
    aValues = (float[]) event.values.clone(); 
  } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {   
    //取得磁場感測器的資料
    mValues = (float[]) event.values.clone();      
  } else {
    Log.v("sensor", "call back, but not register:" + event.sensor.getType());  
  }
  
  checkOrientation();
 }
 
 //進行方位的判斷
 private void checkOrientation() {
  if (aValues != null && mValues != null) {
    float[] R = new float[9]; 
	float[] values = new float[3];
	
	//進行陣列旋轉
	SensorManager.getRotationMatrix(R, null, aValues, mValues);             
	
	//取得方位資訊
	SensorManager.getOrientation(R, values); 
	
	xTxt.setText("方位角：" + values[0]);
	yTxt.setText("投擲角：" + values[1]);
	zTxt.setText("滾動角：" + values[2]);  
  }
 }
  
 //實作SensorEventListener, 所必須提供的函數
 public void onAccuracyChanged(Sensor sensor, int arg1) {
 } 
}

