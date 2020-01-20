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

//�[�t�״���
public class Sensor_Accelerometer extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 private TextView xTxt, yTxt, zTxt;
 
 //�]�w���o�[�t�׷P����
 private int sensorType = Sensor.TYPE_ACCELEROMETER;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
  
  //���o�P�����޲z����
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
  
  //���o��ܵ��G����
  xTxt = (TextView)this.findViewById(R.id.xTxt);
  yTxt = (TextView)this.findViewById(R.id.yTxt);
  zTxt = (TextView)this.findViewById(R.id.zTxt);
 }
 
 //Activity��_�ɰ���
 protected void onResume() {
  super.onResume();
  
  //���o�P����
  List<Sensor> sensors = sMgr.getSensorList(sensorType);
  
  //�i����U
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
   xTxt.setText("�L�䴩���P����");
   Log.v("sensor", "no suitable sensor");
  } 
 }
 
 //Activity����ɰ���
 protected void onPause() {
  super.onPause();
  sMgr.unregisterListener(this);
 }
 
 //��@SensorEventListener, �ҥ������Ѫ����
 public void onSensorChanged(SensorEvent event) {
  if (event.sensor.getType() == sensorType) {
	xTxt.setText("X �b�[�t�סG" + event.values[0] + " m/s^2");
	yTxt.setText("Y �b�[�t�סG" + event.values[1] + " m/s^2");
	zTxt.setText("Z �b�[�t�סG" + event.values[2] + " m/s^2");
    //Log.v("sensor", "result X:" + event.values[0] + " m/s^2");
    //Log.v("sensor", "result Y:" + event.values[1] + " m/s^2");
    //Log.v("sensor", "result Z:" + event.values[2] + " m/s^2");
	
	//�����w�z
	double result = 0;
	result += Math.pow(event.values[0], 2.0);
	result += Math.pow(event.values[1], 2.0);
	result += Math.pow(event.values[2], 2.0);
	result = Math.sqrt(result);
	Log.v("sensor", "result:" + result);
	
  } else {
    Log.v("sensor", "call back, but not register:" + event.sensor.getType());  
  }
 }
 
 //��@SensorEventListener, �ҥ������Ѫ����
 public void onAccuracyChanged(Sensor arg0, int arg1) {
 }
 
}