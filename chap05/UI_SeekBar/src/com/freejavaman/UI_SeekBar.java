package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class UI_SeekBar extends Activity {
	TextView txt;
	SeekBar seekbar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
     
     //取得物件實體
     txt = (TextView)this.findViewById(R.id.txt);
     seekbar = (SeekBar)this.findViewById(R.id.seekbar);
     
     //進行委任的工作
     seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		
       public void onStartTrackingTouch(SeekBar mySeekBar) {
    	txt.setText("開始轉動滾動軸");
 	   }
    	 
	   public void onStopTrackingTouch(SeekBar mySeekBar) {
		txt.setText("停止轉動滾動軸");	
	   }
		
	   public void onProgressChanged(SeekBar mySeekBar, int progress, boolean fromUser) {
		 txt.setText("執行轉動滾動軸:" + progress);
	   }
	 });
    }
}