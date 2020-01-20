package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

//Avtivity��@Runnable����A�ǳƭn����t�@��Thread
public class UI_ProgressBar extends Activity implements Runnable {
	
	TextView txt;
    ProgressBar pBar;
    Button btn;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     //���o�������
     txt = (TextView)this.findViewById(R.id.txt);
     pBar = (ProgressBar)this.findViewById(R.id.pBar);        
     btn = (Button)this.findViewById(R.id.btn);
     
     //�e�U���s�Q���ƥ�
     btn.setOnClickListener(new View.OnClickListener() {		
	  public void onClick(View v) {
	   txt.setText("�u�@�}�l����");
	   
	   //���PorgressBat
	   pBar.setVisibility(View.VISIBLE);
	   
	   //�إ߰����,�åB�e�U����@Runnable����������
	   Thread myThread = new Thread(UI_ProgressBar.this);
	   myThread.start();
	  }
	 });
    }
  
  //�إ�handler����A�ǳƱ����T��  
  Handler handler = new Handler(){
   public void handleMessage(Message msg) {
	super.handleMessage(msg);
	pBar.setVisibility(View.GONE);
	txt.setText("�u�@�}�l����");
   }     
  };  
    
  //�إ߰�����A�����I�������{��  
  public void run() {
   try {
	//�Ȱ�5��A�����I���u�@���椤  
	Thread.sleep(5000);  
   }catch (Exception e) {	   
   }
   handler.sendEmptyMessage(0);
  }
}