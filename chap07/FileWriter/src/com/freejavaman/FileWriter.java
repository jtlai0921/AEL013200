package com.freejavaman;

import java.io.*;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class FileWriter extends Activity {
  EditText edit;
  Button saveBtn;
	 
  public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
	  
   edit = (EditText)this.findViewById(R.id.edit);
   saveBtn = (Button)this.findViewById(R.id.saveBtn);
	  
   //點選按鈕，執行資料儲存
   saveBtn.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	 try {	
  	  //取得使用者輸入的資料      
	  FileOutputStream fout = FileWriter.this.openFileOutput("myFile", Activity.MODE_WORLD_READABLE);
      fout.write(edit.getText().toString().getBytes());
      fout.close();
      Log.v("file", "write file ok");
	 }catch (Exception e) {
	  Log.e("file", "write file err:" + e);	 
	 }
	}
  });
 }

 protected void onStart() {
  super.onStart();
  try {
	   
    //開啟檔案輸入資料流
    FileInputStream fin = FileWriter.this.openFileInput("myFile");
    
    //取得檔案內容長度
    byte[] datas = new byte[fin.available()];
    fin.read(datas);
    
    //取回暫存的資料, 並顯示的輸入框
    edit.setText(new String(datas));
    
    fin.close();
    Log.v("file", "read file ok");
  }catch (Exception e) {	  
   Log.e("file", "read file err:" + e);	  
  }
 } 
}