package com.freejavaman;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

public class CanvasDrawer extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  //不要使用版面配置，而是採用自訂的View元件
  MyCanvasView view = new MyCanvasView(this);
  setContentView(view);
 }
 
 //自訂的View元件
 private class MyCanvasView extends View {
  
  //自訂View元件的建構者函數	 
  public MyCanvasView(Context context) {
   super(context);  
  }

  //覆寫執行"畫螢幕"的功能
  protected void onDraw(Canvas canvas) {
   super.onDraw(canvas);
   
   //設定背景白色
   canvas.drawColor(Color.WHITE);
   
   Paint paint = new Paint();
   paint.setAntiAlias(true); //消除鋸齒
   
   //設定空心
   paint.setStyle(Paint.Style.STROKE); 
   paint.setColor(Color.RED); //設定顏色
   paint.setStrokeWidth(8); //空心外框寬度   
   canvas.drawCircle(80, 88, 80, paint);
   
   //設定 實心
   paint.setStyle(Paint.Style.FILL); 
   paint.setColor(Color.YELLOW); //設定顏色
   canvas.drawCircle(240 + 4, 88, 80, paint);
   
   //設定漸層
   Shader shader = new LinearGradient(0,0,168,168,
		               new int[]{Color.BLUE,Color.WHITE},
		               null,Shader.TileMode.REPEAT);
   paint.setShader(shader);
   canvas.drawCircle(400 + 4, 88, 80, paint);
   
   //在畫布上顯示文字
   paint.setShader(null);
   paint.setColor(Color.GREEN);
   paint.setTextSize(68);
   canvas.drawText("我在畫布上寫字", 20, 300, paint);
  }
 }
 
 
}