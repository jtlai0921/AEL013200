package com.freejavaman;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//�ϳ�����
public class SimpleCompass extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 
 //�]�w���o�ϳ��P����
 private int sensorType = Sensor.TYPE_MAGNETIC_FIELD;
 
 private MyCompass myCompass;
 
 //�ۭq��View����
 private class MyCompass extends View {
  
  Paint paint;
  Path path;
  float mDegree;
	 
  //�ۭqView���󪺫غc�̨��	 
  public MyCompass(Context context) {
   super(context); 
   
   Paint paint = new Paint();
   paint.setAntiAlias(true); //��������
   
   //�]�w���
   paint.setStyle(Paint.Style.FILL); 
   paint.setColor(Color.BLACK); //�]�w�C��
   
   //�e�X�T���Ϊ��b�Y
   path = new Path();
   path.moveTo(0, 100);
   path.lineTo(-50, -50);
   path.lineTo(0, 0);
   path.lineTo(50, -50);
  }

  //�мg����"�e�ù�"���\��
  protected void onDraw(Canvas canvas) {
   super.onDraw(canvas);
   
   //�]�w�I���զ�
   canvas.drawColor(Color.WHITE);
   
   Matrix mat = canvas.getMatrix();
   if (mat != null) {
    mat.preRotate(180 + mDegree);
    mat.postTranslate(getWidth() / 2, getHeight() / 2);
    canvas.setMatrix(mat);
    canvas.drawPath(path, paint);
   } else {
	Log.v("sensor", "Matrix is NULL");   
   }
   
   
  }
 }
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  myCompass = new MyCompass(this);
  setContentView(myCompass);
  
  //���o�P�����޲z����
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
 }
 
 //Activity��_�ɰ���
 protected void onResume() {
  super.onResume();
  
  //���o�P����
  List<Sensor> sensors = sMgr.getSensorList(sensorType);
  
  //�i����U
  if (sensors != null && sensors.size() > 0) {
    sMgr.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);    
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
    myCompass.mDegree = (float)Math.toDegrees(Math.atan2(event.values[0], event.values[1]));
    myCompass.invalidate();
  } else {
    Log.v("sensor", "call back, but not register:" + event.sensor.getType());  
  }
 }
 
 //��@SensorEventListener, �ҥ������Ѫ����
 public void onAccuracyChanged(Sensor arg0, int arg1) {
 } 
}

