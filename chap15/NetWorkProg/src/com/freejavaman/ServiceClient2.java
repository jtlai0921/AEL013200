package com.freejavaman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceClient2 extends Activity {

 //提供輸出運算參數	
 EditText xValue, yValue;
 TextView reslut;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
  
  //取得資料輸入與顯示之物件
  xValue = (EditText)this.findViewById(R.id.xValue);
  yValue = (EditText)this.findViewById(R.id.yValue);
  reslut = (TextView)this.findViewById(R.id.reslut);
  
  //執行運算之按鈕
  Button btn1 = (Button)this.findViewById(R.id.btn1);
  Button btn2 = (Button)this.findViewById(R.id.btn2);
  Button btn3 = (Button)this.findViewById(R.id.btn3);
  Button btn4 = (Button)this.findViewById(R.id.btn4);
  
  //執行加法運算
  btn1.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("add");
   }
  });
  
  //執行減法運算
  btn2.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("dec");
   }
  });
  
  //執行乘法運算
  btn3.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("multiple");
   }
  });  
  
  //執行除法運算
  btn4.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("div");
   }
  });
 }
 
 //執行下載HTML的內容
 private void doTCPService(String actType){
  try{
   //建立與伺服端的連線
   Socket socket = new Socket("192.168.1.100", 16888);
	    
   //取得對伺服端的資料流
   DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
   DataInputStream dis = new DataInputStream(socket.getInputStream());
	    
   //提交運算資料    
   dos.writeBytes(actType + "_" + xValue.getText() + "_" + yValue.getText() + "\n");
	    
   //接受並顯示訊息
   String reslutStr = dis.readLine();
   Log.v("network", "doTCPService reslutStr:" + reslutStr);
   reslut.setText(reslutStr);
  } catch(IOException e){
   Log.e("network", "doTCPService error:" + e);
  }  
 }
 
}