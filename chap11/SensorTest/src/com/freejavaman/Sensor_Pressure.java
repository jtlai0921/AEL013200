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

//���O����
public class Sensor_Pressure extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 private TextView sensorTxt;
 
 //�]�w���o���O�P����
 private int sensorType = Sensor.TYPE_PRESSURE;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //���o�P�����޲z����
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
  
  //���o��ܵ��G����
  sensorTxt = (TextView)this.findViewById(R.id.sensorTxt);
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
    
    Log.v("sensor", "MaximumRange:" + sensors.get(0).getMaximumRange());
    
  } else {
   sensorTxt.setText("�L�䴩���P����");
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
    sensorTxt.setText("���O�G" + event.values[0] + " kilopascals");    
    Log.v("sensor", "result:" + event.values[0] + " kilopascals");
  } else {
    Log.v("sensor", "call back, but not register:" + event.sensor.getType());  
  }
 }
 
 //��@SensorEventListener, �ҥ������Ѫ����
 public void onAccuracyChanged(Sensor arg0, int arg1) {
 }
 
}