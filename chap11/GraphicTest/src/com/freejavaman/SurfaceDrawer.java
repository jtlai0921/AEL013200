package com.freejavaman;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SurfaceDrawer extends Activity implements Runnable, SurfaceHolder.Callback {
 
 private SurfaceView myView;	
 private SurfaceHolder myHolder;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  //設定使用全螢幕顯示，一定要置於setContentView之前
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.main);
  
  //取得SurfaceView物件
  myView = (SurfaceView)this.findViewById(R.id.myView);
  
  //用來處理CallBack的物件
  myHolder = myView.getHolder();
  
  //設定處理的元件為本物件
  myHolder.addCallback(SurfaceDrawer.this);
  
  //按鈕元件
  /*Button myBtn = (Button)this.findViewById(R.id.myBtn);
  myBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	SurfaceDrawer.this.drawTest();
   }	  
  });*/
  
  //建立額外執行緒，並進行繪圖的功能
  Thread myThread = new Thread(this);
  myThread.start();
 }
 
 //獨立執行緒要進行的工作
 public void run() {
  drawTest();	 
 }	 
 
 //進行繪圖的工作
 private void drawTest() {
	 
  //待畫布準備之後，才可以開始進行畫圖
  Canvas canvas = null;  
  while (canvas == null) {
   canvas = myHolder.lockCanvas();
  }
  
  //設定背景白色
  canvas.drawColor(Color.WHITE);  
  Paint paint = new Paint();
  paint.setAntiAlias(true); //消除鋸齒
  
  //設定空心
  paint.setStyle(Paint.Style.STROKE); 
  paint.setColor(Color.RED); //設定顏色
  paint.setStrokeWidth(8); //空心外框寬度   
  canvas.drawCircle(80, 88, 80, paint);
  
  myHolder.unlockCanvasAndPost(canvas);
 }
 
 //實作SurfaceHolder.Callback介面，必須要提供的函數
 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
 }
 
 //實作SurfaceHolder.Callback介面，必須要提供的函數
 public void surfaceCreated(SurfaceHolder holder) {
 }

 //實作SurfaceHolder.Callback介面，必須要提供的函數
 public void surfaceDestroyed(SurfaceHolder holder) {
 }
 
}