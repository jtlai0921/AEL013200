package com.test;

import java.io.FileInputStream;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FileReader extends Activity {
  
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
	Context saveContext = createPackageContext("com.freejavaman", Activity.MODE_WORLD_READABLE);
	FileInputStream fin = saveContext.openFileInput("myFile");
	    
	//���o�ɮפ��e����
	byte[] datas = new byte[fin.available()];
	fin.read(datas);
	    
	//���^�Ȧs�����, ����ܪ���J��
	txt.setText("�x�s����ơG" + new String(datas));
	
	fin.close();
    Log.v("file", "read file ok");
   }catch (Exception e) {	  
	Log.e("file", "read file err:" + e);	  
   }
  } 
  
}