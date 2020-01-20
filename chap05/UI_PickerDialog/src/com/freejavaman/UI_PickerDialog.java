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
     
     //建立TextView與Button物件實體
     txt1 = (TextView)this.findViewById(R.id.txt1);
     txt2 = (TextView)this.findViewById(R.id.txt2);
     Button btn1 = (Button)this.findViewById(R.id.btn1);
     Button btn2 = (Button)this.findViewById(R.id.btn2);
     
     //點選按鈕時，顯示DatePickerDialog
     btn1.setOnClickListener(new View.OnClickListener() {
      //點選第一個按鈕
      public void onClick(View view) {
    	 //建立 DatePickerDialog之物件實體
    	 new DatePickerDialog(UI_PickerDialog.this, new OnDateSetListener() {
    		//日期被設定選擇
			public void onDateSet(DatePicker dPicker, int yyyy, int mm, int dd) {
			 txt1.setText("你選擇的日期：" + yyyy + "/" + (mm + 1) + "/" + dd);
			}
		}, 2011, 5, 8).show();
	  }
	 });
     
     //點選按鈕時，顯示TimePickerDialog
     btn2.setOnClickListener(new View.OnClickListener() {
       //點選第二個按鈕	 
       public void onClick(View view) {
    	 //建立 TimePickerDialog之物件實體
       	 new TimePickerDialog(UI_PickerDialog.this, new OnTimeSetListener() {
       	    //時間被設定選擇
   			public void onTimeSet(TimePicker tPicker, int hh, int mn) {
   			 txt2.setText("你選擇的時間：" + hh + ":" + mn);
   			}
   	    }, 13, 14, true).show();
   	  }
   	 });
     
    }
}