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
    
    //取得物件實體
    txt = (TextView)this.findViewById(R.id.txt);
  }
  
  protected void onStart() {
   super.onStart();
   try {
		   
	//開啟檔案輸入資料流
	Context saveContext = createPackageContext("com.freejavaman", Activity.MODE_WORLD_READABLE);
	FileInputStream fin = saveContext.openFileInput("myFile");
	    
	//取得檔案內容長度
	byte[] datas = new byte[fin.available()];
	fin.read(datas);
	    
	//取回暫存的資料, 並顯示的輸入框
	txt.setText("儲存的資料：" + new String(datas));
	
	fin.close();
    Log.v("file", "read file ok");
   }catch (Exception e) {	  
	Log.e("file", "read file err:" + e);	  
   }
  } 
  
}