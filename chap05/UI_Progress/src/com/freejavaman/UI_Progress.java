package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UI_Progress extends Activity {
    
    TextView txt;
    ProgressBar pBar;
    Button btn;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
     //取得物件實體
     txt = (TextView)this.findViewById(R.id.txt);
     pBar = (ProgressBar)this.findViewById(R.id.pBar);        
     btn = (Button)this.findViewById(R.id.btn);
       
    }
}