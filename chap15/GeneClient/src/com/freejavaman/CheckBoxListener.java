package com.freejavaman;

import android.widget.CheckBox;
import android.widget.CompoundButton;

//�B�zCheckBox�I��ƥ󪺪���
public class CheckBoxListener implements CheckBox.OnCheckedChangeListener {
 GeneActivity papa;
		 
 //���oActivity���󤧰Ѧҭ�
 public CheckBoxListener(GeneActivity papa) {
  this.papa = papa;
 }
 
 //�ϥΪ��I��CheckBox�ﶵ
 public void onCheckedChanged(CompoundButton cBtn, boolean isChecked) {
  //�P�_�����ǿﶵ�Q���
  StringBuffer str = new StringBuffer("");	 
  
  //�έp������ƶq
  int selLocal = 0;
  
  //�ʺA�զ����X��Ʀr��
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
  
  //��s���X��Ʀr��,�çR���̫᪺���u
  papa.setChromosomeStr(str.toString().substring(0, str.toString().length() - 1));
  
  //�]�w��������X�a�ϼƶq
  papa.setSelectLocation(selLocal);
 }	
}
