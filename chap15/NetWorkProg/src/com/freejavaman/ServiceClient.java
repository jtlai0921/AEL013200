package com.freejavaman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceClient extends Activity {

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
	doWebService("add");
   }
  });
  
  //執行減法運算
  btn2.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doWebService("dec");
   }
  });
  
  //執行乘法運算
  btn3.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doWebService("multiple");
   }
  });  
  
  //執行除法運算
  btn4.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doWebService("div");
   }
  });
 }
 
 //執行下載HTML的內容
 private void doWebService(String actType){
   try {	 
	//指向提供服務的URL
	URL url = new URL("http://192.168.1.100:8080/AndroidWeb/AndroidServlet");
	URLConnection urlCONN = url.openConnection();
	urlCONN.setDoOutput(true);
	
    //組合所欲傳遞的參數
    String data = "actType=" + actType + "&" + 
	              "xValue=" + xValue.getText() + "&" +
                  "yValue=" + yValue.getText();
	
    //建立輸出資料流，並提交參數
    OutputStreamWriter wr = new OutputStreamWriter(urlCONN.getOutputStream(), "Big5");
	wr.write(data);
	wr.flush();
			    
	//取得伺服端的執行結果
	BufferedReader rd = new BufferedReader(new InputStreamReader(urlCONN.getInputStream()));
	String returnMsg = "";
	String line;

	//組合所有執行結果
	while ((line = rd.readLine()) != null) {
	 returnMsg += line;
	}
	
	//關閉輸出入資料流
    wr.close();
	rd.close();
	wr = null;
	rd = null;
	
	Log.v("network", "returnMsg:" + returnMsg);
	
	//顯示執行結果
	if (returnMsg.indexOf("<Error>") != -1) {
	 //執行結果發生問題
	 reslut.setText("運算過程發生問題");
	} else if (returnMsg.indexOf("<Result>") != -1) {
	 //取得運算結果的字串索引值
	 int sInx = returnMsg.indexOf("<Result>");
	 sInx += "<Result>".length();	 
	 int eInx = returnMsg.indexOf("</Result>");
	 
	 Log.v("network", "sInx:" + sInx);
	 Log.v("network", "eInx:" + eInx);
	 
	 reslut.setText(returnMsg.substring(sInx, eInx));
	 
	} else {
	 //無法判斷伺服端執行結果	
     reslut.setText("無法判斷執行結果");
	}
	
   } catch (Exception e) {
	reslut.setText("執行錯誤：" + e);
   }
 }
 
}