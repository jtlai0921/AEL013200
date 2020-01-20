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
     
     //取得相關物件之實體
     txt1 = (TextView)findViewById(R.id.txt1);
     txt2 = (TextView)findViewById(R.id.txt2);
     dPicker = (DatePicker)findViewById(R.id.dPicker);
     tPicker = (TimePicker)findViewById(R.id.tPicker);
     
     //設定日期
     //dPicker.updateDate(2011, 6, 8);
     
     //設定啟動時的日期，以及委外事件處理
     dPicker.init(2011, 5, 8, new DatePicker.OnDateChangedListener(){		
   	  public void onDateChanged(DatePicker datePicker, int yyyy, int mm, int dd) {
   	   txt1.setText("你選擇的日期：" + yyyy + "/" + (mm + 1) + "/" + dd);	  
   	  }    	 
     });
     
     //設定時間
     tPicker.setCurrentHour(new Integer(13)); 
     tPicker.setCurrentMinute(new Integer(14));
     
     //設定以24小時制顯示
     tPicker.setIs24HourView(new Boolean(true)); 
     
     //進行事件委外處理
     tPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
		public void onTimeChanged(TimePicker timePicker, int hh, int mn) {
		 txt2.setText("你選擇的時間：" + hh + ":" + mn); 	
		}
	 });
     
    }
}