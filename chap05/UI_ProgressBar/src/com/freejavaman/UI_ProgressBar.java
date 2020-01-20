package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

//Avtivity實作Runnable物件，準備要執行另一個Thread
public class UI_ProgressBar extends Activity implements Runnable {
	
	TextView txt;
    ProgressBar pBar;
    Button btn;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     //取得物件實體
     txt = (TextView)this.findViewById(R.id.txt);
     pBar = (ProgressBar)this.findViewById(R.id.pBar);        
     btn = (Button)this.findViewById(R.id.btn);
     
     //委託按鈕被按事件
     btn.setOnClickListener(new View.OnClickListener() {		
	  public void onClick(View v) {
	   txt.setText("工作開始執行");
	   
	   //顯示PorgressBat
	   pBar.setVisibility(View.VISIBLE);
	   
	   //建立執行緒,並且委託給實作Runnable介面的物件
	   Thread myThread = new Thread(UI_ProgressBar.this);
	   myThread.start();
	  }
	 });
    }
  
  //建立handler物件，準備接受訊息  
  Handler handler = new Handler(){
   public void handleMessage(Message msg) {
	super.handleMessage(msg);
	pBar.setVisibility(View.GONE);
	txt.setText("工作開始結束");
   }     
  };  
    
  //建立執行緒，模擬背景中的程式  
  public void run() {
   try {
	//暫停5秒，模擬背景工作執行中  
	Thread.sleep(5000);  
   }catch (Exception e) {	   
   }
   handler.sendEmptyMessage(0);
  }
}