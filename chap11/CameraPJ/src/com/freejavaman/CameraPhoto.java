package com.freejavaman;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class CameraPhoto extends Activity  implements SurfaceHolder.Callback{
 
 private SurfaceView surface1;	
 private SurfaceHolder surface1Holder;
	
 //back-facing camera
 Camera bfCamera;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  //設定使用全螢幕顯示，一定要置於setContentView之前
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.main3);
  
  //取得SurfaceView物件
  surface1 = (SurfaceView)this.findViewById(R.id.surface1);
  
  //用來處理CallBack的物件
  surface1Holder = surface1.getHolder();
  
  surface1Holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  
  //設定處理的元件為本物件
  surface1Holder.addCallback(CameraPhoto.this);
  
  //Log.v("camera", "" + Camera.getNumberOfCameras());
  
  //進行預覽
  Button myBtn = (Button)this.findViewById(R.id.myBtn);
  myBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	CameraPhoto.this.doPreview();
   }	  
  });
  
  //進行照相
  Button myBtn2 = (Button)this.findViewById(R.id.myBtn2);
  myBtn2.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	CameraPhoto.this.takePhoto();
   }	  
  }); 
  
 }
 
 //進行照相功能
 private void takePhoto() {
  bfCamera.takePicture(shutter, raw, jpeg);  	 
 }
 
 //執行照相功能，傳入JPEG資料
 private Camera.PictureCallback jpeg = new Camera.PictureCallback(){
  public void onPictureTaken(byte[] bytes, Camera camera) {
   //將傳入的位元組資料，轉換成 Bitmap 物件  
   Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
   
   try {
	 //設定要儲存的圖檔位置與名稱
	 File imgFile = new File("/sdcard/myCamera_" + new java.util.Date().getTime() + ".jpg");
	 
	 //設定輸出資料流
	 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imgFile));
	 
	 //設定檔案格式與圖檔品質
	 bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
	 
	 //寫出檔案
	 out.flush();
	 out.close();
	 out = null;
	 
	 //再進入預覽
	 bfCamera.startPreview();
	 
   } catch (Exception e) {
	Log.e("camera", "take photo error:" + e);  
   }
  } 
 };
 
 //拍照瞬間被呼叫使用
 private Camera.ShutterCallback shutter = new Camera.ShutterCallback() {
  public void onShutter() {
  } 
 };
 
 //執行照相功能，傳入未處理的原始資料
 private Camera.PictureCallback raw = new Camera.PictureCallback(){
  public void onPictureTaken(byte[] bytes, Camera camera) {
  } 
 };
 
 //初始與開啟相機預覽
 private void doPreview() {
  //開啟相機
  bfCamera = Camera.open();
  
  if (bfCamera != null) {
   try {
	//取得相機的參數設定
	Camera.Parameters parameters = bfCamera.getParameters();
	
	//若要儲存時，格式使用JPEG
	parameters.setPictureFormat(PixelFormat.JPEG);
	
	//查詢支援的預覽大小
	/*List<Camera.Size> list = parameters.getSupportedPreviewSizes();
	for (int i = 0; i < list.size(); i++) {
	 Log.v("camera", "預覽支援，寬:" + ((Camera.Size)list.get(i)).width + "，高:" + ((Camera.Size)list.get(i)).height);
	}*/
	
	//查詢支援的圖檔大小
	/*List<Camera.Size> list = parameters.getSupportedPictureSizes();
	for (int i = 0; i < list.size(); i++) {
	 Log.v("camera", "圖檔支援，，寬:" + ((Camera.Size)list.get(i)).width + "，高:" + ((Camera.Size)list.get(i)).height);
	}*/
	
	//設定圖檔大小	
	parameters.setPictureSize(2048, 1536);
	
	//儲存設定值
	bfCamera.setParameters(parameters);
	
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

    //back-facing的旋轉角度
    int result = (info.orientation - degrees + 360) % 360;
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