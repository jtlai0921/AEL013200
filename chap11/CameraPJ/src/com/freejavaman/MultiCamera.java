package com.freejavaman;

import java.util.List;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MultiCamera extends Activity  implements SurfaceHolder.Callback{
 
 private SurfaceView surface2;	
 private SurfaceHolder surface2Holder;
 
 //front-facing camera
 Camera ffCamera;
  
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  //�]�w�ϥΥ��ù���ܡA�@�w�n�m��setContentView���e
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.main2);
  
  //���oSurfaceView����
  surface2 = (SurfaceView)this.findViewById(R.id.surface2);
  
  //�ΨӳB�zCallBack������
  surface2Holder = surface2.getHolder();
  
  surface2Holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  
  //�]�w�B�z�����󬰥�����
  surface2Holder.addCallback(MultiCamera.this);
    
  Button myBtn = (Button)this.findViewById(R.id.myBtn);
  myBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	MultiCamera.this.doPreview();
   }	  
  });  
 }
 
 //��l�P�}�Ҭ۾��w��
 public void doPreview() {   
  //�}�ҵ��T���Y
  ffCamera = Camera.open(1);  
  if (ffCamera != null) {
   try {	
	//���o��������ਤ�סA�H�p��w�������ਤ��
	Camera.CameraInfo info = new Camera.CameraInfo();
    Camera.getCameraInfo(1, info);
    int rotation = getWindowManager().getDefaultDisplay().getRotation();
    int degrees = 0;
    switch (rotation) {
      case Surface.ROTATION_0: degrees = 0; break;
      case Surface.ROTATION_90: degrees = 90; break;
      case Surface.ROTATION_180: degrees = 180; break;
      case Surface.ROTATION_270: degrees = 270; break;
    }
    
    //front-facing, �ĥ���g�ĪG
    int result = (info.orientation + degrees) % 360;
    result = (360 - result) % 360;    
    ffCamera.setDisplayOrientation(result);
	
	//�]�w�w����SurfaceView
    ffCamera.setPreviewDisplay(surface2Holder);
    
    //�}�l�w�����u�@
    ffCamera.startPreview();
    Log.v("camera", "start to preview");
    
   } catch (Exception e) {
	Log.e("camera", "open camera error:" + e);
   }
  }  
 } 
 
 //�bpause�ɡA����۾�
 protected void onPause() {
  super.onPause();
  
  if (ffCamera != null) {
   try {	  
    ffCamera.stopPreview();//�����w��
    ffCamera.release(); //����귽
   }catch (Exception e) {} 
  }
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