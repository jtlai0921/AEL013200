package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class UI_SlidingDrawer extends Activity {
    
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main2);
     
     //取得物件實體
     SlidingDrawer sDrawer = (SlidingDrawer)this.findViewById(R.id.myDrawer);
     
     //委任抽屜打開事件
     sDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener(){		
	  public void onDrawerOpened() {
	   Log.v("UI_SlidingDrawer", "抽屜打開");		
	  } 
     });
     
     //委任抽屜關閉事件
     sDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener(){		
	  public void onDrawerClosed() {
	   Log.v("UI_SlidingDrawer", "抽屜關閉");		
	  } 
     });
    }
}