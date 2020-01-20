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

 //多選選項	
 public CheckBox box1, box2, box3, box4, box5;
 public CheckBox box6, box7, box8, box9, box10;
 
 //排程按鈕
 private Button btn1;
 
 //儲存染色體字串
 private String chromosomeStr = "";
 
 //記錄選取客戶數量
 private int selectLocation = 0;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //重設染色體字串與選取客戶數量
  chromosomeStr = "";
  selectLocation = 0;
  
  //取得選單物件之實體
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
  
  //取得按鈕物件之實體 
  btn1 = (Button)findViewById(R.id.btn1);
  
  //建立傾聽者物件，並傳入Activity做為參考值
  CheckBoxListener myListener = new CheckBoxListener(this);
	   
  //委託OnCheckedChange事件
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
  
  //委任按鈕事件
  btn1.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	//進行排程   
	planSchedular();
   }
  });  
 }
 
 //提交排程資訊給伺服端
 private void planSchedular(){
  //判斷是否選定三個以上的地區	 
  if (selectLocation <= 3) {
	Toast.makeText(this, "請選擇三個以上的地區", Toast.LENGTH_SHORT).show();
  } else {
    //確認可以提交資料
	  
	//避免重覆按鈕  
	btn1.setEnabled(false);  
	try{
	 //建立與伺服端的連線
	 Socket socket = new Socket("192.168.1.107", 16888);
	 socket.setSoTimeout(1000 * 60 * 5);
	 
	 //取得對伺服端的資料流
	 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	 DataInputStream dis = new DataInputStream(socket.getInputStream());
			    
	 //提交運算資料    
	 dos.writeBytes(chromosomeStr + "\n");
			    
	 //接受伺服端的排程結果
	 String reslutStr = dis.readLine();	 
	 Log.v("network", "planSchedular reslutStr:" + reslutStr);
	 
	 if (reslutStr != null && !reslutStr.startsWith("ERR")) {
	  //正確取得排程資料
	  Toast.makeText(this, "開始進行排程", Toast.LENGTH_LONG).show();
	  displayResult(reslutStr);	 
	 } else {
	  //排程時發生錯誤 
	  Toast.makeText(this, "伺服端排程錯誤", Toast.LENGTH_LONG).show();
	 }
	} catch(IOException e){
	 Log.e("network", "planSchedular error:" + e);	
	 Toast.makeText(this, "網路錯誤", Toast.LENGTH_LONG).show();
	}
	
	//重新啟用按鈕  
	btn1.setEnabled(true);
  } 
 }
 
 //進行字串剖析, 並前往結果顯示頁
 private void displayResult(String reslutStr){
  String geneStr = reslutStr.substring(reslutStr.indexOf("result:") + 1, reslutStr.indexOf("_"));
  Log.e("network", "displayResult geneStr:" + geneStr);
  
  //判斷是否取得排程資料 
  if (geneStr != null && !geneStr.equals("")) {
   //儲存拜訪順序的字串物件 	  
   StringBuffer msg = new StringBuffer("拜訪順序:\n");
   
   char[] geneArray = geneStr.toCharArray();	
   for (int i = 0; i < geneArray.length; i++) {
	if ("A".equals("" + geneArray[i]))
	 msg.append("新光三越\n");
	
	if ("B".equals("" + geneArray[i]))
	  msg.append("太平洋SOGO\n");
		  
    if ("C".equals("" + geneArray[i]))
	  msg.append("京華城\n");

    if ("D".equals("" + geneArray[i]))
	  msg.append("大葉高島屋\n");
		  
	if ("E".equals("" + geneArray[i]))
	  msg.append("京站時尚廣場\n");
		  
	if ("F".equals("" + geneArray[i]))
	  msg.append("微風廣場\n");
		  
	if ("G".equals("" + geneArray[i]))
	  msg.append("遠東百貨\n");
		  
	if ("H".equals("" + geneArray[i]))
	  msg.append("BELLAVITA\n");
		  
	 if ("I".equals("" + geneArray[i]))
	  msg.append("美麗華百樂園\n");
		  
	 if ("J".equals("" + geneArray[i]))
	  msg.append("明曜百貨\n");	
   }
   
   //前往顯示結果頁
   Intent intent = new Intent();

   //設定from與to的Activity
   intent.setClass(this, ResultActivity.class);

   //建立欲傳遞的資料
   Bundle bundle = new Bundle();
   bundle.putString("result", msg.toString());

   //將資料設定至Intent物件之中
   intent.putExtras(bundle);

   GeneActivity.this.startActivity(intent);
   GeneActivity.this.finish();	  
  } else {
   Toast.makeText(this, "排程資料錯誤", Toast.LENGTH_LONG).show();  
  }
 }
 
 //取得染色體字串
 public String getChromosomeStr() {
  return chromosomeStr;
 }
 
 //設定染色體字串
 public void setChromosomeStr(String chromosomeStr) {
  this.chromosomeStr = chromosomeStr;
 }
 
 //取得選取拜訪地區數量
 public int getSelectLocation() {
  return selectLocation;
 }

 //設定拜訪地區數量
 public void setSelectLocation(int selectLocation) {
  this.selectLocation = selectLocation;
 }
 
 
}

	