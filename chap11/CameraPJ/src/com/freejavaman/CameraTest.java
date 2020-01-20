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

public class CameraTest extends Activity  implements SurfaceHolder.Callback{
 
 private SurfaceView surface1;	
 private SurfaceHolder surface1Holder;
	
 //back-facing camera
 Camera bfCamera;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  //設定使用全螢幕顯示，一定要置於setContentView之前
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.main);
  
  //取得SurfaceView物件
  surface1 = (SurfaceView)this.findViewById(R.id.surface1);
  
  //用來處理CallBack的物件
  surface1Holder = surface1.getHolder();
  
  surface1Holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  
  //設定處理的元件為本物件
  surface1Holder.addCallback(CameraTest.this);
  
  //Log.v("camera", "" + Camera.getNumberOfCameras());
  
  //進行預覽
  Button myBtn = (Button)this.findViewById(R.id.myBtn);
  myBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	CameraTest.this.doPreview();
   }	  
  });  
 }
 
 //初始與開啟相機預覽
 public void doPreview() {
  //開啟相機
  bfCamera = Camera.open();
  
  if (bfCamera != null) {
   try {
	//取得相機的參數設定
	Camera.Parameters parameters = bfCamera.getParameters();
	
	//若要儲存時，格式使用JPEG
	//parameters.setPictureFormat(PixelFormat.JPEG);
	
	//查詢支援的預覽大小
	List<Camera.Size> list = parameters.getSupportedPreviewSizes();
	for (int i = 0; i < list.size(); i++) {
	 Log.v("camera", "預覽支援，寬:" + ((Camera.Size)list.get(i)).width + "，高:" + ((Camera.Size)list.get(i)).height);
	}
	
	//查詢支援的圖檔大小
	list = parameters.getSupportedPictureSizes();
	for (int i = 0; i < list.size(); i++) {
	 Log.v("camera", "圖檔支援，，寬:" + ((Camera.Size)list.get(i)).width + "，高:" + ((Camera.Size)list.get(i)).height);
	}
	
	//設定預覽格式
	//setPreviewSize(int width, int height)
	//parameters.setPreviewSize(512, 384);
	//parameters.setPreviewSize(272, 272);
	
	//設定圖檔大小
	//setPictureSize(int width, int height)
	//parameters.setPictureSize(512, 384);
	
	//儲存設定值
	//bfCamera.setParameters(parameters);
	
	//取得手機的旋轉角度，以計算預覽的旋轉角度
	Camera.CameraInfo info = new Camera.CameraInfo();
    Camera.getCameraInfo(0, info);
    int rotation = getWindowManager().getDefaultDisplay().getRotation();
    int degrees = 0;
    switch (rotation) {
      case Surface.ROTATION_0: degrees = 0; break;
      case Surface.ROTATION_90: degrees = 90; break;
      case Surface.ROTATION_180: degrees = 180; break;
      case Surface.ROTATION_270: degrees = 270; break;
    }

    int result;
    //back-facing
    result = (info.orientation - degrees + 360) % 360;
    
    /*if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
     result = (info.orientation + degrees) % 360;
     result = (360 - result) % 360;  // compensate the mirror
    } else {  // back-facing
     result = (info.orientation - degrees + 360) % 360;
    }*/
    bfCamera.setDisplayOrientation(result);
	
	//設定預覽的SurfaceView
    bfCamera.setPreviewDisplay(surface1Holder);
    
    //開始預覽的工作
    bfCamera.startPreview();
    Log.v("camera", "start to preview");
    
   } catch (Exception e) {
	Log.e("camera", "open camera error:" + e);
   }
  }
 } 
 
 //在pause時，釋放相機
 protected void onPause() {
  super.onPause();  
  if (bfCamera != null) {
   bfCamera.stopPreview();//關閉預覽
   bfCamera.release(); //釋放資源
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