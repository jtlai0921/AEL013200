package com.freejavaman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ServiceServer extends Activity {
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try{
   //建立ServerSocket物件, 傾聽於16888埠
   ServerSocket srvSocket = new ServerSocket(16888);
	
   Log.v("network", "android server start");
   
   //等待使用者輸入
   while (true) {
    Socket socket = srvSocket.accept(); 
    ServiceObject myService = new ServiceObject(socket);
   }
  } catch(IOException e){
   Log.e("network", "android server error:" + e);
  }
 }
}

class ServiceObject extends Thread {
	 
 //與客戶端建立的資料流
 DataInputStream dis;
 DataOutputStream dos;
		    
 public ServiceObject(Socket socket){	  
  try {	
   //取得與客戶端之間的資料流
   dis = new DataInputStream(socket.getInputStream());
   dos = new DataOutputStream(socket.getOutputStream());    	
		  
   //開始執行緒
   start();
  } catch (Exception e){
   System.out.println(e);	
  } 
 }
		 
 public void run(){  
  try {   
   while (true) {
    //客戶端所傳遞的運算資料
    String datas = dis.readLine();
    Log.v("network", "android server data:" + datas);
	    
    //回傳執行結果
    dos.writeBytes("return by server" + datas + "\n");    
   }
  } catch (Exception e){
	  Log.e("network", "android server(run) error:" + e);	
  } 
 }
}