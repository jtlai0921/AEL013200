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
     
   //���o�������󤧹���
   txt1 = (TextView)findViewById(R.id.txt1);
   box1 = (CheckBox)findViewById(R.id.box1);
   box2 = (CheckBox)findViewById(R.id.box2);
   box3 = (CheckBox)findViewById(R.id.box3);
     
   //�إ߶�ť�̪���A�öǤJActivity�����Ѧҭ�
   MyListener myListener = new MyListener(this);
   
   //�e�UOnCheckedChange�ƥ�
   box1.setOnCheckedChangeListener(myListener);
   box2.setOnCheckedChangeListener(myListener);
   box3.setOnCheckedChangeListener(myListener);
 }
}

//��@OnCheckedChangeListener������
class MyListener implements CheckBox.OnCheckedChangeListener {
 UI_CheckBox myActivity;
 
 //���oActivity���󤧰Ѧҭ�
 public MyListener(UI_CheckBox myActivity) {
  this.myActivity = myActivity;
 }
 
 public void onCheckedChanged(CompoundButton cBtn, boolean isChecked) {
  String str = "��ƪ��ޯ�G";
  
  //�P�_�����ǿﶵ�Q���	 
  if (myActivity.box1.isChecked())
    str += "Android ";
  
  if (myActivity.box2.isChecked())
	str += "���� ";
  
  if (myActivity.box3.isChecked())
	str += "Java ";
  
  //��ܰT��
  myActivity.txt1.setText(str);
 } 
}