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
  
  //�]�w�ϥΥ��ù���ܡA�@�w�n�m��setContentView���e
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.main);
  
  //���oSurfaceView����
  surface1 = (SurfaceView)this.findViewById(R.id.surface1);
  
  //�ΨӳB�zCallBack������
  surface1Holder = surface1.getHolder();
  
  surface1Holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  
  //�]�w�B�z�����󬰥�����
  surface1Holder.addCallback(CameraTest.this);
  
  //Log.v("camera", "" + Camera.getNumberOfCameras());
  
  //�i��w��
  Button myBtn = (Button)this.findViewById(R.id.myBtn);
  myBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	CameraTest.this.doPreview();
   }	  
  });  
 }
 
 //��l�P�}�Ҭ۾��w��
 public void doPreview() {
  //�}�Ҭ۾�
  bfCamera = Camera.open();
  
  if (bfCamera != null) {
   try {
	//���o�۾����ѼƳ]�w
	Camera.Parameters parameters = bfCamera.getParameters();
	
	//�Y�n�x�s�ɡA�榡�ϥ�JPEG
	//parameters.setPictureFormat(PixelFormat.JPEG);
	
	//�d�ߤ䴩���w���j�p
	List<Camera.Size> list = parameters.getSupportedPreviewSizes();
	for (int i = 0; i < list.size(); i++) {
	 Log.v("camera", "�w���䴩�A�e:" + ((Camera.Size)list.get(i)).width + "�A��:" + ((Camera.Size)list.get(i)).height);
	}
	
	//�d�ߤ䴩�����ɤj�p
	list = parameters.getSupportedPictureSizes();
	for (int i = 0; i < list.size(); i++) {
	 Log.v("camera", "���ɤ䴩�A�A�e:" + ((Camera.Size)list.get(i)).width + "�A��:" + ((Camera.Size)list.get(i)).height);
	}
	
	//�]�w�w���榡
	//setPreviewSize(int width, int height)
	//parameters.setPreviewSize(512, 384);
	//parameters.setPreviewSize(272, 272);
	
	//�]�w���ɤj�p
	//setPictureSize(int width, int height)
	//parameters.setPictureSize(512, 384);
	
	//�x�s�]�w��
	//bfCamera.setParameters(parameters);
	
	//���o��������ਤ�סA�H�p��w�������ਤ��
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
	
	//�]�w�w����SurfaceView
    bfCamera.setPreviewDisplay(surface1Holder);
    
    //�}�l�w�����u�@
    bfCamera.startPreview();
    Log.v("camera", "start to preview");
    
   } catch (Exception e) {
	Log.e("camera", "open camera error:" + e);
   }
  }
 } 
 
 //�bpause�ɡA����۾�
 protected void onPause() {
  super.onPause();  
  if (bfCamera != null) {
   bfCamera.stopPreview();//�����w��
   bfCamera.release(); //����귽
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