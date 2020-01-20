package com.freejavaman;

import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UI_TextView extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       /*TextView txt = (TextView)this.findViewById(R.id.text1); 
       txt.setText("Set text from code");
       txt.setTextColor(0x770000ff);
       txt.setTextColor(color.black);*/
    }
}