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

//�ϳ�����
public class Sensor_Orientation extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 private TextView xTxt, yTxt, zTxt;
 
 //�x�s�[�t�P����,�Ҩ��o�����
 private float[] aValues = null;
 
 //�x�s�ϳ��P����,�Ҩ��o�����
 private float[] mValues = null;
 
 //�]�w���o�ϳ��P����
 private int sensorType = Sensor.TYPE_ALL;
 
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
  
  //���o�[�t�׷P����
  Sensor accelerometer_sensor = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
  
  //���o�ϳ��P����
  Sensor magnetic_sensor = sMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
  
  //�i���ƨ��o���U
  if (accelerometer_sensor != null && magnetic_sensor != null){
    sMgr.registerListener(this, accelerometer_sensor, SensorManager.SENSOR_DELAY_UI);
    sMgr.registerListener(this, magnetic_sensor, SensorManager.SENSOR_DELAY_UI);
  } else {   
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
  if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
    //���o�[�t�׷P���������
    aValues = (float[]) event.values.clone(); 
  } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {   
    //���o�ϳ��P���������
    mValues = (float[]) event.values.clone();      
  } else {
    Log.v("sensor", "call back, but not register:" + event.sensor.getType());  
  }
  
  checkOrientation();
 }
 
 //�i���쪺�P�_
 private void checkOrientation() {
  if (aValues != null && mValues != null) {
    float[] R = new float[9]; 
	float[] values = new float[3];
	
	//�i��}�C����
	SensorManager.getRotationMatrix(R, null, aValues, mValues);             
	
	//���o����T
	SensorManager.getOrientation(R, values); 
	
	xTxt.setText("��쨤�G" + values[0]);
	yTxt.setText("���Y���G" + values[1]);
	zTxt.setText("�u�ʨ��G" + values[2]);  
  }
 }
  
 //��@SensorEventListener, �ҥ������Ѫ����
 public void onAccuracyChanged(Sensor sensor, int arg1) {
 } 
}

