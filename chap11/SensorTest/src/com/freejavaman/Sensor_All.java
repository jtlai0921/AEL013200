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
import android.widget.TextView;

//查詢手機共支援多少感測器
public class Sensor_All extends Activity {
 
 private SensorManager sMgr;
 private int sensorType = Sensor.TYPE_ALL;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //取得感測器管理元件
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
 }
 
 //Activity恢復時執行
 protected void onResume() {
  super.onResume();
  
  //取得感測器
  List<Sensor> sensors = sMgr.getSensorList(sensorType);
  
  Log.v("sensor", "total sensor:" + sensors.size());
  
  for (int i = 0; i < sensors.size(); i++) {
	Sensor sensor = sensors.get(i); 
	Log.v("sensor", "======================");
    Log.v("sensor", "MaximumRange:" + sensor.getMaximumRange());
	Log.v("sensor", "MinDelay:" + sensor.getMinDelay());
	Log.v("sensor", "Name():" + sensor.getName());
	Log.v("sensor", "Power:" + sensor.getPower());
	Log.v("sensor", "Resolution:" + sensor.getResolution());
	Log.v("sensor", "Type:" + checkType(sensor.getType()));
	Log.v("sensor", "Vendor:" + sensor.getVendor());
	Log.v("sensor", "Version:" + sensor.getVersion());  
  }
 } 
 
 private String checkType(int type) {
  String typeStr = "";
  
  switch(type) {
   case Sensor.TYPE_ACCELEROMETER:
	    typeStr = "ACCELEROMETER";
	    break;
   case Sensor.TYPE_GRAVITY:
	    typeStr = "GRAVITY";
        break;
   case Sensor.TYPE_GYROSCOPE:
	    typeStr = "GYROSCOPE";
        break;
   case Sensor.TYPE_LIGHT:
	    typeStr = "LIGHT";
        break;
   case Sensor.TYPE_LINEAR_ACCELERATION:
	    typeStr = "LINEAR_ACCELERATION";
        break;
   case Sensor.TYPE_MAGNETIC_FIELD:
	    typeStr = "MAGNETIC_FIELD";
        break;
   case Sensor.TYPE_ORIENTATION:
	    typeStr = "ORIENTATION";
        break;
   case Sensor.TYPE_PRESSURE:
	    typeStr = "PRESSURE";
        break;
   case Sensor.TYPE_PROXIMITY:
	    typeStr = "PROXIMITY";
        break;
   case Sensor.TYPE_ROTATION_VECTOR:
	    typeStr = "ROTATION_VECTOR";
        break;
   case Sensor.TYPE_TEMPERATURE:
	    typeStr = "TEMPERATURE";
        break;
   default:	 
	    typeStr = "unknow";
        break;
  }
  return typeStr;	 
 }
 
}