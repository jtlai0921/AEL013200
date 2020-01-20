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

//磁場測試
public class MyCompass extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 
 //儲存加速感測器,所取得的資料
 private float[] aValues = null;
 
 //儲存磁場感測器,所取得的資料
 private float[] mValues = null;
 
 //設定取得磁場感測器
 private int sensorType = Sensor.TYPE_ALL;
 
 private CompassView compassView;
 private float degree;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  compassView = new CompassView(this);  
  setContentView(compassView);
  
  //取得感測器管理元件
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
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
	
	degree = (float)Math.toDegrees(values[0]);
	
	if (compassView != null)
	 compassView.invalidate();        
  }
 }
  
 //實作SensorEventListener, 所必須提供的函數
 public void onAccuracyChanged(Sensor sensor, int arg1) {
 }
 
 //畫出指北針的View
 private class CompassView extends View {
  private Paint   mPaint = new Paint();
  private Path    mPath = new Path();
  private boolean mAnimate;
  private long    mNextTime;

  public CompassView(Context context) {
    super(context);
    //繪製箭頭
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
    
    //根據傳入的角度，修正與旋轉箭頭
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

