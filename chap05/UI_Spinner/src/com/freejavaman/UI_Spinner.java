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
	
	String[] dinner = {"���\", "���\", "�t��", "����"};
	ArrayAdapter<String> adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
        
     //���o�������󤧹���
     txt1 = (TextView)findViewById(R.id.txt1);
     spin1 = (Spinner)findViewById(R.id.spin1);
     
     //�]�wSpinner���ﶵ
     adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dinner);
     spin1.setAdapter(adapter);
     
    }
}