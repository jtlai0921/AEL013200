package com.freejavaman;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UI_Dialog extends Activity {
	
  TextView txt1;
  String[] stations = {"�x�_", "���", "�s��", "�]��", "�x��", "����", "���L", "�Ÿq", "�x�n", "����", "�̪F"};
  boolean[] isChk = {false,false,false,false,false,false,false,false,false,false,false,};
  
  AlertDialog.Builder builder;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
        
    //�إ�TextView�PButton�������
    txt1 = (TextView)this.findViewById(R.id.txt1);        
    Button btn1 = (Button)this.findViewById(R.id.btn1);
      
    //�I����s���AlertDialog
    btn1.setOnClickListener(new View.OnClickListener() {
     //�I����s
     public void onClick(View view) {
      AlertDialog.Builder builder = new AlertDialog.Builder(UI_Dialog.this);
      
      /*builder.setTitle("��lAlertDialog");
      builder.setMessage("�S������׹�");
      builder.show();  */
       
    	 
      //��²�y�k 	 
      //new AlertDialog.Builder(UI_Dialog.this).setTitle("��lAlertDialog").setMessage("�S������׹�").show();
      
      //�㦳�T�ӫ��s����ܮ�
      /*AlertDialog.Builder builder = new AlertDialog.Builder(UI_Dialog.this);
      builder.setTitle("����s�\��");
      builder.setMessage("�T�ӥ\��s");      
      builder.setPositiveButton("����s", null);
      builder.setNeutralButton("�����s", null);
      builder.setNegativeButton("�k��s", null);
      builder.show();*/
            
      //builder = new AlertDialog.Builder(UI_Dialog.this);
      //builder.setTitle("��C����ܮ�");
      /*builder.setMessage("�X�o�����G");      
      builder.setPositiveButton("����s", null);
      builder.setNeutralButton("�����s", null);
      builder.setNegativeButton("�k��s", null);*/
      
      //�إߦC��ﶵ      
      /*builder.setItems(stations, new DialogInterface.OnClickListener(){
      public void onClick(DialogInterface dialog, int inx) {
 		 txt1.setText("�z��ܪ��O" + stations[inx]);
 		} 
       });*/
      
      //��ܨ���C����ܮ�
      /*builder.setSingleChoiceItems(stations, 0, new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog, int inx) {		 
		 txt1.setText("�z��ܪ��O" + stations[inx]);
		} 
      });
      builder.show();*/
      
      //��ܨ�h��C����ܮ�
      builder.setMultiChoiceItems(stations, isChk, new DialogInterface.OnMultiChoiceClickListener(){
		public void onClick(DialogInterface dialog, int inx, boolean isChked) {		 
		 //txt1.setText("�z��ܪ��O" + stations[inx]);
		 Log.v("dialogTest", "do here");
		 
		 if (isChked) {
		   Log.v("dialogTest", "�z��ܪ��O" + stations[inx]);	 
		 } else {
		   Log.v("dialogTest", "�z�������O" + stations[inx]);
		 }
		} 
      });
      builder.show();
      
 	 }
 	});      
  }
}