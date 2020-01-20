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
  
  //���o��ܸIĲ�I�ƶq������
  cntTxt = (TextView)this.findViewById(R.id.cntTxt);
 }

 public boolean onTouchEvent(MotionEvent event) {
  
  //���o, �����Ĳ�I�I��
  cntTxt.setText("" + event.getPointerCount());
    
  /*
  //���ߨC�@��Ĳ�I�I 
  for (int inx = 0; inx < event.getPointerCount(); inx++) {
   //���oĲ�I�I��ID
   int pointID = event.getPointerId(inx);	  
   Log.v("touch", "pointID:" + pointID);
   
   //���o�y�и�T
   Log.v("touch", "X:" + event.getX(pointID));
   Log.v("touch", "Y:" + event.getY(pointID)); 
  }*/
    
  //�P�_���@�بƥ�o��
  switch(event.getAction()) {
   case MotionEvent.ACTION_DOWN: //�IĲ�ƥ�}�l
	    Log.v("touch", "�ƥ�}�l");
	    break;
   case MotionEvent.ACTION_UP: //�IĲ�ƥ󵲧�
	    Log.v("touch", "�ƥ󵲧�");
	    cntTxt.setText("0");
	    break;	    
   case MotionEvent.ACTION_POINTER_1_DOWN: //�Ĥ@�I���U
	    Log.v("touch", "�Ĥ@�I���U");	    
	    break;	    
   case MotionEvent.ACTION_POINTER_1_UP: //�Ĥ@�I��}
	    Log.v("touch", "�Ĥ@�I��}");
	    break;
   case MotionEvent.ACTION_POINTER_2_DOWN: //�ĤG�I���U
	    Log.v("touch", "�ĤG�I���U");
	    break;	    
   case MotionEvent.ACTION_POINTER_2_UP: //�ĤG�I��}
	    Log.v("touch", "�ĤG�I��}");
	    break;	    
   case MotionEvent.ACTION_POINTER_3_DOWN: //�ĤT�I���U
	    Log.v("touch", "�ĤT�I���U");
	    break;	    
   case MotionEvent.ACTION_POINTER_3_UP: //�ĤT�I��}
	    Log.v("touch", "�ĤT�I��}");
	    break;
   default:break;	    
  }
  
  return super.onTouchEvent(event);
 } 
}