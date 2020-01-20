package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class UI_CheckBox extends Activity {
	
  public TextView txt1;	
  public CheckBox box1, box2, box3;
	
  public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
     
   //取得相關物件之實體
   txt1 = (TextView)findViewById(R.id.txt1);
   box1 = (CheckBox)findViewById(R.id.box1);
   box2 = (CheckBox)findViewById(R.id.box2);
   box3 = (CheckBox)findViewById(R.id.box3);
     
   //建立傾聽者物件，並傳入Activity做為參考值
   MyListener myListener = new MyListener(this);
   
   //委託OnCheckedChange事件
   box1.setOnCheckedChangeListener(myListener);
   box2.setOnCheckedChangeListener(myListener);
   box3.setOnCheckedChangeListener(myListener);
 }
}

//實作OnCheckedChangeListener的物件
class MyListener implements CheckBox.OnCheckedChangeListener {
 UI_CheckBox myActivity;
 
 //取得Activity物件之參考值
 public MyListener(UI_CheckBox myActivity) {
  this.myActivity = myActivity;
 }
 
 public void onCheckedChanged(CompoundButton cBtn, boolean isChecked) {
  String str = "具備的技能：";
  
  //判斷有那些選項被選取	 
  if (myActivity.box1.isChecked())
    str += "Android ";
  
  if (myActivity.box2.isChecked())
	str += "雲端 ";
  
  if (myActivity.box3.isChecked())
	str += "Java ";
  
  //顯示訊息
  myActivity.txt1.setText(str);
 } 
}