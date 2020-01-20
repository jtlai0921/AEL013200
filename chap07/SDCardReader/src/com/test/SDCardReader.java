package com.test;

import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SDCardReader extends Activity {

 TextView txt;
	   
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
	    
  //���o�������
  txt = (TextView)this.findViewById(R.id.txt);
 }
	  
 protected void onStart() {
  super.onStart();
   try {			   
	//�}���ɮ׿�J��Ƭy
	File file = new File("/sdcard/sdcardFile.txt");
    FileInputStream fin = new FileInputStream(file);
		    
	//���o�ɮפ��e����
	byte[] datas = new byte[fin.available()];
	fin.read(datas);
		    
	//���^�Ȧs�����, ����ܪ���J��
	txt.setText("�x�s����ơG" + new String(datas));
		
	fin.close();
	Log.v("file", "read file ok");
   } catch (Exception e) {	  
 	Log.e("file", "read file err:" + e);	  
   }
  } 
}