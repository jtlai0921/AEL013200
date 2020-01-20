package com.freejavaman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SDCardWriter extends Activity {
  
  EditText edit;
  Button saveBtn;
		 
  public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
		  
   edit = (EditText)this.findViewById(R.id.edit);
   saveBtn = (Button)this.findViewById(R.id.saveBtn);
		  
   //�I����s�A�������x�s
   saveBtn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 try {	
  	  //�}���ɮ׸�Ƭy
	  //File file = new File("/sdcard/sdcardFile.txt");
	  File file = new File("/data/myFile.txt");
	  FileOutputStream fout = new FileOutputStream(file);
	  
	  //�g�X�ɮ�
      fout.write(edit.getText().toString().getBytes());
	  fout.close();
	  Log.v("file", "write file ok");
	 } catch (Exception e) {
	  Log.e("file", "write file err:" + e);	 
	 }
	}
   });
  }

  protected void onStart() {
   super.onStart();
    try {
	 //�}���ɮ׿�J��Ƭy
     //File file = new File("/sdcard/sdcardFile.txt");
     File file = new File("/data/myFile.txt");
	 FileInputStream fin = new FileInputStream(file);
	    
	 //���o�ɮפ��e����
	 byte[] datas = new byte[fin.available()];
	 fin.read(datas);
	    
	 //���^�Ȧs�����, ����ܪ���J��
	 edit.setText(new String(datas));
	 
	 fin.close();
	 Log.v("file", "read file ok");
    }catch (Exception e) {	  
	 Log.e("file", "read file err:" + e);	  
	}
  } 
}