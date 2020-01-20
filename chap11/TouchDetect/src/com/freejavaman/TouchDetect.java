package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class TouchDetect extends Activity {
 
 TextView cntTxt;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //取得顯示碰觸點數量的元件
  cntTxt = (TextView)this.findViewById(R.id.cntTxt);
 }

 public boolean onTouchEvent(MotionEvent event) {
  
  //取得, 並顯示觸碰點數
  cntTxt.setText("" + event.getPointerCount());
    
  /*
  //輪詢每一個觸碰點 
  for (int inx = 0; inx < event.getPointerCount(); inx++) {
   //取得觸碰點的ID
   int pointID = event.getPointerId(inx);	  
   Log.v("touch", "pointID:" + pointID);
   
   //取得座標資訊
   Log.v("touch", "X:" + event.getX(pointID));
   Log.v("touch", "Y:" + event.getY(pointID)); 
  }*/
    
  //判斷那一種事件發生
  switch(event.getAction()) {
   case MotionEvent.ACTION_DOWN: //碰觸事件開始
	    Log.v("touch", "事件開始");
	    break;
   case MotionEvent.ACTION_UP: //碰觸事件結束
	    Log.v("touch", "事件結束");
	    cntTxt.setText("0");
	    break;	    
   case MotionEvent.ACTION_POINTER_1_DOWN: //第一點按下
	    Log.v("touch", "第一點按下");	    
	    break;	    
   case MotionEvent.ACTION_POINTER_1_UP: //第一點放開
	    Log.v("touch", "第一點放開");
	    break;
   case MotionEvent.ACTION_POINTER_2_DOWN: //第二點按下
	    Log.v("touch", "第二點按下");
	    break;	    
   case MotionEvent.ACTION_POINTER_2_UP: //第二點放開
	    Log.v("touch", "第二點放開");
	    break;	    
   case MotionEvent.ACTION_POINTER_3_DOWN: //第三點按下
	    Log.v("touch", "第三點按下");
	    break;	    
   case MotionEvent.ACTION_POINTER_3_UP: //第三點放開
	    Log.v("touch", "第三點放開");
	    break;
   default:break;	    
  }
  
  return super.onTouchEvent(event);
 } 
}