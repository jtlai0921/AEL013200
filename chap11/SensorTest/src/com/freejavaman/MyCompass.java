package com.freejavaman;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//�ϳ�����
public class MyCompass extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 
 //�x�s�[�t�P����,�Ҩ��o�����
 private float[] aValues = null;
 
 //�x�s�ϳ��P����,�Ҩ��o�����
 private float[] mValues = null;
 
 //�]�w���o�ϳ��P����
 private int sensorType = Sensor.TYPE_ALL;
 
 private CompassView compassView;
 private float degree;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  compassView = new CompassView(this);  
  setContentView(compassView);
  
  //���o�P�����޲z����
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
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
	
	degree = (float)Math.toDegrees(values[0]);
	
	if (compassView != null)
	 compassView.invalidate();        
  }
 }
  
 //��@SensorEventListener, �ҥ������Ѫ����
 public void onAccuracyChanged(Sensor sensor, int arg1) {
 }
 
 //�e�X���_�w��View
 private class CompassView extends View {
  private Paint   mPaint = new Paint();
  private Path    mPath = new Path();
  private boolean mAnimate;
  private long    mNextTime;

  public CompassView(Context context) {
    super(context);
    //ø�s�b�Y
    mPath.moveTo(0, -50);
    mPath.lineTo(-20, 60);
    mPath.lineTo(0, 50);
    mPath.lineTo(20, 60);
    mPath.close();
  }
 
  protected void onDraw(Canvas canvas) {
    Paint paint = mPaint;
    canvas.drawColor(Color.WHITE);
         
    paint.setAntiAlias(true);
    paint.setColor(Color.BLACK);
    paint.setStyle(Paint.Style.FILL);

    int w = canvas.getWidth();
    int h = canvas.getHeight();
    int cx = w / 2;
    int cy = h / 2;
    
    //�ھڶǤJ�����סA�ץ��P����b�Y
    canvas.translate(cx, cy);
    canvas.rotate(-degree);
    canvas.drawPath(mPath, mPaint);
  }
 
  protected void onAttachedToWindow() {
   mAnimate = true;
   super.onAttachedToWindow();
  }
     
  protected void onDetachedFromWindow() {
   mAnimate = false;
   super.onDetachedFromWindow();
  }
 }
 
}

