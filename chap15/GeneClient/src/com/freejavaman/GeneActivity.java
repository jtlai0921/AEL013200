package com.freejavaman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class GeneActivity extends Activity {

 //�h��ﶵ	
 public CheckBox box1, box2, box3, box4, box5;
 public CheckBox box6, box7, box8, box9, box10;
 
 //�Ƶ{���s
 private Button btn1;
 
 //�x�s�V����r��
 private String chromosomeStr = "";
 
 //�O������Ȥ�ƶq
 private int selectLocation = 0;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //���]�V����r��P����Ȥ�ƶq
  chromosomeStr = "";
  selectLocation = 0;
  
  //���o��檫�󤧹���
  box1 = (CheckBox)findViewById(R.id.box1);
  box2 = (CheckBox)findViewById(R.id.box2);
  box3 = (CheckBox)findViewById(R.id.box3);
  box4 = (CheckBox)findViewById(R.id.box4);
  box5 = (CheckBox)findViewById(R.id.box5);
  box6 = (CheckBox)findViewById(R.id.box6);
  box7 = (CheckBox)findViewById(R.id.box7);
  box8 = (CheckBox)findViewById(R.id.box8);
  box9 = (CheckBox)findViewById(R.id.box9);
  box10 = (CheckBox)findViewById(R.id.box10);
  
  //���o���s���󤧹��� 
  btn1 = (Button)findViewById(R.id.btn1);
  
  //�إ߶�ť�̪���A�öǤJActivity�����Ѧҭ�
  CheckBoxListener myListener = new CheckBoxListener(this);
	   
  //�e�UOnCheckedChange�ƥ�
  box1.setOnCheckedChangeListener(myListener);
  box2.setOnCheckedChangeListener(myListener);
  box3.setOnCheckedChangeListener(myListener);
  box4.setOnCheckedChangeListener(myListener);
  box5.setOnCheckedChangeListener(myListener);
  box6.setOnCheckedChangeListener(myListener);
  box7.setOnCheckedChangeListener(myListener);
  box8.setOnCheckedChangeListener(myListener);
  box9.setOnCheckedChangeListener(myListener);
  box10.setOnCheckedChangeListener(myListener);
  
  //�e�����s�ƥ�
  btn1.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	//�i��Ƶ{   
	planSchedular();
   }
  });  
 }
 
 //����Ƶ{��T�����A��
 private void planSchedular(){
  //�P�_�O�_��w�T�ӥH�W���a��	 
  if (selectLocation <= 3) {
	Toast.makeText(this, "�п�ܤT�ӥH�W���a��", Toast.LENGTH_SHORT).show();
  } else {
    //�T�{�i�H������
	  
	//�קK���Ы��s  
	btn1.setEnabled(false);  
	try{
	 //�إ߻P���A�ݪ��s�u
	 Socket socket = new Socket("192.168.1.107", 16888);
	 socket.setSoTimeout(1000 * 60 * 5);
	 
	 //���o����A�ݪ���Ƭy
	 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	 DataInputStream dis = new DataInputStream(socket.getInputStream());
			    
	 //����B����    
	 dos.writeBytes(chromosomeStr + "\n");
			    
	 //�������A�ݪ��Ƶ{���G
	 String reslutStr = dis.readLine();	 
	 Log.v("network", "planSchedular reslutStr:" + reslutStr);
	 
	 if (reslutStr != null && !reslutStr.startsWith("ERR")) {
	  //���T���o�Ƶ{���
	  Toast.makeText(this, "�}�l�i��Ƶ{", Toast.LENGTH_LONG).show();
	  displayResult(reslutStr);	 
	 } else {
	  //�Ƶ{�ɵo�Ϳ��~ 
	  Toast.makeText(this, "���A�ݱƵ{���~", Toast.LENGTH_LONG).show();
	 }
	} catch(IOException e){
	 Log.e("network", "planSchedular error:" + e);	
	 Toast.makeText(this, "�������~", Toast.LENGTH_LONG).show();
	}
	
	//���s�ҥΫ��s  
	btn1.setEnabled(true);
  } 
 }
 
 //�i��r���R, �ëe�����G��ܭ�
 private void displayResult(String reslutStr){
  String geneStr = reslutStr.substring(reslutStr.indexOf("result:") + 1, reslutStr.indexOf("_"));
  Log.e("network", "displayResult geneStr:" + geneStr);
  
  //�P�_�O�_���o�Ƶ{��� 
  if (geneStr != null && !geneStr.equals("")) {
   //�x�s���X���Ǫ��r�ꪫ�� 	  
   StringBuffer msg = new StringBuffer("���X����:\n");
   
   char[] geneArray = geneStr.toCharArray();	
   for (int i = 0; i < geneArray.length; i++) {
	if ("A".equals("" + geneArray[i]))
	 msg.append("�s���T�V\n");
	
	if ("B".equals("" + geneArray[i]))
	  msg.append("�ӥ��vSOGO\n");
		  
    if ("C".equals("" + geneArray[i]))
	  msg.append("�ʵث�\n");

    if ("D".equals("" + geneArray[i]))
	  msg.append("�j�����q��\n");
		  
	if ("E".equals("" + geneArray[i]))
	  msg.append("�ʯ��ɩ|�s��\n");
		  
	if ("F".equals("" + geneArray[i]))
	  msg.append("�L���s��\n");
		  
	if ("G".equals("" + geneArray[i]))
	  msg.append("���F�ʳf\n");
		  
	if ("H".equals("" + geneArray[i]))
	  msg.append("BELLAVITA\n");
		  
	 if ("I".equals("" + geneArray[i]))
	  msg.append("���R�ئʼֶ�\n");
		  
	 if ("J".equals("" + geneArray[i]))
	  msg.append("���`�ʳf\n");	
   }
   
   //�e����ܵ��G��
   Intent intent = new Intent();

   //�]�wfrom�Pto��Activity
   intent.setClass(this, ResultActivity.class);

   //�إ߱��ǻ������
   Bundle bundle = new Bundle();
   bundle.putString("result", msg.toString());

   //�N��Ƴ]�w��Intent���󤧤�
   intent.putExtras(bundle);

   GeneActivity.this.startActivity(intent);
   GeneActivity.this.finish();	  
  } else {
   Toast.makeText(this, "�Ƶ{��ƿ��~", Toast.LENGTH_LONG).show();  
  }
 }
 
 //���o�V����r��
 public String getChromosomeStr() {
  return chromosomeStr;
 }
 
 //�]�w�V����r��
 public void setChromosomeStr(String chromosomeStr) {
  this.chromosomeStr = chromosomeStr;
 }
 
 //���o������X�a�ϼƶq
 public int getSelectLocation() {
  return selectLocation;
 }

 //�]�w���X�a�ϼƶq
 public void setSelectLocation(int selectLocation) {
  this.selectLocation = selectLocation;
 }
 
 
}

	