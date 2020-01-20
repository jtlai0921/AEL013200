package com.freejavaman;

import android.widget.CheckBox;
import android.widget.CompoundButton;

//處理CheckBox點選事件的物件
public class CheckBoxListener implements CheckBox.OnCheckedChangeListener {
 GeneActivity papa;
		 
 //取得Activity物件之參考值
 public CheckBoxListener(GeneActivity papa) {
  this.papa = papa;
 }
 
 //使用者點選CheckBox選項
 public void onCheckedChanged(CompoundButton cBtn, boolean isChecked) {
  //判斷有那些選項被選取
  StringBuffer str = new StringBuffer("");	 
  
  //統計選取的數量
  int selLocal = 0;
  
  //動態組成拜訪資料字串
  if (papa.box1.isChecked()) {
    str.append("A_");
    selLocal++;
  } 
		  
  if (papa.box2.isChecked()) {
	str.append("B_");
    selLocal++;
  }	
  
  if (papa.box3.isChecked()) {
	str.append("C_");
    selLocal++;
  }
  
  if (papa.box4.isChecked()) {
	str.append("D_");
    selLocal++;
  }
  
  if (papa.box5.isChecked()) {
	str.append("E_");
    selLocal++;
  }
  
  if (papa.box6.isChecked()) {
    str.append("F_");
    selLocal++;
  }	
  
  if (papa.box7.isChecked()) {
	str.append("G_");
    selLocal++;
  }	
  
  if (papa.box8.isChecked()) {
	str.append("H_");
    selLocal++;
  }  
  
  if (papa.box9.isChecked()) {
	str.append("I_");
    selLocal++;
  } 
  
  if (papa.box10.isChecked()) {
	str.append("J_");
    selLocal++;
  }  
  
  //更新拜訪資料字串,並刪除最後的底線
  papa.setChromosomeStr(str.toString().substring(0, str.toString().length() - 1));
  
  //設定選取的拜訪地區數量
  papa.setSelectLocation(selLocal);
 }	
}
