package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class UI_Picker extends Activity {
	
	TextView txt1, txt2;
	DatePicker dPicker;
	TimePicker tPicker;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
     
     //���o�������󤧹���
     txt1 = (TextView)findViewById(R.id.txt1);
     txt2 = (TextView)findViewById(R.id.txt2);
     dPicker = (DatePicker)findViewById(R.id.dPicker);
     tPicker = (TimePicker)findViewById(R.id.tPicker);
     
     //�]�w���
     //dPicker.updateDate(2011, 6, 8);
     
     //�]�w�Ұʮɪ�����A�H�Ωe�~�ƥ�B�z
     dPicker.init(2011, 5, 8, new DatePicker.OnDateChangedListener(){		
   	  public void onDateChanged(DatePicker datePicker, int yyyy, int mm, int dd) {
   	   txt1.setText("�A��ܪ�����G" + yyyy + "/" + (mm + 1) + "/" + dd);	  
   	  }    	 
     });
     
     //�]�w�ɶ�
     tPicker.setCurrentHour(new Integer(13)); 
     tPicker.setCurrentMinute(new Integer(14));
     
     //�]�w�H24�p�ɨ����
     tPicker.setIs24HourView(new Boolean(true)); 
     
     //�i��ƥ�e�~�B�z
     tPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
		public void onTimeChanged(TimePicker timePicker, int hh, int mn) {
		 txt2.setText("�A��ܪ��ɶ��G" + hh + ":" + mn); 	
		}
	 });
     
    }
}