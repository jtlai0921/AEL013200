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

//磁場測試
public class SimpleCompass extends Activity
                                implements SensorEventListener
{
 
 private SensorManager sMgr;
 
 //設定取得磁場感測器
 private int sensorType = Sensor.TYPE_MAGNETIC_FIELD;
 
 private MyCompass myCompass;
 
 //自訂的View元件
 private class MyCompass extends View {
  
  Paint paint;
  Path path;
  float mDegree;
	 
  //自訂View元件的建構者函數	 
  public MyCompass(Context context) {
   super(context); 
   
   Paint paint = new Paint();
   paint.setAntiAlias(true); //消除鋸齒
   
   //設定實心
   paint.setStyle(Paint.Style.FILL); 
   paint.setColor(Color.BLACK); //設定顏色
   
   //畫出三角形的箭頭
   path = new Path();
   path.moveTo(0, 100);
   path.lineTo(-50, -50);
   path.lineTo(0, 0);
   path.lineTo(50, -50);
  }

  //覆寫執行"畫螢幕"的功能
  protected void onDraw(Canvas canvas) {
   super.onDraw(canvas);
   
   //設定背景白色
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
  
  //取得感測器管理元件
  sMgr = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
 }
 
 //Activity恢復時執行
 protected void onResume() {
  super.onResume();
  
  //取得感測器
  List<Sensor> sensors = sMgr.getSensorList(sensorType);
  
  //進行註冊
  if (sensors != null && sensors.size() > 0) {
    sMgr.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);    
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
    myCompass.mDegree = (float)Math.toDegrees(Math.atan2(event.values[0], event.values[1]));
    myCompass.invalidate();
  } else {
    Log.v("sensor", "call back, but not register:" + event.sensor.getType());  
  }
 }
 
 //實作SensorEventListener, 所必須提供的函數
 public void onAccuracyChanged(Sensor arg0, int arg1) {
 } 
}

