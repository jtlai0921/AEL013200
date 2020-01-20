package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class UI_Spinner extends Activity {
	
	TextView txt1;	
	Spinner spin1;
	
	String[] dinner = {"中餐", "西餐", "速食", "素食"};
	ArrayAdapter<String> adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
        
     //取得相關物件之實體
     txt1 = (TextView)findViewById(R.id.txt1);
     spin1 = (Spinner)findViewById(R.id.spin1);
     
     //設定Spinner的選項
     adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dinner);
     spin1.setAdapter(adapter);
     
    }
}