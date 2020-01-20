package com.freejavaman;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class UI_PickerDialog extends Activity {
    
	TextView txt1, txt2;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
     
     //�إ�TextView�PButton�������
     txt1 = (TextView)this.findViewById(R.id.txt1);
     txt2 = (TextView)this.findViewById(R.id.txt2);
     Button btn1 = (Button)this.findViewById(R.id.btn1);
     Button btn2 = (Button)this.findViewById(R.id.btn2);
     
     //�I����s�ɡA���DatePickerDialog
     btn1.setOnClickListener(new View.OnClickListener() {
      //�I��Ĥ@�ӫ��s
      public void onClick(View view) {
    	 //�إ� DatePickerDialog���������
    	 new DatePickerDialog(UI_PickerDialog.this, new OnDateSetListener() {
    		//����Q�]�w���
			public void onDateSet(DatePicker dPicker, int yyyy, int mm, int dd) {
			 txt1.setText("�A��ܪ�����G" + yyyy + "/" + (mm + 1) + "/" + dd);
			}
		}, 2011, 5, 8).show();
	  }
	 });
     
     //�I����s�ɡA���TimePickerDialog
     btn2.setOnClickListener(new View.OnClickListener() {
       //�I��ĤG�ӫ��s	 
       public void onClick(View view) {
    	 //�إ� TimePickerDialog���������
       	 new TimePickerDialog(UI_PickerDialog.this, new OnTimeSetListener() {
       	    //�ɶ��Q�]�w���
   			public void onTimeSet(TimePicker tPicker, int hh, int mn) {
   			 txt2.setText("�A��ܪ��ɶ��G" + hh + ":" + mn);
   			}
   	    }, 13, 14, true).show();
   	  }
   	 });
     
    }
}