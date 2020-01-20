package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class UI_ACTextView extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
     
     AutoCompleteTextView actView = (AutoCompleteTextView)this.findViewById(R.id.actView);
     
     //建立範本資料
     String[] sampleFile = {"d", "do", "do-r", "do-ra", "do-ra-m", "do-ra-mi"};
     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,sampleFile);
     
     //設定範本資料至AutoCompleteTextView
     actView.setAdapter(adapter);
    }
}