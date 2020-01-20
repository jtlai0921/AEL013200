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
  
  //�]�w�ϥΥ��ù���ܡA�@�w�n�m��setContentView���e
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.main);
  
  //���oSurfaceView����
  myView = (SurfaceView)this.findViewById(R.id.myView);
  
  //�ΨӳB�zCallBack������
  myHolder = myView.getHolder();
  
  //�]�w�B�z�����󬰥�����
  myHolder.addCallback(SurfaceDrawer.this);
  
  //���s����
  /*Button myBtn = (Button)this.findViewById(R.id.myBtn);
  myBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	SurfaceDrawer.this.drawTest();
   }	  
  });*/
  
  //�إ��B�~������A�öi��ø�Ϫ��\��
  Thread myThread = new Thread(this);
  myThread.start();
 }
 
 //�W�߰�����n�i�檺�u�@
 public void run() {
  drawTest();	 
 }	 
 
 //�i��ø�Ϫ��u�@
 private void drawTest() {
	 
  //�ݵe���ǳƤ���A�~�i�H�}�l�i��e��
  Canvas canvas = null;  
  while (canvas == null) {
   canvas = myHolder.lockCanvas();
  }
  
  //�]�w�I���զ�
  canvas.drawColor(Color.WHITE);  
  Paint paint = new Paint();
  paint.setAntiAlias(true); //��������
  
  //�]�w�Ť�
  paint.setStyle(Paint.Style.STROKE); 
  paint.setColor(Color.RED); //�]�w�C��
  paint.setStrokeWidth(8); //�Ťߥ~�ؼe��   
  canvas.drawCircle(80, 88, 80, paint);
  
  myHolder.unlockCanvasAndPost(canvas);
 }
 
 //��@SurfaceHolder.Callback�����A�����n���Ѫ����
 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
 }
 
 //��@SurfaceHolder.Callback�����A�����n���Ѫ����
 public void surfaceCreated(SurfaceHolder holder) {
 }

 //��@SurfaceHolder.Callback�����A�����n���Ѫ����
 public void surfaceDestroyed(SurfaceHolder holder) {
 }
 
}