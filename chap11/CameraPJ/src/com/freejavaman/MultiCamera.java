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
  
  //設定使用全螢幕顯示，一定要置於setContentView之前
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.main2);
  
  //取得SurfaceView物件
  surface2 = (SurfaceView)this.findViewById(R.id.surface2);
  
  //用來處理CallBack的物件
  surface2Holder = surface2.getHolder();
  
  surface2Holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  
  //設定處理的元件為本物件
  surface2Holder.addCallback(MultiCamera.this);
    
  Button myBtn = (Button)this.findViewById(R.id.myBtn);
  myBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	MultiCamera.this.doPreview();
   }	  
  });  
 }
 
 //初始與開啟相機預覽
 public void doPreview() {   
  //開啟視訊鏡頭
  ffCamera = Camera.open(1);  
  if (ffCamera != null) {
   try {	
	//取得手機的旋轉角度，以計算預覽的旋轉角度
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
    
    //front-facing, 採用鏡射效果
    int result = (info.orientation + degrees) % 360;
    result = (360 - result) % 360;    
    ffCamera.setDisplayOrientation(result);
	
	//設定預覽的SurfaceView
    ffCamera.setPreviewDisplay(surface2Holder);
    
    //開始預覽的工作
    ffCamera.startPreview();
    Log.v("camera", "start to preview");
    
   } catch (Exception e) {
	Log.e("camera", "open camera error:" + e);
   }
  }  
 } 
 
 //在pause時，釋放相機
 protected void onPause() {
  super.onPause();
  
  if (ffCamera != null) {
   try {	  
    ffCamera.stopPreview();//關閉預覽
    ffCamera.release(); //釋放資源
   }catch (Exception e) {} 
  }
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