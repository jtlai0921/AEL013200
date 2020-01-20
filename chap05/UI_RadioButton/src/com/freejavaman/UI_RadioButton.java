package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class UI_RadioButton extends Activity {
	
	TextView txt1;
	RadioGroup group1;
	RadioButton btn1, btn2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
     
     //���o�������󤧹���
     txt1 = (TextView)findViewById(R.id.txt1);
 	 group1 = (RadioGroup)findViewById(R.id.group1);
 	 btn1 = (RadioButton)findViewById(R.id.btn1);
     btn2 = (RadioButton)findViewById(R.id.btn2);
 	 
     btn2.setChecked(true);
     
     group1.setOnCheckedChangeListener(new OnCheckedChangeListener() {		
		public void onCheckedChanged(RadioGroup group, int checkedId) {
		 if (checkedId == btn1.getId()) {
		   txt1.setText("�A��ܫ��s�@");	 
		 } else if (checkedId == btn2.getId()) {
		   txt1.setText("�A��ܫ��s�G");
		 }
		}
	 });
    }
}