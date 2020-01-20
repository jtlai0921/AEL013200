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
     
     //���o�������
     txt = (TextView)this.findViewById(R.id.txt);
     seekbar = (SeekBar)this.findViewById(R.id.seekbar);
     
     //�i��e�����u�@
     seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		
       public void onStartTrackingTouch(SeekBar mySeekBar) {
    	txt.setText("�}�l��ʺu�ʶb");
 	   }
    	 
	   public void onStopTrackingTouch(SeekBar mySeekBar) {
		txt.setText("������ʺu�ʶb");	
	   }
		
	   public void onProgressChanged(SeekBar mySeekBar, int progress, boolean fromUser) {
		 txt.setText("������ʺu�ʶb:" + progress);
	   }
	 });
    }
}