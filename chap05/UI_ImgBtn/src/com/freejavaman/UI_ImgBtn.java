package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class UI_ImgBtn extends Activity {
	
	ImageButton btn;
    TextView txt;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     btn = (ImageButton)findViewById(R.id.button1);
     txt = (TextView)findViewById(R.id.txt1);
     btn.setOnClickListener(new View.OnClickListener() {		
   		public void onClick(View arg0) {
   		 txt.setText("please press button");			
   		}
   	 });        
    }
}